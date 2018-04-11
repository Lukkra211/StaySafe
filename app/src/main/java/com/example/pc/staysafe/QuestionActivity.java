package com.example.pc.staysafe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pc.staysafe.adapters.MyPagerAdapter;
import com.example.pc.staysafe.adapters.NonSwipeableViewPager;
import com.example.pc.staysafe.fragments.FinalPageFragment;
import com.example.pc.staysafe.fragments.QuestionFragment;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Question;
import com.example.pc.staysafe.objects.Result;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionActivity extends AppCompatActivity {

    static final String ARTICLE_ID_KEY = "id";

    public static String QUESTIONID_VALUE = "questionID";
    public static String TYPEKEY_VALUE = "type";
    public static String QUESTIONKEY_VALUE = "qustions";

    public static String ARTICLEIDKEY_VALUE = "articleID";

    private int MAX_FRAGMENTS;
    private int CURRENT_FRAGMENT;

    private int articleID;
    private NonSwipeableViewPager viewPager;
    private ArticleDatabase database;
    private ArrayList<Fragment> qFragments;
    private ArrayList<QuestionFragment> questionFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Init all params
        CURRENT_FRAGMENT = 0;
        database = ArticleDatabase.getDatabase(this);
        Bundle bundle = getIntent().getExtras();

        ArrayList<Question> questions = new ArrayList<>();
        questionFragments = new ArrayList<>();

        if (bundle != null) {
            articleID = bundle.getInt(ARTICLE_ID_KEY);

            questions.addAll(Arrays.asList(database.questionDao().getQuestions(articleID)));
            qFragments = new ArrayList<>();

            //Creates all fragments
            for (Question q: questions) {
                QuestionFragment questionFragment = createFragment(q);
                qFragments.add(questionFragment);
                questionFragments.add(questionFragment);
            }
            qFragments.add(new FinalPageFragment());

            MAX_FRAGMENTS = qFragments.size();
            //Set swiping between screens
            MyPagerAdapter myPA = new MyPagerAdapter(this, qFragments);
            viewPager = findViewById(R.id.view_handler);
            viewPager.setAdapter(myPA);
        }
    }

    public void continueButton(View v){
        Button continueB = v.findViewById(R.id.continueButton);
        CURRENT_FRAGMENT += 1;
        if(CURRENT_FRAGMENT < MAX_FRAGMENTS - 1) {
            viewPager.setCurrentItem(CURRENT_FRAGMENT);
        }else if (continueB.getText() != "FINALIZE") {
            continueB.setText("FINALIZE");
            viewPager.setCurrentItem(MAX_FRAGMENTS);
            FinalPageFragment finalFragment = (FinalPageFragment) qFragments.get(qFragments.size() - 1);
            for (Result res : getResults()) {
                finalFragment.createResult(res);
            }
            finalFragment.createLayout();
        } else if(CURRENT_FRAGMENT > MAX_FRAGMENTS){
                Intent back = new Intent(getBaseContext(), DangerActivity.class);
                startActivity(back);
                finish();
            }
    }

    //Create fragment, set its arguments and pass them
    public QuestionFragment createFragment(Question question){
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTIONID_VALUE, question.questionId);
        bundle.putInt(TYPEKEY_VALUE, question.type);
        bundle.putString(QUESTIONKEY_VALUE, question.question);
        bundle.putInt(ARTICLE_ID_KEY, articleID);
        QuestionFragment qFragment = new QuestionFragment();
        qFragment.setArguments(bundle);
        return qFragment;
    }

    public ArrayList<Result> getResults(){
        ArrayList<Result> results = new ArrayList<>();
        for(QuestionFragment qFrag: questionFragments){
            results.add(qFrag.getResult());
        }
        return results;
    }

    public ArticleDatabase getDatabase(){
        return  database;
    }
}
