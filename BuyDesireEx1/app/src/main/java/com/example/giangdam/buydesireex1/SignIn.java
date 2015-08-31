package com.example.giangdam.buydesireex1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.dao.DBUser;
import com.example.giangdam.buydesireex1.daomanager.DatabaseManager;
import com.example.giangdam.buydesireex1.daomanager.IDatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class SignIn extends AppCompatActivity {


    private List<DBUser> userList;
    /**
     * Manages the database for this application..
     */
    private IDatabaseManager databaseManager;

    EditText txtEmailSignIn,txtPasswordSignIn;
    Button btnSignIn;

    TextView lblnotifysignin;

    public static long UserId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtEmailSignIn = (EditText)findViewById(R.id.txtEmailSignIn);
        txtPasswordSignIn = (EditText)findViewById(R.id.txtPasswordSignIn);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        lblnotifysignin = (TextView)findViewById(R.id.lblnotifysignin);

        txtEmailSignIn.setFocusable(true);

        // init database manager
        databaseManager = new DatabaseManager(this);
        userList = new ArrayList<DBUser>();
        userList = databaseManager.listUsers();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSignInRight()){
                    //save share_preference
                    LoginActivity.pref = getSharedPreferences(LoginActivity.MYACCOUNT_SHAREPRE, MODE_PRIVATE);
                    SharedPreferences.Editor edit = LoginActivity.pref.edit();
                    edit.putLong("My_User_Id", UserId);
                    edit.apply();
                    LoginActivity.typeLogin = 4;
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    lblnotifysignin.setText("(*) Email or Password is wrong !!!");
                }

            }
        });

    }

    public boolean isSignInRight(){
        for(int i = 0; i< userList.size(); i++){
            if(txtEmailSignIn.getText().toString().equals(userList.get(i).getEmail())  && txtPasswordSignIn.getText().toString().equals(userList.get(i).getPassword())    ){
                UserId = userList.get(i).getId();
                return true;
            }
        }
        return false;
    }


}
