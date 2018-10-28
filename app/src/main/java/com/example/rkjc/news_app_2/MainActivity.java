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
    private static final String TAG = "### MainActivity ### ";
    private EditText mSearchBoxEditText;
    private ProgressBar mProgressBar;
    private TextView mTextView_1;

    private RecyclerView mResultsRecyclerView;
    private NewsRecyclerViewAdapter mNewsViewAdapter;


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
        mResultsRecyclerView = (RecyclerView) findViewById(R.id.rv_listItems);
        mResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Log.d(TAG, "*** finished onCreate method ***");
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
            Context context = MainActivity.this;
            String newsQuery = mSearchBoxEditText.getText().toString();
            NewsQueryTask task = new NewsQueryTask();

            task.execute(newsQuery);

            return true;
        }

        if(itemThatWasClickedId == R.id.make_toast){
            Context context = MainActivity.this;
            String textToShow = "Toast is made";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class NewsQueryTask extends AsyncTask <String, Void, String> {
        @Override
        protected void onPreExecute(){
            Log.d(TAG + "onPreExecute", "*** should happen first ***");
        }

        @Override
        protected String doInBackground(String... params){
            Log.d(TAG + " doInBackground", "calling doInBackground");

            URL url = NetworkUtils.buildUrl(params[0]);

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
            //mTextView_1.setText(newsSearchResults);
            ArrayList<NewsItem> newsItemList = new ArrayList<NewsItem>();
            JsonUtils.parseNews(newsItemList, newsSearchResults);

            mNewsViewAdapter = new NewsRecyclerViewAdapter(MainActivity.this, newsItemList);
            //mNewsViewAdapter.SetItemArrayList(newsItemList);

            mResultsRecyclerView.setAdapter(mNewsViewAdapter);

        }
    }
}
