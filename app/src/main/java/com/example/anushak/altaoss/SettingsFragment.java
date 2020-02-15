package com.example.anushak.altaoss;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

public class SettingsFragment extends Fragment {

    Button b1,b2,b3,b4,b5;
    ScrollView l1,l2,l3,l4,l5;
    View view;

    public static final String TITLE = "Branding Solutions";

    public static SettingsFragment newInstance() {

        return new SettingsFragment();
    }
    // @Nullable
    @Override
    // public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_settings, container, false);

        l1 = (ScrollView) view.findViewById(R.id.l1);
        l2 = (ScrollView) view.findViewById(R.id.l2);
        l3 = (ScrollView) view.findViewById(R.id.l3);
        l4 = (ScrollView) view.findViewById(R.id.l4);
        l5 = (ScrollView) view.findViewById(R.id.l5);

        b1 = (Button) view.findViewById(R.id.b1);
        b2 = (Button) view.findViewById(R.id.b2);
        b3 = (Button) view.findViewById(R.id.b3);
        b4 = (Button) view.findViewById(R.id.b4);
        b5 = (Button) view.findViewById(R.id.b5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);

                b1.setBackgroundColor(Color.RED);
                b2.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.WHITE);
                b4.setBackgroundColor(Color.WHITE);
                b5.setBackgroundColor(Color.WHITE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);

                b1.setBackgroundColor(Color.WHITE);
                b2.setBackgroundColor(Color.RED);
                b3.setBackgroundColor(Color.WHITE);
                b4.setBackgroundColor(Color.WHITE);
                b5.setBackgroundColor(Color.WHITE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.VISIBLE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.GONE);

                b1.setBackgroundColor(Color.WHITE);
                b2.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.RED);
                b4.setBackgroundColor(Color.WHITE);
                b5.setBackgroundColor(Color.WHITE);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.VISIBLE);
                l5.setVisibility(View.GONE);

                b1.setBackgroundColor(Color.WHITE);
                b2.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.WHITE);
                b4.setBackgroundColor(Color.RED);
                b5.setBackgroundColor(Color.WHITE);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);
                l4.setVisibility(View.GONE);
                l5.setVisibility(View.VISIBLE);

                b1.setBackgroundColor(Color.WHITE);
                b2.setBackgroundColor(Color.WHITE);
                b3.setBackgroundColor(Color.WHITE);
                b4.setBackgroundColor(Color.WHITE);
                b5.setBackgroundColor(Color.RED);
            }
        });

         return  view;
    }
}