package com.book.service.impl;

import com.book.entity.Borrow;
import com.book.mapper.BookMapper;
import com.book.service.BookService;
import com.book.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Override
    public List<Borrow> getBorrowList() {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.getBorrowList();
        }
    }

    @Override
    public void returnBook(Integer id) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            bookMapper.deleteBorrow(id);
        }
    }
}
