package com.example.newsarticlemanagementsystem.Service;

import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsArticleService {

    private List<NewsArticle> newsArticles = new ArrayList<>();

    // 1. Get all NewsArticles IN Service

    public List<NewsArticle> getAllNews() {
        return newsArticles;
    }

    // 2.  Add a NewsArticle IN Service
    public boolean addNew(NewsArticle newsArticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(newsArticle.getId())) {
                return false;
            }
        }
        newsArticles.add(newsArticle);
        return true;
    }

    // 3. Update a NewsArticle IN Service
    public boolean updateNews(String id, NewsArticle newsArticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }

    // 4. Delete a NewsArticle IN Service
    public boolean deleteNew(String id) {
        for (NewsArticle n : newsArticles) {
            if (n.getId().equals(id)) {
                newsArticles.remove(n);
                return true;
            }
        }
        return false;
    }

    // 5. Publish NewsArticles IN Service
    public boolean publishNew(String id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)) {
                if (!newsArticles.get(i).isPublished()) {
                    newsArticles.get(i).setPublished(true);
                    return true;
                }
            }
        }
        return false;
    }

    //  6 . Get all Published NewsArticles IN Service
    public ArrayList<NewsArticle> getPublishedNews() {
        ArrayList<NewsArticle> publishedNews = new ArrayList<>();

        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).isPublished()) {
                publishedNews.add(newsArticles.get(i));
            }
        }
        return publishedNews;
    }

    // 7. Get NewsArticles by Category IN Service
    public ArrayList<NewsArticle> getNewsByCategory(String category) {
        ArrayList<NewsArticle> categoryArticles = new ArrayList<>();

        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getCategory().equalsIgnoreCase(category)) {
                categoryArticles.add(newsArticles.get(i));

            }
        }
        return categoryArticles;
    }


}
