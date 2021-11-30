package com.test.tabsdemoapplicication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    ViewPagerAdapter viewPagerAdapter;
    private String selectedYear = "2020";
    private boolean isTabCreated = false;
    private List<String> years;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Call Api request
        sendApiRequest(selectedYear);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                sendApiRequest(years.get(position));


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });


    }

    private void sendApiRequest(String selectedYear) {
        ApiRequet requet = new ApiRequet() {
            @Override
            public void onSuccess(List<String> items) {

                if (!isTabCreated) {
                    // get all Years data
                    years = new ArrayList<>();
                    years.add("2020");
                    years.add("2021");

                    TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                            (tab, position) -> tab.setText("Year " + years.get(position))
                    );
                    tabLayoutMediator.attach();
                    isTabCreated = true;
                    FragmentA fragmentA = viewPagerAdapter.getFragmentAList().get(0);
                    List<Record> recordList = new ArrayList<>();
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    fragmentA.updateData(recordList);
                    Log.e("FragA inside tab", "" + fragmentA);
                } else {
                    FragmentA fragmentA = viewPagerAdapter.getFragmentAList().get(tabLayout.getSelectedTabPosition());
                    List<Record> recordList = new ArrayList<>();
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    recordList.add(new Record(selectedYear, "" + new Random().nextInt(100)));
                    fragmentA.updateData(recordList);
                    Log.e("FragA inside tab", "" + fragmentA);
                }
            }

            @Override
            public void onFail() {

            }
        };

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        requet.onSuccess(null);
    }


}
