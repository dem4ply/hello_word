package org.dem4ply.hello_word.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Sqlite extends SQLiteOpenHelper {
    static class CONST {
        static class tabla {
            static String SONG = "CREATE TABLE song (id integer NOT NULL PRIMARY KEY,name varchar(255) NOT NULL,genre varchar(255) NOT NULL, duration REAL NOT NULL);";
        }
        static String [] tablas = {tabla.SONG};

    }

    public Sqlite(Context context) {
        super(context, "main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        for (int i = 0; i < CONST.tablas.length; ++i)
        {
            db.execSQL(CONST.tablas[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }

    public Cursor Consul(String sql)
    {
        Log.v("Sqlite: consulta", sql);
        SQLiteDatabase db = getReadableDatabase();
        Cursor result = db.rawQuery(sql, null);
        //db.close();
        return result;
    }

    public void Exec(String sql)
    {
        Log.v("Sqlite: exec", sql);
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

}