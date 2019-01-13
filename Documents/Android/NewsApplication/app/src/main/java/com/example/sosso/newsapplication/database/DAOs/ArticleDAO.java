package com.example.sosso.newsapplication.database.DAOs;

import com.example.sosso.newsapplication.models.Article;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDAO {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Insert
    void insertAll(List<Article> articles);
}
