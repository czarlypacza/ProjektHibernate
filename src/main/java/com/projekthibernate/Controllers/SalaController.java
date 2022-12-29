package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.SalaDao;
import com.projekthibernate.POJO.Sala;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    private TableView<?> miejsca;

    @FXML
    private Button normalneminus;

    @FXML
    private Button normalneplus;

    @FXML
    private Button platnosc;

    SalaDao salaDao = new SalaDao();



    protected void loadData(int ID_sali) {

        miejsca.getColumns().clear();

        Sala sala = salaDao.getSala(ID_sali);
        for (int i = 0; i < sala.getRzedy(); i++) {
            miejsca.getColumns().add(new TableColumn<>("R"+i));
            miejsca.getColumns().get(i).setMaxWidth((miejsca.getPrefWidth()/sala.getRzedy())*0.99);
            miejsca.getColumns().get(i).setPrefWidth(miejsca.getPrefWidth()/ sala.getRzedy());
        }
        miejsca.getColumns().get(0).setText("AAAAA");


    }

}
