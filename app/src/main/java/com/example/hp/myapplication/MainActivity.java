package com.example.hp.myapplication;

import android.content.SharedPreferences;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MainPagerAdapter pagerAdapter;
    public ViewPager pager;
    LinearLayout v4;
    LinearLayout v3;
    LinearLayout v2;
    public FragmenPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<View> v = new ArrayList<>();

        /*v2 = (LinearLayout) getLayoutInflater().inflate (R.layout.view_2, null);
        v.add(v2);

        v3 = (LinearLayout) getLayoutInflater().inflate (R.layout.view_3, null);
        v.add(v3);

        v4 = (LinearLayout) getLayoutInflater().inflate (R.layout.view_4, null);
        v.add(v4);*/

        ArrayList<Fragment> fragments  = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new BFragment());

        CFragment c  = new CFragment();
        c.setAdapter(adapter);
        c.setViewPager(pager);
        fragments.add(c);

        /*pagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById (R.id.view_pager);
        pager.setAdapter (pagerAdapter);*/

        adapter = new FragmenPagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById (R.id.view_pager);
        pager.setAdapter (adapter);

        c.setAdapter(adapter);
        c.setViewPager(pager);

        // Create an initial view to display; must be a subclass of FrameLayout.
        LayoutInflater inflater = getLayoutInflater();
        final LinearLayout v0 = (LinearLayout) inflater.inflate (R.layout.view, null);
         //pagerAdapter.addView (v0, 0);
        // pagerAdapter.notifyDataSetChanged();


        /*Button b = (Button) v4.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* pagerAdapter.removeView(pager, v2);
                pagerAdapter.removeView(pager, v3);
                pagerAdapter.removeView(pager, v4);

                pagerAdapter.removeAllViews(pager);

                pagerAdapter.addView (new DFragment(), 0);
                pagerAdapter.notifyDataSetChanged();
            }
        });*/

        /*Button b3 = (Button) v3.findViewById(R.id.button);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pagerAdapter.addView (v0, 0);
                pagerAdapter.notifyDataSetChanged();
            }
        });*/
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to add a view to the ViewPager.
   /* public void addView (View newPage)
    {
        int pageIndex = pagerAdapter.addView (newPage);
        // You might want to make "newPage" the currently displayed page:
        pager.setCurrentItem (pageIndex, true);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to remove a view from the ViewPager.
    public void removeView (View defunctPage)
    {
        int pageIndex = pagerAdapter.removeView (pager, defunctPage);
        // You might want to choose what page to display, if the current page was "defunctPage".
        if (pageIndex == pagerAdapter.getCount())
            pageIndex--;
        pager.setCurrentItem (pageIndex);
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to get the currently displayed page.
    public View getCurrentPage ()
    {
        return pagerAdapter.getView (pager.getCurrentItem());
    }

    //-----------------------------------------------------------------------------
    // Here's what the app should do to set the currently displayed page.  "pageToShow" must
    // currently be in the adapter, or this will crash.
    public void setCurrentPage (View pageToShow)
    {
        pager.setCurrentItem (pagerAdapter.getItemPosition (pageToShow), true);
    }*/
}