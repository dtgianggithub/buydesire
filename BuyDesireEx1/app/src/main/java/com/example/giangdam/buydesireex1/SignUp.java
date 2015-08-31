package com.example.giangdam.buydesireex1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giangdam.buydesireex1.dao.DBUser;
import com.example.giangdam.buydesireex1.daomanager.DatabaseManager;
import com.example.giangdam.buydesireex1.daomanager.IDatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {


    private List<DBUser> userList;
    /**
     * Manages the database for this application..
     */
    private IDatabaseManager databaseManager;


    EditText txtFirstName,txtLastName,txtEmailSignUp,txtPasswordSignUp;
    Button btnSignUp;

    TextView lblnotifysignup;

    boolean isSameEmail = false;


/*
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtFirstName = (EditText)findViewById(R.id.txtFirstName);
        txtLastName = (EditText)findViewById(R.id.txtLastName);
        txtEmailSignUp = (EditText)findViewById(R.id.txtEmailSignUp);
        txtPasswordSignUp = (EditText)findViewById(R.id.txtPasswordSignUp);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        lblnotifysignup = (TextView)findViewById(R.id.lblnotifysignup);


        txtFirstName.setFocusable(true);

        // init database manager
        databaseManager = new DatabaseManager(this);
        userList = new ArrayList<DBUser>();
        userList = databaseManager.listUsers();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(isSignUpRight()){
                    DBUser user = new DBUser();
                    user.setFirstname(txtFirstName.getText().toString());
                    user.setLastname(txtLastName.getText().toString());
                    user.setEmail(txtEmailSignUp.getText().toString());
                    user.setPassword(txtPasswordSignUp.getText().toString());
                    user = databaseManager.insertUser(user);
                    clearFocus();
                }else{
                    if(isSameEmail){
                        lblnotifysignup.setText("(*) Your email had used by another user or wrong syntax ! Take other !!!");
                    }else {
                        lblnotifysignup.setText("(*) Check your information, maybe it is wrong !!!");
                    }

                }
            }
        });



    }


    private boolean validateEmail(String email) {
        //return EMAIL_ADDRESS_PATTERN.matcher(email).matches();

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void clearFocus(){
        txtFirstName.setText("");
        txtLastName.setText("");
        txtEmailSignUp.setText("");
        txtPasswordSignUp.setText("");
        txtFirstName.setFocusable(true);
    }

    public boolean isSignUpRight(){

        if(checkName() && checkEmail() && checkPassword()){
            return true;
        }
        else {
            return false;
        }
    }

    public  boolean checkName(){
        if(!txtFirstName.getText().toString().equals("")  && !txtLastName.getText().toString().equals("")){
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkEmail(){



        if(!validateEmail(txtEmailSignUp.getText().toString())){
            isSameEmail = true;
            return false;
        }

        for(int i = 0; i< userList.size(); i++){
            if(txtEmailSignUp.getText().toString().equals(userList.get(i).getEmail())){
                isSameEmail = true;
                return false;
            }
        }

       isSameEmail = false;
        return true;
    }

    public boolean checkPassword(){
        if(txtPasswordSignUp.getText().toString().equals(""))
        {
            return false;
        }
        else {
            return true;
        }
    }


}
