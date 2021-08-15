package com.hfad.flower;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            loadFragment(new MainFragment());
        //btnzIn = (Button)findViewById(R.id.zoomButton);
        //btnzOut = (Button)findViewById(R.id.btnZoomOut);


//        img.setOnHoverListener(new View.OnHoverListener() {
//            @Override
//            public boolean onHover(View v, MotionEvent event) {
//                Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//                img.startAnimation(animZoomIn);
//                return false;
//            }
//        });
//        btnzOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
//                img.startAnimation(animZoomOut);
//            }
//        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        //mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
    }

//    private void hideView() {
//        img1.setVisibility(View.GONE);
//
//        meditations.setVisibility(View.GONE);
//        prayers.setVisibility(View.GONE);
//        mystical_writings.setVisibility(View.GONE);
//        abdulBaha.setVisibility(View.GONE);
//        bahaullah.setVisibility(View.GONE);
//        theBab.setVisibility(View.GONE);
//        sevenValleys.setVisibility(View.GONE);
//        fourValleys.setVisibility(View.GONE);
//        hiddenWords.setVisibility(View.GONE);
//        parisTalks.setVisibility(View.GONE);
//        lightOfTheWorld.setVisibility(View.GONE);
//        selections.setVisibility(View.GONE);
//    }

//    private void showView() {
//        img1.setVisibility(View.VISIBLE);
//
//        meditations.setVisibility(View.VISIBLE);
//        prayers.setVisibility(View.VISIBLE);
//        mystical_writings.setVisibility(View.VISIBLE);
//        abdulBaha.setVisibility(View.VISIBLE);
//        bahaullah.setVisibility(View.VISIBLE);
//        theBab.setVisibility(View.VISIBLE);
//        sevenValleys.setVisibility(View.VISIBLE);
//        fourValleys.setVisibility(View.VISIBLE);
//        hiddenWords.setVisibility(View.VISIBLE);
//        parisTalks.setVisibility(View.VISIBLE);
//        lightOfTheWorld.setVisibility(View.VISIBLE);
//        selections.setVisibility(View.VISIBLE);
//    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); // save the changes
    }

    @Override
    public void onBackPressed() {
        //showView();
        //selectionChosen();

        super.onBackPressed();
    }

//   public void selectionChosen() {
//               if (getIntent().hasExtra("EXTRA")) {
//            switch (getIntent().getStringExtra("EXTRA")) {
//                case "The Evolution of Matter and Development of the Soul":
//                    parisTalks.setVisibility(View.VISIBLE);
//                    break;
//
//            }
//        }
//   }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            hideView();
//        }
//    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
//        img.startAnimation(animZoomIn);
//        return false;
//    }
}
