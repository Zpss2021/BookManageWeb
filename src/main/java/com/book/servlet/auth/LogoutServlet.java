package com.book.servlet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("loginUser");
        Cookie cookieUsername = new Cookie("username", "username");
        Cookie cookiePassword = new Cookie("password", "password");
        cookieUsername.setMaxAge(0);
        cookiePassword.setMaxAge(0);
        resp.addCookie(cookieUsername);
        resp.addCookie(cookiePassword);
        resp.sendRedirect("login");
    }
}
