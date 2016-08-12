package com.example.hp.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by HP on 12/08/2016.
 */
public class DFragment extends Fragment {

    FragmenPagerAdapter adapter;
    ViewPager pager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.view_4, container, false );

        Button b = (Button) v.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = pager.getCurrentItem();

                adapter.removeFragment(pager, 0);
                adapter.notifyDataSetChanged();

                pager.setCurrentItem(i-1);
            }
        });

        return v;
    }

    public void setAdapter(FragmenPagerAdapter adapter){
         this.adapter = adapter;
    }

    public void setViewPager(ViewPager v)
    {
        this.pager = v;
    }
}