package com.example.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
private int second=0;
private boolean running;
private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            second=savedInstanceState.getInt("sec");
            running=savedInstanceState.getBoolean("run");
            wasrunning=savedInstanceState.getBoolean("wasrun");

        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("sec",second);
        outState.putBoolean("run", running);
        outState.putBoolean("wasrun", wasrunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning=running;
        running=false;

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(wasrunning){
            running=true;
        }

    }

    public void runTimer(){
        final TextView timeview= (TextView) findViewById(R.id.textView);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours= second/3600;
                int miniutes= (second%3600)/60;
                int secs = second%60;
                String time= String.format(Locale.getDefault(), "%d:%02d:%02d",hours,miniutes,secs);
                timeview.setText(time);
            if(running)
            {
                second++;
            }
            handler.postDelayed(this,1000);
            }
        });
    }

    public void onstart(View view) {
        running=true;
    }

    public void onstop(View view) {
        running=false;
    }

    public void onreset(View view) {
        running=false;
        second=0;
    }
}