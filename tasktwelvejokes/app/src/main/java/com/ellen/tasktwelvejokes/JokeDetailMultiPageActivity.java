package com.ellen.tasktwelvejokes;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.Log;
import android.view.ViewTreeObserver;

public class JokeDetailMultiPageActivity extends FragmentActivity {

    private ViewPager pagesView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_joke_detail_multi_page);
        pagesView = (ViewPager) findViewById(R.id.pages);


        // to get ViewPager width and height we have to wait global layout
        pagesView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                PageSplitter pageSplitter = new PageSplitter(pagesView.getWidth(), pagesView.getHeight(), 1, 0);

                TextPaint textPaint = new TextPaint();
                textPaint.setTextSize(getResources().getDimension(R.dimen.text_size));

                String jokeDetail;

                if (savedInstanceState == null) {
                    Bundle extras = getIntent().getExtras();
                    if (extras == null) {
                        jokeDetail = null;
                    } else {
                        jokeDetail = extras.getString("jokeDetail");
                    }
                } else {
                    jokeDetail = (String) savedInstanceState.getSerializable("jokeDetail");
                }
                Log.d("JokeDetail", jokeDetail);
                Log.d("JokeDetail.lenght", String.valueOf(jokeDetail.length()));

                pageSplitter.append(jokeDetail, textPaint);

                pagesView.setAdapter(new TextPagerAdapter(getSupportFragmentManager(), pageSplitter.getPages()));
                pagesView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }
}
