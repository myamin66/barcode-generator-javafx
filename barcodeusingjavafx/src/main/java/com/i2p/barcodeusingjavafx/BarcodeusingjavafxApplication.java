package com.i2p.barcodeusingjavafx;

import com.i2p.barcodeusingjavafx.services.CategoriesService;
import com.i2p.barcodeusingjavafx.services.ProductsService;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BarcodeusingjavafxApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BarcodeusingjavafxApplication.class, args);
        Application.launch(Barcode.class, args);
    }

    @Bean
    public ProductsService pservice(){
        return new ProductsService();
    }

    @Bean
    public CategoriesService cservice(){
        return new CategoriesService();
    }

    @Bean
    public BarcodeController barcodeController(){
        return new BarcodeController();
    }

}
