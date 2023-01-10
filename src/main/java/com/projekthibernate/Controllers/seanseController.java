package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.FilmDao;
import com.projekthibernate.Dao.SeansDao;
import com.projekthibernate.POJO.Film;
import com.projekthibernate.POJO.Seans;
import com.projekthibernate.POJO.SeansEntry;
import com.projekthibernate.Rezerwacja;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class seanseController implements Initializable {

    @FXML
    private MenuItem Dodajseans;

    @FXML
    private TableView<SeansEntry> Seanse;


    @FXML
    private DatePicker data;

    @FXML
    private TableColumn<SeansEntry, SeansEntry> delete;

    @FXML
    private TableColumn<SeansEntry, SeansEntry> edit;

    @FXML
    private TableColumn<SeansEntry, Integer> idfilmu;

    @FXML
    private TableColumn<SeansEntry, Integer> idseansu;

    @FXML
    private TableColumn<SeansEntry, String> tytul;

    @FXML
    private MenuItem powrot;

    @FXML
    private MenuItem refresh;

    SeansDao seansDao = new SeansDao();

    ObservableList<SeansEntry> listaSeanse = FXCollections.observableArrayList();


    @FXML
    void dodajseans() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("dodajseans.fxml"));
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

    @FXML
    void refresh() {
        listaSeanse.clear();

        if (data.getValue()!=null) {
            System.out.println(data.getValue().toString());
            List<?> SeanseInfo = seansDao.getSeansEntry(data.getValue().toString());
            Iterator<?> iterator =SeanseInfo.iterator();

            while (iterator.hasNext()){
                Object[] objects = (Object[]) iterator.next();

                listaSeanse.add(new SeansEntry((Integer) objects[0], (Integer) objects[1], (String) objects[2]));
            }


            Seanse.setItems(listaSeanse);

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.setValue(LOCAL_DATE("2022-12-17"));
        LoadData();
    }

    private void LoadData() {
        refresh();


        Seanse.setEditable(true);
        idseansu.setCellValueFactory(new PropertyValueFactory<>("ID_seansu"));
        idfilmu.setCellValueFactory(new PropertyValueFactory<>("ID_filmu"));
        tytul.setCellValueFactory(new PropertyValueFactory<>("Tytul"));

        Callback<TableColumn<SeansEntry, Integer>, TableCell<SeansEntry, Integer>> IntegerCellFactory
                = TextFieldTableCell.forTableColumn(new IntegerStringConverter());
        idseansu.setCellFactory(col -> {
            TableCell<SeansEntry, Integer> cell = IntegerCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        idseansu.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeansEntry, Integer>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<SeansEntry, Integer> t) {
                ((SeansEntry) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setID_seansu(t.getNewValue());
            }
        });
        idfilmu.setCellFactory(col -> {
            TableCell<SeansEntry, Integer> cell = IntegerCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        idfilmu.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeansEntry, Integer>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<SeansEntry, Integer> t) {
                ((SeansEntry) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setID_filmu(t.getNewValue());
            }
        });

        /*Callback<TableColumn<SeansEntry, String>, TableCell<SeansEntry, String>> StringCellFactory
                = TextFieldTableCell.forTableColumn();
        tytul.setCellFactory(col -> {
            TableCell<SeansEntry, String> cell = StringCellFactory.call(col);
            cell.setAlignment(Pos.CENTER_LEFT);
            return cell;
        });
        tytul.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<SeansEntry, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<SeansEntry, String> t) {
                ((SeansEntry) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setTytul(t.getNewValue());
            }
        });*/


        delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delete.setCellFactory(param-> new TableCell<SeansEntry,SeansEntry>(){
            private final Button deleteButton = new Button();


            @Override
            protected void updateItem(SeansEntry item, boolean empty) {
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
                    seansDao.usunSeans(seansDao.getSeans(item.getID_seansu()));


                    refresh();

                });

            }
        });
        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param-> new TableCell<SeansEntry,SeansEntry>(){
            private final Button editButton = new Button("edit");

            @Override
            protected void updateItem(SeansEntry item, boolean empty) {
                super.updateItem(item, empty);


                editButton.setGraphic(new FontIcon());
                editButton.setId("edit-button");
                if (item==null){
                    setGraphic(null);
                    return;
                }
                setGraphic(editButton);
                editButton.setOnAction(actionEvent -> {
                    Seans seansupdate = seansDao.getSeans(item.getID_seansu());
                    seansupdate.setID_filmu(FilmDao.getFilm(item.getID_filmu()));
                    seansDao.updateSeans();

                });

            }
        });

    }


    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

}
