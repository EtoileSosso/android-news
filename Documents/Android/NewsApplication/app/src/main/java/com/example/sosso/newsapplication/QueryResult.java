package com.example.sosso.newsapplication;

import com.example.sosso.newsapplication.models.Article;

import java.util.List;

public class QueryResult {
    List<Article> articles;
    String status;
    int totalResults;

    public QueryResult(List<Article> articles, String status, int totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
