package dao;

import entities.Album;
import entities.Tag;
import entities.Photo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PhotoDao
{
    private static final String	SELECT_PHOTO_BY_ALBUM	= "SELECT p from Photo p " +"WHERE p.album = :album";

    private static final String BY_ID_PARAM = "album";

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;


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


    Photo photo;

    public void addPhoto(Photo photo)
    {
        try
        {
            em.persist(photo);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
    }

    public Album findAlbumById(Long id)
    {
        return em.find(Album.class, id);
    }

    public Photo findPhotoById(Long id)
    {
        return em.find(Photo.class, id);
    }

    public Photo editPhoto(Photo photoToUpdate)
    {
        Photo photo = null;
        try
        {
            photo = em.merge(photoToUpdate);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return photo;
    }

    public void deletePhoto(Photo photo)
    {
        em.remove(editPhoto(photo));
    }

    public List<Photo> getPhotoAlbums(Album album)
    {
        Query query = em.createQuery(SELECT_PHOTO_BY_ALBUM);
        query.setParameter(BY_ID_PARAM, album);
        return query.getResultList();
    }
}
