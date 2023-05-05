package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unibl.etf.lanacmarketa.bp.model.*;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class EvidencijaProizvodaMarketController implements Initializable {

    public Integer idMarketa = EvidencijaProizvodaController.idMarketaFromParent;

    @FXML
    TextField unesiKolicinu;
    @FXML
    Label imeMarketa;
    @FXML
    Button dodaj;
    @FXML
    Button izmijeni;
    @FXML
    Button obrisi;
    @FXML
    ChoiceBox<String> proizvodiChoiceBox;
    @FXML
    TableView<TabelaEvidencijaProizvoda> evidencijaProizvodaTableView;
    @FXML
    TableColumn<TabelaEvidencijaProizvoda, Integer> kolicina;
    @FXML
    TableColumn<TabelaEvidencijaProizvoda, String> sifra;
    @FXML
    TableColumn<TabelaEvidencijaProizvoda, String> naziv;
    ProizvodController proizvodController = new ProizvodController();
    EvidencijaProizvodaController evidencijaProizvodaController = new EvidencijaProizvodaController();
    MarketController marketController = new MarketController();
    LinkedHashMap<String, TabelaEvidencijaProizvoda> linkedHashMap = new LinkedHashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proizvodiChoiceBox.setItems(getProizvodi());
        linkedHashMap = getProizvodiFromMarket(idMarketa);
        Market market = marketController.getMarketByIdMarket(idMarketa);
        imeMarketa.setText(market.getNaziv());
        loadDataToTable();
    }

    public void loadDataToTable() {
        ObservableList<TabelaEvidencijaProizvoda> list = FXCollections.observableArrayList();
        for(Map.Entry<String, TabelaEvidencijaProizvoda> i : linkedHashMap.entrySet()){
            list.addAll(i.getValue());
        }
        sifra.setCellValueFactory(new PropertyValueFactory<TabelaEvidencijaProizvoda, String>("sifra"));
        naziv.setCellValueFactory(new PropertyValueFactory<TabelaEvidencijaProizvoda, String>("naziv"));
        kolicina.setCellValueFactory(new PropertyValueFactory<TabelaEvidencijaProizvoda, Integer>("kolicina"));
        evidencijaProizvodaTableView.setItems(list);
        proizvodiChoiceBox.setValue("");
    }

    public ObservableList<String> getProizvodi() {
        List<Proizvod> proizvodList = proizvodController.getAll();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Proizvod p : proizvodList){
            list.addAll(p.getSifraProizvoda() + " " + p.getNaziv());
        }
        return list;
    }

    public LinkedHashMap<String, TabelaEvidencijaProizvoda> getProizvodiFromMarket(Integer idMarketa) {
        LinkedHashMap<String, TabelaEvidencijaProizvoda> evidencijaProizvoda = new LinkedHashMap<>();
        List<EvidencijaProizvoda> listEvidencijaProizvoda = evidencijaProizvodaController.getProizvodiFromMarket(idMarketa);
        for(EvidencijaProizvoda evid : listEvidencijaProizvoda) {
            Proizvod proizvod = proizvodController.getProizvodByIdProizvod(evid.getIdProizvoda());
            if(proizvod != null) {
                TabelaEvidencijaProizvoda tabela = new TabelaEvidencijaProizvoda(proizvod.getSifraProizvoda(),
                                                                                proizvod.getNaziv(),
                                                                                evid.getKolicina());
                evidencijaProizvoda.put(proizvod.getSifraProizvoda(), tabela);
            }
        }
        return evidencijaProizvoda;
    }

    public void dodajProizvodUMarket(ActionEvent event) {
        if(proizvodiChoiceBox.getValue() != null && !proizvodiChoiceBox.getSelectionModel().isEmpty()) {
            String selectedProizvod = proizvodiChoiceBox.getValue();
            String[] selectedSplit = selectedProizvod.split(" ");
            String sifraProizvoda = selectedSplit[0];
            if(linkedHashMap.containsKey(sifraProizvoda)){
                AlertBox.displayError("Izabrani proizvod se vec nalazi u evidenciji proizvoda marketa.");
            }else {
                Proizvod proizvod = proizvodController.getProizvodBySifraProizvod(sifraProizvoda);
                if(proizvod != null) {
                    EvidencijaProizvoda evidencijaProizvoda = new EvidencijaProizvoda(idMarketa, proizvod.getProizvod(), 0);
                    if(evidencijaProizvodaController.save(evidencijaProizvoda)) {
                        TabelaEvidencijaProizvoda tabela = new TabelaEvidencijaProizvoda(proizvod.getSifraProizvoda(), proizvod.getNaziv(), evidencijaProizvoda.getKolicina());
                        linkedHashMap.put(proizvod.getSifraProizvoda(), tabela);
                        loadDataToTable();
                        AlertBox.displayConfirmation("Uspjesno ste dodali proizvod u evidenciju proizvoda marketa.");
                    }else{
                        AlertBox.displayError("Dodavanje proizvoda nije uspjesno izvrseno.");
                    }
                }
            }
        }else {
            AlertBox.displayError("Morate selectovati proizvod.");
        }
    }

    public void izmijeniProizvod(ActionEvent event) {
        TabelaEvidencijaProizvoda tabela = evidencijaProizvodaTableView.getSelectionModel().getSelectedItem();
        if(tabela != null && !unesiKolicinu.getText().isEmpty()) {
            if(isNumeric(unesiKolicinu.getText()) && Integer.parseInt(unesiKolicinu.getText()) > 0) {
                Proizvod proizvod = proizvodController.getProizvodBySifraProizvod(tabela.getSifra());
                EvidencijaProizvoda evidencijaProizvod = evidencijaProizvodaController.getProizvodFromEvidencijaP(idMarketa, proizvod.getProizvod());
                Integer kolicinaStaroStanje = evidencijaProizvod.getKolicina();
                Integer kolicinaDodato = Integer.parseInt(unesiKolicinu.getText());
                if(evidencijaProizvodaController.update(new EvidencijaProizvoda(idMarketa, proizvod.getProizvod(), kolicinaStaroStanje + kolicinaDodato))){
                    unesiKolicinu.setText("");
                    TabelaEvidencijaProizvoda tabelaNovo = new TabelaEvidencijaProizvoda(tabela.getSifra(), tabela.getNaziv(), kolicinaStaroStanje + kolicinaDodato);
                    linkedHashMap.remove(tabela.getSifra());
                    linkedHashMap.put(tabelaNovo.getSifra(), tabelaNovo);
                    loadDataToTable();
                    AlertBox.displayConfirmation("Uspjesno ste izmijenili stanje.");
                }else {
                    AlertBox.displayError("Imjena nije uspjesno izvrsena.");
                }
            }else {
                AlertBox.displayError("Unesite broj veci od nule.");
            }
        }else {
            AlertBox.displayError("Unesite kolicinu, a zatim selektujte proizvod.");
        }
    }

    public void obrisiProzvod(ActionEvent event) {
        TabelaEvidencijaProizvoda tabela = evidencijaProizvodaTableView.getSelectionModel().getSelectedItem();
        if(tabela != null) {
            Proizvod proizvod = proizvodController.getProizvodBySifraProizvod(tabela.getSifra());
            if(evidencijaProizvodaController.delete(idMarketa, proizvod.getProizvod())){
                AlertBox.displayConfirmation("Uspjesno ste obrisali proizvod iz evidencije proizvoda.");
                linkedHashMap.remove(proizvod.getSifraProizvoda());
                loadDataToTable();
            } else {
                AlertBox.displayError("Brisanje proizvoda nije uspjesno izvrseno.");
            }
        }else {
            AlertBox.displayError("Morate selektovati proizvod.");
        }
    }

    public static boolean isNumeric(String strNum) {
        if(strNum == null) {
            return false;
        }
        try{
            int num = Integer.parseInt(strNum);
        }catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
