package com.example.madindividual;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DescendingOrder extends AppCompatActivity {

    TextView[] textView;
    Button[] numberButtons;
    Button buttonExit;

    List<Integer> selectedNumbersList, questionNumberList, answerNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascending_order);
        buttonExit = findViewById(R.id.buttonExit);
        selectedNumbersList = new ArrayList<>();

        questionNumberList = new ArrayList<>();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView = new TextView[6];
        textView[0] = findViewById(R.id.textView1);
        textView[1] = findViewById(R.id.textView2);
        textView[2] = findViewById(R.id.textView3);
        textView[3] = findViewById(R.id.textView4);
        textView[4] = findViewById(R.id.textView5);
        textView[5] = findViewById(R.id.textView6);

        // Array to store references to number buttons
        numberButtons = new Button[6];
        numberButtons[0] = findViewById(R.id.buttonNumber1);
        numberButtons[1] = findViewById(R.id.buttonNumber2);
        numberButtons[2] = findViewById(R.id.buttonNumber3);
        numberButtons[3] = findViewById(R.id.buttonNumber4);
        numberButtons[4] = findViewById(R.id.buttonNumber5);
        numberButtons[5] = findViewById(R.id.buttonNumber6);

        // Generate new numbers and populate number buttons
        generateNewNumbers();
        buttonListen();
    }

    private void buttonListen() {
        // Set click listeners for number buttons
        for (int i = 0; i < numberButtons.length; i++) {
            final int index = i;
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectNumber(index);
                }
            });
        }
    }

    private void generateNewNumbers() {
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int num = rand.nextInt(100);
            if (!questionNumberList.contains(num)) {
                questionNumberList.add(num);
            } else {
                i--;
            }
            numberButtons[i].setText(String.valueOf(num));
        }
    }

    private void selectNumber(int index) {
        int num = Integer.parseInt(numberButtons[index].getText().toString());
        if (!selectedNumbersList.contains(num)) {
            selectedNumbersList.add(num);
        }
        updateTextView();
    }

    private void updateTextView() {
        for (int i = 0; i < selectedNumbersList.size(); i++) {
            textView[i].setText(String.valueOf(selectedNumbersList.get(i)));
        }
    }

    public void onSubmitClick(View view) {
        isDescendingOrder();
    }

    private void isDescendingOrder() {
        answerNumberList = new ArrayList<>(selectedNumbersList);

        // Sort the copy in descending order
        Collections.sort(answerNumberList, Collections.reverseOrder());
        boolean isCorrect = true;

        // Check if each element in the sorted array is equal to its corresponding element in the original array
        for (int i = 0; i < selectedNumbersList.size(); i++) {
            if (!answerNumberList.get(i).equals(selectedNumbersList.get(i))) {
                isCorrect = false; // Numbers are not in descending order
                break;
            }
        }

        if (isCorrect) {// Numbers are in ascending order
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Game Over");
            alertDialogBuilder.setMessage("Numbers are in descending order!");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish(); // Close the activity
                }
            });
            alertDialogBuilder.show();
        }
        else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Game Over");
            alertDialogBuilder.setMessage("Numbers are not in descending order!");
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish(); // Close the activity
                }
            });
            alertDialogBuilder.show();
        }
    }
}