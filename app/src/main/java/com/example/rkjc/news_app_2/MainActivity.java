package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity ***myCode***";
    private EditText mSearchBoxEditText;
    private ProgressBar mProgressBar;
    private TextView mTextView_1;
    private RecyclerView mRecycleView;
    private List<FeedItem> feedsList;
    private RcRecyclerViewAdapter rcViewAdapter;

    //TODO continue with recyclerView example
    // https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example#40584425
    // https://stacktips.com/tutorials/android/android-recyclerview-example

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*** start onCreate method ***");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.search_box_1);
        mProgressBar = (ProgressBar) findViewById(R.id.progressB);
        mTextView_1 = (TextView) findViewById(R.id.textview1);

        // attach to the recyclerView item in the activity_main.xml
        mRecycleView = (RecyclerView) findViewById(R.id.rv_listItems);
        LinearLayoutManager cyclayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(cyclayoutManager);

        // for testing purposes
        feedsList = new ArrayList<FeedItem>();

        feedsList.add(new FeedItem("a-1", "a-2", "a-3"));
        feedsList.add(new FeedItem("b-1", "b-2", "b-3"));
        feedsList.add(new FeedItem("c-1", "c-2", "c-3"));

        Log.d(TAG, "*** finished onCreate method ***");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private URL makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        String urlString = githubSearchUrl.toString();
        Log.d(TAG + "in makeGithubSearchQuery", urlString);
        return githubSearchUrl;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatWasClickedId = item.getItemId();

        if(itemThatWasClickedId == R.id.action_search){
            Context context = MainActivity.this;
            URL url = makeGithubSearchQuery();
            GithubQueryTask task = new GithubQueryTask();
            task.execute(url);

            return true;
        }

        if(itemThatWasClickedId == R.id.make_toast){
            Context context = MainActivity.this;
            String textToShow = "Toast is made";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }

        if(itemThatWasClickedId == R.id.push_button){
            Context context = MainActivity.this;
            rcViewAdapter = new RcRecyclerViewAdapter(this, feedsList);
            mRecycleView.setAdapter(rcViewAdapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class GithubQueryTask extends AsyncTask <URL, Void, String> {
        @Override
        protected void onPreExecute(){
            Log.d(TAG + "onPreExecute", "*** should happen first ***");
        }

        @Override
        protected String doInBackground(URL... params){
            Log.d(TAG + " doInBackground", "calling doInBackground");

            String githubSearchResults = "";
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String str){
            Log.d(TAG + "onPostExecute", str);
            mTextView_1.setText(str);
        }
    }
}
