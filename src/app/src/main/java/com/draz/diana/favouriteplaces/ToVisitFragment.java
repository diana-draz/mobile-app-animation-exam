package com.draz.diana.favouriteplaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ToVisitFragment extends Fragment {

    RecyclerView rv;
    PlacesRecyclerViewAdapter placesRecyclerViewAdapter;
    public ToVisitFragment() {
        // Required empty public constructor
    }

    public static ToVisitFragment newInstance() {
        ToVisitFragment fragment = new ToVisitFragment();
        return fragment;
    }

    public void refresh() {
        placesRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_to_visit, container, false);
        rv = v.findViewById(R.id.toVisitList);
        placesRecyclerViewAdapter = new ToVisitPlacesAdapter();
        rv.setAdapter(placesRecyclerViewAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        return v;
    }
}
