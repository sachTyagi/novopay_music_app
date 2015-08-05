package com.novopay.sachinmusicapp.services;

import android.app.Service;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.novopay.sachinmusicapp.MainActivity;
import com.novopay.sachinmusicapp.events.SeekbarStopEvent;
import com.novopay.sachinmusicapp.events.SeekbarUpdatingEvent;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by sachintyagi on 8/5/15.
 */
public class MusicService extends Service {
    public static MediaPlayer mediaPlayer=null;
    public static final String KEY_METHOD = "method";
    public static final String METHOD_PLAY = "method_paly";
    public static final String METHOD_STOP = "method_stop";
    public static final String METHOD_FF = "method_ff";
    public static final String METHOD_RW = "method_rw";
    public static final String METHOD_PAUSE = "method_pause";
    public static final EventBus eventBus = new EventBus();
    private static int current_song = -1;



    public static int getCurrentPosition(){
        if(mediaPlayer!=null && mediaPlayer.isPlaying())
            return mediaPlayer.getCurrentPosition();
        return -1;
    }

    public static int getDuration(){
        if(mediaPlayer!=null)
            return mediaPlayer.getDuration();
        return -1;
    }

    @Nullable
    @Override
    @DebugLog
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    @DebugLog
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    @DebugLog
    public void onCreate() {
        super.onCreate();
    }

    @DebugLog
    private void playMediaPlayer(int song_id)
    {
        //Toast.makeText(this, song_id, Toast.LENGTH_SHORT).show();

        if(mediaPlayer==null || (current_song!=song_id && mediaPlayer!=null)) {
            if(mediaPlayer!=null && current_song!=song_id)
                mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, song_id);
            current_song=song_id;
        }

        mediaPlayer.start();

        SeekbarUpdatingEvent seekbarUpdatingEvent = new SeekbarUpdatingEvent();
        seekbarUpdatingEvent.setProgress(mediaPlayer.getCurrentPosition());
        seekbarUpdatingEvent.setDuration(mediaPlayer.getDuration());

        eventBus.post(seekbarUpdatingEvent);
        //musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP_AND_SEEK);
    }

    private void pauseMediaPlayer()
    {
        if(mediaPlayer!=null)
        mediaPlayer.pause();
    }

    private void stopMediaPlayer()
    {
        if(mediaPlayer!=null) {
            mediaPlayer.stop();
            current_song = -1;
        }
        SeekbarStopEvent seekbarStopEvent = new SeekbarStopEvent();
        seekbarStopEvent.setPosition(mediaPlayer.getCurrentPosition());
        eventBus.post(seekbarStopEvent);
//        if(mediaPlayer==null)
//            Toast.makeText(this, "media player is null", Toast.LENGTH_SHORT).show();
//        else
//            Toast.makeText(this, "media player in not null "+mediaPlayer.toString() , Toast.LENGTH_SHORT).show();
    }

    private void ffMediaPlayer()
    {
        if(mediaPlayer!=null)
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
    }

    private void rrMediaPlayer()
    {
        if(mediaPlayer!=null)
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
    }

    @Override
    @DebugLog
    public int onStartCommand(Intent intent, int flags, int startId) {

        String method = intent.getStringExtra(KEY_METHOD);
        int song_id = intent.getIntExtra("id",-1);


        if(method.equals(METHOD_PLAY))
            playMediaPlayer(song_id);
        else if (method.equals(METHOD_PAUSE))
            pauseMediaPlayer();
        else if (method.equals(METHOD_STOP))
            stopMediaPlayer();
        else if (method.equals(METHOD_FF))
            ffMediaPlayer();
        else if (method.equals(METHOD_RW))
            rrMediaPlayer();

        return super.onStartCommand(intent, flags, startId);
    }
}
