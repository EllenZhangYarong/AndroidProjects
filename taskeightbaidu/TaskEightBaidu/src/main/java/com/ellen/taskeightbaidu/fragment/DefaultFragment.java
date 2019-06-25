package com.ellen.taskeightbaidu.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellen.taskeightbaidu.R;

import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultFragment extends android.support.v4.app.Fragment {


    public DefaultFragment() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_default,container,false);

        return view;
    }


}
