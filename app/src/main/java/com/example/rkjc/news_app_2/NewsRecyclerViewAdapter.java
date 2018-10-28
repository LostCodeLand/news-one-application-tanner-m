package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private ArrayList<NewsItem> newsItemList;



    public void SetItemArrayList(ArrayList<NewsItem> newsItemList){
        this.newsItemList = newsItemList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View simple_view = mInflater.inflate(R.layout.news_item, viewGroup, false);
        NewsViewHolder viewHolder = new NewsViewHolder(simple_view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder aViewHolder, int index) {
        NewsItem newsItem = newsItemList.get(index);
        //title, description, time
        aViewHolder.n_title.setText(newsItem.getTitle());
        aViewHolder.n_description.setText(newsItem.getDescription());
        aViewHolder.n_time.setText(newsItem.getTime());
    }


    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView n_title;
        public TextView n_description;
        public TextView n_time;
        String mUrlString;

        public NewsViewHolder(View view) {
            super(view);
            this.n_title = (TextView) view.findViewById(R.id.news_title);
            this.n_description = (TextView) view.findViewById(R.id.news_description);
            this.n_time = (TextView) view.findViewById(R.id.news_time);
        }

        @Override
        public void onClick(View view) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mUrlString)));
        }
    }

    @Override
    public int getItemCount() {
        return (null != newsItemList ? newsItemList.size() : 0);
    }

}
