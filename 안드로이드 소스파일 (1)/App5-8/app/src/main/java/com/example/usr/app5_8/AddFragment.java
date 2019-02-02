package com.example.usr.app5_8;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        MainActivity.name = (EditText) view.findViewById(R.id.editText);
        MainActivity.tel = (EditText) view.findViewById(R.id.editText2);
        MainActivity.save = (Button) view.findViewById(R.id.add);
        return view;
    }
}
