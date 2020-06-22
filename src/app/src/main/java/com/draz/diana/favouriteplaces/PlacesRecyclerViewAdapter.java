package com.draz.diana.favouriteplaces;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.icu.util.IslamicCalendar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PlacesRecyclerViewAdapter extends RecyclerView.Adapter<PlacesRecyclerViewAdapter.ViewHolder> {

    public static ArrayList<Place> Places;

    private boolean IsVisited;

    public PlacesRecyclerViewAdapter(boolean isVisited) {
        IsVisited =isVisited;

        if (Places == null) {
            Places = new ArrayList<Place>();
            initPlaces();
        }
    }

    public void add(Place place) {
        Places.add(place);
    }

    public void invert(int id) {
        for (int i = 0; i < Places.size(); i++) {
            if (Places.get(i).Id == id) {
                Places.get(i).IsVisited = Places.get(i).IsVisited;
            }
        }
    }

    private void initPlaces() {
        for (int i = 0; i < 20; i++) {
            Place place = new Place(i + 1, "Place " + String.valueOf(i + 1), "Description of place " + String.valueOf(i + 1), i % 2 == 0);
            Places.add(place);
        }
    }


    private ArrayList<Place> getFilterApplied() {
        ArrayList<Place> arr = new ArrayList<Place>();
        for (int i = 0; i< Places.size(); i++) {
            if (Places.get(i).IsVisited == IsVisited) {
                arr.add(Places.get(i));
            }
        }

        return arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (!IsVisited) {
             view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.one_to_visit, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.one_visited, parent, false);
        }

        return new ViewHolder(view, IsVisited);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place p = getFilterApplied().get(position);
        holder.idTextView.setText(String.valueOf(p.Id));
        holder.nameTextView.setText(p.Name);
        holder.descriptionTextView.setText(p.Description);
        holder.visitButton.setTag(p.Id);

        holder.visitButton.setVisibility(View.VISIBLE);
        holder.visitButton.setAlpha(1);

        View.OnClickListener onClickListener = new ClickListener(this)
        {

        };
        holder.visitButton.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return getFilterApplied().size();
    }

    class ClickListener implements View.OnClickListener {
        private final PlacesRecyclerViewAdapter adapter;

        public ClickListener(PlacesRecyclerViewAdapter adapter) {
            this.adapter = adapter;
        }

        @Override
        public void onClick(View v) {
            int id = (int)v.getTag();
            int position = 0;

            ArrayList<Place> places = getFilterApplied();
            for(int i = 0; i < places.size(); i++)
            {
                if (places.get(i).Id == id) {
                    places.get(i).IsVisited = !places.get(i).IsVisited;
                    position = i;
                }
            }
            v.animate().alpha(0f).setDuration(500)
                    .setListener(new AnimatorListener(v, adapter, position));

        }
    }

    class AnimatorListener implements Animator.AnimatorListener {

        private final View v;
        private final int position;
        private final PlacesRecyclerViewAdapter adapter;

        public AnimatorListener(View v, PlacesRecyclerViewAdapter adapter, int position ) {
            this.v = v;
            this.adapter = adapter;
            this.position = position;
        }


        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            this.v.setVisibility(View.INVISIBLE);
            adapter.notifyItemRemoved(position);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView idTextView;
        final TextView nameTextView;
        final TextView descriptionTextView;
        final Button visitButton;

        ViewHolder(View view, boolean isVisited) {
            super(view);
            if (isVisited) {
                idTextView = (TextView) view.findViewById(R.id.visited_id);
                nameTextView = (TextView) view.findViewById(R.id.visited_name);
                descriptionTextView = (TextView) view.findViewById(R.id.visited_description);
                visitButton = (Button) view.findViewById(R.id.visited_button);
                visitButton.setText("Visit again");
            } else {
                visitButton = (Button) view.findViewById(R.id.visit_button);
                descriptionTextView = (TextView) view.findViewById(R.id.to_visit_description);
                idTextView = (TextView) view.findViewById(R.id.to_visit_id);
                nameTextView = (TextView) view.findViewById(R.id.to_visit_name);
                visitButton.setText("Visit");
            }

        }
    }
}
