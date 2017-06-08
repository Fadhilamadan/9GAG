package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Comment_Activity extends AppCompatActivity {

    public static RecyclerView rv;
    //public static RecycleAdapterComment adapterComment;


    public ViewPager vp;
    public static Comment_Activity instance = null;
    public static ArrayList<Comment> comments;
    EditText Cupuman;
    ArrayList<String> username;
    ArrayList<String> deskripsi;
/*
    public static Comment_Activity newInstance(ArrayList<Comment> prodsComment)  {
        Comment_Activity cf = new Comment_Activity();
        adapterComment = new RecycleAdapterComment(prodsComment);
        return cf;
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final ProductHelper product = new ProductHelper(getApplicationContext());
        //product.getWritableDatabase();
        //product.sqlInsertUsername("rama", "rama");
        //product.sqlInsertUsername("adit", "adit");



        product.getWritableDatabase();
        ArrayList<Comment> p = product.sqlSelectComment();
        for (int i = 0; i < p.size(); i++) {
            username.add(""+p.get(i).getUsername_id());
            deskripsi.add(p.get(i).getTesting());
            Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());
        }

        //final ArrayList<Username> pa = product.sqlSelectUsername();
        //for (int i = 0; i < pa.size(); i++) {
        //    Log.d("username cok", pa.get(i).getId() + ", " + pa.get(i).getUsername() + ", " + pa.get(i).getPassword());
        //}
        /*Cupuman = (EditText)findViewById(R.id.txtKomen);
        Button Komen = (Button) findViewById(R.id.btnComment);
        Komen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.getWritableDatabase();
                String value =Cupuman.getText().toString();
                product.sqlInsertComment(100,a,value);
                ArrayList<Comment> p = product.sqlSelectComment();
                for (int i = 0; i < p.size(); i++) {
                    Toast.makeText(getApplicationContext(),String.valueOf(p.get(i).getUsername_id()),Toast.LENGTH_LONG).show();
                Log.d("Comment cok", p.get(i).getId() + ", " + p.get(i).getUsername_id() + ", "+ p.get(i).getPosting_id()+"," + p.get(i).getTesting()+ ", " + p.get(i).getUpvite());}
            }
        });*/

    }
        /*ReadData rd = new ReadData(this.getApplicationContext());
        rd.execute("http://103.52.146.34/penir/penir08/comment.php");*/
    }

    /*public static void readDataFinish(Context context, String result,int posting) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        try {
            JSONObject json = new  JSONObject(result);
            JSONArray json2 = json.getJSONArray("comment");
            comments = new ArrayList<Comment>();
            for (int i = 0; i <json2.length(); i++) {
                JSONObject c = json2.getJSONObject(i);
                if(c.getInt("posting_id")==posting) {
                    int id = c.getInt("id");
                    int posting_id = c.getInt("posting_id");
                    String username = c.getString("username");
                    String comment = c.getString("comment");
                    comments.add(new Comment(id,posting_id, username, comment));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
