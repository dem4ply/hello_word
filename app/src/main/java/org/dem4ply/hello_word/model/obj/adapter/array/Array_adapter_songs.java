package org.dem4ply.hello_word.model.obj.adapter.array;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.dem4ply.hello_word.R;
import org.dem4ply.hello_word.model.obj.Song;

import java.util.ArrayList;

/**
 * Created by dem4ply on 09/02/2015.
 */
public class Array_adapter_songs extends ArrayAdapter<Song> {

    static class View_holder {
        public TextView txt_name;
        public TextView txt_genre;
        public TextView txt_duration;
    }

    protected Activity _context;

    public Array_adapter_songs(Activity context, ArrayList<Song> songs) {
        super(context, R.layout.activity_main, songs );
        this._context = context;
    }

    @Override
    public View getView(final int posicion, View contentView, ViewGroup parent)
    {
        View item = contentView;
        View_holder holder;

        if (item == null) {
            LayoutInflater inflater = this._context.getLayoutInflater();
            item = inflater.inflate(R.layout.fragment_elem_list_song, null);

            holder = new View_holder();
            holder.txt_name = (TextView) item.findViewById(R.id.txt_name);
            holder.txt_genre= (TextView) item.findViewById(R.id.txt_genre);
            holder.txt_duration = (TextView) item.findViewById(R.id.txt_duration);

            item.setTag(holder);
        }
        else {
            holder = (View_holder) item.getTag();
        }

        final Song song = getItem(posicion);

        holder.txt_name.setText(song.name );
        holder.txt_genre.setText(song.genre );
        holder.txt_duration.setText( String.format("%f", song.duration) );

        return item;
    }

}
