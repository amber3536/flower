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
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ParisTalks extends Fragment {
    private View view;
    MediaPlayer mp;
    private TextView tv;
    private FloatingActionButton playBtn;
    private TextView txt;
    String[] mobileArray = {"The Pitiful Causes of War, and the Duty of Everyone to Strive for Peace",
            "The Universal Love", "Beauty and Harmony in Diversity", "Lecture Given at a Studio in Paris",
            "Pain and Sorrow"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_prayer_list, container, false);
        //tv = view.findViewById(R.id.textView);
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

        //txt.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getActivity(), R.color.colorAccent)));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, mobileArray);

        final ListView listView = (ListView) view.findViewById(R.id.prayer_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

//                switch (selectedItem) {
//                    case "The Evolution of Matter and Development of the Soul":

                        loadFragment(new AudioPlayerParisTalks(), "EXTRA", selectedItem);
                      //  break;
              //  }



//                TextView tv = view.findViewById(R.id.label);
//                tv.setText("boo");
//                Intent intent = new Intent(view.getContext(), ParisTalks.class);
//                intent.putExtra(selectedItem, "Paris");
                //getFragmentManager().popBackStack();
                //textView.setText("The best football player is : " + selectedItem);
            }
        });
//        TextView tv = view.findViewById(R.id.label);
//        tv.setText("somehow");

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pauseBtn.setVisibility(View.VISIBLE);
                //playBtn.setVisibility(View.GONE);
                loadFragment(new AudioPlayerParisTalks(), "EXTRA", "all");
                //mp.start();
            }
        });

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
