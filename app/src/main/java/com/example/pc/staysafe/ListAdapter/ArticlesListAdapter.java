package com.example.pc.staysafe.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.R;

import java.util.ArrayList;

/**
 * Give Item into ListView (It is made for Article class)
 */
public class ArticlesListAdapter extends ArrayAdapter<Article> {

    public ArticlesListAdapter(Context context, ArrayList<Article> art) {
        super(context, 0, art);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Article art = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_test_article, parent, false);
        }

        TextView titleText = (TextView) convertView.findViewById(R.id.mainTitle);
        TextView timeToReadText = (TextView) convertView.findViewById(R.id.timeToRead);
        TextView questionCount = (TextView) convertView.findViewById(R.id.questionCount);

        if (art != null) {
            titleText.setText("Nazev testu: " + art.title);
            timeToReadText.setText("Tvůj čas pro čtení je: " + Integer.toString(art.minutes));
            questionCount.setText("Počet stránek je: " + Integer.toString(art.pages));
        }

        return convertView;
    }
}
