package com.hfad.flower;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
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
    private Intent intent;
    private float pos = 0;
    private String tag = "Audio The Bab";
    private BackgroundSoundService bgSound;
    private String tr = "TRACK";
    private int playAllOn = 0;
    private int numTracks = 1; //change later
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

            pos = savedInstanceState.getFloat("position");
            Log.i(tag, "onCreateView: " + pos);

            track = savedInstanceState.getString(tr, track);
            Log.i("Audio The Bab", "onCreateView: " + track);
        }


        intent = new Intent(getActivity(),BackgroundSoundService.class);
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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
                    requireActivity().startService(intent);
                    //mp.start();
                }
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);

                if (bgSound.isPlaying()) {
                    intent.putExtra("pos", bgSound.getCurrentPosition());
                    Log.i(tag, "onClick: " + bgSound.getCurrentPosition());
                    bgSound.pause();
                }

            }
        });

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(track) {
                    case prayer1:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer2:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer3:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer4;
                        playTrack(track);
                        break;
                    case prayer4:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
                        playTrack(track);
                        break;
                    case prayer5:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer6;
                        playTrack(track);
                        break;
                    case prayer6:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer7;
                        playTrack(track);
                        break;
                    case prayer7:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer8;
                        playTrack(track);
                        break;
                    case prayer8:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer9;
                        playTrack(track);
                        break;
                    case prayer9:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer10;
                        playTrack(track);
                        break;
                    case prayer10:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer11;
                        playTrack(track);
                        break;
                    case prayer11:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer12;
                        playTrack(track);
                        break;
                    case prayer12:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case prayer13:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer14;
                        playTrack(track);
                        break;
                    case prayer14:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case "all":
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == numTracks)
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
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer14;
                        playTrack(track);
                        break;
                    case prayer2:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case prayer3:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer4:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer5:
                        if (bgSound.isPlaying())
                            bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer4;
                        playTrack(track);
                        break;
                    case prayer6:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
                        playTrack(track);
                        break;
                    case prayer7:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer6;
                        playTrack(track);
                        break;
                    case prayer8:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer7;
                        playTrack(track);
                        break;
                    case prayer9:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer8;
                        playTrack(track);
                        break;
                    case prayer10:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer9;
                        playTrack(track);
                        break;
                    case prayer11:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer10;
                        playTrack(track);
                        break;
                    case prayer12:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer11;
                        playTrack(track);
                        break;
                    case prayer13:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer12;
                        playTrack(track);
                        break;
                    case prayer14:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case "all":
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == numTracks)
                            trackNum = 0;
                        else
                            trackNum++;
                        playAll(trackNum);
                        //txt.setText(prayerArray[0]);
                        break;

                }
            }
        });



//        mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {
//
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                //performOnEnd();
//                // mp.release();
//                Log.i("Audio Bahaullah", "onCompletion: " + trackNum);
//                if (track.equals("all") && trackNum < 1) {
//                    trackNum++;
//                    playAll(trackNum);
//                }
//                else if (track.equals("all") && trackNum == 1) {
//                    trackNum = 0;
//                    playAllOn = 1;
//                    playBtn.setVisibility(View.VISIBLE);
//                    pauseBtn.setVisibility(View.GONE);
//                }
//                else {
//                    playBtn.setVisibility(View.VISIBLE);
//                    pauseBtn.setVisibility(View.GONE);
//                }
//
//            }
//
//        });



        return view;
    }

    private void playAll(int num) {
        switch (num) {
            case 0:
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
//                mp.setOnCompletionListener(listener);
//                gradientDrawable = new GradientDrawable(
//                        GradientDrawable.Orientation.TOP_BOTTOM,
//                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
//                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
//                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
//                                ContextCompat.getColor(getContext(), R.color.colorAccent)});
//
//                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
//                img.setImageResource(R.mipmap.attract_photo_foreground);
//                txt.setText(prayerArray[0]);
                playTrack(prayer1);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
               // mp.start();
                break;
            case 1:
                // mp.release();
                // mp.stop();
                // mp.reset();
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.brooklyn_bridge);
//                mp.setOnCompletionListener(listener);
//                txt.setText(prayerArray[1]);
//                img.setImageResource(R.mipmap.lauded_photo_foreground);
                playTrack(prayer2);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 2:
                playTrack(prayer3);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 3:
                playTrack(prayer4);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 4:
                playTrack(prayer5);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 5:
                playTrack(prayer6);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 6:
                playTrack(prayer7);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 7:
                playTrack(prayer8);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 8:
                playTrack(prayer9);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 9:
                playTrack(prayer10);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 10:
                playTrack(prayer11);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 11:
                playTrack(prayer12);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 12:
                playTrack(prayer13);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 13:
                playTrack(prayer14);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
        }
    }

    private void playTrack(String track) {
        switch(track) {
            case prayer1:
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.o_thou_whose_face2);
//                mp.setOnCompletionListener(listener);
                intent.putExtra("track", R.raw.bahai_9);
                Log.i(tag, "playTrack: made it here");
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_10);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
//                mp.setOnCompletionListener(listener);
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
                intent.putExtra("track", R.raw.bahai_11);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_12);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_13);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_14);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_15);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_16);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_17);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_18);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_19);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_20);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.bahai_21);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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
                intent.putExtra("track", R.raw.marian);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
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

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundSoundService.MyBinder binder = (BackgroundSoundService.MyBinder) service;
            bgSound = binder.getService();
            bgSound.setListener(AudioPlayerTheBab.this);
            Log.i(tag, "onServiceConnected: ");
            //serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //serviceBound = false;
        }
    };


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
        //mp.stop();
        if (bgSound != null)
            bgSound.stop();
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
        if (bgSound.isPlaying()) {
            outState.putFloat("position", bgSound.getCurrentPosition());
            bgSound.pause();
        }
        Log.i("Audio The Bab", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

    public void trackEnded() {
        if (track.equals("all") && trackNum < numTracks) {
            trackNum++;
            playAll(trackNum);
        }
        else if (track.equals("all") && trackNum == numTracks) {
            trackNum = 0;
            playAllOn = 1;
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            playTrack(prayerArray[0]);
        }
        else {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
        }
//        playBtn.setVisibility(View.VISIBLE);
//        pauseBtn.setVisibility(View.GONE);
    }

}
