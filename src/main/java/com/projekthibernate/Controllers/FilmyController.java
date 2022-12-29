package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.POJO.Film;
import com.projekthibernate.Rezerwacja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FilmyController implements Initializable {

    @FXML
    private TableColumn<Film, Integer> ID;

    @FXML
    private TableColumn<Film, String> Rezyser;

    @FXML
    private TableColumn<Film, String> Tytulfilmu;

    @FXML
    private TableColumn<Film, Integer> czastrwania;

    @FXML
    private MenuItem dodajFILM;

    @FXML
    private TableColumn<Film, Integer> ocena;

    @FXML
    private MenuItem refresh;

    @FXML
    private MenuItem powrot;

    @FXML
    private TableView<Film> tabelafilmy;

    @FXML
    private MenuItem usunfilm;

    FilmDao filmDao = new FilmDao();

    ObservableList<Film> listaFilmy = FXCollections.observableArrayList();

    @FXML
    void dodajFILM() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("dodajfilm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();//forlularz po kliknieciu w menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Powrot() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("repertuar.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) powrot.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadData();
    }

    @FXML
    void refresh() {
        listaFilmy.clear();
        listaFilmy.addAll(filmDao.getFilmy());
        tabelafilmy.setItems(listaFilmy);
    }

    private void LoadData(){
        refresh();

        ID.setCellValueFactory(new PropertyValueFactory<>("ID_filmu"));
        Tytulfilmu.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
        Rezyser.setCellValueFactory(new PropertyValueFactory<>("rezyser"));
        czastrwania.setCellValueFactory(new PropertyValueFactory<>("Czas_trwania"));
        ocena.setCellValueFactory(new PropertyValueFactory<>("Ocena"));
    }

}
