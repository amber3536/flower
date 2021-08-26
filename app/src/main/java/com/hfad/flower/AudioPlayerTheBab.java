package com.hfad.flower;

import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayerTheBab extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private GradientDrawable gradientDrawable;
    String[] prayerArray = {"Lauded be Thy name...", "It is better to guide...", "God loveth those who are...",
            "God hath, at all times...", "Rid thou thyself...", "He--glorified be His mention...",
            "Say: Praise be to God", "Glory be unto Thee...", "I beg Thee to forgive me...", "Glory be to Thee, O God!",
            "O Lord! Enable all the peoples...", "Throughout eternity Thou hast been...", "I adjure Thee by Thy might...",
            "Praise be to Thee..."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_audio, container, false);

        playBtn = view.findViewById(R.id.fab_play);
        pauseBtn = view.findViewById(R.id.fab_pause);
        img = view.findViewById(R.id.audio_img);
        txt = view.findViewById(R.id.audio_txt);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            track = bundle.getString("EXTRA", "");
            //Log.i("here", "onCreateView: " + position);
        }



        switch(track) {
            case "Lauded be Thy name...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.o_thou_whose_face2);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lauded_be_thy_name_foreground);
                txt.setText(prayerArray[0]);
                break;
            case "It is better to guide...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.it_is_better_to_guide_foreground);
                txt.setText(prayerArray[1]);
                break;
            case "God loveth those who are...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.god_loveth_those_who_are_foreground);
                txt.setText(prayerArray[2]);
                break;
            case "God hath, at all times...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.god_hath_at_all_times_foreground);
                txt.setText(prayerArray[3]);
                break;
            case "Rid thou thyself...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.rid_thou_thyself_foreground);
                txt.setText(prayerArray[4]);
                break;
            case "He--glorified be His mention...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedOrange),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedOrange)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.he_glorified_be_his_mention_foreground);
                txt.setText(prayerArray[5]);
                break;
            case "Say: Praise be to God":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.say_praise_be_to_god_foreground);
                txt.setText(prayerArray[6]);
                break;
            case "Glory be unto Thee...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPurple),
                                ContextCompat.getColor(getContext(), R.color.colorFadedDarkPurple),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.glory_be_unto_thee_foreground);
                txt.setText(prayerArray[7]);
                break;
            case "all":
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
                break;
            case "I beg Thee to forgive me...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.i_beg_thee_to_forgive_me_foreground);
                txt.setText(prayerArray[8]);
                break;
            case "Glory be to Thee, O God!":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.glory_be_to_thee_o_god_foreground);
                txt.setText(prayerArray[9]);
                break;
            case "O Lord! Enable all the peoples...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorFadedPurple),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_lord_enable_all_the_peoples_foreground);
                txt.setText(prayerArray[10]);
                break;
            case "Throughout eternity Thou hast been...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedOrange),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.throughout_eternity_thou_hast_been_foreground);
                txt.setText(prayerArray[11]);
                break;
            case "I adjure Thee by Thy might...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.i_adjure_thee_by_thy_might_foreground);
                txt.setText(prayerArray[12]);
                break;
            case "Praise be to Thee...":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedOrange),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.praise_be_to_thee_foreground);
                txt.setText(prayerArray[13]);
                break;

        }


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.GONE);

                mp.start();
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);

                mp.pause();
            }
        });


        mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //performOnEnd();
                // mp.release();
                Log.i("Audio Bahaullah", "onCompletion: " + trackNum);
                if (track.equals("all") && trackNum < 1) {
                    trackNum++;
                    playAll(trackNum);
                }
                else {
                    playBtn.setVisibility(View.VISIBLE);
                    pauseBtn.setVisibility(View.GONE);
                }

            }

        });



        return view;
    }

    private void playAll(int num) {
        switch (num) {
            case 0:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.attract_photo_foreground);
                txt.setText(prayerArray[0]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.GONE);
                mp.start();
                break;
            case 1:
                // mp.release();
                // mp.stop();
                // mp.reset();
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.brooklyn_bridge);
                mp.setOnCompletionListener(listener);
                txt.setText(prayerArray[1]);
                img.setImageResource(R.mipmap.lauded_photo_foreground);
                //mp.setLooping(false);
                mp.start();
                break;
        }
    }


//        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.brooklyn_bridge);
//        GradientDrawable gd = new GradientDrawable(
//                GradientDrawable.Orientation.TOP_BOTTOM,
//                new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
//                        ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
//                        ContextCompat.getColor(getContext(), R.color.colorFadedPink),
//                        ContextCompat.getColor(getContext(), R.color.colorAccent)});
//
//
//
//        view.findViewById(R.id.layout_audio_player).setBackground(gd);
//        img.setImageResource(R.mipmap.lauded_photo_foreground);
//        txt.setText(prayerArray[1]);
//        mp.start();


    @Override
    public void onDestroy() {
        mp.stop();
        Log.i("Audio The Bab", "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i("Audio The Bab", "onResume: ");
        super.onResume();
    }

}
