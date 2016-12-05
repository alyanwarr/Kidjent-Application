package com.example.apanoo.apanoo_agile;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import customfonts.MyTextView;

/**
 * Created by APANOO on 1/12/2016.
 */

public class MathGame extends AppCompatActivity implements View.OnClickListener {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Users user;
    private View mContentView;
    private LinearLayout returne;
    private TextView Operand1;
    private TextView Operand2;
    private TextView Operator;
    private TextView Equal;
    private TextView Result;
    private TextView Choice1;
    private TextView Choice2;
    private TextView Choice3;
    private TextView Choice4;
    private TextView lifes;
    private TextView score;
    private int unknwnvalue;
    private String unknown;
    private int Gamescore = 0;
    private int life = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edits();
        setContentView(R.layout.mathgame);
        user = (Users) getIntent().getParcelableExtra("Users");
        mContentView = findViewById(R.id.fullscreen_content);
        Operand1 = (TextView) findViewById(R.id.Operand1);
        Operand2 = (TextView) findViewById(R.id.Operand2);
        Operator = (TextView) findViewById(R.id.Operator);
        Equal = (TextView) findViewById(R.id.Equal);
        Result = (TextView) findViewById(R.id.Result);
        Choice1 = (TextView) findViewById(R.id.Choice1);
        Choice2 = (TextView) findViewById(R.id.Choice2);
        Choice3 = (TextView) findViewById(R.id.Choice3);
        Choice4 = (TextView) findViewById(R.id.Choice4);
        lifes = (TextView) findViewById(R.id.life);
        lifes.setText(String.valueOf(life));
        score = (TextView) findViewById(R.id.score);
        score.setText(String.valueOf(Gamescore));
        Equal.setText("=");
        unknown = unknownRand();
        System.out.println("Unknown :" + unknown);
        unknwnvalue = equationGen(unknown);
        System.out.println("unknown Value:" + unknwnvalue);
        ChoicesAssign(unknwnvalue, unknown);
        Choice1.setOnClickListener(this);
        Choice2.setOnClickListener(this);
        Choice3.setOnClickListener(this);
        Choice4.setOnClickListener(this);
        returne = (LinearLayout)findViewById(R.id.playreturn);
        returne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MathGame.this,categories.class);
                int oldscore = user.getScore();
                int newscore = user.getScore() + Gamescore;
                user.setScore(newscore);
                helper.Update_Score(user.getUname(), oldscore, newscore);
                it.putExtra("Users", user);
                startActivity(it);
                finish();
            }
        });
    }

    int equationGen(String unknown) {
        int operand1, operand2, result;
        char operator;
        while (true) {
            operand1 = RandOperand();
            operand2 = RandOperand();
            operator = RandOperator();
            if (Result(operand1, operand2, operator) != -100) {
                result = Result(operand1, operand2, operator);
                break;
            }
        }
        Operand1.setText(String.valueOf(operand1));
        Operand2.setText(String.valueOf(operand2));
        Operator.setText(String.valueOf(operator));
        Result.setText(String.valueOf(result));
        switch (unknown) {
            case "Operand1":
                Operand1.setText("_");
                return operand1;
            case "Operand2":
                Operand2.setText("_");
                return operand2;
            case "Result":
                Result.setText("_");
                return result;
        }
        return result;
    }

    String unknownRand() {
        Random r = new Random();
        int i = r.nextInt(4 - 1) + 1;
        switch (i) {
            case 1:
                return "Operand1";
            case 2:
                return "Operand2";
            case 3:
                return "Result";
            default:
                return "Result";
        }
    }

    int choicesGen(String unknown) {
        Random r = new Random();
        if ((unknown == "Operand1") || (unknown == "Operand2")) {
            return r.nextInt(10 - 0) + 0;
        } else {
            return r.nextInt(82 - 0) + 0;
        }
    }

    /*void ChoicesAssign(int result, String unknown) {
        int randresult;
        Random r = new Random();
        int randchoice = r.nextInt(5 - 1) + 1;
        System.out.println("Choice:" + randchoice);
        switch (randchoice) {
            case 1:
                Choice1.setText(String.valueOf(result));
                do {
                    randresult = choicesGen(unknown);
                    if (randresult != result)
                        Choice2.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice2.getText().toString())))
                        Choice3.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice2.getText().toString())) && (randresult != Integer.parseInt(Choice3.getText().toString())))
                        Choice4.setText(String.valueOf(randresult));
                } while (randresult == result);
                break;
            case 2:
                Choice2.setText(String.valueOf(result));
                do {
                    randresult = choicesGen(unknown);
                    if (randresult != result)
                        Choice1.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())))
                        Choice3.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())) && (randresult != Integer.parseInt(Choice3.getText().toString())))
                        Choice4.setText(String.valueOf(randresult));
                } while (randresult == result);
                break;
            case 3:
                Choice3.setText(String.valueOf(result));
                randresult = choicesGen(unknown);
                do {
                    if (randresult != result)
                        Choice1.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())))
                        Choice2.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())) && (randresult != Integer.parseInt(Choice2.getText().toString())))
                        Choice4.setText(String.valueOf(randresult));
                } while (randresult == result);
                break;
            case 4:
                Choice4.setText(String.valueOf(result));

                do {
                    randresult = choicesGen(unknown);
                    if (randresult != result)
                        Choice1.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())))
                        Choice2.setText(String.valueOf(randresult));
                } while (randresult == result);
                do {
                    randresult = choicesGen(unknown);
                    if ((randresult != result) && (randresult != Integer.parseInt(Choice1.getText().toString())) && (randresult != Integer.parseInt(Choice2.getText().toString())))
                        Choice3.setText(String.valueOf(randresult));
                } while (randresult == result);
                break;
        }
    }*/
    void ChoicesAssign(int result, String unknown) {
        Random r = new Random();
        int resultchoice = r.nextInt(4)+1;
        if(resultchoice == 1){
            Choice1.setText(String.valueOf(result));
        }else if(resultchoice == 2){
            Choice2.setText(String.valueOf(result));
        }else if(resultchoice == 3){
            Choice3.setText(String.valueOf(result));
        }else {
            Choice4.setText(String.valueOf(result));
        }
        otherchoises(resultchoice);
    }

    void otherchoises(int resultchoice){
        switch(resultchoice){
            case 1:
                Choice2.setText(String.valueOf(choicescheck()));
                Choice3.setText(String.valueOf(choicescheck()));
                Choice4.setText(String.valueOf(choicescheck()));
                break;
            case 2:
                Choice1.setText(String.valueOf(choicescheck()));
                Choice3.setText(String.valueOf(choicescheck()));
                Choice4.setText(String.valueOf(choicescheck()));
                break;
            case 3:
                Choice1.setText(String.valueOf(choicescheck()));
                Choice2.setText(String.valueOf(choicescheck()));
                Choice4.setText(String.valueOf(choicescheck()));
                break;
            case 4:
                Choice1.setText(String.valueOf(choicescheck()));
                Choice2.setText(String.valueOf(choicescheck()));
                Choice3.setText(String.valueOf(choicescheck()));
                break;
        }
    }

    int choicescheck(){
        int number = 0;
        while(true){
            number = choicesGen(unknown);
            if(number != Integer.parseInt(Choice1.getText().toString())){
                if(number != Integer.parseInt(Choice2.getText().toString())){
                    if(number != Integer.parseInt(Choice3.getText().toString())){
                        if(number != Integer.parseInt(Choice4.getText().toString())){
                            return number;
                        }
                    }
                }
            }
        }
    }
    char RandOperator() {
        Random r = new Random();
        int randoperator = r.nextInt(5 - 1) + 1;
        switch (randoperator) {
            case 1:
                return '+';
            case 2:
                return '-';
            case 3:
                return '×';
            case 4:
                return '÷';
        }
        return '+';
    }

    int RandOperand() {
        Random r = new Random();
        return r.nextInt(10 - 0) + 0;
    }

    int Result(int operand1, int operand2, char operator) {
        int Result = 0;
        switch (operator) {
            case '+':
                Result = operand1 + operand2;
                break;
            case '-':
                Result = operand1 - operand2;
                break;
            case '×':
                if (operand1 == 0 || operand2 == 0) {
                    Result = -100;
                    break;
                } else Result = operand1 * operand2;
                break;
            case '÷':
                if (operand1 == 0 || operand2 == 0) {
                    Result = -100;
                    break;
                } else if ((operand1 % operand2) != 0)
                    Result = -100;
                else Result = operand1 / operand2;
                break;
        }
        return Result;
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent it = new Intent(MathGame.this, categories.class);
        int oldscore = user.getScore();
        int newscore = user.getScore() + Gamescore;
        user.setScore(newscore);
        helper.Update_Score(user.getUname(), oldscore, newscore);
        it.putExtra("Users", user);
        finish();
        startActivity(it);
    }

    @Override
    public void onClick(View view) {
        if (life == 0) {
            Toast Death = Toast.makeText(MathGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
        }
        int choice;
        switch (view.getId()) {
            case R.id.Choice1:
                choice = Integer.parseInt(Choice1.getText().toString());
                if (choice == unknwnvalue) {
                    CorrectAns(Choice1);
                } else {
                    IncorrectAns(Choice1);
                }
                break;
            case R.id.Choice2:
                choice = Integer.parseInt(Choice2.getText().toString());
                if (choice == unknwnvalue) {
                    CorrectAns(Choice2);
                } else {
                    IncorrectAns(Choice2);
                }
                break;
            case R.id.Choice3:
                choice = Integer.parseInt(Choice3.getText().toString());
                if (choice == unknwnvalue) {
                    CorrectAns(Choice3);
                } else {
                    IncorrectAns(Choice3);
                }
                break;
            case R.id.Choice4:
                choice = Integer.parseInt(Choice4.getText().toString());
                if (choice == unknwnvalue) {
                    CorrectAns(Choice4);
                } else {
                    IncorrectAns(Choice4);
                }
                break;
        }
    }

    public void IncorrectAns(TextView choice) {
       // choice.setTextColor(Color.RED);
       // choice.setClickable(false);
       // choice.setPaintFlags(choice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        life--;


        if (life == 0) {
            Toast Death = Toast.makeText(MathGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
            Intent it = new Intent(MathGame.this, categories.class);
            int oldscore = user.getScore();
            int newscore = user.getScore() + Gamescore;
            user.setScore(newscore);
            helper.Update_Score(user.getUname(), oldscore, newscore);
            it.putExtra("Users", user);
            finish();
            startActivity(it);
        }
        else {
            Toast wrong = Toast.makeText(MathGame.this, "Wrong Answer! " + life +" lives left!", Toast.LENGTH_SHORT);
            wrong.show();
            newQuestion(choice);

        lifes.setText(String.valueOf(life));}


    }

    public void CorrectAns(TextView choice) {
        Gamescore++;
        score.setText(String.valueOf(Gamescore));

        newQuestion(choice);
    }

    private void newQuestion(TextView choice) {
        switch (unknown) {
            case "Operand1":
                Operand1.setText(choice.getText().toString());
                break;
            case "Operand2":
                Operand2.setText(choice.getText().toString());
                break;
            case "Result":
                Result.setText(choice.getText().toString());
                break;
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1s = 1000ms

                ResetAllChoices();
                lifes.setText(String.valueOf(life));
                unknown = unknownRand();
                System.out.println("Unknown :" + unknown);
                unknwnvalue = equationGen(unknown);
                System.out.println("unknown Value:" + unknwnvalue);
                ChoicesAssign(unknwnvalue, unknown);

            }
        }, 500);

    }

    private void ResetAllChoices() {
        Choice1.setPaintFlags(Choice1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice2.setPaintFlags(Choice2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice3.setPaintFlags(Choice3.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice4.setPaintFlags(Choice4.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        Choice1.setClickable(true);
        Choice2.setClickable(true);
        Choice3.setClickable(true);
        Choice4.setClickable(true);



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
