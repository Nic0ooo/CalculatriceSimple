package com.fr.esgi.calculatricesimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView result;
    private double operand1 = 0;
    private double operand2 = 0;
    private String currentOperator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.resultat);

        setNumberButtonListeners();
        setOperatorButtonListeners();
        setEqualsButtonListener();
        setClearButtonListener();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String number = button.getText().toString();
                if (isNewOperation) {
                    result.setText(number);
                    isNewOperation = false;
                } else {
                    result.append(number);
                }
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {R.id.addition, R.id.soustraction, R.id.multiplication, R.id.division};

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentOperator = button.getText().toString();
                operand1 = Double.parseDouble(result.getText().toString());
                isNewOperation = true;
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setEqualsButtonListener() {
        findViewById(R.id.egal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operand2 = Double.parseDouble(result.getText().toString());
                double result = 0;
                switch (currentOperator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            result.setText("Error");
                            return;
                        }
                        break;
                }
                result.setText(String.valueOf(result));
                isNewOperation = true;
            }
        });
    }

    private void setClearButtonListener() {
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("0");
                operand1 = 0;
                operand2 = 0;
                currentOperator = "";
                isNewOperation = true;
            }
        });
    }
}
