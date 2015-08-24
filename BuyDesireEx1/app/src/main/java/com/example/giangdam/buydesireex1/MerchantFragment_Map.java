package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.giangdam.buydesireex1.googlemap.MyInfoWindow;
import com.example.giangdam.retrofitmodel.Desire;
import com.example.giangdam.retrofitmodel.ProductBound;
import com.example.giangdam.retrofitservice.BuyDesireService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class MerchantFragment_Map extends Fragment implements OnMapReadyCallback {


    static GoogleMap googleMap ;
    static LatLng firstStore;

    String API = "";

    RestAdapter restAdapter;
    BuyDesireService buyDesireService ;
    String request = "";
    int pageSize = 10;
    int pageIndex = 0;

    ArrayList<Desire> productDesireList;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.merchants_fragment_map, container,false);


        if(googleMap == null){
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
        }


        if(firstStore == null){
            //data from Json server
            productDesireList = new ArrayList<>();


            API = getActivity().getResources().getString(R.string.API);

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API).build();

            buyDesireService = restAdapter.create(BuyDesireService.class);
            request = createRequest(0, pageSize, 1661, "VN", pageIndex);

            doRequest();
        }
        else {
            if (googleMap != null) {
                setupMap();
            }
        }

        return  view;
    }

    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }






    public void doRequest() {
        buyDesireService.getDesires(request, new Callback<ProductBound>() {
            @Override
            public void success(ProductBound productBound, Response response) {
                productDesireList.clear();
                for (Desire desire : productBound.getDesires()) {
                    productDesireList.add(desire);
                }

                if(productDesireList.size() != 0){
                    firstStore = new LatLng(productDesireList.get(0).getLatitude(), productDesireList.get(0).getLongitude());

                    if (googleMap != null) {
                        setupMap();
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    public static void changeFirstStore(LatLng newFirstStore){
        firstStore = new LatLng(newFirstStore.latitude,newFirstStore.longitude);
        if(googleMap != null){
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStore, 17));

            //add marker
            googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_default)).anchor(0.0f,
                    0.0f).position(firstStore)).showInfoWindow();
        }
    }


    public String createRequest(int productcataloueId,int pageSize, int userId,  String countryCode, int pageIndex){
        return "{\"DeviceID\":\"android_id\",\"ProductCatalogueID\":"+productcataloueId +",\"AppVersion\":\"4.6\",\"pageSize\":"+pageSize+",\"MobilePlatform\":1,\"UserID\":"+userId+",\"DeviceType\":1,\"CreditCard\":0,\"DeviceAPID\":\"0ea4696f-9a3a-4214-9dab-bf3618f91561\",\"SortCriteria\":0,\"Filter\":0,\"CountryCode\":\""+countryCode+"\",\"PageIndex\":"+pageIndex+",\"CachedDataVersion\":-1}";
    }


    public void setupMap(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setInfoWindowAdapter(new MyInfoWindow(getActivity().getBaseContext(), R.layout.my_info_window));

        //if (productDesireList.size() != 0) {
            //firstStore = new LatLng(productDesireList.get(0).getLatitude(), productDesireList.get(0).getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStore, 17));

            //add marker
            googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_default)).anchor(0.0f,
                    0.0f).position(firstStore)).showInfoWindow();


            googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Intent intent = new Intent(getActivity(), StoreInfomationActivity.class);
                    Bundle bundle = new Bundle();

                    //Need to tranfer store name

                    bundle.putDouble("Latitude", firstStore.latitude);
                    bundle.putDouble("Longitude",firstStore.longitude);
                    intent.putExtra("StoreInfoBundle",bundle);
                    startActivity(intent);
                }
            });

       // }
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

       /*
        if (googleMap != null)
        {
           setupMap();
        }

        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (googleMap != null)
            {
                setupMap();
            }
        }

        */


    }

    public void onDestroyView() {

        if (googleMap != null) {
            googleMap = ((MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
            getActivity().getFragmentManager().beginTransaction()
                    .remove(getActivity().getFragmentManager().findFragmentById(R.id.map)).commit();
            googleMap = null;
        }
        super.onDestroyView();

    }

    @Override
    public void onResume() {

        super.onResume();
        if (googleMap != null && firstStore!= null) {
            setupMap();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
