package com.example.zaba37.dniotwartepb.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaba37.dniotwartepb.R;
import com.example.zaba37.dniotwartepb.model.DrawerItem;

import java.util.List;

/**
 * Created by zaba3 on 25.02.2016.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {
    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;

    public DrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DrawerItemHolder drawerHolder = new DrawerItemHolder();
        View view;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(layoutResID, parent, false);
        drawerHolder.itemName = (TextView) view.findViewById(R.id.drawerItemName);
        drawerHolder.icon = (ImageView) view.findViewById(R.id.drawerIcon);

        DrawerItem dItem = this.drawerItemList.get(position);
       // drawerHolder.icon.setImageDrawable(dItem.getImgRes());
        drawerHolder.itemName.setText(dItem.getItemName());

        return view;
    }

    private static class DrawerItemHolder {
        TextView itemName;
        ImageView icon;
    }
}
