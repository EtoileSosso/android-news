package com.example.sosso.newsapplication.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sosso.newsapplication.R;
import com.example.sosso.newsapplication.adapter.ArticleAdapter;
import com.example.sosso.newsapplication.listeners.ArticleListener;
import com.example.sosso.newsapplication.models.Article;
import com.example.sosso.newsapplication.viewmodels.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ArticleListFragment extends Fragment implements ArticleListener{

    // déclaration de variables

    ArticleAdapter adapter;
    ArticleViewModel viewModel = new ArticleViewModel();
    private List<Article> articles = new ArrayList<>();
    private ArticleViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article_list,  container, false);
        init(v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // modèle
        model.getArticles().observe(this, articleList -> {
            adapter.setArticleList(articleList);
            adapter.notifyDataSetChanged();
            model.saveArticles(articleList);
        });
    }

    private void init(View v) {
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ArticleAdapter(articles, (ArticleListener) this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onShare(Article article) {
        Uri img = Uri.parse(article.getUrlToImage());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.getTitle() + " " + article.getDescription() + " " + article.getUrl() + " " + img);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    @Override
    public void onSelect(Article article) {
        model.setSelected(article);
        Fragment fragment = new ArticleSingleFragment();
        // replaces fragment
        replaceFragment(fragment);

    }
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = null;
        if (getFragmentManager() != null) {
            transaction = getFragmentManager().beginTransaction();
        }
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
