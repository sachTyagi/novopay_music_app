package com.novopay.sachinmusicapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.sachinmusicapp.model.Music;
import com.novopay.sachinmusicapp.provider.MusicSqliteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

/**
 * Created by sachintyagi on 8/4/15.
 */
public class ListFragment extends Fragment {
    private ListView listView;
    ListViewAdapter listViewAdapter;
    ArrayList<Music> musicArrayList = new ArrayList<>();


    @Nullable
    @Override
    @DebugLog
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        musicArrayList.add(new Music("album-0","Arjit Singh","adhuri_kahani","adhuri"));
        musicArrayList.add(new Music("album-1","Mika Singh","selfie","selfie_poster"));
        musicArrayList.add(new Music("album-2","Jassi Gill","bapu_zimidar","bapu"));
        musicArrayList.add(new Music("album-3","Daljit","patiala","patiala_peg"));
        musicArrayList.add(new Music("album-4","Ranjit Bawa","yaari_chandigarh","yaari"));
        View view = inflater.inflate(R.layout.list_fragment, container,false);
        listView = (ListView) view.findViewById(R.id.fragment_list_listview);
        listViewAdapter = new ListViewAdapter(getActivity(),musicArrayList);
        listView.setAdapter(listViewAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music itemClicked = (Music) listView.getItemAtPosition(position);
                String album = (String) itemClicked.getAlbum();
                String artist = (String) itemClicked.getArtist();
                String song = (String) itemClicked.getSong();
                String image = (String) itemClicked.getImage();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("album", album);
                intent.putExtra("artist", artist);
                intent.putExtra("song", song);
                intent.putExtra("image", image);
                startActivity(intent);
            }
        });
        MusicSqliteOpenHelper musicSqliteOpenHelper = new MusicSqliteOpenHelper(getActivity());
        SQLiteDatabase sqLiteDatabase = musicSqliteOpenHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(MusicSqliteOpenHelper.Tables.MUSIC, null, null, null, null, null, null);

        System.out.println(cursor.getCount());

//        if(cursor!=null)
//        {
//            cursor.moveToFirst();
//            do{
//                String image = cursor.getString(cursor.getColumnIndex(MusicSqliteOpenHelper.TableMusic.IMAGE));
//            }while (cursor.moveToNext());
//        }
            return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
