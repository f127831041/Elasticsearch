package com.example.elasticsearch.dto;

import lombok.Data;

/**
 * @Author viper
 * @Date 2023/3/10 上午 10:16
 * @Version 1.0
 */

@Data
public class BooksDTO {
    private Long id;
    private String title;
    private String author;
    private String content;
    private Long categoryId;
}
