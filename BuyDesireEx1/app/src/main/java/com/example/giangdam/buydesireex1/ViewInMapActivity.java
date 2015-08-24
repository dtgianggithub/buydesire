package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.giangdam.buydesireex1.googlemap.MyInfoWindow;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewInMapActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
    LatLng storeLatLng;

    static GoogleMap googleMap ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_in_map);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_viewinmap);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_nav_logo);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("StoreInfoBundle");
        if(!bundle.isEmpty())
        {
            storeLatLng = new LatLng(bundle.getDouble("Latitude"), bundle.getDouble("Longitude"));
            Toast.makeText(ViewInMapActivity.this,String.valueOf(storeLatLng.latitude),Toast.LENGTH_SHORT).show();
        }


        if(googleMap == null){
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            if(googleMap!= null){
                setupMap();
            }
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (googleMap != null) {
                    googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
                    getFragmentManager().beginTransaction()
                            .remove(getFragmentManager().findFragmentById(R.id.map)).commit();
                    googleMap = null;
                }

                finish();
            }
        });
    }



    public void setupMap(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setInfoWindowAdapter(new MyInfoWindow(getBaseContext(), R.layout.my_info_window));


        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLatLng, 17));

        //add marker
        googleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_default)).anchor(0.0f,
                0.0f).position(storeLatLng)).showInfoWindow();


        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(ViewInMapActivity.this, StoreInfomationActivity.class);
                Bundle bundle = new Bundle();

                //Need to tranfer store name

                bundle.putDouble("Latitude", storeLatLng.latitude);
                bundle.putDouble("Longitude", storeLatLng.longitude);
                intent.putExtra("StoreInfoBundle", bundle);
                startActivity(intent);

            }
        });






    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (googleMap != null)
        {
            setupMap();
        }


        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (googleMap != null)
            {
                setupMap();
            }
        }
    }


    public void onDestroy() {

        if (googleMap != null) {
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
            getFragmentManager().beginTransaction()
                    .remove(getFragmentManager().findFragmentById(R.id.map)).commit();
            googleMap = null;
        }

        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_in_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
