package controllers;

import dao.AlbumDao;
import dao.UserDao;
import entities.Album;
import entities.TypeAlbum;
import entities.TypeUtilisateur;
import entities.Utilisateur;
import metiers.AdminForm;
import metiers.AlbumForm;
import metiers.InscriptionForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin", "/admin/list-user","/admin/add-user","/admin/edit-user","/admin/delete-user","/admin/detail-user", "/admin/list-album","/user/edit-album","/user/delete-album"})
public class AdminServlet extends HttpServlet
{
    public static final String ATT_USER = "utilisateur";
    public static final String ATT_ALBUM = "album";
    public static final String ATT_FORM = "form";

    @EJB
    private UserDao userDao;

    @EJB
    private AlbumDao albumDao;

    private static final String ADMIN_HOME = "/WEB-INF/admin/admin.jsp";
    private static final String GET_USERS = "/WEB-INF/admin/list-user.jsp";
    private static final String GET_ALBUMS = "/WEB-INF/admin/list-album.jsp";
    private static final String ADD_USER = "/WEB-INF/admin/add-user.jsp";
    private static final String EDIT_USER = "/WEB-INF/admin/edit-user.jsp";
    private static final String EDIT_ALBUM = "/WEB-INF/admin/edit-album.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        switch (url)
        {
            case "/admin/add-user":
                InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                Utilisateur utilisateur = inscriptionForm.register( request );
                request.setAttribute( ATT_FORM, inscriptionForm );
                request.setAttribute( ATT_USER, utilisateur );
                getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                break;

            case "/admin/edit-user":
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String contact = request.getParameter("telephone");
                Long id = Long.parseLong(request.getParameter("id"));
                Long type_user = Long.parseLong(request.getParameter("type_user"));
                TypeUtilisateur typeUtilisateur = new TypeUtilisateur();
                typeUtilisateur.setId(type_user);
                InscriptionForm inscriptionForm1 = new InscriptionForm(userDao);

                Utilisateur userToUpdate = inscriptionForm1.findById(id);
                userToUpdate.setNom(nom);
                userToUpdate.setPrenom(prenom);
                userToUpdate.setContact(contact);
                inscriptionForm1.editUser(userToUpdate);
                request.setAttribute( ATT_FORM, inscriptionForm1 );
                request.setAttribute( ATT_USER, userToUpdate );
                getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                break;

            case "/admin/edit-album":
                String theme = request.getParameter("theme");
                Long id_alLong = Long.parseLong(request.getParameter("id"));
                Long type_album = Long.parseLong(request.getParameter("type_album"));
                TypeAlbum typeAlbum = new TypeAlbum();
                typeAlbum.setId(type_album);
                AlbumForm albumForm = new AlbumForm(albumDao);

                Album albumToUpdate = albumForm.findAlbumById(id_alLong);
                albumToUpdate.setTheme(theme);
                albumToUpdate.setType_album(typeAlbum);
                albumForm.editAlbum(albumToUpdate);
                request.setAttribute( ATT_FORM, albumForm );
                request.setAttribute( ATT_ALBUM, albumToUpdate );
                response.sendRedirect(GET_ALBUMS);
//                getServletContext().getRequestDispatcher(GET_ALBUMS).forward(request, response);
                break;

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        List<Utilisateur> utilisateurs;
        List<Album> albums;
        switch (url)
        {
            case "/admin/list-user":
                String req = request.getParameter("req");
                if(req.equals("admin"))
                {
                    InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                    utilisateurs  = inscriptionForm.findAdmins();
                    request.setAttribute("utilisateurs", utilisateurs);
                    getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                }
                else if(req.equals("user"))
                {
                    InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                    utilisateurs  = inscriptionForm.findUsers();
                    request.setAttribute("utilisateurs", utilisateurs);
                    getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                }
                else if(req.equals("all"))
                {
                    InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                    utilisateurs  = inscriptionForm.getUsers();
                    request.setAttribute("utilisateurs", utilisateurs);
                    getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                }
                InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                 utilisateurs  = inscriptionForm.getUsers();
                request.setAttribute("utilisateurs", utilisateurs);
                getServletContext().getRequestDispatcher(GET_USERS).forward(request, response);
                break;

            case "/admin":
                AlbumForm albumForm3 = new AlbumForm(albumDao);
                Long nb_album = albumForm3.CountAllAlbum();
                InscriptionForm inscriptForm = new InscriptionForm(userDao);
                Long nb_admin = inscriptForm.CountAllAdmin();
                Long nb_user = inscriptForm.CountAllUser();
                request.setAttribute("nb_album",nb_album);
                request.setAttribute("nb_admin",nb_admin);
                request.setAttribute("nb_user",nb_user);
                getServletContext().getRequestDispatcher(ADMIN_HOME).forward(request, response);
                break;

            case "/admin/add-user":
                getServletContext().getRequestDispatcher(ADD_USER).forward(request, response);
                break;

            case "/admin/edit-user":
                InscriptionForm inscriptForm2 = new InscriptionForm(userDao);
                Long id = Long.parseLong(request.getParameter("id"));
                Utilisateur utilisateur = inscriptForm2.findById(id);
                request.setAttribute("utilisateur", utilisateur);
                getServletContext().getRequestDispatcher(EDIT_USER).forward(request, response);
                break;

            case "/admin/delete-user":
                InscriptionForm inscriptForm1 = new InscriptionForm(userDao);
                Long id_user =Long.parseLong(request.getParameter("id"));
                inscriptForm1.deleteUser(inscriptForm1.findById(id_user));
                response.sendRedirect("list-user");
                break;

            case "/admin/list-album":
                AlbumForm albumForm = new AlbumForm(albumDao);
                albums  = albumForm.getAllAlbum();
                request.setAttribute("albums", albums);
                getServletContext().getRequestDispatcher(GET_ALBUMS).forward(request, response);
                break;

            case "/user/edit-album":
                AlbumForm albumForm1 = new AlbumForm(albumDao);
                Long id_album = Long.parseLong(request.getParameter("id"));
                Album album  = albumForm1.findAlbumById(id_album);
                request.setAttribute("album", album);
                getServletContext().getRequestDispatcher(EDIT_ALBUM).forward(request, response);
                break;

            case "/user/delete-album":
                AlbumForm albumForm2 = new AlbumForm(albumDao);
                Long id_albumLong = Long.parseLong(request.getParameter("id"));
                albumForm2.deleteAlbum(albumForm2.findAlbumById(id_albumLong));
                response.sendRedirect(request.getContextPath()+"/admin/list-album");
                break;

        }
    }
}
