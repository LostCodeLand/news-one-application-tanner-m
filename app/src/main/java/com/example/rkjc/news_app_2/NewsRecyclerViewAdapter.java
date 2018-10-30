package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private static final String TAG = "### NewsRecyclerViewAdapter ### ";
    // TODO add onClickListener
    // https://dzone.com/articles/click-listener-for-recyclerview-adapter

    private ArrayList<NewsItem> newsItemList;
    private Context mContext;


    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> feedItemList) {
        this.newsItemList = feedItemList;
        this.mContext = context;
    }

    public void SetItemArrayList(ArrayList<NewsItem> newsItemList){
        this.newsItemList = newsItemList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.news_item, viewGroup, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        view.setOnClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder aViewHolder, int index) {
        NewsItem newsItem = newsItemList.get(index);
        aViewHolder.bind(newsItem);
    }


    class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView n_title;
        public TextView n_description;
        public TextView n_time;
        public TextView n_spare;
        public String mUrlString;


        public NewsViewHolder(View view) {
            super(view);
            this.n_title = (TextView) view.findViewById(R.id.news_title);
            this.n_description = (TextView) view.findViewById(R.id.news_description);
            this.n_time = (TextView) view.findViewById(R.id.news_time);
            this.n_spare = (TextView) view.findViewById(R.id.news_spare);
        }

        public void bind(NewsItem newsItem) {
            mUrlString = newsItem.getUrl();
            Log.d(TAG, "*** bind URL ***" + mUrlString);
            n_title.setText("Title: " + newsItem.getTitle());
            n_description.setText("Description: " + newsItem.getDescription());
            n_time.setText("Date: " + newsItem.getTime());
            n_spare.setText("URL: " + newsItem.getUrl());
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
