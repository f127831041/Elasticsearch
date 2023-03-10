package com.example.elasticsearch.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Author viper
 * @Date 2023/3/9 上午 10:49
 * @Version 1.0
 */
@Data
@Builder
@TableName("books")
public class Books implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String content;
    
    @TableField("category_id")
    private Long categoryId;
}
