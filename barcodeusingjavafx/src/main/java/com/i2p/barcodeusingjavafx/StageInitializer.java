package com.i2p.barcodeusingjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import com.i2p.barcodeusingjavafx.Barcode.StageReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener <StageReadyEvent> {
    @Value("classpath:/barcode.fxml")
    private Resource barcodeResource;
    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        ConfigurableApplicationContext context = Barcode.applicationContext;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(barcodeResource.getURL());
            fxmlLoader.setControllerFactory(context::getBean);
            Parent parent=fxmlLoader.load();
            Stage stage=stageReadyEvent.getStage();
            stage.setScene(new Scene(parent,1000,600));
            stage.setTitle("Barcode Generator for Products with Special Category");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
