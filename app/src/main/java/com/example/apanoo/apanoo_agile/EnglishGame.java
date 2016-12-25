package com.example.apanoo.apanoo_agile;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by APANOO on 8/12/2016.
 */

public class EnglishGame extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Users user;
    private View mContentView;
    ArrayList<QPicture> ArrayOfPictures=new ArrayList<>();
    QPicture QImage=new QPicture();
    private TextView lifes;
    private TextView score;
    private int Gamescore = 0;
    private int life = 3;
    private ImageView QImageview;
    private TextView choice1;
    private TextView choice2;
    private TextView choice3;
    private TextView choice4;
    private LinearLayout returne;
    String PrevImage="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.englishgame);
        ArrayOfPictures= com.example.apanoo.apanoo_agile.ArrayOfPictures.GetArrayofPictures();
        user = (Users) getIntent().getParcelableExtra("Users");
        mContentView = findViewById(R.id.fullscreen_content);
        QImageview=(ImageView)findViewById(R.id.ivQPicture);
        choice1=(TextView)findViewById(R.id.choice1);
        choice2=(TextView)findViewById(R.id.choice2);
        choice3=(TextView)findViewById(R.id.choice3);
        choice4=(TextView)findViewById(R.id.choice4);
        lifes=(TextView)findViewById(R.id.Life);
        lifes.setText(String.valueOf(life));
        score=(TextView)findViewById(R.id.Score);
        score.setText(String.valueOf(Gamescore));
        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
        choice4.setOnClickListener(this);
        returne = (LinearLayout)findViewById(R.id.engreturn);
        returne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EnglishGame.this,categories.class);
                int oldscore = user.getEngScore();
                int newscore = user.getEngScore() + Gamescore;
                user.setEngscore(newscore);
                helper.Update_EngScore(user.getUname(), oldscore, newscore);
                it.putExtra("Users", user);
                startActivity(it);
                finish();
            }
        });
        QImage =QuestionImageGen(PrevImage);
        QuestionGen(QImage);
    }
    QPicture QuestionImageGen(String PrevImage)
    { Random r=new Random();
        while (true){
            int randimgnum=r.nextInt(ArrayOfPictures.size());
            if(!ArrayOfPictures.get(randimgnum).GetName().equals(PrevImage))
                return ArrayOfPictures.get(randimgnum);
        }
    }
    void QuestionGen(QPicture QImage){
        QImageview.setImageURI(Uri.parse(QImage.GetPath()));
        PrevImage=QImage.GetName();
        ChoicesAssign(QImage.GetName());
    }



    void ChoicesAssign(String result) {
        Random r = new Random();
        int resultchoice = r.nextInt(4)+1;
        if(resultchoice == 1){
            choice1.setText(result);
        }else if(resultchoice == 2){
            choice2.setText(result);
        }else if(resultchoice == 3){
            choice3.setText(result);
        }else {
            choice4.setText(result);
        }
        otherchoises(resultchoice);
    }

    void otherchoises(int resultchoice){
        switch(resultchoice){
            case 1:
                choice2.setText(String.valueOf(choicescheck()));
                choice3.setText(String.valueOf(choicescheck()));
                choice4.setText(String.valueOf(choicescheck()));
                break;
            case 2:
                choice1.setText(String.valueOf(choicescheck()));
                choice3.setText(String.valueOf(choicescheck()));
                choice4.setText(String.valueOf(choicescheck()));
                break;
            case 3:
                choice1.setText(String.valueOf(choicescheck()));
                choice2.setText(String.valueOf(choicescheck()));
                choice4.setText(String.valueOf(choicescheck()));
                break;
            case 4:
                choice1.setText(String.valueOf(choicescheck()));
                choice2.setText(String.valueOf(choicescheck()));
                choice3.setText(String.valueOf(choicescheck()));
                break;
        }
    }

    String choicescheck(){
        String choicevalue;
        while(true){
            choicevalue = choicesGen();
            if(!choicevalue.equals(choice1.getText().toString())){
                if(!choicevalue.equals(choice2.getText().toString())){
                    if(!choicevalue.equals(choice3.getText().toString())){
                        if(!choicevalue.equals(choice4.getText().toString())){
                            return choicevalue;
                        }
                    }
                }
            }
        }
    }
    String choicesGen()
    {
        Random r=new Random();
        int randchoice=r.nextInt(ArrayOfPictures.size());
        return ArrayOfPictures.get(randchoice).GetName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choice1:
                if (choice1.getText().toString().equals(QImage.GetName()))
                    CorrectAns("choice1");
                else
                    IncorrectAns("choice1");
                break;
            case R.id.choice2:
                if (choice2.getText().toString().equals(QImage.GetName()))
                    CorrectAns("choice2");
                else
                    IncorrectAns("choice2");
                break;
            case R.id.choice3:
                if (choice3.getText().toString().equals(QImage.GetName()))
                    CorrectAns("choice3");
                else
                    IncorrectAns("choice3");
                break;
            case R.id.choice4:
                if (choice4.getText().toString().equals(QImage.GetName()))
                    CorrectAns("choice4");
                else
                    IncorrectAns("choice4");
                break;
    }
    }


    public void CorrectAns(String choice) {
        Gamescore++;
        score.setText(String.valueOf(Gamescore));
        switch (choice)
        {
            case "choice1":
                choice1.setTextColor(Color.GREEN);
                newQuestion();
                break;
            case "choice2":
                choice2.setTextColor(Color.GREEN);
                newQuestion();
                break;
            case "choice3":
                choice3.setTextColor(Color.GREEN);
                newQuestion();
                break;
            case "choice4":
                choice4.setTextColor(Color.GREEN);
                newQuestion();
                break;
        }

    }
    public void newQuestion()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                lifes.setText(String.valueOf(life));
                QImage =QuestionImageGen(PrevImage);
                QuestionGen(QImage);
                choice1.setTextColor(Color.WHITE);
                choice1.setClickable(true);
                choice2.setTextColor(Color.WHITE);
                choice2.setClickable(true);
                choice3.setTextColor(Color.WHITE);
                choice3.setClickable(true);
                choice4.setTextColor(Color.WHITE);
                choice4.setClickable(true);
            }
        }, 500);
    }

    public void IncorrectAns(String choice) {
        switch (choice)
        {
            case "choice1":
                choice1.setTextColor(Color.RED);
                choice1.setClickable(false);
                newQuestion();
                break;
            case "choice2":
                choice2.setTextColor(Color.RED);
                choice2.setClickable(false);
                newQuestion();
                break;
            case "choice3":
                choice3.setTextColor(Color.RED);
                choice3.setClickable(false);
                newQuestion();
                break;
            case "choice4":
                choice4.setTextColor(Color.RED);
                choice4.setClickable(false);
                newQuestion();
                break;
        }

        life--;
        if (life == 0) {
            Toast Death = Toast.makeText(EnglishGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
            Intent it = new Intent(EnglishGame.this, categories.class);
            int oldscore = user.getEngScore();
            int newscore = user.getEngScore() + Gamescore;
            user.setEngscore(newscore);
            helper.Update_EngScore(user.getUname(), oldscore, newscore);
            it.putExtra("Users", user);
            finish();
            startActivity(it);
        }
        else {
            Toast wrong = Toast.makeText(EnglishGame.this, "Wrong Answer! " + life +" lives left!", Toast.LENGTH_SHORT);
            wrong.show();
            newQuestion();

            lifes.setText(String.valueOf(life));}


    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(EnglishGame.this, categories.class);
        int oldscore = user.getEngScore();
        int newscore = user.getEngScore() + Gamescore;
        user.setEngscore(newscore);
        helper.Update_EngScore(user.getUname(), oldscore, newscore);
        it.putExtra("Users", user);
        finish();
        startActivity(it);
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
