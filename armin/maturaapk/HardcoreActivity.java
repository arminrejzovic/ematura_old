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
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HardcoreActivity extends AppCompatActivity {
    private Button prvi;
    private Button drugi;
    private Button treci;
    private Button cetvrti;
    private TextView pitanje;
    public int qn;
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
    private LinearLayout home;
    private ArrayList<String> lista;

    private Boolean whiteText = false;
    private Boolean german;
    private Boolean navbar;

    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardcore);
        home = findViewById(R.id.dom);
        lista = new ArrayList<>();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navbar = preferences.getBoolean("navbar",false);
        german = preferences.getBoolean("german",false);

        if (navbar){
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }

        thema = preferences.getString("wallpaper","navy");

        if (thema.equalsIgnoreCase("gold")) {
            home.setBackground(getDrawable(R.drawable.gold));
        } else if (thema.equalsIgnoreCase("navy")) {
            home.setBackground(getDrawable(R.drawable.navy));
            whiteText = true;
        } else if (thema.equalsIgnoreCase("amethyst")) {
            home.setBackground(getDrawable(R.drawable.amethyst));
            whiteText = true;
        } else if (thema.equalsIgnoreCase("aqua")) {
            home.setBackground(getDrawable(R.drawable.aqua));
            whiteText = true;
        } else if (thema.equalsIgnoreCase("crystal")) {
            home.setBackground(getDrawable(R.drawable.crystal));
            whiteText = true;
        } else if (thema.equalsIgnoreCase("garnet")) {
            home.setBackground(getDrawable(R.drawable.garnet));
        } else if (thema.equalsIgnoreCase("jade")) {
            home.setBackground(getDrawable(R.drawable.jade));
            whiteText = true;
        } else if (thema.equalsIgnoreCase("ocean")) {
            home.setBackground(getDrawable(R.drawable.ocean));
            whiteText = true;
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
            whiteText = true;
        } else if  (thema.equalsIgnoreCase("cherry")) {
            home.setBackground(getDrawable(R.drawable.cherry));
            whiteText = true;
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

        qn = 0;
        Collections.shuffle(bosanski);
        Collections.shuffle(engleski);
        Collections.shuffle(njem);
        Collections.shuffle(latin);
        Collections.shuffle(inf);
        Collections.shuffle(nijaz);
        Collections.shuffle(logic);
        Collections.shuffle(niho);
        Collections.shuffle(lik);
        Collections.shuffle(fiz);
        Collections.shuffle(chem);
        Collections.shuffle(selmin);
        Collections.shuffle(geo);
        Collections.shuffle(his);
        Collections.shuffle(psih);
        Collections.shuffle(soc);
        Collections.shuffle(muz);
        Collections.shuffle(tizo);
        createGame();

        if (whiteText){
            prvi.setTextColor(Color.WHITE);
            drugi.setTextColor(Color.WHITE);
            treci.setTextColor(Color.WHITE);
            cetvrti.setTextColor(Color.WHITE);
            pitanje.setTextColor(Color.WHITE);
        }

        newGame();
    }

    public void newGame() {

        if (qn < 100) {
            Pitanje currentPitanje = pitanja.get(qn);

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
                        prvi.setBackgroundColor(Color.BLUE);
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
                        drugi.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
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
                        drugi.setBackgroundColor(Color.BLUE);
                        correct++;
                        prvi.setEnabled(false);
                        lista.add(qn+". pitanje:             Tačno");
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                prvi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        prvi.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
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
                        treci.setBackgroundColor(Color.BLUE);
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
                        prvi.setBackgroundColor(Color.BLUE);
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
                        drugi.setBackgroundColor(Color.BLUE);
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
                        cetvrti.setBackgroundColor(Color.BLUE);
                        lista.add(qn+". pitanje:             netačno");
                        prvi.setEnabled(false);
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
            } else if (rand == 4) {
                cetvrti.setText(currentPitanje.getRight());
                lista.add(qn+". pitanje:             Tačno");
                drugi.setText(currentPitanje.getWrong1());
                treci.setText(currentPitanje.getWrong2());
                prvi.setText(currentPitanje.getWrong3());

                cetvrti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cetvrti.setBackgroundColor(Color.BLUE);
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
                        prvi.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                drugi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drugi.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
                        drugi.setEnabled(false);
                        treci.setEnabled(false);
                        cetvrti.setEnabled(false);
                    }
                });
                treci.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        treci.setBackgroundColor(Color.BLUE);
                        prvi.setEnabled(false);
                        lista.add(qn+". pitanje:             netačno");
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
            if (currentPitanje.getRight().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong1().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong2().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            if (currentPitanje.getWrong3().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            qn++;
        } else {
            Intent i = new Intent(this, Scoreboard.class);
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
        for (int i=0;i<15;i++){
            pitanja.add(bosanski.get(i));
        }

        if (german){
            for (int i=0;i<14;i++){
                pitanja.add(njem.get(i));
            }
        }
        else {
            for (int i=0;i<14;i++){
                pitanja.add(engleski.get(i));
            }
        }

        for (int i=0;i<14;i++){
            pitanja.add(math.get(i));
        }

        for (int i=0;i<2;i++){
            pitanja.add(latin.get(i));
        }
        for (int i=0;i<4;i++){
            pitanja.add(inf.get(i));
        }
        for (int i=0;i<5;i++){
            pitanja.add(fiz.get(i));
        }
        for (int i=0;i<6;i++){
            pitanja.add(chem.get(i));
        }
        for (int i=0;i<6;i++){
            pitanja.add(selmin.get(i));
        }
        for (int i=0;i<6;i++){
            pitanja.add(geo.get(i));
        }
        for (int i=0;i<4;i++){
            pitanja.add(his.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(nijaz.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(psih.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(logic.get(i));
        }
        for (int i=0;i<4;i++){
            pitanja.add(soc.get(i));
        }
        for (int i=0;i<2;i++){
            pitanja.add(niho.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(muz.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(lik.get(i));
        }
        for (int i=0;i<3;i++){
            pitanja.add(tizo.get(i));
        }

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jeste li sigurni da želite odustati?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(HardcoreActivity.this, Home.class);
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

}
