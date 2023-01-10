package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.Rezerwacja;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dodajfilmController {

    @FXML
    private TextField czastrwania;

    @FXML
    private TextField ocena;

    @FXML
    private TextField rezyser;

    @FXML
    private TextField tytul;

    @FXML
    private Button wyczysc;

    @FXML
    private Button zapisz;

    FilmDao filmDao = new FilmDao();

    FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("filmy.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    FilmyController filmyController = fxmlLoader.getController();

    public dodajfilmController() throws IOException {
    }


    @FXML
    private void zapisz(){
        if (tytul.getText()!=null&& rezyser.getText()!=null&&czastrwania.getText()!=null&&ocena.getText()!=null){
            filmDao.dodajFilm(tytul.getText(), rezyser.getText(),Integer.parseInt(czastrwania.getText()),Integer.parseInt(ocena.getText()));
        }
        filmyController.refresh();
    }

    public void wyczysc(){
        tytul.setText("");
        rezyser.setText("");
        czastrwania.setText("");
        ocena.setText("");
    }



}
