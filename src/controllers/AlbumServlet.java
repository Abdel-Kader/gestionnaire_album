package controllers;

import dao.AlbumDao;
import dao.PhotoDao;
import dao.UserDao;
import entities.*;
import metiers.AlbumForm;
import metiers.InscriptionForm;
import metiers.PhotoForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

@WebServlet(name = "AlbumServlet", urlPatterns = {"/user/add-album", "/user/mes-album","/user/detail-album","/user/with-me","/user/delete-autorisation"})
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
        maxFileSize=1024*1024*50,          // 50 MB
        maxRequestSize=1024*1024*100,      // 100 MB
        location="D:/Master 2/Java EE/gestionnaire_album/web/")


public class AlbumServlet extends HttpServlet
{
    private static final String ADD_URL = "/WEB-INF/album/add-album.jsp";
    private static final String ALBUMS_URL = "/WEB-INF/album/mes-albums.jsp";
    private static final String DETAIL_ALBUM = "/WEB-INF/album/detail-album.jsp";
    public static final String ATT_ALBUM = "album";
    public static final String ATT_FORM = "form";


    @EJB
    private AlbumDao albumDao;
    @EJB
    private UserDao userDao;
    @EJB
    private PhotoDao photoDao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        Utilisateur user;
        HttpSession session = request.getSession(false);

        switch (url)
        {
            case "/user/add-album":
                user = (Utilisateur) session.getAttribute("utilisateur");
                List<Utilisateur> utilisateurs = userDao.getAllUsers(user.getId());
                request.setAttribute("users", utilisateurs);
                getServletContext().getRequestDispatcher(ADD_URL).forward(request, response);
                break;

            case "/user/mes-album":
                List<Album> album;
                AlbumForm albumForm = new AlbumForm(albumDao);
                if(session != null)
                {
                    user = (Utilisateur) session.getAttribute("utilisateur");
                    album = albumForm.getUserAlbums(user);
                    request.setAttribute("album", album);
                }
                getServletContext().getRequestDispatcher(ALBUMS_URL).forward(request, response);
                break;

            case "/user/detail-album":
                List<Photo> photos;
                PhotoForm photoForm = new PhotoForm(photoDao);
                Long id_album = Long.parseLong(request.getParameter("id_album"));
                Album album1;
                AlbumForm albumForm1 = new AlbumForm(albumDao);
                album1 = albumForm1.findAlbumById(id_album);
                photos = photoForm.getPhotoAlbums(album1);
                request.setAttribute("album", album1);
                request.setAttribute("photo", photos);
                getServletContext().getRequestDispatcher(DETAIL_ALBUM).forward(request, response);
                break;

            case "/user/with-me":
                Set<Album> albumList;
                if(session != null)
                {
                    user = (Utilisateur) session.getAttribute("utilisateur");
                    albumList = user.getAlbums();
                    request.setAttribute("album", albumList);
                }
                getServletContext().getRequestDispatcher(ALBUMS_URL).forward(request, response);
                break;
            case "/user/delete-autorisation":
                Long id_al = Long.parseLong(request.getParameter("id"));
                Long id_user = Long.parseLong(request.getParameter("id_user"));
                AlbumForm albumForm2 = new AlbumForm(albumDao);
                InscriptionForm inscriptionForm = new InscriptionForm(userDao);
                Utilisateur user2 = inscriptionForm.findById(id_user);
                Album album2 = albumForm2.findAlbumById(id_al);
                album2.getUtilisateurs().remove(user2);
                user2.getAlbums().remove(album2);
                response.sendRedirect(request.getContextPath()+"/user/mes-album");


        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        switch (url)
        {
            case "/user/add-album":
                Album album;

                AlbumForm albumForm = new AlbumForm(albumDao);

                album = albumForm.addAlbum( request );
                String fileName = "D:/Master 2/Java EE/gestionnaire_album/web/images/"+album.getTitre();

                Path path = Paths.get(fileName);
                if (!Files.exists(path)) {
                    Files.createDirectory(path);
                    System.out.println("Directory created");
                }
                else
                {
                    System.out.println("Directory already exists");
                }

                if(album == null)
                {
                    request.setAttribute(ATT_FORM, albumForm);
                    request.setAttribute(ATT_ALBUM, album);
                    getServletContext().getRequestDispatcher(ADD_URL).forward(request, response);
                }
                else{
                    response.sendRedirect(request.getContextPath()+"/user/mes-album");
                }
        }
    }
}
