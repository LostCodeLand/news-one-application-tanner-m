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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView mResultsRecyclerView;
    private NewsRecyclerViewAdapter mNewsViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultsRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        mResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatWasClickedId = item.getItemId();

        if(itemThatWasClickedId == R.id.action_search){
            NewsQueryTask task = new NewsQueryTask();
            task.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class NewsQueryTask extends AsyncTask <Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            Log.d(TAG + " doInBackground", "calling doInBackground");
            URL url = NetworkUtils.buildUrl();
            String newsSearchResults = "";
            try {
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newsSearchResults;
        }

        @Override
        protected void onPostExecute(String newsSearchResults){
            Log.d(TAG + "*** onPostExecute ****", newsSearchResults);
            mNewsViewAdapter = new NewsRecyclerViewAdapter(MainActivity.this, JsonUtils.parseNews(newsSearchResults));
            mResultsRecyclerView.setAdapter(mNewsViewAdapter);
        }
    }
}
