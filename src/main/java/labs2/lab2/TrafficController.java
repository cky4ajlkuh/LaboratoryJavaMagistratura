package labs2.lab2;

import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import labs2.lab2.entity.Car;

import java.net.URL;
import java.util.ResourceBundle;

public class TrafficController implements Initializable {

    @FXML
    private Circle greenLight;

    @FXML
    private ImageView lightningMcQueen;

    @FXML
    private Circle redLight;

    @FXML
    private ImageView trafficLights;

    @FXML
    private Circle yellowLight;

    public TrafficController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lightningMcQueen.setVisible(true);
        trafficLights.setImage(new Image("traffic_lights.png"));
        trafficLights.setFitWidth(200);
        trafficLights.setFitHeight(200);
        trafficLights.setX(299);
        trafficLights.setY(169);
        trafficLights.setVisible(true);
    }
}
