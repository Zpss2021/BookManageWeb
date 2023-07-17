package com.book.servlet.pages;

import com.book.entity.User;
import com.book.service.StudentService;
import com.book.service.impl.StudentServiceImpl;
import com.book.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        User loginuser = (User) req.getSession().getAttribute("loginUser");
        context.setVariable("nickname", loginuser.getNickname());
        context.setVariable("studentList", studentService.getStudentList());
        ThymeleafUtil.process("students.html", context, resp.getWriter());
    }
}
