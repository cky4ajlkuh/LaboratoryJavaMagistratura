package com.example.labs2.state.stateImpl;

import com.example.labs2.state.Man;
import com.example.labs2.state.State;
import javafx.scene.image.Image;

public class SemesterState implements State {
    private final Image sleepState;

    public SemesterState(Image sleepState){
        this.sleepState = sleepState;
    }
    @Override
    public void handle(Man man) {
        man.setImageState(sleepState);
    }
}
