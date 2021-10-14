package com.example.weblab2.resContent;

import lombok.Getter;

/**
 * @author tsypk
 * @project lab2
 */
public class PointService {
    private final double x;
    private final double y;
    private final double r;
    @Getter
    private boolean isHit;

    public PointService(Point point) {
        x = point.getX();
        y = point.getY();
        r = point.getR();
    }

    public void handle() {
        isHit = isValid() && isInArea();
    }

    private boolean isInArea() {
        return ((x >= 0 && y >= 0 && (x * x + y * y) <= (r * r / 4.0d)) ||
                (x <= 0 && y <= 0 && Math.abs(y) <= r && Math.abs(x) <= (r / 2.0d)) ||
                (x <= 0 && y >= 0 && y <= (x + r / 2.0d)));
    }

    private boolean isValid() {
        return x > -3.0d && x < 3.0d && y >= -3.0d && y <= 5.0d && r > 2.0 && r < 5.0;
    }
}
