package com.example.pc.staysafe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pc.staysafe.R;
import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.utils.ColorUtils;

/**
 * Dialog showing progress bar
 */
public class ProgressBarDialog {
    /** Actual dialog that the class manipulates with */
    private Dialog dialog;

    /* View widgets */
    private ProgressBar progressBar;
    private TextView textView;


    public ProgressBarDialog(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_bar, null);

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));

        initParams(view);
    }

    private void initParams(View v) {
        progressBar = v.findViewById(R.id.dialog_progressBar_progressBar);
        textView = v.findViewById(R.id.dialog_progressBar_text);
    }

    public void show(String text) {
        textView.setText(text);
        dialog.show();
    }

    public void close() {
        dialog.cancel();
    }
}
