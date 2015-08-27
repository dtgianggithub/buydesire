package com.example.giangdam.buydesireex1.savedesire;

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
public class MySaveDesireAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<String> arrayList = null;
    int layoutId ;
    int type = -1;

    public MySaveDesireAdapter(Context context, int layoutId, ArrayList<String> arrayList, int type){
        super();
        this.context =context;
        this.arrayList = arrayList;
        this.layoutId = layoutId;
        this.type = type;

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
        public ImageView imgsavedesire;
        public TextView lblsavedesiretitle;
        public ImageView imgsavedesirechoosen;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.lblsavedesiretitle = (TextView)convertView.findViewById(R.id.lblsavedesiretitle);
            viewHolder.imgsavedesire = (ImageView)convertView.findViewById(R.id.imgsavedesire);
            viewHolder.imgsavedesirechoosen = (ImageView)convertView.findViewById(R.id.imgsavedesirechoosen);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.lblsavedesiretitle.setText(arrayList.get(position));
        switch (type){
            case 0:
                viewHolder.imgsavedesire.setImageResource(R.drawable.listsavedesire);
                viewHolder.imgsavedesirechoosen.setImageResource(R.drawable.ic_save_desirelist_selete);
                break;
            case 1:
                switch (position){
                    case 0:
                        viewHolder.imgsavedesire.setImageResource(R.drawable.myfriend);
                        viewHolder.imgsavedesirechoosen.setImageResource(R.drawable.ic_save_desirelist_selete);
                        break;
                    case 1:
                        viewHolder.imgsavedesire.setImageResource(R.drawable.everyone);
                        break;
                    case 2:
                        viewHolder.imgsavedesire.setImageResource(R.drawable.onlyme);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }


        return  convertView;
    }


}
