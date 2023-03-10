package com.example.elasticsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@MapperScan("com.example.elasticsearch.mapper")
@EnableElasticsearchRepositories(basePackages = "com.example.elasticsearch.repository")
@SpringBootApplication
public class ElasticsearchApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }
    
}
