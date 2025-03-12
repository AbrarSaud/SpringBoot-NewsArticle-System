package com.example.newsarticlemanagementsystem.Controller;


import com.example.newsarticlemanagementsystem.Api.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {
    private final NewsArticleService newsArticleService;

    // 1. Get all NewsArticles IN Controller
    @GetMapping("/get")
    public ResponseEntity getAllNews() {
        return ResponseEntity.ok(newsArticleService.getAllNews());
    }

    // 2.  Add a NewsArticle IN Controller
    @PostMapping("/add")
    public ResponseEntity addNew(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String messageError = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(messageError));
        }
        boolean isAddNew = newsArticleService.addNew(newsArticle);
        if (isAddNew) {
            return ResponseEntity.status(200).body(new ApiResponse("Addend successfully!"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("NCannot add \"news article\" ,ID is taken!!"));
    }

    // 3. Update a NewsArticle IN Controller
    @PutMapping("/update/{id}")
    public ResponseEntity updateNew(@PathVariable String id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String massageError = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(massageError));
        }
        boolean isUpdate = newsArticleService.updateNews(id, newsArticle);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("Updateend successfully!"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("NOT found"));
    }

    // 4. Delete a NewsArticle IN Controller
    @DeleteMapping("/delete/{id}")
    private ResponseEntity deleteNew(@PathVariable String id) {
        boolean isDelete = newsArticleService.deleteNew(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("Delete  successfully!"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("NOT found!"));
    }

    // 5. Publish NewsArticles IN Controller
    @PutMapping("/publish/{id}")
    public ResponseEntity<ApiResponse> publishNew(@PathVariable String id) {
        boolean isPublished = newsArticleService.publishNew(id);
        if (isPublished) {
            return ResponseEntity.status(200).body(new ApiResponse("News  published successfully!"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("News NOT found or already published!"));
    }

    //  6. Get all Published NewsArticles IN Controller
    @GetMapping("/published-all")
    public ResponseEntity getPublishedNews() {
        ArrayList<NewsArticle> publishedArticles = newsArticleService.getPublishedNews();

        if (publishedArticles != null) {
            return ResponseEntity.status(200).body(publishedArticles);
        }
        return ResponseEntity.status(404).body(null);
    }

    // 7. Get NewsArticles by Category IN Controller
    @GetMapping("/category/{category}")
    public ResponseEntity getNewsByCategory(@PathVariable String category) {
        ArrayList<NewsArticle> categoryArticles = newsArticleService.getNewsByCategory(category);
        if (categoryArticles != null) {
            return ResponseEntity.status(200).body(categoryArticles);
        }
        return ResponseEntity.status(404).body(null);
    }


}
