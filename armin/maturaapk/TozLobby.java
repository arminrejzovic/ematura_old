package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class TozLobby extends AppCompatActivity {

    private CheckBox practice;
    private CheckBox simulation;
    private boolean practiceMode;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public String theme;
    private boolean navbar;

    private TextView disclaimer;
    private TextView lobbyTitle;
    private Button startButton;
    private View line1;
    private View line2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        theme = preferences.getString("wallpaper","navy");

        try {
            int styleID = getResources().getIdentifier(theme,"style",getPackageName());
            setTheme(styleID);
        }
        catch (Exception e) {
            int backgroundID = getResources().getIdentifier(theme.toLowerCase(),"color",getPackageName());
            getWindow().setBackgroundDrawableResource(backgroundID);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toz_lobby);

        practice = findViewById(R.id.treningCB);
        simulation = findViewById(R.id.simulationCB);
        disclaimer = findViewById(R.id.disclaimer);
        lobbyTitle = findViewById(R.id.naslovLobby);
        line1 = findViewById(R.id.linetr);
        line2 = findViewById(R.id.linehc);
        startButton = findViewById(R.id.start);
        practiceMode = true;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        TypedArray typedArray = obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        @ColorInt int colorAccent = typedArray.getColor(0, 0);
        typedArray.recycle();
        line1.setBackground(new ColorDrawable(colorAccent));
        line2.setBackground(new ColorDrawable(colorAccent));
        practice.setTextColor(colorAccent);
        simulation.setTextColor(colorAccent);
        int[][] states = {{android.R.attr.state_checked}, {}};
        int[] colors = {colorAccent, Color.GRAY};
        CompoundButtonCompat.setButtonTintList(practice, new ColorStateList(states, colors));
        CompoundButtonCompat.setButtonTintList(simulation, new ColorStateList(states, colors));
    }



    public void start(View view) {
        if (practiceMode){
        Intent i = new Intent(this, TozTrening.class);
        startActivity(i);
        }
        else if (!practiceMode) {
            Intent i = new Intent(this, HardcoreActivity.class);
            startActivity(i);
        }
    }

    public void treningMode(View view) {
            practice.setChecked(true);
            simulation.setChecked(false);
            practiceMode = true;
            disclaimer.setVisibility(View.INVISIBLE);
    }


    public void hardcoreMode(View view) {
        practice.setChecked(false);
        simulation.setChecked(true);
        practiceMode = false;
        disclaimer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void hideNavigationBar(View view) {
        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
