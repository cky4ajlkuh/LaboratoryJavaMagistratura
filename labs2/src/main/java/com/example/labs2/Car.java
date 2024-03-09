package com.example.labs2;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Car {
    private final PathTransition pathTransition = new PathTransition();

    @FXML
    private final ImageView lightningMcQueen;

    public Car(ImageView lightningMcQueen) {
        this.lightningMcQueen = lightningMcQueen;
    }

    public PathTransition getPathTransition() {
        return pathTransition;
    }

    public void moveCar() {
        Path longPath = new Path();
        longPath.getElements().add(new MoveTo(50, 300));
        longPath.getElements().add(new LineTo(800, 300));
        pathTransition.setNode(lightningMcQueen);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(-1);
        pathTransition.setAutoReverse(false);
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(longPath);
        pathTransition.play();
    }
}
