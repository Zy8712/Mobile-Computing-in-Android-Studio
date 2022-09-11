package com.example.bli.caps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CapsActivity extends AppCompatActivity {

    private Game game = new Game();
    private String question;
    private String answer;
    private int score = 0;
    private int qNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);
        ask();
    }

    private void ask()
    {
        qNum++;
        String qy = "Q# " +qNum;
        ((TextView) findViewById(R.id.qNum)).setText(qy);
        String q = game.qa();
        String regex = "\n";
        String [] qva = q.split(regex);
        question = qva[0];
        answer = qva[1];
        ((TextView) findViewById(R.id.question)).setText(question);
    }

    public void onDone(View v)
    {
        EditText answerView = (EditText) findViewById(R.id.answer);
        String ans = answerView.getText().toString().toUpperCase();

        String p = "\n" +"Q# "+qNum +": " +question +"\n" +"Your Answer: " +ans +"\n" +"Correct Answer: "+answer +"\n";
        ((TextView) findViewById(R.id.log)).append(p);
        answerView.setText("");

        if (ans.equals(answer.toUpperCase()))
        {
            score++;
            String s = String.format("%d", score);
            String s2 = "Score: " +s;
            ((TextView) findViewById(R.id.score)).setText(s2);
        }

        if(qNum == 9) //Check if it is question number 9
        {
            Button btn = (Button) findViewById(R.id.done);
            btn.setEnabled(false);
            ((TextView) findViewById(R.id.qNum)).setText("GAME OVER");
        }

        if (qNum < 9)
        {
            ask();
        }

        if (qNum == 10)
        {
            finish();
        }
    }
}
