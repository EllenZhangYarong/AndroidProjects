package com.ellen.taskeightbaidu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.taskeightbaidu.R;

/**
 * Created by ellen on 15/12/3.
 */
public class OrdersFragment extends Fragment {


    public OrdersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_orders,container,false);
        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Not Finished ! ",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
