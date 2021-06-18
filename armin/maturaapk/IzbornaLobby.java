package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class IzbornaLobby extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner predmeti;
    private Button start;
    private String odabir;
    private LinearLayout home;
    public String thema;
    private TextView titlebar;
    private View stline;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    private boolean navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izborna_lobby);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        titlebar = findViewById(R.id.titlebar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        home = findViewById(R.id.kuca);
        predmeti = findViewById(R.id.predmeti);
        start = findViewById(R.id.start);
        stline = findViewById(R.id.stline);
        thema = preferences.getString("wallpaper","navy");

        navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        if (thema.equalsIgnoreCase("gold")) {
            home.setBackground(getDrawable(R.drawable.gold));
            blackUI();
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
            blackUI();
        } else if (thema.equalsIgnoreCase("jade")) {
            home.setBackground(getDrawable(R.drawable.jade));
            whiteUI();
        } else if (thema.equalsIgnoreCase("ocean")) {
            home.setBackground(getDrawable(R.drawable.ocean));
            whiteUI();
        } else if (thema.equalsIgnoreCase("sunset")) {
            home.setBackground(getDrawable(R.drawable.sunset));
            blackUI();
        } else if  (thema.equalsIgnoreCase("red")) {
            home.setBackground(getDrawable(R.drawable.red));
            blackUI();
        } else if  (thema.equalsIgnoreCase("silk")) {
            home.setBackground(getDrawable(R.drawable.silk));
            blackUI();
        } else if  (thema.equalsIgnoreCase("white")) {
            home.setBackgroundColor(Color.WHITE);
            start.setBackgroundColor(Color.WHITE);
            blackUI();
        } else if  (thema.equalsIgnoreCase("black")) {
            home.setBackgroundColor(Color.BLACK);
            whiteUI();
        } else if  (thema.equalsIgnoreCase("cherry")) {
            home.setBackground(getDrawable(R.drawable.cherry));
            whiteUI();
        }

        predmeti.setOnItemSelectedListener(this);
    }

    public void gotoIZBORNA(View view) {
        Intent i = new Intent(this, IzbornaClassicActivity.class);
        i.putExtra("subject",odabir);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        odabir = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void onBackPressed() {
        Intent i = new Intent(this, Home.class);
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
        start.setTextColor(Color.WHITE);
        titlebar.setTextColor(Color.WHITE);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.izborni, R.layout.spinner_item_white);
        adapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        predmeti.setAdapter(adapter);
    }

    private void blackUI(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.izborni, R.layout.spinner_item_light);
        adapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        predmeti.setAdapter(adapter);
        stline.setBackgroundColor(Color.BLACK);
    }
}
