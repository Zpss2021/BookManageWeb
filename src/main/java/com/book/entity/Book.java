package com.book.entity;

import lombok.Data;

@Data
public class Book {
    private Integer bid;
    private String title;
    private String desc;
    private Double price;
}
