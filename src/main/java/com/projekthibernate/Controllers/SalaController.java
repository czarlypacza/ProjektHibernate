package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.SalaDao;
import com.projekthibernate.POJO.Row;
import com.projekthibernate.POJO.Sala;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;

public class SalaController {

    @FXML
    private Label iloscbiletow;

    @FXML
    private Label iloscmiejsc;

    @FXML
    private Label iloscnormalnych;

    @FXML
    private Label ilosculgowych;

    @FXML
    private TableView<Row> miejsca;

    @FXML
    private Button normalneminus;

    @FXML
    private Button normalneplus;

    @FXML
    private Button platnosc;

    SalaDao salaDao = new SalaDao();

    ObservableList<Row> rzedy = FXCollections.observableArrayList();

    protected void loadData(int ID_sali) {

        miejsca.getColumns().clear();

        Sala sala = salaDao.getSala(ID_sali);
        miejsca.getColumns().add(new TableColumn<Row, Integer>("Nr."));
        miejsca.getColumns().get(0).setMaxWidth(30);
        miejsca.getColumns().get(0).setPrefWidth(30);
        miejsca.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nr"));

        for (int i = 1; i <= sala.getKolumny(); i++) {
            miejsca.getColumns().add(new TableColumn<Row, Row>("R" + i));
            miejsca.getColumns().get(i).setMaxWidth(((miejsca.getPrefWidth() - 30) / sala.getKolumny()) * 0.99);
            miejsca.getColumns().get(i).setPrefWidth((miejsca.getPrefWidth() - 30) / sala.getKolumny());
            Siedzenie siedzenie = new Siedzenie();
            Button button =new Button();

            miejsca.getColumns().get(i).setCellValueFactory(param -> new ReadOnlyObjectWrapper(button));



        }




       /* javafx.util.Callback<TableColumn<Row, Row>, TableCell<Row, Row>> cellFactorysiedzenie = new javafx.util.Callback<>() {

            @Override
            public TableCell<Row, Row> call(TableColumn<Row, Row> rowTableColumn) {
                TableCell<Row, Row> cell = new TableCell<>() {

                    @Override
                    public void updateItem(Row item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setGraphic(new Button());

                        }
                    }

                };
                return cell;
            }

        };*/


        /*for (int i = 1; i <= sala.getRzedy(); i++) {

            //miejsca.getColumns().get(i).setCellValueFactory(param -> new ReadOnlyObjectWrapper<>());
            //miejsca.getColumns().get(i).setCellFactory(cellFactorysiedzenie);
            //miejsca.getColumns().get(i).setCellValueFactory(new SimpleBooleanProperty());


            miejsca.getColumns().get(i).setCellFactory(param-> new TableCell<Row, Boolean>(){
                private final Button deleteButton = new Button();


                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);


                    deleteButton.setGraphic(new Button("aaa"));
                    if (item==null){
                        setGraphic(null);
                        return;
                    }
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(actionEvent -> {
                    });

                }
            });
        }*/


        for (int j = 1; j <= sala.getRzedy(); j++) {
            rzedy.add(new Row(j, sala.getKolumny()));
        }
        miejsca.setItems(rzedy);


        /*for (int i = 1; i <= sala.getKolumny(); i++) {

            *//*miejsca.getColumns().get(i).getCellObservableValue(i).getValue();
            System.out.println(miejsca.getColumns().get(i).getCellObservableValue(i).getValue().toString());
            for (int j = 1; j < sala.getRzedy(); j++) {
                System.out.println( miejsca.getColumns().get(i).getCellObservableValue(j).getValue());
            }*//*

            ((Button) miejsca.getColumns().get(1).getCellObservableValue(1).getValue()).setText("aaaaa");
            System.out.println(((Button) miejsca.getColumns().get(i).getCellObservableValue(i).getValue()).getText());

        }*/


    }


    static class Siedzenie extends Button{
        Boolean zajete = false;




    }


}