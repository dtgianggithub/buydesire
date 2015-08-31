package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.giangdam.retrofitmodel.Desire;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ShareQRCode extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;
    ImageView imgproductcanvas;
    Button btnScanQRCode;


    Desire mDesire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheOnDisk(true)
                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY).displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).memoryCache(new WeakMemoryCache()).diskCacheSize(100*1024*1024).build();

        ImageLoader.getInstance().init(configuration);


        setContentView(R.layout.activity_share_qrcode);



        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_shareQRcode);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Share this QR code with your...");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("product_bundle");
        mDesire = (Desire) bundle.getSerializable("product");



        imgproductcanvas = (ImageView)findViewById(R.id.imgproductcanvas);
        btnScanQRCode = (Button)findViewById(R.id.btnScanQRCode);


        ImageLoader imageLoaderAvartar = ImageLoader.getInstance();
        DisplayImageOptions imageOptionsAvartar = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .displayer(new RoundedBitmapDisplayer(1000))
                .resetViewBeforeLoading(true).build();

        imageLoaderAvartar.displayImage(mDesire.getProductImage(),imgproductcanvas, imageOptionsAvartar);


        btnScanQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareQRCode.this, CameraPreviewActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share_qrcode, menu);
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
