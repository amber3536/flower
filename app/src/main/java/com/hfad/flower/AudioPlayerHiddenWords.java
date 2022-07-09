package com.hfad.flower;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

import java.util.Arrays;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AudioPlayerHiddenWords extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private ImageView img;
    private TextView txt;
    private int playAllOn = 0;
    private int playAll = 0;
    private TextView currTime;
    private TextView endTime;
    private int shuffleOn = 0;
    private int repeatOn = 0;
    private Queue<Integer> pq = new PriorityQueue<>();

    private FloatingActionButton shuffleBtn;
    private Random rand = new Random();
    private FloatingActionButton repeatBtn;
    private int count = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private SeekBar seekBar;
    private String tr = "TRACK";
    private String tag = "Audio Hidden Words";
    private Bundle bundle;
    private int playAllCtrl = 0;
    private int isPlaying = 0;
    private Intent intent;
    private BackgroundSoundService bgSound;
    private float pos = 0;
    private int numTracks = 3; //change later
    private GradientDrawable gradientDrawable;
    final String prayer1 = "From the Arabic: 1-20";
    final String prayer2 = "From the Arabic: 21-40";
    final String prayer3 = "From the Arabic: 41-59";
    final String prayer4 = "From the Arabic: 60-71";

    String[] prayerArray = {"From the Arabic: 1-20", "From the Arabic: 21-40",
            "From the Arabic: 41-59", "From the Arabic: 60-71"};


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
        currTime = view.findViewById(R.id.currTime);
        endTime = view.findViewById(R.id.endTime);
        repeatBtn = view.findViewById(R.id.fab_repeat);
        shuffleBtn = view.findViewById(R.id.fab_shuffle);

        bundle = this.getArguments();



        if (bundle != null) {

            track = bundle.getString("EXTRA", "");
            //Log.i("here", "onCreateView: " + position);
        }

        if (savedInstanceState != null) {
            pos = savedInstanceState.getFloat("position");
            track = savedInstanceState.getString(tr, track);
            isPlaying = savedInstanceState.getInt("playing");

            if (track.equals("all")) {
                trackNum = savedInstanceState.getInt("all");
            }
            else if (track.equals("shuffle")) {
                shuffleOn = 1;
                shuffleBtn.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                trackNum = savedInstanceState.getInt("all");
            }
            else if (track.equals("repeat")) {
                repeatOn = 1;
                repeatBtn.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                trackNum = savedInstanceState.getInt("all");
            }

            Log.i(tag, "onCreateView: " + track);
        }

        intent = new Intent(getActivity(),BackgroundSoundService.class);
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        if (track == "all")
            trackNum = 0;
        else
            trackNum = Arrays.asList(prayerArray).indexOf(track);
        playTrack(track);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    bgSound.seekTo(progress);
                    intent.putExtra("pos", (float)progress);
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                }
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
                Log.i(tag, "onClick: in playBtn");


                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                playAllCtrl = 0;

                if (playAllOn == 1) {
                    playAllOn = 0;
                    playAll(trackNum);
                }
                else {
                    requireActivity().startService(intent);
                }
//                  playTrack(track);

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

        shuffleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shuffleOn == 0) {
                    shuffleOn = 1;
                    shuffleBtn.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    track = "shuffle";
                    //trackNum = rand.nextInt(numTracks+1);
                    //pq.add(trackNum);
                }
                else {
                    track = "all";
                    shuffleOn = 0;
                    shuffleBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cdcdc5")));
                }

                //shuffleBtn.getBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.fadedBlue)));
                //shuffleBtn.getBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.fadedGray)));
            }
        });

        repeatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeatOn == 0) {
                    repeatOn = 1;
                    repeatBtn.setBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
                    track = "repeat";
                    //trackNum = rand.nextInt(numTracks+1);
                    //pq.add(trackNum);
                }
                else {
                    track = "all";
                    repeatOn = 0;
                    repeatBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cdcdc5")));
                }

                //shuffleBtn.getBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.fadedBlue)));
                //shuffleBtn.getBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.fadedGray)));
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
                        forwardTrack(prayer1);
                        break;

                    case "all":
                    case "repeat":
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
                    case "shuffle":
                        if (bgSound.isPlaying())
                            playAllCtrl = 0;
                        else
                            playAllCtrl = 1;
                        bgSound.pause();
                        playBtn.setVisibility(View.VISIBLE);
                        pauseBtn.setVisibility(View.GONE);
