package com.hfad.flower;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayerAbdulBaha extends Fragment {

    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private SeekBar seekBar;
    private float pos = 0;
    private int playAllOn = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private BackgroundSoundService bgSound;
    private Intent intent;
    private int trackNum = 0;
    private int numTracks = 1; //change later
    private String tr = "TRACK";
    private String tag = "Audio Abdul Baha";
    private GradientDrawable gradientDrawable;
    final String prayer1 = "Make firm our steps...";
    final String prayer2 = "O compassionate God!";
    final String prayer3 = "O Thou beloved of my heart...";
    final String prayer4 = "O Thou compassionate Lord...";
    final String prayer5 = "O my Lord! Thou knowest...";
    final String prayer6 = "Lord! Pitiful are we...";
    final String prayer7 = "O Thou kind Lord!";
    final String prayer8 = "O my merciful Lord!";
    final String prayer9 = "He is the Most Holy...";
    final String prayer10 = "O God of Mercy!";
    final String prayer11 = "O Lord! Grant me a measure...";
    final String prayer12 = "O God! Refresh and gladden...";
    final String prayer13 = "O Divine Providence!";
    String[] prayerArray = {"Make firm our steps...", "O compassionate God!", "O Thou beloved of my heart...",
            "O Thou compassionate Lord...", "O my Lord! Thou knowest...", "Lord! Pitiful are we...",
            "O Thou kind Lord!", "O my merciful Lord!", "He is the Most Holy...", "O God of Mercy!",
            "O Lord! Grant me a measure...", "O God! Refresh and gladden...", "O Divine Providence!"};


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
            track = savedInstanceState.getString(tr, track);
            Log.i("Audio Abdul Baha", "onCreateView: " + track);
        }


        intent = new Intent(getActivity(),BackgroundSoundService.class);
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        seekBar = view.findViewById(R.id.seekBar1);
        playTrack(track);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    bgSound.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Audio Bahaullah", "onClick: in playBtn");


                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
               // requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                //requireActivity().startService(intent);
//                if (playAllOn == 1) {
//                    playAllOn = 0;
//                    playAll(trackNum);
//                }
//                else {
//                    mp.start();
//                }
                if (playAllOn == 1) {
                    playAllOn = 0;
                    playAll(trackNum);
                }
                else {
                    requireActivity().startService(intent);
                    //mp.start();
                }

                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);


            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);
                mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);

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
                switch (track) {
                    case prayer1:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer2:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer3:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer4;
                        playTrack(track);
                        break;
                    case prayer4:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
                        playTrack(track);
                        break;
                    case prayer5:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer6;
                        playTrack(track);
                        break;
                    case prayer6:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer7;
                        playTrack(track);
                        break;
                    case prayer7:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer8;
                        playTrack(track);
                        break;
                    case prayer8:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer9;
                        playTrack(track);
                        break;
                    case prayer9:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer10;
                        playTrack(track);
                        break;
                    case prayer10:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer11;
                        playTrack(track);
                        break;
                    case prayer11:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer12;
                        playTrack(track);
                        break;
                    case prayer12:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case prayer13:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case "all":
                        //playAllOn = 1;
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
                switch (track) {
                    case prayer1:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer13;
                        playTrack(track);
                        break;
                    case prayer2:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer1;
                        playTrack(track);
                        break;
                    case prayer3:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer2;
                        playTrack(track);
                        break;
                    case prayer4:
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer3;
                        playTrack(track);
                        break;
                    case prayer5:
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
                    case "all":
                        //playAllOn = 1;
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == 0)
                            trackNum = numTracks;
                        else
                            trackNum--;
                        playAll(trackNum);
                        //txt.setText(prayerArray[0]);
                        break;
                }
            }
        });

//        forwardBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pauseBtn.setVisibility(View.VISIBLE);
//                playBtn.setVisibility(View.INVISIBLE);
//
//                mp.start();
//            }
//        });

