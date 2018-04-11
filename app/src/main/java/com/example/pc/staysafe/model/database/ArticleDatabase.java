package com.example.pc.staysafe.model.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pc.staysafe.model.dao.AnswerDao;
import com.example.pc.staysafe.model.dao.ArticleDao;
import com.example.pc.staysafe.model.dao.PageDao;
import com.example.pc.staysafe.model.dao.QuestionDao;
import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.model.entity.Page;
import com.example.pc.staysafe.model.entity.Question;

/**
 * Object representing database with DAOs. Act as singleton.
 */
@Database(entities = {Answer.class, Article.class, Page.class, Question.class}, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {
    private static ArticleDatabase instance;

    // Declare all DAOs
    public abstract ArticleDao articleDao();

    public abstract PageDao pageDao();

    public abstract AnswerDao answerDao();

    public abstract QuestionDao questionDao();

    public static ArticleDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.inMemoryDatabaseBuilder(context, ArticleDatabase.class)//, "article-db")
                    .allowMainThreadQueries().addCallback(DB_CALLBACK).build();
        }
        return instance;
    }

    /* =============================================================================================
     * Migrations and Callbacks
     * =============================================================================================
     */

    static final RoomDatabase.Callback DB_CALLBACK = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            /* Here is insertion of initial data. Insertion take place at first run of the app.
             * For now, only testing data are inserted. No real one.
             */
            for (int i = 0; i <= 3; i++) {
                ContentValues values = new ContentValues();
                values.put("title", new String[]{"Social media", "Drugs", "Dangerous locations", "Internet"}[i]);
                values.put("pages", i / 3 + 2);
                values.put("minutes", i + 10);
                values.put("difficulty", i);
                long articleid = db.insert("article", SQLiteDatabase.CONFLICT_FAIL, values);

                ContentValues page = new ContentValues();
                page.put("article_id", articleid);
                page.put("subtitle", "Webside dangers" );
                page.put("text", "Because the internet is easily accessible to anyone, it can be a dangerous place. Know who you're dealing with or what you're getting into. Predators, cyber criminals, bullies, and corrupt businesses will try to take advantage of the unwary visitor.<h3>Social engineering</h3> Social engineering is essentially the art of manipulating people to perform actions or divulge confidential information.There are many examples of scams, including fraud—like Nigerian money offers—and predators/fake friends asking for your private information, passwords, or Social Security number. <h3>Hacked websites</h3> Spam in your inbox, some pop-up messages, and click bait articles/social media posts will try to get you to click on a link that looks like it's taking you to a legitimate website.<br> File sharing and piracy<br> Enable the proper settings on your computer to prevent access not just to files you intend to share, but also to other information on your hard drive, e.g., your tax returns and medical records. This is especially important if you have downloaded or shared material that is protected by copyright laws.<br>");
                db.insert("page", SQLiteDatabase.CONFLICT_FAIL, page);

                ContentValues questions = new ContentValues();
                questions.put("article_id", articleid);
                questions.put("type", 1);
                questions.put("question", "What is article about?");
                long question1Id = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions);
                ContentValues answers = new ContentValues();
                answers.put("question_id", question1Id);
                answers.put("answer", "Safety");
                answers.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers);


                ContentValues questions1 = new ContentValues();
                questions1.put("article_id", articleid);
                questions1.put("type", 2);
                questions1.put("question", "What are most dangerous places?");
                long question1Id2 = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions1);
                ContentValues answers1 = new ContentValues();
                answers1.put("question_id", question1Id2);
                answers1.put("answer", "Poland");
                answers1.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers1);
                ContentValues answers2 = new ContentValues();
                answers2.put("question_id", question1Id2);
                answers2.put("answer", "Czech");
                answers2.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers2);
                ContentValues answers3 = new ContentValues();
                answers3.put("question_id", question1Id2);
                answers3.put("answer", "Germany");
                answers3.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers3);
                ContentValues answers3v2 = new ContentValues();
                answers3v2.put("question_id", question1Id2);
                answers3v2.put("answer", "Italia");
                answers3v2.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers3v2);

                ContentValues questions2 = new ContentValues();
                questions2.put("article_id", articleid);
                questions2.put("type", 3);
                questions2.put("question", "How many people are robbed in this country per year");
                long question2Id = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions2);
                ContentValues answers4 = new ContentValues();
                answers4.put("question_id", question2Id);
                answers4.put("answer", 69);
                answers4.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers4);

                ContentValues questions3 = new ContentValues();
                questions3.put("article_id", articleid);
                questions3.put("type", 4);
                questions3.put("question", "Was this test great?");
                long question3Id = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions3);
                ContentValues answers5 = new ContentValues();
                answers5.put("question_id", question3Id);
                answers5.put("answer", "Yes");
                answers5.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers5);
                ContentValues answers6 = new ContentValues();
                answers6.put("question_id", question3Id);
                answers6.put("answer", "No");
                answers6.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers6);
            }
        }
    };
}
