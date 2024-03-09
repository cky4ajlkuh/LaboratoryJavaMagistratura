package com.example.labs2.state.stateImpl;

import com.example.labs2.state.Man;
import com.example.labs2.state.State;
import javafx.scene.image.Image;

public class HolidaysState implements State {

    private final Image happyState;

    public HolidaysState(Image happyState){
        this.happyState = happyState;
    }

    @Override
    public void handle(Man man) {
        man.setImageState(happyState);
    }
}