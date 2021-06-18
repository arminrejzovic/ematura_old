package com.example.armin.maturaapk;

/**
 * Created by Armin on 27.01.2019.
 */

public class TrueFalseQuestion {
    private String pitanje;
    private String tf;

    public TrueFalseQuestion(String pitanje, String tf) {
        this.pitanje = pitanje;
        this.tf = tf;
    }

    public String getPitanje() {
        return pitanje;
    }

    public String getTf() {
        return tf;
    }

}
