package com.book.mapper;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "bookId"),
            @Result(column = "title", property = "bookName"),
            @Result(column = "time", property = "time"),
            @Result(column = "sid", property = "stuId"),
            @Result(column = "name", property = "stuName")
    })
    @Select("select * from borrow, student, book where borrow.bid = book.bid and student.sid = borrow.sid")
    List<Borrow> getBorrowList();

    @Insert("insert into borrow(sid, bid, time) values(#{sid}, #{bid}, NOW())")
    void addBorrow(@Param("sid") Integer sid, @Param("bid") Integer bid);

    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(Integer id);

    @Select("select * from book")
    List<Book> getBookList();
}
