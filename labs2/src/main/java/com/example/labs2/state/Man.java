package com.example.labs2.state;

import javafx.scene.image.Image;

public class Man {
    private Image imageState;

    public Man(Image imageState) {
        this.imageState = imageState;
    }

    public void setImageState(Image view) {
        this.imageState = view;
    }

    public Image getImageState() {
        return imageState;
    }
}