//        mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {
//
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                //performOnEnd();
//                // mp.release();
//                Log.i("Audio Bahaullah", "onCompletion: " + trackNum);
//                if (track.equals("all") && trackNum < numTracks) {
//                    trackNum++;
//                    playAll(trackNum);
//                }
//                else if (track.equals("all") && trackNum == numTracks) {
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

    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            seekBar.setMax(bgSound.getDuration());
            Log.i(tag, "onClick: bgSound.getDuration " + bgSound.getDuration());
            //seekBar.setProgress((int)(bgSound.getCurrentPosition()/1000));
            seekBar.setProgress((int)bgSound.getCurrentPosition());
            Log.i(tag, "run: bgSound.getCurrentPos " + bgSound.getCurrentPosition());
            mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
        }
    };


    private void playAll(int num) {
        switch (num) {
            case 0:
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
//                mp.setOnCompletionListener(listener);
//                gradientDrawable = new GradientDrawable(
//                        GradientDrawable.Orientation.TOP_BOTTOM,
//                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
//                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
//                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
//                                ContextCompat.getColor(getContext(), R.color.colorAccent)});
//
//                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
//                img.setImageResource(R.mipmap.make_firm_our_steps_foreground);
//                txt.setText(prayerArray[0]);
                playTrack(prayerArray[0]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 1:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[1]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 2:
                playTrack(prayerArray[2]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 3:
                playTrack(prayerArray[3]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 4:
                playTrack(prayerArray[4]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 5:
                playTrack(prayerArray[5]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 6:
                playTrack(prayerArray[6]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 7:
                playTrack(prayerArray[7]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 8:
                playTrack(prayerArray[8]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 9:
                playTrack(prayerArray[9]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 10:
                playTrack(prayerArray[10]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 11:
                playTrack(prayerArray[11]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
            case 12:
                playTrack(prayerArray[12]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                break;
        }
    }

    private void playTrack(String track) {

        switch(track) {
            case prayer1:
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.o_thou_whose_face2);
//                mp.setOnCompletionListener(listener);
               // intent = new Intent(getActivity(), BackgroundSoundService.class);
                intent.putExtra("track", R.raw.bahai_9);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.make_firm_our_steps_foreground);
                txt.setText(prayerArray[0]);
                break;
            case prayer2:
                intent.putExtra("track", R.raw.bahai_10);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_compassionate_god_foreground);
                txt.setText(prayerArray[1]);
                break;
            case prayer3:
                intent.putExtra("track", R.raw.bahai_11);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_thou_beloved_foreground);
                txt.setText(prayerArray[2]);
                break;
            case prayer4:
                intent.putExtra("track", R.raw.bahai_12);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_thou_compassionate_lord_foreground);
                txt.setText(prayerArray[3]);
                break;
            case prayer5:
                intent.putExtra("track", R.raw.bahai_13);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_my_lord_thou_knowest_foreground);
                txt.setText(prayerArray[4]);
                break;
            case prayer6:
                intent.putExtra("track", R.raw.bahai_14);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lord_pitiful_are_we_foreground);
                txt.setText(prayerArray[5]);
                break;
            case prayer7:
                intent.putExtra("track", R.raw.bahai_15);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_thou_kind_lord_foreground);
                txt.setText(prayerArray[6]);
                break;
            case prayer8:
                intent.putExtra("track", R.raw.bahai_16);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedDarkPurple),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedGreen)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_my_merciful_lord_foreground);
                txt.setText(prayerArray[7]);
                break;
            case prayer9:
                intent.putExtra("track", R.raw.bahai_17);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.he_is_the_most_holy_foreground);
                txt.setText(prayerArray[8]);
                break;
            case prayer10:
                intent.putExtra("track", R.raw.bahai_18);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_god_of_mercy_foreground);
                txt.setText(prayerArray[9]);
                break;
            case prayer11:
                intent.putExtra("track", R.raw.bahai_19);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.fadedOrange),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_lord_grant_me_foreground);
                txt.setText(prayerArray[10]);
                break;
            case prayer12:
                intent.putExtra("track", R.raw.bahai_20);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_god_refresh_and_foreground);
                txt.setText(prayerArray[11]);
                break;
            case prayer13:
                intent.putExtra("track", R.raw.bahai_21);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.o_divine_providence_foreground);
                txt.setText(prayerArray[12]);
                break;
            case "all":
                //playAllOn = 1;
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
                break;
//            case "The Evolution of Matter and Development of the Soul":
//               // mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
//                gradientDrawable = new GradientDrawable(
//                        GradientDrawable.Orientation.TOP_BOTTOM,
//                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
//                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
//                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
//                                ContextCompat.getColor(getContext(), R.color.colorAccent)});
//
//                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
//                break;


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

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundSoundService.MyBinder binder = (BackgroundSoundService.MyBinder) service;
            bgSound = binder.getService();
            bgSound.setListener(AudioPlayerAbdulBaha.this);
            Log.i("Main Activity", "onServiceConnected: ");
            //serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //serviceBound = false;
        }
    };


    @Override
    public void onDestroy() {
//        mp.stop();
        bgSound.stop();
        Log.i("Audio Abdul Baha", "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i("Audio Abdul Baha", "onResume: ");
        super.onResume();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        //outState.putInt(STATE_COUNTER, mCounter);
        Log.i("Audio Abdul Baha", "onSaveInstanceState: " + track);
        if (bgSound.isPlaying()) {
            outState.putFloat("position", bgSound.getCurrentPosition());
            bgSound.pause();
        }
        outState.putString(tr, track);
    }

    public void trackEndedAbdulBaha() {
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

