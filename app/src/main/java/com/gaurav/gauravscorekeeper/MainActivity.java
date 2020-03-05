package com.gaurav.gauravscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreA;
    private TextView scoreB;
    private RadioGroup rb;
    private int rate = 1;
    private int a_sco = 0;
    private int b_sco = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = (TextView) findViewById(R.id.team_a_sco);
        scoreB = (TextView) findViewById(R.id.team_b_sco);
        rb = (RadioGroup) findViewById(R.id.setting);

        rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one: rate = 1;
                    break;

                    case R.id.two: rate = 2;
                    break;

                    case R.id.three: rate = 3;
                    break;

                    default: rate = 1;
                    break;
                }
            }

        });
    }

    public void display(){
        scoreA.setText(Integer.toString(a_sco));
        scoreB.setText(Integer.toString(b_sco));
    }

    public void Add(View v){
        switch (v.getId()){
            case R.id.team_a_plu: a_sco = a_sco + rate;
            break;

            case R.id.team_b_plu: b_sco = b_sco + rate;
            break;

            default: a_sco = a_sco;
            b_sco = b_sco;
            break;
        }
        display();
    }

    public void Subtract(View v){
        switch (v.getId()){
            case R.id.team_a_min: a_sco = a_sco - rate;
                break;

            case R.id.team_b_min: b_sco = b_sco - rate;
                break;

            default: a_sco = a_sco;
                b_sco = b_sco;
                break;
        }
        display();
    }

}
