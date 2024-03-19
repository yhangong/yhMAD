package com.example.madindividual;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class CompareNumbersActivity extends AppCompatActivity {
    TextView textViewNumbers;
    Button buttonYes, buttonNo, buttonExit;
    TextView textViewScore;
    int score = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers);
        textViewNumbers = findViewById(R.id.textViewNumbers);
        buttonYes = findViewById(R.id.buttonYes);
        buttonNo = findViewById(R.id.buttonNo);
        textViewScore = findViewById(R.id.textViewScore);
        buttonExit = findViewById(R.id.buttonExit);

        generateNewNumbers();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewNumbers.getText() != null && !textViewNumbers.getText().toString().isEmpty()) {
                    String[] numbersAndOperator = textViewNumbers.getText().toString().split(" ");
                    if (numbersAndOperator.length == 3) {
                        int num1 = Integer.parseInt(numbersAndOperator[0]);
                        int num2 = Integer.parseInt(numbersAndOperator[2]);
                        String operator = numbersAndOperator[1];
                        if (operator.equals("less")) {
                            if (num1 < num2) {
                                score++;
                            }
                        } else if (operator.equals("equal")) {
                            if (num1 == num2) {
                                score++;
                            }
                        } else if (operator.equals("greater")) {
                            if (num1 > num2) {
                                score++;
                            }
                        }
                    }
                }
                updateScore();
                count++;
                checkGameEnd();
                generateNewNumbers();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textViewNumbers.getText() != null && !textViewNumbers.getText().toString().isEmpty()) {
                    String[] numbersAndOperator = textViewNumbers.getText().toString().split(" ");
                    if (numbersAndOperator.length == 3) {
                        int num1 = Integer.parseInt(numbersAndOperator[0]);
                        int num2 = Integer.parseInt(numbersAndOperator[2]);
                        String operator = numbersAndOperator[1];
                        if (operator.equals("less")) {
                            if (num1 >= num2) {
                                score++;
                            }
                        } else if (operator.equals("equal")) {
                            if (num1 != num2) {
                                score++;
                            }
                        } else if (operator.equals("greater")) {
                            if (num1 <= num2) {
                                score++;
                            }
                        }
                    }
                }
                updateScore();
                count++;
                checkGameEnd();
                generateNewNumbers();
            }
        });
    }


    private void generateNewNumbers() {
        Random rand = new Random();
        int num1 = rand.nextInt(100);
        int num2 = rand.nextInt(100);

        // Generate a random comparison operator
        int randomOperator = rand.nextInt(3);
        String operator;
        switch (randomOperator) {
            case 0:
                operator = "less";
                break;
            case 1:
                operator = "equal";
                break;
            default:
                operator = "greater";
                break;
        }

        textViewNumbers.setText(num1 + " " + operator + " " + num2);
    }

    private void updateScore() {
        textViewScore.setText("Score: " + score);
    }

    private void checkGameEnd() {
        if (count == 10) {
            buttonNo.setOnClickListener(null);
            buttonYes.setOnClickListener(null);

            // Game ended, show final score or perform other actions
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