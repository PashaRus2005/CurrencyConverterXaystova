package com.example.creditcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLoanAmount, editTextInterestRate, editTextLoanTerm;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextLoanTerm = findViewById(R.id.editTextLoanTerm);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanPayment();
            }
        });
    }

    private void calculateLoanPayment() {
        double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
        double interestRate = Double.parseDouble(editTextInterestRate.getText().toString());
        double loanTerm = Double.parseDouble(editTextLoanTerm.getText().toString());

        double monthlyInterestRate = interestRate / 12 / 100;
        double numOfPayments = loanTerm * 12;
        double monthlyPayment = loanAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numOfPayments)));

        @SuppressLint("DefaultLocale") String result = String.format("Ежемесячный платеж: %.2f руб.", monthlyPayment);

        textViewResult.setText(result);
    }
}
