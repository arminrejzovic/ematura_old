package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Scoreboard extends AppCompatActivity {

    private ImageView slika;
    private TextView score;
    private TextView ocjena;
    private String thema;
    private Button finish;
    private LinearLayout home;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public ArrayList<String> test;
    private Button rep;
    private boolean navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        ocjena = findViewById(R.id.grade);
        score = findViewById(R.id.Score);
        slika=findViewById(R.id.slika);
        home= findViewById(R.id.dom);
        finish = findViewById(R.id.finish);
        rep = findViewById(R.id.izvjestaj);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        thema = preferences.getString("wallpaper","navy");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        if (thema.equalsIgnoreCase("gold")) {
            home.setBackground(getDrawable(R.drawable.gold));
        } else if (thema.equalsIgnoreCase("navy")) {
            home.setBackground(getDrawable(R.drawable.navy));
            whiteUI();
        } else if (thema.equalsIgnoreCase("amethyst")) {
            home.setBackground(getDrawable(R.drawable.amethyst));
            whiteUI();
        } else if (thema.equalsIgnoreCase("aqua")) {
            home.setBackground(getDrawable(R.drawable.aqua));
            whiteUI();
        } else if (thema.equalsIgnoreCase("crystal")) {
            home.setBackground(getDrawable(R.drawable.crystal));
            whiteUI();
        } else if (thema.equalsIgnoreCase("garnet")) {
            home.setBackground(getDrawable(R.drawable.garnet));
        } else if (thema.equalsIgnoreCase("jade")) {
            home.setBackground(getDrawable(R.drawable.jade));
            whiteUI();
        } else if (thema.equalsIgnoreCase("ocean")) {
            home.setBackground(getDrawable(R.drawable.ocean));
            whiteUI();
        } else if (thema.equalsIgnoreCase("sunset")) {
            home.setBackground(getDrawable(R.drawable.sunset));
        } else if  (thema.equalsIgnoreCase("red")) {
            home.setBackground(getDrawable(R.drawable.red));
        } else if  (thema.equalsIgnoreCase("silk")) {
            home.setBackground(getDrawable(R.drawable.silk));
        } else if  (thema.equalsIgnoreCase("white")) {
            home.setBackgroundColor(Color.WHITE);
            finish.setBackgroundColor(Color.WHITE);
            rep.setBackgroundColor(Color.WHITE);
        } else if  (thema.equalsIgnoreCase("black")) {
            home.setBackgroundColor(Color.BLACK);
            ocjena.setTextColor(Color.WHITE);
            score.setTextColor(Color.WHITE);
            finish.setTextColor(Color.WHITE);
            finish.setBackgroundColor(Color.BLACK);
            rep.setTextColor(Color.WHITE);
            rep.setBackgroundColor(Color.BLACK);
        } else if  (thema.equalsIgnoreCase("cherry")) {
            home.setBackground(getDrawable(R.drawable.cherry));
            whiteUI();
        }

        Intent intent = getIntent();
        Integer scores = intent.getIntExtra("Correct",0);
        test = intent.getStringArrayListExtra("Lista");

        score.setText(score.getText()+" "+scores+"%");

        if (scores<=39){
            ocjena.setText(ocjena.getText()+" 1");
            slika.setImageResource(R.drawable.grade_f);
        }
        else if (scores<=54){
            ocjena.setText(ocjena.getText()+" 2");
            slika.setImageResource(R.drawable.grade_d);
        }
        else if (scores<=69){
            ocjena.setText(ocjena.getText()+" 3");
            slika.setImageResource(R.drawable.grade_c);
        }
        else if (scores<=84){
            ocjena.setText(ocjena.getText()+" 4");
            slika.setImageResource(R.drawable.grade_b);
        }
        else {
            ocjena.setText(ocjena.getText()+" 5");
            slika.setImageResource(R.drawable.grade_a);
        }
        if ((thema.equalsIgnoreCase("cherry"))||(thema.equalsIgnoreCase("red"))){
            slika.setColorFilter(Color.WHITE);
        }
    }

    public void finish(View view) {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void onBackPressed() {

    }

    public void doReport(View view) {
        Intent i = new Intent(this, Report.class);
        i.putExtra("Lista",test);
        startActivity(i);
    }
    public void navi(View view) {
        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void whiteUI(){
        ocjena.setTextColor(Color.WHITE);
        score.setTextColor(Color.WHITE);
        finish.setTextColor(Color.WHITE);
        rep.setTextColor(Color.WHITE);
    }
}
