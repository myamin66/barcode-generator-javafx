package com.i2p.barcodeusingjavafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class Barcode extends Application {
    public static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext=new SpringApplicationBuilder(BarcodeusingjavafxApplication.class).run();
    }

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent (stage));
    }
    static class StageReadyEvent extends ApplicationEvent{
        public StageReadyEvent (Stage stage){
            super(stage);
        }
        public Stage getStage() {
            return ((Stage)getSource());
        }
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}

