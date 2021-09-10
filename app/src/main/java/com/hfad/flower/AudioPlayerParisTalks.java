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

public class AudioPlayerParisTalks extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private int playAllOn = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private String tr = "TRACK";
    private Bundle bundle;
    private int numTracks = 1; //change later
    private int trackCount = 0;
    private GradientDrawable gradientDrawable;
    final String prayer1 = "The Pitiful Causes of War, and the Duty of Everyone to Strive for Peace";
    final String prayer2 = "The Universal Love";
    final String prayer3 = "Beauty and Harmony in Diversity";
    final String prayer4 = "Lecture Given at a Studio in Paris";
    final String prayer5 = "Pain and Sorrow";

    String[] prayerArray = {"The Pitiful Causes of War, and the Duty of Everyone to Strive for Peace",
            "The Universal Love", "Beauty and Harmony in Diversity", "Lecture Given at a Studio in Paris",
            "Pain and Sorrow"};


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

        bundle = this.getArguments();



        if (bundle != null) {
            track = bundle.getString("EXTRA", "");
            //Log.i("here", "onCreateView: " + position);
        }

        if (savedInstanceState != null) {

            track = savedInstanceState.getString(tr, track);
            Log.i("Audio Paris", "onCreateView: " + track);
        }

        playTrack(track);



        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Audio Paris", "onClick: in playBtn");


                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                if (playAllOn == 1) {
                    playAllOn = 0;
                    playAll(trackNum);
                }
                else {
                    mp.start();
                }
//                  playTrack(track);

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
                switch (track) {
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
                        track = prayer1;
                        playTrack(track);
                        break;
                    case "all":
                        //playAllOn = 1;
                        mp.pause();
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
                        mp.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        track = prayer5;
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
                    case "all":
                        //playAllOn = 1;
                        mp.pause();
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

        mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                //performOnEnd();
                // mp.release();
                Log.i("Audio Bahaullah", "onCompletion: " + trackNum);
                if (track.equals("all") && trackNum < numTracks) {
                    trackNum++;
                    playAll(trackNum);
                }
                else if (track.equals("all") && trackNum == numTracks) {
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
                //trackNum = 0; //don't forget to reset this on last track
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
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.the_pitiful_causes_foreground);
                txt.setText(prayerArray[0]);
                trackCount = 0;
                break;
            case prayer2:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedOlive),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.an_indian_said_foreground);
                txt.setText(prayerArray[1]);
                trackCount = 1;
                break;
            case prayer3:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedOlive)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.beauty_and_harmony_foreground);
                txt.setText(prayerArray[2]);
                trackCount = 2;
                break;
            case prayer4:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedBlue),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lecture_given_at_foreground);
                txt.setText(prayerArray[3]);
                trackCount = 3;
                break;
            case prayer5:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedBlue),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.lightBlue),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.create_in_me_a_pure_photo_foreground);
                txt.setText(prayerArray[4]);
                trackCount = 4;
                break;

            case "all":
                //playAllOn = 1;
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
                break;
            case "The Evolution of Matter and Development of the Soul":
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
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
        Log.i("Audio Paris", "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i("Audio Paris", "onResume: ");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        // Save our own state now
        //outState.putInt(STATE_COUNTER, mCounter);
        Log.i("Audio Paris", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

}
