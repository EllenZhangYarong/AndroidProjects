package com.ellen.taskninefragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ellen.taskninefragment.fragments.FragmentBack;
import com.ellen.taskninefragment.fragments.FragmentFront;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Boolean mShowingBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowingBack = false;

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new FragmentFront())
                    .commit();
        }

        findViewById(R.id.container).setOnClickListener(this);

    }

    private void flipCard() {

        Log.e("TAG", "FlipCards---" + mShowingBack.toString());

        if (mShowingBack) {
            getFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                .replace(R.id.container, new FragmentBack())
                .addToBackStack(null)
                .commit();



    }

    @Override
    public void onClick(View v) {
        Log.e("TAG", "onClickView" + mShowingBack.toString());
        flipCard();
    }
}
