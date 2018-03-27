package com.example.pc.staysafe;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.staysafe.ListAdapter.ArticlesListAdapter;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Article;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Shows all articles on given danger type
 */
public class DangerActivity extends AppCompatActivity {

    private ArticleDatabase articleDatabase;
    private ImageButton returnButton;
    private ListView testArticles;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        initParams();
        initUI();
    }

    private void initParams() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_layout);
        testArticles = findViewById(R.id.TestArticlesContainer);
        returnButton = findViewById(R.id.dangerAReturnB);
        this.articleDatabase = ArticleDatabase.getDatabase(this);

        ArrayList<Article> arts = new ArrayList<>();
        arts.addAll(Arrays.asList(articleDatabase.articleDao().fetchArticles()));
        ArticlesListAdapter testArticlesAdapter = new ArticlesListAdapter(this, arts);
        testArticles.setAdapter(testArticlesAdapter);
    }

    private void initUI() {
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        //Make Item in listview clickable and change title of page when cliked
        testArticles.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                setDialog(parent, position);
            }
        });
    }
    private void setDialog (AdapterView<?> parent, int position) {
        Article article = (Article) parent.getItemAtPosition(position);
        dialog.setTitle(String.valueOf(article.articleId));
        ImageButton endDialog = dialog.findViewById(R.id.endDialogButton);
        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        TextView dialogText = dialog.findViewById(R.id.dialogText);

        endDialog.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialogTitle.setText(article.title);
        dialog.show();
    }

}