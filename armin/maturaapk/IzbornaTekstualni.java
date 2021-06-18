package com.example.armin.maturaapk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class IzbornaTekstualni extends AppCompatActivity {
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    private ArrayList<TekstualnoPitanje> his = Assets.getHisSingleQ();
    private ArrayList<TekstualnoPitanje> chem = Assets.getHemSQ();
    //private ArrayList<singleQ> selmin = Assets.getBioSQ();
    //private ArrayList<singleQ> geo = Assets.getGeoSQ();

    private ArrayList<TekstualnoPitanje> pitanja = new ArrayList<>();

    private LinearLayout home;
    private TextView pitanje;
    private Button dalje;
    private EditText otvet;
    private Button submit;
    private ImageView kartinka;

    private String thema;
    private ArrayList<String> lista;
    private int qn = 0;
    private int correct;
    private TekstualnoPitanje current;
    private int bounds;
    private String subj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izborna_test);

        Intent intent = getIntent();
        correct = intent.getIntExtra("Correct",0);
        subj = intent.getStringExtra("Predmet");
        lista = intent.getStringArrayListExtra("Lista");

        home = findViewById(R.id.dom);
        pitanje = findViewById(R.id.vopros);
        dalje = findViewById(R.id.dalje);
        otvet = findViewById(R.id.otvet);
        submit = findViewById(R.id.submit);
        kartinka = findViewById(R.id.pict);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        thema = preferences.getString("wallpaper","navy");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        Collections.shuffle(his);
        Collections.shuffle(chem);
        //Collections.shuffle(geo);
        //Collections.shuffle(bio);
        createGame();

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

        newGame();
    }

    public void dalje(View view) {
        if (submit.isEnabled()){
            Toast.makeText(this,"Niste odgovorili na pitanje",Toast.LENGTH_SHORT).show();
        }
        else {
            nextQ();
        }
    }

    public void submit(View view) {
        if ((otvet.getText().toString().equalsIgnoreCase(current.getCorrect()))||(otvet.getText().toString().equalsIgnoreCase(current.getAcceptable1()))||(otvet.getText().toString().equalsIgnoreCase(current.getAcceptable2()))){
            otvet.setBackgroundColor(Color.GREEN);
            submit.setEnabled(false);
            lista.add(qn+". pitanje(unos jednog pojma):             Tačno");
            correct++;
        }
        else {
            otvet.setBackgroundColor(Color.RED);
            lista.add(qn+". pitanje(unos jednog pojma):             netačno");
            submit.setEnabled(false);
        }
    }

    public void newGame(){
        if (qn<bounds){
            current = pitanja.get(qn);
            pitanje.setText(qn+1+". "+ current.getQuestion());

            if (current.getCorrect().equalsIgnoreCase("2-aminopropanska kiselina")){
                kartinka.setVisibility(View.VISIBLE);
                kartinka.setImageResource(R.drawable.aminopropanskakiselina);
            }
            else if (current.getCorrect().equalsIgnoreCase("1,2-etandiol")){
                kartinka.setVisibility(View.VISIBLE);
                kartinka.setImageResource(R.drawable.etandiol);
            }
            else if (current.getCorrect().equalsIgnoreCase("5-hlor-4-metil-2-heksin")){
                kartinka.setVisibility(View.VISIBLE);
                kartinka.setImageResource(R.drawable.heksin);
            }
        }
        else {
            if (subj.equalsIgnoreCase("Historija")){
                Intent i = new Intent(this, IzbornaDQActivity.class);
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                i.putExtra("Lista",lista);
                startActivity(i);
            }
            else if (subj.equalsIgnoreCase("Hemija")){
                Intent i = new Intent(this, IzbornaDQActivity.class);
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                i.putExtra("Lista",lista);
                startActivity(i);
            }
            /*else if (subj.equalsIgnoreCase("Geografija")){
                Intent i = new Intent(this, IzbornaDoubleQ.class);
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                startActivity(i);
            }
            else if (subj.equalsIgnoreCase("Biologija")){
                Intent i = new Intent(this, IzbornaDoubleQ.class);
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                startActivity(i);
            }*/
        }
        qn++;
    }

    public void nextQ(){
        otvet.setBackgroundColor(Color.parseColor("#50ffffff"));
        submit.setEnabled(true);
        otvet.setText("");
        kartinka.setVisibility(View.GONE);
        newGame();
    }

    public void createGame(){
        if (subj.equalsIgnoreCase("historija")){
            bounds = 4;
            for (int i=0;i<bounds;i++){
                pitanja.add(his.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("hemija")){
            bounds = 10;
            for (int i=0;i<bounds;i++){
                pitanja.add(chem.get(i));
            }
        }
        /*else if (subj.equalsIgnoreCase("geografija")){
            bounds = 10;
            for (int i=0;i<bounds;i++){
                pitanja.add(chem.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("biologija")){
            bounds = 10;
            for (int i=0;i<bounds;i++){
                pitanja.add(chem.get(i));
            }
        }*/

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jeste li sigurni da želite odustati?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(IzbornaTekstualni.this, Home.class);
                //i.putExtra("themeV", thema);
                startActivity(i);
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

    private void whiteUI(){
        pitanje.setTextColor(Color.WHITE);
        dalje.setTextColor(Color.WHITE);
        otvet.setTextColor(Color.WHITE);
        submit.setTextColor(Color.WHITE);
    }
}


