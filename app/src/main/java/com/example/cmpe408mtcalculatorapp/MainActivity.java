package com.example.cmpe408mtcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView operationsTv;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationsTv = findViewById(R.id.operations);
        resultTv = findViewById(R.id.result);

        // Initialize buttons and set click listeners
        initButton(R.id.modBtn);
        initButton(R.id.clearAllBtn);
        initButton(R.id.clearBtn);
        initButton(R.id.deleteBtn);
        initButton(R.id.oneOverXBtn);
        initButton(R.id.squareBtn);
        initButton(R.id.squareRootBtn);
        initButton(R.id.divisionBtn);
        initButton(R.id.sevenBtn);
        initButton(R.id.eightBtn);
        initButton(R.id.nineBtn);
        initButton(R.id.multiplyBtn);
        initButton(R.id.fourBtn);
        initButton(R.id.fiveBtn);
        initButton(R.id.sixBtn);
        initButton(R.id.subtractionBtn);
        initButton(R.id.oneBtn);
        initButton(R.id.twoBtn);
        initButton(R.id.threeBtn);
        initButton(R.id.additionBtn);
        initButton(R.id.plusMinusBtn);
        initButton(R.id.zeroBtn);
        initButton(R.id.decimalBtn);
        initButton(R.id.equalsBtn);
    }

    private void initButton(int id) {
        Button button = findViewById(id);
        button.setOnClickListener(this);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void checkIfOperationIsEmpty(String operations) {
        if (operations.isEmpty()) {
            showToast("Please enter a number first");
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        // Concatenate the operations in the TextView
        String operations = operationsTv.getText().toString();

        if (buttonText.equals("C") || buttonText.equals("CE")) {
            operationsTv.setText("");
            resultTv.setText("");
            return;
        }

        if (buttonText.equals("=")) {
            checkIfOperationIsEmpty(operations);
            operationsTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("D")) {
            if (!operations.isEmpty()) {
                operations = operations.substring(0, operations.length() - 1);
            }
        } else if (buttonText.equals("1/x") || buttonText.equals("x²") || buttonText.equals("√x")) {
            operations = getResult(operations, buttonText);
        } else {
            operationsTv.setText("");
            operations += buttonText;
        }

        operationsTv.setText(operations);

        String finalResult = getResult(operations, buttonText);

        if (!finalResult.equals("error")) {
            resultTv.setText(finalResult);
        }
    }

    private String getResult(String data, String button) {
        try {
            String result;
            if (button.equals("1/x")) {
                result = String.valueOf(1 / Double.parseDouble(data));
            } else if (button.equals("x²")) {
                result = String.valueOf(Double.parseDouble(data) * Double.parseDouble(data));
            } else if (button.equals("√x")) {
                result = String.valueOf(Math.pow(Double.parseDouble(data), 0.5));
            } else {
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
                if (result.endsWith(".0")) {
                    result = result.replace(".0", "");
                }
            }
            return result;
        } catch (ArithmeticException e) {
            showToast("Error: " + e.getMessage());
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }
}