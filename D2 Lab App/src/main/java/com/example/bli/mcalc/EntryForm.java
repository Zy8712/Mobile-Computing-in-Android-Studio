package com.example.bli.mcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EntryForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mortgage_layout);
    }

    public void buttonClicked(View v)
    {
        EditText pri = (EditText) findViewById(R.id.principle);
        String p = pri.getText().toString();

        EditText amor = (EditText) findViewById(R.id.amortization);
        String a = amor.getText().toString();

        EditText inter = (EditText) findViewById(R.id.interest);
        String i = inter.getText().toString();

        MortgageModel myModel = new MortgageModel(p, a, i);
        String myPayment = myModel.computePayment();

        ((TextView) findViewById(R.id.answer)).setText(myPayment);
    }

    public static void main(String[] args) {
        MortgageModel myModel = new MortgageModel ("700000", "25", "2.75");
        System.out.println(myModel.computePayment());

        myModel = new MortgageModel("300000", "20", "4.50");
        System.out.println(myModel.computePayment());
    }
}


