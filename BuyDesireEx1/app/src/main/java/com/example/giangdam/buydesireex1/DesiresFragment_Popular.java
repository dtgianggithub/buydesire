package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.giangdam.retrofitadapter.RetrofitProductAdater;
import com.example.giangdam.retrofitmodel.Desire;
import com.example.giangdam.retrofitmodel.ProductBound;
import com.example.giangdam.retrofitservice.BuyDesireService;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class DesiresFragment_Popular extends android.support.v4.app.Fragment {

    /*
    GridView productgrid;
    String JsonLink = "http://dev.m.api.buydesire.com/api/Tablet/Desires/GetDesires?request={%22DeviceID%22:%22android_id%22,%22ProductCatalogueID%22:0,%22AppVersion%22:%224.6%22,%22pageSize%22:10,%22MobilePlatform%22:1,%22UserID%22:1661,%22DeviceType%22:1,%22CreditCard%22:0,%22DeviceAPID%22:%220ea4696f-9a3a-4214-9dab-bf3618f91561%22,%22SortCriteria%22:0,%22Filter%22:0,%22CountryCode%22:%22VN%22,%22PageIndex%22:0,%22CachedDataVersion%22:-1}";
    ArrayList<ProductDesire> productDesireList;
    MyProductAdater myProductAdater;

    String cachefile = "desiresfragmentpopular.cache";
    Boolean isCache = false;
    File pathCacheDir;
    JsonSearchThread task;
    */

    String API = "";

    RestAdapter restAdapter;
    BuyDesireService buyDesireService ;
    String request = "";
    int pageSize = 10;
    int pageIndex = 0;


    GridView productgrid;
    ArrayList<Desire> productDesireList;
    RetrofitProductAdater myProductAdater;




    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.desires_fragment_popular,container,false);

        productgrid = (GridView)view.findViewById(R.id.productgrid);

        //data from Json server
        productDesireList = new ArrayList<>();
        myProductAdater = new RetrofitProductAdater(getActivity().getApplicationContext(),R.layout.product_grid,productDesireList);
        productgrid.setAdapter(myProductAdater);


        API = getActivity().getResources().getString(R.string.API);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();

        buyDesireService = restAdapter.create(BuyDesireService.class);
        request = createRequest(0, pageSize, 1661, "VN", pageIndex);

        doRequest();


        productgrid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= pageSize) {
                    pageSize = pageSize + 10;
                    request = createRequest(0, pageSize, 1661, "VN", pageIndex);
                    doRequest();
                }
            }
        });

        //solveCache();
        return view;
    }



    public void doRequest(){
        buyDesireService.getDesires(request, new Callback<ProductBound>() {
            @Override
            public void success(ProductBound productBound, Response response) {
                productDesireList.clear();
                for(Desire desire : productBound.getDesires()){
                    productDesireList.add(desire);
                }
                myProductAdater.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }



    public String createRequest(int productcataloueId,int pageSize, int userId,  String countryCode, int pageIndex){
        return "{\"DeviceID\":\"android_id\",\"ProductCatalogueID\":"+productcataloueId +",\"AppVersion\":\"4.6\",\"pageSize\":"+pageSize+",\"MobilePlatform\":1,\"UserID\":"+userId+",\"DeviceType\":1,\"CreditCard\":0,\"DeviceAPID\":\"0ea4696f-9a3a-4214-9dab-bf3618f91561\",\"SortCriteria\":0,\"Filter\":0,\"CountryCode\":\""+countryCode+"\",\"PageIndex\":"+pageIndex+",\"CachedDataVersion\":-1}";
    }

/*
    public void solveCache(){
        task = new JsonSearchThread();
        isCache = false;
        //check cache
        pathCacheDir = getActivity().getCacheDir();
        String data = "";
        try {
            data = CacheManager.readCache(cachefile,pathCacheDir);
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

*/

    /*

    public void onPause() {
        super.onPause();
        if(!task.isCancelled())
            task.cancel(true);
    }

    */

    /*

    public void onResume(){
        super.onResume();
        solveCache();
    }

    */


    /*
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

*/

}
