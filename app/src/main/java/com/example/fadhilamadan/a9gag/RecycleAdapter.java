package com.example.fadhilamadan.a9gag;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TextView nama = (TextView) holder.itemView.findViewById(R.id.txtNamaProduk);
        TextView harga = (TextView) holder.itemView.findViewById(R.id.txtHarga);
        TextView desk = (TextView) holder.itemView.findViewById(R.id.txtDeskrip);
        ImageView iv = (ImageView) holder.itemView.findViewById(R.id.imgLogo);
        nama.setText(productList.get(position).getNama());
        harga.setText("Rp. " + productList.get(position).getHarga());
        desk.setText( productList.get(position).getDeskripsi());

        URL url = null;
        try {
            url = new URL("http://103.52.146.34/penir/penir08/img/trending/" + productList.get(position).getId() + ".jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            iv.setImageBitmap(bmp);

        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageButton komen = (ImageButton) holder.itemView.findViewById(R.id.imgComment);
        komen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lol = new Intent(v.getContext(),KomenActivity.class);
                String a = ""+productList.get(position).getId();
                //Log.d("hanya", a);
                lol.putExtra("abc",a);
                v.getContext().startActivity(lol);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}