package com.ellen.taskeightbaidufirstpage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ellen.taskeightbaidufirstpage.LoginActivity;
import com.ellen.taskeightbaidufirstpage.R;

/**
 * Created by ellen on 15/12/8.
 */
public class OrdersPageFragment extends Fragment {
    private Button btnLogin;

    public OrdersPageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_orders_page, container, false);

        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), LoginActivity.class));


            }
        });
        return view;
    }
}
