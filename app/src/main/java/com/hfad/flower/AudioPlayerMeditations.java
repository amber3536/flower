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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioPlayerMeditations extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private int playAllOn = 0;
    private int count = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = -1;
    private SeekBar seekBar;
    private Intent intent;
    private int playAllCtrl = 0;
    private int isPlaying = 0;
    private BackgroundSoundService bgSound;
    private List<Integer> intList = new ArrayList<>();
    private Random rand = new Random();
    private String tr = "TRACK";
    private Bundle bundle;
    private float pos = 0;
    private String tag = "Audio Paris Talks";
    private int numTracks = 4; //change later
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

        track = "all";

//        if (bundle != null) {
//            track = bundle.getString("EXTRA", "");
//            //Log.i("here", "onCreateView: " + position);
//        }

        if (savedInstanceState != null) {

            pos = savedInstanceState.getFloat("position");
            track = savedInstanceState.getString(tr, track);
            isPlaying = savedInstanceState.getInt("playing");

//            if (track.equals("all")) {
            trackNum = savedInstanceState.getInt("all");
           // }
            Log.i("Audio Paris", "onCreateView: " + track);
        }

        intent = new Intent(getActivity(),BackgroundSoundService.class);
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
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
                Log.i("Audio Paris", "onClick: in playBtn");


                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                if (playAllOn == 1) {
                    playAllOn = 0;
                    playAll(trackNum);
                }
                else {
                    requireActivity().startService(intent);
                }

                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
//                  playTrack(track);

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
                        forwardTrack(prayer2);
                        break;
                    case prayer2:
                        forwardTrack(prayer3);
                        break;
                    case prayer3:
                        forwardTrack(prayer4);
                        break;
                    case prayer4:
                        forwardTrack(prayer5);
                        break;
                    case prayer5:
                        forwardTrack(prayer1);
                        break;
                    case "all":
                        //playAllOn = 1;
                        if (bgSound.isPlaying())
                            playAllCtrl = 0;
                        else
                            playAllCtrl = 1;
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
                        if (trackNum == numTracks)
                            trackNum = 0;
                        else
                            trackNum++;
                        pos = 0;
                        bgSound.seekTo(0);
                        seekBar.setProgress(0);
                        intent.putExtra("pos", bgSound.getCurrentPosition());
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
                        backTrack(prayer5);
