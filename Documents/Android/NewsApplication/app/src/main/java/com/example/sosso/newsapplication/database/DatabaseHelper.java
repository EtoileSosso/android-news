package com.example.sosso.newsapplication.database;

import android.content.Context;

import androidx.room.Room;
import bolts.Task;

public class DatabaseHelper {
    private static ArticleDatabase db;
    public static void init(final Context context) {
        Task.callInBackground( () -> {
            db = Room.databaseBuilder(context, ArticleDatabase.class, "article-db").build();
            return null;
        }).continueWith(task -> null, Task.UI_THREAD_EXECUTOR);

    }
    public static ArticleDatabase getDb() { return db; }
}
