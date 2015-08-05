package com.novopay.sachinmusicapp.provider;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.database.Cursor;


/**
 * Created by sachintyagi on 8/5/15.
 */
public class MusicSqliteOpenHelper extends SQLiteOpenHelper{

    public static int VERSION = 1;
    public static String DATABASE_NAME="musicdb";

    public interface Tables{
        String MUSIC = "music";
    }

    public interface TableMusic{
        String ARTIST = "artist";
        String ALBUM = "album";
        String SONG = "song";
        String IMAGE = "image";
    }

    public MusicSqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    final String CREATE_TABLE_MUSIC = "create table " + Tables.MUSIC + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TableMusic.ALBUM + " TEXT NOT NULL,"
            + TableMusic.ARTIST + " TEXT NOT NULL,"
            + TableMusic.SONG + " TEXT NOT NULL,"
            + TableMusic.IMAGE + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MUSIC);
        ContentValues values = new ContentValues();
        values.put(TableMusic.ALBUM,"album-0");
        values.put(TableMusic.ARTIST,"Arjit Singh");
        values.put(TableMusic.SONG, "adhuri_kahani");
        values.put(TableMusic.IMAGE,"adhuri");
        db.insert(Tables.MUSIC, null, values);
        values.put(TableMusic.ALBUM, "album-1");
        values.put(TableMusic.ARTIST,"Mika Singh");
        values.put(TableMusic.SONG, "selfie");
        values.put(TableMusic.IMAGE,"selfie_poster");
        db.insert(Tables.MUSIC, null, values);
        values.put(TableMusic.ALBUM, "album-2");
        values.put(TableMusic.ARTIST,"Jassi Gill");
        values.put(TableMusic.SONG, "bapu_zimidar");
        values.put(TableMusic.IMAGE, "bapu");
        db.insert(Tables.MUSIC, null, values);
        values.put(TableMusic.ALBUM, "album-3");
        values.put(TableMusic.ARTIST,"Daljit");
        values.put(TableMusic.SONG, "patiala");
        values.put(TableMusic.IMAGE,"patiala_peg");
        db.insert(Tables.MUSIC, null, values);
        values.put(TableMusic.ALBUM, "album-4");
        values.put(TableMusic.ARTIST,"Ranjit Bawa");
        values.put(TableMusic.SONG, "yaari_chandigarh");
        values.put(TableMusic.IMAGE, "yaari");
        db.insert(Tables.MUSIC, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
