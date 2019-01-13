package com.example.sosso.newsapplication.viewmodels;

import com.example.sosso.newsapplication.BuildConfig;
import com.example.sosso.newsapplication.QueryResult;
import com.example.sosso.newsapplication.database.DatabaseHelper;
import com.example.sosso.newsapplication.models.Article;
import com.example.sosso.newsapplication.network.ArticleService;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticleViewModel extends ViewModel {
    private MutableLiveData<List<Article>> articlesLiveData;
    private MutableLiveData<Article> selected = new MutableLiveData<>();

    public LiveData<List<Article>> getArticles() {
        if(articlesLiveData == null) {
            articlesLiveData = new MutableLiveData<List<Article>>();
            loadArticles();
        }
        return articlesLiveData;
    }

    public void loadArticles() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/v2/")
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        Call<QueryResult> qr = service.listNews("us", BuildConfig.ApiKey);

        qr.enqueue(new Callback<QueryResult>() {
            @Override
            public void onResponse(Call<QueryResult> call, Response<QueryResult> response) {
                List<Article> articles = response.body().getArticles();
                articlesLiveData.setValue(articles);
            }

            @Override
            public void onFailure(Call<QueryResult> call, Throwable t) {
                System.out.println("REQ ERR -" + t.getLocalizedMessage());
            }
        });


    }

    public void saveArticles(final List<Article> articleList) {
        Task.callInBackground((Callable<Void>) () -> {
            DatabaseHelper.getDb().articleDAO().insertAll(articleList);
            List<Article> articles = DatabaseHelper.getDb().articleDAO().getAll();
            return null;
        });
    }

    public void setSelected(Article article) { selected.setValue(article) ;}

    public LiveData<Article> getSelected() { return selected; }
}
