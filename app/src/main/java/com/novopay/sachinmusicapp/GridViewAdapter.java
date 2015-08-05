package com.novopay.sachinmusicapp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.novopay.sachinmusicapp.model.Music;

import java.util.ArrayList;

/**
 * Created by sachintyagi on 8/4/15.
 */
public class GridViewAdapter extends BaseAdapter {

    private ArrayList<Music> musicArrayList;
    Context context;

    public GridViewAdapter(Context context, ArrayList<Music> musicArrayList) {
        this.musicArrayList = musicArrayList;
        this.context = context;
    }

    class ViewHolder {
        private TextView album;
        private TextView artist;
        private TextView song;
    }

    @Override
    public int getCount() {
        return musicArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return musicArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.fragment_grid_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.album = (TextView) view.findViewById(R.id.album_grid);
            viewHolder.artist = (TextView) view.findViewById(R.id.artist_grid);
            viewHolder.song = (TextView) view.findViewById(R.id.song_grid);

            view.setTag(viewHolder);
        }

        if(viewHolder==null)
            viewHolder = (ViewHolder) view.getTag();
        Music music = (Music) getItem(position);

        viewHolder.album.setText(music.getAlbum());
        viewHolder.artist.setText(music.getArtist());
        viewHolder.song.setText(music.getSong());

        return view;
    }
}
