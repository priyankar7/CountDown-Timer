package com.example.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btnStartPause, btnReset, btnSet;
    TextView tvViewCountDown;
    EditText etHours, etMinutes,etSeconds;

    CountDownTimer countDownTimer;
    long START_TIME = 0 ;
    long TIME_LEFT = START_TIME;
    boolean TimerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvViewCountDown = findViewById(R.id.tvViewCountDown);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnReset = findViewById(R.id.btnReset);
        btnSet = findViewById(R.id.btnSet);
        etHours = findViewById(R.id.etHours);
        etMinutes = findViewById(R.id.etMinutes);
        etSeconds = findViewById(R.id.etSeconds);

        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TimerRunning){
                    pauseTimer();
                }
                else {
                    startTimer();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimer();
            }
        });
        updateCountDownText();
    }

    public void setTimer(){
        int hr = Integer.parseInt(etHours.getText().toString());
        int min = Integer.parseInt(etMinutes.getText().toString());
        int sec = Integer.parseInt(etSeconds.getText().toString());

        long s = (min + (hr*60))*60 + sec;
        long ms = s * 1000;
        START_TIME = ms;
        TIME_LEFT = START_TIME;
        updateCountDownText();
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(TIME_LEFT, 100) {
            @Override
            public void onTick(long l) {
                TIME_LEFT = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                btnStartPause.setText("Start");
            }
        }.start();

        TimerRunning = true;
        btnStartPause.setText("Pause");
    }

    public void pauseTimer(){
        countDownTimer.cancel();
        TimerRunning = false;
        btnStartPause.setText("Start");
    }

    public void resetTimer(){
        TIME_LEFT = START_TIME;
        updateCountDownText();
    }

    public void updateCountDownText(){
        int hours = (int) (TIME_LEFT / 1000) / 3600;
        int minutes = (int) ((TIME_LEFT / 1000) / 60) % 60;
        int seconds = (int) (TIME_LEFT / 1000) % 60;

        String counter = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours,minutes, seconds);

        tvViewCountDown.setText(counter);
    }
}