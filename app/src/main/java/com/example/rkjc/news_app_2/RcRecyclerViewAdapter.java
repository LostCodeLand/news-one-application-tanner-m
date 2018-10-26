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
import java.util.List;


public class RcRecyclerViewAdapter extends RecyclerView.Adapter<RcRecyclerViewAdapter.RcViewHolder> {

    private List<FeedItem> feedItemList;
    private LayoutInflater mInflater;
    private Context mContext;

    // TODO replace this with a constructor
    public RcRecyclerViewAdapter(Context context, ArrayList<FeedItem> feedItemList) {
        this.mInflater = LayoutInflater.from(context);
        this.feedItemList = feedItemList;
    }


    @Override
    public RcViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View simple_view = mInflater.inflate(R.layout.simple_item, viewGroup, false);
        RcViewHolder viewHolder = new RcViewHolder(simple_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RcViewHolder aViewHolder, int index) {
        FeedItem feedItem = feedItemList.get(index);

        aViewHolder.textView1.setText(feedItem.getStr_1());
        aViewHolder.textView2.setText(feedItem.getStr_2());
        aViewHolder.textView3.setText(feedItem.getStr_3());
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class RcViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        String mUrlString;

        public RcViewHolder(View view) {
            super(view);
            this.textView1 = (TextView) view.findViewById(R.id.simp_text_1);
            this.textView2 = (TextView) view.findViewById(R.id.simp_text_2);
            this.textView3 = (TextView) view.findViewById(R.id.simp_text_3);
        }



        @Override
        public void onClick(View view) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mUrlString)));
        }
    }


}
