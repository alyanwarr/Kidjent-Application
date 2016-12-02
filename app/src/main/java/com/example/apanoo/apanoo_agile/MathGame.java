package com.example.apanoo.apanoo_agile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Peter on 1/12/2016.
 */

public class MathGame extends Activity implements View.OnClickListener {
    private DatabaseHelper helper = new DatabaseHelper(this);
    private Users user;
    TextView Operand1;
    TextView Operand2;
    TextView Operator;
    TextView Equal;
    TextView Result;
    TextView Choice1;
    TextView Choice2;
    TextView Choice3;
    TextView Choice4;
    TextView lifes;
    TextView score;
    Button refresh;
    int unknwnvalue;
    String unknown;
    int Gamescore = 0;
    int life = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mathgame);
        user = (Users) getIntent().getParcelableExtra("Users");
        Operand1 = (TextView) findViewById(R.id.Operand1);
        Operand2 = (TextView) findViewById(R.id.Operand2);
        Operator = (TextView) findViewById(R.id.Operator);
        Equal = (TextView) findViewById(R.id.Equal);
        Result = (TextView) findViewById(R.id.Result);
        Choice1 = (TextView) findViewById(R.id.Choice1);
        Choice2 = (TextView) findViewById(R.id.Choice2);
        Choice3 = (TextView) findViewById(R.id.Choice3);
        Choice4 = (TextView) findViewById(R.id.Choice4);
        refresh = (Button) findViewById(R.id.refresh);
        lifes = (TextView) findViewById(R.id.life);
        lifes.setText(String.valueOf(life));
        score = (TextView) findViewById(R.id.score);
        score.setText(String.valueOf(Gamescore));
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MathGame.this, MathGame.class);
                finish();
                startActivity(i);
            }
        });
        Equal.setText("=");
        /*char x=RandOperator();
        System.out.println(x);
        int y=RandOperand();
        System.out.println(y);
        int z=RandOperand();
        System.out.println(z);
        int i=Result(y,z,x);
        System.out.println(i);*/
        unknown = unknownRand();
        System.out.println("Unknown :" + unknown);
        unknwnvalue = equationGen(unknown);
        System.out.println("unknown Value:" + unknwnvalue);
        ChoicesAssign(unknwnvalue, unknown);
        Choice1.setOnClickListener(this);
        Choice2.setOnClickListener(this);
        Choice3.setOnClickListener(this);
        Choice4.setOnClickListener(this);

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
                Operand1.setText("__");
                return operand1;
            case "Operand2":
                Operand2.setText("__");
                return operand2;
            case "Result":
                Result.setText("__");
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
        if ((unknown == "Operand1") || (unknown == "Operand2"))
            return r.nextInt(10 - 0) + 0;
        else
            return r.nextInt(82 - 0) + 0;

    }

    void ChoicesAssign(int result, String unknown) {
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
                return '*';
            case 4:
                return '/';
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
            case '*':
                if (operand1 == 0 || operand2 == 0) {
                    Result = -100;
                    break;
                } else Result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
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
        Intent it = new Intent(MathGame.this, welcome.class);
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
        choice.setTextColor(Color.RED);
        choice.setClickable(false);
        choice.setPaintFlags(choice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        life--;
        lifes.setText(String.valueOf(life));
        if (life == 0) {
            Toast Death = Toast.makeText(MathGame.this, "You ran out of lifes", Toast.LENGTH_SHORT);
            Death.show();
            Intent it = new Intent(MathGame.this, welcome.class);
            int oldscore = user.getScore();
            int newscore = user.getScore() + Gamescore;
            user.setScore(newscore);
            helper.Update_Score(user.getUname(), oldscore, newscore);
            it.putExtra("Users", user);
            finish();
            startActivity(it);
        }

    }

    public void CorrectAns(TextView choice) {
        Gamescore++;
        score.setText(String.valueOf(Gamescore));
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
                life = 3;
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
        Choice1.setTextColor(Color.GRAY);
        Choice2.setTextColor(Color.GRAY);
        Choice3.setTextColor(Color.GRAY);
        Choice4.setTextColor(Color.GRAY);

        Choice1.setPaintFlags(Choice1.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice2.setPaintFlags(Choice2.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice3.setPaintFlags(Choice3.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        Choice4.setPaintFlags(Choice4.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        Choice1.setClickable(true);
        Choice2.setClickable(true);
        Choice3.setClickable(true);
        Choice4.setClickable(true);



    }
}
