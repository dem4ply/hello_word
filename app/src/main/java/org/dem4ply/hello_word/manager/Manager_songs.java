package org.dem4ply.hello_word.manager;

import android.content.Context;
import android.database.Cursor;

import org.dem4ply.hello_word.model.obj.Song;
import org.dem4ply.hello_word.tools.Sqlite;

import java.util.ArrayList;

/**
 * Created by dem4ply on 08/02/2015.
 */
public class Manager_songs {
    public static void save(Context context, Song song) {
        Sqlite query = new Sqlite(context);
        String sql_format = "insert or replace into song " +
                "(name, genre, duration) " +
                "values ('%s', '%s', %f);";
        query.Exec(String.format(sql_format,
                song.name, song.genre, song.duration
        ));
        query.close();
    }

    public static ArrayList< Song > query (Context context) {
        ArrayList< Song > result = new ArrayList< Song >();
        Sqlite query = new Sqlite(context);
        String sql = String.format(
                "SELECT * " +
                        "FROM song");
        Cursor c = query.Consul(sql);
        while (c.moveToNext()) {
            result.add(new Song( c ) );
        }
        c.close();
        query.close();
        return result;
    }
}
