package com.book.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MybatisUtil {
    private static final String configPath = "mybatis-config.xml";
    private static final SqlSessionFactory factory;

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(configPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MybatisUtil() {
    }

    public static SqlSession getSession() {
        return factory.openSession(true);
    }
}
