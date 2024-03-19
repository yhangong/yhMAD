package com.example.madindividual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    TextView textViewWelcome;
    Button buttonCompare, buttonOrder, buttonCompose, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        buttonCompare = findViewById(R.id.buttonCompare);
        buttonOrder = findViewById(R.id.buttonOrder);
        buttonCompose = findViewById(R.id.buttonCompose);
        buttonExit = findViewById(R.id.buttonExit);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        textViewWelcome.setText("Welcome, " + name);

        // Add onClick listeners for buttons to start respective activities
        buttonCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CompareNumbersActivity.class);
                startActivity(intent);
            }
        });


        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, OrderMenu.class);
                startActivity(intent);
            }
        });

        buttonCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ComposeNumbersActivity.class);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}