package com.example.pc.staysafe.ListAdapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
    public View getView(int position, View view, ViewGroup parent) {
        Article article = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.danger_listview_row,
                    parent,
                    false);
        }

        View difficulty = view.findViewById(R.id.listView_row_difficulty);
        TextView title = view.findViewById(R.id.listView_row_title);
        TextView timeToRead = view.findViewById(R.id.listView_row_additionalInfo);

        if (article != null) {
            title.setText(article.title);
            timeToRead.setText(getContext().getString(R.string.danger_listView_additionalInfo,
                                                      article.minutes));
            difficulty.setBackgroundColor(article.getColor(getContext()));
        }

        return view;
    }
}
