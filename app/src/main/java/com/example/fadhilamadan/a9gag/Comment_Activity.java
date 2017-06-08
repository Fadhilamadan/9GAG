package com.example.fadhilamadan.a9gag;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Comment_Activity extends AppCompatActivity {

    public ViewPager vp;
    public static Comment_Activity instance = null;
    public static ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_);
        instance = this;
        ReadData rd = new ReadData(this);
        rd.execute("http://103.52.146.34/penir/penir08/comment.php");
    }
    public static void readDataFinish(Context context, String result,int posting) {
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

    }

}
