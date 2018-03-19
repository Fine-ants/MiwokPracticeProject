package com.example.android.miwok;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jordanhuus on 3/18/2018.
 */

public class NumbersFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View numberView = inflater.inflate(R.layout.fragment_numbers, container, false);
        return numberView;
    }
}
