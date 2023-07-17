package com.book.servlet.pages;

import com.book.entity.User;
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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
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
        User loginuser = (User) req.getSession().getAttribute("loginUser");
        context.setVariable("nickname", loginuser.getNickname());
        context.setVariable("borrowList", bookService.getBorrowList());
        context.setVariable("bookCount", bookService.getBookList().size());
        context.setVariable("studentCount", studentService.getStudentList().size());
        ThymeleafUtil.process("index.html", context, resp.getWriter());
    }
}
