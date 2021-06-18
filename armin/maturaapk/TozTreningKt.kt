package com.example.armin.maturaapk

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class TozTreningKt : AppCompatActivity() {

    //ArrayLists for Questions
    private val bosanski = Assets.getBosnian()
    private val engleski = Assets.getEnglish()
    private val njem = Assets.getGerman()
    private val latin = Assets.getLatin()
    private val inf = Assets.getInf()
    private val nijaz = Assets.getPhilosophy()
    private val logic = Assets.getLogic()
    private val niho = Assets.getDemocracy()
    private val lik = Assets.getArt()
    private val fiz = Assets.getPhysics()
    private val chem = Assets.getChemistry()
    private val selmin = Assets.getBiology()
    private val geo = Assets.getGeo()
    private val his = Assets.getHistory()
    private val psih = Assets.getPsychology()
    private val soc = Assets.getSociology()
    private val muz = Assets.getMusic()
    private val tizo = Assets.getTizo()
    private val pitanja = ArrayList<Pitanje>()
    private val math = Assets.getMath()

    //Variables for tracking Questions
    private val questionNumber = 0
    private val correct = 0
    private val currentQuestion: Pitanje? = null

    //Appearance Variables
    private var german = false
    private var navbar = false

    //Variables passed to next Activity

    //Button variables
    var tacno: Button? = null
    var option1: Button? = null
    var option2: Button? = null
    var option3: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toz_trening_kt)
        val home: View = findViewById(R.id.dom)
        val prvi: Button  = findViewById(R.id.one)
        val drugi: Button = findViewById(R.id.two)
        val treci: Button = findViewById(R.id.three)
        val cetvrti: Button = findViewById(R.id.four)
        val pitanje: TextView = findViewById(R.id.naslov)
        val hint: ImageButton = findViewById(R.id.hint)

        pitanje.movementMethod = ScrollingMovementMethod()
        prvi.movementMethod = ScrollingMovementMethod()
        drugi.movementMethod = ScrollingMovementMethod()
        treci.movementMethod = ScrollingMovementMethod()
        cetvrti.movementMethod = ScrollingMovementMethod()
        var izvjestaj: ArrayList<String> = ArrayList()

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = preferences.edit()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        navbar = preferences.getBoolean("navbar", false)
        german = preferences.getBoolean("german", false)


        if (navbar) {
            val decorView = window.decorView
            val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.systemUiVisibility = uiOptions
        }




    }
}