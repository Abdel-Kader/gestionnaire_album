package metiers;
import dao.AlbumDao;
import dao.ExceptionDao;
import dao.UserDao;
import entities.Album;
import entities.TypeAlbum;
import entities.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;


public final class AlbumForm
{
    private static final String CHAMP_THEME = "theme";
    private static final String CHAMP_TYPE = "type_album";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();
    private AlbumDao albumDao;
    private UserDao userDao;

    public AlbumForm(AlbumDao albumDao)
    {
        this.albumDao = albumDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    public String getResultat() {
        return resultat;
    }

    Long proprietaire_id;
    Utilisateur id_proprietaire = new Utilisateur();
    Utilisateur user;

    public Album addAlbum(HttpServletRequest request)
    {
        String theme = getValeurChamp(request, CHAMP_THEME);
        TypeAlbum typeAlbum = new TypeAlbum();
        String[] users_param =  request.getParameterValues("user");

        Long type_album = Long.parseLong(getValeurChamp(request, CHAMP_TYPE));
        typeAlbum.setId(type_album);
        HttpSession session = request.getSession(false);
        if(session != null)
        {
            user = (Utilisateur) session.getAttribute("utilisateur");
            proprietaire_id = user.getId();
        }
        id_proprietaire.setId(proprietaire_id);
        Album album = new Album();

        try
        {
            traiterTheme(theme, typeAlbum, id_proprietaire, album);
            traiterTypeTheme(type_album, album);
            if(users_param != null)
            {
                Long[] users_id = new Long[users_param.length];
                for(int i = 0; i<users_param.length; i++ )
                {
                    Utilisateur user;
                    users_id[i] = Long.parseLong(users_param[i]);
                    user = albumDao.findUserById(users_id[i]);
                    album.getUtilisateurs().add(user);
                }
            }

            if (erreurs.isEmpty())
            {
                albumDao.addAlbum(album);
                resultat = "Album ajouté avec succès";
            }
            else
            {
                resultat = "Echec de l'ajout de l'album !";
            }
        } catch (ExceptionDao e)
        {
            resultat = "Une erreur s'est produite lors de l'ajout de l'album !";
            e.printStackTrace();
        }

        return album;
    }

    public List<Album> getUserAlbums(Utilisateur user)
    {
       return albumDao.getUserAlbums(user);

    }

    public Long CountAllAlbum()
    {
        return albumDao.CountAllAlbum();
    }

    public Album editAlbum(Album albumToUpdate)
    {
        return albumDao.editAlbum(albumToUpdate);
    }

    public List<Album> getAllAlbum()
    {
        return albumDao.findAllAlbum();
    }

    public List<Album> getPublicAlbums(TypeAlbum type_album)
    {
        return albumDao.getPublicAlbums(type_album);
    }

    private void traiterTheme( String theme, TypeAlbum type_album, Utilisateur id_proprietaire, Album album )
    {
        try
        {
            validationTheme( theme, type_album, id_proprietaire );
        }
        catch ( ExceptionDao e )
        {
            setErreur( CHAMP_THEME, e.getMessage() );
        }
        album.setTheme( theme );

        album.setTitre(theme+"_"+timestamp.getTime());
        Utilisateur proprietaire = new Utilisateur();
        proprietaire.setId(proprietaire_id);
        album.setProprietaire(proprietaire);
    }

    private void traiterTypeTheme(Long id_type_album, Album album ) {
        try {
            validationType( id_type_album );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_TYPE, e.getMessage() );
        }

        TypeAlbum typeAlbum = new TypeAlbum();
        typeAlbum.setId(id_type_album);
        album.setType_album(typeAlbum);
    }

    private void validationTheme( String theme, TypeAlbum type_album, Utilisateur proprietaire ) throws ExceptionDao {
        if ( theme != null ) {
            if ( albumDao.checkIfAlbum( theme, type_album, proprietaire ) != null )
            {
                throw new ExceptionDao( "Cet album existe déjà, merci d'en choisir une autre !" );
            }
        } else {
            throw new ExceptionDao( "Ce champ est obligatoire. Merci de le remplir" );
        }
    }

    private void validationType( Long type_album ) throws ExceptionDao {
        if ( type_album == null ) {
            throw new ExceptionDao( "Ce champ est obligatoire." );
        }
    }

    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 )
        {
            return null;
        } else {
            return valeur;
        }
    }

    public Album findAlbumById(Long id)
    {
        return albumDao.findAlbumById(id);
    }

    public void deleteAlbum(Album album)
    {
        albumDao.deleteAlbum(album);
    }

}
