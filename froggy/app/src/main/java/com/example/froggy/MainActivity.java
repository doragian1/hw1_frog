package com.example.froggy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.froggy.R;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    User myUser = new User();
    String name;
    private EditText editUserNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUserNameText = findViewById(R.id.userNameText);
        Button startGameButton = findViewById(R.id.startButton);
        //while (name == null)
        myUser.setName(name);
        // if (name!= null){
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editUserNameText.getText().toString();
                if (name.length() == 0)
                    Toast.makeText(getApplicationContext(), "You Have To Insert You'r Name!", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), PlayPage.class);
                    intent.putExtra("userName", name);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
