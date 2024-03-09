package com.example.labs2.mvc;

import javafx.scene.chart.XYChart;

public class Graphics {
    private final XYChart<Double, Double> chart;

    public Graphics(XYChart<Double, Double> graph) {
        this.chart = graph;
    }

    public void drawLines(XYChart.Series<Double, Double> points) {
        chart.getData().add(points);
    }

    public void clear() {
        chart.getData().clear();
    }
}
