package controllers;

import dao.AlbumDao;
import dao.PhotoDao;
import entities.Album;
import entities.Photo;
import entities.Utilisateur;
import metiers.AlbumForm;
import metiers.PhotoForm;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;


@WebServlet(name = "PhotoServlet", urlPatterns = {"/user/add-photo", "/user/edit-photo", "/user/delete-photo","/list-photo"})
@MultipartConfig(fileSizeThreshold=1024*1024*10,    // 10 MB
        maxFileSize=1024*1024*50,          // 50 MB
        maxRequestSize=1024*1024*100,      // 100 MB
        location="D:/Master 2/Java EE/gestionnaire_album/web/")
public class PhotoServlet extends HttpServlet
{

    @EJB
    private PhotoDao photoDao;
    @EJB
    private AlbumDao albumDao;

    private static final String ADD_URL = "/WEB-INF/photo/add-photo.jsp";
    private static final String GET_URL = "/WEB-INF/photo/get-photo.jsp";
    private static final String EDIT_URL = "/WEB-INF/photo/edit-photo.jsp";

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        switch (url)
        {
            case "/user/add-photo":
                List<Album> album;
                HttpSession session = request.getSession(false);
                if(session != null)
                {
                    AlbumForm albumForm = new AlbumForm(albumDao);
                    Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
                    album = albumForm.getUserAlbums(user);
                    if(album !=null)
                    {
                        request.setAttribute("album", album);
                    }
                }
                getServletContext().getRequestDispatcher(ADD_URL).forward(request, response);
                break;

            case "/list-photo":
                List<Photo> photos;
                PhotoForm photoForm = new PhotoForm(photoDao);
                Album album1 = new Album();
                Album album2;
                Long id_album = Long.parseLong(request.getParameter("album"));
                album2 = albumDao.findAlbumById(id_album);
                album1.setId(id_album);

                photos = photoForm.getPhotoAlbums(album1);
                request.setAttribute("photo", photos);
                request.setAttribute("album", album2);
                getServletContext().getRequestDispatcher(GET_URL).forward(request, response);
                break;

            case "/user/delete-photo":
                Long id = Long.parseLong(request.getParameter("id"));
                PhotoForm photoForm1 = new PhotoForm(photoDao);
                Photo photo = photoForm1.findById(id);
                photoForm1.deletePhoto(photo);
                response.sendRedirect(request.getContextPath()+"/user/mes-album");
                break;

            case "/user/edit-photo":
                Long id1 = Long.parseLong(request.getParameter("id"));
                String titre = request.getParameter("titre");
                PhotoForm photoForm2 = new PhotoForm(photoDao);
                Photo photo1 = photoForm2.findById(id1);
                request.setAttribute("album", titre);
                request.setAttribute("photo", photo1);
                getServletContext().getRequestDispatcher(EDIT_URL).forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String url = request.getServletPath();
        switch (url)
        {
            case "/user/add-photo":
                Photo photo;
                PhotoForm photoForm = new PhotoForm(photoDao);
                Part part = request.getPart("img_src");
                String fileName = timestamp.getTime()+"_"+extractFileName(part);
                Long id = Long.parseLong(request.getParameter("album"));
                String folder_to_save_image = albumDao.getById(id);
                String savePath = "images"+ File.separator +folder_to_save_image + File.separator + fileName;
                part.write(savePath + File.separator);
                int width = 0,height = 0;
                File file = new File("D:/Master 2/Java EE/gestionnaire_album/web/images/"+folder_to_save_image+"/"+fileName);
                ImageInputStream iis = ImageIO.createImageInputStream(file);
                Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

                if (readers.hasNext()) {
                    //Get the first available ImageReader
                    ImageReader reader = readers.next();
                    reader.setInput(iis, true);
                    width = reader.getWidth(0);
                    height = reader.getHeight(0);
                }
                photo = photoForm.addPhoto( request, fileName, width, height );
                if(photo == null)
                {
                    request.setAttribute("form", photoForm);
                    request.setAttribute("photo", photo);
                    getServletContext().getRequestDispatcher(ADD_URL).forward(request, response);
                }
                else
                {
                    response.sendRedirect(request.getContextPath()+"/user/mes-album");
                }
                break;

            case "/user/edit-photo":
                Long id1 = Long.parseLong(request.getParameter("id"));
                PhotoForm photoForm1 = new PhotoForm(photoDao);
                Photo photoToUpdate = photoForm1.findById(id1);
                Photo photo1;
                String titre = request.getParameter("titre");
                String description = request.getParameter("description");
                photoToUpdate.setDescription(description);
                photoToUpdate.setTitre(titre);
                Part part1 = request.getPart("img_src");
                String fileName1;
                String fil = extractFileName(part1);
                if(!fil.isEmpty())
                {
                    fileName1 = timestamp.getTime()+"_"+extractFileName(part1);
                    String folder_to_save_image1 = request.getParameter("album");
                    String savePath1 = "images"+ File.separator +folder_to_save_image1 + File.separator + fileName1;
                    part1.write(savePath1 + File.separator);
                    photoToUpdate.setImg_source(fileName1);
                }

                photo1 = photoDao.editPhoto( photoToUpdate);
                if(photo1 == null)
                {
                    request.setAttribute("form", photoForm1);
                    request.setAttribute("photo", photo1);
                    getServletContext().getRequestDispatcher(EDIT_URL).forward(request, response);
                }
                else
                {
                    response.sendRedirect(request.getContextPath()+"/user/mes-album");
                }
                break;

        }
    }

    private String extractFileName(Part part)
    {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
