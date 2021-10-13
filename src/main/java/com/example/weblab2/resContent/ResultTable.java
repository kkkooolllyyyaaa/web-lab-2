package com.example.weblab2.resContent;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * @author tsypk
 * @project lab2
 */
public class ResultTable {
    @Getter
    private final ArrayList<Result> points;
    @Setter
    @Getter
    private Double displayR;

    public ResultTable() {
        points = new ArrayList<>();
    }

    public void addResult(Result point) {
        points.add(point);
    }

    public void clearTable() {
        points.clear();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Result result : points) {
            res.append(result.toString());
        }
        return res.toString();
    }

    public String getSvg() {
        StringBuilder res = new StringBuilder();
        for (Result result : points) {
            res.append(result.getDotSvg(displayR)).append("\n");
        }
        return res.toString();
    }
}
