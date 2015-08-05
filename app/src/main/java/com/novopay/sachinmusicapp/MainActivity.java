package com.novopay.sachinmusicapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.sachinmusicapp.events.SeekbarForwarEvent;
import com.novopay.sachinmusicapp.events.SeekbarRewindEvent;
import com.novopay.sachinmusicapp.events.SeekbarStopEvent;
import com.novopay.sachinmusicapp.events.SeekbarUpdatingEvent;
import com.novopay.sachinmusicapp.services.MusicService;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {
    private ImageView mPlaybutton;
    private ImageView mPausebutton;
    private ImageView mRewindbutton;
    private ImageView mForwardbutton;
    private ImageView mStopbutton;
    private ImageView mIncreasebutton;
    private ImageView mDecresebutton;
    private ImageView mPoster;
    private TextView songName;
    private SeekBar seekBar;
    private MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MusicService.eventBus.register(this);
        Intent intent = getIntent();
        String album = intent.getStringExtra("album");
        String artist = intent.getStringExtra("artist");
        String song = intent.getStringExtra("song");
        String image = intent.getStringExtra("image");

        final int song_id = this.getResources().getIdentifier(song,"raw",this.getPackageName());


        mPoster = (ImageView) findViewById(R.id.activity_main_images);
        mPlaybutton = (ImageView) findViewById(R.id.activity_main_play);
        mPausebutton = (ImageView) findViewById(R.id.activity_main_pause);
        mForwardbutton = (ImageView) findViewById(R.id.activity_main_forward);
        mRewindbutton = (ImageView) findViewById(R.id.activity_main_rewind);
        mStopbutton = (ImageView) findViewById(R.id.activity_main_stop);
        mIncreasebutton = (ImageView) findViewById(R.id.activity_main_increase_volume);
        mDecresebutton = (ImageView) findViewById(R.id.activity_main_decrease_volume);
        songName = (TextView) findViewById(R.id.activity_main_text);

        songName.setText(song);

        int image_id = this.getResources().getIdentifier(image, "raw", this.getPackageName());
        Picasso.with(this.getBaseContext()).load(image_id).into(mPoster);

        final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        seekBar = (SeekBar) findViewById(R.id.seek);

        mPlaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_PLAY);
                intent.putExtra("id", song_id);
                startService(intent);

            }
        });

        mPausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_PAUSE);
                intent.putExtra("id",song_id);
                startService(intent);
            }
        });


        mForwardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Forward is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_FF);
                intent.putExtra("id", song_id);
                startService(intent);
            }
        });

        mRewindbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rewind is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_RW);
                intent.putExtra("id", song_id);
                startService(intent);

            }
        });

        mStopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Stop is clicked", Toast.LENGTH_SHORT).show();
                //mediaPlayer.seekTo(0);
                //if(mediaPlayer.isPlaying())
                 //   mediaPlayer.pause();
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(MusicService.KEY_METHOD,MusicService.METHOD_STOP);
                intent.putExtra("id", song_id);
                startService(intent);
            }
        });

        mIncreasebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Toast.makeText(MainActivity.this, "Increse Volume is clicked", Toast.LENGTH_SHORT).show();
                //mediaPlayer.setVolume(originalVolume+10,originalVolume+10);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume + 1, 0);
            }
        });

        mDecresebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                Toast.makeText(MainActivity.this, "Decrease Volume is clicked", Toast.LENGTH_SHORT).show();
                //mediaPlayer.setVolume(originalVolume+10,originalVolume+10);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume - 1, 0);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    MusicService.mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "OnStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "OnPause");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onEvent(SeekbarUpdatingEvent event){
        //seekBar.setProgress(100);
        seekBar.setMax(event.getDuration());
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
        //Toast.makeText(MainActivity.this, "event", Toast.LENGTH_SHORT).show();

    }

    public void onEvent(SeekbarStopEvent event){
        seekBar.setProgress(0);
    }

//    public void onEvent(SeekbarForwarEvent event){
//        seekBar.setProgress(0);
//    }
//
//    public void onEvent(SeekbarRewindEvent event){
//        seekBar.setProgress(0);
//    }

    public static int MESSAGE_WAKE_UP_AND_SEEK = 10;
    public static int MESSAGE_WAKE_UP_AND_STOP = 11;

    class MusicHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg){
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {
                if (MusicService.mediaPlayer != null) {
                    if (MusicService.mediaPlayer.isPlaying()) {
                        seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                    }
                }
            }
        }
    }
}
