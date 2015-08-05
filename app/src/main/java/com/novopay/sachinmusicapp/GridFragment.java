package com.novopay.sachinmusicapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.novopay.sachinmusicapp.model.Music;

import java.util.ArrayList;



/**
 * Created by sachintyagi on 8/4/15.
 */
public class GridFragment extends Fragment {
    private GridView gridView;
    GridViewAdapter gridViewAdapter;
    ArrayList<Music> musicArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        musicArrayList.add(new Music("album0","sachin","song","images"));
        musicArrayList.add(new Music("album1","sachin","song","images"));
        musicArrayList.add(new Music("album2","sachin","song","images"));
        musicArrayList.add(new Music("album0","sachin","song","images"));
        musicArrayList.add(new Music("album1","sachin","song","images"));
        View view = inflater.inflate(R.layout.grid_fragment,container, false);
        gridView = (GridView) view.findViewById(R.id.fragment_list_gridview);
        gridViewAdapter = new GridViewAdapter(getActivity(),musicArrayList);
        gridView.setAdapter(gridViewAdapter);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
