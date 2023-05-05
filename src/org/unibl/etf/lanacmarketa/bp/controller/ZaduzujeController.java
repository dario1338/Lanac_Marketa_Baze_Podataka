package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.unibl.etf.lanacmarketa.bp.model.*;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperZaduzuje;


import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class ZaduzujeController implements Initializable {

    public List<Zaduzuje> getAll() { return WrapperZaduzuje.selectAll(); }
    public boolean save (Zaduzuje zaduzuje) { return WrapperZaduzuje.insert(zaduzuje) == 1; }
    public boolean update(Zaduzuje zaduzuje) { return WrapperZaduzuje.update(zaduzuje) == 1; }
    public boolean delete (String jmb) { return  WrapperZaduzuje.deleteZaduzenjeByJMB(jmb) == 1; }

    @FXML
    ChoiceBox<String> prodavci;
    @FXML
    ChoiceBox<String> kase;
    @FXML
    Button zaduzi;
    public static String prodavacImePrezime;
    public static String zaduzenaKasa;
    public static HashMap<String, Integer> id = PrijavaController.kasaId;
    public static Zaduzuje zaduzujeFromParent;
    private final Prijava prijavaProdavci = PrijavaController.prijava;
    private final ZaduziKasu zaduziKasu = PrijavaController.kasa;
    public ProdavacController prodavacController = new ProdavacController();
    public KasaController kasaController = new KasaController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshProdavciAndKaseChoiceBox();
    }

    public void refreshProdavciAndKaseChoiceBox() {
        if(prijavaProdavci != null) {
            prodavci.setItems(prijavaProdavci.getProdavci());
        }
        if(zaduziKasu != null){
            kase.setItems(zaduziKasu.getKase());
        }
    }

    @FXML
    private void zaduziKasu (ActionEvent event) {
        if(prodavci.getValue() != null && kase.getValue() != null) {
            prodavacImePrezime = prodavci.getValue();
            zaduzenaKasa = kase.getValue();
            String[] imeP = prodavacImePrezime.split(" ");
            String ime = imeP[0];
            String prezime = imeP[1];
            Prodavac pro = new Prodavac();
            List<Prodavac> listProdavac = prodavacController.getAll();
            for(Prodavac p : listProdavac) {
                if(p.getPrezime().equals(prezime) && p.getIme().equals(ime)) {
                    pro = p;
                }
            }
            Integer idKase = id.get(zaduzenaKasa);
            List<Kasa> listKasa = kasaController.getAll();
            Kasa kasa = new Kasa();
            for(Kasa k : listKasa) {
                if(k.getIdKase().equals(idKase))
                    kasa = k;
            }
            zaduzujeFromParent = new Zaduzuje();
            zaduzujeFromParent.setIdKase(kasa.getIdKase());
            zaduzujeFromParent.setJmb(pro.getJmb());
            zaduzujeFromParent.setStanje(20);
            Date date = new Date();
            long time = date.getTime();
            Timestamp timestamp = new Timestamp(time);
            timestamp.setNanos(0);
            zaduzujeFromParent.setOdVremena(timestamp);
            zaduzujeFromParent.setDoVremena(null);
            save(zaduzujeFromParent);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Prodavac.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                ProdavacMenuController prodavacMenuController = loader.getController();
                prodavacMenuController.setController(this);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle(prodavci.getValue());
                stage.show();
                prodavci.getItems().remove(prodavci.getValue());
                kase.getItems().remove(kase.getValue());
                prodavci.getSelectionModel().select(null); //Kako da deselektujemo vrijednost iz choiceBoxa nakon sto smo je procitali tj da je opet postavimo na null
                kase.getSelectionModel().select(null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            AlertBox.displayError("Morate odabrati vrijednost za svako ponudjeno polje.");
        }
    }

}
