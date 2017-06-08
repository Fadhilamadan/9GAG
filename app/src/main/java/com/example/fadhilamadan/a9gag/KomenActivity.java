package com.example.fadhilamadan.a9gag;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class KomenActivity extends ListActivity {


    ArrayList<Comment> p;
    ArrayList<String> komentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen);
        final ProductHelper product = new ProductHelper(getApplicationContext());
        product.getWritableDatabase();
        komentar = new ArrayList<String>();
        ArrayList<Comment> p = product.sqlSelectComment();
        for (int i = 0; i < p.size(); i++) {
            //Toast.makeText(getApplicationContext(),String.valueOf(p.get(i).getUsername_id()),Toast.LENGTH_LONG).show();
            //Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());
            komentar.add(""+p.get(i).getId()+" : "+p.get(i).getTesting());
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,komentar);
        setListAdapter(aa);
    }
}
