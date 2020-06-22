package com.draz.diana.favouriteplaces.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.draz.diana.favouriteplaces.R;
import com.draz.diana.favouriteplaces.ToVisitFragment;
import com.draz.diana.favouriteplaces.VisitedFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public ToVisitFragment toVisitFragment;
    public VisitedFragment visitedFragment;

    public TabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            if (toVisitFragment == null)
            {
                toVisitFragment = ToVisitFragment.newInstance();
            }
            return toVisitFragment;
        } else {
            if (visitedFragment == null)
            {
                visitedFragment = VisitedFragment.newInstance();
            }
            return visitedFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}