package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class halamanutama extends AppCompatActivity {
    public ViewPager vp;
    public TabLayout tabs;
    public static ArrayList<Product> prods;

    public static halamanutama instance = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanutama);
        instance = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = tabs.getTabAt(position);
                tab.select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(vp);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager vp) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        //adapter.addFragment(new hotFragment());

        hotFragment cd = new hotFragment();
        cd.newInstance(prods);
        adapter.addFragment(cd);

        adapter.addFragment(new trendingFragment());
        adapter.addFragment(new freshFragment());

        vp.setAdapter(adapter);
    }

    public static void readDataFinish (Context context, String result) {
        try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("product");
            prods = new ArrayList<Product>();
            for (int i = 0; i < json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                int id = c.getInt("id");
                String name = c.getString("username");
                String password = c.getString("password");
                prods.add(new Product( id, name, password));
            }
            //instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
