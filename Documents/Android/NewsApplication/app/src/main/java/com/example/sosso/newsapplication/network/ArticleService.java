package com.example.sosso.newsapplication.network;

import com.example.sosso.newsapplication.QueryResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {
    @GET("top-headlines")
    Call<QueryResult> listNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
