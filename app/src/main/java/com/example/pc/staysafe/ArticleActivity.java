package com.example.pc.staysafe;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewParent;

import com.example.pc.staysafe.adapters.MyPagerAdapter;
import com.example.pc.staysafe.fakeModel.FakeModel;
import com.example.pc.staysafe.fakeModel.FakePage;
import com.example.pc.staysafe.fragments.ArticleFragment;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity {
    public static String SUBTEXTKEY_VALUE = "subtext";
    public static String TEXTKEY_VALUE = "text";

    private FakeModel fkModel;
    private ArrayList<FakePage> fkPages;
    private ArrayList<Fragment> arFragments;

    private ViewPager viewPager;
    private MyPagerAdapter myPA;
    /*Takes values from Pages and then pass them to call method to crate fragment
    And set pager adapter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        fkModel = new FakeModel();
        fkPages = fkModel.getFkpages();
        arFragments = new ArrayList<>();

        for (FakePage fkpag:fkPages){
            arFragments.add(createFragment(fkpag));
        }

        myPA = new MyPagerAdapter(this, arFragments);
        viewPager = findViewById(R.id.view_handler);
        viewPager.setAdapter(myPA);
    }
    //Create fragment, set its arguments and pass them
    public Fragment createFragment(FakePage fakePage){
        Bundle bundle = new Bundle();
        bundle.putString(SUBTEXTKEY_VALUE, fakePage.subtitle);
        bundle.putString(TEXTKEY_VALUE, fakePage.text);
        ArticleFragment artFragment = new ArticleFragment();
        artFragment.setArguments(bundle);
        return artFragment;
    }
}