//                        bgSound.pause();
//                        playBtn.setVisibility(View.VISIBLE);
//                        pauseBtn.setVisibility(View.GONE);


                        Log.i(tag, "onClick: pos " + pos);
                        break;
                    case prayer2:
                        backTrack(prayer1);
                        break;
                    case prayer3:
                        backTrack(prayer2);
                        break;
                    case prayer4:
                        backTrack(prayer3);
                        break;
                    case prayer5:
                        backTrack(prayer4);
                        break;
                    case "all":
                        //playAllOn = 1;
                        if (bgSound.getCurrentPosition() < 2000) {
                            if (trackNum == 0)
                                trackNum = numTracks;
                            else
                                trackNum--;

                            if (!bgSound.isPlaying()) {
                                bgSound.pause();
                                //track = prayer8;
                                bgSound.seekTo(0);
                                seekBar.setProgress(0);
                                pos = 0;
                                intent.putExtra("pos", bgSound.getCurrentPosition());
                                //playTrack(track);
                                //midSong = 1;
                                //playAll(trackNum);
                                //bgSound.pause();
                            }
                            else {
                                bgSound.stop();
                                //track = prayer8;
                                //playTrack(track);
                                //bgSound.start();
                                playAll(trackNum);
                            }
                        }
                        else {
                            if (bgSound.isPlaying()) {
                                playAllCtrl = 1;
                                bgSound.pause();
                                bgSound.seekTo(0);
                                seekBar.setProgress(0);
                                bgSound.start();
                            } else {
//                                bgSound.pause();
//                                playBtn.setVisibility(View.VISIBLE);
//                                pauseBtn.setVisibility(View.GONE);
//                                mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
                                seekBar.setProgress(0);
                                bgSound.seekTo(0);
                                intent.putExtra("pos", bgSound.getCurrentPosition());
                                playAllCtrl = 0;
                                pos = 0;
                            }
                        }
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

    private void forwardTrack(String curr) {
        bgSound.pause();
        playBtn.setVisibility(View.VISIBLE);
        pauseBtn.setVisibility(View.GONE);
        pos = 0;
        track = curr;
        bgSound.seekTo(0);
        seekBar.setProgress(0);
        intent.putExtra("pos", bgSound.getCurrentPosition());
        playTrack(curr);
    }

    private void backTrack(String curr) {
        if (bgSound.getCurrentPosition() < 2000) {
            if (!bgSound.isPlaying()) {
                bgSound.pause();
                track = curr;
                bgSound.seekTo(0);
                seekBar.setProgress(0);
                pos = 0;
                playTrack(track);
            }
            else {
                bgSound.seekTo(0);
                seekBar.setProgress(0);
                pos = 0;
                bgSound.pause();
                track = curr;
                playTrack(track);
                requireActivity().startService(intent);
            }

        }
        else {
            if (bgSound.isPlaying()) {
                bgSound.pause();
                bgSound.seekTo(0);
                seekBar.setProgress(0);
                pos = 0;
                bgSound.start();
            }
            else {
                Log.i(tag, "onClick: paused in prayer1");
//                                bgSound.pause();
//                                playBtn.setVisibility(View.VISIBLE);
//                                pauseBtn.setVisibility(View.GONE);
//                                mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
                seekBar.setProgress(0);
                bgSound.seekTo(0);
                pos = 0;
                intent.putExtra("pos", bgSound.getCurrentPosition());
            }
            //playTrack(track);
        }
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
                playTrack(prayerArray[0]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 1:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[1]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 2:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[2]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 3:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[3]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 4:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[4]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
        }
    }

    private void playTrack(String track) {

        switch(track) {
            case prayer1:
                intent.putExtra("track", R.raw.bahai_26);
                intent.putExtra("pos", pos);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    // seekBar.setProgress((int)pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                        isPlaying = 0;
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }

                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedNavy),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.the_pitiful_causes_foreground);
                txt.setText(prayerArray[0]);
                break;
            case prayer2:
                intent.putExtra("track", R.raw.bahai_27_copy);
                intent.putExtra("pos", pos);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    // seekBar.setProgress((int)pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                        isPlaying = 0;
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }

                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedOlive),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.an_indian_said_foreground);
                txt.setText(prayerArray[1]);
                break;
            case prayer3:
                intent.putExtra("track", R.raw.bahai_28);
                intent.putExtra("pos", pos);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    // seekBar.setProgress((int)pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                        isPlaying = 0;
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedOlive)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.beauty_and_harmony_foreground);
                txt.setText(prayerArray[2]);
                break;
            case prayer4:
                intent.putExtra("track", R.raw.bahai_29);
                intent.putExtra("pos", pos);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    // seekBar.setProgress((int)pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                        isPlaying = 0;
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedBlue),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lecture_given_at_foreground);
                txt.setText(prayerArray[3]);
                break;
            case prayer5:
                intent.putExtra("track", R.raw.bahai_30);
                intent.putExtra("pos", pos);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    // seekBar.setProgress((int)pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                        isPlaying = 0;
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedBlue)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.pain_and_sorrow_foreground);
                txt.setText(prayerArray[4]);
                break;

            case "all":
                //playAllOn = 1;
                if (trackNum == -1)
                    trackNum = rand.nextInt(numTracks+1);
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
                break;
//            case "The Evolution of Matter and Development of the Soul":
//                mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.paris_talks20);
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
            bgSound.setListener(AudioPlayerMeditations.this);
            Log.i(tag, "onServiceConnected: ");
            //serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //serviceBound = false;
        }
    };

    @Override
    public void onDestroy() {
        if (bgSound != null)
            bgSound.stop();
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
        if (bgSound.isPlaying()) {
            outState.putInt("playing", 1);
        }
        Log.i(tag, "onSaveInstanceState: bgSound playing");
        outState.putFloat("position", bgSound.getCurrentPosition());
        outState.putInt("all", trackNum);

        Log.i("Audio Paris", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

    public void trackEndedMeditations() {
        playAllCtrl = 0;

        if (count < numTracks) {
            count++;
            intList.add(trackNum);

            while (intList.contains(trackNum)) {
                trackNum = rand.nextInt(numTracks + 1);
            }
            playAll(trackNum);

//            intArray[count] = trackNum;
//            while (intArray.contains[trackNum])
        } else {
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            intList.clear();
            count = 0;
            playAllOn = 1;
            trackNum = -1;

        }
    }

}
