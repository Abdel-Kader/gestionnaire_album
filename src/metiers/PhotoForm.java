package metiers;

import dao.ExceptionDao;
import dao.PhotoDao;
import entities.Album;
import entities.Tag;
import entities.Photo;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoForm
{
    private static final String CHAMP_TITRE = "titre";
    private static final String CHAMP_DESCRIPTION = "description";
    private static final String CHAMP_HAUTEUR = "hauteur";
    private static final String CHAMP_LARGEUR = "largeur";
    private static final String CHAMP_SOURCE = "source";
    private static final String CHAMP_ALBUM = "album";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    private PhotoDao photoDao;

    public PhotoForm(PhotoDao photoDao)
    {
        this.photoDao = photoDao;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public String getResultat() {
        return resultat;
    }
    Long album_id;

    public Photo addPhoto(HttpServletRequest request, String fileName, int hauteur, int largeur)
    {
        String titre = getValeurChamp(request, CHAMP_TITRE);
        String description = getValeurChamp(request, CHAMP_DESCRIPTION);
        Long id_album = Long.parseLong(getValeurChamp(request, CHAMP_ALBUM));
        Photo photo = new Photo();

        String key_words =  request.getParameter("key_words");
        if(key_words != null)
        {
            String[] tokens = key_words.split(",");
            for(int i = 0; i<tokens.length; i++ )
            {
                Tag tag = new Tag();
                tag.setTag(tokens[i]);
                photoDao.addMot(tag);
                photo.getTags().add(tag);
            }
        }

        try
        {
            traiterTitre(titre, photo);
            traiterAlbum(id_album, photo);

            photo.setDescription( description );
            photo.setImg_source( fileName );
            photo.setHauteur( hauteur );
            photo.setLargeur( largeur );

            if (erreurs.isEmpty())
            {
                photoDao.addPhoto(photo);
                resultat = "Photo ajoutée avec succès";
            }
            else
            {
                resultat = "Echec de l'ajout de la photo !";
            }
        }
        catch (ExceptionDao e)
        {
            resultat = "Une erreur s'est produite lors de l'ajout de la photo!";
            e.printStackTrace();
        }

        return photo;

    }

    private void traiterTitre( String titre, Photo photo ) {
        try {
            validationTitre( titre );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_TITRE, e.getMessage() );
        }
        photo.setTitre( titre );
    }

    private void traiterAlbum(Long id_album, Photo photo ) {
        try {
            validationAlbum( id_album );
        } catch ( ExceptionDao e ) {
            setErreur( CHAMP_ALBUM, e.getMessage() );
        }

        Album album = new Album();
        album.setId(id_album);
        photo.setAlbum(album);
    }

    private void validationTitre( String titre ) throws ExceptionDao
    {
        if ( titre == null ) {
            throw new ExceptionDao( "Ce champ est obligatoire." );
        }
    }


    public Photo updatePhoto(HttpServletRequest request, String fileName)
    {
        String titre = getValeurChamp(request, CHAMP_TITRE);
        String description = getValeurChamp(request, CHAMP_DESCRIPTION);
        Photo photo = new Photo();

        try
        {
            traiterTitre(titre, photo);

            photo.setDescription( description );
            photo.setImg_source( fileName );

            if (erreurs.isEmpty())
            {
                photoDao.editPhoto(photo);
                resultat = "Photo modifiée avec succès";
            }
            else
            {
                resultat = "Echec de modification de la photo !";
            }
        }
        catch (ExceptionDao e)
        {
            resultat = "Une erreur s'est produite lors de la modification de la photo!";
            e.printStackTrace();
        }

        return photo;

    }


    public List<Photo> getPhotoAlbums(Album album)
    {
        return photoDao.getPhotoAlbums(album);
    }

    private void validationAlbum( Long album ) throws ExceptionDao {
        if ( album == null ) {
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

    public Photo findById(Long id)
    {
        return photoDao.findPhotoById(id);
    }

    public void deletePhoto(Photo photo)
    {
        photoDao.deletePhoto(photo);
    }
}
