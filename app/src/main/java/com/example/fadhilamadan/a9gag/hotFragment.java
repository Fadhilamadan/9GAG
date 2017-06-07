package com.example.fadhilamadan.a9gag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class hotFragment extends Fragment {
    public static RecyclerView rv;
    public static RecycleAdapter adapter;

    public static hotFragment newInstance(ArrayList<Product> prods)  {
        hotFragment hf = new hotFragment();
        adapter = new RecycleAdapter(prods);
        return hf;
    }

    public hotFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hot, container, false);
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycle);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
		return view;

    }

}


