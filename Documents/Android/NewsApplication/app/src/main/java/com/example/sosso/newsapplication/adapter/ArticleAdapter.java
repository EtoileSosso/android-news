package com.example.sosso.newsapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sosso.newsapplication.R;
import com.example.sosso.newsapplication.listeners.ArticleListener;
import com.example.sosso.newsapplication.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder> {
    private List<Article> articleList;
    ArticleListener listener;

    public ArticleAdapter(List<Article> articleList, ArticleListener listener) {
        this.articleList = articleList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.article_list_item, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(articleList.get(position));
    }

    public int getItemCount() { return articleList.size(); }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        TextView url;
        ImageView image;
        ImageView share;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            url = itemView.findViewById(R.id.url);
            image = itemView.findViewById(R.id.image);
            share = itemView.findViewById(R.id.share);
        }

        public void bind(Article article) {
            title.setText(article.getTitle());
            desc.setText(article.getDescription());
            url.setText(article.getUrl());
            Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(image);

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { listener.onShare(article); }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { listener.onSelect(article); }
            });
        }
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
