package com.example.pc.staysafe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pc.staysafe.R;

public class ArticleFragment extends Fragment {
    public static String SUBTEXTKEY_VALUE = "subtext";
    public static String TEXTKEY_VALUE = "text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        Bundle bundle = getArguments();
        String subtitle = bundle.getString(SUBTEXTKEY_VALUE);
        String text = bundle.getString(TEXTKEY_VALUE);

        TextView subtitleView = view.findViewById(R.id.subtitleView);
        TextView textView =  view.findViewById(R.id.textView);

        subtitleView.setText(subtitle);
        textView.setText(text);
        // Inflate the layout for this fragment
        return view;

    }
}
