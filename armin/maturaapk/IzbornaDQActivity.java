package com.example.armin.maturaapk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class IzbornaDQActivity extends AppCompatActivity {
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;

    private ArrayList<DoubleQuestion> his = Assets.getHisDoubleQ();
    private ArrayList<DoubleQuestion> chem = Assets.getHemDQ();
    private ArrayList<DoubleQuestion> selmin = Assets.getBioDQ();
    private ArrayList<DoubleQuestion> geo = Assets.getGeoDQ();
    private ArrayList<DoubleQuestion> pitanja = new ArrayList<>();

    private LinearLayout home;
    private TextView pitanje;
    private Button dalje;
    private EditText otvet1;
    private EditText otvet2;
    private Button submit;

    private String thema;
    private ArrayList<String> lista;
    private int qn;
    private int bounds;
    private String subj;
    private int correct;
    private DoubleQuestion current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izborna_double_q);

        Intent intent = getIntent();
        correct = intent.getIntExtra("Correct",0);
        subj = intent.getStringExtra("Predmet");
        lista = intent.getStringArrayListExtra("Lista");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        home = findViewById(R.id.dom);
        pitanje = findViewById(R.id.vopros);
        dalje = findViewById(R.id.dalje);
        otvet1 = findViewById(R.id.otvet1);
        otvet2 = findViewById(R.id.otvet2);
        submit = findViewById(R.id.submit);
        pitanje.setMovementMethod(new ScrollingMovementMethod());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        thema = preferences.getString("wallpaper","navy");

        Collections.shuffle(his);
        Collections.shuffle(chem);
        Collections.shuffle(geo);
        Collections.shuffle(selmin);
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
        if ((otvet1.getText().toString().equalsIgnoreCase(current.getAnswer1()))){
            otvet1.setBackgroundColor(Color.GREEN);
            submit.setEnabled(false);
            correct++;
            lista.add(qn+".a) pitanje(unos dva pojma):             Tačno");

            if ((otvet2.getText().toString().equalsIgnoreCase(current.getAnswer2()))){
                otvet2.setBackgroundColor(Color.GREEN);
                submit.setEnabled(false);
                correct++;
                lista.add(qn+".b) pitanje(unos dva pojma):             Tačno");
            }
            else {
                otvet2.setBackgroundColor(Color.RED);
                lista.add(qn+".b) pitanje(unos dva pojma):             netačno");
                submit.setEnabled(false);
            }
        }
        else {
            otvet1.setBackgroundColor(Color.RED);
            submit.setEnabled(false);
            lista.add(qn+".a) pitanje(unos dva pojma):             netačno");

            if ((otvet2.getText().toString().equalsIgnoreCase(current.getAnswer2()))){
                otvet2.setBackgroundColor(Color.GREEN);
                submit.setEnabled(false);
                correct++;
                lista.add(qn+".b) pitanje(unos dva pojma):             Tačno");
            }
            else {
                otvet2.setBackgroundColor(Color.RED);
                lista.add(qn+".b) pitanje(unos dva pojma):             netačno");
                submit.setEnabled(false);
            }
        }
    }

    public void newGame(){
        if (qn<bounds){
            current = pitanja.get(qn);
            pitanje.setText(qn+1+". "+ current.getQuestion());
        }
        else {
            if (subj.equalsIgnoreCase("Historija")){
                Intent i = new Intent(this, TacnoNetacnoActivity.class);
                i.putExtra("Correct",correct);
                i.putExtra("Lista",lista);
                i.putExtra("Predmet",subj);
                startActivity(i);
            }
            else if (subj.equalsIgnoreCase("Hemija")){
                Intent i = new Intent(this, Scoreboard.class);
                float truescore = (float) correct/34*100;
                correct = Math.round(truescore);
                i.putExtra("Correct",correct);
                i.putExtra("Lista",lista);
                i.putExtra("Predmet",subj);
                startActivity(i);
            }
            else if (subj.equalsIgnoreCase("Geografija")){
                Intent i = new Intent(this, TacnoNetacnoActivity.class);
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                i.putExtra("Lista",lista);
                startActivity(i);
            }
            else if (subj.equalsIgnoreCase("Biologija")){
                Intent i = new Intent(this, Scoreboard.class);
                float truescore = (float) correct/40*100;
                i.putExtra("Correct",correct);
                i.putExtra("Predmet",subj);
                i.putExtra("Lista",lista);
                startActivity(i);
            }
        }
        qn++;
    }

    public void nextQ(){
        otvet1.setBackgroundColor(Color.parseColor("#50ffffff"));
        otvet2.setBackgroundColor(Color.parseColor("#50ffffff"));
        submit.setEnabled(true);
        otvet1.setText("");
        otvet2.setText("");
        newGame();
    }

    public void createGame(){
        if (subj.equalsIgnoreCase("historija")){
            bounds = 10;
            for (int i=0;i<bounds;i++){
                pitanja.add(his.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("hemija")){
            bounds = 4;
            for (int i=0;i<bounds;i++){
                pitanja.add(chem.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("geografija")){
            bounds = 8;
            for (int i=0;i<bounds;i++){
                pitanja.add(geo.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("biologija")){
            bounds = 10;
            for (int i=0;i<bounds;i++){
                pitanja.add(selmin.get(i));
            }
        }

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jeste li sigurni da želite odustati?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(IzbornaDQActivity.this, Home.class);
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
        otvet1.setTextColor(Color.WHITE);
        otvet2.setTextColor(Color.WHITE);
        submit.setTextColor(Color.WHITE);
    }
}
