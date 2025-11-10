package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginPage = path.endsWith("Login.jsp") || path.endsWith("login");

        if (loggedIn || isLoginPage) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/Login.jsp");
        }
    }
}
