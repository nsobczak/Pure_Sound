package com.pez.audio_player_application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pez.audio_player_application.AudioPlayerApplication;
import com.pez.audio_player_application.R;
import com.pez.audio_player_application.pojo.Track;
import com.pez.audio_player_application.utils.TimeUtilities;

import java.util.List;


//__________________________________________________________________________

/**
 * @author nicolas
 * @date 28/02/17
 */
public class TracksAdapter extends BaseAdapter {
    private List<Track> trackList;
    private final LayoutInflater layoutInflater;


    //__________________________________________________________________________
    public TracksAdapter(List<Track> newTrackList) {
        this.trackList = newTrackList;
        this.layoutInflater = LayoutInflater.from(AudioPlayerApplication.getContext());
    }


    @Override
    public int getCount() {
        int result = 0;
        if (this.trackList != null) {
            result = this.trackList.size();
        }
        return result;
    }


    @Override
    public Object getItem(int position) {
        Track result = null;
        if (this.trackList != null) {
            result = this.trackList.get(position);
        }
        return result;
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }


    //__________________________________________________________________________

    /**
     * getView, best method
     *
     * @param position
     * @param convertView
     * @param viewGroup
     * @return convertView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        TracksAdapterViewHolder holder;

        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.adapter_tracksfragment_customlayout, null);
            holder = new TracksAdapterViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TracksAdapterViewHolder) convertView.getTag();
        }

        //Get and set the current item
        final Track track = (Track) getItem(position);
        holder.title.setText(track.getName());
        holder.artist.setText(track.getArtist());
        holder.duration.setText(TimeUtilities.milliSecondsToString(track.getDuration()));

        //View with the right info
        return convertView;
    }


}
