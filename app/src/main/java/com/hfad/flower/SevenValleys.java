package com.hfad.flower;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SevenValleys extends Fragment {
    private View view;
    MediaPlayer mp;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_coming_soon, container, false);
        tv = view.findViewById(R.id.textView);


        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
            }
        });
        return view;
    }
}
