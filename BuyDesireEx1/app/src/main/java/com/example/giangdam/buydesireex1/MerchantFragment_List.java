package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.giangdam.retrofitmodel.Desire;
import com.example.giangdam.retrofitmodel.ProductBound;
import com.example.giangdam.retrofitservice.BuyDesireService;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class MerchantFragment_List extends Fragment {



    MyMerchantListAdapter myMerchantListAdapter ;  // custom adapter
    ArrayList<Desire> arrayListMerchantList;//array list string list menu
    ListView lvMerchantList; //listview contain menu list


    String API = "";

    RestAdapter restAdapter;
    BuyDesireService buyDesireService ;
    String request = "";
    int pageSize = 10;
    int pageIndex = 0;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merchants_fragment_list,container,false);


        arrayListMerchantList = new ArrayList<Desire>();
        myMerchantListAdapter = new MyMerchantListAdapter(getActivity(),R.layout.custom_list_merchant_list,arrayListMerchantList);
        lvMerchantList = (ListView)view.findViewById(R.id.lvMerchantList);
        lvMerchantList.setAdapter(myMerchantListAdapter);

        lvMerchantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //MerchantFragment_Map.firstStore = new LatLng(arrayListMerchantList.get(position).getLatitude(),arrayListMerchantList.get(position).getLongitude());

                MerchantFragment_Map.changeFirstStore(new LatLng(arrayListMerchantList.get(position).getLatitude(),arrayListMerchantList.get(position).getLongitude()));
                MerchantFragment.tabHostMerchant.setCurrentTab(0);
                MerchantFragment.viewpagerMerchant.setCurrentItem(0);
            }
        });


        API = getActivity().getResources().getString(R.string.API);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();

        buyDesireService = restAdapter.create(BuyDesireService.class);
        request = createRequest(0, pageSize, 1661, "VN", pageIndex);

        doRequest();


        lvMerchantList.setOnScrollListener(new AbsListView.OnScrollListener() {
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


        return  view;
    }


    public void doRequest(){
        buyDesireService.getDesires(request, new Callback<ProductBound>() {
            @Override
            public void success(ProductBound productBound, Response response) {
                arrayListMerchantList.clear();
                for(Desire desire : productBound.getDesires()){
                    arrayListMerchantList.add(desire);
                }
                myMerchantListAdapter.notifyDataSetChanged();
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




}
