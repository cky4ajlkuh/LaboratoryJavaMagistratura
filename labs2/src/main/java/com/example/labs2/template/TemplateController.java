package com.example.labs2.template;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class TemplateController implements Initializable {
    @FXML
    public Button endButton;

    @FXML
    public Rectangle fieldRectangle;

    @FXML
    public Button startButton;
    @FXML
    public AnchorPane pane;

    @FXML
    public Button addCubeButton;
    @FXML
    public Button addStarButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Animation.setBounds(fieldRectangle);
    }

    public void setBall() {
        if (startButton.isPressed()) {
            startThread(new Ball());
        }
    }

    public void exitApplication() {
        System.exit(1);
    }

    public void setCube() {
        if (addCubeButton.isPressed()) {
            startThread(new Cube());
        }
    }

    public void setStar() {
        if (addStarButton.isPressed()) {
            startThread(new Star());
        }
    }

    private void startThread(Animation animation) {
        Thread thread = new Thread(animation);
        thread.start();
        pane.getChildren().add(animation.getFigure());
    }
}
