package com.icaynia.arimusic;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class TabbedActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public Global global;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        /* layout */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AriSong");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int position) {
                //Force the fragment to reload its data
                mSectionsPagerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //Not used
            }


        });


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        // global initialize
        global = (Global) getApplication();
        global.initSetting();
        // musicBar initialize
        global.musicBar = (MusicBar) findViewById(R.id.musicBar);
        global.musicBar.setActivity(TabbedActivity.this);
        global.getMusicList();

        global.musicBar.updatePlayingInfo();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();

        return super.onCreateOptionsMenu(menu);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = null;
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_home, container, false);
                    onFragmentHome(rootView);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_musiclist, container, false);
                    onFragmentMusicList(rootView);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_playlist, container, false);
                    onFragmentNowList(rootView);
                    break;
                case 4:
                    rootView = inflater.inflate(R.layout.fragment_analytics, container, false);
                    onFragmentAnalytics(rootView);
            }
            //getFragmentManager().beginTransaction().detach(this).attach(this).commit();


            return rootView;
        }


        public void onFragmentHome(View v) {
            //home
            //
            new HomeFragment(getContext(), v);
        }

        public void onFragmentMusicList(View v) {
            //MusicList
            new MyMusicFragment(getContext(), v);
        }

        public void onFragmentNowList(View v) {
            //NowList
            new PlayListFragment(getContext(), v);
        }

        public void onFragmentAnalytics(View v) {
            //Analytics
        }

        @Override
        public void onResume() {
            super.onResume();
         }



    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "HOME";
                case 1:
                    return "MY MUSIC";
                case 2:
                    return "PLAYLIST";
                case 3:
                    return "ANALYTICS";
            }
            return null;

        }
        @Override
        public int getItemPosition(Object object) {

            return POSITION_NONE;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public class myThread extends Thread {
        public myThread() {

        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }


}
