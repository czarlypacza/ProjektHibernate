package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.RepertuarEntryDao;
import com.projekthibernate.Dao.SalaDao;
import com.projekthibernate.POJO.Repertuar;
import com.projekthibernate.POJO.RepertuarEntry;
import com.projekthibernate.POJO.Sala;
import com.projekthibernate.Rezerwacja;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.*;

import static com.projekthibernate.Controllers.RezerwzacjaController.LOCAL_DATE;
import static com.projekthibernate.Controllers.RezerwzacjaController.LOCAL_TIME;

public class dodajgodzineseansuController implements Initializable {

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox<RepertuarEntry> film;
    @FXML
    private ComboBox<RepertuarEntry> sale;

    @FXML
    private TextField godz;

    @FXML
    private TextField min;

    @FXML
    private Button powrot;

    @FXML
    private Button zapisz;
    /*FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("repertuar.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    RezerwzacjaController repertuarController = fxmlLoader.getController();
    ObservableList<RepertuarEntry> listaRepertuarFilmy = repertuarController.listaRepertuarFilmy;*/


    SalaDao salaDao = new SalaDao();
    ObservableList<Sala> listaSale= FXCollections.observableArrayList();
    RepertuarEntryDao repertuarEntryDao = new RepertuarEntryDao();
    ObservableList<RepertuarEntry> listaRepertuarFilmy = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.setValue(LOCAL_DATE("2022-12-17"));

        if (data.getValue()!=null) {
            System.out.println(data.getValue().toString());
            List<?> RepertuarInfo = repertuarEntryDao.selectRepertuarinfo(data.getValue().toString());
            Iterator<?> iterator =RepertuarInfo.iterator();

            while (iterator.hasNext()){
                Object[] objects = (Object[]) iterator.next();
                System.out.println(Arrays.toString(objects));
                List<LocalTime> godzinyList = repertuarEntryDao.selectGodziny((Integer)objects[0]);

                listaRepertuarFilmy.add(new RepertuarEntry((Integer) objects[0], (Integer) objects[1], (String) objects[2], (Integer) objects[3], (Integer) objects[4], (ArrayList<LocalTime>) godzinyList));
            }
            //film.setItems(listaRepertuarFilmy);
            film.getItems().addAll(listaRepertuarFilmy);

            listaSale.addAll(salaDao.getSale());



        }




    }

    public void zapisz(){
        System.out.println(LOCAL_TIME(""+godz+":"+min));
        //film.getValue().getGodziny().add(LOCAL_TIME(""+godz+":"+min));
        //Repertuar rep = new Repertuar(film.getValue().getID_Seansu(),)



    }

}
