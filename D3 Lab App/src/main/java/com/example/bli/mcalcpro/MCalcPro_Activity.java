package com.example.bli.mcalcpro;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import ca.roumani.i2c.MPro;

public class MCalcPro_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);
        this.tts = new TextToSpeech(this, this);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void buttonClicked(View v)
    {
        EditText pB = (EditText) findViewById(R.id.pBox);
        String p = pB.getText().toString();

        EditText aB = (EditText) findViewById(R.id.aBox);
        String a = aB.getText().toString();

        EditText iB = (EditText) findViewById(R.id.iBox);
        String i = iB.getText().toString();

        try {
            MPro myModel = new MPro();
            myModel.setPrinciple(p);
            myModel.setAmortization(a);
            myModel.setInterest(i);

            String myPayment = "Monthly Payment = " + myModel.computePayment("%,.2f");
            myPayment += "\n\n";
            myPayment += "By making this payments monthly for " +a +" years, the mortgage will be paid in full. " +
                    "But if you terminate the mortgage on its nth anniversary, the balance still owing depends on n " +
                    "as it should below.";
            myPayment += "\n\n";
            myPayment += "       n         Balance";

            myPayment += "\n\n";
            myPayment += String.format("%8d", 0) + myModel.outstandingAfter(0, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 1) + myModel.outstandingAfter(1, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 2) + myModel.outstandingAfter(2, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 3) + myModel.outstandingAfter(3, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 4) + myModel.outstandingAfter(4, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 5) + myModel.outstandingAfter(5, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 10) + myModel.outstandingAfter(10, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 15) + myModel.outstandingAfter(15, "%,16.0f");
            myPayment += "\n\n";
            myPayment += String.format("%8d", 20) + myModel.outstandingAfter(20, "%,16.0f");

            ((TextView) findViewById(R.id.output)).setText(myPayment);
            tts.speak(myPayment, TextToSpeech.QUEUE_FLUSH, null);
        }
        catch (Exception e)
        {
            ((TextView) findViewById(R.id.output)).setText(e.getMessage());
            tts.speak(e.getMessage(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void onInit (int initStatus)
    {
        this.tts.setLanguage(Locale.US);
    }

    public void onAccuracyChanged (Sensor arg0, int arg1)
    {

    }

    public void onSensorChanged(SensorEvent event)
    {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];
        double a = Math.sqrt(ax*ax + ay*ay + az*az);
        if (a>20)
        {
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
    }


    public static void main(String[] args) {
        try {
            MPro mp = new MPro();
            mp.setPrinciple("400000");
            mp.setAmortization("20");
            mp.setInterest("5");
            System.out.println(mp.computePayment("%,.2f"));
            System.out.println(mp.outstandingAfter(2, "%,16.0f"));
        }
        catch(Exception e)
        {
         System.out.println(e.getMessage());
        }
    }
}
