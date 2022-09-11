package com.example.bli.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_layout);
    }

    public void buttonClicked(View v)
    {
        EditText weightView = (EditText) findViewById(R.id.weightBox);
        String weight = weightView.getText().toString();

        EditText heightView = (EditText) findViewById(R.id.heightBox);
        String height = heightView.getText().toString();

        BMIModel myModel = new BMIModel(weight, height);
        String myBMI = myModel.getBMI();

        ((TextView) findViewById(R.id.answer)).setText(myBMI);
    }

    public static void main(String[] args) {
        BMIModel myModel = new BMIModel("100", "1.8");
        System.out.println(myModel.getBMI());

        myModel = new BMIModel("45", "1.35");
        System.out.println(myModel.getBMI());

        myModel = new BMIModel("80", "1.2");
        System.out.println(myModel.getBMI());
    }
}
