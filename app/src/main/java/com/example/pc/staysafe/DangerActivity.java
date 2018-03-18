package com.example.pc.staysafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.staysafe.Model.Objects.Article;
import com.example.pc.staysafe.Model.TestModel;

import java.util.ArrayList;

/**
 * Shows all articles on given danger type
 */
public class DangerActivity extends AppCompatActivity {

    private TestModel testModel = new TestModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger);

        ArrayList<Article> art = testModel.getArticles(TestModel.DangerType.REAL);

        for (Article article : art) {
            Log.w("AAA Title", article.getTitle());
            Log.w("AAA Time", Integer.toString(article.getTimeToRead()));
            for (String subarticle : article.getSubarticles()) {
                Log.w("AAA Sub", subarticle);
            }
        }
    }
}