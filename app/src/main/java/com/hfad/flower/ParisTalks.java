package com.hfad.flower;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ParisTalks extends Fragment {
    private View view;
    MediaPlayer mp;
    private TextView tv;
    String[] mobileArray = {"The Evolution of Matter and Development of the Soul","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_paris_talks, container, false);
        //tv = view.findViewById(R.id.textView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, mobileArray);

        final ListView listView = (ListView) view.findViewById(R.id.paris_talks_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

//                switch (selectedItem) {
//                    case "The Evolution of Matter and Development of the Soul":

                        loadFragment(new AudioPlayer(), "EXTRA", selectedItem);
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
