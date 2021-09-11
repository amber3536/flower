package com.hfad.flower;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {
    private View view;
    //MediaPlayer mp;
    //private TextView tv;
    private Button btnzIn;
    private Button btnzOut;
    private ImageView img1;
    private ImageView paris_pic;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private ImageView img7;
    private ImageView img8;
    private ImageView img9;
    private TextView meditations;
    private TextView prayers;
    private TextView mystical_writings;
    private TextView txt;
    private TextView abdulBaha;
    private TextView bahaullah;
    private TextView theBab;
    private TextView hiddenWords;
    private TextView sevenValleys;
    private TextView fourValleys;
    private TextView selections;
    private TextView parisTalks;
    private TextView lightOfTheWorld;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);
        setRetainInstance(true);

        img1 = (ImageView)view.findViewById(R.id.petalView);
        // paris_pic = findViewById(R.id.)
//        img2 = (ImageView)findViewById(R.id.petalView2);
//        img3 = (ImageView)findViewById(R.id.petalView3);
//        img4 = (ImageView)findViewById(R.id.petalView4);
//        img5 = (ImageView)findViewById(R.id.petalView5);
//        img6 = (ImageView)findViewById(R.id.petalView6);
//        img7 = (ImageView)findViewById(R.id.petalView7);
//        img8 = (ImageView)findViewById(R.id.petalView8);
//        img9 = (ImageView)findViewById(R.id.petalView9);
        meditations = view.findViewById(R.id.meditations);
        mystical_writings = view.findViewById(R.id.mysticalWords);
        prayers = view.findViewById(R.id.prayers);

        hiddenWords = view.findViewById(R.id.hiddenWords);
        sevenValleys = view.findViewById(R.id.sevenValleys);
        fourValleys = view.findViewById(R.id.fourValleys);
        bahaullah = view.findViewById(R.id.bahaullah);
        abdulBaha = view.findViewById(R.id.abdulBaha);
        theBab = view.findViewById(R.id.theBab);
        selections = view.findViewById(R.id.selectionsAbdulBaha);
        parisTalks = view.findViewById(R.id.parisTalks);
        lightOfTheWorld = view.findViewById(R.id.lightOfTheWorld);

        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{ContextCompat.getColor(getContext(), R.color.fadedTurquoise),
                        ContextCompat.getColor(getContext(), R.color.colorFadedDarkPurple),
                        ContextCompat.getColor(getContext(), R.color.colorFadedPurple),
                        ContextCompat.getColor(getContext(), R.color.fadedTurquoise)});

        view.findViewById(R.id.layout_main).setBackground(gradientDrawable);



        //MediaPlayer mp = MediaPlayer.create(this, getResources().openRawResource(R.raw.brooklyn_bridge));

//        if (getIntent().hasExtra("EXTRA")) {
//            switch (getIntent().getStringExtra("EXTRA")) {
//                case "The Evolution of Matter and Development of the Soul":
//                    parisTalks.setVisibility(View.VISIBLE);
//                    break;
//
//            }
//        }
        selections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                //img1.startAnimation(animZoomIn);
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
//                img1.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img3.clearAnimation();
//                img4.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new SevenValleys());
                // hideView();
            }
        });

        lightOfTheWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                //img2.startAnimation(animZoomIn);
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
//                img2.startAnimation(animClockwise);
//
//                img1.clearAnimation();
//                img3.clearAnimation();
//                img4.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new SevenValleys());
                // hideView();
            }
        });



        parisTalks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                //img3.startAnimation(animZoomIn);
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
//                img3.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img1.clearAnimation();
//                img4.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new ParisTalks());
                //hideView();
            }
        });

        hiddenWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                //img4.startAnimation(animZoomIn);
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise2);
//                img4.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img3.clearAnimation();
//                img1.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new HiddenWords());
                // hideView();
            }
        });

        sevenValleys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise2);
//                img5.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img3.clearAnimation();
//                img4.clearAnimation();
//                img1.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new SevenValleys());
                // hideView();
            }
        });

        fourValleys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadFragment(new SevenValleys());
                //hideView();
            }
        });

        bahaullah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadFragment(new Bahaullah());
                //hideView();
            }
        });

        theBab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise3);
//                img8.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img3.clearAnimation();
//                img4.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img1.clearAnimation();
//                img9.clearAnimation();

                loadFragment(new TheBab());
                //hideView();
            }
        });

        abdulBaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animation animClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise3);
//                img9.startAnimation(animClockwise);
//
//                img2.clearAnimation();
//                img3.clearAnimation();
//                img4.clearAnimation();
//                img5.clearAnimation();
//                img6.clearAnimation();
//                img7.clearAnimation();
//                img8.clearAnimation();
//                img1.clearAnimation();

                loadFragment(new AbdulBaha());
                //hideView();
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); // save the changes
    }
}
