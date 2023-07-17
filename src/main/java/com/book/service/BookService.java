package com.book.service;

import com.book.entity.Book;
import com.book.entity.Borrow;

import java.util.List;

public interface BookService {
    List<Borrow> getBorrowList();
    void addBorrow(Integer sid, Integer bid);
    void returnBook(Integer id);
    List<Book> getActiveBookList();
}
