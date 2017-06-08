package com.example.fadhilamadan.a9gag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class trendingFragment extends Fragment {
    public static RecyclerView rv;
    public static RecycleAdapterTrending adapterTrending;


    public trendingFragment() {
        // Required empty public constructor
    }
    public static trendingFragment newInstance(ArrayList<Product> prodsTrend) {
        trendingFragment td = new trendingFragment();
        adapterTrending = new RecycleAdapterTrending(prodsTrend);
        return td;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_card_content2, container, false);
        View view = inflater.inflate(R.layout.fragment_fresh, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycler);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapterTrending);
        return view;
    }

}
