package com.example.giangdam.retrofitadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.DetailProductActivity;
import com.example.giangdam.buydesireex1.R;
import com.example.giangdam.buydesireex1.SaveDesireActivity;
import com.example.giangdam.buydesireex1.ViewInMapActivity;
import com.example.giangdam.retrofitmodel.Desire;
import com.google.android.gms.maps.MapFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class RetrofitProductAdater extends BaseAdapter {

    ArrayList<Desire> arrayList;
    Context context;
    int layoutId;
    int mPostion = -1;


    public RetrofitProductAdater(Context context, int layoutId, ArrayList<Desire> arrayList){
        super();
        this.context = context;
        this.layoutId  = layoutId;
        this.arrayList = arrayList;
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
        public ImageView imgProductImage;
        public TextView txtRetailerName;
        public ImageButton imgbtnDesires;
        public TextView txtDesiresCount;
        public ImageButton imgbtnLocal;
        public ImageButton imgbtnShoppingBag;
        public ImageLoader imageLoader;
        public DisplayImageOptions imageOptions;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layoutId, parent,false);
            viewHolder.txtRetailerName = (TextView)convertView.findViewById(R.id.txtRetailerName);
            viewHolder.txtDesiresCount = (TextView)convertView.findViewById(R.id.txtDesiresCount);
            viewHolder.imgProductImage = (ImageView)convertView.findViewById(R.id.imgProductImage);
            viewHolder.imgbtnDesires = (ImageButton)convertView.findViewById(R.id.imgbtnDesires);
            viewHolder.imgbtnLocal = (ImageButton)convertView.findViewById(R.id.imgbtnLocal);
            viewHolder.imgbtnShoppingBag = (ImageButton)convertView.findViewById(R.id.imgbtnShoppingBag);

            viewHolder.imageLoader = ImageLoader.getInstance();
            viewHolder.imageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                    .resetViewBeforeLoading(true).build();
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        mPostion = position;
       viewHolder.txtRetailerName.setText((CharSequence) arrayList.get(position).getRetailerName());
        viewHolder.txtDesiresCount.setText(String.valueOf(arrayList.get(position).getDesiresCount()));

        //set Image Product
        //new viewImageUrl(viewHolder.imgProductImage).execute(arrayList.get(position).getProductImage());
        viewHolder.imageLoader.displayImage(arrayList.get(position).getProductImage(),viewHolder.imgProductImage, viewHolder.imageOptions);
        viewHolder.imgProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send product
                Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("product", arrayList.get(position));
                intent.putExtra("product_bundle", bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }


        });

        viewHolder.imgbtnShoppingBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send product
                Intent intent = new Intent(context.getApplicationContext(), DetailProductActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("product", arrayList.get(position));
                intent.putExtra("product_bundle", bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


        viewHolder.imgbtnDesires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SaveDesireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


        viewHolder.imgbtnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new Activity with map

                Intent intent = new Intent(context.getApplicationContext(), ViewInMapActivity.class);
                Bundle bundle = new Bundle();

                bundle.putDouble("Latitude", arrayList.get(position).getLatitude());
                bundle.putDouble("Longitude",arrayList.get(position).getLongitude());
                intent.putExtra("StoreInfoBundle",bundle);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                if (ViewInMapActivity.googleMap != null) {
                    ViewInMapActivity.googleMap = ((MapFragment)ViewInMapActivity.visiblefragmentManager.findFragmentById(R.id.map)).getMap();
                    ViewInMapActivity.visiblefragmentManager.beginTransaction()
                            .remove(ViewInMapActivity.visiblefragmentManager.findFragmentById(R.id.map)).commit();
                    ViewInMapActivity.googleMap = null;
                }

                context.getApplicationContext().startActivity(intent);
            }
        });
        return  convertView;
    }




    private class viewImageUrl extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public viewImageUrl(ImageView bmImage){
            this.bmImage = bmImage;
        }


        @Override
        protected Bitmap doInBackground(String... params) {

            String urlDisplay = params[0];
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(urlDisplay).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }catch (RuntimeException e){
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            bmImage.setImageBitmap(bitmap);
        }
    }
}
