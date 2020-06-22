package com.draz.diana.favouriteplaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class VisitedFragment extends Fragment {

    RecyclerView rv;

    PlacesRecyclerViewAdapter placesRecyclerViewAdapter;

    public VisitedFragment() {
        // Required empty public constructor
    }
    public static VisitedFragment newInstance() {
        VisitedFragment fragment = new VisitedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_visited, container, false);
        rv = v.findViewById(R.id.visitedList);
        rv.findViewById(R.id.visitedList);
        placesRecyclerViewAdapter = new VisitedPlacesAdapter();
        rv.setAdapter(placesRecyclerViewAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return v;
    }

    public void refresh() {
        placesRecyclerViewAdapter.notifyDataSetChanged();
    }
}
