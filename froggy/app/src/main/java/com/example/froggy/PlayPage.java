package com.example.froggy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

public class PlayPage extends AppCompatActivity implements View.OnClickListener {
    private TextView points;
    private TextView pointsMiss;
    private TextView editTimer;
    private String name;
    private Button[] allButtons;
    private String buttonId;
    private GridLayout gridLayout;
    private int random;
    private int random2;
    private CountDownTimer countDown;
    private ImageView heartView1;
    private ImageView heartView2;
    private ImageView heartView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
        Bundle bundle = getIntent().getExtras();
        name = bundle.get("userName").toString();
        allButtons = new Button[9];
        editTimer = findViewById(R.id.timerId);
        pointsMiss = findViewById(R.id.missPointsId);
        points = findViewById(R.id.pointsId);
        gridLayout = findViewById(R.id.gridId);

        heartView1=findViewById(R.id.heart1);
        heartView2=findViewById(R.id.heart2);
        heartView3=findViewById(R.id.heart3);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            allButtons[i] = (Button) gridLayout.getChildAt(i);
            allButtons[i].setOnClickListener(this);
        }
                final Handler handler = new Handler();
        countDown=new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                editTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);

                 final int random = (int) ((Math.random() * 10) - 1);

                if (allButtons[random].getBackground().getConstantState() != getResources().getDrawable(R.drawable.frog).getConstantState()) {
                    allButtons[random].setBackgroundResource(R.drawable.frog);
                    random2 = (int) (Math.random() * 2) + 2;

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (allButtons[random].getBackground().getConstantState() == getResources().getDrawable(R.drawable.frog).getConstantState()) {
                                allButtons[random].setBackgroundResource(R.drawable.leaf);
                            }
                        }
                    }, 1000 * random2);
                }
            }

            public void onFinish() {
                editTimer.setText("Done");
                Intent intent = new Intent(getApplicationContext(), finishView.class);
                intent.putExtra("userName", name);
                intent.putExtra("userPoints", points.getText().toString());
                intent.putExtra("userPointsMiss", pointsMiss.getText().toString());
                startActivity(intent);
                finish();

            }
        }.start();

    }
    public void finishGame(String message){
        editTimer.setText(message);
        Intent intent = new Intent(getApplicationContext(), finishView.class);
        intent.putExtra("userName", name);
        intent.putExtra("userPoints", points.getText().toString());
        intent.putExtra("userPointsMiss", pointsMiss.getText().toString());
        countDown.cancel();
        startActivity(intent);


        finish();
    }
    @Override
    public void onClick(View view) {
        Button b = findViewById(view.getId());
        int currentPoints = Integer.parseInt(points.getText().toString().split(" ")[1]);
        int currentMiss = Integer.parseInt(pointsMiss.getText().toString().split(" ")[1]);
        if (b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.frog).getConstantState()) {
            points.setText("Points: " + (currentPoints + 1));
            b.setBackgroundResource(R.drawable.leaf);
            if (currentPoints == 29) {

                finishGame("You Win");
            }
        } else if (b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.leaf).getConstantState()) {


            currentMiss++;
            if(currentMiss==1)
                heartView1.setVisibility(View.INVISIBLE);
            if(currentMiss==2)
                heartView2.setVisibility(View.INVISIBLE);
            if(currentMiss==3)
                heartView3.setVisibility(View.INVISIBLE);

            pointsMiss.setText("Miss: " + currentMiss);
            b.setBackgroundResource(R.drawable.leaf);


            if (currentMiss == 3) {
                finishGame("You Lose!");

        }
        }
    }
}
