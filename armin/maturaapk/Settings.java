package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    public LinearLayout linearSettings;
    private String thema;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    private TextView themes;
    private  TextView report;
    private TextView titlebar;
    private boolean navbar;
    private View line1;
    private View line2;
    private View line3;
    private CheckBox enableGerman;
    private CheckBox enableNavBar;
    private Drawable[] drawthemes;
    private Drawable[] drawReport;
    private Drawable[] drawENB;
    private Drawable[] drawGerman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        linearSettings = findViewById(R.id.linearsettings);
        themes= findViewById(R.id.themes);
        report = findViewById(R.id.report);
        line1 = findViewById(R.id.lineteint);
        line2 = findViewById(R.id.lineintnjem);
        line3 = findViewById(R.id.linennjemrep);
        enableGerman = findViewById(R.id.enableGerman);
        enableNavBar = findViewById(R.id.enableNavBar);
        drawthemes = themes.getCompoundDrawables();
        drawReport = report.getCompoundDrawables();
        drawENB = enableNavBar.getCompoundDrawables();
        drawGerman = enableGerman.getCompoundDrawables();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        thema = preferences.getString("wallpaper","navy");
        titlebar = findViewById(R.id.titlebar);
        navbar = preferences.getBoolean("navbar",false);
        boolean german = preferences.getBoolean("german",false);
        enableNavBar.setChecked(navbar);
        enableGerman.setChecked(german);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        if (thema.equalsIgnoreCase("gold")) {
            linearSettings.setBackground(getDrawable(R.drawable.gold));
            blackUI();
        } else if (thema.equalsIgnoreCase("navy")) {
            linearSettings.setBackground(getDrawable(R.drawable.navy));
            whiteUI();
        } else if (thema.equalsIgnoreCase("amethyst")) {
            linearSettings.setBackground(getDrawable(R.drawable.amethyst));
            whiteUI();
        } else if (thema.equalsIgnoreCase("aqua")) {
            linearSettings.setBackground(getDrawable(R.drawable.aqua));
            whiteUI();
        } else if (thema.equalsIgnoreCase("crystal")) {
            linearSettings.setBackground(getDrawable(R.drawable.crystal));
            whiteUI();
        } else if (thema.equalsIgnoreCase("garnet")) {
            linearSettings.setBackground(getDrawable(R.drawable.garnet));
            blackUI();
        } else if (thema.equalsIgnoreCase("jade")) {
            linearSettings.setBackground(getDrawable(R.drawable.jade));
            whiteUI();
        } else if (thema.equalsIgnoreCase("ocean")) {
            linearSettings.setBackground(getDrawable(R.drawable.ocean));
            whiteUI();
        } else if (thema.equalsIgnoreCase("sunset")) {
            linearSettings.setBackground(getDrawable(R.drawable.sunset));
            blackUI();
        } else if  (thema.equalsIgnoreCase("red")) {
            linearSettings.setBackground(getDrawable(R.drawable.red));
            blackUI();
        } else if  (thema.equalsIgnoreCase("silk")) {
            linearSettings.setBackground(getDrawable(R.drawable.silk));
            blackUI();
        } else if  (thema.equalsIgnoreCase("white")) {
            linearSettings.setBackgroundColor(Color.WHITE);
            blackUI();
        } else if  (thema.equalsIgnoreCase("black")) {
            linearSettings.setBackgroundColor(Color.BLACK);
            whiteUI();
        } else if  (thema.equalsIgnoreCase("cherry")) {
            linearSettings.setBackground(getDrawable(R.drawable.cherry));
            whiteUI();
        }

    }

    public void GOTOthemes(View view) {
        Intent i = new Intent(this, Themes.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        startActivity(i);
    }

    public void gotoFB(View view) {
        Intent i = new Intent(this, FeedbackActivity.class);
        startActivity(i);
    }

    public void navbar_toggle(View view) {
        if (enableNavBar.isChecked()){
            editor.putBoolean("navbar",true);
            editor.commit();
        }
        else{
            editor.putBoolean("navbar",false);
            editor.commit();
        }
    }

    public void german(View view) {
        if (enableGerman.isChecked()){
            editor.putBoolean("german",true);
            editor.commit();
        }
        else{
            editor.putBoolean("german",false);
            editor.commit();
        }
    }
    public void navi(View view) {
        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void whiteUI(){
        themes.setTextColor(Color.WHITE);
        report.setTextColor(Color.WHITE);
        enableGerman.setTextColor(Color.WHITE);
        enableNavBar.setTextColor(Color.WHITE);
        titlebar.setTextColor(Color.WHITE);
        drawthemes[0].setTint(Color.WHITE);
        drawReport[0].setTint(Color.WHITE);
        drawENB[0].setTint(Color.WHITE);
        drawGerman[0].setTint(Color.WHITE);
        int[][] states = {{android.R.attr.state_checked}, {}};
        int[] colors = {Color.WHITE, Color.GRAY};
        CompoundButtonCompat.setButtonTintList(enableGerman, new ColorStateList(states, colors));
        CompoundButtonCompat.setButtonTintList(enableNavBar, new ColorStateList(states, colors));
    }
    private void blackUI(){
        drawthemes[0].setTint(Color.BLACK);
        drawReport[0].setTint(Color.BLACK);
        drawENB[0].setTint(Color.BLACK);
        drawGerman[0].setTint(Color.BLACK);
        line3.setBackgroundColor(Color.BLACK);
        line2.setBackgroundColor(Color.BLACK);
        line1.setBackgroundColor(Color.BLACK);
    }
}
