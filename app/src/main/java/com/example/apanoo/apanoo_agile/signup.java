package com.example.apanoo.apanoo_agile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.ImageView;

import customfonts.MyEditText;
import customfonts.MyTextView;

/**
 * Created by APANOO on 11/21/2016.
 */

public class signup extends AppCompatActivity {
    private ImageView signupback;
    private MyEditText username;
    private MyEditText  email;
    private MyEditText  password;
    private MyEditText  confpass;
    private MyTextView  signup;
    private View mContentView;
    Boolean hidden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.signup);
        mContentView = findViewById(R.id.fullscreen_content);
        hide();
        signupback = (ImageView) findViewById(R.id.signupback);
        signupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signup.this,main.class);
                finish();
                startActivity(it);
            }
        });
        username = (MyEditText)findViewById(R.id.UPusername);
        username.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                    System.out.println("Hidden = "+hidden);
                }
            }
        });
        email = (MyEditText)findViewById(R.id.email);
        email.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                }
            }
        });
        password = (MyEditText)findViewById(R.id.UPpassword);
        password.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                }
            }
        });
        confpass = (MyEditText)findViewById(R.id.confirm_pass);
        confpass.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
                }
            }
        });
        signup = (MyTextView)findViewById(R.id.create);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(signup.this,imagechoose.class);
                finish();
                startActivity(it);
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        hide();
    }
    @Override
    public void onBackPressed() {
        if(!hidden) {
            hide();
        }else{
            super.onBackPressed();
        }
    }
    private void edits(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
    private void hide() {
        mContentView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
        hidden = true;
    }
}
