package com.coursework.gui.impl;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Animations {
    
    public static void animateStrawberry(ImageView strawberry) {
        double rotation = strawberry.getRotate();
        double amplitude = 15 + Math.random() * 10;
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.ZERO, 
                new KeyValue(strawberry.translateYProperty(), 0),
                new KeyValue(strawberry.rotateProperty(), rotation)
            ),
            new KeyFrame(Duration.seconds(1.5 + Math.random()),
                new KeyValue(strawberry.translateYProperty(), amplitude, 
                    Interpolator.EASE_BOTH),
                new KeyValue(strawberry.rotateProperty(), rotation + 2, 
                    Interpolator.EASE_BOTH)
            ),
            new KeyFrame(Duration.seconds(3 + Math.random() * 2),
                new KeyValue(strawberry.translateYProperty(), 0, 
                    Interpolator.EASE_BOTH),
                new KeyValue(strawberry.rotateProperty(), rotation, 
                    Interpolator.EASE_BOTH)
            )
        );
        
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
        
        strawberry.setUserData(timeline);
    }
    
    public static void animateAllStrawberries(Pane parentPane) {
        parentPane.getChildren().stream()
            .filter(node -> node instanceof ImageView)
            .map(node -> (ImageView) node)
            .filter(iv -> iv.getImage() != null && 
                  iv.getImage().getUrl().contains("strawberry"))
            .forEach(Animations::animateStrawberry);
    }
    
    public static void stopAnimation(ImageView strawberry) {
        Animation animation = (Animation) strawberry.getUserData();
        if (animation != null) {
            animation.stop();
        }
    }
}