package com.example.armin.maturaapk;

/**
 * Created by Armin on 15.01.2019.
 */

public class Pitanje {
    private String question;
    private String right;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    private String hint;

    public Pitanje(String question, String right, String wrong1, String wrong2, String wrong3, String hint) {
        this.question = question;
        this.right = right;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        this.wrong3 = wrong3;
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }


    public String getRight() {
        return right;
    }


    public String getWrong1() {
        return wrong1;
    }


    public String getWrong2() {
        return wrong2;
    }


    public String getWrong3() {
        return wrong3;
    }


    public String getHint() {
        return hint;
    }

}
