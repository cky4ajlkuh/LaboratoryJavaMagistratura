package com.example.labs2.observer;

import javafx.scene.image.ImageView;

public class Observer {
    private final ImageView imageOpen;
    private final ImageView imageClose;

    public Observer(ImageView imageOpen, ImageView imageClose) {
        this.imageOpen = imageOpen;
        this.imageClose = imageClose;
        change();
    }

    public void change() {
        imageOpen.setOnMouseClicked(mouseEvent -> {
            if (imageOpen.isVisible()) {
                imageOpen.setVisible(false);
                imageClose.setVisible(true);
            }
        });
        imageClose.setOnMouseClicked(mouseEvent -> {
            if (imageClose.isVisible()) {
                imageOpen.setVisible(true);
                imageClose.setVisible(false);
            }
        });
    }
}
