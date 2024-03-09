package com.example.labs2.template;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Ball extends Animation implements Runnable {

    private final Circle ball;

    public Ball() {
        ball = new Circle();
        ball.setRadius(20);
        ball.setStroke(new Color(0, 0, 0, 1));
        ball.setStrokeType(StrokeType.INSIDE);
        ball.setLayoutX(530);
        ball.setLayoutY(350);
        ball.setFill(new Color(Math.random(), Math.random(), Math.random(), 1));
        ball.setVisible(true);
    }

    @Override
    public void move() {
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<>() {
            double deltaX = Math.random();
            double deltaY = Math.random();

            @Override
            public void handle(final ActionEvent t) {
                ball.setLayoutX(ball.getLayoutX() - deltaX);
                ball.setLayoutY(ball.getLayoutY() - deltaY);
                boolean atRightBorder = ball.getLayoutX() >= (Animation.RIGHT_BORDER - ball.getRadius());
                boolean atLeftBorder = ball.getLayoutX() <= (Animation.LEFT_BORDER + ball.getRadius());
                boolean atBottomBorder = ball.getLayoutY() >= (Animation.DOWN_BORDER - ball.getRadius());
                boolean atTopBorder = ball.getLayoutY() <= (Animation.UP_BORDER + ball.getRadius());

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
        return ball;
    }

    @Override
    public void run() {
        move();
    }
}
