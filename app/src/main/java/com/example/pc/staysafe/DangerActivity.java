package com.example.pc.staysafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.staysafe.ListAdapter.ArticlesListAdapter;
import com.example.pc.staysafe.dialog.ArticleInfoDialog;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Article;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shows all articles on given danger type
 */
public class DangerActivity extends AppCompatActivity {
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
        Article article = (Article) parent.getItemAtPosition(position);
        articleDialog.show(article);
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
                    Log.w("AAA", "Ahoj");
                    break;

                case R.id.dialog_articleInfo_test:
                    Log.w("AAA", "Druha");
                    break;
            }
        }
    };
}