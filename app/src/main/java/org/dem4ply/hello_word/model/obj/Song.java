package org.dem4ply.hello_word.model.obj;

import android.database.Cursor;

import com.google.gson.Gson;

/**
 * Created by dem4ply on 08/02/2015.
 */
public class Song {
    public int id;
    public String name;
    public String genre;
    public float duration;

    public Song() {
        this.id = 0;
        this.name = "";
        this.genre = "";
        this.genre = "";
    }

    public Song(Cursor c) {
        int i_id = c.getColumnIndex("id");
        int i_name = c.getColumnIndex("name");
        int i_duration = c.getColumnIndex("duration");
        int i_genre = c.getColumnIndex("genre");
        this.id = c.getInt( i_id );
        this.name = c.getString( i_name );
        this.genre = c.getString( i_genre );
        this.duration = c.getFloat( i_duration );
    }

    public Song (int id, String name, String genre, float duration) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public Song (String name, String genre, float duration) {
        this.id = 0;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    public String toJSON() {
        return new Gson().toJson(this, Song.class);
    }


}
