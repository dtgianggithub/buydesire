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
 * Created by Giang.Dam on 8/10/2015.
 */
public class MyRecommendationsAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;

    public MyRecommendationsAdapter(Context context, int layoutId, ArrayList<String> arrayList){
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
        public TextView txtrecommendations;
        public ImageView imgarrowright;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.txtrecommendations = (TextView)convertView.findViewById(R.id.txtrecommendations);
            viewHolder.imgarrowright = (ImageView)convertView.findViewById(R.id.imgarrowright);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txtrecommendations.setText(arrayList.get(position));
        return  convertView;
    }


}
