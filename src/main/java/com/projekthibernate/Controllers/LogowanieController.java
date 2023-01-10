package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.UserDao;
import com.projekthibernate.POJO.User;
import com.projekthibernate.Rezerwacja;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LogowanieController implements Initializable {

    @FXML
    private PasswordField haslo;

    @FXML
    private TextField login;

    @FXML
    private Button zaloguj;

    UserDao userDao = new UserDao();

    List<User> users = userDao.getUsers();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    public void LogIN() throws IOException {

        String logi = login.getText();
        String hasl = haslo.getText();

        for (User u :users) {
            System.out.println(u.getLogin());
            System.out.println(u.getPassword());
            System.out.println(logi);
            if (u.getLogin().equals(logi)){
                if (u.getPassword().equals(hasl)){
                    FXMLLoader fxmlLoader = new FXMLLoader(Rezerwacja.class.getResource("repertuar.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1144, 790);
                    //Stage stage = new Stage();
                    Stage stage = (Stage) zaloguj.getParent().getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else {
                    Alert pass = new Alert(Alert.AlertType.WARNING,"Wrong password");
                    pass.showAndWait();
                    login.clear();
                    haslo.clear();
                }
            }else {
                Alert pass = new Alert(Alert.AlertType.WARNING,"Wrong login");
                pass.showAndWait();
                login.clear();
                haslo.clear();
            }
        }

    }





}
