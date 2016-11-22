package com.example.apanoo.apanoo_agile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import customfonts.MyTextView;

/**
 * Created by omar_adel on 11/21/2016.
 */

public class profile extends AppCompatActivity {
    private DatabaseHelper helper=new DatabaseHelper(this);
    private Users user;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    private LinearLayout back;
    private MyTextView username;
    private ImageView propic;
    private MyTextView signout;
    private View mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.profile);
        user=(Users)getIntent().getParcelableExtra("Users");
        mContentView = findViewById(R.id.fullscreen_content);
        username=(MyTextView)findViewById(R.id.usernameprof);
        username.setText(user.getUname());
        propic=(ImageView)findViewById(R.id.propic);
        signout=(MyTextView)findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    startActivity(new Intent(profile.this, main.class));
                    finish();
            }
        });
        hide();
        back = (LinearLayout)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        //=========================================================================
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
        propic.setImageURI(Uri.parse(user.getProfilepic()));
        //=========================================================================
    }
    @Override
    protected void onStart(){
        super.onStart();
        hide();
    }
    private void edits() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    private void hide() {
        mContentView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
