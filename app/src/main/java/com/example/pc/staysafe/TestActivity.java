package com.example.pc.staysafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.model.entity.Page;


/**
 * Shows all articles on given danger type
 */
public class TestActivity extends AppCompatActivity {

    ArticleDatabase articleDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        // initialize database connection
        initParams();

        // This is example how data from database is fetched
        // To fetch articles, .articleDao() is used. Other DAOs exists for example Page DAO.
        // DAO are objects used to search, update or delete data in database.
        Article[] articles = articleDatabase.articleDao().fetchArticles();
        for (Article article : articles) {
            Log.i("Test", article.title + ' ' + String.valueOf(article.pages));

            // Get all pages of current article
            Page[] pages = articleDatabase.pageDao().getArticlePages(article.articleId);
            Log.i("ahoj", "Received number of pages " + String.valueOf(pages.length));
            for (Page page : pages) {
                Log.i("ahoj", page.subtitle + " -> " + page.text);
            }
        }
    }

    private void initParams() {
        this.articleDatabase = ArticleDatabase.getDatabase(this);
    }
}