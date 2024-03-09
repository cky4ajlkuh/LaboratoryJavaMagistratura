package com.example.labs2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Facade {
    public static void controlling(Car car, TrafficLights trafficLights) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.001), event -> {
            if (!trafficLights.getRedLight().isVisible()) {
                if (car.getPathTransition().getNode().getTranslateX() > 190 && car.getPathTransition().getNode().getTranslateX() < 200) {
                    car.getPathTransition().pause();
                }
            } else {
                car.getPathTransition().play();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
