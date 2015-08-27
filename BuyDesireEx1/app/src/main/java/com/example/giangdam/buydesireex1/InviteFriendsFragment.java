package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.share.model.AppInviteContent;
import com.facebook.share.widget.AppInviteDialog;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class InviteFriendsFragment extends android.support.v4.app.Fragment {


    String[] string_menu_addfriend;
    MyAddFriendsUsingAdapter myAddFriendsUsingAdapter ;  // custom adapter
    ArrayList<String> arrayListAddFriend;//array list string list menu
    ListView lvAddNewFriendsUsing; //listview contain menu list

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.invitefriends_fragment,container,false);

        arrayListAddFriend = new ArrayList<String>();
        string_menu_addfriend = getResources().getStringArray(R.array.addfriendlist);
        Collections.addAll(arrayListAddFriend, string_menu_addfriend); // replace for for() or foreach
        myAddFriendsUsingAdapter = new MyAddFriendsUsingAdapter(getActivity(),R.layout.custom_list_addnewfriends,arrayListAddFriend);
        lvAddNewFriendsUsing = (ListView)view.findViewById(R.id.lvAddNewFriendsUsing);
        lvAddNewFriendsUsing.setAdapter(myAddFriendsUsingAdapter);

        lvAddNewFriendsUsing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (arrayListAddFriend.get(position).equals("Facebook")) {
                    if (LoginActivity.typeLogin == 1) {
                        String appLinkUrl = "https://play.google.com/store/apps/details?id=com.buydesire.android";
                        String previewImageUrl = "https://lh3.ggpht.com/bNuvg7pyQd6oF_fC2ykK5Utyr8doyzOF3ZPa2VoLmxHU50DQpxdi24s3Pk0L1PUhSJs=w300";
                        if (AppInviteDialog.canShow()) {
                            AppInviteContent content = new AppInviteContent.Builder()
                                    .setApplinkUrl(appLinkUrl)
                                    .setPreviewImageUrl(previewImageUrl)
                                    .build();
                            AppInviteDialog.show(getActivity(), content);
                        }
                    }
                }
            }
        });

        return  view;
    }
}
