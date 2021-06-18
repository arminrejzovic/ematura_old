package com.example.armin.maturaapk;

/**
 * Created by Armin on 25.01.2019.
 */

public class TekstualnoPitanje {
    private String question;
    private String correct;
    private String acceptable1;
    private String acceptable2;

    public TekstualnoPitanje(String question, String correct, String acceptable1, String acceptable2) {
        this.question = question;
        this.correct = correct;
        this.acceptable1 = acceptable1;
        this.acceptable2 = acceptable2;
    }

    public String getQuestion() {
        return question;
    }


    public String getCorrect() {
        return correct;
    }


    public String getAcceptable1() {
        return acceptable1;
    }


    public String getAcceptable2() {
        return acceptable2;
    }

}
