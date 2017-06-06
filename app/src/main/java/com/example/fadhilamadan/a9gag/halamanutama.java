package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to quit?").setTitle("Exit");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
            }
        });
        builder.setNegativeButton("No",null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanutama);
        instance = this;

        ReadData rd = new ReadData(this);
        //rd.execute("http://192.168.0.11/penir/penir.php");
        rd.execute("http://penir.jitusolution.com");

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
       // setupViewPager(vp);

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

    /*private void setupViewPager(ViewPager vp) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        //adapter.addFragment(new hotFragment());

        hotFragment cd = new hotFragment();
        cd.newInstance(prods);
        adapter.addFragment(cd);

        adapter.addFragment(new trendingFragment());
        adapter.addFragment(new freshFragment());

        vp.setAdapter(adapter);
    }*/
    private  void setupViewPager() {
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
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        try {
            JSONObject json = new  JSONObject( result );
            JSONArray json2 = json.getJSONArray("product");
            prods = new ArrayList<Product>();
            for (int i = 0; i < json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                String name = c.getString("nama");
                int id = c.getInt("id");
                int harga = c.getInt("harga");
                String deskr = c.getString("deskripsi");
                prods.add(new Product(name, id, harga, deskr));
            }
           instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
