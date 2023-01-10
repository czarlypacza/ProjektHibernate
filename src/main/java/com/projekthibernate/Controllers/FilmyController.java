package com.projekthibernate.Controllers;

import com.projekthibernate.DBAcess;
import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.POJO.Film;
import com.projekthibernate.Rezerwacja;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.FontIconTableCell;
import org.kordamp.ikonli.javafx.Icon;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private TableColumn<Film, Film> del;

    @FXML
    private TableColumn<Film, Film> edit;

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

        tabelafilmy.setEditable(true);
        ID.setCellValueFactory(new PropertyValueFactory<>("ID_filmu"));
        Tytulfilmu.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
        Rezyser.setCellValueFactory(new PropertyValueFactory<>("rezyser"));
        czastrwania.setCellValueFactory(new PropertyValueFactory<>("Czas_trwania"));
        ocena.setCellValueFactory(new PropertyValueFactory<>("Ocena"));
        Callback<TableColumn<Film, Integer>, TableCell<Film, Integer>> IntegerCellFactory
                = TextFieldTableCell.forTableColumn(new IntegerStringConverter());
        czastrwania.setCellFactory(col -> {
            TableCell<Film, Integer> cell = IntegerCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        czastrwania.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Film, Integer>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Film, Integer> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setCzas_trwania(t.getNewValue());
            }
        });
        ocena.setCellFactory(col -> {
            TableCell<Film, Integer> cell = IntegerCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        ocena.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Film, Integer>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Film, Integer> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setOcena(t.getNewValue());
            }
        });
        Callback<TableColumn<Film, String>, TableCell<Film, String>> StringCellFactory
                = TextFieldTableCell.forTableColumn();
        Tytulfilmu.setCellFactory(col -> {
            TableCell<Film, String> cell = StringCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        Tytulfilmu.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Film, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setTytul(t.getNewValue());
            }
        });
        Rezyser.setCellFactory(col -> {
            TableCell<Film, String> cell = StringCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        Rezyser.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Film, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Film, String> t) {
                ((Film) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setRezyser(t.getNewValue());
            }
        });


        del.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        del.setCellFactory(param-> new TableCell<Film,Film>(){
            private final Button deleteButton = new Button();


            @Override
            protected void updateItem(Film item, boolean empty) {
                super.updateItem(item, empty);


                deleteButton.setGraphic(new FontIcon());
                deleteButton.setId("del-button");
                if (item==null){
                    setGraphic(null);
                    return;
                }
                setGraphic(deleteButton);
                deleteButton.setOnAction(actionEvent -> {
                    System.out.println(item);
                    filmDao.usunFilm(item);
                    refresh();

                });

            }
        });
        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param-> new TableCell<Film,Film>(){
            private final Button editButton = new Button("edit");

            @Override
            protected void updateItem(Film item, boolean empty) {
                super.updateItem(item, empty);


                editButton.setGraphic(new FontIcon());
                editButton.setId("edit-button");
                if (item==null){
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(actionEvent -> {
                    filmDao.updateFilm();

                });

            }
        });
    }

}
