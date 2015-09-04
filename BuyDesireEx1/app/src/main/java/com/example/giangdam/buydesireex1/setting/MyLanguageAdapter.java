package com.example.giangdam.buydesireex1.setting;

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
public class MyLanguageAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;

    public MyLanguageAdapter(Context context, int layoutId, ArrayList<String> arrayList){
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
        public ImageView imgSelectLanguage;
        public TextView lblLanguage;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.lblLanguage = (TextView)convertView.findViewById(R.id.lblLanguage);
            viewHolder.imgSelectLanguage = (ImageView)convertView.findViewById(R.id.imgSelectLanguage);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.lblLanguage.setText(arrayList.get(position));

        //get select from sharepreference..... make that selection id default

        return  convertView;
    }


}
