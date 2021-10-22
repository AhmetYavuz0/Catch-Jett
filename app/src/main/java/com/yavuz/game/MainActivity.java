package com.yavuz.game;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;   ImageView imageView4;   Button button;
    ImageView imageView2;   ImageView imageView5;   Random random;
    ImageView imageView3;   TextView score;         ImageView[] images;
    TextView time;          Runnable runnable;      Handler handler;

    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        time = findViewById(R.id.TimeLeft);
        score = findViewById(R.id.score);button = findViewById(R.id.button);
        images = new ImageView[] {imageView1,imageView2,imageView3,imageView4,imageView5};
        hideAll();
        Toast.makeText(getApplicationContext(), "WELCOME TO THE CATCH JETT GAME", Toast.LENGTH_LONG).show();

    }
    public void start (View view)
    {
        movement();
        score.setText("Score :"+point);
        button.setVisibility(View.INVISIBLE);
        new CountDownTimer(10000,300 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Remaining Time : "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                hideAll();
                button.setVisibility(View.VISIBLE);
                if (point<=4)
                {
                    Toast.makeText(getApplicationContext(), "YOU COULD BE BETTER", Toast.LENGTH_SHORT).show();

                }
                else if (point >4 && point<8)
                {
                    Toast.makeText(getApplicationContext(), "YOU ARE GOOD", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "YOU ARE PERFECT", Toast.LENGTH_SHORT).show();
                }

                handler.removeCallbacks(runnable);
                point=0;
            }
        }.start();

    }

    public void im1(View view)
    {
        point++;
        score.setText("Score :"+point);
    }
    public void movement()
    {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                random = new Random();
                int number = random.nextInt(5);
                for (int i=0;i<images.length;i++)
                {
                    images[i].setVisibility(View.INVISIBLE);
                }
                images[number].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 300);
            }
        };handler.post(runnable);
    }
    public void hideAll()
    {
        for (int i=0;i<images.length;i++)
        {
            images[i].setVisibility(View.INVISIBLE);
        }
    }
}