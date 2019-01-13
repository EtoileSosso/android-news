package com.example.sosso.newsapplication.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sosso.newsapplication.R;
import com.example.sosso.newsapplication.viewmodels.ArticleViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ArticleSingleFragment extends Fragment {

    private ArticleViewModel model;
    TextView title;
    TextView url;
    TextView desc;
    ImageView image;
    ImageView share;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_article_single, container, false);
        // Inflate the layout for this fragment

        title = v.findViewById(R.id.title);
        desc = v.findViewById(R.id.description);
        url = v.findViewById(R.id.url);
        image = v.findViewById(R.id.image);
        share = v.findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onShare(); }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model.getSelected().observe(this, article -> {
            title.setText(article.getTitle());
            desc.setText(article.getDescription());
            url.setText(article.getUrl());
            Picasso.get().load(article.getUrlToImage()).into(image);
        });
    }

    public void onShare() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, title.getText() + " " + desc.getText() + " " + url.getText());
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }
}