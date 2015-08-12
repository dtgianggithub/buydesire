package com.example.giangdam.buydesireex1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class MerchantFragment_Directory extends Fragment {


    TabHost tabHostMerchantDirectory;
    HorizontalScrollView horizontalScrollView;


    String[] string_company_name;
    MyMerchantDirectoryAdapter myMerchantDirectoryAdapter ;  // custom adapter
    ArrayList<String> arrayListMerchantDirectory;//array list string list menu
    ListView lvMerchantDirectory; //listview contain menu list


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.merchants_fragment_directory,container,false);


        tabHostMerchantDirectory = (TabHost)view.findViewById(R.id.tabHostMerchantDirectory);
        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.scrollView);

        initTabHost();


        //initial listview
        string_company_name = getActivity().getResources().getStringArray(R.array.listcompanyname);
        arrayListMerchantDirectory = new ArrayList<>();
        Collections.addAll(arrayListMerchantDirectory,string_company_name);
        myMerchantDirectoryAdapter = new MyMerchantDirectoryAdapter(getActivity(),R.layout.custom_list_merchant_directory,arrayListMerchantDirectory);
        lvMerchantDirectory = (ListView)view.findViewById(R.id.lvMerchantDirectory);
        lvMerchantDirectory.setAdapter(myMerchantDirectoryAdapter);

        tabHostMerchantDirectory.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // do some thing
                String currentTabTag = tabHostMerchantDirectory.getCurrentTabTag();
                if(currentTabTag.equals("#")){
                    lvMerchantDirectory.setSelection(0);
                    return;
                }
                int positon = - 1;
                for(int i = 0; i< arrayListMerchantDirectory.size(); i++){
                    if(arrayListMerchantDirectory.get(i).substring(0,1).equalsIgnoreCase(currentTabTag)){
                        positon = i;
                        break;
                    }
                }

                if(positon != -1){
                    lvMerchantDirectory.setSelection(positon);
                }

            }
        });

        return  view;
    }



    private void initTabHost() {
        tabHostMerchantDirectory.setup();

        String[] tabNames = getActivity().getResources().getStringArray(R.array.tabhostdirectory);
        for(int i = 0 ; i< tabNames.length; i++){
            TabHost.TabSpec tabSpec;
            tabSpec = tabHostMerchantDirectory.newTabSpec(tabNames[i]);
            tabSpec.setIndicator(tabNames[i]);
            tabSpec.setContent(new FakeContent(getActivity().getApplicationContext()));
            tabHostMerchantDirectory.addTab(tabSpec);
        }
    }



    public class FakeContent  implements TabHost.TabContentFactory{

        Context context;

        public FakeContent(Context context){
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View fakeview = new View(context);
            fakeview.setMinimumHeight(0);
            fakeview.setMinimumWidth(0);
            return fakeview;
        }
    }





}
