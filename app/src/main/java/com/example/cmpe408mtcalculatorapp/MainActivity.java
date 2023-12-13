package com.example.cmpe408mtcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int MAX_ALLOWED_OPERATION_LENGTH = 4;

    private TextView operationsTv;
    private TextView resultTv;
    private Button modBtn, clearAllBtn, clearBtn, deleteBtn,
            oneOverXBtn, squareBtn, squareRootBtn, divisionBtn,
            sevenBtn, eightBtn, nineBtn, multiplyBtn,
            fourBtn, fiveBtn, sixBtn, subtractionBtn,
            oneBtn, twoBtn, threeBtn, additionBtn,
            plusMinusBtn, zeroBtn, decimalBtn, equalsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationsTv = findViewById(R.id.operations);
        resultTv = findViewById(R.id.result);

        assignButtonIds(modBtn, R.id.modBtn);
        assignButtonIds(clearAllBtn, R.id.clearAllBtn);
        assignButtonIds(clearBtn, R.id.clearBtn);
        assignButtonIds(deleteBtn, R.id.deleteBtn);
        assignButtonIds(oneOverXBtn, R.id.oneOverXBtn);
        assignButtonIds(squareBtn, R.id.squareBtn);
        assignButtonIds(squareRootBtn, R.id.squareRootBtn);
        assignButtonIds(divisionBtn, R.id.divisionBtn);
        assignButtonIds(sevenBtn, R.id.sevenBtn);
        assignButtonIds(eightBtn, R.id.eightBtn);
        assignButtonIds(nineBtn, R.id.nineBtn);
        assignButtonIds(multiplyBtn, R.id.multiplyBtn);
        assignButtonIds(fourBtn, R.id.fourBtn);
        assignButtonIds(fiveBtn, R.id.fiveBtn);
        assignButtonIds(sixBtn, R.id.sixBtn);
        assignButtonIds(subtractionBtn, R.id.subtractionBtn);
        assignButtonIds(oneBtn, R.id.oneBtn);
        assignButtonIds(twoBtn, R.id.twoBtn);
        assignButtonIds(threeBtn, R.id.threeBtn);
        assignButtonIds(additionBtn, R.id.additionBtn);
        assignButtonIds(plusMinusBtn, R.id.plusMinusBtn);
        assignButtonIds(zeroBtn, R.id.zeroBtn);
        assignButtonIds(decimalBtn, R.id.decimalBtn);
        assignButtonIds(equalsBtn, R.id.equalsBtn);
    }

    void assignButtonIds(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    void checkIfOperationIsEmpty(String operations) {
        if (operations.isEmpty()) {
            return;
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();

        // Concat the operations in textview
        String operations = operationsTv.getText().toString();

        if (buttonText.equals("C") || buttonText.equals("CE")) {
            operationsTv.setText("");
            resultTv.setText("");
            return;
        }

        if (buttonText.equals("=")) {
            // checkIfOperationIsEmpty(operations);
            operationsTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("D")) {
            // checkIfOperationIsEmpty(operations);
            operations = operations.substring(0, operations.length() - 1);
        } else {
            operationsTv.setText("");
            operations = operations + buttonText;
        }

        operationsTv.setText(operations);

        String finalResult = getResult(operations);

        if(!finalResult.equals("something went wrong")){
            resultTv.setText(finalResult);
        }


//        // If the operations is empty, then only allow the user to enter numbers
//        if (operations.isEmpty()) {
//            if (buttonText.equals("0")) {
//                return;
//            }
//            if (buttonText.equals(".")) {
//                operationsTv.setText("0.");
//                return;
//            }
//        }
//
//        // If the operations is not empty, then only allow the user to enter numbers and operators
//        if (!operations.isEmpty()) {
//            if (buttonText.equals("0")) {
//                operationsTv.setText(operations + buttonText);
//                return;
//            }
//            if (buttonText.equals(".")) {
//                if (operations.contains(".")) {
//                    return;
//                }
//                operationsTv.setText(operations + buttonText);
//                return;
//            }
//            if (buttonText.equals("+") || buttonText.equals("-") || buttonText.equals("x") || buttonText.equals("÷")) {
//                if (operations.length() >= MAX_ALLOWED_OPERATION_LENGTH) {
//                    return;
//                }
//                operationsTv.setText(operations + buttonText);
//                return;
//            }
//        }
    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "something went wrong";
        }
    }
}