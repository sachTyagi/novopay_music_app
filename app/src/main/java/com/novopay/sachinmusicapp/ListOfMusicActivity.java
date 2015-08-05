package com.novopay.sachinmusicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.sachinmusicapp.services.MusicService;
import com.squareup.picasso.Picasso;

/**
 * Created by sachintyagi on 8/4/15.
 */
public class ListOfMusicActivity extends FragmentActivity{

    private ViewPager viewPager;

    private final int NUMBER_OF_PAGES = 2;

    private MusicListFragmentStatePagerAdapter musicListFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager) findViewById(R.id.activity_viewpager_viewpager);
        musicListFragmentStatePagerAdapter = new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicListFragmentStatePagerAdapter);
    }

    public void setBottomLayout(final String song_name, final int song_id, final String album, final String artist, final String image){
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.bottom_bar);
        rl.setVisibility(View.VISIBLE);
        final ImageView imageView = (ImageView) findViewById(R.id.bottom_play_pause_button);
        imageView.setImageResource(R.drawable.pause);
        imageView.setTag(R.drawable.pause);
        //Picasso.with(this.getBaseContext()).load(R.drawable.pause).into(imageView);
        final TextView song = (TextView) findViewById(R.id.display_song);
        song.setText(song_name);

        song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MainActiv.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListOfMusicActivity.this, MainActivity.class);
                intent.putExtra("album", album);
                intent.putExtra("artist", artist);
                intent.putExtra("song", song_name);
                intent.putExtra("image", image);
                startActivity(intent);

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ListOfMusicActivity.this, imageView.getTag().toString(), Toast.LENGTH_SHORT).show();
                System.out.println("IMAGE VIEW "+(Integer)imageView.getTag());
                System.out.println("IRESOURCE "+R.drawable.pause);

                if((Integer)imageView.getTag() == R.drawable.pause) {
                    Toast.makeText(ListOfMusicActivity.this, "Getting inside if", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.play);
                    imageView.setTag(R.drawable.play);
                    Intent intent = new Intent(ListOfMusicActivity.this, MusicService.class);
                    intent.putExtra(MusicService.KEY_METHOD, MusicService.METHOD_PAUSE);
                    intent.putExtra("id", song_id);
                    startService(intent);
                }
                else
                {
                    Toast.makeText(ListOfMusicActivity.this, "Getting inside else", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.pause);
                    imageView.setTag(R.drawable.pause);
                    Intent intent = new Intent(ListOfMusicActivity.this, MusicService.class);
                    intent.putExtra(MusicService.KEY_METHOD, MusicService.METHOD_PLAY);
                    intent.putExtra("id", song_id);
                    startService(intent);
                }

            }
        });
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0:
                    return new ListFragment();
                case 1:
                    return new GridFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
