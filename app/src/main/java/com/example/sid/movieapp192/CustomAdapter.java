package com.example.sid.movieapp192;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SID on 9/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> list;
    LayoutInflater mLayoutInflater;

    public CustomAdapter(Context context,ArrayList<Model> list){
        this.context=context;
        this.list=list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //inflating row layout
        convertView = mLayoutInflater.inflate(R.layout.row,null);
        TextView mName = (TextView) convertView.findViewById(R.id.tvMovieName);
        mName.setText(list.get(position).getmName());
        TextView mVotes = (TextView) convertView.findViewById(R.id.tvMovieVotes);
        mVotes.setText(list.get(position).getmVotes());
        TextView mId = (TextView) convertView.findViewById(R.id.tvMovieId);
        mId.setText(list.get(position).getmId());
        return convertView;
    }
}
