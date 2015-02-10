package org.dem4ply.hello_word;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import org.dem4ply.hello_word.manager.Manager_songs;
import org.dem4ply.hello_word.model.obj.Song;
import org.dem4ply.hello_word.model.obj.adapter.array.Array_adapter_songs;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        Button btn_search;
        ListView lst_song;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final PlaceholderFragment self = this;
            Log.v("onCreateView", "onCreateView");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            this.lst_song = (ListView) rootView.findViewById(R.id.lst_songs);
            this.btn_search = (Button) rootView.findViewById(R.id.btn_search);
            this.btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    self.click_search();
                }
            });
            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.v("onCreate", "onCreate");
            if (getArguments() != null) {
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            String json = "";

            ArrayList< Song > songs = Manager_songs.query(this.getActivity().getApplicationContext());
            for (Song song : songs) {
                json += song.toJSON();
            }
            Log.v("json", json);

            Array_adapter_songs adapter_songs = new Array_adapter_songs(this.getActivity(), songs);
            lst_song.setAdapter( adapter_songs );
        }

        public void click_search() {
            Intent intent = new Intent(this.getActivity().getApplicationContext(), search_song.class);
            startActivity(intent);
        }
    }
}
