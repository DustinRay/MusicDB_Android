package com.example.musicdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.musicdatabase.adapters.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    private FragmentManager fm = getSupportFragmentManager();
    public static String SEARCH_QUERY = "SEARCH_QUERY";
    private String query;

    private Fragment fragment;
    private String fragmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new BioFragment();
        fragmentName = BioFragment.class.getSimpleName();

        SearchView artistSearchView = findViewById(R.id.artistSearchView);

        viewPager = findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fm);
        viewPager.setAdapter(viewPagerAdapter);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        fragment = new BioFragment();
                        fragmentName = BioFragment.class.getSimpleName();
                        getFragmentManagerTransaction(fragmentName, fragment);
                        bottomNavigationView.getMenu().findItem(R.id.bioTab).setChecked(true);
                        break;
                    case 1:
                        fragment = new AlbumsFragment();
                        fragmentName = AlbumsFragment.class.getSimpleName();
                        getFragmentManagerTransaction(fragmentName, fragment);
                        bottomNavigationView.getMenu().findItem(R.id.albumsTab).setChecked(true);
                        break;
                    case 2:
                        fragment = new SimilarArtistsFragment();
                        fragmentName = SimilarArtistsFragment.class.getSimpleName();
                        getFragmentManagerTransaction(fragmentName, fragment);
                        bottomNavigationView.getMenu().findItem(R.id.similarArtistsTab).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        artistSearchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.bioTab:
                viewPager.setCurrentItem(0);
                break;
            case R.id.albumsTab:
                viewPager.setCurrentItem(1);
                break;
            case R.id.similarArtistsTab:
                viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        this.query = query;
        getFragmentManagerTransaction(fragmentName, fragment);

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void getFragmentManagerTransaction(String fragmentName, Fragment fragment) {
        Bundle args = new Bundle();
        args.putString(SEARCH_QUERY, query);
        fragment.setArguments(args);

        fm.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(fragmentName)
                .commit();
    }
}
