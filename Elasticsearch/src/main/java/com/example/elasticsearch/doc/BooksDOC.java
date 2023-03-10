package com.example.elasticsearch.doc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.elasticsearch.model.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author viper
 * @Date 2023/3/10 上午 10:45
 * @Version 1.0
 */
@Data
@Document(indexName = "books")
public class BooksDOC {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    @Field(type = FieldType.Text, name = "title", analyzer = "icu_analyzer")
    private String title;
    
    @Field(type = FieldType.Text, name = "content", analyzer = "icu_analyzer")
    private String content;
    
    @Field(type = FieldType.Text, name = "author", analyzer = "icu_analyzer")
    private String author;
    
    
    public BooksDOC(Books books){
        this.id = books.getId();
        this.title = books.getTitle();
        this.content = books.getContent();
        this.author = books.getAuthor();
    }
    
    public BooksDOC(){
    
    }
}
