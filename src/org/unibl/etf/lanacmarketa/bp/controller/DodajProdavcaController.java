package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.model.Prodavac;
import org.unibl.etf.lanacmarketa.bp.model.Zaposleni;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DodajProdavcaController implements Initializable {

    @FXML
    TextField jmb;
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    ChoiceBox <String> imeMarketa;
    @FXML
    Button sacuvaj;
    String jmbProdavca;
    String imeProdavca;
    String prezimeProdavca;
    String imeMarketaProdavca;
    Integer idMarketaProdavca;

    MarketController marketController = new MarketController();
    ZaposleniController zaposleniController = new ZaposleniController();
    ProdavacController prodavacController = new ProdavacController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Market> marketList = marketController.getAll();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Market m : marketList){
            list.add(m.getNaziv());
        }
        imeMarketa.setItems(list);
    }

    private RadSaZaposlenimController radSaZaposlenimController = new RadSaZaposlenimController();
    public void setController (RadSaZaposlenimController radSaZaposlenimController) {
        this.radSaZaposlenimController = radSaZaposlenimController;
    }

    public void sacuvajProdavca(ActionEvent event){
        imeMarketaProdavca = imeMarketa.getValue();
        jmbProdavca = jmb.getText();
        imeProdavca = ime.getText();
        prezimeProdavca = prezime.getText();
        List<Market> marketList = marketController.getAll();
        for(Market m : marketList){
            if(imeMarketaProdavca.equals(m.getNaziv())){
                idMarketaProdavca = m.getIdMarketa();
            }
        }
        if(idMarketaProdavca == null || jmbProdavca.equals("") || imeProdavca.equals("") || prezimeProdavca.equals("")) {
            AlertBox.displayError("Popunite sva polja");
        } else {
            boolean postoji = false;
            List<Zaposleni> zaposleniList = zaposleniController.getAll();
            for(Zaposleni z : zaposleniList){
                if (jmbProdavca.equals(z.getJmb())) {
                    postoji = true;
                    break;
                }
            }
            boolean uspjesnoDodat = false;
            if(!postoji){
//                boolean uspjesnoDodatZaposleni = zaposleniController.save(new Zaposleni(jmbProdavca, imeProdavca, prezimeProdavca, idMarketaProdavca));
                boolean uspjesnoDodatZaposleni = zaposleniController.saveZaposleni(new Zaposleni(jmbProdavca, imeProdavca, prezimeProdavca, idMarketaProdavca));
                if(uspjesnoDodatZaposleni) {
                    uspjesnoDodat = prodavacController.save(new Prodavac(jmbProdavca, imeProdavca, prezimeProdavca, idMarketaProdavca));
                }
            }
            if(uspjesnoDodat) {
                AlertBox.displayConfirmation("Dodavanje uspijesno izvrseno.");
                sacuvaj.getScene().getWindow().hide();
                radSaZaposlenimController.loadDataToTable();
            }else {
                AlertBox.displayError("Dodavanje nije uspijesno izvrseno.");
            }
        }
    }
}
