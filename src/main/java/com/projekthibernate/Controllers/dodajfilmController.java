package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.Rezerwacja;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class dodajfilmController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void zapisz(){
        if (tytul.getText()!=null&& rezyser.getText()!=null&&czastrwania.getText()!=null&&ocena.getText()!=null){
            FilmDao filmDao = new FilmDao();
            /*FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("filmy.fxml"));
            FilmyController filmyController = fxmlLoader.getController();*/
            filmDao.dodajFilm(tytul.getText(), rezyser.getText(),Integer.parseInt(czastrwania.getText()),Integer.parseInt(ocena.getText()));

        }

    }

    public void wyczysc(){
        tytul.setText("");
        rezyser.setText("");
        czastrwania.setText("");
        ocena.setText("");
    }



}
