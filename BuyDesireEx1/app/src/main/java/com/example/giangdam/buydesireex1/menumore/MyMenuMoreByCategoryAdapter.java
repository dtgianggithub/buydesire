package com.example.giangdam.buydesireex1.menumore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.R;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/10/2015.
 */
public class MyMenuMoreByCategoryAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;

    public MyMenuMoreByCategoryAdapter(Context context, int layoutId, ArrayList<String> arrayList){
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
        public ImageView imgmenumorecategory;
        public TextView lblmenumorecategory;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.lblmenumorecategory = (TextView)convertView.findViewById(R.id.lblmenumorecategory);
            viewHolder.imgmenumorecategory = (ImageView)convertView.findViewById(R.id.imgmenumorecategory);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.lblmenumorecategory.setText(arrayList.get(position));
        viewHolder.imgmenumorecategory.setImageResource(R.drawable.ic_welcome_male_on);

        /*
        switch (position){
            case 0:
                viewHolder.imgtypeaddfriendusing.setImageResource(R.drawable.ic_friend_facebook);
                break;
            case 1:

                viewHolder.imgtypeaddfriendusing.setImageResource(R.drawable.ic_friend_twitter);
                break;
            case 2:

                viewHolder.imgtypeaddfriendusing.setImageResource(R.drawable.ic_friend_google);
                break;
            case 3:
                viewHolder.imgtypeaddfriendusing.setImageResource(R.drawable.ic_friend_contacts);
                break;
            default:
                break;

        }

        */


        return  convertView;
    }


}
