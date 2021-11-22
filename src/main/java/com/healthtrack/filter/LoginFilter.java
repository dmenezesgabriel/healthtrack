package com.healthtrack.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        String url = req.getRequestURI();
        String action = req.getParameter("action");
        // user
        if (session.getAttribute("user") == null && url.endsWith("user")) {
            if (action.equals("edit")) {
                request.setAttribute("erro", "Entre com o usuário e senha!");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
            // General;
        } else if (session.getAttribute("user") == null && !url.endsWith("healthtrack") && !url.endsWith("features.jsp")
                && !url.endsWith("min.js") && !url.endsWith("min.css") && !url.contains("resources")) {
            request.setAttribute("erro", "Entre com o usuário e senha!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }

}
