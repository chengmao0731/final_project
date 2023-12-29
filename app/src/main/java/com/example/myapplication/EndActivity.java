package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button restartButton = (Button) findViewById(R.id.restartButton);

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