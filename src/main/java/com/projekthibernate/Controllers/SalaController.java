package com.projekthibernate.Controllers;

import com.projekthibernate.Dao.SalaDao;
import com.projekthibernate.POJO.Row;
import com.projekthibernate.POJO.Sala;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;


public class SalaController {

    @FXML
    private Label iloscbiletow;

    @FXML
    protected Label iloscmiejsc;

    @FXML
    private Label iloscnormalnych;

    @FXML
    private Label ilosculgowych;

    @FXML
    private TableView<Row> miejsca = new TableView<>();

    @FXML
    private Button normalneminus;

    @FXML
    private Button normalneplus;

    @FXML
    private Button ulgoweplus;

    @FXML
    private Button ulgoweminus;

    @FXML
    private Button platnosc;

    Integer siedzenia;


    SalaDao salaDao = new SalaDao();

    ObservableList<Row> rzedy = FXCollections.observableArrayList();


    protected void loadData(int ID_sali) {
        Sala sala = salaDao.getSala(ID_sali);//pobranie sali

        double tabelaSize = miejsca.getPrefWidth();

        double kolSize = sala.getKolumny()*50;

        miejsca.getColumns().clear();//wyczyszczenie kolumn

        TableColumn numery = new TableColumn<Row, Integer>();
        numery.setId("kolumna_numery");
        miejsca.getColumns().add(numery);
        miejsca.getColumns().get(0).setMaxWidth((tabelaSize-kolSize)/2);
        miejsca.getColumns().get(0).setPrefWidth((tabelaSize-kolSize)/2);
        miejsca.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nr"));//Dodanie pierwszej kolumny z numerami rzędów

        for (int j = 1; j <= sala.getRzedy(); j++) {
            rzedy.add(new Row(j, sala.getKolumny()));
        }
        miejsca.setItems(rzedy);//dodanie rzedow


        for (int i = 1; i <= sala.getKolumny(); i++) {
            //dodanie kolumn przyciskow
            TableColumn<Row,Boolean> but = new TableColumn("R"+i);
            but.setSortable(false);

            //dodanie "miejsca" na przyciski we wszytskich rzedach
            but.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Row,Boolean >, ObservableValue<Boolean>>() {
                @Override public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Row, Boolean> features) {
                return new SimpleBooleanProperty();
                }
            });
            //dodanie przycisków do komórek
            int finalI = i;
            but.setCellFactory(new Callback<TableColumn<Row, Boolean>, TableCell<Row, Boolean>>() {
                @Override public TableCell<Row, Boolean> call(TableColumn<Row, Boolean> personBooleanTableColumn) {
                    SiedzenieCell cell = new SiedzenieCell("M"+ finalI);
                    cell.setId("cell");
                    return cell; //przycisk
                }
            });

            miejsca.getColumns().add(but);
            /*but.setMaxWidth(((miejsca.getPrefWidth() - 27) / sala.getKolumny()) * 0.9899);
            but.setPrefWidth(((miejsca.getPrefWidth() - 27) / sala.getKolumny())* 0.9899);*/

            but.setMaxWidth(50);
            but.setPrefWidth(50);

