package metiers;

import dao.UserDao;
import entities.Utilisateur;

import java.util.List;

public final class AdminForm
{
    private UserDao userDao;


    public AdminForm(UserDao userDao)
    {
        this.userDao = userDao;
    }

}
