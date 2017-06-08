package com.example.fadhilamadan.a9gag;

<<<<<<< HEAD
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
=======
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
>>>>>>> 923b4cfd2733e0436c8024eaf1c97053c3f8287b

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
<<<<<<< HEAD
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

=======
        instance = this;
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
>>>>>>> 923b4cfd2733e0436c8024eaf1c97053c3f8287b
}
