package com.example.zaba37.dniotwartepb.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.model.DrawerItem;
import com.example.zaba37.dniotwartepb.model.Place;

import java.util.ArrayList;

/**
 * Created by zaba3 on 27.02.2016.
 */
public class VisitedPlaceAdapter extends BaseAdapter {
    private ArrayList<Place> visitedPlaceList;
    private Context context;

    public VisitedPlaceAdapter(Context context) {
        visitedPlaceList = new ArrayList<>();
        this.context = context;

        //only to test
        visitedPlaceList.add(new Place("place 1", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 2", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 3", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 4", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 5", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 6", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));
        visitedPlaceList.add(new Place("place 7", context.getResources().getDrawable(R.drawable.ic_navigation_black_24dp)));

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return visitedPlaceList.size();
    }

    @Override
    public Place getItem(int position) {
        return visitedPlaceList.get(position);
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

        Place place = visitedPlaceList.get(position);
        drawerHolder.image.setImageDrawable(place.getPlaceImage());
        drawerHolder.itemName.setText(place.getPlaceName());

        return view;
    }

    private static class ItemListHolder {
        TextView itemName;
        ImageView image;
    }
}
