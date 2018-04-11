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
            for (int i = 1; i <= 5; i++) {
                ContentValues values = new ContentValues();
                values.put("title", "nadpis_cislo_" + String.valueOf(i));
                values.put("pages", i / 3 + 2);
                values.put("minutes", i + 10);
                values.put("difficulty", i);
                long articleid = db.insert("article", SQLiteDatabase.CONFLICT_FAIL, values);

                for (int x = 1; x <= i / 3 + 2; x++) {
                    ContentValues page = new ContentValues();
                    page.put("article_id", articleid);
                    page.put("subtitle", "podtitulek_" + String.valueOf(i) + '_' + String.valueOf(x));
                    page.put("text", "Lorem ipsum amet.");
                    db.insert("page", SQLiteDatabase.CONFLICT_FAIL, page);
                }

                ContentValues questions = new ContentValues();
                questions.put("article_id", articleid);
                questions.put("type", 1);
                questions.put("question", "Co je Pica?");
                long question1Id = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions);
                ContentValues answers = new ContentValues();
                answers.put("question_id", question1Id);
                answers.put("answer", "Tvoje mama");
                answers.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers);


                ContentValues questions1 = new ContentValues();
                questions1.put("article_id", articleid);
                questions1.put("type", 2);
                questions1.put("question", "Mrdas s tvoji mamou?");
                long question1Id2 = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions1);
                ContentValues answers1 = new ContentValues();
                answers1.put("question_id", question1Id2);
                answers1.put("answer", "Ne ty");
                answers1.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers1);
                ContentValues answers2 = new ContentValues();
                answers2.put("question_id", question1Id2);
                answers2.put("answer", "Samozrejme");
                answers2.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers2);
                ContentValues answers3 = new ContentValues();
                answers3.put("question_id", question1Id2);
                answers3.put("answer", "Di do prdele");
                answers3.put("correct", false);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers3);

                ContentValues questions2 = new ContentValues();
                questions2.put("article_id", articleid);
                questions2.put("type", 3);
                questions2.put("question", "Tvoje neoblibenejsi cislo");
                long question2Id = db.insert("question", SQLiteDatabase.CONFLICT_FAIL, questions2);
                ContentValues answers4 = new ContentValues();
                answers4.put("question_id", question2Id);
                answers4.put("answer", 69);
                answers4.put("correct", true);
                db.insert("answer", SQLiteDatabase.CONFLICT_FAIL, answers4);

                ContentValues questions3 = new ContentValues();
                questions3.put("article_id", articleid);
                questions3.put("type", 3);
                questions3.put("question", "Jebes tvoji mamu?");
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
