package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.unibl.etf.lanacmarketa.bp.model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class PrijavaController implements Initializable {

    @FXML
     ChoiceBox<String> marketChoiceBox;
    @FXML
    Button marketPotvrdi;
    @FXML
    Button administrativniRadnikLogIn;
    @FXML
    Button prodavacLogIn;
    @FXML
    private VBox vBox;
    @FXML
    private VBox vBox1;
    @FXML
    private VBox vBox2;
    private String nazivMarketa = "";
    public MarketController marketController= new MarketController();
    public ProdavacController prodavacController = new ProdavacController();
    public KasaController kasaController = new KasaController();
    private final List<Market> markets = marketController.getAll();
    public static Prijava prijava;
    public static ZaduziKasu kasa;
    public static HashMap<String, Integer> kasaId = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        List<Market> marketList = marketController.getAll();
        for(Market m : marketList) {
            list.add(m.getNaziv());
            marketChoiceBox.setItems(list);
        }
        marketChoiceBox.setOnAction((event -> {
            nazivMarketa = marketChoiceBox.getValue();
            marketPotvrdi.setDisable(false);
        }));

        marketPotvrdi.setOnAction(event -> {
            Integer idMarketa = null;
            if(!nazivMarketa.isEmpty()){
                for(Market m : markets){
                    if(nazivMarketa.equals(m.getNaziv())){
                        idMarketa = m.getIdMarketa();
                    }
                }
                List<Prodavac> prodavacList = prodavacController.getByIdMarketa(idMarketa);
                List<String> prodavci = new ArrayList<>();
                for(Prodavac p : prodavacList){
                    prodavci.add(p.getIme() + " " + p.getPrezime());
                }
                ObservableList<String> listProdavci = FXCollections.observableArrayList();
                listProdavci.addAll(prodavci);
                prijava = new Prijava(listProdavci);

                List<Kasa> kasaList = kasaController.getByIdMarketa(idMarketa);
                ObservableList<String> listKase = FXCollections.observableArrayList();
                int i = 1;
                for(Kasa k : kasaList){
                    String pomocnaKasa = "Kasa " + i;
                    listKase.add(pomocnaKasa);
                    kasaId.put(pomocnaKasa, k.getIdKase());
                    i++;
                }
                kasa = new ZaduziKasu(listKase);
                vBox1.setDisable(false);
                vBox2.setDisable(false);
                administrativniRadnikLogIn.setDisable(false);
                prodavacLogIn.setDisable(false);
                vBox.setDisable(true);
            }
        });
    }

    public void potvrdiMarket(ActionEvent event) {
        if(!nazivMarketa.isEmpty()) {
            vBox1.setDisable(false);
            vBox2.setDisable(false);
            administrativniRadnikLogIn.setDisable(true);
            prodavacLogIn.setDisable(true);
            vBox.setDisable(true);
        }
    }

    @FXML
    public  void logInProdavac(ActionEvent event) {
        if(!nazivMarketa.isEmpty()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Zaduzuje.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //((ZaduzujeController)loader.getController()).setZaduziKasu(kasa);
                stage.setScene(scene);
                stage.setTitle(nazivMarketa);
                stage.show();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

    @FXML
    public  void logInAdministrativniRadnik(ActionEvent event) {
        if (!nazivMarketa.isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdministrativniRadnik.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle(nazivMarketa);
                stage.show();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }

}
