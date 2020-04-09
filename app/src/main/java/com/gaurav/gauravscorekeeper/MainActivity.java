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
import android.util.Log;
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

    SharedPreferences setting;
    boolean god_mode_check;

    private int rate;
    private int a_sco = 0;
    private int b_sco = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setting = PreferenceManager.getDefaultSharedPreferences(this);

        scoreA = findViewById(R.id.team_a_sco);
        scoreB = findViewById(R.id.team_b_sco);
        radbut = findViewById(R.id.setting);
        teama = findViewById(R.id.team_a);
        teamb = findViewById(R.id.team_b);

        teama.setText(setting.getString("team_a_name", "Team A"));
        teamb.setText(setting.getString("team_b_name", "Team B"));

        god_mode_check = setting.getBoolean("god", false);

        if (god_mode_check){
            a_sco = setting.getInt("a_sco",0);
            b_sco = setting.getInt("b_sco",0);
            rate = setting.getInt("rate", 1);
            display();
        } else {
            SharedPreferences.Editor edit = setting.edit();
            edit.putInt("rate",Integer.parseInt(setting.getString("inc_dec","1")));
            edit.putInt("a_sco",0);
            edit.putInt("b_sco",0);
            edit.apply();
            rate = setting.getInt("rate", 1);
        }


        if (rate == 1){
            radbut.check(R.id.one);
        } else if (rate == 2){
            radbut.check(R.id.two);
        } else if (rate == 3){
            radbut.check(R.id.three);
        }

        radbut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one: rate = 1;
                    rateSaver();
                    break;

                    case R.id.two: rate = 2;
                    rateSaver();
                    break;

                    case R.id.three: rate = 3;
                    rateSaver();
                    break;

                    default:
                        rate = Integer.parseInt(setting.getString("inc_dec", "1"));
                        rateSaver();
                    break;
                }
            }

        });

    }

    public void rateSaver(){
        if (god_mode_check){
            SharedPreferences.Editor edit = setting.edit();
            edit.putInt("rate",rate);
            edit.apply();
        }
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
        scoreA.setText(Integer.toString(a_sco));
        scoreB.setText(Integer.toString(b_sco));
    }

    public void Add(View v){
        switch (v.getId()){
            case R.id.team_a_plu: a_sco = a_sco + rate;
                scoreSaver("a",a_sco);
            break;

            case R.id.team_b_plu: b_sco = b_sco + rate;
                scoreSaver("b",b_sco);
            break;

            default:
            break;
        }
        display();
    }

    public void scoreSaver(String team, int val){

        if (god_mode_check){
            SharedPreferences.Editor edit = setting.edit();
            if (team == "a"){
                edit.putInt("a_sco",val);
            } else {
                edit.putInt("b_sco",val);
            }
            edit.apply();
        }
    }

    public void Subtract(View v){
        switch (v.getId()){
            case R.id.team_a_min: a_sco = a_sco - rate;
                scoreSaver("a",a_sco);
                break;

            case R.id.team_b_min: b_sco = b_sco - rate;
                scoreSaver("b",b_sco);
                break;

            default:
                break;
        }
        display();
    }

}
