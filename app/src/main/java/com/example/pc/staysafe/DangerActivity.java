package com.example.pc.staysafe;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.staysafe.ListAdapter.ArticlesListAdapter;
import com.example.pc.staysafe.Model.Objects.Article;
import com.example.pc.staysafe.Model.TestModel;
import java.util.ArrayList;

/**
 * Shows all articles on given danger type
 */
public class DangerActivity extends AppCompatActivity {

    private TestModel testModel = new TestModel();
    private ImageButton returnButton;
    ListView testArticles;
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

        ArrayList<Article> arts = testModel.getArticles(TestModel.DangerType.REAL);
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
        testArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Article text = (Article) parent.getItemAtPosition(position);
                dialog.setTitle(text.getTitle());
                TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
                TextView dialogText = dialog.findViewById(R.id.dialogText);
                String subarticles = "";

                for (String sub:text.getSubarticles()) {
                    subarticles += sub +" ";
                }

                dialogText.setText(subarticles);
                dialogTitle.setText(text.getTitle());
                dialog.show();
            }
        });
    }

}