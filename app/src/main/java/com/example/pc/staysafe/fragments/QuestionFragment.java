package com.example.pc.staysafe.fragments;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.pc.staysafe.QuestionActivity;
import com.example.pc.staysafe.R;
import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Question;
import com.example.pc.staysafe.objects.Result;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionFragment extends Fragment {

    private LinearLayout answerLayout;
    private ArrayList<Answer> answers;
    private String question;
    private int type;

    private EditText singleEntry;
    private EditText numberEntry;
    private RadioGroup multipleGroup;
    private RadioGroup booleanGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        Bundle bundle = getArguments();

        answerLayout = view.findViewById(R.id.answerLayout);
        QuestionActivity activity = (QuestionActivity) getActivity();
        answers = new ArrayList<>();

        //Gets values from bundle passed from main activity
        if (bundle != null) {
            int questionId = bundle.getInt(QuestionActivity.QUESTIONID_VALUE);
            type = bundle.getInt(QuestionActivity.TYPEKEY_VALUE);
            question = bundle.getString(QuestionActivity.QUESTIONKEY_VALUE);

            answers.addAll(Arrays.asList(activity.getDatabase().answerDao().getAnswers(questionId)));

            //Gets textview
            TextView title = view.findViewById(R.id.titleView);

            //Set text in xml
            title.setText(question);

            setAnswerLayout();
        }

        return view;
    }


    //Set answer type
    private void setAnswerLayout() {
        switch (Question.Type.fromInt(type)) {
            case SINGLE_ANSWEAR:
                createSingleAnswer();
                break;
            case MULTIPLE_ANSWEAR:
                createMultipleAnswer();
                break;
            case NUMBER:
                createNumberAnswer();
                break;
            case TRUE_FALSE:
                createBooleanAnswer();
                break;
        }
    }

    //Create SingleAnswer
    private void createSingleAnswer() {
        singleEntry = new EditText(getContext());
        singleEntry.setHint("Enter answer");
        answerLayout.addView(singleEntry);
    }

    //Create MultipleAnswer
    private void createMultipleAnswer() {
        multipleGroup = new RadioGroup(getContext());

        for (Answer answer : answers) {
            RadioButton radButton = new RadioButton(getContext());
            radButton.setText(answer.answer);
            multipleGroup.addView(radButton);
        }
        answerLayout.addView(multipleGroup);
    }

    //Create NumberAnswer
    private void createNumberAnswer() {
        numberEntry = new EditText(getContext());
        numberEntry.setHint("Enter number");
        numberEntry.setInputType(InputType.TYPE_CLASS_NUMBER);
        answerLayout.addView(numberEntry);
    }

    //Create BoleanAnswer
    private void createBooleanAnswer() {
        booleanGroup = new RadioGroup(getContext());
        booleanGroup.setOrientation(LinearLayout.HORIZONTAL);

        RadioButton trueButton = new RadioButton(getContext());
        trueButton.setText("Yes");
        booleanGroup.addView(trueButton);

        RadioButton falseButton = new RadioButton(getContext());
        falseButton.setText("No");
        booleanGroup.addView(falseButton);

        answerLayout.addView(booleanGroup);
    }

    private String getUserAnswer() {
        String userAnswer = "";
        switch (Question.Type.fromInt(type)) {
            case SINGLE_ANSWEAR:
                userAnswer = singleEntry.getText().toString();
                break;

            case MULTIPLE_ANSWEAR:
                if (multipleGroup.getCheckedRadioButtonId() != -1) {
                    int id = multipleGroup.getCheckedRadioButtonId();
                    View radioButton = multipleGroup.findViewById(id);
                    int radioId = multipleGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) multipleGroup.getChildAt(radioId);
                    userAnswer = (String) btn.getText();
                }
                break;

            case NUMBER:
                userAnswer = String.valueOf(numberEntry.getText().toString());
                break;

            case TRUE_FALSE:
                if (booleanGroup.getCheckedRadioButtonId() != -1) {
                    int id = booleanGroup.getCheckedRadioButtonId();
                    View radioButton = booleanGroup.findViewById(id);
                    int radioId = booleanGroup.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) booleanGroup.getChildAt(radioId);
                    userAnswer = (String) btn.getText();
                }
                break;
        }
        return userAnswer;
    }

    public Result getResult() {
        return new Result(question, this.answers, getUserAnswer(), type);
    }
}
