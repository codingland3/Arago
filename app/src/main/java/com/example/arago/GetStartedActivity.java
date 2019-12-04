package com.example.arago;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetStartedActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        changeStatusBar(getWindow());

        // Lấy keyhash (không quan trọng)
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.arago", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



    public void changeStatusBar(Window window){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void clickLogin(View view) {
        Intent intent = new Intent(GetStartedActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void clickToRegister(View view) {
        Intent intent = new Intent(GetStartedActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void clickLoginFB(View view) {

        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                nextActivity(currentProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                Toast.makeText(GetStartedActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                nextActivity(profile);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(GetStartedActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ----------------------
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //Facebook login
//        Profile profile = Profile.getCurrentProfile();
//        nextActivity(profile);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    protected void onStop() {
//        super.onStop();
//        //Facebook login
////        accessTokenTracker.stopTracking();
////        profileTracker.stopTracking();
//    }

    private void nextActivity(Profile profile){
        if(profile != null){
            Intent main = new Intent(GetStartedActivity.this, MainActivity.class);
            main.putExtra("name", profile.getFirstName());
            main.putExtra("surname", profile.getLastName());
            main.putExtra("imageUrl", profile.getProfilePictureUri(200,200).toString());
            startActivity(main);
        }
    }

}
