package com.example.pc.staysafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.staysafe.adapters.ArticlesListAdapter;
import com.example.pc.staysafe.dialog.ArticleInfoDialog;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Article;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shows all articles on given danger type
 */
public class DangerActivity extends AppCompatActivity {

    private Article currentArticle;

    private ArticleInfoDialog articleDialog;
    private ArticleDatabase articleDatabase;

    private ListView testArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        initParams();
        initUI();
    }

    private void initParams() {
        articleDialog = new ArticleInfoDialog(this, buttonListener);
        articleDatabase = ArticleDatabase.getDatabase(this);

        testArticles = findViewById(R.id.TestArticlesContainer);
    }

    private void initUI() {
        ArrayList<Article> arts = new ArrayList<>();
        arts.addAll(Arrays.asList(articleDatabase.articleDao().fetchArticles()));
        ArticlesListAdapter testArticlesAdapter = new ArticlesListAdapter(this, arts);
        testArticles.setOnItemClickListener(listViewListener);
        testArticles.setAdapter(testArticlesAdapter);
    }

    /**
     * Show dialog displaying some information about clicked article;
     *
     * Is trigger automatically when item in ListView is clicked.
     * @param parent ListView object
     * @param position row's index in ListView
     */
    private void showInfoDialog (AdapterView<?> parent, int position) {
        currentArticle = (Article) parent.getItemAtPosition(position);
        articleDialog.show(currentArticle);
    }

    /**
     * Custom listener used when item in ListView is clicked
     */
    private AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            showInfoDialog(parent, position);
        }
    };

    /**
     * Custom listener passed to dialog manager, listens to the dialog's buttons
     */
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog_articleInfo_read:
                    Intent aA  = new Intent(getBaseContext(), ArticleActivity.class);
                    aA.putExtra(ArticleActivity.ARTICLE_ID_KEY, currentArticle.articleId);
                    startActivity(aA);
                    break;

                case R.id.dialog_articleInfo_test:
                    Intent qA = new Intent(getBaseContext(), QuestionActivity.class);
                    qA.putExtra(QuestionActivity.ARTICLE_ID_KEY, currentArticle.articleId);
                    startActivity(qA);
                    break;
            }
        }
    };
}