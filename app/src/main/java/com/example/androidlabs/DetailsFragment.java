package com.example.androidlabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameText = view.findViewById(R.id.nameText);
        TextView heightText = view.findViewById(R.id.heightText);
        TextView massText = view.findViewById(R.id.massText);

        Bundle args = getArguments();
        if (args != null) {
            nameText.setText(args.getString("name", ""));
            heightText.setText(args.getString("height", ""));
            massText.setText(args.getString("mass", ""));
        }

        return view;
    }
}
