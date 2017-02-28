package com.pez.audio_player_application.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pez.audio_player_application.R;


//__________________________________________________________________________

/**
 * @author nicolas
 * @date 28/02/17
 * ViewHolder is a custom object you create to hold a reference to the Views in your hierarchy.
 */
public class ViewHolder
{
    public TextView title;
    public TextView duration;
    public Button button;

    /**
     * Constructor
     *
     * @param view
     */
    public ViewHolder(View view)
    {
        this.title = (TextView) view.findViewById(R.id.TrackTitle);
        this.duration = (TextView) view.findViewById(R.id.TrackDuration);
//        this.button = (Button) view.findViewById(R.id.Play_button);
    }


}
