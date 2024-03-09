package com.example.labs2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public ImageView trafficLights;
    @FXML
    public Circle redLight;
    @FXML
    public Circle yellowLight;
    @FXML
    public Circle greenLight;
    @FXML
    public ImageView lightningMcQueen;
    @FXML
    public AnchorPane pane;

    public Controller() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lightningMcQueen.setImage(new Image("car.png"));
        lightningMcQueen.setFitHeight(70);
        lightningMcQueen.setFitWidth(130);
        lightningMcQueen.setX(20);
        lightningMcQueen.setY(270);
        lightningMcQueen.setVisible(true);
        Car carObject = new Car(lightningMcQueen);
        trafficLights.setImage(new Image("traffic_lights.png"));
        trafficLights.setFitWidth(200);
        trafficLights.setFitHeight(200);
        trafficLights.setX(299);
        trafficLights.setY(169);
        trafficLights.setVisible(true);
        TrafficLights trafficLightsObject = new TrafficLights(trafficLights, redLight, yellowLight, greenLight);
        carObject.moveCar();
        trafficLightsObject.changeLight();
        Facade.controlling(carObject, trafficLightsObject);
    }
}
