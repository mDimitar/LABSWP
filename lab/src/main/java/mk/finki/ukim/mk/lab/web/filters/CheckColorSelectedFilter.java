package mk.finki.ukim.mk.lab.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;

@WebFilter
public class CheckColorSelectedFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();



        if(session.getAttribute("color") != null  || request.getServletPath().equals("/orders")
        || request.getServletPath().contains("/balloons") || request.getServletPath().equals("/list"))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            response.sendRedirect("/balloons");

    }

    @Override
    public void destroy() {

    }
}
