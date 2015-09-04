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
public class MyContactUsAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;

    public MyContactUsAdapter(Context context, int layoutId, ArrayList<String> arrayList){
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
        public TextView lblContactUs;
        public ImageView imgarrowright;
        public ImageView imgImageContactUs;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.lblContactUs = (TextView)convertView.findViewById(R.id.lblContactUs);
            viewHolder.imgarrowright = (ImageView)convertView.findViewById(R.id.imgarrowright);
            viewHolder.imgImageContactUs = (ImageView)convertView.findViewById(R.id.imgImageContactUs);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.lblContactUs.setText(arrayList.get(position));
        switch (position){
            case 0:
                viewHolder.imgImageContactUs.setImageResource(R.drawable.ic_help_mail);
                break;
            case 1:
                viewHolder.imgImageContactUs.setImageResource(R.drawable.ic_help_post);
                break;
            case 2:
                viewHolder.imgImageContactUs.setImageResource(R.drawable.ic_help_call);
                break;
            case 3:
                viewHolder.imgImageContactUs.setImageResource(R.drawable.ic_help_tw);
                break;
            case 4:
                viewHolder.imgImageContactUs.setImageResource(R.drawable.ic_help_rate);
                break;
            default:
                break;
        }
        return  convertView;
    }


}
