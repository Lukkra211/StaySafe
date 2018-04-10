package com.example.pc.staysafe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.pc.staysafe.adapters.MyPagerAdapter;
import com.example.pc.staysafe.adapters.NonSwipeableViewPager;
import com.example.pc.staysafe.fragments.QuestionFragment;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Question;

import java.util.ArrayList;
import java.util.Arrays;

public class QuestionActivity extends AppCompatActivity {

    static final String ARTICLE_ID_KEY = "id";

    public static String QUESTIONID_VALUE = "questionID";
    public static String TYPEKEY_VALUE = "type";
    public static String QUESTIONKEY_VALUE = "qustions";

    private int CURRENT_FRAGMENT;

    private NonSwipeableViewPager viewPager;
    private ArticleDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Init all params
        CURRENT_FRAGMENT = 0;
        database = ArticleDatabase.getDatabase(this);
        Bundle bundle = getIntent().getExtras();

        ArrayList<Question> questions = new ArrayList<>();

        if (bundle != null) {
            int id = bundle.getInt(ARTICLE_ID_KEY);

            questions.addAll(Arrays.asList(database.questionDao().getQuestions(id)));
            ArrayList<Fragment> qFragments = new ArrayList<>();

            //Creates all fragments
            for (Question q: questions) {
                qFragments.add(createFragment(q));
            }

            //Set swiping between screens
            MyPagerAdapter myPA = new MyPagerAdapter(this, qFragments);
            viewPager = findViewById(R.id.view_handler);
            viewPager.setAdapter(myPA);
        }
    }
    public void continueButton(View v){
        CURRENT_FRAGMENT += 1;
        viewPager.setCurrentItem(CURRENT_FRAGMENT);
    }

    //Create fragment, set its arguments and pass them
    public Fragment createFragment(Question question){
        Bundle bundle = new Bundle();
        bundle.putInt(QUESTIONID_VALUE, question.questionId);
        bundle.putInt(TYPEKEY_VALUE, question.type);
        bundle.putString(QUESTIONKEY_VALUE, question.question);
        QuestionFragment qFragment = new QuestionFragment();
        qFragment.setArguments(bundle);
        return qFragment;
    }

    public ArticleDatabase getDatabase(){
        return database;
    }

}
