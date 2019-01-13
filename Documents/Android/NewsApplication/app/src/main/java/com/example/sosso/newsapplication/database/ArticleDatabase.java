package com.example.sosso.newsapplication.database;

import com.example.sosso.newsapplication.database.DAOs.ArticleDAO;
import com.example.sosso.newsapplication.models.Article;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDAO articleDAO();
}
