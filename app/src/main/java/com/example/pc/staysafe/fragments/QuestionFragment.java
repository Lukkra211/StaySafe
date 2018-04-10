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

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionFragment extends Fragment{

    private LinearLayout answerLayout;
    private ArrayList<Answer> answers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        Bundle bundle = getArguments();

        answerLayout = view.findViewById(R.id.answerLayout);
        QuestionActivity activity = (QuestionActivity)getActivity();
         answers = new ArrayList<>();

        //Gets values from bundle passed from main activity
        if (bundle != null) {
            int questionId = bundle.getInt(QuestionActivity.QUESTIONID_VALUE);
            int type = bundle.getInt(QuestionActivity.TYPEKEY_VALUE);
            String question = bundle.getString(QuestionActivity.QUESTIONKEY_VALUE);

            answers.addAll(Arrays.asList(activity.getDatabase().answerDao().getAnswers(questionId)));

            //Gets textview
            TextView title = view.findViewById(R.id.titleView);

            //Set text in xml
            title.setText(question);

            setAnswerLayout(type);
        }

        return view;
    }


    //Set answer type
    private void setAnswerLayout(int type){
        switch (Question.Type.fromInt(type)){
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
                createBoleanAnswer();
                break;
            default:
                createErrorAnswer();
                break;
        }
    }

    //Create SingleAnswer
    private void createSingleAnswer(){
        EditText entry = new EditText(getContext());
        entry.setHint("Enter answer");
        answerLayout.addView(entry);
    }

    //Create MultipleAnswer
    private void createMultipleAnswer(){
        RadioGroup radGroup = new RadioGroup(getContext());
        for(Answer answer:answers){
            RadioButton radButton = new RadioButton(getContext());
            radButton.setText(answer.answer);
            radGroup.addView(radButton);
        }
        answerLayout.addView(radGroup);
    }
    //Create NumberAnswer
    private void createNumberAnswer(){
        EditText entry = new EditText(getContext());
        entry.setHint("Enter number");
        entry.setInputType(InputType.TYPE_CLASS_NUMBER);
        answerLayout.addView(entry);
    }

    //Create BoleanAnswer
    private void createBoleanAnswer(){
        RadioGroup radGroup = new RadioGroup(getContext());
        radGroup.setOrientation(LinearLayout.HORIZONTAL);

        RadioButton trueButton = new RadioButton(getContext());
        trueButton.setText("Yes");
        radGroup.addView(trueButton);

        RadioButton falseButton = new RadioButton(getContext());
        falseButton.setText("No");
        radGroup.addView(falseButton);

        answerLayout.addView(radGroup);
    }

    //Creates Error when no answer type is given
    public void createErrorAnswer(){
        TextView error = new TextView(getContext());
        error.setText("ERROR ANSWER NOT FOUND");
        answerLayout.addView(error);
        Log.e("Answer", "No type of answer was given");
    }
}
