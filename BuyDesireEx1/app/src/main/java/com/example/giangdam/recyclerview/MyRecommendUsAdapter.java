package com.example.giangdam.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 9/4/2015.
 */
public class MyRecommendUsAdapter extends RecyclerView.Adapter<MyRecommendUsAdapter.ViewHolder> {

    ArrayList<Desire> arrayList;
    Context context;
    int layoutId;
    int mPostion = -1;

    public MyRecommendUsAdapter(Context context, int layoutId, ArrayList<Desire> arrayList){
        super();
        this.context = context;
        this.layoutId  = layoutId;
        this.arrayList = arrayList;
    }



    @Override
    public MyRecommendUsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId,parent, false);
        return new MyRecommendUsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecommendUsAdapter.ViewHolder holder, final int position) {
        mPostion = position;
        holder.txtRetailerName.setText((CharSequence) arrayList.get(position).getRetailerName());
        holder.txtDesiresCount.setText(String.valueOf(arrayList.get(position).getDesiresCount()));

        //set Image Product
        //new viewImageUrl(viewHolder.imgProductImage).execute(arrayList.get(position).getProductImage());
        holder.imageLoader.displayImage(arrayList.get(position).getProductImage(),holder.imgProductImage, holder.imageOptions);
        holder.imgProductImage.setOnClickListener(new View.OnClickListener() {
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

        holder.imgbtnShoppingBag.setOnClickListener(new View.OnClickListener() {
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


        holder.imgbtnDesires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), SaveDesireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });


        holder.imgbtnLocal.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgProductImage;
        public TextView txtRetailerName;
        public ImageButton imgbtnDesires;
        public TextView txtDesiresCount;
        public ImageButton imgbtnLocal;
        public ImageButton imgbtnShoppingBag;
        public ImageLoader imageLoader;
        public DisplayImageOptions imageOptions;

        public ViewHolder(View itemView) {
            super(itemView);

            txtRetailerName = (TextView)itemView.findViewById(R.id.txtRetailerName);
            txtDesiresCount = (TextView)itemView.findViewById(R.id.txtDesiresCount);
            imgProductImage = (ImageView)itemView.findViewById(R.id.imgProductImage);
            imgbtnDesires = (ImageButton)itemView.findViewById(R.id.imgbtnDesires);
            imgbtnLocal = (ImageButton)itemView.findViewById(R.id.imgbtnLocal);
            imgbtnShoppingBag = (ImageButton)itemView.findViewById(R.id.imgbtnShoppingBag);

            imageLoader = ImageLoader.getInstance();
            imageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                    .resetViewBeforeLoading(true).build();

        }
    }
}
