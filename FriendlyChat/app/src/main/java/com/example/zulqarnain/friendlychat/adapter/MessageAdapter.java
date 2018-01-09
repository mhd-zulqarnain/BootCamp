package com.example.zulqarnain.friendlychat.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zulqarnain.friendlychat.R;
import com.example.zulqarnain.friendlychat.model.FriendlyMessage;

import java.util.ArrayList;

/**
 * Created by Zul Qarnain on 11/2/2017.
 */

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    public MessageAdapter(Context context, int resource, ArrayList<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View viewItems, @NonNull ViewGroup parent) {
        if (viewItems == null) {
            viewItems = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_messege, parent, false);
        }

        ImageView photoImageView = viewItems.findViewById(R.id.photoImageView);
        TextView messegeView = viewItems.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) viewItems.findViewById(R.id.nameTextView);
        FriendlyMessage message=getItem(position);
        boolean isPhoto = message.getPhotoUrl()!=null;

        if(isPhoto){
            
        }
        return viewItems;

    }
}
