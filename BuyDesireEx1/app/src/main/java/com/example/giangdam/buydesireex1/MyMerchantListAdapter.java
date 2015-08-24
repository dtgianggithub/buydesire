package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giangdam.retrofitmodel.Desire;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/11/2015.
 */
public class MyMerchantListAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<Desire> arrayList = null;
    int layoutId ;

    public MyMerchantListAdapter(Context context, int layoutId,ArrayList<Desire> arrayList){
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
        public ImageView imgstore;
        public TextView txtStoreName;
        public TextView txtDecription;
        public Button btnViewStore;
    }





    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.txtStoreName = (TextView)convertView.findViewById(R.id.txtStoreName);
            viewHolder.txtDecription = (TextView)convertView.findViewById(R.id.txtDecription);
            viewHolder.btnViewStore = (Button)convertView.findViewById(R.id.btnViewStore);
            viewHolder.imgstore = (ImageView)convertView.findViewById(R.id.imgstore);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.btnViewStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), StoreInfomationActivity.class);
                Bundle bundle = new Bundle();

                //Need to tranfer store name

                bundle.putDouble("Latitude", arrayList.get(position).getLatitude());
                bundle.putDouble("Longitude",arrayList.get(position).getLongitude());
                intent.putExtra("StoreInfoBundle",bundle);
                context.startActivity(intent);
            }
        });


        viewHolder.txtStoreName.setText("Store Name");
        viewHolder.txtDecription.setText(String.valueOf(arrayList.get(position).getLatitude()) +","+ String.valueOf(arrayList.get(position).getLongitude()) );

        return  convertView;
    }

}
