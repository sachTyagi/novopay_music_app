package com.novopay.sachinmusicapp;

import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private Button mPlaybutton;
    private Button mPausebutton;
    private Button mRewindbutton;
    private Button mForwardbutton;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.a);

        mPlaybutton = (Button) findViewById(R.id.activity_main_play);
        mPausebutton = (Button) findViewById(R.id.activity_main_pause);
        mForwardbutton = (Button) findViewById(R.id.activity_main_forward);
        mRewindbutton = (Button) findViewById(R.id.activity_main_rewind);
        seekBar = (SeekBar) findViewById(R.id.seek);

        seekBar.setMax(mediaPlayer.getDuration());

        mPlaybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
            }
        });

        mPausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });


        mForwardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Forward is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
            }
        });

        mRewindbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rewind is clicked", Toast.LENGTH_SHORT).show();
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mediaPlayer.seekTo(progress);
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

    public static int MESSAGE_WAKE_UP_AND_SEEK = 10;
    public static int MESSAGE_WAKE_UP_AND_STOP = 11;

    class MusicHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg){
            if (msg.what == MESSAGE_WAKE_UP_AND_SEEK) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP_AND_SEEK, 200);
                    }
                }
            }
        }
    }
}
