package com.example.pc.staysafe;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.staysafe.adapters.MyPagerAdapter;
import com.example.pc.staysafe.fragments.ArticleFragment;
import com.example.pc.staysafe.model.database.ArticleDatabase;
import com.example.pc.staysafe.model.entity.Page;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*Takes values from Pages and then pass them to call method to create fragment
and set pager adapter.
 */
public class ArticleActivity extends AppCompatActivity {
    static final String ARTICLE_ID_KEY = "id";

    public static String SUBTEXTKEY_VALUE = "subtext";
    public static String TEXTKEY_VALUE = "text";

    private ArrayList<Page> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        //Init all params
        ArticleDatabase database = ArticleDatabase.getDatabase(this);
        Bundle bundle = getIntent().getExtras();

        pages = new ArrayList<>();

        if (bundle != null) {
            int id = bundle.getInt(ARTICLE_ID_KEY);
            pages.addAll(Arrays.asList(database.pageDao().getArticlePages(id)));
            ArrayList<Fragment> arFragments = new ArrayList<>();

            //Creates all fragments
            for (Page pag : pages) {
                arFragments.add(createFragment(pag));
            }

            //Set swiping between screens
            MyPagerAdapter myPA = new MyPagerAdapter(this, arFragments);
            ViewPager viewPager = findViewById(R.id.view_handler);
            viewPager.setAdapter(myPA);
        }
    }
    //Create fragment, set its arguments and pass them
    public Fragment createFragment(Page page){
        Bundle bundle = new Bundle();
        bundle.putString(SUBTEXTKEY_VALUE, page.subtitle);
        bundle.putString(TEXTKEY_VALUE, page.text);
        ArticleFragment artFragment = new ArticleFragment();
        artFragment.setArguments(bundle);
        return artFragment;
    }
}
