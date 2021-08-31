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

public class AudioPlayerAbdulBaha extends Fragment {

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
    private int numTracks = 1; //change later
    private int trackCount = 0;
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

        playTrack(track);



        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Audio Bahaullah", "onClick: in playBtn");


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
                        track = prayer13;
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
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.attract_photo_foreground);
                txt.setText(prayerArray[0]);
                trackCount = 0;
                break;
            case prayer2:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lauded_photo_foreground);
                txt.setText(prayerArray[1]);
                trackCount = 1;
                break;
            case prayer3:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.marian);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue),
                                ContextCompat.getColor(getContext(), R.color.fadedGreen),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.glorified_art_thou_photo_foreground);
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
                img.setImageResource(R.mipmap.from_the_sweet_scented_photo_foreground);
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
            case prayer6:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedGreen)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.he_is_the_gracious_photo_foreground);
                txt.setText(prayerArray[5]);
                trackCount = 5;
                break;
            case prayer7:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.glory_to_thee_photo_foreground);
                txt.setText(prayerArray[6]);
                trackCount = 6;
                break;
            case prayer8:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[7]);
                trackCount = 7;
                break;
            case prayer9:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[8]);
                trackCount = 7;
                break;
            case prayer10:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[9]);
                trackCount = 7;
                break;
            case prayer11:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[10]);
                trackCount = 7;
                break;
            case prayer12:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[11]);
                trackCount = 7;
                break;
            case prayer13:
                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.from_the_sweet_scented);
                mp.setOnCompletionListener(listener);
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[12]);
                trackCount = 7;
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
        Log.i("Audio Bahaullah", "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i("Audio Bahaullah", "onResume: ");
        super.onResume();
    }
}
