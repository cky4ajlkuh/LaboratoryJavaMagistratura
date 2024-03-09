package com.example.labs2.state;

import com.example.labs2.state.stateImpl.HolidaysState;
import com.example.labs2.state.stateImpl.SemesterState;
import com.example.labs2.state.stateImpl.SessionState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class StateController implements Initializable {

    public ImageView defaultState;
    public ImageView goodState;
    public ImageView sadState;
    public ImageView sleepState;
    @FXML
    public Button holidaysButton;

    @FXML
    public Button semesterButton;

    @FXML
    public Button sessionButton;
    private final Man man;

    public StateController() {
        defaultState = new ImageView(new Image("/default.png"));
        man = new Man(defaultState.getImage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setStateSession() {
        setStateMan(sessionButton, new SessionState(sadState.getImage()));
    }

    public void setStateHolidays() {
        setStateMan(holidaysButton, new HolidaysState(goodState.getImage()));
    }

    public void setStateSemester() {
        setStateMan(semesterButton, new SemesterState(sleepState.getImage()));
    }

    public void setStateMan(Button button, State state) {
        if (button.isPressed()) {
            state.handle(man);
            defaultState.setImage(man.getImageState());
        }
    }
}
