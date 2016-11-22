package com.example.apanoo.apanoo_agile;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import customfonts.MyEditText;
import customfonts.MyTextView;

/**
 * Created by APANOO on 11/21/2016.
 */

public class imagechoose extends AppCompatActivity {
    private MyTextView finish;
    private MyEditText fullname;
    private View mContentView;
    Boolean hidden = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.imagechoose);
        mContentView = findViewById(R.id.fullscreen_content);
        hide();
        finish = (MyTextView) findViewById(R.id.finish);
       /*
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(welcome.this,signin.class);
                startActivity(it);
            }
        });*/
        fullname = (MyEditText)findViewById(R.id.fullname);
        fullname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    hidden = false ;
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
        }
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
        hidden = true;
    }
}
