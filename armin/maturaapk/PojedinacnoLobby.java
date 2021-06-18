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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class PojedinacnoLobby extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    private Spinner predmeti;
    private Spinner broj;
    private Button start;
    private ArrayList<String> lista;
    private String odabir;
    private String numb;
    private LinearLayout home;
    public String thema;
    private EditText pos;
    private TextView titlebar;
    private TextView pocni;
    private View line1;
    private View line2;
    private View line3;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private int pick;
    private boolean navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pojedinacno);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        titlebar = findViewById(R.id.titlebar);

        lista = Assets.getSubjectList();
        home = findViewById(R.id.kuca);
        pocni=findViewById(R.id.pocniod);
        predmeti = findViewById(R.id.predmeti);
        broj = findViewById(R.id.broj);
        start = findViewById(R.id.start);
        pos = findViewById(R.id.picker);
        line1=findViewById(R.id.lineprbr);
        line2=findViewById(R.id.linebrpo);
        line3=findViewById(R.id.linepost);
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
            blackUI();
        } else if  (thema.equalsIgnoreCase("black")) {
            home.setBackgroundColor(Color.BLACK);
            whiteUI();
        } else if  (thema.equalsIgnoreCase("cherry")) {
            home.setBackground(getDrawable(R.drawable.cherry));
            whiteUI();
        }

        predmeti.setOnItemSelectedListener(this);
        broj.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.predmeti){
            odabir = adapterView.getItemAtPosition(i).toString();
        }
        else if (adapterView.getId() == R.id.broj){
            numb = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void gotoPOJEDINACNI(View view) {
        Intent i = new Intent(this, PojedinacnoActivity.class);
        i.putExtra("number",numb);
        i.putExtra("subject",odabir);
        if (pos.getText().toString().length()>0){
            pick = Integer.parseInt(pos.getText().toString());
            i.putExtra("position",pick);
        }
        else{
            i.putExtra("position",0);
        }
        startActivity(i);
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
        pocni.setTextColor(Color.WHITE);
        pos.setHintTextColor(Color.WHITE);
        pos.setTextColor(Color.WHITE);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.predmets, R.layout.spinner_item_white);
        adapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        predmeti.setAdapter(adapter);
        ArrayAdapter numAdapter = ArrayAdapter.createFromResource(this, R.array.moguci_brojevi, R.layout.spinner_item_white);
        numAdapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        broj.setAdapter(numAdapter);
    }

    private void blackUI(){
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.predmets, R.layout.spinner_item_light);
        adapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        predmeti.setAdapter(adapter);
        ArrayAdapter numAdapter = ArrayAdapter.createFromResource(this, R.array.moguci_brojevi, R.layout.spinner_item_light);
        numAdapter.setDropDownViewResource(R.layout.spinner_list_item_light);
        broj.setAdapter(numAdapter);
        titlebar.setTextColor(Color.BLACK);
        start.setTextColor(Color.BLACK);
        pos.setTextColor(Color.BLACK);
        pos.setHintTextColor(Color.BLACK);
        pocni.setTextColor(Color.BLACK);
        line3.setBackgroundColor(Color.BLACK);
        line2.setBackgroundColor(Color.BLACK);
        line1.setBackgroundColor(Color.BLACK);
    }

}
