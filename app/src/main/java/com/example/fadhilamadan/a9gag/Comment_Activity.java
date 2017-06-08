package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Comment_Activity extends AppCompatActivity {

    public ViewPager vp;
    public static Comment_Activity instance = null;
    public static ArrayList<Comment> comments;
    EditText Cupuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

         final ProductHelper product = new ProductHelper(getApplicationContext());
        product.getWritableDatabase();
        /*product.sqlInsertUsername("rama", "rama");
        product.sqlInsertUsername("adit", "adit");*/

        final ArrayList<Username> p = product.sqlSelectUsername();
        for (int i = 0; i < p.size(); i++) {
            Log.d("username cok", p.get(i).getId() + ", " + p.get(i).getUsername() + ", " + p.get(i).getPassword());
        }
        Cupuman = (EditText)findViewById(R.id.txtKomen);
        Button Komen = (Button) findViewById(R.id.btnComment);
        Komen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.getWritableDatabase();
                String value =Cupuman.getText().toString();
                product.sqlInsertComment(50,50,value);
                ArrayList<Comment> p = product.sqlSelectComment();
                for (int i = 0; i < p.size(); i++) {
                    Toast.makeText(getApplicationContext(),String.valueOf(p.get(i).getUsername_id()),Toast.LENGTH_LONG).show();
                Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());}
            }
        });

    }

}
