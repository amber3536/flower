package com.hfad.flower;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

public class BackgroundSoundService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer mp;
    private float position;
    private String tag = "Background Sound Service";
    private AudioPlayerBahaullah bahaullahListener;
    private AudioPlayerAbdulBaha abdulBahaListener;
    private AudioPlayerTheBab theBabListener;
    private AudioPlayerPrayers prayersListener;
    private AudioPlayerHiddenWords hiddenWordsListener;
    private AudioPlayerParisTalks parisTalksListener;
    private SeekBar seekBar;
    private int figure;
    //private AudioPlayerBahaullah bahaullah = new AudioPlayerBahaullah();
    private final IBinder mBinder = new MyBinder();
   // private IPla listener = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        position = intent.getFloatExtra("pos", 0);
        Log.i(tag, "onStartCommand: " + position);
        int prayer = intent.getIntExtra("track", 0);
        Log.i(tag, "onStartCommand: " + this);
        mp = MediaPlayer.create(this, prayer);

        //seekBar.setMax(mp.getDuration());

        mp.setOnCompletionListener(this);
        mp.seekTo((int)position);
        //mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);

        mp.start();

//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser)
//                    mp.seekTo(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });


//        mp.setOnCompletionListener(listener = new MediaPlayer.OnCompletionListener() {
//
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//            Log.i(tag, "onCompletion: ");

            //bahaullah.resetButtons();
            //performOnEnd();
            // mp.release();
//            Log.i("Audio Bahaullah", "onCompletion: " + trackNum);
//            if (track.equals("all") && trackNum < numTracks) {
//                trackNum++;
//                playAll(trackNum);
//            }
//            else if (track.equals("all") && trackNum == numTracks) {
//                trackNum = 0;
//                playAllOn = 1;
//                playBtn.setVisibility(View.VISIBLE);
//                pauseBtn.setVisibility(View.GONE);
//                playTrack(prayerArray[0]);
//            }
//            else {
//                playBtn.setVisibility(View.VISIBLE);
//                pauseBtn.setVisibility(View.GONE);
//            }

      //  }

   // });

        return START_STICKY;
    }

//    private Handler mSeekbarUpdateHandler = new Handler();
//    private Runnable mUpdateSeekbar = new Runnable() {
//        @Override
//        public void run() {
//            seekBar.setProgress(mp.getCurrentPosition());
//            mSeekbarUpdateHandler.postDelayed(this, 50);
//        }
//    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(tag, "onDestroy: stopSelf");
        stopSelf();
        //bahaullahListener = null;
//        if (mp != null)
//            mp.stop();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i(tag, "onCompletion: figure " + figure);
        Log.i(tag, "onCompletion: " + abdulBahaListener);
        if (figure == 0)
            bahaullahListener.trackEndedBahaullah();
        else if (figure == 2)
            abdulBahaListener.trackEndedAbdulBaha();
        else if (figure == 1)
            theBabListener.trackEndedTheBab();
        else if (figure == 3)
            parisTalksListener.trackEndedParisTalks();
        else if (figure == 4)
            hiddenWordsListener.trackEndedHiddenWords();
        else if (figure == 5)
            prayersListener.trackEndedPrayers();
    }

    public void setListener(AudioPlayerBahaullah listener) {
        bahaullahListener = listener;
        figure = 0;
    }

    public void setListener(AudioPlayerAbdulBaha listener) {
        abdulBahaListener = listener;
    }

    public void setListener(AudioPlayerTheBab listener) {
        theBabListener = listener;
        figure = 1;
    }

    public void setListener(AudioPlayerParisTalks listener) {
        parisTalksListener = listener;
        figure = 3;
    }

    public void setListener(AudioPlayerHiddenWords listener) {
        hiddenWordsListener = listener;
        figure = 4;
    }

    public void setListener(AudioPlayerPrayers listener) {
        prayersListener = listener;
        figure = 5;
    }

    public boolean isPlaying() {
        if (mp == null)
            return false;
        else
            return mp.isPlaying();
    }

    public int getDuration() {
        if (mp != null)
            return mp.getDuration();
       // Log.i(tag, "getDuration: " + mp.getDuration());
        return 0;
    }

    public float getCurrentPosition() {
        if (mp != null)
            return mp.getCurrentPosition();
        else
            return 0;
    }

    public void seekTo(int progress)  {
        mp.seekTo(progress);
    }


//    @Override
//    public void onCompletion(MediaPlayer mediaPlayer) {
//        Log.i(tag, "onCompletion: ");
//    }



//    @Override
//    public void onCompletion(MediaPlayer mediaPlayer) {
//
//
//    }

    public class MyBinder extends Binder {
        public BackgroundSoundService getService() {
            return BackgroundSoundService.this;
        }
    }

    public void pause() {
        if (mp != null)
            mp.pause();
       // mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);
    }

    public void stop() {
        if (mp != null)
            mp.stop();
    }






}
