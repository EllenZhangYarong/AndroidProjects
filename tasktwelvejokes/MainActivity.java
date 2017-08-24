package com.ellen.tasktwelvejokes;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static String TAG = "MainActivity";

    private static String URL = "http://1.ellenzhang.applinzi.com/articles-json.php";

    private static final String MY_PREFS_NAME = "MyPrefsFile";

    private ListView lvJokes;
    Date date;
    String jsonStringForPause;


    JokesAsyncTask jokesAsyncTask;

    //    private LruCache lruCacheJokeBeanToString; //Create a CACHE for jokebean.

    RefreshableView refreshableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = new Date(System.currentTimeMillis());

        lvJokes = (ListView) findViewById(R.id.lvJokes);
        lvJokes.setOnItemClickListener(this);

        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(1500);
                    if(isNetworkAvailable()==true){

                        jokesAsyncTask = new JokesAsyncTask();
                        jokesAsyncTask.execute(URL);

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },0);


        if (isNetworkAvailable() != true) {

            Log.d(TAG, "网络不通，这里处理缓存数据");
//            Toast.makeText(this, "Network unvailable, sorry.", Toast.LENGTH_LONG).show();

            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            String jokeJsonString = prefs.getString("joke", "No joke");

            List<JokeBean> jokeBeansList = new ArrayList<>();

            parseJsonString(jokeJsonString, jokeBeansList);

            JokesAdapter adapter = new JokesAdapter(MainActivity.this, jokeBeansList);
            lvJokes.setAdapter(adapter);

        } else {

            jokesAsyncTask = new JokesAsyncTask();
            jokesAsyncTask.execute(URL);

        }

    }

    private void parseJsonString(String jokeJsonString, List<JokeBean> jokeBeansList) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        try {
            jsonArray = new JSONArray(jokeJsonString);
            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                JokeBean jokeBean = new JokeBean();

                jokeBean.setJokeId(jsonObject.getString("ID"));
                jokeBean.setJokeTitle(jsonObject.getString("post_title"));
                jokeBean.setJokeDetail(jsonObject.getString("post_content"));
                jokeBean.setJokeDate(jsonObject.getString("post_date"));

//                System.out.println("----------------------");
//                System.out.println(jokeBean.toString());
//                System.out.println("----------------------");

                jokeBeansList.add(jokeBean);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    *
    * 测试网络状态
    *
    * */
    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        JokeBean jokebean = (JokeBean) parent.getItemAtPosition(position);
//        Toast.makeText(MainActivity.this,"Clicked -- > " + jokebean.toString(),Toast.LENGTH_LONG).show();

        String jokeDetail = jokebean.getJokeDetail();
        Log.d("JokeDetail-->", jokeDetail);

        Intent intent = new Intent(this,JokeDetailMultiPageActivity.class);

        intent.putExtra("jokeDetail", jokeDetail);
        startActivity(intent);


    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String jokeJsonString = prefs.getString("joke", "No joke");

        List<JokeBean> jokeBeansList = new ArrayList<>();

        parseJsonString(jokeJsonString, jokeBeansList);

        JokesAdapter adapter = new JokesAdapter(MainActivity.this, jokeBeansList);
        lvJokes.setAdapter(adapter);
    }

    /*
            *
            * Async access internet
            *
            * */
    class JokesAsyncTask extends AsyncTask<String, Void, List<JokeBean>> {

        @Override
        protected List<JokeBean> doInBackground(String... params) {
            return getJsonData(params[0]);

        }

        @Override
        protected void onPostExecute(List<JokeBean> jokeBeans) {
            super.onPostExecute(jokeBeans);

            JokesAdapter adapter = new JokesAdapter(MainActivity.this, jokeBeans);
            lvJokes.setAdapter(adapter);
        }
    }


    /*
    * 1.get JSON data from URL
    *
    * 2.transform json data into beans
    * */
    private List<JokeBean> getJsonData(String param) {
        List<JokeBean> jokeBeansList = new ArrayList<>();

        try {

            String jsonString = readStream(new URL(param).openStream());

            jsonStringForPause = jsonString;
            parseJsonString(jsonString, jokeBeansList);
            jsonToSharedPreference(jsonString);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return jokeBeansList;
    }

    /*
    * Write Json String into SharedPreference
    * */
    private void jsonToSharedPreference(String jsonString) {

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.putString("joke", jsonString);
        editor.apply();
    }

    /*
    *
    * Obtain Json String from SharedPreference
    * */

    private String getJsonStringFromSharedPreference() {

        String jokeJsonString ;

        //读取SharedPreference文件，获得jsonString,解析
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        jokeJsonString = prefs.getString("joke", "No joke");
        return jokeJsonString;

    }


    /*
    * parse data from webpage
    * */
    private String readStream(InputStream is) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        String result = "";

        try {

            String line = "";
            isr = new InputStreamReader(is, "utf-8");
            br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {

                result += line;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (jokesAsyncTask != null) {
            jokesAsyncTask.cancel(true);
        }
    }
}
