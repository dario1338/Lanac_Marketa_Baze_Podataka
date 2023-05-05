package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.model.Prodavac;
import org.unibl.etf.lanacmarketa.bp.model.Zaposleni;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IzmijeniProdavcaController implements Initializable {

    @FXML
    Label imeProdavca;
    @FXML
    Label prezimeProdavca;
    @FXML
    Label trenutniMarket;
    @FXML
    ChoiceBox<String> noviMarketChoiceBox;
    @FXML
    Button sacuvaj;
    Prodavac prodavac = RadSaZaposlenimController.prodavacFromParentView;
    String ime = prodavac.getIme();
    String prezime = prodavac.getPrezime();
    Integer idMarketa = prodavac.getIdMarketa();
    MarketController marketController = new MarketController();
    Market market = marketController.getMarketByIdMarket(idMarketa);
    String imeMarketa = market.getNaziv();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            imeProdavca.setText(ime);
            prezimeProdavca.setText(prezime);
            trenutniMarket.setText(imeMarketa);

            ObservableList<String> list = FXCollections.observableArrayList();
            List<Market> marketList = marketController.getAll();
             for(Market m : marketList) {
                 if(!imeMarketa.equals(m.getNaziv())) {
                     list.add(m.getNaziv());
                     noviMarketChoiceBox.setItems(list);
                 }
            }
    }

    private RadSaZaposlenimController radSaZaposlenimController = new RadSaZaposlenimController();
    public void setController (RadSaZaposlenimController radSaZaposlenimController) {
        this.radSaZaposlenimController = radSaZaposlenimController;
    }

    @FXML
    public void sacuvajIzmjene(ActionEvent event) {
        if(noviMarketChoiceBox.getValue() != null){
            String noviMarket = noviMarketChoiceBox.getValue();
            ZaposleniController zController = new ZaposleniController();
            List<Zaposleni> zaposleniList = zController.getByIdMarketa(idMarketa);
            for(Zaposleni z : zaposleniList) {
                if(prodavac.getJmb().equals(z.getJmb())) {
                    List<Market> marketList = marketController.getAll();
                    for(Market m : marketList) {
                        if(noviMarket.equals(m.getNaziv())){
                            z.setIdMarketa(m.getIdMarketa());
                            int retVal = zController.update(z);
                            Alert alert;
                            if(retVal == 1) {
                                alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("Izmjena je uspjesno izvrsena.");
                                sacuvaj.getScene().getWindow().hide();
                                radSaZaposlenimController.loadDataToTable();
                            }else {
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setContentText("Izmjena nije uspjesno izvrsena.");
                            }
                            alert.showAndWait();
                            break;
                        }
                    }
                }
            }
        }
    }
}
