package com.book.service.impl;

import com.book.entity.User;
import com.book.mapper.UserMapper;
import com.book.service.UserService;
import com.book.util.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User loginUser = userMapper.getUser(username, password);
            if (loginUser == null)
                return false;
            session.setAttribute("loginUser", loginUser);
            return true;
        }
    }
}
