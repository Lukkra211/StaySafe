package com.example.pc.staysafe.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Text representing one page of {@link Article}
 */
@Entity(tableName = "page", foreignKeys = @ForeignKey(
        entity = Article.class,
        parentColumns = "article_id",
        childColumns = "page_id"))
public class Page {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "page_id")
    public int pageId;

    @ColumnInfo(name = "article_id")
    public int articleId;

    public String subtitle;
    public String text;
}
