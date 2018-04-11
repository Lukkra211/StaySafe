package com.example.pc.staysafe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.staysafe.ArticleActivity;
import com.example.pc.staysafe.R;

//Fragmet to show text from pages
public class ArticleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article, container, false);
        Bundle bundle = getArguments();

        //Gets values from bundle passed from main activity
        String subtitle = bundle.getString(ArticleActivity.SUBTEXTKEY_VALUE);
        String text = bundle.getString(ArticleActivity.TEXTKEY_VALUE);

        //Gets textview
        TextView subtitleView = view.findViewById(R.id.subtitleView);
        TextView textView =  view.findViewById(R.id.textView);

        //Set text in xml
        subtitleView.setText(subtitle);
        textView.setText(Html.fromHtml(text));

        return view;
    }
}
