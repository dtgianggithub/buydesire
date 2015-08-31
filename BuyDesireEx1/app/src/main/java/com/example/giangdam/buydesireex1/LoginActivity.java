package com.example.giangdam.buydesireex1;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.util.Arrays;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.RequestToken;

public class LoginActivity extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    //mange type login(facebook, twitter, google+)
    public static int typeLogin = 0;
    //0: have not login with any api yet
    //1: facebook
    //2: twitter
    //3: google plus

    //manage login with facebook
    LoginButton login_button;
    CallbackManager callbackManager;
    public static AccessToken accessToken;


    //manage login with twitter
    //First: Share preferenece keys
    public Button btn_login_twitter;
    Twitter twitter;
    RequestToken requestToken = null;
    twitter4j.auth.AccessToken accessTokenTwitter;
    String oauth_url,oauth_verifier,profile_url;
    Dialog auth_dialog;
    WebView web;
    public static SharedPreferences pref;
    ProgressDialog progress;
    public static final String TWITTER_SHAREPRE = "mytwittersharepreference";



    //manage login with google plus
    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Client used to interact with Google APIs. */
    public static GoogleApiClient googleApiClient;

    /* A flag indicating that a PendingIntent is in progress and prevents
     * us from starting further intents.
     */
    private boolean mIntentInProgress;
    Button btn_login_googleplus;
    public static final String GOOGLEPLUS_SHAREPRE = "mygoogleplussharepreference";
    public static Person currentPerson;



    public static final String MYACCOUNT_SHAREPRE = "myaccountsharepreference";

    TextView lblSignUp, lblSignIn;

    String loginFB = "You had login with Facebook";
    String loginTW = "You had login with Twitter";
    String loginGP = "You had login with GooglePlus";
    String loginMA = "You had login with BuyDesire Account";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //DECLARE FOR FACEBOOK
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        //DECLARE FOR TWITTER



        //DECLARE FOR GOOGLE PLUS


        //SETTING ACTIVITY LAYOUT FILE
        setContentView(R.layout.activity_login);


        //CHECK TYPE API WITH ACCESS TOKEN
        accessToken = AccessToken.getCurrentAccessToken();


        pref = getSharedPreferences(MYACCOUNT_SHAREPRE, MODE_PRIVATE);
        long UserId = pref.getLong("My_User_Id", -1);



        pref = getSharedPreferences(TWITTER_SHAREPRE, MODE_PRIVATE);
        String _accesstokentwitter = pref.getString("ACCESS_TOKEN", "");

        pref = getSharedPreferences(GOOGLEPLUS_SHAREPRE, MODE_PRIVATE);
        String _sessionId = pref.getString("SESSION_ID", "");

        if(UserId != -1){
            typeLogin = 4;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if( !_sessionId.equals("")){
            typeLogin = 3;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        if( !_accesstokentwitter.equals("")){
            typeLogin = 2;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        if(accessToken != null){
            typeLogin = 1;
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }




        btn_login_googleplus = (Button)findViewById(R.id.btn_login_googleplus);
        btn_login_googleplus.setOnClickListener(new LoginGooglePlus());

        btn_login_twitter = (Button)findViewById(R.id.btn_login_twitter);
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("12cecIEZAldeAqPEQhBQh3MGq", "KTrYJBtzraJ7AbE6xICjuKThQxAjfedN0cijdCuAgVNThbcLJR");
        btn_login_twitter.setOnClickListener(new LoginTwitterProcess());


        login_button = (LoginButton)findViewById(R.id.login_button);
        login_button.setReadPermissions(Arrays.asList("public_profile", "user_friends", "email"));
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                typeLogin = 1;
                accessToken = loginResult.getAccessToken();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });




        lblSignIn = (TextView) findViewById(R.id.lblSignIn);
        lblSignUp = (TextView) findViewById(R.id.lblSignUp);

        lblSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignIn.class);
                startActivity(intent);
            }
        });

        lblSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }


    public void notifyLogin(String notification){
        String serName= Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(serName);

        String contenttitle = "BuyDesire Notification";
        String contenttext = "Click to view app notification";

        Intent intent= new Intent(getApplicationContext(), ViewNofitcationActivtiy.class);
        intent.putExtra("notification",notification);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(getApplication())
                        .setSmallIcon(R.drawable.ic_recommend_bd)
                        .setContentTitle(contenttitle).setContentIntent(pendingIntent)
                        .setContentText(contenttext);


        notificationManager.notify(1, mBuilder.build());
    }


    public class LoginGooglePlus implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) LoginActivity.this)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) LoginActivity.this)
                    .addApi(Plus.API)
                    .addScope(Plus.SCOPE_PLUS_LOGIN)
                    .addScope(Plus.SCOPE_PLUS_PROFILE)
                    .build();

            googleApiClient.connect();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        // We've resolved any connection errors.  mGoogleApiClient can be used to
        //access Google APIs on behalf of the user.
        typeLogin = 3;
        String personName ="";
        String urlImg = "";
        if (Plus.PeopleApi.getCurrentPerson(googleApiClient) != null) {
            currentPerson = Plus.PeopleApi.getCurrentPerson(googleApiClient);
            personName = currentPerson.getDisplayName();
            urlImg = currentPerson.getImage().getUrl();
            Person.Image personPhoto = currentPerson.getImage();
            String personGooglePlusProfile = currentPerson.getUrl();
        }
        pref = getSharedPreferences(GOOGLEPLUS_SHAREPRE,MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("SESSION_ID", String.valueOf(googleApiClient.getSessionId()));
        edit.putString("PERSONNAME_GOOGLEPLUS",personName);
        edit.putString("URLIMG_GOOGLEPLUS", urlImg);
        edit.apply();




        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (!mIntentInProgress && connectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                startIntentSenderForResult(connectionResult.getResolution().getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                // The intent was canceled before it was sent.  Return to the default
                // state and attempt to connect to get an updated ConnectionResult.
                mIntentInProgress = false;
                googleApiClient.connect();
            }
        }


    }


    private  class LoginTwitterProcess implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            new TokenTwitterGet().execute();
        }
    }


    private class TokenTwitterGet extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {

            try {
                requestToken = twitter.getOAuthRequestToken();
                oauth_url = requestToken.getAuthorizationURL();
            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return oauth_url;
        }
        @Override
        protected void onPostExecute(String oauth_url) {
            if(oauth_url != null){
                Log.e("URL", oauth_url);
                auth_dialog = new Dialog(LoginActivity.this);
                auth_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                auth_dialog.setContentView(R.layout.auth_dialog);
                web = (WebView)auth_dialog.findViewById(R.id.webv);
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl(oauth_url);
                web.setWebViewClient(new WebViewClient() {
                    boolean authComplete = false;
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon){
                        super.onPageStarted(view, url, favicon);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        if (url.contains("oauth_verifier") && authComplete == false){
                            authComplete = true;
                            Log.e("Url",url);
                            Uri uri = Uri.parse(url);
                            oauth_verifier = uri.getQueryParameter("oauth_verifier");

                            auth_dialog.dismiss();
                            new AccessTokenGet().execute();
                        }else if(url.contains("denied")){
                            auth_dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Sorry !, Permission Denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                auth_dialog.show();
                auth_dialog.setCancelable(true);
            }else{
                Toast.makeText(LoginActivity.this, "Sorry !, Network Error or Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class AccessTokenGet extends AsyncTask<String, String, Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(LoginActivity.this);
            progress.setMessage("Fetching Data ...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();

        }


        @Override
        protected Boolean doInBackground(String... args) {

            try {
                accessTokenTwitter = twitter.getOAuthAccessToken(requestToken, oauth_verifier);
                pref = getSharedPreferences(TWITTER_SHAREPRE,MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("ACCESS_TOKEN", accessTokenTwitter.getToken());
                edit.putString("ACCESS_TOKEN_SECRET", accessTokenTwitter.getTokenSecret());
                User user = twitter.showUser(accessTokenTwitter.getUserId());
                profile_url = user.getOriginalProfileImageURL();
                edit.putString("NAME", user.getName());
                edit.putString("IMAGE_URL", user.getOriginalProfileImageURL());

                edit.apply();


            } catch (TwitterException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }
        @Override
        protected void onPostExecute(Boolean response) {
            if(response){
                progress.hide();
                typeLogin = 2;
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if(typeLogin == 3){
            if (requestCode == RC_SIGN_IN) {
                mIntentInProgress = false;

                if (!googleApiClient.isConnecting()) {
                    googleApiClient.connect();
                }
            }
        }



    }
}
