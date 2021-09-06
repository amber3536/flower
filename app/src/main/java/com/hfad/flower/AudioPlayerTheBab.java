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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayerTheBab extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private String tr = "TRACK";
    private int playAllOn = 0;
    private int maxNumTracks = 1; //change later
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private GradientDrawable gradientDrawable;
    final String prayer1 = "Lauded be Thy name...";
    final String prayer2 = "It is better to guide...";
    final String prayer3 = "God loveth those who are...";
    final String prayer4 = "God hath, at all times...";
    final String prayer5 = "Rid thou thyself...";
    final String prayer6 = "He--glorified be His mention...";
    final String prayer7 = "Say: Praise be to God";
    final String prayer8 = "Glory be unto Thee...";
    final String prayer9 = "I beg Thee to forgive me...";
    final String prayer10 = "Glory be to Thee, O God!";
    final String prayer11 = "O Lord! Enable all the peoples...";
    final String prayer12 = "Throughout eternity Thou hast been...";
    final String prayer13 = "I adjure Thee by Thy might...";
    final String prayer14 = "Praise be to Thee...";
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
        forwardBtn = view.findViewById(R.id.fab_forward);
        backBtn = view.findViewById(R.id.fab_back);
        img = view.findViewById(R.id.audio_img);
        txt = view.findViewById(R.id.audio_txt);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            track = bundle.getString("EXTRA", "");
            //Log.i("here", "onCreateView: " + position);
        }

        if (savedInstanceState != null) {

            track = savedInstanceState.getString(tr, track);
            Log.i("Audio The Bab", "onCreateView: " + track);
        }


        playTrack(track);


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);

                if (playAllOn == 1) {
                    playAllOn = 0;
                    playAll(trackNum);
                }
                else {
                    mp.start();
                }
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

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(track) {
                    case prayer1:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer2:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer3:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer4;
                        playTrack(track);
                        break;
                    case prayer4:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
                        playTrack(track);
                        break;
                    case prayer5:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer6;
                        playTrack(track);
                        break;
                    case prayer6:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer7;
                        playTrack(track);
                        break;
                    case prayer7:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer8;
                        playTrack(track);
                        break;
                    case prayer8:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer9;
                        playTrack(track);
                        break;
                    case prayer9:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer10;
                        playTrack(track);
                        break;
                    case prayer10:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer11;
                        playTrack(track);
                        break;
                    case prayer11:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer12;
                        playTrack(track);
                        break;
                    case prayer12:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case prayer13:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer14;
                        playTrack(track);
                        break;
                    case prayer14:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case "all":
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == maxNumTracks)
                            trackNum = 0;
                        else
                            trackNum++;
                        playAll(trackNum);
                        //txt.setText(prayerArray[0]);
                        break;

                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(track) {
                    case prayer1:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer14;
                        playTrack(track);
                        break;
                    case prayer2:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case prayer3:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer4:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer5:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer4;
                        playTrack(track);
                        break;
                    case prayer6:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
                        playTrack(track);
                        break;
                    case prayer7:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer6;
                        playTrack(track);
                        break;
                    case prayer8:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer7;
                        playTrack(track);
                        break;
                    case prayer9:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer8;
                        playTrack(track);
                        break;
                    case prayer10:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer9;
                        playTrack(track);
                        break;
                    case prayer11:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer10;
                        playTrack(track);
                        break;
                    case prayer12:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer11;
                        playTrack(track);
                        break;
                    case prayer13:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer12;
                        playTrack(track);
                        break;
                    case prayer14:
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case "all":
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == maxNumTracks)
                            trackNum = 0;
                        else
                            trackNum++;
                        playAll(trackNum);
                        //txt.setText(prayerArray[0]);
                        break;

                }
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
                else if (track.equals("all") && trackNum == 1) {
                    trackNum = 0;
                    playAllOn = 1;
                    playBtn.setVisibility(View.VISIBLE);
                    pauseBtn.setVisibility(View.GONE);
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
                mp.setOnCompletionListener(listener);
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
                playBtn.setVisibility(View.INVISIBLE);
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
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                mp.start();
                break;
        }
    }

    private void playTrack(String track) {
        switch(track) {
            case prayer1:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.o_thou_whose_face2);
                mp.setOnCompletionListener(listener);
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
            case prayer2:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedOlive),
                                ContextCompat.getColor(getContext(), R.color.fadedTurquoise)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.it_is_better_to_guide_foreground);
                txt.setText(prayerArray[1]);
                break;
            case prayer3:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
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
            case prayer4:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
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
            case prayer5:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorFadedDarkPurple),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.rid_thou_thyself_foreground);
                txt.setText(prayerArray[4]);
                break;
            case prayer6:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
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
            case prayer7:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
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
            case prayer8:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
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

            case prayer9:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case prayer10:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case prayer11:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case prayer12:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case prayer13:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case prayer14:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                mp.setOnCompletionListener(listener);
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
            case "all":
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        //outState.putInt(STATE_COUNTER, mCounter);
        Log.i("Audio The Bab", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

}
