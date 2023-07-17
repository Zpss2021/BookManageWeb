package com.book.servlet.auth;

import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import com.book.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String username = null;
            String password = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username"))
                    username = cookie.getValue();
                if (cookie.getName().equals("password"))
                    password = cookie.getValue();
            }
            if (username != null && password != null)
                if (userService.auth(username, password, req.getSession())) {
                    resp.sendRedirect("index");
                    return;
                }
        }
        Context context = new Context();
        if (req.getSession().getAttribute("login-failure") != null) {
            context.setVariable("failure", true);
            req.getSession().removeAttribute("login-failure");
        }
        if (req.getSession().getAttribute("loginUser") != null) {
            resp.sendRedirect("index");
            return;
        }
        ThymeleafUtil.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember-me");
        if (userService.auth(username, password, req.getSession())) {
            if (remember != null) {
                Cookie cookieUsername = new Cookie("username", username);
                Cookie cookiePassword = new Cookie("password", password);
                cookieUsername.setMaxAge(60 * 60 * 24 * 7);
                cookiePassword.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(cookieUsername);
                resp.addCookie(cookiePassword);
            }
            resp.sendRedirect("index");
        } else {
            req.getSession().setAttribute("login-failure", new Object());
            this.doGet(req, resp);
        }
    }
}
