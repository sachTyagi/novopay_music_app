package com.novopay.sachinmusicapp.events;

/**
 * Created by sachintyagi on 8/5/15.
 */
public class SeekbarUpdatingEvent {
    private int duration;
    private int progress;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
