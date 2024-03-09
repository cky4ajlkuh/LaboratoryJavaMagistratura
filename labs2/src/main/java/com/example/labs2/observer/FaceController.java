package com.example.labs2.observer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class FaceController implements Initializable {

    @FXML
    public ImageView eyeLeftClose;

    @FXML
    public ImageView eyeLeftOpen;

    @FXML
    public ImageView eyeRightClose;

    @FXML
    public ImageView eyeRightOpen;

    @FXML
    public ImageView mouthOpen;

    @FXML
    public ImageView noseBlack;

    @FXML
    public ImageView noseColor;

    @FXML
    public ImageView smile;

    public FaceController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Observer(eyeRightOpen, eyeRightClose);
        new Observer(eyeLeftOpen, eyeLeftClose);
        new Observer(mouthOpen, smile);
        new Observer(noseBlack, noseColor);
    }
}
