package com.example.sosso.newsapplication.listeners;

import com.example.sosso.newsapplication.models.Article;

public interface ArticleListener {
    void onShare(Article article);
    void onSelect(Article article);
}
