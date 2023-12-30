package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EndActivity extends AppCompatActivity {
    private TextView guess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        TextView guess = findViewById(R.id.guess);
        Button restartButton = (Button) findViewById(R.id.restartButton);
        String guessnumber = getIntent().getStringExtra("guessNumber");
        guess.setText("恭喜你回答正確，總共猜了" + guessnumber+"次");


        restartButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                    Toast.makeText(EndActivity.this, ("Game restarted"), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EndActivity.this, Start.class);
                    startActivity(intent);
                    // 結束當前 Activity
                    finish();
            }
        });
    }
}