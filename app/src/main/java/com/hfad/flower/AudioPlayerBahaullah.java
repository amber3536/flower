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

import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class AudioPlayerBahaullah extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private SeekBar seekBar;
    private ImageView img;
    private TextView txt;
    private TextView currTime;
    private TextView endTime;
    private Intent intent;
    private int playAll = 0;
    private int playAllOn = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private int playAllCtrl = 0;
    private String tr = "TRACK";
    private BackgroundSoundService bgSound;
    private Bundle bundle;
    private float pos = 0;
    private String tag = "Audio Bahaullah";
    private int numTracks = 7;
    private int isPlaying = 0;
    private GradientDrawable gradientDrawable;
    private final String prayer1 = "Attract the hearts of men...";
    private final String prayer2 = "Lauded be Thy name...";
    final String prayer3 = "Glorified art Thou, O Lord my God...";
    final String prayer4 = "From the sweet-scented streams...";
    final String prayer5 = "Create in me a pure heart...";
    final String prayer6 = "He is the Gracious, the All-Bountiful...";
    final String prayer7 = "Glory to Thee, O my God!";
    final String prayer8 = "Magnified, O Lord my God, be Thy name...";
    String[] prayerArray = {"Attract the hearts of men...", "Lauded be Thy name...", "Glorified art Thou, O Lord my God...",
            "From the sweet-scented streams...", "Create in me a pure heart...", "He is the Gracious, the All-Bountiful...",
            "Glory to Thee, O my God!", "Magnified, O Lord my God, be Thy name..."};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_audio, container, false);

        playBtn = view.findViewById(R.id.fab_play);
        pauseBtn = view.findViewById(R.id.fab_pause);
        forwardBtn = view.findViewById(R.id.fab_forward);
        backBtn = view.findViewById(R.id.fab_back);
        img = view.findViewById(R.id.audio_img);
        txt = view.findViewById(R.id.audio_txt);
        currTime = view.findViewById(R.id.currTime);
        endTime = view.findViewById(R.id.endTime);
        //seekBar = view.findViewById(seekBar1);

        bundle = this.getArguments();
        //setRetainInstance(true);



        if (bundle != null) {
            track = bundle.getString("EXTRA", "");
            //Log.i("here", "onCreateView: " + position);
        }

        if (savedInstanceState != null) {
//            continuePlay = 1;
            pos = savedInstanceState.getFloat("position");
            Log.i(tag, "onCreateView: " + pos);
//            mp.seekTo(pos);

            track = savedInstanceState.getString(tr, track);
            isPlaying = savedInstanceState.getInt("playing");

            if (track.equals("all")) {
                trackNum = savedInstanceState.getInt("all");
            }

            //bgSound.seekTo((int)pos);
            Log.i("Audio Bahaullah", "onCreateView: " + track);
//            seekBar.setProgress((int)bgSound.getCurrentPosition());
//            Log.i(tag, "run: bgSound.getCurrentPos " + bgSound.getCurrentPosition());
            //mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
        }

        intent = new Intent(getActivity(),BackgroundSoundService.class);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
        Log.i(tag, "onCreateView: before bind");
        playTrack(track);
        requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i(tag, "onCreateView: after bind");

        //seekBar.setProgress();




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    bgSound.seekTo(progress);
                    intent.putExtra("pos", (float)progress);
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
//                    bgSound.seekTo(progress);
//                    float timeElapsed = bgSound.getCurrentPosition();
//                    Log.i(tag, "onProgressChanged: " + timeElapsed);
//                    intent.putExtra("pos", timeElapsed);
//                    currTime.setText(String.format(Locale.US, "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed), TimeUnit.MILLISECONDS.toSeconds((long) timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed))));
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
                  Log.i("Audio Bahaullah", "onClick: in playBtn");


                  pauseBtn.setVisibility(View.VISIBLE);
                  playBtn.setVisibility(View.INVISIBLE);
                  playAllCtrl = 0;
                 // requireActivity().startService(intent);

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
                    //midSong = 0;
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
                       forwardTrack(prayer6);
                       break;
                   case prayer6:
                       forwardTrack(prayer7);
                       break;
                   case prayer7:
                       forwardTrack(prayer8);
                       break;
                   case prayer8:
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
//                       bgSound.pause();
//                       playBtn.setVisibility(View.VISIBLE);
//                       pauseBtn.setVisibility(View.GONE);
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
                        backTrack(prayer8);
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
                    case prayer6:
                        backTrack(prayer5);
                        break;
                    case prayer7:
                        backTrack(prayer6);
                        break;
                    case prayer8:
                        backTrack(prayer7);
                        break;
                    case "all":
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
            //Log.i(tag, "onClick: bgSound.getDuration " + bgSound.getDuration());
            //seekBar.setProgress((int)(bgSound.getCurrentPosition()/1000));
            float timeElapsed = bgSound.getCurrentPosition();
            seekBar.setProgress((int)timeElapsed);
            currTime.setText(String.format(Locale.US,"%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed), TimeUnit.MILLISECONDS.toSeconds((long) timeElapsed) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeElapsed))));

            //currTime.setText((int) bgSound.getCurrentPosition());
            //Log.i(tag, "run: bgSound.getCurrentPos " + bgSound.getCurrentPosition());
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
        //bgSound.prep(intent);
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
            case 4:
                playTrack(prayerArray[4]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 5:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[5]);

                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 6:
                playTrack(prayerArray[6]);

                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
            case 7:
                playTrack(prayerArray[7]);
                if (playAllCtrl == 0) {
                    pauseBtn.setVisibility(View.VISIBLE);
                    playBtn.setVisibility(View.INVISIBLE);
                    requireActivity().startService(intent);
                }
                mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                break;
        }
        //intent.putExtra("all", 0);
    }

    private void playTrack(String track) {
        Log.i(tag, "playTrack: " + track);
        switch(track) {
            case prayer1:
                intent.putExtra("track", R.raw.bahai_1);
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
                img.setImageResource(R.mipmap.attract_photo_foreground);
                txt.setText(prayerArray[0]);
                break;
            case prayer2:
                intent.putExtra("track", R.raw.bahai_2);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);

                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.lauded_photo_foreground);
                txt.setText(prayerArray[1]);
                break;
            case prayer3:
                intent.putExtra("track", R.raw.bahai_3_copy);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
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
                img.setImageResource(R.mipmap.glorified_art_thou_photo_foreground);
                txt.setText(prayerArray[2]);
                break;
            case prayer4:
                intent.putExtra("track", R.raw.bahai_4);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
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
                img.setImageResource(R.mipmap.from_the_sweet_scented_photo_foreground);
                txt.setText(prayerArray[3]);
                break;
            case prayer5:
                intent.putExtra("track", R.raw.bahai_5_copy);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedGray),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.create_in_me_a_pure_photo_foreground);
                txt.setText(prayerArray[4]);
                break;
            case prayer6:
                intent.putExtra("track", R.raw.bahai_6);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.fadedGreen),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.fadedGreen)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.he_is_the_gracious_photo_foreground);
                txt.setText(prayerArray[5]);
                break;
            case prayer7:
                intent.putExtra("track", R.raw.bahai_7);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.glory_to_thee_photo_foreground);
                txt.setText(prayerArray[6]);
                break;
            case prayer8:
                intent.putExtra("track", R.raw.bahai_8_copy);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    if (isPlaying == 1) {
                        requireActivity().startService(intent);
                        pauseBtn.setVisibility(View.VISIBLE);
                        playBtn.setVisibility(View.INVISIBLE);
                    }
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 500);
                    playAllCtrl = 1;
                    pos = 0;
                }
                gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{ContextCompat.getColor(getContext(), R.color.colorAccent),
                                ContextCompat.getColor(getContext(), R.color.colorFadedRed),
                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
                                ContextCompat.getColor(getContext(), R.color.colorAccent)});

                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
                img.setImageResource(R.mipmap.magnified_oh_lord_foreground);
                txt.setText(prayerArray[7]);
                break;
            case "all":
                playAll = 1;
                playAll(trackNum);

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
            bgSound.setListener(AudioPlayerBahaullah.this);
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
       // mp.stop();

        bgSound.stop();

        Log.i("Audio Bahaullah", "onDestroy: ");

        super.onDestroy();
    }

    @Override
    public void onResume() {
        Log.i("Audio Bahaullah", "onResume: ");
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

        Log.i("Audio Bahaullah", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

    public void setEndTime(int totalTime) {
        Log.i("Audio Bahaullah", "setEndTime: " + totalTime);
        //endTime.setText(String.format("%02d", TimeUnit.MILLISECONDS.toSeconds( totalTime)) );
        endTime.setText(String.format(Locale.US, "%02d:%02d", TimeUnit.MILLISECONDS.toMinutes((long) totalTime), TimeUnit.MILLISECONDS.toSeconds((long) totalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) totalTime))));
    }

    public void trackEndedBahaullah() {
        playAllCtrl = 0;
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
            bgSound.seekTo(0);
            seekBar.setProgress(0);
            currTime.setText("00:00");
            mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
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
