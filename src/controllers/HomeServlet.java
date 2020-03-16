package controllers;

import dao.AlbumDao;
import entities.Album;
import entities.TypeAlbum;
import metiers.AlbumForm;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet
{
    @EJB
    private AlbumDao albumDao;
    private static final String HOME_URL = "/WEB-INF/home.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Album> album;
        AlbumForm albumForm = new AlbumForm(albumDao);
        TypeAlbum type_album = new TypeAlbum();
        Long nb = new Long(2);
        type_album.setId(nb);
        album = albumForm.getPublicAlbums(type_album);
        request.setAttribute("album", album);
        getServletContext().getRequestDispatcher(HOME_URL).forward(request, response);

    }
}
