package dao;
import entities.Album;
import entities.Tag;
import entities.TypeAlbum;
import entities.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
public class AlbumDao
{
    private static final String	SELECT_IF_ALBUM	= "SELECT a from Album a " +"WHERE a.theme = :theme and a.type_album = :type_album and a.proprietaire = :proprietaire";
    private static final String	SELECT_USER_ALBUMS	= "SELECT a from Album a " +"WHERE a.proprietaire = :proprietaire";
    private static final String	SELECT_ALBUM_BY_ID	= "SELECT a from Album a " +"WHERE a.id = :id";
    private static final String	FIND_ALL	= "SELECT a from Album a";
    private static final String	COUNT_ALL	= "SELECT count(a.id) from Album a";
    private static final String	SELECT_ALL_PUBLIC_ALBUMS = "SELECT a from Album a " +"WHERE a.type_album = :type_album";

    private static final String THEME_PARAM = "theme";
    private static final String TYPE_PARAM = "type_album";
    private static final String BY_ID_PARAM = "id";
    private static final String ID_PARAM = "proprietaire";


    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;


    Album album;

    public void addAlbum(Album album)
    {
        try
        {
            em.persist(album);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
    }

    public void addMot(Tag tag)
    {
        try
        {
            em.persist(tag);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
    }


    public Utilisateur findUserById(Long id)
    {
        return em.find(Utilisateur.class, id);
    }


    public Album findAlbumById(Long id)
    {
        return em.find(Album.class, id);
    }

    public List<Album> findAllAlbum()
    {
        Query query = em.createQuery(FIND_ALL);
        return query.getResultList();
    }

    public Album editAlbum(Album albumToUpdate)
    {
        Album album = null;
        try
        {
            album = em.merge(albumToUpdate);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return album;
    }

    public void deleteAlbum(Album album)
    {
        em.remove(editAlbum(album));
    }

    public String getById(Long id)
    {
        Query query = em.createQuery(SELECT_ALBUM_BY_ID);
        query.setParameter(BY_ID_PARAM, id);
        try
        {
            album = (Album) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return album.getTitre();
    }

    public Album checkIfAlbum(String theme, TypeAlbum type_album, Utilisateur proprietaire)
    {
        Query query = em.createQuery(SELECT_IF_ALBUM);
        query.setParameter(THEME_PARAM, theme);
        query.setParameter(TYPE_PARAM, type_album);
        query.setParameter(ID_PARAM, proprietaire);
        try
        {
            album = (Album) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return album;
    }

    public List<Album> getUserAlbums(Utilisateur proprietaire)
    {
        Query query = em.createQuery(SELECT_USER_ALBUMS);
        query.setParameter(ID_PARAM, proprietaire);
         return query.getResultList();
    }


    public List<Album> getPublicAlbums(TypeAlbum type_album)
    {
        Query query = em.createQuery(SELECT_ALL_PUBLIC_ALBUMS);
        query.setParameter(TYPE_PARAM, type_album);
         return query.getResultList();
    }

    public Long CountAllAlbum()
    {
        Query query = em.createQuery(COUNT_ALL);
        return (long) query.getSingleResult();
    }


}
