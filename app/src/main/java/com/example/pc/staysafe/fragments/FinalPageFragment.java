package com.example.pc.staysafe.fragments;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pc.staysafe.QuestionActivity;
import com.example.pc.staysafe.R;
import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Question;
import com.example.pc.staysafe.objects.Result;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

//Fragmet to show text from pages
public class FinalPageFragment extends Fragment {

    private ScrollView resultLayout;
    private QuestionActivity activity;
    private  LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_finalpage, container, false);

        resultLayout = view.findViewById(R.id.resultLayout);
        activity = (QuestionActivity) getActivity();

        linearLayout =  new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createResult(Result res) {

        TextView question = new TextView(getContext());
        question.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        question.setText(res.question);
        question.setTextSize(20);
        question.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayout.addView(question);

        switch (Question.Type.fromInt(res.type)) {
            case MULTIPLE_ANSWEAR:
                checkMultipleAnswer(res.answers, res.userAnswer);
                break;
            default:
                checkSingleAnswer(res.answers, res.userAnswer);
                break;
        }
        View border = new View(getContext());
        border.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, 2));
        border.setBackgroundColor(Color.BLACK);
        linearLayout.addView(border);
    }
    public void createLayout() {
        resultLayout.addView(linearLayout);
    }

    //Create SingleAnswer
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void checkSingleAnswer(ArrayList<Answer> answers, String userAnswer) {
        boolean wasRight=false;
        for (Answer ans : answers) {
            TextView ansText = new TextView(getContext());
            ansText.setLayoutParams(new TableRow.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
            ansText.setText(ans.answer);
            ansText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            ansText.setTextSize(15);
            if (ans.correct && ans.answer.equals(userAnswer)) {
                setUserAnswer(ans.answer, true);
            } else{
                setUserAnswer(userAnswer, false);
            }
        }
    }

    //Create MultipleAnswer
    private void checkMultipleAnswer(ArrayList<Answer> answers, String userAnswer) {
        for (Answer ans : answers) {
            TextView ansText = new TextView(getContext());
            ansText.setText(ans.answer);
            if (ans.correct) {
                setUserAnswer(ans.answer, true);
            } else {
                if (ans.answer.equals(userAnswer)) {
                    setUserAnswer(ans.answer, false);
                }else {
                    linearLayout.addView(ansText);
                }
            }
        }
    }


    public void setUserAnswer(String userAnswerString, boolean right){
        TextView userAnswer = new TextView(getContext());
        userAnswer.setText(userAnswerString);
        if(right) {
            userAnswer.setTextColor(Color.GREEN);
        }else{
            userAnswer.setTextColor(Color.RED);
        }

        linearLayout.addView(userAnswer);
    }
}
