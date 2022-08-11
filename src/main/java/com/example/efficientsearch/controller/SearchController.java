package com.example.efficientsearch.controller;

import com.example.efficientsearch.entity.SearchDocument;
import com.example.efficientsearch.repository.SearchDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/searchService")
public class SearchController {

    @Autowired
    SearchDocumentRepository searchDocumentRepository;

    @GetMapping("/search")
    ResponseEntity searchMethod(@RequestParam("value") String value){

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(value);
        Sort sort = Sort.by("score");
        List<SearchDocument> searchDocumentList = searchDocumentRepository.findAllBy(textCriteria,sort);

        return ResponseEntity.ok(searchDocumentList);
    }

    @PostMapping("/save")
    ResponseEntity searchMethod(@RequestBody SearchDocument searchDocument){
        SearchDocument response = searchDocumentRepository.save(searchDocument);
        return ResponseEntity.ok(response);
    }

    //title will be fixed and search will be base on value
    @GetMapping("/search/title/{title}")
    ResponseEntity searchMethodTitle(@PathVariable String title ,@RequestParam("value") String value){

        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(value);
        List<SearchDocument> searchDocumentList = searchDocumentRepository.findAllByBookTitle(title,textCriteria);

        return ResponseEntity.ok(searchDocumentList);
    }

    @GetMapping("/new-search")
    ResponseEntity searchMethodNew(@RequestParam("value") String value){


        Sort sort = Sort.by("score");
        List<SearchDocument> searchDocumentList = searchDocumentRepository.findAllByNewSearch(value,sort);

        return ResponseEntity.ok(searchDocumentList);
    }

}
