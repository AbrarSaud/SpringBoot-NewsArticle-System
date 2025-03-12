package com.example.newsarticlemanagementsystem.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "The 'ID' must be NOT Empty")
    private String id;

    @NotEmpty(message = "The 'Title' must be NOT Empty")
    @Size(min = 5, max = 100, message = "The 'Title' must be between 5 and 100 characters.")
    private String title;

    @NotEmpty(message = "The 'Author' must be NOT Empty")
    @Size(min = 4, max = 20, message = "The 'Author' must be between 4 and 20 characters.")
    private String author;


    @NotEmpty(message = "The 'Content' must be NOT Empty")
    @Size(min = 200, max = 1500, message = "The 'Content' must be between 200 and 1500 characters.")
    private String content;

    @NotEmpty(message = "The 'Category' must be NOT Empty")
    @Pattern(regexp = "Politics||Sports||Technology" , message = "Must be either \"politics\", \" sports\" or \" technology\" only.")
    private String category;

    @NotEmpty(message = "The 'image Url' must be NOT Empty")
    private String imageUrl;

    @NotNull(message = "The 'isPublished' must be NOT Empty")
    @AssertFalse(message = "Must by default 'false'")
    private boolean isPublished = false;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
