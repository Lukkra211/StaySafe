package com.example.pc.staysafe.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.staysafe.Model.Objects.Article;
import com.example.pc.staysafe.R;

import java.util.ArrayList;

/**
 * Created by balda on 20.03.2018.
 */

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.test_article, parent, false);
        }

        TextView titleText = (TextView) convertView.findViewById(R.id.mainTitle);
        TextView timeToReadText = (TextView) convertView.findViewById(R.id.timeToRead);

        if (art.getTitle() != null) {
            titleText.setText("Nazev testu " + art.getTitle());
        }

        if (Integer.toString(art.getTimeToRead()) != null) {
            timeToReadText.setText("Tvůj čas pro čtení je: " + Integer.toString(art.getTimeToRead()));
        }

        return convertView;
    }
}
