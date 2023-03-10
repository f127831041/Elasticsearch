package com.example.elasticsearch.service;

import com.example.elasticsearch.doc.BooksDOC;
import com.example.elasticsearch.dto.BooksDTO;
import com.example.elasticsearch.mapper.BooksMapper;
import com.example.elasticsearch.model.Books;
import com.example.elasticsearch.repository.BooksRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author viper
 * @Date 2023/3/9 下午 02:15
 * @Version 1.0
 */
@Service
public class BooksService extends BaseService<Books, BooksMapper> {
    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private BooksRepository booksRepository;
    @Resource
    private ElasticsearchOperations elasticsearchOperations;
    
    public List<Books> getPageQuery(BooksDTO dto) {
        return booksMapper.getPageQuery(dto);
    }
    
    public List<Books> getTitle(String title) {
        return booksMapper.getTitle(title);
    }
    
    public List<BooksDOC> getSearch(String keyWord) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.should(QueryBuilders.matchQuery("title", keyWord));
        boolQuery.should(QueryBuilders.matchQuery("author", keyWord));
        boolQuery.should(QueryBuilders.matchQuery("content", keyWord));
        queryBuilder.withQuery(boolQuery);
        SearchHits<BooksDOC> searchHits = elasticsearchOperations.search(queryBuilder.build(), BooksDOC.class);
        return searchHits.getSearchHits().stream().map(SearchHit::getContent).toList();
    }
    
    public List<SearchHit<BooksDOC>> getSearchHit(String keyWord) {
        return booksRepository.findBySearchHit(keyWord);
    }
    
    public void add(BooksDTO dto){
        Books books = Books.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .content(dto.getContent())
                .categoryId(dto.getCategoryId())
                .build();
        booksMapper.insert(books);
        BooksDOC dco = new BooksDOC(books);
        //新增至elasticsearch文檔
        booksRepository.save(dco);
    }
    
    public void update(BooksDTO dto){
        Books books = booksMapper.selectById(dto.getId());
        books.setTitle(dto.getTitle());
        books.setAuthor(dto.getAuthor());
        books.setContent(dto.getContent());
        books.setCategoryId(dto.getCategoryId());
        booksMapper.updateById(books);
        //更新elasticsearch文檔
        Optional<BooksDOC> docOptional = booksRepository.findById(dto.getId());
        if (docOptional.isPresent()) {
            BooksDOC doc = docOptional.get();
            doc.setTitle(dto.getTitle());
            doc.setAuthor(dto.getAuthor());
            doc.setContent(dto.getContent());
            booksRepository.save(doc);
        }
    }
    
    public void delete(long id){;
        booksMapper.deleteById(id);
        //刪除elasticsearch文檔
        booksRepository.deleteById(id);
    }
}
