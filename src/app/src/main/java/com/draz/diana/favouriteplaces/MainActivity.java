package com.draz.diana.favouriteplaces;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.draz.diana.favouriteplaces.ui.main.TabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private TabsPagerAdapter tabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabsPagerAdapter = new TabsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        tabsPagerAdapter.visitedFragment.refresh();
        tabsPagerAdapter.toVisitFragment.refresh();
    }

    @Override
    public void onPageSelected(int position) {
        tabsPagerAdapter.visitedFragment.refresh();
        tabsPagerAdapter.toVisitFragment.refresh();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        tabsPagerAdapter.visitedFragment.refresh();
        tabsPagerAdapter.toVisitFragment.refresh();
    }
}