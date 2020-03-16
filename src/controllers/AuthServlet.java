package controllers;

import dao.UserDao;
import entities.Utilisateur;
import metiers.InscriptionForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthServlet",urlPatterns = {"/login", "/register","/deconnexion"})
public class AuthServlet extends HttpServlet
{

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    private static final String LOGIN_URL = "/WEB-INF/auth/login.jsp";
    private static final String REGISTER_URL = "/WEB-INF/auth/register.jsp";
    private static final String INDEX_URL = "/user/mes-album";
    private static final String ADMIN = "/admin";
    private static final String SESSION_USER = "utilisateur";

    @EJB
    private UserDao userDao;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();

        switch (url)
        {
            case "/login" :
                getServletContext().getRequestDispatcher(LOGIN_URL).forward(request, response);
                break;

            case "/register" :
                getServletContext().getRequestDispatcher(REGISTER_URL).forward(request, response);
                break;

            case "/deconnexion":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath()+"/login");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Utilisateur utilisateur;
        String url = request.getServletPath();
        switch (url)
        {
            case "/login" :
                InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                utilisateur = inscriptionForm.userLogin( request );
                HttpSession session = request.getSession();

                request.setAttribute( ATT_FORM, inscriptionForm );
                request.setAttribute( ATT_USER, utilisateur );

                if(inscriptionForm.getErreurs().isEmpty())
                {
                    session.setAttribute(SESSION_USER, utilisateur);
                    if(utilisateur.getType_utilisateur().getId() == (long)2)
                    {
                        response.sendRedirect(request.getContextPath()+INDEX_URL);
                    }
                    else
                    {
                        response.sendRedirect(request.getContextPath()+ADMIN);
                    }
                }
                else
                {
                    request.setAttribute( ATT_FORM, inscriptionForm );
                    request.setAttribute( ATT_USER, utilisateur );
                    session.setAttribute(SESSION_USER, null);
                    getServletContext().getRequestDispatcher(LOGIN_URL).forward(request, response);
                }
                break;
            case "/register" :
                InscriptionForm inscriptionForm1 = new InscriptionForm(userDao);
                utilisateur = inscriptionForm1.register( request );
                if(utilisateur == null)
                {
                    request.setAttribute( ATT_FORM, inscriptionForm1 );
                    request.setAttribute( ATT_USER, utilisateur );
                    getServletContext().getRequestDispatcher(REGISTER_URL).forward(request, response);
                }
                else
                {
                    response.sendRedirect(request.getContextPath()+INDEX_URL);
                }
                break;
        }

    }
}