            //usawienie szerokosci kolumn
        }

        miejsca.getColumns().add(new TableColumn<Row, Integer>());
        miejsca.getColumns().get(miejsca.getColumns().size()-1).setId("kolumna_numery");
        miejsca.getColumns().get(miejsca.getColumns().size()-1).setMaxWidth((tabelaSize-kolSize+1)/2);
        miejsca.getColumns().get(miejsca.getColumns().size()-1).setPrefWidth((tabelaSize-kolSize+1)/2);


        //funkcjonalnosc dodanie przycisku normalnego
        normalneplus.setOnMouseClicked(new EventHandler() {
               @Override
               public void handle(Event event) {
                   int pula = getPula();

                   if (Integer.parseInt(iloscmiejsc.getText())!=pula){
                       int miejsca;

                       if (iloscnormalnych.getText().isBlank()) miejsca = 0;
                       else miejsca=Integer.parseInt(iloscnormalnych.getText());

                       miejsca++;
                       iloscnormalnych.setText(Integer.toString(miejsca));

                   }
                   pula=getPula();
                   iloscbiletow.setText(Integer.toString(pula));

               }
           }
        );

        normalneminus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int pula = getPula();

                int miejsca;

                if(iloscnormalnych.getText().isBlank()){
                    miejsca=0;
                }else {
                    miejsca=Integer.parseInt(iloscnormalnych.getText());
                }

                if (miejsca>1){
                    miejsca--;
                    iloscnormalnych.setText(Integer.toString(miejsca));
                }else{
                    iloscnormalnych.setText("");
                }
                pula=getPula();
                iloscbiletow.setText(Integer.toString(pula));
            }
        });

        //funkcjonalnosc biletow ulgowych
        ulgoweplus.setOnMouseClicked(new EventHandler() {
               @Override
               public void handle(Event event) {
                   int pula = getPula();

                   if (Integer.parseInt(iloscmiejsc.getText())!=pula){
                       int miejsca;

                       if (ilosculgowych.getText().isBlank()) miejsca = 0;
                       else miejsca=Integer.parseInt(ilosculgowych.getText());

                       miejsca++;
                       ilosculgowych.setText(Integer.toString(miejsca));
                   }
                   pula=getPula();
                   iloscbiletow.setText(Integer.toString(pula));
               }
           }
        );

        ulgoweminus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int pula = getPula();

                int miejsca;

                if(ilosculgowych.getText().isBlank()){
                    miejsca=0;
                }else {
                    miejsca=Integer.parseInt(ilosculgowych.getText());
                }

                if (miejsca>1){
                    miejsca--;
                    ilosculgowych.setText(Integer.toString(miejsca));
                }else{
                    ilosculgowych.setText("");
                }
                pula=getPula();

                iloscbiletow.setText(Integer.toString(pula));

            }
        });
    }


    public void DoPlatnosci(){




    }


    private int getPula(){
        int pula;
        if(iloscnormalnych.getText().isBlank()&&ilosculgowych.getText().isBlank()){
            pula = 0;
        }else {
            if (iloscnormalnych.getText().isBlank()||ilosculgowych.getText().isBlank()){
                if (iloscnormalnych.getText().isBlank()){
                    pula = Integer.parseInt(ilosculgowych.getText());
                }else{
                    pula = Integer.parseInt(iloscnormalnych.getText());
                }
            }else{
                pula= Integer.parseInt(iloscnormalnych.getText())+Integer.parseInt(ilosculgowych.getText());
            }
        }
        return pula;
    }

    static class Siedzenie extends Button{
        Boolean zajete;

        public Siedzenie(String s) {
            super(s);
            this.zajete = false;
        }
    }

    class SiedzenieCell extends TableCell<Row,Boolean>{
        final Siedzenie siedzenie;

        final StackPane paddedbutton = new StackPane();

        public SiedzenieCell(String nr) {
            siedzenie = new Siedzenie("");
            siedzenie.setId("siedzenie");
            setButtonImg(siedzenie,"file:src/main/resources/Images/siedzenie.png");
            //siedzenie.setGraphic(new ImageView(new Image("file:src/main/resources/Images/siedzenie.png")));


            paddedbutton.getChildren().add(siedzenie);
            siedzenie.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent mouseEvent) {
                  //System.out.println(mouseEvent.getSource().toString());
                    if (siedzenie.zajete){
                        siedzenie.zajete=false;
                        System.out.println(siedzenie.zajete);
                        setButtonImg(siedzenie,"file:src/main/resources/Images/siedzenie.png");
                        int miejsca;

                        miejsca = Integer.parseInt(iloscmiejsc.getText());
                        miejsca--;
                        iloscmiejsc.setText(Integer.toString(miejsca));

                        if (miejsca==0){
                            iloscmiejsc.setText("");
                        }

                    }else{
                        siedzenie.zajete=true;
                        System.out.println(siedzenie.zajete);
                        setButtonImg(siedzenie,"file:src/main/resources/Images/seat_chosen.png");
                        int miejsca;

                        if (iloscmiejsc.getText().isBlank()){
                            miejsca=1;
                            iloscmiejsc.setText(Integer.toString(miejsca));
                        }else {
                            miejsca = Integer.parseInt(iloscmiejsc.getText());
                            miejsca++;
                            iloscmiejsc.setText(Integer.toString(miejsca));
                        }
                    }
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedbutton);
            } else {
                setGraphic(null);
            }
        }

        private void setButtonImg(Button btn, String url) {
            Image image = new Image(url);
            ImageView imgView = new ImageView(image);
            imgView.setFitHeight(23);
            imgView.setPreserveRatio(true);
            btn.setGraphic(imgView);
        }
    }


}