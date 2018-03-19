package com.example.pc.staysafe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.staysafe.Model.Objects.Article;
import com.example.pc.staysafe.Model.TestModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

        ListView testArticles = findViewById(R.id.TestArticlesContainer);
        for (Article article : art) {

            Log.w("AAA Title", article.getTitle());
            Log.w("AAA Time", Integer.toString(article.getTimeToRead()));

            for (String subarticle : article.getSubarticles()) {
                Log.w("AAA SubArticles", subarticle);
            }
        }

       ArticlesListAdapter testArticlesAdapter = new ArticlesListAdapter(this, art);
        testArticles.setAdapter(testArticlesAdapter);
    }
}
 class ArticlesListAdapter extends ArrayAdapter<Article> {
     public ArticlesListAdapter(Context context, ArrayList<Article> art) {
         super(context, 0, art);
     }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Article art = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.test_article, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView timeToRead = (TextView) convertView.findViewById(R.id.timeToRead);
        if(art.getTitle() != null){
        title.setText("Nazev testu " + art.getTitle());
        }
        if(Integer.toString(art.getTimeToRead()) != null) {
            timeToRead.setText("Tvůj čas pro čtení je: " + Integer.toString(art.getTimeToRead()));
        }

        return convertView;
    }
}