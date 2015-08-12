package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class FindFriendsFragment extends android.support.v4.app.Fragment {

    String[] string_menu_addfriend;
    MyAddFriendsUsingAdapter myAddFriendsUsingAdapter ;  // custom adapter
    ArrayList<String> arrayListAddFriend;//array list string list menu
    ListView lvAddNewFriendsUsing; //listview contain menu list


    String[] string_recommendations;
    MyRecommendationsAdapter myRecommendationsAdapter ;  // custom adapter
    ArrayList<String> arrayListRecommendation;//array list string list menu
    ListView lvRecommendations; //listview contain menu list

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findfriends_fragment,container,false);

        arrayListAddFriend = new ArrayList<String>();
        string_menu_addfriend = getResources().getStringArray(R.array.addfriendlist);
        Collections.addAll(arrayListAddFriend, string_menu_addfriend); // replace for for() or foreach
        myAddFriendsUsingAdapter = new MyAddFriendsUsingAdapter(getActivity(),R.layout.custom_list_addnewfriends,arrayListAddFriend);
        lvAddNewFriendsUsing = (ListView)view.findViewById(R.id.lvAddNewFriendsUsing);
        lvAddNewFriendsUsing.setAdapter(myAddFriendsUsingAdapter);


        arrayListRecommendation = new ArrayList<String>();
        string_recommendations = getResources().getStringArray(R.array.recommedations);
        Collections.addAll(arrayListRecommendation, string_recommendations); // replace for for() or foreach
        myRecommendationsAdapter = new MyRecommendationsAdapter(getActivity(),R.layout.custom_list_recommendations,arrayListRecommendation);
        lvRecommendations = (ListView)view.findViewById(R.id.lvRecommendations);
        lvRecommendations.setAdapter(myRecommendationsAdapter);




        return  view;
    }
}
