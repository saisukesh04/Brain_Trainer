package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView playAgainMsg;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locOfAnswer;
    TextView answerView;
    TextView pointsView;
    TextView questionView;
    TextView timerView;
    int score = 0;
    int numberOfQuestions = 0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    public void startGame(View view){
        playAgainMsg.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        score = 0;
        numberOfQuestions = 0;
        timerView.setText("30s");
        pointsView.setText("0/0");
        answerView.setText("");
        generateQuestion();
        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                gameLayout.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.VISIBLE);
                playAgainMsg.setVisibility(View.VISIBLE);
                playAgainMsg.setText("  Time's Up!\nSCORE is "+Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }

    public void generateQuestion(){

        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int incorrectAnswer;
        answers.clear();

        questionView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locOfAnswer = rand.nextInt(4);

        for (int i=0;i<4;i++){
            if (i==locOfAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer = rand.nextInt(45);
                while(incorrectAnswer == a+b) {
                    incorrectAnswer = rand.nextInt(45);
                }
                answers.add(incorrectAnswer);
            }
        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locOfAnswer))){
            answerView.setText("CORRECT");
            score++;
        }else{
            answerView.setText("WRONG");
        }
        numberOfQuestions++;
        pointsView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        questionView = (TextView) findViewById(R.id.questionView);
        pointsView = (TextView) findViewById(R.id.pointsView);
        answerView = (TextView) findViewById(R.id.answerView);
        timerView = (TextView) findViewById(R.id.timerView);
        startButton = (Button) findViewById(R.id.startButton);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        playAgainMsg = findViewById(R.id.playAgainMsg);

    }
}