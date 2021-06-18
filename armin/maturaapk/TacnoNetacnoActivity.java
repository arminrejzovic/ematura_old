package com.example.armin.maturaapk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TacnoNetacnoActivity extends AppCompatActivity {
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    
    private ArrayList<TrueFalseQuestion> his = Assets.getHisTF();
    private ArrayList<TrueFalseQuestion> geo = Assets.getGeoTF();
    private ArrayList<TrueFalseQuestion> pitanja = new ArrayList<>();
    
    private Button True;
    private Button False;
    private LinearLayout home;
    private TextView pitanje;
    private Button dalje;

    private String thema;
    private ArrayList<String> lista;
    private int qn;
    private int correct;
    private int bounds;
    private TrueFalseQuestion current;
    private String subj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tacnonetacno);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        correct = intent.getIntExtra("Correct",0);
        subj = intent.getStringExtra("Predmet");
        lista = intent.getStringArrayListExtra("Lista");
        
        True = findViewById(R.id.tacno);
        False = findViewById(R.id.netacno);
        pitanje = findViewById(R.id.vopros);
        home = findViewById(R.id.dom);
        dalje = findViewById(R.id.dalje);
        subj = intent.getStringExtra("Predmet");
        pitanje.setMovementMethod(new ScrollingMovementMethod());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        thema = preferences.getString("wallpaper","navy");

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
        } else if  (thema.equalsIgnoreCase("black")) {
            home.setBackgroundColor(Color.BLACK);
            whiteUI();
        } else if  (thema.equalsIgnoreCase("cherry")) {
            home.setBackground(getDrawable(R.drawable.cherry));
            whiteUI();
        }

        boolean navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        createGame();
        newGame();
    }

    public void dalje(View view) {
        if (True.isEnabled()){
            Toast.makeText(this,"Niste odgovorili na pitanje",Toast.LENGTH_SHORT).show();
        }
        else {
            nextQ();
        }
    }

    public void newGame(){
        if (qn<bounds){
            current = pitanja.get(qn);
            pitanje.setText(qn+1+". "+current.getPitanje());
        }
        else {
            Intent i = new Intent(this, Scoreboard.class);
            if (subj.equalsIgnoreCase("Geografija")){
                float truescore = (float) correct/34*100;
                correct = Math.round(truescore);
                i.putExtra("Correct",correct);
                i.putExtra("Lista", lista);
                startActivity(i);
            }
            else{
                float truescore = (float) correct/62*100;
                correct = Math.round(truescore);
                i.putExtra("Correct",correct);
                i.putExtra("Lista", lista);
                startActivity(i);
            }
        }
        qn++;
    }

    public void nextQ(){
        True.setBackgroundColor(Color.parseColor("#50ffffff"));
        False.setBackgroundColor(Color.parseColor("#50ffffff"));
        True.setEnabled(true);
        False.setEnabled(true);
        newGame();
    }

    public void tocno(View view) {
        if (current.getTf().equalsIgnoreCase("true")){
            True.setBackgroundColor(Color.GREEN);
            True.setEnabled(false);
            False.setEnabled(false);
            lista.add(qn+". pitanje(tačno-netačno):             Tačno");
            correct++;
        }
        else {
            True.setBackgroundColor(Color.RED);
            False.setBackgroundColor(Color.GREEN);
            True.setEnabled(false);
            lista.add(qn+". pitanje(tačno-netačno):             netačno");
            False.setEnabled(false);
        }
    }

    public void netocno(View view) {
        if (current.getTf().equalsIgnoreCase("False")){
            False.setBackgroundColor(Color.GREEN);
            lista.add(qn+". pitanje(tačno-netačno):             Tačno");
            True.setEnabled(false);
            False.setEnabled(false);
            correct++;
        }
        else {
            True.setBackgroundColor(Color.GREEN);
            False.setBackgroundColor(Color.RED);
            lista.add(qn+". pitanje(tačno-netačno):             netačno");
            True.setEnabled(false);
            False.setEnabled(false);
        }
    }
    public void createGame() {
        if (subj.equalsIgnoreCase("historija")) {
            bounds = 8;
            for (int i = 0; i < bounds; i++) {
                pitanja.add(his.get(i));
            }
        } else if (subj.equalsIgnoreCase("geografija")) {
            bounds = 8;
            for (int i = 0; i < bounds; i++) {
                pitanja.add(geo.get(i));
            }
        }
    }
    private void whiteUI(){
        pitanje.setTextColor(Color.WHITE);
        dalje.setTextColor(Color.WHITE);
        True.setTextColor(Color.WHITE);
        False.setTextColor(Color.WHITE);
    }
}
