package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giangdam.retrofitmodel.Desire;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class DetailProductActivity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;

    TextView lblProductNameDetail,lblProductPriceDetail,lblProductCompanyDetail,lblDesireCountDetail,lblseemore,lblFeatures;

    ImageView imgProduct;

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
        setContentView(R.layout.activity_detail_product);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("product_bundle");
        mDesire = (Desire) bundle.getSerializable("product");

        lblProductCompanyDetail = (TextView)findViewById(R.id.lblProductCompanyDetail);
        lblProductNameDetail = (TextView)findViewById(R.id.lblProductNameDetail);
        lblProductPriceDetail = (TextView)findViewById(R.id.lblProductPriceDetail);
        lblDesireCountDetail = (TextView)findViewById(R.id.lblDesireCountDetail);
        lblseemore = (TextView)findViewById(R.id.lblseemore);
        lblFeatures = (TextView)findViewById(R.id.lblFeatures);


        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar_detail_product);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Product Name");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Detail Product");

        imgProduct = (ImageView)findViewById(R.id.imgProduct);
        //ImageLoader for avater
        final ImageLoader imageLoaderAvartar = ImageLoader.getInstance();
        final DisplayImageOptions imageOptionsProduct = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                .resetViewBeforeLoading(true).build();



        if(mDesire!= null){
            imageLoaderAvartar.displayImage(mDesire.getProductImage(), imgProduct, imageOptionsProduct);
            lblProductNameDetail.setText(mDesire.getProductName());
            lblProductCompanyDetail.setText(mDesire.getRetailerName());
            lblDesireCountDetail.setText(String.valueOf(mDesire.getDesiresCount()));
            lblProductPriceDetail.setText("POA");
            lblseemore.setText("See more from " + mDesire.getRetailerName());
            getSupportActionBar().setTitle(mDesire.getRetailerName());
        }








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case R.id.detail_product_desires_detail_barcode:
                //show activity
                Intent intent = new Intent(this, ShareQRCode.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("product",mDesire );
                intent.putExtra("product_bundle", bundle);
                startActivity(intent);

                break;
            case R.id.detail_product_desires_detail_plus:
                break;
            case R.id.detail_product_desires_detail_recommend:
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
