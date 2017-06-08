package com.example.fadhilamadan.a9gag;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class KomenActivity extends AppCompatActivity {


    ArrayList<Comment> p;
    ArrayList<String> komentar;
    ListView lv;
    EditText Cupuman;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_komen);
        Intent intent = getIntent();
        a = intent.getStringExtra("abc");

        final ProductHelper product = new ProductHelper(getApplicationContext());
        product.getWritableDatabase();
        komentar = new ArrayList<String>();
        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_LONG).show();
        ArrayList<Comment> p = product.sqlSelectCommentParam(a);
        for (int i = 0; i < p.size(); i++) {
            //Toast.makeText(getApplicationContext(),String.valueOf(p.get(i).getUsername_id()),Toast.LENGTH_LONG).show();
            //Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());
            komentar.add(""+p.get(i).getId()+" : "+p.get(i).getTesting());
        }
        lv = (ListView) findViewById(R.id.lstComment);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,komentar);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, komentar);
        lv.setAdapter(arrayAdapter);
        Cupuman = (EditText)findViewById(R.id.txtKomen);
        ImageButton Komen = (ImageButton) findViewById(R.id.btnKomen);
        Komen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.getWritableDatabase();
                String value =Cupuman.getText().toString();
                product.sqlInsertComment(1,a,value);
                ArrayList<Comment> p = product.sqlSelectComment();
                refresh(a);
            }
        });
    }
    public void refresh(String a){
        final ProductHelper product = new ProductHelper(getApplicationContext());
        product.getWritableDatabase();
        komentar = new ArrayList<String>();
        ArrayList<Comment> p = product.sqlSelectCommentParam(a);
        for (int i = 0; i < p.size(); i++) {
            //Toast.makeText(getApplicationContext(),String.valueOf(p.get(i).getUsername_id()),Toast.LENGTH_LONG).show();
            //Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());
            komentar.add(""+p.get(i).getId()+" : "+p.get(i).getTesting());
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,komentar);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, komentar);
        lv.setAdapter(arrayAdapter);
    }
}
