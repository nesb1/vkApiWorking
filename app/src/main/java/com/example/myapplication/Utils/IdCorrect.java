package com.example.myapplication.Utils;

public class IdCorrect {
    private String nullString = null;
    public boolean isCorrectId(String str) {
        return str!=(nullString) && !str.equals("") && !str.isEmpty() && isNotContainsText(str);
    }

    private boolean isNotContainsText(String txt) {
        try {
            Integer.parseInt(txt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
