package com.example.elasticsearch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.elasticsearch.dto.BooksDTO;
import com.example.elasticsearch.model.Books;
import com.example.elasticsearch.provider.BooksProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Author viper
 * @Date 2023/3/9 上午 10:43
 * @Version 1.0
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {
    
    @SelectProvider(type = BooksProvider.class, method = "getPageQuery")
    List<Books> getPageQuery(@Param("dto") BooksDTO dto);
    
    @Select("select * from books where title = #{title}")
    List<Books> getTitle(@Param("title") String title);
}
