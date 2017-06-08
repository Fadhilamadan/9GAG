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

    public static trendingFragment newInstance(ArrayList<Product> prodsTrending)  {
        trendingFragment trendFract = new trendingFragment();
        adapterTrending = new RecycleAdapterTrending(prodsTrending);
        return trendFract;
    }

    public trendingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        rv = (RecyclerView) view.findViewById(R.id.recycletrending);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapterTrending);
		return view;
    }

}
