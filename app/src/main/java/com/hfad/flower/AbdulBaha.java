package com.hfad.flower;

import android.content.res.ColorStateList;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AbdulBaha extends Fragment {
    private View view;
    MediaPlayer mp;
    private FloatingActionButton playBtn;
    private FloatingActionButton pauseBtn;
    private Button btn;
    private TextView txt;

    String[] mobileArray = {"Make firm our steps...", "O compassionate God!", "O Thou beloved of my heart...",
            "O Thou compassionate Lord...", "O my Lord! Thou knowest...", "Lord! Pitiful are we...",
            "O Thou kind Lord!", "O my merciful Lord!", "He is the Most Holy...", "O God of Mercy!",
            "O Lord! Grant me a measure...", "O God! Refresh and gladden...", "O Divine Providence!"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_prayer_list, container, false);
        //setRetainInstance(true);
        // btn = view.findViewById(R.id.button_play_all);
        txt = view.findViewById(R.id.tv_prayer_list);
        playBtn = view.findViewById(R.id.fab_prayer_list);
        //pauseBtn = view.findViewById(R.id.fab_bahaullah1);

        TextPaint paint = txt.getPaint();
        float width = paint.measureText("Play All");

        Shader textShader = new LinearGradient(0, 0, width, txt.getTextSize(),
                new int[]{
//                        ContextCompat.getColor(getContext(), R.color.red),
//                        ContextCompat.getColor(getContext(), R.color.orange),
//
//                        ContextCompat.getColor(getContext(), R.color.green),
                        ContextCompat.getColor(getContext(), R.color.blue),
                        ContextCompat.getColor(getContext(), R.color.violet)
                }, null, Shader.TileMode.CLAMP);
        txt.getPaint().setShader(textShader);

        txt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));
        //tv.setTypeface(null, Typeface.BOLD);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, mobileArray);

        final ListView listView = (ListView) view.findViewById(R.id.prayer_list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                //Intent intent = new Intent(view.getContext(), MainActivity.class);
                //intent.putExtra("EXTRA", selectedItem);
                loadFragment(new AudioPlayerAbdulBaha(), "EXTRA", selectedItem);

//                switch (selectedItem) {
//                    case "O Thou Whose Face":
//
//                        break;
//                    case "From the Sweet Scented Streams":
//                        Intent intent2 = new Intent(view.getContext(), MainActivity.class);
//                        intent2.putExtra("EXTRA", selectedItem);
//                        loadFragment(new AudioPlayer());
//                        break;
//                }



//                TextView tv = view.findViewById(R.id.label);
//                tv.setText("boo");
//                Intent intent = new Intent(view.getContext(), ParisTalks.class);
//                intent.putExtra(selectedItem, "Paris");
                //getFragmentManager().popBackStack();
                //textView.setText("The best football player is : " + selectedItem);
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pauseBtn.setVisibility(View.VISIBLE);
                //playBtn.setVisibility(View.GONE);
                loadFragment(new AudioPlayerAbdulBaha(), "EXTRA", "all");
                //mp.start();
            }
        });
//        TextView tv = view.findViewById(R.id.label);
//        tv.setText("somehow");



//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mp.start();
//            }
//        });
        return view;
    }



    public void loadFragment(Fragment fragment, String key, String message) {
        FragmentManager fm = getFragmentManager();
        Bundle result = new Bundle();
        result.putString(key, message);
        fragment.setArguments(result);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
