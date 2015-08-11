package com.example.giangdam.buydesireex1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.giangdam.model.CacheManager;
import com.example.giangdam.model.MyJsonReader;
import com.example.giangdam.model.Product;
import com.example.giangdam.model.ProductDesire;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class RecommendedFragment_you extends android.support.v4.app.Fragment {



    GridView productgrid;
    String JsonLink = "http://dev.m.api.buydesire.com/api/Tablet/Desires/GetDesires?request={%22DeviceID%22:%22android_id%22,%22ProductCatalogueID%22:0,%22AppVersion%22:%224.6%22,%22pageSize%22:10,%22MobilePlatform%22:1,%22UserID%22:1661,%22DeviceType%22:1,%22CreditCard%22:0,%22DeviceAPID%22:%220ea4696f-9a3a-4214-9dab-bf3618f91561%22,%22SortCriteria%22:0,%22Filter%22:0,%22CountryCode%22:%22VN%22,%22PageIndex%22:0,%22CachedDataVersion%22:-1}";
    ArrayList<ProductDesire> productDesireList;
    MyProductAdater myProductAdater;


    String cachefile = "desiresfragmentfriends.cache";
    Boolean isCache = false;
    File pathCacheDir;

    JsonSearchThread task;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommended_fragment_you,container,false);
        productgrid = (GridView)view.findViewById(R.id.productgrid);

        productDesireList = new ArrayList<>();
        myProductAdater = new MyProductAdater(getActivity().getApplicationContext(),R.layout.product_grid,productDesireList);
        productgrid.setAdapter(myProductAdater);

        solveCache();



        return  view;
    }

    public void solveCache(){
        task = new JsonSearchThread();
        isCache = false;
        //check cache
        pathCacheDir = getActivity().getCacheDir();
        String data = "";
        try {
            data = CacheManager.readCache(cachefile, pathCacheDir);
            isCache = true;
            JSONObject jsonObject = new JSONObject(data);
            Product product = new Gson().fromJson(String.valueOf(jsonObject),Product.class);

            for(ProductDesire pr: product.getDesires()){
                productDesireList.add(pr);
            }
            myProductAdater.notifyDataSetChanged();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(!isCache){

            task.execute(JsonLink);
        }

    }

    public void onPause() {
        super.onPause();
        if(!task.isCancelled())
            task.cancel(true);
    }

    public void onResume(){
        super.onResume();
        solveCache();
    }

    private  class JsonSearchThread  extends AsyncTask<String, ProductDesire, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {


            String url = params[0];
            JSONObject jsonObject;
            try {

                jsonObject = MyJsonReader.readJsonfromUrl(url);
                Product product = new Gson().fromJson(String.valueOf(jsonObject), Product.class);

                for(ProductDesire pr : product.getDesires()){
                    publishProgress(pr);
                    SystemClock.sleep(1000);
                }

                return jsonObject;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(ProductDesire... values) {
            super.onProgressUpdate(values);
            productDesireList.add(values[0]);
            myProductAdater.notifyDataSetChanged();
        }

        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            try {

                CacheManager.createCache(result, cachefile, pathCacheDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
