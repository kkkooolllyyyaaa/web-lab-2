package com.example.weblab2.resContent;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tsypk
 * @project lab2
 */
public class Result {
    private final double x;
    private final double y;
    @Getter
    private final double r;
    @Getter
    @Setter
    private String curTime;
    @Setter
    private String excTime;
    @Setter
    @Getter
    private boolean isDot;
    private final boolean isHit;
    private final double width;
    private final double shift;

    public Result(double x, double y, double r, boolean isHit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isHit = isHit;
        this.isDot = false;
        width = 350.0;
        shift = 70.0;
    }

    @Override
    public String toString() {
        return "<tr> " +
                "<td>" + x + "</td> " +
                "<td>" + y + "</td> " +
                "<td>" + r + "</td> " +
                "<td> " + curTime + "</td> " +
                "<td>" + excTime + "</td> " +
                "<td" + (isHit ? " style=\"color:green\"" : " style=\"color:red\"") + ">" +
                isHit + "</td> </tr>";
    }

    public String getDotSvg(double displayR) {
        if (isDot) {
            double xCor = (x * ((width - shift) / 2) / displayR) + width / 2;
            double yCor = (width / 2 - (y * ((width - shift) / 2) / displayR));
            String color = (isHit ? "\"green\"" : "\"red\"");
            return "<circle class=\"coordinate-dot\" r=\"2.5\" cx=\"" + xCor + "\" cy=\"" + yCor + "\" stroke=" + color + " fill=" + color + "></circle>\n";
        } else return "";
    }
}
