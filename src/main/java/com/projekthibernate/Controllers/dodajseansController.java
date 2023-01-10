package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.Dao.SeansDao;
import com.projekthibernate.POJO.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class dodajseansController implements Initializable {

    @FXML
    private DatePicker data;

    @FXML
    private Button dodaj;

    @FXML
    private ComboBox<String> film;

    @FXML
    private Button powrot;

    FilmDao filmDao = new FilmDao();

    SeansDao seansDao = new SeansDao();

    ObservableList<Film> listaFilmy = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaFilmy.addAll(filmDao.getFilmy());
        Iterator listaiterator = listaFilmy.iterator();

        while (listaiterator.hasNext()){
            film.getItems().add(((Film)listaiterator.next()).getTytul());
        }


    }

    public Film wybrany(){
        for (Film f:listaFilmy) {
            if (f.getTytul().equals(film.getValue())){
                System.out.println(f.getTytul());
                return f;
            }
        }
        return null;
    }


    public void zapisz(){
        seansDao.dodajSeans(wybrany(),data.getValue());


    }


}
