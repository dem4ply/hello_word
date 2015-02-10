package org.dem4ply.hello_word;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import org.dem4ply.hello_word.model.obj.Song;
import org.dem4ply.hello_word.model.obj.adapter.array.Array_adapter_songs;
import org.dem4ply.hello_word.tools.Http;

import java.util.ArrayList;


public class search_song extends ActionBarActivity {

    Button btn_search_again;
    ListView lst_songs;
    ProgressBar pgb_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_song);
        btn_search_again = (Button) findViewById(R.id.btn_search_again);
        btn_search_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgb_loading.setVisibility( View.VISIBLE );
                new tsk_Search_song().execute();
            }
        });
        lst_songs = (ListView) findViewById(R.id.lst_songs);
        pgb_loading = (ProgressBar) findViewById(R.id.pgb_loading);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_song, menu);
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

    public void refresh_list_songs(String json) {
        pgb_loading.setVisibility( View.GONE );
        Song[] songs = new Gson().fromJson(json, Song[].class);
        ArrayList<Song> a_songs = new ArrayList<Song>();
        for (Song song : songs) {
            a_songs.add(song);
        }
        Array_adapter_songs adapter_songs = new Array_adapter_songs( this, a_songs);
        lst_songs.setAdapter( adapter_songs );
    }

    private class tsk_Search_song extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String result;
            result = Http.get("http://ip.jsontest.com/");
            Log.v("result", result);
            result = "[{\"name\":\"name_1\",\"genre\":\"rock\",\"duration\":13.5},{\"name\":\"no more sorrow\",\"genre\":\"rock\",\"duration\":80},{\"name\":\"dream on\",\"genre\":\"rock\",\"duration\":90}]";
            return result;
        }

        protected void onPostExecute(String json) {
            refresh_list_songs(json);
        }
    }
}
