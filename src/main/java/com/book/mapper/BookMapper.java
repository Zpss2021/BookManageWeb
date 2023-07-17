package com.book.mapper;

import com.book.entity.Borrow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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


    @Delete("delete from borrow where id = #{id}")
    void deleteBorrow(Integer id);
}
