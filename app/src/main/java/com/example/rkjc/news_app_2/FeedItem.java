package com.example.rkjc.news_app_2;

public class FeedItem {
    private String str_1;
    private String str_2;
    private String str_3;

    FeedItem(String st1, String st2, String st3){
        this.str_1 = st1;
        this.str_2 = st2;
        this.str_3 = st3;
    }

    public String getStr_1() {
        return str_1;
    }

    public String getStr_2() {
        return str_2;
    }

    public String getStr_3() {
        return str_3;
    }
}
