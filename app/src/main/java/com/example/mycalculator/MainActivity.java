package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private GridLayout gl;
   private Button clear ;
    private  TextView cal_text,result;
    private StringBuilder input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = new StringBuilder();

        gl = findViewById(R.id.gridLayout);
        cal_text = findViewById(R.id.calc);


        }
    private double eval(String expression) {
        try {
            Stack<Double> values = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for (int i = 0; i < expression.length(); i++) {
                char currentChar = expression.charAt(i);

                if (Character.isDigit(currentChar) || currentChar == '.') {
                    StringBuilder operand = new StringBuilder();
                    while (i < expression.length() &&
                            (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                        operand.append(expression.charAt(i));
                        i++;
                    }
                    i--;

                    values.push(Double.parseDouble(operand.toString()));
                } else if (currentChar == '(') {
                    operators.push(currentChar);
                } else if (currentChar == ')') {
                    while (operators.peek() != '(') {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.pop(); // Pop the '('
                } else if (isOperator(currentChar)) {
                    while (!operators.isEmpty() && hasPrecedence(currentChar, operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(currentChar);
                }
            }

            while (!operators.isEmpty()) {
                values.push(applyOperation(operators.pop(),values.pop(), values.pop()));
            }

            return values.pop();
        } catch (Exception e) {
            cal_text.setText("ERROR");
            return Double.NaN; // Or any other appropriate value
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == 'X' || c == '/' || c == '%';
    }

    private boolean hasPrecedence(char op1, char op2) {
        return (op1 == 'X' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-');
    }

    private double applyOperation(char operator, double operand2 , double operand1) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case 'X':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return formatResult(operand1 / operand2);
            case '%':

                    double result = (operand1 /100)* operand2;
                    return formatResult(result);

            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private double formatResult(double result) {
        // Format the result to have at most 6 decimal places
        DecimalFormat df = new DecimalFormat("#.######");
        return Double.parseDouble(df.format(result));
    }

    public void onButtonClick(View view) {
//        Button button = (Button) view;
//        input.append(button.getText().toString());
//        cal_text.setText(input.toString());

        Button button = (Button) view;
        String buttonText = button.getText().toString();

        // Check if the last character is an operator and the current button is an operator
        if (isOperator(buttonText.charAt(0)) && input.length() > 0) {
            char lastChar = input.charAt(input.length() - 1);
            if (isOperator(lastChar) && lastChar != '-') {
                // Replace the last operator with the new one
                input.setCharAt(input.length() - 1, buttonText.charAt(0));
                updateDisplay();
                return;
            }
        }

        // Append the button text to the input
        input.append(buttonText);
        updateDisplay();
    }
    public void onBackButtonClick(View view) {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            updateDisplay(); // Update the display after removing the last character
        }
    }

    // Method to update the display with the current input
    private void updateDisplay() {
        cal_text.setText(input.toString());
    }

    public void onEqualsButtonClick(View view) {
        try {
            // Evaluate the expression using eval() method
            double result = eval(input.toString());

            // Display the result
            cal_text.setText(String.valueOf(result));

            // Clear the input for the next calculation
            input.setLength(0);
            input.append(String.valueOf(result));
        } catch (Exception e) {
            // Handle the case where the expression is invalid
            cal_text.setText("Error");
        }
    }
    public void onClearButtonClick(View view) {
        input.setLength(0);
        cal_text.setText("0");
    }


}