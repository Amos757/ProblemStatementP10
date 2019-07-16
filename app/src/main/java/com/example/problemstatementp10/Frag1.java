package com.example.problemstatementp10;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag1 extends Fragment {

    Button buttonColor;

    public Frag1() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        buttonColor.findViewById(R.id.buttonColor);

        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonColor.setBackgroundResource(R.color.orange);
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);


    }

}
