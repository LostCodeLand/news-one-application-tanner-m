package com.example.rkjc.news_app_2;

public class NewsItem {
    private String title;
    private String description;
    private String time;

    //title, description, time
    NewsItem(String title, String description, String time){
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }
}
