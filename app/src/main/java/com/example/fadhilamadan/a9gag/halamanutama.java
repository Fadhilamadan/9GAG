package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class halamanutama extends AppCompatActivity {

    public ViewPager vp;
    public TabLayout tabs;
    public static ArrayList<Product> prods;
    public NavigationView nv;
    DrawerLayout dl;

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

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                dl = (DrawerLayout) findViewById(R.id.drawer);
                dl.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.itemProfil:
                        nv.getMenu().getItem(0).setChecked(true);
                        break;
                    case R.id.itemNews:
                        nv.getMenu().getItem(1).setChecked(true);
                        break;
                }

                return false;
            }
        });

        dl = (DrawerLayout) findViewById(R.id.drawer);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"FAB ditekan!", Snackbar.LENGTH_LONG).setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dl.openDrawer(GravityCompat.START);
                    }
                }).show();

            }
        });

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == item.getItemId()) {
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
