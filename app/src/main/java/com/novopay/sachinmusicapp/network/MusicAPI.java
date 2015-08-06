package com.novopay.sachinmusicapp.network;

import com.novopay.sachinmusicapp.model.MusicResponse;

import javax.security.auth.callback.Callback;

import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by sachintyagi on 8/6/15.
 */
public class MusicAPI {
    private static final String URL = "https://www.kimonolabs.com/api";
    private static MusicInterface musicInteface = null;

    public static MusicInterface getApi() {
        if(musicInteface == null)
        {
            RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(URL).build();
            musicInteface = restAdapter.create(MusicInterface.class);
        }
        return musicInteface;
    }

    public interface MusicInterface {
        @GET("/2uqcht9u?apikey=2zsLMw4QFsEvh9V8hzoqDe5cWwQGtvXD")
        void getMusicList(retrofit.Callback<MusicResponse> musicResponseCallback);

    }
}
