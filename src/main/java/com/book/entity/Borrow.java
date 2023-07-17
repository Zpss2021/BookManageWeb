package com.book.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {
    private Integer id;
    private Integer bookId;
    private String bookName;
    private Date time;
    private Integer stuId;
    private String stuName;
}
