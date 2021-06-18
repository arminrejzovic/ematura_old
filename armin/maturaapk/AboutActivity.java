package com.example.armin.maturaapk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private TextView tekst;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private String thema;
    private View line1;
    private View line2;
    private LinearLayout home;
    private TextView title;
    private boolean navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        thema = preferences.getString("wallpaper","Navy");

        try {
            int styleID = getResources().getIdentifier(thema,"style",getPackageName());
            setTheme(styleID);
        }
        catch (Exception e) {
            int backgroundID = getResources().getIdentifier(thema.toLowerCase(),"color",getPackageName());
            getWindow().setBackgroundDrawableResource(backgroundID);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tekst = findViewById(R.id.tekst);
        home = findViewById(R.id.abouthome);
        title=findViewById(R.id.titleAbout);
        line1=findViewById(R.id.topline);
        line2=findViewById(R.id.bottomline);

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
