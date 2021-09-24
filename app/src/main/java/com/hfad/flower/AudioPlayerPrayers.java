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

import java.util.Random;

public class AudioPlayerPrayers extends Fragment {
    private View view;
    public MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private String track;
    private SeekBar seekBar;
    private ImageView img;
    private TextView txt;
    private Intent intent;
    private int playAllOn = 0;
    private FloatingActionButton backBtn;
    private FloatingActionButton forwardBtn;
    private MediaPlayer.OnCompletionListener listener;
    private int trackNum = 0;
    private String tr = "TRACK";
    private BackgroundSoundService bgSound;
    private Bundle bundle;
    private float pos = 0;
    private String tag = "Audio Bahaullah";
    private int numTracks = 7;
    private Random rand = new Random();
    private int continuePlay = 0;
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

//    public AudioPlayerBahaullah(SeekBar seekBar) {
//    }


//    @Override
//    public void onCreate (Bundle savedInstanceState) {
//
//
//        Log.i(tag, "onCreate: " + "bla");
//        super.onCreate(savedInstanceState);
//
//    }

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
        //seekBar = view.findViewById(seekBar1);

        bundle = this.getArguments();
        //setRetainInstance(true);

        track = "all";

//        if (bundle != null) {
//            track = bundle.getString("EXTRA", "");
//            //Log.i("here", "onCreateView: " + position);
//        }

        if (savedInstanceState != null) {
//            continuePlay = 1;
            pos = savedInstanceState.getFloat("position");
            Log.i(tag, "onCreateView: " + pos);
//            mp.seekTo(pos);

            track = savedInstanceState.getString(tr, track);
            Log.i("Audio Bahaullah", "onCreateView: " + track);
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
                Log.i("Audio Bahaullah", "onClick: in playBtn");


                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
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
                //mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);
//                  if (playAllOn == 1) {
//                      playAllOn = 0;
//                      playAll(trackNum);
//                  }
//                  else {
//                      mp.start();
//                  }


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

                //requireActivity().stopService(intent);
                //.pause();
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
                        track = prayer8;
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

        // bgSound.mp.setOnCompletionListener();


//        forwardBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pauseBtn.setVisibility(View.VISIBLE);
//                playBtn.setVisibility(View.INVISIBLE);
//
//                mp.start();
//            }
//        });

//        bgSound.mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {
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
//                    playTrack(prayerArray[0]);
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
//                                ContextCompat.getColor(getContext(), R.color.colorFadedYellow),
//                                ContextCompat.getColor(getContext(), R.color.colorFadedPink),
//                                ContextCompat.getColor(getContext(), R.color.colorAccent)});
//
//                view.findViewById(R.id.layout_audio_player).setBackground(gradientDrawable);
//                img.setImageResource(R.mipmap.attract_photo_foreground);
//                txt.setText(prayerArray[0]);
                // if (mp == null && !mp.isPlaying()) {
                // if (continuePlay == 0) {
                playTrack(prayerArray[0]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                requireActivity().startService(intent);
                // }



                break;
            case 1:
                // mp.release();
                // mp.stop();
                // mp.reset();
                // if (continuePlay == 0) {
                playTrack(prayerArray[1]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                //   }

                break;
            case 2:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[2]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 3:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[3]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 4:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[4]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 5:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[5]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 6:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[6]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
            case 7:
                // mp.release();
                // mp.stop();
                // mp.reset();
                playTrack(prayerArray[7]);
                pauseBtn.setVisibility(View.VISIBLE);
                playBtn.setVisibility(View.INVISIBLE);
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                requireActivity().startService(intent);
                break;
//            case 8:
//                // mp.release();
//                // mp.stop();
//                // mp.reset();
//                playTrack(prayerArray[8]);
//                pauseBtn.setVisibility(View.VISIBLE);
//                playBtn.setVisibility(View.INVISIBLE);
//                //trackNum = 0; //don't forget to reset this on last track
//                //mp.setLooping(false);
//                requireActivity().startService(intent);
//                break;
        }
    }

    private void playTrack(String track) {

        switch(track) {
            case prayer1:
                //requireActivity().startService(new Intent(getActivity(),BackgroundSoundService.class));

                intent.putExtra("track", R.raw.bahai_1);
//                mp = MediaPlayer.create(getContext(), R.raw.from_the_sweet_scented);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
//
//                mp.setOnCompletionListener(listener);
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
                // mp = MediaPlayer.create(getContext(), R.raw.marian);
                intent.putExtra("track", R.raw.bahai_2);
                if (pos != 0) {
                    intent.putExtra("pos", pos);
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.brooklyn_bridge);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.marian);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.brooklyn_bridge);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.marian);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.marian);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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
                    requireActivity().startService(intent);
                    pos = 0;

                }
//                mp = MediaPlayer.create(getContext(), R.raw.brooklyn_bridge);
//                if (pos != 0) {
//                    mp.seekTo(pos);
//                    pos = 0;
//                    mp.start();
//                }
//                mp.setOnCompletionListener(listener);
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


                //playAllOn = 1;
                playAll(rand.nextInt(numTracks+1));
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

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BackgroundSoundService.MyBinder binder = (BackgroundSoundService.MyBinder) service;
            bgSound = binder.getService();
            bgSound.setListener(AudioPlayerPrayers.this);
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
        // mp.stop();
        if (bgSound != null)
            bgSound.stop();
        //bgSound.unbindService(serviceConnection);
        //bgSound.onDestroy();
        // bgSound = null;
        // bgSound.unbindService(serviceConnection);
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
            outState.putFloat("position", bgSound.getCurrentPosition());
            bgSound.pause();
        }

        Log.i("Audio Bahaullah", "onSaveInstanceState: " + track);
        outState.putString(tr, track);
    }

    public void trackEndedPrayers() {
        if (track.equals("all") && trackNum < numTracks) {
            //trackNum++;
            playAll(rand.nextInt(numTracks+1));
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

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//
////        Configuration config=getResources().getConfiguration();
////        if(config.orientation == Configuration.ORIENTATION_PORTRAIT)
////        {
////            view.setContentView(R.layout.fragment_audio);
////        }
////        else if(config.orientation == Configuration.ORIENTATION_LANDSCAPE)
////        {
////            setContentView(R.layout.fragment_audio);
////        }
//    }


//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//
//        super.onRestoreInstanceState(savedInstanceState);
//
////        position = savedInstanceState.getInt("Position");
////        mediaPlayer.seekTo(position);
////        if (savedInstanceState.getBoolean("isplaying"))
////            mediaPlayer.start();
////
////
////
//    }

}
