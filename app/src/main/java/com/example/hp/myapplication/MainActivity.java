package com.example.hp.myapplication;

import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public ViewPager pager;
    public StatePagerAdapter adapter;
    int adPosition = 2;
    boolean onNotShown = true;
    int counter  = 1;
    ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<View> v = new ArrayList<>();

        fragments = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new CFragment());

        DFragment d = new DFragment();
        fragments.add(adPosition, d);

        adapter = new StatePagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int i = pager.getCurrentItem();

                Log.i(TAG, "size " + adapter.getCount() + " position " + position + " counter " + counter);

                Fragment f = null;
                if(position + 1 < adapter.getCount())
                    f = adapter.getFragment(position);

                if( f != null && f instanceof DFragment && onNotShown)
                {
                    Log.i(TAG, "dfragment");
                    onNotShown = false;
                    adapter.addFragment(new DFragment(), position - 1);
                    adapter.notifyDataSetChanged();
                    //pager.setCurrentItem(position + 1, false);
                }


                /*if(adPosition > 1 && adPosition == counter){

                    adPosition = -1;

                    adapter.addFragment(new DFragment(), position);
                    adapter.notifyDataSetChanged();
                    if(position + 2 < adapter.getCount())
                        adapter.addFragment(new DFragment(), position + 2);
                    //adapter.removeFragment(pager, adPosition - 2);
                    adapter.notifyDataSetChanged();
                    pager.setCurrentItem(position + 1, false);


                    /*adapter.removeAllFragments(pager);
                    fragments.clear();
                    fragments.add(new AFragment());
                    fragments.add(new CFragment());
                    fragments.add(new CFragment());
                    fragments.add(new CFragment());

                    adapter = new StatePagerAdapter(getSupportFragmentManager(), fragments);
                    pager.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pager.setCurrentItem(i);
                }*/

                counter++;


                /*if(f instanceof DFragment)
                {
                    onNotShown = false;
                    counter--;
                }

                //if(!onNotShown && (position == adPosition + 1 || position == adPosition - 1)) {
                if(!onNotShown && !(f instanceof DFragment)) {

                    onNotShown = true;

                    adapter.removeFragment(pager, adPosition);
                    adapter.notifyDataSetChanged();
                    if (position == adPosition + 1)
                        pager.setCurrentItem(i - 1);
                    else if (position == adPosition - 1)
                        pager.setCurrentItem(i);

                    Log.d(TAG, "leaving position");
                }*/

                Log.i(TAG, "counter " + counter);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}