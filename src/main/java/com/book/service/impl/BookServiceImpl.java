package com.book.service.impl;

import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.mapper.BookMapper;
import com.book.service.BookService;
import com.book.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.getBorrowList();
        }
    }

    @Override
    public void addBorrow(Integer sid, Integer bid) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            bookMapper.addBorrow(sid, bid);
        }
    }

    @Override
    public void returnBook(Integer id) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            bookMapper.deleteBorrow(id);
        }
    }

    @Override
    public List<Book> getActiveBookList() {
        Set<Integer> borrowedSet = new HashSet<>();
        this.getBorrowList().forEach(borrow -> borrowedSet.add(borrow.getBookId()));
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.getBookList()
                    .stream()
                    .filter(book -> !borrowedSet.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }
}
