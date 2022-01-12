package com.hfad.flower;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
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
    private AudioPlayerMeditations meditationsListener;
    private AudioPlayerHiddenWords hiddenWordsListener;
    private AudioPlayerParisTalks parisTalksListener;
    private AudioPlayerMysticalWords mysticalWordsListener;
    private SeekBar seekBar;
    private Intent intent1;
    private int prayer;
    private int figure;
    //private AudioPlayerBahaullah bahaullah = new AudioPlayerBahaullah();
    private final IBinder mBinder = new MyBinder();
   // private IPla listener = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(tag, "onBind: ");

        return mBinder;
    }

    @Override
    public void onCreate() {
        Log.i(tag, "onCreate: ");
        super.onCreate();
        //mp = MediaPlayer.create(this, prayer);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        position = intent.getFloatExtra("pos", 0);
        Log.i(tag, "onStartCommand: " + position);
        int ans = intent.getIntExtra("all", 0);

        if (ans == 1)
            prep(intent);
        //seekBar.setMax(mp.getDuration());
        mp.setOnCompletionListener(this);

        mp.seekTo((int)position);

        //mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);

        mp.start();



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
        if (mp != null)
            mp.release();
        super.onDestroy();
        Log.i(tag, "onDestroy: stopSelf");
        //stopSelf();
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
        else if (figure == 6)
            meditationsListener.trackEndedMeditations();
        else if (figure == 7)
            mysticalWordsListener.trackEndedMysticalWords();
    }

    public void prep(Intent intent) {

        prayer = intent.getIntExtra("track", 0);
        Log.i(tag, "onStartCommand: " + prayer);
        mp = MediaPlayer.create(this, prayer);
    }



    public void setListener(AudioPlayerBahaullah listener) {
        bahaullahListener = listener;
        //bahaullahListener.setEndTime(getDuration());
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

    public void setListener(AudioPlayerMeditations listener) {
        meditationsListener = listener;
        figure = 6;
    }

    public void setListener(AudioPlayerMysticalWords listener) {
        mysticalWordsListener = listener;
        figure = 7;
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
        //Log.i(tag, "getDuration: " + mp.getDuration());
        return 0;
    }

    public float getCurrentPosition() {
        if (mp != null)
            return mp.getCurrentPosition();
        else
            return 0;
    }

    public void seekTo(int progress)  {
        Log.i(tag, "seekTo: " + progress);
        if (mp != null)
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

    public void start() {
        if (mp != null)
            mp.start();
    }

    public void stop() {
        if (mp != null)
            mp.stop();
    }






}
