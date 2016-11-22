package com.example.apanoo.apanoo_agile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import customfonts.MyEditText;
import customfonts.MyTextView;

/**
 * Created by APANOO on 11/21/2016.
 */

public class imagechoose extends AppCompatActivity {
    private Users user;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private static final int RESULT_LOAD_IMAGE = 0;
    private MyTextView finish;
    private ImageView imageToUpload;
    private View mContentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user=(Users)getIntent().getParcelableExtra("Users");
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.imagechoose);
        mContentView = findViewById(R.id.fullscreen_content);
        hide();
        imageToUpload = (ImageView) findViewById(R.id.profilepic);
        imageToUpload.setImageURI(Uri.parse(user.getProfilepic()));
        imageToUpload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent galleryIntent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });
        finish = (MyTextView) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(imagechoose.this,welcome.class);
                i.putExtra("Users", user);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data !=null)
        {
            Uri selectedImage = data.getData();
            helper.Update_Picture(user.getUname(),user.getProfilepic(),selectedImage.toString());
            user.setProfilepic(selectedImage.toString());
            imageToUpload.setImageURI(Uri.parse(helper.GetColumn(user.getUname(),"ProfilePic")));
        }
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
