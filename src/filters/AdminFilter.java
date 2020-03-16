package filters;

import entities.Utilisateur;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter
{
    private static final String LOGIN_URL = "/login";
    private static final String HOME = "/home";
    public void destroy()
    {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        if(user == null)
        {
            response.sendRedirect(request.getContextPath()+LOGIN_URL);
        }
        else
        {
            if(user.getType_utilisateur().getId() != (long) 1)
            {
                response.sendRedirect(request.getContextPath()+HOME);
            }
            else
            {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException
    {

    }

}
