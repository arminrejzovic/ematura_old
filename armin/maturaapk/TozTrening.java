package com.example.armin.maturaapk;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Collections;
import java.util.Random;

public class TozTrening extends AppCompatActivity {
    //Views
    private Button prvi;
    private Button drugi;
    private Button treci;
    private Button cetvrti;
    private TextView pitanje;
    private LinearLayout home;
    private ImageButton hint;
    //ArrayLists for Questions
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
    //Variables for tracking Questions
    private int qn=0;
    private int correct=0;
    private Pitanje currentPitanje;
    //Appearance Variables
    private boolean german;
    private boolean navbar;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    //Variables passed to next Activity
    private ArrayList<String> izvjestaj;
    //Button variables
    Button tacno = null;
    Button option1 = null;
    Button option2 = null;
    Button option3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toz_trening);
        home = findViewById(R.id.dom);
        prvi = findViewById(R.id.one);
        drugi = findViewById(R.id.two);
        treci = findViewById(R.id.three);
        cetvrti = findViewById(R.id.four);
        pitanje = findViewById(R.id.naslov);
        hint = findViewById(R.id.hint);

        pitanje.setMovementMethod(new ScrollingMovementMethod());
        prvi.setMovementMethod(new ScrollingMovementMethod());
        drugi.setMovementMethod(new ScrollingMovementMethod());
        treci.setMovementMethod(new ScrollingMovementMethod());
        cetvrti.setMovementMethod(new ScrollingMovementMethod());
        izvjestaj = new ArrayList<>();

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

        String thema = preferences.getString("wallpaper", "navy");

        switch (thema.toLowerCase()){
            case "gold": {
                home.setBackground(getDrawable(R.drawable.gold));
                break;
            }
            case "navy": {
                home.setBackground(getDrawable(R.drawable.navy));
                whiteUI();
                break;
            }
            case "amethyst": {
                home.setBackground(getDrawable(R.drawable.amethyst));
                whiteUI();
                break;
            }
            case "aqua": {
                home.setBackground(getDrawable(R.drawable.aqua));
                whiteUI();
                break;
            }
            case "crystal": {
                home.setBackground(getDrawable(R.drawable.crystal));
                whiteUI();
                break;
            }
            case "garnet": {
                home.setBackground(getDrawable(R.drawable.garnet));
                break;
            }
            case "jade": {
                home.setBackground(getDrawable(R.drawable.jade));
                whiteUI();
                break;
            }
            case "ocean": {
                home.setBackground(getDrawable(R.drawable.ocean));
                whiteUI();
                break;
            }
            case "sunset": {
                home.setBackground(getDrawable(R.drawable.sunset));
                break;
            }
            case "red": {
                home.setBackground(getDrawable(R.drawable.red));
                break;
            }
            case "silk": {
                home.setBackground(getDrawable(R.drawable.silk));
                break;
            }
            case "cherry": {
                home.setBackground(getDrawable(R.drawable.cherry));
                whiteUI();
                break;
            }
            case "white": {
                home.setBackgroundColor(Color.WHITE);
                break;
            }
            case "black": {
                home.setBackgroundColor(Color.BLACK);
                whiteUI();
                break;
            }
        }

        createGame();
        Play();
    }

    public void Play() {
        if (qn < 100) {
            currentPitanje = pitanja.get(qn);

            if (currentPitanje.getHint().length()<1){
                hint.setVisibility(View.GONE);
            }
            else {
                hint.setVisibility(View.VISIBLE);
            }

            Random random = new Random();
            int rand = random.nextInt(4) + 1;

            pitanje.setText(qn+1+". "+currentPitanje.getQuestion());

            switch (rand){
                case 1: {
                    tacno = prvi;
                    option1 = drugi;
                    option2= treci;
                    option3 = cetvrti;
                    break;
                }
                case 2: {
                    tacno = drugi;
                    option1 = prvi;
                    option2= treci;
                    option3 = cetvrti;
                    break;
                }
                case 3: {
                    tacno = treci;
                    option1 = drugi;
                    option2= prvi;
                    option3 = cetvrti;
                    break;
                }
                case 4: {
                    tacno = cetvrti;
                    option1 = drugi;
                    option2= treci;
                    option3 = prvi;
                    break;
                }
            }

            tacno.setText(currentPitanje.getRight());
            option1.setText(currentPitanje.getWrong1());
            option2.setText(currentPitanje.getWrong2());
            option3.setText(currentPitanje.getWrong3());

            tacno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tacno.setBackgroundColor(Color.GREEN);
                    correct++;
                    izvjestaj.add(qn+". pitanje:             Tačno");
                    tacno.setEnabled(false);
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                    option3.setEnabled(false);
                }
            });

            option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    option1.setBackgroundColor(Color.RED);
                    tacno.setBackgroundColor(Color.GREEN);
                    izvjestaj.add(qn+". pitanje:             netačno");
                    tacno.setEnabled(false);
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                    option3.setEnabled(false);
                }
            });
            option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    option2.setBackgroundColor(Color.RED);
                    tacno.setBackgroundColor(Color.GREEN);
                    izvjestaj.add(qn+". pitanje:             netačno");
                    tacno.setEnabled(false);
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                    option3.setEnabled(false);
                }
            });
            option3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    option3.setBackgroundColor(Color.RED);
                    tacno.setBackgroundColor(Color.GREEN);
                    izvjestaj.add(qn+". pitanje:             netačno");
                    tacno.setEnabled(false);
                    option1.setEnabled(false);
                    option2.setEnabled(false);
                    option3.setEnabled(false);
                }
            });


            if (currentPitanje.getRight().equalsIgnoreCase(currentPitanje.getWrong1())){
                prvi.setAllCaps(false);
                drugi.setAllCaps(false);
                treci.setAllCaps(false);
                cetvrti.setAllCaps(false);
            }

            if (currentPitanje.getRight().length()>60 || currentPitanje.getWrong1().length()>60 ||
                    currentPitanje.getWrong2().length()>60 || currentPitanje.getWrong3().length()>60){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            }
            if (currentPitanje.getRight().length()>100 || currentPitanje.getWrong1().length()>100 ||
                    currentPitanje.getWrong2().length()>100 || currentPitanje.getWrong3().length()>100){
                prvi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                drugi.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                treci.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                cetvrti.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            }
            qn++;
        }
        else {
            Intent i = new Intent(this, Scoreboard.class);
            i.putExtra("Correct", correct);
            i.putExtra("Lista", izvjestaj);
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
            nextQuestion();
        }
    }

    public void nextQuestion(){
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
        Play();
    }

    public void createGame(){
        shuffleQuestions();
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

    private void shuffleQuestions() {
        Collections.shuffle(bosanski);
        Collections.shuffle(engleski);
        Collections.shuffle(njem);
        Collections.shuffle(math);
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
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Jeste li sigurni da želite odustati?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(TozTrening.this, Home.class);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("ZATVORI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();

            if (currentPitanje.getHint().contains("drawable")){
                View dialogLayout = inflater.inflate(R.layout.hint_dialogue, null);
                dialog.setView(dialogLayout);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();

                ImageView hint = dialog.findViewById(R.id.hintImage);
                int id = getResources().getIdentifier(currentPitanje.getHint(), "drawable", getPackageName());
                hint.setImageResource(id);
            }
            else {
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

