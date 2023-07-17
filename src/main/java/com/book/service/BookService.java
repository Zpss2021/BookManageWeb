package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Borrow> getBorrowList();
    void addBorrow(Integer sid, Integer bid);
    void returnBook(Integer id);
    List<Book> getActiveBookList();
    Map<Book, Boolean> getBookList();
    void deleteBook(Integer bid);
    void addBook(String title, String desc, Double price);
}
