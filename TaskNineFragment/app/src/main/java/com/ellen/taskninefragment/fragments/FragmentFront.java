package com.ellen.taskninefragment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskninefragment.R;

/**
 * Fragment represents the front of the card
 * Created by ellen on 15/12/14.
 */
public class FragmentFront extends android.app.Fragment {

    public FragmentFront() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_fragment_front,container,false);
        return root;
    }
}
