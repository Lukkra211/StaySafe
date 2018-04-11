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
                    .allowMainThreadQueries()
                    .addCallback(DB_CALLBACK)
                    .build();
        }
        return instance;
    }

    /* =============================================================================================
     * Migrations and Callbacks
     * =============================================================================================
     */

    static final RoomDatabase.Callback DB_CALLBACK = new RoomDatabase.Callback() {
        public void onCreate (SupportSQLiteDatabase db) {
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

                for(int z=1; z <= 4; z++) {
                    ContentValues questions = new ContentValues();
                    questions.put("article_id", articleid);
                    questions.put("type", z);
                    questions.put("question", "Ses pica?");
                    long questionId = db.insert("questionsTable", SQLiteDatabase.CONFLICT_FAIL, questions);


                        ContentValues answers = new ContentValues();
                        answers.put("question_id", questionId);
                        answers.put("answer", "ANOPico");
                        answers.put("correct", false);
                        db.insert("answerTable", SQLiteDatabase.CONFLICT_FAIL, answers);

                    ContentValues answers2 = new ContentValues();
                    answers.put("question_id", questionId);
                    answers.put("answer", "Kundo");
                    answers.put("correct", false);
                    db.insert("answerTable", SQLiteDatabase.CONFLICT_FAIL, answers);

                    ContentValues answers3 = new ContentValues();
                    answers.put("question_id", questionId);
                    answers.put("answer", "Pico");
                    answers.put("correct", true);
                    db.insert("answerTable", SQLiteDatabase.CONFLICT_FAIL, answers);
                }
            }
        }
    };
}
