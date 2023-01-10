package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.RepertuarEntryDao;
import com.projekthibernate.POJO.*;
import com.projekthibernate.Rezerwacja;
import com.projekthibernate.DBAcess;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RezerwzacjaController implements Initializable {

    @FXML
    private TableColumn<RepertuarEntry, String> Tytulfilmu;
    @FXML
    private TableColumn<RepertuarEntry, Integer> czastrwania;
    @FXML
    private TableColumn<RepertuarEntry, Integer> ocena;
    @FXML
    private TableColumn<RepertuarEntry, RepertuarEntry> przedpoludniem;
    @FXML
    private TableColumn<RepertuarEntry, RepertuarEntry> popoludniu;
    @FXML
    private TableColumn<RepertuarEntry, RepertuarEntry> wieczorem;
    @FXML
    private TableView<RepertuarEntry> tabelaseanse;

    @FXML
    private Button bestsellery;
    @FXML
    private Button najlepiejoceniane;
    @FXML
    private Button nowosci;

    @FXML
    private DatePicker dzienseansu;

    @FXML
    private MenuItem dodajfilm;
    @FXML
    private MenuItem dodajpracownika;
    @FXML
    private MenuItem dodajsale;
    @FXML
    private MenuItem filmy;
    @FXML
    private MenuItem seanse;
    @FXML
    private MenuItem usunpracownika;
    @FXML
    private MenuItem usunsale;
    @FXML
    private MenuItem dodajgodzine;


    RepertuarEntryDao repertuarEntryDao = new RepertuarEntryDao();

    ObservableList<RepertuarEntry> listaRepertuarFilmy = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dzienseansu.setValue(LOCAL_DATE("2022-12-17"));
        loadData();
    }

    @FXML
    protected void refresh() {
        DBAcess.getEntityManagerFactory().isOpen();


         listaRepertuarFilmy.clear();
        if (dzienseansu.getValue()!=null) {
            System.out.println(dzienseansu.getValue().toString());
            List<?> RepertuarInfo = repertuarEntryDao.selectRepertuarinfo(dzienseansu.getValue().toString());
            Iterator<?> iterator =RepertuarInfo.iterator();

            while (iterator.hasNext()){
                 Object[] objects = (Object[]) iterator.next();
                 System.out.println(Arrays.toString(objects));
                 List<LocalTime> godzinyList = repertuarEntryDao.selectGodziny((Integer)objects[0]);

                 listaRepertuarFilmy.add(new RepertuarEntry((Integer) objects[0], (Integer) objects[1], (String) objects[2], (Integer) objects[3], (Integer) objects[4], (ArrayList<LocalTime>) godzinyList));
            }

            tabelaseanse.setItems(listaRepertuarFilmy);

        }

    }


    private void loadData()  {
        refresh();

        Tytulfilmu.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
        czastrwania.setCellValueFactory(new PropertyValueFactory<>("Czas_trwania"));
        ocena.setCellValueFactory(new PropertyValueFactory<>("Ocena"));


        przedpoludniem.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        //dodanie przyciskow do tabeli
        javafx.util.Callback<TableColumn<RepertuarEntry, RepertuarEntry>, TableCell<RepertuarEntry, RepertuarEntry>> cellFactoryprzed = new javafx.util.Callback<>() {

            @Override
            public TableCell<RepertuarEntry, RepertuarEntry> call(TableColumn<RepertuarEntry, RepertuarEntry> repertuarEntryBooleanTableColumn) {
                TableCell<RepertuarEntry, RepertuarEntry> cell = new TableCell<>() {

                    @Override
                    public void updateItem(RepertuarEntry item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox hBox = createHBox( item,"12:00:00","00:00:00");

                            setGraphic(hBox);
                            setText(null);
                        }
                    }

                };
                return cell;
            }

        };
        przedpoludniem.setCellFactory(cellFactoryprzed);

        popoludniu.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        javafx.util.Callback<TableColumn<RepertuarEntry, RepertuarEntry>, TableCell<RepertuarEntry, RepertuarEntry>> cellFactorypo = new javafx.util.Callback<>() {

            @Override
            public TableCell<RepertuarEntry, RepertuarEntry> call(TableColumn<RepertuarEntry, RepertuarEntry> repertuarEntryBooleanTableColumn) {
                TableCell<RepertuarEntry, RepertuarEntry> cell = new TableCell<>() {

                    @Override
                    public void updateItem(RepertuarEntry item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox hBox = createHBox( item,"18:00:00","12:00:00");
                            setGraphic(hBox);
                            setText(null);
                        }
                    }

                };
                return cell;
            }

        };
        popoludniu.setCellFactory(cellFactorypo);

        wieczorem.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        javafx.util.Callback<TableColumn<RepertuarEntry, RepertuarEntry>, TableCell<RepertuarEntry, RepertuarEntry>> cellFactorywiecz = new javafx.util.Callback<>() {

            @Override
            public TableCell<RepertuarEntry, RepertuarEntry> call(TableColumn<RepertuarEntry, RepertuarEntry> repertuarEntryBooleanTableColumn) {
                TableCell<RepertuarEntry, RepertuarEntry> cell = new TableCell<>() {

                    @Override
                    public void updateItem(RepertuarEntry item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            HBox hBox = createHBox( item,"23:59:59","18:00:00");
                            setGraphic(hBox);
                            setText(null);
                        }
                    }

                };
                return cell;
            }

        };
        wieczorem.setCellFactory(cellFactorywiecz);



    }

    @FXML
    protected void FILMY(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("filmy.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) filmy.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void seanse(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("seanse.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) seanse.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void dodajGodzine(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("dodajgodzineseansu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) dodajgodzine.getParentPopup().getOwnerWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    public static final LocalTime LOCAL_TIME (String dateString){
        LocalTime localTime = LocalTime.parse(dateString);
        return localTime;
    }

    //stworzenie hbox z potrzebnymi przyciskami
    public HBox createHBox(RepertuarEntry repertuarEntry,String before,String after){
        HBox hBox = new HBox();
        for (int i = 0; i < repertuarEntry.getGodziny().size(); i++) {
            if (repertuarEntry.getGodziny().get(i).isBefore(LOCAL_TIME(before))&&repertuarEntry.getGodziny().get(i).isAfter(LOCAL_TIME(after)))
                hBox.getChildren().add(new Button(repertuarEntry.getGodziny().get(i).toString()));
        }
        for (int i = 0; i < hBox.getChildren().size(); i++) {
            hBox.getChildren().get(i).setId("przycisk");
            hBox.setSpacing(10);
            hBox.getChildren().get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println(((Button) event.getSource()).getText());
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("sala.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        SalaController salaController = fxmlLoader.getController();
                        salaController.loadData(repertuarEntryDao.getIDsali(repertuarEntry.getID_Seansu(),LOCAL_TIME(((Button) event.getSource()).getText())));
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                        /*Dodanie funkcjonalnosci przechodzenia do sali*/
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return hBox;
    }


}
