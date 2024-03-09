package com.example.labs2.mvc;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private final List<Point> values = new ArrayList<>();

    public void add(double x) {
        values.add(new Point(x));
    }

    public void remove(int index) {
        values.remove(index);
    }

    public Point getPoint(int index) {
        if (index <= values.size() - 1) {
            return values.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean contains(double x) {
        for (int i = 0; i < getSize(); i++) {
            if (x == Double.parseDouble(values.get(i).getX())) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return values.size();
    }
}
