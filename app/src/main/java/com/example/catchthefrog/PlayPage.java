package com.example.catchthefrog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
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
    private Button []allButtons ;
    private String buttonId;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_page);
        Bundle bundle=getIntent().getExtras();
        name= bundle.get("userName").toString();
        allButtons=new Button[9];
        editTimer = findViewById(R.id.timerId);
        pointsMiss = findViewById(R.id.missPointsId);
        points = findViewById(R.id.pointsId);
        gridLayout=findViewById(R.id.gridId);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            allButtons[i] = (Button) gridLayout.getChildAt(i);
            allButtons[i].setOnClickListener(this);
        }
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                editTimer.setText("Seconds remaining: " + millisUntilFinished / 1000);

                int random=(int)((Math.random()*10)-1);
               allButtons[random].setBackgroundResource(R.drawable.frog);

                }
                public void onFinish() {
                editTimer.setText("Done");
                Intent intent = new Intent(getApplicationContext(), finishView.class);
                intent.putExtra("userName",name);
                intent.putExtra("userPoints",points.getText().toString());
                intent.putExtra("userPointsMiss",pointsMiss.getText().toString());
                startActivity(intent);

            }
        }.start();

    }

    @Override
    public void onClick(View view) {
        Button b=findViewById(view.getId());
        int currentPoints =  Integer.parseInt(points.getText().toString().split(" ")[1]);
        int currentMiss = Integer.parseInt(pointsMiss.getText().toString().split(" ")[1]);
        if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.frog).getConstantState())
        {
            //Toast.makeText(getApplicationContext(), points.getText().toString(), Toast.LENGTH_SHORT).show();
            points.setText("Points: "+(currentPoints + 1));
            b.setBackgroundResource(R.drawable.leaf);
            if(currentPoints == 29){
                editTimer.setText("You Win");
                Intent intent = new Intent(getApplicationContext(), finishView.class);
                intent.putExtra("userName",name);
                intent.putExtra("userPoints",points.getText().toString());
                intent.putExtra("userPointsMiss",pointsMiss.getText().toString());
                startActivity(intent);

            }
        }
        else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.leaf).getConstantState()){
            currentMiss++;
            pointsMiss.setText("Miss: "+currentMiss);
            b.setBackgroundResource(R.drawable.leaf);


            if(currentMiss==3){

                editTimer.setText("You Lose");
                Intent intent = new Intent(getApplicationContext(), finishView.class);
                intent.putExtra("userName",name);
                intent.putExtra("userPoints",points.getText().toString());
                intent.putExtra("userPointsMiss",pointsMiss.getText().toString());
                startActivity(intent);
            }
        }
    }
}
