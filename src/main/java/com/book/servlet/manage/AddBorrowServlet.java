package com.book.servlet.manage;

import com.book.service.BookService;
import com.book.service.StudentService;
import com.book.service.impl.BookServiceImpl;
import com.book.service.impl.StudentServiceImpl;
import com.book.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/add-borrow")
public class AddBorrowServlet extends HttpServlet {
    private BookService bookService;
    private StudentService studentService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
        studentService = new StudentServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        context.setVariable("bookList", bookService.getActiveBookList());
        context.setVariable("studentList", studentService.getStudentList());
        ThymeleafUtil.process("add-borrow.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid = Integer.valueOf(req.getParameter("student"));
        Integer bid = Integer.valueOf(req.getParameter("book"));
        bookService.addBorrow(sid, bid);
        resp.sendRedirect("index");
    }
}
