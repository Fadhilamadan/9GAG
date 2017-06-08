package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.content.Intent;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class halamanutama extends AppCompatActivity {
    public ViewPager vp;
    public TabLayout tabs;
    public NavigationView nv;
    public DrawerLayout dl;
    public static ArrayList<Product> prods;
    public static ArrayList<Product> prodsTrending;
    public static ArrayList<Product> prodsFresh;
    public static halamanutama instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanutama);
        instance = this;

        //ProductHelper product = new ProductHelper(getApplicationContext());
        //product.getWritableDatabase();
        //product.sqlInsert("JAM KAKI", "JAM YANG DIPASANG DI KAKI", 350000);
        //product.sqlInsert("Jam Kaki", "Jam fashion yang dipasang di kaki", 350000);
        //product.sqlInsert("Baju Kebalik", "Baju seragam yang kancingnya di punggung", 120000);
        //product.sqlInsert("Wig Transparan", "Rambut palsu tidak terlihat", 470000);

        //ini toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ini utk baca JSON
        ReadData rd = new ReadData(this);
        rd.execute("http://103.52.146.34/penir/penir08/picthot.php");

        ReadData rd2 = new ReadData(this);
        rd2.execute("http://103.52.146.34/penir/penir08/picttrend.php");
        ReadData rd3 = new ReadData(this);
        rd3.execute("http://103.52.146.34/penir/penir08/pictfresh.php");

        vp = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(vp);
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

        nv = (NavigationView) findViewById(R.id.nav_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DrawerLayout dl = (DrawerLayout) findViewById(R.id.drawer);
                dl.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.itemProfil:
                        nv.getMenu().getItem(0).setChecked(true);
                        break;
                    case R.id.itemNews:
                        nv.getMenu().getItem(1).setChecked(true);
                        break;
                    case R.id.itemSetting:
                        nv.getMenu().getItem(3).setChecked(true);
                        vp.setCurrentItem(1);
                }
                return false;
            }
        });

        //ini button floating
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"FAB ditekan",Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        });

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!= null){
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        dl = (DrawerLayout) findViewById(R.id.drawer);
        int id = item.getItemId();
        if (id == android.R.id.home){
            dl.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager () {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        hotFragment cd = new hotFragment();
        cd.newInstance(prods);
        adapter.addFragment(cd);

        trendingFragment td = new trendingFragment();
        td.newInstance(prodsTrending);
        adapter.addFragment(td);

        trendingFragment fd = new trendingFragment();
        fd.newInstance(prodsFresh);
        adapter.addFragment(fd);

        //adapter.addFragment(new trendingFragment());
        adapter.addFragment(new freshFragment());

        //adapter.addFragment(new CardContentFragment());
        //adapter.addFragment(new InboxFragment());

        vp.setAdapter(adapter);
    }

    //region read data utk gambar hot
    public  void readDataFinish(Context context, String result) {
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("picthot");
            prods = new ArrayList<Product>();
            for (int i = 0; i <json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                String name = c.getString("nama");
                int id = c.getInt("id");
                int harga = c.getInt("harga");
                String deskrip = c.getString("deskripsi");
                prods.add(new Product(name,id,harga,deskrip));
            }
            instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region read data utk gambar trending
    public  void readDataFinishTrending(Context context, String result) {
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("picttrend");
            prodsTrending = new ArrayList<Product>();
            for (int i = 0; i <json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                String name = c.getString("nama");
                int id = c.getInt("id");
                int harga = c.getInt("harga");
                String deskrip = c.getString("deskripsi");
                prodsTrending.add(new Product(name,id,harga,deskrip));
            }
            instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  void readDataFinishFresh(Context context, String result) {
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("pictfresh");
            prodsFresh = new ArrayList<Product>();
            for (int i = 0; i <json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                String name = c.getString("nama");
                int id = c.getInt("id");
                int harga = c.getInt("harga");
                String deskrip = c.getString("deskripsi");
                prodsFresh.add(new Product(name,id,harga,deskrip));
            }
            instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //ini method utk read comment
    public  void readDataFinishComment(Context context, String result) {
        //Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        /*try {
            JSONObject json = new JSONObject(result);
            JSONArray json2 = json.getJSONArray("picttrend");
            prodsTrending = new ArrayList<Product>();
            for (int i = 0; i <json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                String name = c.getString("nama");
                int id = c.getInt("id");
                int harga = c.getInt("harga");
                String deskrip = c.getString("deskripsi");
                prodsTrending.add(new Product(name,id,harga,deskrip));
            }
            instance.setupViewPager();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }

}
