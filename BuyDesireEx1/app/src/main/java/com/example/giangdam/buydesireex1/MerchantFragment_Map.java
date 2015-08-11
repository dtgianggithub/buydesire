package com.example.giangdam.buydesireex1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giangdam.model.CacheManager;
import com.example.giangdam.model.MyJsonReader;
import com.example.giangdam.model.Product;
import com.example.giangdam.model.ProductDesire;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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
public class MerchantFragment_Map extends Fragment implements OnMapReadyCallback {


    static GoogleMap googleMap ;
    SupportMapFragment mapFragment;

    String JsonLink = "http://dev.m.api.buydesire.com/api/Tablet/Desires/GetDesires?request={%22DeviceID%22:%22android_id%22,%22ProductCatalogueID%22:0,%22AppVersion%22:%224.6%22,%22pageSize%22:10,%22MobilePlatform%22:1,%22UserID%22:1661,%22DeviceType%22:1,%22CreditCard%22:0,%22DeviceAPID%22:%220ea4696f-9a3a-4214-9dab-bf3618f91561%22,%22SortCriteria%22:0,%22Filter%22:0,%22CountryCode%22:%22VN%22,%22PageIndex%22:0,%22CachedDataVersion%22:-1}";
    ArrayList<ProductDesire> productDesireList;

    String cachefile = "merchantfragmentmap.cache";
    Boolean isCache = false;
    File pathCacheDir;

    JsonSearchThread task;

    LatLng firstStore;




    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.merchants_fragment_map, container,false);


        //data from Json server
        productDesireList = new ArrayList<>();

        solveCache();



        if(googleMap == null){
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            if(googleMap!= null)
            {
                setupMap();
            }
        }

        return  view;
    }


    public void setupMap(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setBuildingsEnabled(true);

        if (productDesireList.size() != 0) {
            firstStore = new LatLng(productDesireList.get(0).getLatitude(), productDesireList.get(0).getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStore, 13));

            //add marker
            googleMap.addMarker(new MarkerOptions().title("First Store").snippet("This is the first store" +
                    " in our list you can visit").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_recommend_bd)).anchor(0.0f,
                    1.0f).position(firstStore));
        }
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (googleMap != null)
        {
           setupMap();
        }

        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap(); // getMap is deprecated
            // Check if we were successful in obtaining the map.
            if (googleMap != null)
            {
                setupMap();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (googleMap != null) {
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            getActivity().getFragmentManager().beginTransaction()
                    .remove(getActivity().getFragmentManager().findFragmentById(R.id.map)).commit();
            googleMap = null;
        }
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


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
