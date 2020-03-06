package com.gaurav.gauravscorekeeper;

/* I have compare my project with Faisal, and looking at his way of coding and mine I found several differences,
- First of all, he used one function for each button to maintain score. Which is fine for this project but can be difficult in maintain in larger projects
- He used tags to get value from the button directly which is much efficient than using the switch case like I did.
- Because of me using switch case statement, many lines of my code are redundant now that I got a chance to analyze it, even if else would have been better
- Unlike Faisal, I like to write each variable on a single line, I think it is more readable way to write code, it may will make difference for bigger projects
- He is also using the default constraint layout for the project, and I hve used grid layout which I think is much flexible.
- I did had to add many unnecessary empty text views to maintain proper spacing between the elements but it that is just because of my lack of knowledge
- I did like his id naming style, which is more descriptive than that i have used, this maybe small project but it is a good practice
- He also hs made his program look much better using images, but I only did the bare minimum for looks
*/

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreA;
    private TextView scoreB;
    private RadioGroup radbut;
    private int rate = 1;
    private int a_sco = 0;
    private int b_sco = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreA = (TextView) findViewById(R.id.team_a_sco);
        scoreB = (TextView) findViewById(R.id.team_b_sco);
        radbut = (RadioGroup) findViewById(R.id.setting);

        radbut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
