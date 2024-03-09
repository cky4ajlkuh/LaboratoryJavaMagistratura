package com.example.labs2.mvc;

public class Point {
    private Double x;
    private Double y;

    public Point(Double x) {
        this.x = x;
        this.y = calculateY(x);
    }

    private Double calculateY(Double x) {
        return x * x + x + 1;
    }

    public String getX() {
        return x.toString();
    }

    public void setX(String x) {
        if (isDouble(x)) {
            this.x = Double.valueOf(x);
            setY(x);
        }
    }

    public String getY() {
        return y.toString();
    }

    public void setY(String y) {
        this.y = calculateY(Double.valueOf(y));
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Not value of Double insert!");
            return false;
        }
    }

}
