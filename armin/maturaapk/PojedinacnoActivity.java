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
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PojedinacnoActivity extends AppCompatActivity {
    private Button prvi;
    private Button drugi;
    private Button treci;
    private Button cetvrti;
    private ImageButton hint;
    private TextView pitanje;
    public int qn;
    private int pos;
    public int correct=0;
    private ArrayList<Pitanje> bosanski = Assets.getBosnian();
    private ArrayList<Pitanje> engleski = Assets.getEnglish();
    private ArrayList<Pitanje> njem = Assets.getGerman();
    private ArrayList<Pitanje> latin = Assets.getLatin();
    private ArrayList<Pitanje> inf = Assets.getInf();
    private ArrayList<Pitanje> nijaz = Assets.getPhilosophy();
    private ArrayList<Pitanje> logic = Assets.getLogic();
    private ArrayList<Pitanje> niho = Assets.getDemocracy();
    private ArrayList<Pitanje> lik = Assets.getArt();
    private ArrayList<Pitanje> fiz = Assets.getPhysics();
    private ArrayList<Pitanje> chem = Assets.getChemistry();
    private ArrayList<Pitanje> selmin = Assets.getBiology();
    private ArrayList<Pitanje> geo = Assets.getGeo();
    private ArrayList<Pitanje> his = Assets.getHistory();
    private ArrayList<Pitanje> psih = Assets.getPsychology();
    private ArrayList<Pitanje> soc = Assets.getSociology();
    private ArrayList<Pitanje> muz = Assets.getMusic();
    private ArrayList<Pitanje> tizo = Assets.getTizo();
    private ArrayList<Pitanje> pitanja = new ArrayList<>();
    private ArrayList<Pitanje> math = Assets.getMath();
    private String thema;
    private Pitanje currentPitanje;
    private LinearLayout home;
    private ArrayList<String> lista;

    private int bounds;
    private String subj;
    private float truescore;
    private Boolean whiteText = false;
    private Boolean navbar;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_sub);
        home = findViewById(R.id.dom);
        lista = new ArrayList<>();
        hint = findViewById(R.id.hintss);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        subj = intent.getStringExtra("subject");
        String n = intent.getStringExtra("number");
        pos = intent.getIntExtra("position",0);


        if (n.equalsIgnoreCase("10 pitanja")){
            bounds=10;
        }
        else if (n.equalsIgnoreCase("20 pitanja")){
            bounds=20;
        }
        else if (n.equalsIgnoreCase("50 pitanja")){
            bounds=50;
        }
        else if (n.equalsIgnoreCase("Sva pitanja")){
            bounds=100;
        }
        else {
            bounds = 10;
        }

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        navbar = preferences.getBoolean("navbar",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }


        prvi = findViewById(R.id.one);
        drugi = findViewById(R.id.two);
        treci = findViewById(R.id.three);
        cetvrti = findViewById(R.id.four);
        pitanje = findViewById(R.id.naslov);

        pitanje.setMovementMethod(new ScrollingMovementMethod());
        prvi.setMovementMethod(new ScrollingMovementMethod());
        drugi.setMovementMethod(new ScrollingMovementMethod());
        treci.setMovementMethod(new ScrollingMovementMethod());
        cetvrti.setMovementMethod(new ScrollingMovementMethod());

        thema = preferences.getString("wallpaper","navy");

        if (thema.equalsIgnoreCase("gold")) {
            home.setBackground(getDrawable(R.drawable.gold));
        } else if (thema.equalsIgnoreCase("navy")) {
            home.setBackground(getDrawable(R.drawable.navy));
            whiteUI();
            pitanje.setTextColor(Color.WHITE);
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

        createGame();
        if ((pos>1)&&(pos<=bounds)){
            qn=pos-1;
        }
        newGame();
    }

    public void newGame() {

        if (qn < bounds) {
            currentPitanje = pitanja.get(qn);
            if (currentPitanje.getHint().length()<1){
                hint.setVisibility(View.GONE);
            }
            else {
                hint.setVisibility(View.VISIBLE);
            }

            Random x = new Random();
            int rand = x.nextInt(4) + 1;

            pitanje.setText(qn+1+". "+currentPitanje.getQuestion());

            if (rand == 1) {
                prvi.setText(currentPitanje.getRight());
                drugi.setText(currentPitanje.getWrong1());
                treci.setText(currentPitanje.getWrong2());
                cetvrti.setText(currentPitanje.getWrong3());

                prvi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prvi.setBackgroundColor(Color.GREEN);
                        correct++;
                        lista.add(qn+". pitanje:             Tačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                drugi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drugi.setBackgroundColor(Color.RED);
                        prvi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.RED);
                        prvi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.RED);
                        prvi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
            } else if (rand == 2) {
                drugi.setText(currentPitanje.getRight());
                prvi.setText(currentPitanje.getWrong1());
                treci.setText(currentPitanje.getWrong2());
                cetvrti.setText(currentPitanje.getWrong3());

                drugi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drugi.setBackgroundColor(Color.GREEN);
                        correct++;
                        lista.add(qn+". pitanje:             Tačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                prvi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prvi.setBackgroundColor(Color.RED);
                        drugi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.RED);
                        drugi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.RED);
                        drugi.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
            } else if (rand == 3) {
                treci.setText(currentPitanje.getRight());
                drugi.setText(currentPitanje.getWrong1());
                prvi.setText(currentPitanje.getWrong2());
                cetvrti.setText(currentPitanje.getWrong3());

                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.GREEN);
                        correct++;
                        lista.add(qn+". pitanje:             Tačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });

                prvi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prvi.setBackgroundColor(Color.RED);
                        treci.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                drugi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drugi.setBackgroundColor(Color.RED);
                        treci.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.RED);
                        treci.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
            } else if (rand == 4) {
                cetvrti.setText(currentPitanje.getRight());
                drugi.setText(currentPitanje.getWrong1());
                treci.setText(currentPitanje.getWrong2());
                prvi.setText(currentPitanje.getWrong3());

                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.GREEN);
                        correct++;
                        lista.add(qn+". pitanje:             Tačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                prvi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prvi.setBackgroundColor(Color.RED);
                        cetvrti.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                drugi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drugi.setBackgroundColor(Color.RED);
                        cetvrti.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.RED);
                        cetvrti.setBackgroundColor(Color.GREEN);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });

            }
            if (currentPitanje.getRight().equalsIgnoreCase(currentPitanje.getWrong1())){
                prvi.setAllCaps(false);
                drugi.setAllCaps(false);
                treci.setAllCaps(false);
                cetvrti.setAllCaps(false);
            }
            else if (currentPitanje.getQuestion().equalsIgnoreCase("Zaokruži slovo ispred pravilno napisanih skraćenica za: kilometar, Televiziju Tuzlanskog kantona i Bosnu i Hercegovinu")){
                prvi.setAllCaps(false);
                drugi.setAllCaps(false);
                treci.setAllCaps(false);
                cetvrti.setAllCaps(false);
            }
            if (currentPitanje.getRight().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            }
            if (currentPitanje.getRight().length()>100){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong1().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            }
            if (currentPitanje.getWrong1().length()>100){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong2().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            }
            if (currentPitanje.getWrong2().length()>100){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong3().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            }
            if (currentPitanje.getWrong3().length()>100){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            qn++;
        } else {
            Intent i = new Intent(this, Scoreboard.class);
            if (pos>0){
                truescore = (float) correct/(bounds-pos+1)*100;
            }
            else truescore = (float) correct/(bounds)*100;
            correct = Math.round(truescore);
            i.putExtra("Correct",correct);
            i.putExtra("Lista", lista);
            startActivity(i);
        }
    }

    public void dalje(View view) {
        if (prvi.isEnabled()){
            Toast nema = Toast.makeText(this, "Niste odgovorili na pitanje", Toast.LENGTH_SHORT);
            nema.show();
        }
        else{
            if (navbar){
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
            }
            nextQ();
        }
    }

    public void nextQ(){
        prvi.setBackgroundColor(Color.parseColor("#50ffffff"));
        drugi.setBackgroundColor(Color.parseColor("#50ffffff"));
        treci.setBackgroundColor(Color.parseColor("#50ffffff"));
        cetvrti.setBackgroundColor(Color.parseColor("#50ffffff"));
        prvi.setEnabled(true);
        drugi.setEnabled(true);
        treci.setEnabled(true);
        cetvrti.setEnabled(true);
        prvi.setAllCaps(true);
        drugi.setAllCaps(true);
        treci.setAllCaps(true);
        cetvrti.setAllCaps(true);
        prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        newGame();
    }

    public void createGame(){
        if (subj.equalsIgnoreCase("bosanski")){
            if (bounds==100){
                bounds = bosanski.size();
            }
            else if ((bounds>=20)&&(bounds>bosanski.size())){
                bounds = bosanski.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(bosanski.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("demokratija")){
            if (bounds==100){
                bounds = niho.size();
            }
            else if ((bounds>=20)&&(bounds>niho.size())){
                bounds = niho.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(niho.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("matematika")){
            if (bounds==100){
                bounds = math.size();
            }
            else if ((bounds>=20)&&(bounds>math.size())){
                bounds = math.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(math.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("engleski")){
            if (bounds==100){
                bounds = engleski.size();
            }
            else if ((bounds>=20)&&(bounds>engleski.size())){
                bounds = engleski.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(engleski.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("filozofija")){
            if (bounds==100){
                bounds = nijaz.size();
            }
            else if ((bounds>=20)&&(bounds>nijaz.size())){
                bounds = nijaz.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(nijaz.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("fizika")){
            if (bounds==100){
                bounds = fiz.size();
            }
            else if ((bounds>=20)&&(bounds>fiz.size())){
                bounds = fiz.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(fiz.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("geografija")){
            if (bounds==100){
                bounds = geo.size();
            }
            else if ((bounds>=20)&&(bounds>geo.size())){
                bounds = geo.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(geo.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("hemija")){
            if (bounds==100){
                bounds = chem.size();
            }
            else if ((bounds>=20)&&(bounds>chem.size())){
                bounds = chem.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(chem.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("historija")){
            if (bounds==100){
                bounds = his.size();
            }
            else if ((bounds>=20)&&(bounds>his.size())){
                bounds = his.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(his.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("informatika")){
            if (bounds==100){
                bounds = inf.size();
            }
            else if ((bounds>=20)&&(bounds>inf.size())){
                bounds = inf.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(inf.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("latinski")){
            if (bounds==100){
                bounds = latin.size();
            }
            else if ((bounds>=20)&&(bounds>latin.size())){
                bounds = latin.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(latin.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("likovno")){
            if (bounds==100){
                bounds = lik.size();
            }
            else if ((bounds>=20)&&(bounds>lik.size())){
                bounds = lik.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(lik.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("logika")){
            if (bounds==100){
                bounds = logic.size();
            }
            else if ((bounds>=20)&&(bounds>logic.size())){
                bounds = logic.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(logic.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("njemački")){
            if (bounds==100){
                bounds = njem.size();
            }
            else if ((bounds>=20)&&(bounds>njem.size())){
                bounds = njem.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(njem.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("muzičko")){
            if (bounds==100){
                bounds = muz.size();
            }
            else if ((bounds>=20)&&(bounds>muz.size())){
                bounds = muz.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(muz.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("psihologija")){
            if (bounds==100){
                bounds = psih.size();
            }
            else if ((bounds>=20)&&(bounds>psih.size())){
                bounds = psih.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(psih.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("sociologija")){
            if (bounds==100){
                bounds = soc.size();
            }
            else if ((bounds>=20)&&(bounds>soc.size())){
                bounds = soc.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(soc.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("tjelesni")){
            if (bounds==100){
                bounds = tizo.size();
            }
            else if ((bounds>=20)&&(bounds>tizo.size())){
                bounds = tizo.size();
            }
            for (int i=0;i<bounds;i++){
                pitanja.add(tizo.get(i));
            }
        }
        else if (subj.equalsIgnoreCase("biologija")){
            if (bounds==100){
                bounds = selmin.size();
            }
            else if ((bounds>=20)&&(bounds>selmin.size())){
                bounds = selmin.size();
            }
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
                Intent i = new Intent(PojedinacnoActivity.this, Home.class);
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

    public void hint(View view) {
        if (prvi.isEnabled()){
            Toast toast = Toast.makeText(this,"Odgovorite na pitanje da biste dobili objašnjenje",Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            if (currentPitanje.getHint().contains("drawable")){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("ZATVORI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.hint_dialogue, null);
                dialog.setView(dialogLayout);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();

                ImageView hint = dialog.findViewById(R.id.hintImage);
                int id = getResources().getIdentifier(currentPitanje.getHint(), "drawable", getPackageName());
                hint.setImageResource(id);
            }
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("ZATVORI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog dialog = builder.create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.textual_hint, null);
                dialog.setView(dialogLayout);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();

                TextView hinttext = dialog.findViewById(R.id.hintView);
                hinttext.setText(currentPitanje.getHint());
            }
        }
    }
    private void whiteUI(){
        prvi.setTextColor(Color.WHITE);
        drugi.setTextColor(Color.WHITE);
        treci.setTextColor(Color.WHITE);
        cetvrti.setTextColor(Color.WHITE);
        pitanje.setTextColor(Color.WHITE);
    }
}
