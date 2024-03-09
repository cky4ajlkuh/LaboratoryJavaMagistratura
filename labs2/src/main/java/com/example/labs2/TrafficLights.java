package com.example.labs2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TrafficLights {
    @FXML
    public ImageView trafficLights;
    @FXML
    public Circle redLight;
    @FXML
    public Circle yellowLight;
    @FXML
    public Circle greenLight;

    public TrafficLights(ImageView trafficLights, Circle redLight, Circle yellowLight, Circle greenLight) {
        this.trafficLights = trafficLights;
        this.redLight = redLight;
        this.yellowLight = yellowLight;
        this.greenLight = greenLight;
    }

    public Circle getRedLight() {
        return redLight;
    }

    public void changeLight() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1), event -> {
                    redLight.setVisible(false);
                    yellowLight.setVisible(true);
                    greenLight.setVisible(true);
                }),
                new KeyFrame(Duration.seconds(3), event -> {
                    redLight.setVisible(true);
                    yellowLight.setVisible(false);
                    greenLight.setVisible(true);
                }),
                new KeyFrame(Duration.seconds(5), event -> {
                    redLight.setVisible(true);
                    yellowLight.setVisible(true);
                    greenLight.setVisible(false);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
