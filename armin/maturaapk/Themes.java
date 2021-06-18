package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Themes extends AppCompatActivity {

    private String themeName;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        boolean navbar = sharedPreferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }

    public void amethyst(View view) {
        themeName = "Amethyst";
        editor.putString("wallpaper","Amethyst");
        editor.commit();
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        startActivity(i);
    }

    public void sunset(View view) {
        themeName = "Sunset";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Sunset");
        editor.commit();
        startActivity(i);
    }


    public void ocean(View view) {
        themeName = "Ocean";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Ocean");
        editor.commit();
        startActivity(i);
    }

    public void navy(View view) {
        themeName = "Navy";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Navy");
        editor.commit();
        startActivity(i);
    }


    public void jade(View view) {
        themeName = "Jade";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Jade");
        editor.commit();
        startActivity(i);
    }


    public void gold(View view) {
        themeName = "Gold";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Gold");
        editor.commit();
        startActivity(i);
    }


    public void garnet(View view) {
        themeName = "Garnet";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Garnet");
        editor.commit();
        startActivity(i);
    }


    public void crystal(View view) {
        themeName = "Crystal";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Crystal");
        editor.commit();
        startActivity(i);
    }

    public void aque(View view) {
        themeName = "Aqua";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Aqua");
        editor.commit();
        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Settings.class);
        //i.putExtra("themeV", themeName);
        startActivity(i);
    }

    public void red (View view) {
        themeName = "Ruby";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Ruby");
        editor.commit();
        startActivity(i);
    }

    public void black(View view) {
        themeName = "Black";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Black");
        editor.commit();
        startActivity(i);
    }

    public void white(View view) {
        themeName = "White";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","White");
        editor.commit();
        startActivity(i);
    }

    public void silk(View view) {
        themeName = "Silk";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Silk");
        editor.commit();
        startActivity(i);
    }

    public void cherry(View view) {
        themeName = "Cherry";
        Intent i = new Intent(this, Home.class);
        //i.putExtra("themeV", themeName);
        editor.putString("wallpaper","Cherry");
        editor.commit();
        startActivity(i);
    }
}
