package com.example.hp.myapplication;

import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public ViewPager pager;
    public StatePagerAdapter adapter;
    int adPosition = 3;
    boolean onNotShown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<View> v = new ArrayList<>();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());

        DFragment d = new DFragment();
        fragments.add(adPosition, d);

        adapter = new StatePagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapter);

        if(adPosition == 0)
            onNotShown = false;

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.i(TAG, "size " + adapter.getCount() + " index " + position);

                Fragment f = adapter.getFragment(position);

                if(f instanceof DFragment)
                    onNotShown = false;

                //if(!onNotShown && (position == adPosition + 1 || position == adPosition - 1)) {
                if(!onNotShown && !(f instanceof DFragment)) {

                    onNotShown = true;
                    int i = pager.getCurrentItem();
                    adapter.removeFragment(pager, adPosition);
                    adapter.notifyDataSetChanged();
                    if (position == adPosition + 1)
                        pager.setCurrentItem(i - 1);
                    else if (position == adPosition - 1)
                        pager.setCurrentItem(i);

                    Log.d(TAG, "leaving position");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}