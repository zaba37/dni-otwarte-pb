package com.greenApp.zaba37.dzienotwartypb.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenApp.zaba37.dzienotwartypb.R;

import java.util.ArrayList;

/**
 * Created by zaba3 on 27.02.2016.
 */
public class VisitedPlaceAdapter extends BaseAdapter {
    private Context context;

    public VisitedPlaceAdapter(Context context) {
        this.context = context;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemListHolder drawerHolder = new ItemListHolder();
        View view;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.list_item_place, parent, false);
        drawerHolder.itemName = (TextView) view.findViewById(R.id.tvPlaceName);
        drawerHolder.image = (ImageView) view.findViewById(R.id.imgPlace);



        return view;
    }

    private static class ItemListHolder {
        TextView itemName;
        ImageView image;
    }
}
