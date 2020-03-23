package com.example.musicdatabase.adapters;

import com.example.musicdatabase.AlbumsFragment;
import com.example.musicdatabase.BioFragment;
import com.example.musicdatabase.SimilarArtistsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BioFragment();
            case 1:
                return new AlbumsFragment();
            case 2:
                return new SimilarArtistsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}