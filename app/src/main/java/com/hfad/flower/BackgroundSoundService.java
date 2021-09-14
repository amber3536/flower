package com.hfad.flower;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class BackgroundSoundService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer mp;
    private int position;
    private String tag = "Background Sound Service";
    private AudioPlayerBahaullah bahaullahListener;
    private AudioPlayerAbdulBaha abdulBahaListener;
    private AudioPlayerTheBab theBabListener;
    //private AudioPlayerBahaullah bahaullah = new AudioPlayerBahaullah();
    private final IBinder mBinder = new MyBinder();
   // private IPla listener = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int prayer = intent.getIntExtra("track", 0);
        position = intent.getIntExtra("pos", 0);
        mp = MediaPlayer.create(this, prayer);
        mp.setOnCompletionListener(this);
        mp.seekTo(position);
        mp.start();

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

    @Override
    public void onDestroy() {
        super.onDestroy();

        mp.stop();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (bahaullahListener != null)
            bahaullahListener.resetButtons();
        else if (abdulBahaListener != null)
            abdulBahaListener.resetButtons();
    }

    public void setListener(AudioPlayerBahaullah listener) {
        bahaullahListener = listener;
    }

    public void setListener(AudioPlayerAbdulBaha listener) {
        abdulBahaListener = listener;
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
        mp.pause();
    }

    public void stop() {
        mp.stop();
    }






}
