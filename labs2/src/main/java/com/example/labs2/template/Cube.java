package com.example.labs2.template;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Cube extends Animation implements Runnable {

    private final Rectangle cube;

    public Cube() {
        cube = new Rectangle();
        cube.setArcHeight(5);
        cube.setArcWidth(5);
        cube.setHeight(40);
        cube.setWidth(40);
        cube.setLayoutX(510);
        cube.setLayoutY(330);
        cube.setStroke(new Color(0, 0, 0, 1));
        cube.setStrokeType(StrokeType.INSIDE);
        cube.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
        cube.setVisible(true);
    }


    @Override
    public void move() {
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {
            double deltaX = Math.random();
            double deltaY = Math.random();

            @Override
            public void handle(final ActionEvent t) {
                cube.setLayoutX(cube.getLayoutX() - deltaX);
                cube.setLayoutY(cube.getLayoutY() - deltaY);
                boolean atRightBorder = cube.getLayoutX() >= (Animation.RIGHT_BORDER - cube.getWidth());
                boolean atLeftBorder = cube.getLayoutX() <= Animation.LEFT_BORDER;
                boolean atBottomBorder = cube.getLayoutY() >= (Animation.DOWN_BORDER - cube.getHeight());
                boolean atTopBorder = cube.getLayoutY() <= Animation.UP_BORDER;

                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
            }
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    @Override
    public Shape getFigure() {
        return cube;
    }

    @Override
    public void run() {
        move();
    }
}
