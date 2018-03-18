package com.example.pc.staysafe.Model.Objects;

import java.util.ArrayList;

public class Article {
    private final ArrayList<String> subarticles;
    private final int timeToRead;
    private final String title;

    public Article(String title, int timeToRead, ArrayList<String> subarticles) {
        this.subarticles = subarticles;
        this.timeToRead = timeToRead;
        this.title = title;
    }

    public ArrayList<String> getSubarticles() {
        return subarticles;
    }

    public int getTimeToRead() {
        return timeToRead;
    }

    public String getTitle() {
        return title;
    }
}
