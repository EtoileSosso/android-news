package com.example.sosso.newsapplication;

import android.os.Bundle;

import com.example.sosso.newsapplication.database.DatabaseHelper;
import com.example.sosso.newsapplication.fragments.ArticleListFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper.init(this);

        // IMPLEMENTS FRAGMENT

        ArticleListFragment fragment = new ArticleListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
