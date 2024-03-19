package com.example.madindividual;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderMenu extends AppCompatActivity {

    Button buttonAs,buttonDe, buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        buttonAs = findViewById(R.id.buttonAs);
        buttonDe = findViewById(R.id.buttonDe);
        buttonExit = findViewById(R.id.buttonExit);

        buttonAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderMenu.this, AscendingOrder.class);
                startActivity(intent);
            }
        });

        buttonDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderMenu.this, DescendingOrder.class);
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