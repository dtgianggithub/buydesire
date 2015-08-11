package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/4/2015.
 */
public class MyArrayAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;

    public MyArrayAdapter(Context context, int layoutId,ArrayList<String> arrayList){
        super();
        this.context =context;
        this.arrayList = arrayList;
        this.layoutId = layoutId;

    }




    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public static class ViewHolder{
        public ImageView img_menu;
        public TextView lbl_menu;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.lbl_menu = (TextView)convertView.findViewById(R.id.lbl_menu);
            viewHolder.img_menu = (ImageView)convertView.findViewById(R.id.img_menu);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.lbl_menu.setText(arrayList.get(position));


        switch (position){
            case 0:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_add);
                break;
            case 1:

                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_desire);
                break;
            case 2:

                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_recommended);
                break;
            case 3:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_gift);
                break;
            case 4:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_merchants);
                break;
            case 5:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_find_friends);
                break;
            case 6:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_invite_friends);
                break;
            case 7:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_shopping_bag);
                break;
            case 8:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_help);
                break;
            case 9:
                viewHolder.img_menu.setImageResource(R.drawable.ic_menu_settings);
                break;
            default:
                break;

        }


        return  convertView;
    }



}
