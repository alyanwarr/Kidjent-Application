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
import android.widget.Toast;

import customfonts.MyEditText;
import customfonts.MyTextView;

/**
 * Created by APANOO on 11/21/2016.
 */

public class signup extends AppCompatActivity {
    private DatabaseHelper helper = new DatabaseHelper(this);
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
                //===========================================
                String strusername = username.getText().toString();
                String strpassword = password.getText().toString();
                String strconfirmpassword = confpass.getText().toString();
                String stremail= email.getText().toString();
                if(!strusername.matches("^[a-zA-Z0-9_-]{3,15}$")){
                    Toast WrongUsername = Toast.makeText(signup.this,"Username Must be of 3 minimum characters and contain only letters,numbers and _,- ", Toast.LENGTH_SHORT);
                    WrongUsername.show();
                }/*
             else if(!password.matches(" ")){
                Toast WrongUsername = Toast.makeText(SignUp.this,"^{4,16}$", Toast.LENGTH_SHORT);
                WrongUsername.show();
            */
                else if(strpassword.length()<4){
                    Toast PassLength = Toast.makeText(signup.this,"Password must be minimum of 4 characters", Toast.LENGTH_SHORT);
                    PassLength.show();
                }
                else if(strpassword.length()>16){
                    Toast PassLength = Toast.makeText(signup.this,"Password must be maximum of 16 characters", Toast.LENGTH_SHORT);
                    PassLength.show();
                }
                else if(!strpassword.equals(strconfirmpassword)){
                    //popup
                    Toast PassMissmatch = Toast.makeText(signup.this,"Password and confirm password are not matching", Toast.LENGTH_SHORT);
                    PassMissmatch.show();

                }
                else if (helper.UserNameExist(strusername)){
                    Toast UsernameTaken = Toast.makeText(signup.this,"Username already taken,try another", Toast.LENGTH_SHORT);
                    UsernameTaken.show();
                }
                else if (helper.EmailExist(stremail)){
                    Toast EmailTaken = Toast.makeText(signup.this,"This e-mail is used by an other account", Toast.LENGTH_SHORT);
                    EmailTaken.show();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(stremail).matches()){
                    Toast InvalidEmail = Toast.makeText(signup.this,"Please enter a valid email", Toast.LENGTH_SHORT);
                    InvalidEmail.show();
                }
                else
                {
                    //insert details in DB
                    Users c = new Users();
                    c.setUname(strusername);
                    c.setEmail(stremail);
                    c.setPass(strpassword);
                    helper.insertUser(c);
                    c.setProfilepic(helper.GetColumn(strusername,"ProfilePic"));
                    //===========================================
                    Intent it = new Intent(signup.this,imagechoose.class);
                    it.putExtra("Users", c);
                    finish();
                    startActivity(it);
                }
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
            Intent it = new Intent(signup.this,main.class);
            finish();
            startActivity(it);
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
