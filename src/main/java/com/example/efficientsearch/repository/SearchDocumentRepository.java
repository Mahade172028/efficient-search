package com.example.efficientsearch.repository;

import com.example.efficientsearch.entity.SearchDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SearchDocumentRepository extends MongoRepository<SearchDocument,String> {

    List<SearchDocument> findAllBy(TextCriteria criteria , Sort sort);

    List<SearchDocument> findAllByBookTitle(String title,TextCriteria criteria);


    @Query("{ $text: { $search: ?0 } }")
    List<SearchDocument> findAllByNewSearch(String value,Sort sort);
}
