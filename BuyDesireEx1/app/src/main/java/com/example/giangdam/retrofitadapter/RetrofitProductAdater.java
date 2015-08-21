package com.example.giangdam.retrofitadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.R;
import com.example.giangdam.retrofitmodel.Desire;
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
    public View getView(int position, View convertView, ViewGroup parent) {
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


       viewHolder.txtRetailerName.setText((CharSequence) arrayList.get(position).getRetailerName());
        viewHolder.txtDesiresCount.setText(String.valueOf(arrayList.get(position).getDesiresCount()));

        //set Image Product
        //new viewImageUrl(viewHolder.imgProductImage).execute(arrayList.get(position).getProductImage());
        viewHolder.imageLoader.displayImage(arrayList.get(position).getProductImage(),viewHolder.imgProductImage, viewHolder.imageOptions);
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
