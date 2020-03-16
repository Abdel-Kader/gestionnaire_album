package dao;

import entities.TypeUtilisateur;
import entities.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

import static java.lang.System.*;

@Stateless
public class UserDao
{
    private static final String	SELECT_LOGIN	= "SELECT u from Utilisateur u WHERE u.login = :login";
    private static final String	FIND_SHARED	= "SELECT u from Utilisateur u WHERE u.id <> :id";
    private static final String	FIND_USER	= "SELECT u from Utilisateur u where " + "u.type_utilisateur = :type_user";
    private static final String	FIND_ALL	= "SELECT u from Utilisateur u";
    private static final String	FIND_ADMIN	= "SELECT u from Utilisateur u where " + "u.type_utilisateur = :type_use";
    private static final String	COUNT_ALL	= "SELECT count(u.id) from Utilisateur u where u.type_utilisateur = :type_user";
    private static final String	USER_LOGIN	= "SELECT u from Utilisateur u " +"WHERE u.login = :login and u.password = :password";

    private static final String LOGIN_PARAM = "login";
    private static final String ID_PARAM = "id";

    private static final String PASSWORD_PARAM = "password";

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    Utilisateur user = null;
    public void ajouter(Utilisateur utilisateur)
    {
        try
        {
            em.persist(utilisateur);

        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
    }

   public List<Utilisateur> getAllUsers(Long id)
    {
        Query query = em.createQuery(FIND_SHARED);
        query.setParameter(ID_PARAM, id);
        return query.getResultList();
    }


    public Utilisateur getLogin(String login)
    {
        Query query = em.createQuery(SELECT_LOGIN);
        query.setParameter(LOGIN_PARAM, login);
        try
        {
            user = (Utilisateur) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return user;
    }

    public List<Utilisateur> findAllUsers()
    {
        Query query = em.createQuery(FIND_ALL);
        return query.getResultList();
    }

    public List<Utilisateur> findUsers()
    {
        TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
        typeUtilisateur.setId((long) 2);
        Query query = em.createQuery(FIND_USER);
        query.setParameter("type_user", typeUtilisateur);
        return query.getResultList();
    }

    public List<Utilisateur> findAdmin()
    {
        TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
        typeUtilisateur.setId((long) 1);
        Query query = em.createQuery(FIND_ADMIN);
        query.setParameter("type_use", typeUtilisateur);
        return query.getResultList();
    }

    public Utilisateur userLogin(String login, String password)
    {
        Query query = em.createQuery(USER_LOGIN);
        query.setParameter(LOGIN_PARAM, login);
        query.setParameter(PASSWORD_PARAM, password);
        try
        {
            user = (Utilisateur) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }

        return user;
    }

    public Utilisateur findById(Long id)
    {
        return em.find(Utilisateur.class, id);
    }

    public Utilisateur editUser(Utilisateur userToUpdate)
    {
        Utilisateur user = null;
        try
        {
            user = em.merge(userToUpdate);
        }
        catch (Exception e)
        {
            throw new ExceptionDao(e.getMessage());
        }
        return user;
    }

    public void deleteUser(Utilisateur user)
    {
        em.remove(editUser(user));
    }

    public Long CountAllUser()
    {
        TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
        typeUtilisateur.setId((long) 2);
        Query query = em.createQuery(COUNT_ALL);
        query.setParameter("type_user", typeUtilisateur);
        return (long) query.getSingleResult();
    }

    public Long CountAllAdmin()
    {
        TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
        typeUtilisateur.setId((long) 1);
        Query query = em.createQuery(COUNT_ALL);
        query.setParameter("type_user", typeUtilisateur);
        return (long) query.getSingleResult();
    }

}
