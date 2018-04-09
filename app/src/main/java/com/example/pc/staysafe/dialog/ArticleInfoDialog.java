package com.example.pc.staysafe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pc.staysafe.R;
import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.utils.ColorUtils;

/**
 * Class that helps manage ArticleInfo dialog
 */
public class ArticleInfoDialog {
    private String TAG = "ArticleInfoDialog";
    private Context context;

    /** Actual dialog that the class manipulates with */
    private Dialog dialog;

    /* View widgets */
    private TextView textView_title;
    private TextView textView_minutes;
    private TextView textView_pages;
    private TextView textView_difficulty;
    private Button button_read;
    private Button button_test;
    private LinearLayout linearLayoutRoot;

    private View.OnClickListener buttonListener;


    /** Enable dialog to be created without specified view, uses `dialog_articleInfo` as default */
    public ArticleInfoDialog(Context context, View.OnClickListener buttonListener) {
        this(context,
             LayoutInflater.from(context).inflate(R.layout.dialog_article_info, null),
             buttonListener);
    }

    public ArticleInfoDialog(Context context, View v, View.OnClickListener buttonListener) {
        Log.d(TAG, "Article dialog helper created");
        this.context = context;
        this.buttonListener = buttonListener;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(v);

        initParams(v);
        initUI();
    }

    private void initParams(View v) {
        textView_title = v.findViewById(R.id.dialog_articleInfo_title);
        textView_minutes = v.findViewById(R.id.dialog_articleInfo_minutes);
        textView_pages = v.findViewById(R.id.dialog_articleInfo_pages);
        textView_difficulty = v.findViewById(R.id.dialog_articleInfo_difficulty);
        linearLayoutRoot = v.findViewById(R.id.dialog_articleInfo_root);

        button_read = v.findViewById(R.id.dialog_articleInfo_read);
        button_test = v.findViewById(R.id.dialog_articleInfo_test);
    }

    private void initUI() {
        button_read.setOnClickListener(buttonListener);
        button_test.setOnClickListener(buttonListener);
    }

    /**
     * Show info about the given article
     * @param article Article which information should be shown in the dialog
     */
    public void show(Article article) {
        Log.v(TAG, "Show article " + Integer.toString(article.articleId));

        // Visual settings
        textView_title.setText(article.title);
        textView_title.setBackgroundColor(article.getColor(context));
        textView_minutes.setText(context.getString(R.string.dialog_article_info_minutes_answear,
                                                   article.minutes));
        textView_pages.setText(context.getString(R.string.dialog_article_info_pages_answear,
                article.minutes, 20));
        textView_difficulty.setText(context.getString(R.string.dialog_article_info_difficulty_answear,
                article.getDifficulty()));
        linearLayoutRoot.setBackgroundColor(ColorUtils.lighten(article.getColor(context), .75));

        // Show dialog
        dialog.show();
    }
}
