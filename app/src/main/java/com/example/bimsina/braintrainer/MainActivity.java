package com.example.bimsina.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton,playAgain;
    RelativeLayout insideLayout;
    TextView timer,question,result,scoreView,timesUp;
    Random rand;
    int a,b;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int positionOfCorrectAnswer;
    int incorrectAnswer,numberOfQestions;
    int score =0;
    CountDownTimer countDownTimer;
    boolean gameIsActive = false;
    Button button0 ,button1 ,button2,button3;

    public void start(View view)
    {
        startButton = findViewById(R.id.startButton);
        insideLayout = findViewById(R.id.layoutInside);
        gameIsActive = true;

        timesUp.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        insideLayout.setVisibility(View.VISIBLE);


        countDownTimer = new CountDownTimer(30000,1000){
            public void onTick(long milliSec){
                timer.setText(String.valueOf(milliSec/1000) + "s");
            }
            public void onFinish(){
                timer.setText("0 s");
                timesUp.setVisibility(View.VISIBLE);
                playAgain.setVisibility(View.VISIBLE);
                gameIsActive = false;
                timesUp.setText("Your Score "+score + "/" +numberOfQestions);
            }
        }.start();

    }

    public  void nextQuestion(){
        a = rand.nextInt(20)+1;
        b = rand.nextInt(20)+1;

        question.setText(a+"+"+b);
        positionOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        for(int i=0 ;i<4 ;i++)
        {
            if(i==positionOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else{
                incorrectAnswer = rand.nextInt(40);
                while (incorrectAnswer == a+b)
                {
                    incorrectAnswer = rand.nextInt(40);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void checkAnswer(View view)
    {
        if(gameIsActive) {
            if (view.getTag().toString().equals(Integer.toString(positionOfCorrectAnswer))) {

                score++;
                result.setText("Correct!");
                Log.i("Correct", "yay " + score);
            } else {
                result.setText("Incorrect!");
            }
            numberOfQestions++;
            scoreView.setText(score + "/" + numberOfQestions);
            nextQuestion();
        }
    }
    public  void playAgain(View view)
    {
        playAgain.setVisibility(View.INVISIBLE);
        timesUp.setVisibility(View.INVISIBLE);
        score =0;
        scoreView.setText("0/0");
        numberOfQestions =0;
        countDownTimer.start();
        gameIsActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rand = new Random();
        question = findViewById(R.id.questions);
        timer = findViewById(R.id.timer);
        timesUp = findViewById(R.id.timesUpButton);
        result = findViewById(R.id.result);
        scoreView = findViewById(R.id.score);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgain = findViewById(R.id.playAgain);

        nextQuestion();



    }
}
