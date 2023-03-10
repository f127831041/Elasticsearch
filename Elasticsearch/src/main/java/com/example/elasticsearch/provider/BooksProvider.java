package com.example.elasticsearch.provider;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.elasticsearch.dto.BooksDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * @Author viper
 * @Date 2023/3/10 上午 10:11
 * @Version 1.0
 */
public class BooksProvider {
    public String getPageQuery(BooksDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("books");
            if (StringUtils.isNotBlank(dto.getTitle())) {
                WHERE("title LIKE CONCAT('%', #{dto.title}, '%')");
            }
            if (StringUtils.isNotBlank(dto.getAuthor())) {
                WHERE("author LIKE CONCAT('%', #{dto.author}, '%')");
            }
        }}.toString();
    }
}
