package com.example.alex.mapsdownloader;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RegionsFragment extends android.support.v4.app.ListFragment {

    private static final String KEY = "path";
    private ArrayList<Integer> path;
    private List<Region> regions;

    public RegionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(KEY)) {
            path = args.getIntegerArrayList(KEY);
        }

        regions = Parser.getRegions(path);

        RegionsAdapter adapter = new RegionsAdapter(getActivity(), regions);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Region current = regions.get(position);
        if (current.getSubregions().size() > 0) {
            ((MainActivity) getActivity()).showNextRegionFragment(position, current.getName());
        }
    }
}
