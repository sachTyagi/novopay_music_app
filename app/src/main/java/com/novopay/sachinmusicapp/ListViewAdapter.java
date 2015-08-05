package com.novopay.sachinmusicapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.sachinmusicapp.model.Music;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

/**
 * Created by sachintyagi on 8/4/15.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Music> listOfMusic;

    public ListViewAdapter(Context context, ArrayList<Music> listOfMusic) {
        this.context = context;
        this.listOfMusic = listOfMusic;
    }

    class ViewHolder{
        private TextView album;
        private TextView artist;
        private TextView song;
        private ImageView image_list;
    }



    @Override
    public int getCount() {
        return listOfMusic.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfMusic.get(position);
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
            view = inflater.inflate(R.layout.fragment_list_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.album = (TextView) view.findViewById(R.id.album);
            viewHolder.artist = (TextView) view.findViewById(R.id.artist);
            viewHolder.song = (TextView) view.findViewById(R.id.song);
            viewHolder.image_list = (ImageView) view.findViewById(R.id.image_list);

            view.setTag(viewHolder);
        }

        if(viewHolder==null)
            viewHolder = (ViewHolder) view.getTag();

        Music music = (Music) getItem(position);

        viewHolder.album.setText(music.getAlbum());
        viewHolder.artist.setText(music.getArtist());
        viewHolder.song.setText(music.getSong());
        String image = music.getImage();
        int image_id = context.getApplicationContext().getResources().getIdentifier(image,"raw",context.getPackageName());
        Picasso.with(context).load(image_id).into(viewHolder.image_list);

        return view;
    }
}