//                        if (trackNum == numTracks)
//                            trackNum = 0;
//                        else
//                            trackNum++;
                        if (count < numTracks) {
                            pq.add(trackNum);
                            count++;
                            while (pq.contains(trackNum)) {
                                trackNum = rand.nextInt(numTracks+1);
                            }

                            pos = 0;
                            bgSound.seekTo(0);
                            seekBar.setProgress(0);
                            intent.putExtra("pos", bgSound.getCurrentPosition());
                            playAll(trackNum);
                        }
                        else {
                            //playBtn.setVisibility(View.VISIBLE);
                            //pauseBtn.setVisibility(View.GONE);
                            pq.clear();
                            count = 0;
                            //playAllOn = 1;
                            trackNum = rand.nextInt(numTracks+1);
                            pos = 0;
                            bgSound.seekTo(0);
                            seekBar.setProgress(0);
                            intent.putExtra("pos", bgSound.getCurrentPosition());
                            playAll(trackNum);

                        }

                        break;
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (track) {
                    case prayer1:
                        backTrack(prayer4);
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
                    case "all":
                    case "repeat":
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
                                playAllCtrl = 1;
                                playAll(numTracks);
                                //playTrack(track);
                                //midSong = 1;
                                //playAll(trackNum);
                                //bgSound.pause();
                            }
                            else {
                                bgSound.stop();
                                playAllCtrl = 0;
                                //track = prayer8;
                                //playTrack(track);
                                //bgSound.start();
                                playAll(trackNum);
                            }
                        }
                        else {
                            if (bgSound.isPlaying()) {
                                playAllCtrl = 0;
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
                                playAllCtrl = 1;
                                pos = 0;
                            }
                        }
                        //txt.setText(prayerArray[0]);
                        break;
                    case "shuffle":
                        if (bgSound.getCurrentPosition() < 2000) {
//                            if (trackNum == 0)
//                                trackNum = numTracks;
//                            else {
//                                trackNum--;
//                            }
                            if (!pq.isEmpty()) {
                                trackNum = pq.poll();
                            }

                            if (!bgSound.isPlaying()) {
                                bgSound.pause();
                                //track = prayer8;
                                bgSound.seekTo(0);
                                seekBar.setProgress(0);
                                pos = 0;
                                intent.putExtra("pos", bgSound.getCurrentPosition());
                                playAllCtrl = 1;
                                playAll(trackNum);
                                //playTrack(track);
                                //midSong = 1;
                                //playAll(trackNum);
                                //bgSound.pause();
                            }
                            else {
                                bgSound.stop();
                                playAllCtrl = 0;
                                //track = prayer8;
                                //playTrack(track);
                                //bgSound.start();
                                playAll(trackNum);
                            }
                        }
                        else {
                            if (bgSound.isPlaying()) {
                                playAllCtrl = 0;
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
                                playAllCtrl = 1;
                                pos = 0;
                            }
                        }
                        break;
                }
            }
        });





        return view;
    }

    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            int totalTime = bgSound.getDuration();
            seekBar.setMax(totalTime);
            Log.i(tag, "onClick: bgSound.getDuration " + bgSound.getDuration());
            //seekBar.setProgress((int)(bgSound.getCurrentPosition()/1000));
            float timeElapsed = bgSound.getCurrentPosition();
            seekBar.setProgress((int)timeElapsed);
            currTime.setText(String.format(Locale.US,"%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed), TimeUnit.MILLISECONDS.toSeconds((long) timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed))));
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
        currTime.setText("00:00");
        intent.putExtra("pos", bgSound.getCurrentPosition());
        playTrack(curr);
    }

    private void backTrack(String curr) {
        currTime.setText("00:00");
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
        intent.putExtra("all", 1);
        switch (num) {
            case 0:
                playTrack(prayerArray[0]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 1:
                playTrack(prayerArray[1]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 2:
                playTrack(prayerArray[2]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 3:
                playTrack(prayerArray[3]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
//            case 4:
//                playTrack(prayerArray[4]);
//                pauseBtn.setVisibility(View.VISIBLE);
//                playBtn.setVisibility(View.INVISIBLE);
//                requireActivity().startService(intent);
//                break;
        }
    }

    private void playTrack(String track) {

        switch(track) {
            case prayer1:
                intent.putExtra("track", R.raw.bahai_22_silence);
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
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.hidden_words_1_foreground);
                txt.setText(prayerArray[0]);
                break;
            case prayer2:
                intent.putExtra("track", R.raw.bahai_23_silence);
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
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.fadedGray)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.hidden_words_2_foreground);
                txt.setText(prayerArray[1]);
                break;
            case prayer3:
                intent.putExtra("track", R.raw.bahai_24_silence);
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
                                ContextCompat.getColor(getContext(), R.color.fadedForestGreen),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.hidden_words_3_foreground);
                txt.setText(prayerArray[2]);
                break;
            case prayer4:
                intent.putExtra("track", R.raw.bahai_25_copy);
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
                img.setImageResource(R.mipmap.hidden_words_4_foreground);
                txt.setText(prayerArray[3]);
                break;

            case "all":
                playAll = 1;
                playAll(trackNum);
                //txt.setText(prayerArray[0]);
                break;
        }
        if (bgSound != null && playAll == 0) {
            Log.i(tag, "playTrack: truth");
            bgSound.prep(intent);
            setEndTime(bgSound.getDuration());
        }
        playAll = 0;
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundSoundService.MyBinder binder = (BackgroundSoundService.MyBinder) service;
            bgSound = binder.getService();
            bgSound.setListener(AudioPlayerHiddenWords.this);
            playTrack(track);
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
        mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
        bgSound.stop();
        Log.i(tag, "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i(tag, "onResume: ");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);

        if (bgSound.isPlaying()) {
            outState.putInt("playing", 1);
        }
        Log.i(tag, "onSaveInstanceState: bgSound playing");
        outState.putFloat("position", bgSound.getCurrentPosition());
        outState.putInt("all", trackNum);
        // Save our own state now
        //outState.putInt(STATE_COUNTER, mCounter);
        Log.i(tag, "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

    public void setEndTime(int totalTime) {
        Log.i("Audio Hidden Words", "setEndTime: " + totalTime);
        //endTime.setText(String.format("%02d", TimeUnit.MILLISECONDS.toSeconds( totalTime)) );
        endTime.setText(String.format(Locale.US, "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) totalTime), TimeUnit.MILLISECONDS.toSeconds((long) totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) totalTime))));
    }

    public void trackEndedHiddenWords() {
        playAllCtrl = 0;
        if (track.equals("all") && trackNum < numTracks) {
            trackNum++;
            currTime.setText("00:00");
            //mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            intent.putExtra("pos", bgSound.getCurrentPosition());
            playAll(trackNum);
        }
        else if (track.equals("all") && trackNum == numTracks) {
            trackNum = 0;
            playAllOn = 1;
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            playTrack(prayerArray[0]);
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            currTime.setText("00:00");
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
        }
        else if (track.equals("shuffle")) {
            currTime.setText("00:00");
            //mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            intent.putExtra("pos", bgSound.getCurrentPosition());
            if (count < numTracks) {
                count++;
                pq.add(trackNum);
                //intList.add(trackNum);

                //while (intList.contains(trackNum)) {
                while (pq.contains(trackNum)) {
                    Log.i(tag, "trackEndedBahaullah: " + trackNum);
                    trackNum = rand.nextInt(numTracks+1);
                }
                playAll(trackNum);

//            intArray[count] = trackNum;
//            while (intArray.contains[trackNum])
            }
            else {
                playBtn.setVisibility(View.VISIBLE);
                pauseBtn.setVisibility(View.GONE);
                pq.clear();
                count = 0;
                //playAllOn = 1;
                trackNum = 0;

            }
        }
        else if (track.equals("repeat")) {
            currTime.setText("00:00");
            //mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            //playBtn.setVisibility(View.VISIBLE);
            //pauseBtn.setVisibility(View.GONE);
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            intent.putExtra("pos", bgSound.getCurrentPosition());
            playAll(trackNum);
        }
        else {
            currTime.setText("00:00");
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
            playBtn.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            intent.putExtra("pos", bgSound.getCurrentPosition());
        }

    }


}
