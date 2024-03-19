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
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ComposeNumbersActivity extends AppCompatActivity {
    TextView textViewNumber, firstnumber, secondnumber;
    Button[] numberButtons;
    Button buttonExit;
    int targetNumber;
    List<Integer> selectedNumbers, questionNumberList;
    int score = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_numbers);

        textViewNumber = findViewById(R.id.textViewNumber);
        firstnumber = findViewById(R.id.firstnumber);
        secondnumber = findViewById(R.id.secondnumber);
        buttonExit = findViewById(R.id.buttonExit);
        questionNumberList = new ArrayList<>();


        // Array to store references to number buttons
        numberButtons = new Button[6];
        numberButtons[0] = findViewById(R.id.buttonNumber1);
        numberButtons[1] = findViewById(R.id.buttonNumber2);
        numberButtons[2] = findViewById(R.id.buttonNumber3);
        numberButtons[3] = findViewById(R.id.buttonNumber4);
        numberButtons[4] = findViewById(R.id.buttonNumber5);
        numberButtons[5] = findViewById(R.id.buttonNumber6);

        // Initialize selectedNumbers list
        selectedNumbers = new ArrayList<>();

        // Generate new target number and populate number buttons
        generateNewNumber();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Set click listeners for number buttons
        for (int i = 0; i < numberButtons.length; i++) {
            final int index = i;
            numberButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selectedNumber = Integer.parseInt(numberButtons[index].getText().toString());
                    selectNumber(selectedNumber);
                }
            });
        }
    }

    private void generateNewNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
        textViewNumber.setText("Compose " + targetNumber);
        selectedNumbers.clear();

        // Generate two correct numbers
        int correctNumber1 = rand.nextInt(targetNumber) + 1;
        int correctNumber2 = targetNumber - correctNumber1;

        // Generate four random numbers
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            numbers.add(i + 1);
        }
        numbers.remove(Integer.valueOf(correctNumber1));
        numbers.remove(Integer.valueOf(correctNumber2));
        Collections.shuffle(numbers);
        List<Integer> randomNumbers = numbers.subList(0, 4);

        // Merge the correct and random numbers
        List<Integer> allNumbers = new ArrayList<>();
        allNumbers.add(correctNumber1);
        allNumbers.add(correctNumber2);
        allNumbers.addAll(randomNumbers);
        Collections.shuffle(allNumbers);

        // Set the numbers on the buttons
        for (int i = 0; i < 6; i++) {
            numberButtons[i].setText(String.valueOf(allNumbers.get(i)));
        }
    }

    private void selectNumber(int number) {
        if (selectedNumbers.size() < 2) {
            // Check if the number is already selected
            if (!selectedNumbers.contains(number)) {
                selectedNumbers.add(number);
                if (selectedNumbers.size() == 1) {
                    // If the first number is selected, display it in the firstnumber TextView
                    TextView firstNumberTextView = findViewById(R.id.firstnumber);
                    firstNumberTextView.setText(String.valueOf(number));
                } else if (selectedNumbers.size() == 2) {
                    // If the second number is selected, display it in the secondnumber TextView
                    TextView secondNumberTextView = findViewById(R.id.secondnumber);
                    secondNumberTextView.setText(String.valueOf(number));
                }
            } else {
                // Inform the user that the number is already selected
                Toast.makeText(this, "This number is already selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onSubmitClick(View view) {
        if (selectedNumbers.size() == 2) {
            checkSum();
            generateNewNumber();
        } else {
            // Inform the user to select two numbers before submitting
            Toast.makeText(this, "Please select two numbers before submitting", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkSum() {
        int sum = selectedNumbers.get(0) + selectedNumbers.get(1);
        if (sum == targetNumber) {
            score++;
        }
        count++;
        firstnumber.setText("");
        secondnumber.setText("");
        updateScore();
        checkGameEnd();
    }

    private void updateScore() {
        TextView textViewScore = findViewById(R.id.textViewScore);
        textViewScore.setText("Score: " + score);
    }

    private void checkGameEnd() {
        if (count == 10) {
            // Game ended, show final score
            showFinalScoreAlert();
        }
    }

    private void showFinalScoreAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Game Over");
        alertDialogBuilder.setMessage("Your final score is: " + score);
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