package com.example.elasticsearch.repository;

import com.example.elasticsearch.doc.BooksDOC;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author viper
 * @Date 2023/3/10 上午 10:48
 * @Version 1.0
 */
@Repository
public interface BooksRepository extends ElasticsearchRepository<BooksDOC, Long> {
    @Highlight(fields = {
            @HighlightField(name = "title"),
            @HighlightField(name = "content"),
            @HighlightField(name = "author")
    }, parameters = @HighlightParameters(
            preTags = "<em>",
            postTags = "</em>"
    ))
    @Query("{\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\",\"fields\": [\"title\", \"content\", \"author\"]}}]}}")
    List<SearchHit<BooksDOC>> findBySearchHit(String keyWord);
}
