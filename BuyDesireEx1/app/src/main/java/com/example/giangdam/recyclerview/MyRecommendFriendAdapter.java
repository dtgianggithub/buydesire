package com.example.giangdam.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.giangdam.buydesireex1.R;
import com.example.giangdam.retrofitmodel.Desire;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Giang.Dam on 9/4/2015.
 */
public class MyRecommendFriendAdapter extends RecyclerView.Adapter<MyRecommendFriendAdapter.ViewHolder> {

    ArrayList<Desire> arrayList;
    Context context;
    int layoutId;


    public MyRecommendFriendAdapter(Context context, int layoutId, ArrayList<Desire> arrayList){
        super();
        this.context = context;
        this.layoutId  = layoutId;
        this.arrayList = arrayList;
    }

    @Override
    public MyRecommendFriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId,parent, false);
        return new MyRecommendFriendAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyRecommendFriendAdapter.ViewHolder holder, int position) {
        holder.imageLoader.displayImage(arrayList.get(position).getProductImage(),holder.imgProductImage, holder.imageOptions);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgProductImage;
        public ImageLoader imageLoader;
        public DisplayImageOptions imageOptions;

        public ViewHolder(View itemView) {
            super(itemView);

            imgProductImage = (ImageView)itemView.findViewById(R.id.imgProductImage);
            imageLoader = ImageLoader.getInstance();
            imageOptions = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                    .resetViewBeforeLoading(true).build();

        }
    }
}
