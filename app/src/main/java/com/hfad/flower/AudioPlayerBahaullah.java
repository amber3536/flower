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


public class AudioPlayerBahaullah extends Fragment {
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
    private int trackCount = 0;
    private GradientDrawable gradientDrawable;
    String[] prayerArray = {"Attract the hearts of men...", "Lauded be Thy name...", "Glorified art Thou, O Lord my God...",
            "From the sweet-scented streams...", "Create in me a pure heart...", "He is the Gracious, the All-Bountiful...",
            "Glory to Thee, O my God!", "Magnified, O Lord my God, be Thy name..."};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_audio, container, false);

        playBtn = view.findViewById(R.id.fab_play);
        pauseBtn = view.findViewById(R.id.fab_pause);
        forwardBtn = view.findViewById(R.id.fab_forward);
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
                   case "Attract the Hearts of Men...":
                       mp.pause();
                       playBtn.setVisibility(View.VISIBLE);
                       pauseBtn.setVisibility(View.GONE);
                        track = "Lauded Be Thy Name...";
                        playTrack(track);
                       break;
                   case "Lauded Be Thy Name...":
                       track = "Glorified Art Thou, O Lord My God...";
                       playTrack(track);
                       break;
                   case "Glorified Art Thou, O Lord My God...":
                       track = "From the Sweet-Scented Streams...";
                       playTrack(track);
                       break;
                   case "From the Sweet-Scented Streams...":
                       track = "Create in Me a Pure Heart...";
                       playTrack(track);
                       break;
                   case "Create in Me a Pure Heart...":
                       track = "He is the Gracious, the All_Bountiful...";
                       playTrack(track);
                       break;
                   case "He is the Gracious, the All_Bountiful...":
                       track = "Glory to Thee, O My God!";
                       playTrack(track);
                       break;
                   case "Glory to Thee, O My God!":
                       track = "Magnified, O Lord My God, Be Thy Name...";
                       playTrack(track);
                       break;
                   case "Magnified, O Lord My God, Be Thy Name...":
                       track = "Attract the Hearts of Men...";
                       playTrack(track);
                       break;
                   case "all":
                       //playAllOn = 1;
                       trackNum++;
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
                //trackNum = 0; //don't forget to reset this on last track
                //mp.setLooping(false);
                mp.start();
                break;
        }
    }

    private void playTrack(String track) {

        switch(track) {
            case "Attract the Hearts of Men...":
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
            case "Lauded Be Thy Name...":
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
            case "Glorified Art Thou, O Lord My God...":
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
            case "From the Sweet-Scented Streams...":
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
            case "Create in Me a Pure Heart...":
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
            case "He is the Gracious, the All_Bountiful...":
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
            case "Glory to Thee, O My God!":
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
            case "Magnified, O Lord My God, Be Thy Name...":
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
