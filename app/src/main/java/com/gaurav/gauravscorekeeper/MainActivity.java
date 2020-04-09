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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView scoreA, teama;
    private TextView scoreB, teamb;
    private RadioGroup radbut;

    private int rate;
    private int a_sco = 0;
    private int b_sco = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sett = PreferenceManager.getDefaultSharedPreferences(this);

        final int def_rate = Integer.parseInt(sett.getString("inc_dec", "1"));

        scoreA = findViewById(R.id.team_a_sco);
        scoreB = findViewById(R.id.team_b_sco);
        radbut = findViewById(R.id.setting);
        teama = findViewById(R.id.team_a);
        teamb = findViewById(R.id.team_b);

        teama.setText(sett.getString("team_a_name", "Team A"));
        teamb.setText(sett.getString("team_b_name", "Team B"));

        if (def_rate == 1){
            radbut.check(R.id.one);
        } else if (def_rate == 2){
            radbut.check(R.id.two);
        } else {
            radbut.check(R.id.three);
        }

        rate = def_rate;

        radbut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one: rate = 1;
                    break;

                    case R.id.two: rate = 2;
                    break;

                    case R.id.three: rate = 3;
                    break;

                    default:
                        rate = def_rate;
                    break;
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.Settings){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else {
            Toast.makeText(getBaseContext(),"Gaurav Raj Ghimire", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void display(){
        scoreA.setText((a_sco));
        scoreB.setText((b_sco));
    }

    public void Add(View v){
        switch (v.getId()){
            case R.id.team_a_plu: a_sco = a_sco + rate;
            break;

            case R.id.team_b_plu: b_sco = b_sco + rate;
            break;

            default:
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

            default:
                break;
        }
        display();
    }

}
