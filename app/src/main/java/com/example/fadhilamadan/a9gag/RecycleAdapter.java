package com.example.fadhilamadan.a9gag;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Sonny on 6/5/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Product> productList;

    RecycleAdapter (List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent, false);

        RecyclerView.ViewHolder vhold = new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
        return vhold;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView username = (TextView) holder.itemView.findViewById(R.id.txtUsername);
        TextView password = (TextView) holder.itemView.findViewById(R.id.txtPassword);
        username.setText(productList.get(position).getUsername());
        password.setText(productList.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
