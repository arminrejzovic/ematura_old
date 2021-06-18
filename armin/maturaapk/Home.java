package com.example.armin.maturaapk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    public String thema;
    private boolean navbar;

    private TextView naslov;
    private Button toz;
    private Button pojedinacno;
    private Button settings;
    private Button about;
    private Button izborna;
    private View line1;
    private View line2;
    private View line3;
    private View line4;

    private Drawable[] drawTOZ;
    private Drawable[] drawPojedinacno;
    private Drawable[] drawSettings;
    private Drawable[] drawAbout;
    private Drawable[] drawIzborna;


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
        setContentView(R.layout.activity_home);

        naslov = findViewById(R.id.naslovMain);
        toz = findViewById(R.id.toz);
        pojedinacno = findViewById(R.id.pojedini);
        settings = findViewById(R.id.settings);
        about = findViewById(R.id.about);
        izborna = findViewById(R.id.izborna);
        line1=findViewById(R.id.linetozizb);
        line2=findViewById(R.id.lineizbpoj);
        line3=findViewById(R.id.linepojset);
        line4=findViewById(R.id.linesetabo);
        drawTOZ = toz.getCompoundDrawables();
        drawPojedinacno = pojedinacno.getCompoundDrawables();
        drawSettings = settings.getCompoundDrawables();
        drawAbout = about.getCompoundDrawables();
        drawIzborna = izborna.getCompoundDrawables();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navbar = preferences.getBoolean("navbar",false);
        boolean firstTime = preferences.getBoolean("first", true);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        if (firstTime){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("ZATVORI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    editor.putBoolean("first",false);
                    editor.apply();
                    dialog.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.whats_new_popup, null);
            dialog.setView(dialogLayout);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();
        }

        TypedArray typedArray = obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
        @ColorInt int colorAccent = typedArray.getColor(0, 0);
        typedArray.recycle();
        line1.setBackground(new ColorDrawable(colorAccent));
        line2.setBackground(new ColorDrawable(colorAccent));
        line3.setBackground(new ColorDrawable(colorAccent));
        line4.setBackground(new ColorDrawable(colorAccent));
        drawTOZ[0].setTint(colorAccent);
        drawPojedinacno[0].setTint(colorAccent);
        drawSettings[0].setTint(colorAccent);
        drawAbout[0].setTint(colorAccent);
        drawIzborna[0].setTint(colorAccent);
    }

    public void gotoTOZ(View view) {
        Intent i = new Intent(this, TozLobby.class);
        startActivity(i);

    }

    public void launchSettings(View view) {
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }


    public void GOTOpojedinacno(View view) {
        Intent i = new Intent(this, PojedinacnoLobby.class);
        startActivity(i);
    }

    public void izborna(View view) {
        Intent i = new Intent(this, IzbornaLobby.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jeste li sigurni da želite napustiti aplikaciju?");
        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    public void about(View view) {
        Intent i = new Intent(this, AboutActivity.class);
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
