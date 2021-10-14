package com.example.weblab2.resContent;

import lombok.Getter;

import java.text.DateFormat;
import java.util.Locale;

/**
 * @author tsypk
 * @project lab2
 */
public class TimeHandler {
    private double startTime;
    @Getter
    private String curTime;
    @Getter
    private String excTime;

    public void START() {
        startTime = System.nanoTime();
    }

    public void FINISH() {
        double finishTime = System.nanoTime();
        excTime = String.format("%4.2f", (finishTime - startTime) / 1000000);
        curTime = DateFormat.getTimeInstance(DateFormat.DEFAULT, new Locale("RU", "ru")).format(new java.util.Date());
    }
}
