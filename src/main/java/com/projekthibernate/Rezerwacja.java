package com.projekthibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Rezerwacja extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("logowanie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 464, 419);*/
        FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("repertuar.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Kino");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}