package com.example.labs2.template;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Star extends Animation {

    private final SVGPath star;

    public Star() {
        star = new SVGPath();
        star.setLayoutX(530);
        star.setLayoutY(350);
        star.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
        star.setVisible(true);
        star.setContent("M10, 1 L10, 1 4, 18 19, 6 1, 6 16, 18 z");
    }

    @Override
    public Shape getFigure() {
        return star;
    }

    @Override
    public void move() {
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {
            double deltaX = Math.random();
            double deltaY = Math.random();

            @Override
            public void handle(final ActionEvent t) {
                star.setLayoutX(star.getLayoutX() - deltaX);
                star.setLayoutY(star.getLayoutY() - deltaY);
                boolean atRightBorder = star.getLayoutX() >= (Animation.RIGHT_BORDER - star.getBoundsInLocal().getMaxX());
                boolean atLeftBorder = star.getLayoutX() <= Animation.LEFT_BORDER;
                boolean atBottomBorder = star.getLayoutY() >= (Animation.DOWN_BORDER - star.getBoundsInLocal().getMaxY());
                boolean atTopBorder = star.getLayoutY() <= Animation.UP_BORDER;

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
    public void run() {
        move();
    }
}
