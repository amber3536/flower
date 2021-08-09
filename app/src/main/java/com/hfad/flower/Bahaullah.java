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

public class Bahaullah extends Fragment {
    private View view;
    MediaPlayer mp;
    private TextView tv;
    String[] mobileArray = {"Attract the Hearts of Men", "Lauded Be Thy Name", "Glorified Art Thou, O Lord My God",
            "From the Sweet-Scented Streams", "Create in Me a Pure Heart", "He is the Gracious, the All_Bountiful",
            "Glory to Thee, O My God!", "Magnified, O Lord My God, Be Thy Name"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bahaullah, container, false);
        //tv = view.findViewById(R.id.textView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, mobileArray);

        final ListView listView = (ListView) view.findViewById(R.id.bahaullah_list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                //Intent intent = new Intent(view.getContext(), MainActivity.class);
                //intent.putExtra("EXTRA", selectedItem);
                loadFragment(new AudioPlayer(), "EXTRA", selectedItem);

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
