package com.example.cmpe408mtcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int MAX_ALLOWED_OPERATION_LENGTH = 4;

    private TextView operationsTv, resultTv;
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

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        operationsTv.setText(buttonText);
    }
}