package com.example.catchthefrog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class finishView extends AppCompatActivity {
    private String name;
    private String point;
    private String missPoint;
private TextView nameView;
    private TextView pointView;
    private TextView missView;
    private Button playAgian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_view);
        Bundle bundle=getIntent().getExtras();
        name= bundle.get("userName").toString();
        point= bundle.get("userPoints").toString();
        missPoint= bundle.get("userPointsMiss").toString();

        nameView=findViewById(R.id.nameId);
        pointView=findViewById(R.id.finalScore);
        missView=findViewById(R.id.missId);
        playAgian=findViewById(R.id.playAgainID);
        nameView.setText(name);
        pointView.setText(point);
        missView.setText(missPoint);

        playAgian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        }
}
