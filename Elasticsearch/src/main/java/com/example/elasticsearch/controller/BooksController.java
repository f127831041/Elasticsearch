package com.example.elasticsearch.controller;

import com.example.elasticsearch.doc.BooksDOC;
import com.example.elasticsearch.dto.BooksDTO;
import com.example.elasticsearch.model.Result;
import com.example.elasticsearch.service.BooksService;
import com.example.elasticsearch.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author viper
 * @Date 2023/3/9 下午 01:48
 * @Version 1.0
 */
@RestController
public class BooksController {
    @Autowired
    private BooksService booksService;
    
    @GetMapping("/books/all")
    public List<Books> getAll(){
        return booksService.getAll();
    }
    
    @PostMapping("/books/page")
    public List<Books> getPage(BooksDTO dto){
        return booksService.getPageQuery(dto);
    }
    
    @GetMapping("/books/title/{str}")
    public List<Books> getTitle(@PathVariable("str") String str){
        return booksService.getTitle(str);
    }
    
    @GetMapping("/books/search/{str}")
    public List<BooksDOC> getSearch(@PathVariable("str") String str){
        return booksService.getSearch(str);
    }
    
    @GetMapping("/books/search-hit/{str}")
    public List<SearchHit<BooksDOC>> getSearchHit(@PathVariable("str") String str){
        return booksService.getSearchHit(str);
    }
    
    @PostMapping("/books/add")
    public ResponseEntity<?> add(BooksDTO dto){
        booksService.add(dto);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
    
    @PostMapping("/books/update")
    public ResponseEntity<?> update(BooksDTO dto){
        booksService.update(dto);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
    
    @GetMapping("/books/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        booksService.delete(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
    
}
