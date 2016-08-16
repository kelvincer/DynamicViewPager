package com.example.hp.myapplication;

import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public ViewPager pager;
    public StatePagerAdapter adapter;
    int adPosition = 2;
    boolean onNotShown = true;
    int counter = 1;
    ArrayList<Fragment> fragments;
    List<Integer> indexAds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indexAds = new ArrayList<>();

        fragments = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new CFragment());

        final DFragment d = new DFragment();
        if (adPosition == 1)
            fragments.add(adPosition - 1, d);

        if (adPosition == 2)
            fragments.add(adPosition - 1, d);

        adapter = new StatePagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            float tempPositionOffset = 0;
            boolean onScrollingLeft = false;
            int currentPosition = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                if (position == 0) {
                    if (tempPositionOffset < positionOffset) {
                        // Log.d("eric", "scrolling left ...");
                        onScrollingLeft = true;
                    } else {
                        // Log.d("eric", "scrolling right ...");
                        onScrollingLeft = false;
                    }

                    tempPositionOffset = positionOffset;

                    // Log.d("eric", "position " + position + "; " + " positionOffset " + positionOffset + "; " + " positionOffsetPixels " + positionOffsetPixels + ";");
                }
            }

            @Override
            public void onPageSelected(int position) {

                Log.i(TAG, "size " + adapter.getCount() + " position " + position + " counter " + counter);

                if (onScrollingLeft) {
                    if (counter == adPosition - 2) {

                        Log.i(TAG, "enter left");

                        adPosition = 0;
                        if (position > 0) {
                            int i = adapter.addFragment(new DFragment(), position);
                            indexAds.add(i);
                        }
                        //if(counter < adapter.getCount() - 1)
                        if (position + 1 < adapter.getCount()) {
                            int i = adapter.addFragment(new DFragment(), position + 2);
                            indexAds.add(i);
                        }
                        pager.setCurrentItem(position + 1, false);

                        return;
                    }
                } else {

                    Log.i(TAG, "counter " + counter);

                    if (counter == adPosition - 2) {

                        Log.i(TAG, "enter right");
                        adPosition = 0;
                        if (position > 1) {
                            int i = adapter.addFragment(new DFragment(), position - 1);
                            indexAds.add(i);
                        }
                        if (position < adapter.getCount()) {
                            int i = adapter.addFragment(new DFragment(), position + 1);
                            indexAds.add(i);
                        }
                        pager.setCurrentItem(position, false);

                        return;

                    }
                }



                if (position < adapter.getCount() - 1) {
                    if (onNotShown &&!(adapter.getFragment(position) instanceof DFragment) && (adapter.getFragment(position + 1) instanceof DFragment)) {
                        onNotShown = false;
                        currentPosition = position + 1;

                        Log.i(TAG, "current position " + currentPosition);
                    }
                }

                if ( position + 3 == currentPosition){

                    currentPosition = 0;

                    Collections.reverse(indexAds);

                    for (int i : indexAds) {

                        adapter.removeFragment(pager, i);
                    }
                    Log.i(TAG, "remove fragment");

                    indexAds.clear();
                    pager.setCurrentItem(position, false);




                    /*if (currentPosition == position + 2 || currentPosition == position) {
                        Fragment f = null;
                        if (position < adapter.getCount() - 1)
                            f = adapter.getFragment(position + 1);
                        if (f != null && !(f instanceof DFragment) && indexAds.size() > 0) {

                            Collections.reverse(indexAds);

                            for (int i : indexAds) {

                                adapter.removeFragment(pager, i);
                            }
                            Log.i(TAG, "remove fragment");

                            indexAds.clear();
                            pager.setCurrentItem(position - 1, false);
                        }

                    }*/
                }
                counter++;

                //Log.i(TAG, "counter " + counter);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}