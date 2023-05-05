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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.model.Prodavac;
import org.unibl.etf.lanacmarketa.bp.model.Zaposleni;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RadSaZaposlenimController implements Initializable {
    public static Prodavac prodavacFromParentView;

    @FXML
    Button dodaj;
    @FXML
    Button izmijeni;
    @FXML
    Button obrisi;
    @FXML
    Button prikaziSve;
    @FXML
    Button prikazi;
    @FXML
    ChoiceBox<String> nazivMarketaChoiceBox;
    @FXML
    TableView<Prodavac> tabela;
    @FXML
    TableColumn<Prodavac, String> jmb;
    @FXML
    TableColumn<Prodavac, String> ime;
    @FXML
    TableColumn<Prodavac, String> prezime;
    @FXML
    TableColumn<Prodavac, Integer> idMarketa;
    private Integer idIzabranogMarketa;
    ProdavacController prodavacController = new ProdavacController();
    MarketController marketController = new MarketController();
    ZaposleniController zaposleniController = new ZaposleniController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataToTable();

        List<Market> marketList = marketController.getAll();
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Market m : marketList){
            list.add(m.getNaziv());
        }
        nazivMarketaChoiceBox.setItems(list);
    }

    public void loadDataToTable() {
        jmb.setCellValueFactory(new PropertyValueFactory<Prodavac, String>("jmb"));
        ime.setCellValueFactory(new PropertyValueFactory<Prodavac, String>("ime"));
        prezime.setCellValueFactory(new PropertyValueFactory<Prodavac, String>("prezime"));
        idMarketa.setCellValueFactory(new PropertyValueFactory<Prodavac, Integer>("idMarketa"));

        tabela.setItems(getProdavci());
        tabela.getSortOrder().add(idMarketa);
        idMarketa.setSortType(TableColumn.SortType.ASCENDING);
        tabela.sort();
    }

    public void dodajProdavca(ActionEvent event) {
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajProdavca.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);

             DodajProdavcaController dodajProdavcaController = loader.getController();
             dodajProdavcaController.setController(this);

             Stage stage = new Stage();
             stage.initModality(Modality.WINDOW_MODAL);
             stage.initOwner(((Node) event.getSource()).getScene().getWindow());
             stage.setScene(scene);
             stage.setTitle("Dodaj novog prodavca");
             stage.show();
        } catch (IOException io) {
             io.printStackTrace();
        }
    }

    public void prikaziSveProdavce(ActionEvent event) {
        tabela.setItems(getProdavci());
        tabela.getSortOrder().add(idMarketa);
        idMarketa.setSortType(TableColumn.SortType.ASCENDING);
        tabela.sort();
    }

    public void prikaziProdavcePoMarketu(ActionEvent event) {
        if(nazivMarketaChoiceBox.getValue() != null){
        String nazivMarketa = nazivMarketaChoiceBox.getValue();
        List<Market> marketList = marketController.getAll();
        for(Market m : marketList){
            if(nazivMarketa.equals(m.getNaziv())){
                idIzabranogMarketa = m.getIdMarketa();
            }
        }
        tabela.setItems(getProdavciByIdMarket(idIzabranogMarketa));
        }
    }

    public ObservableList<Prodavac> getProdavci() {
        List<Prodavac> prodavacList = prodavacController.getAll();
        ObservableList<Prodavac> list = FXCollections.observableArrayList();
        for(Prodavac p : prodavacList){
            list.addAll(p);
        }
        return list;
    }

    public ObservableList<Prodavac> getProdavciByIdMarket(Integer idMarketa) {
        List<Prodavac> prodavacList = prodavacController.getByIdMarketa(idMarketa);
        ObservableList<Prodavac> list = FXCollections.observableArrayList();
        for(Prodavac p : prodavacList) {
            list.addAll(p);
        }
        return list;
    }

    public void izmijeniMarket(ActionEvent event) {
        prodavacFromParentView = tabela.getSelectionModel().getSelectedItem();
        if(prodavacFromParentView != null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IzmijeniProdavca.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                IzmijeniProdavcaController izmijeniProdavcaController = loader.getController();
                izmijeniProdavcaController.setController(this);

                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setTitle("Izimijeni market prodavca");
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertBox.displayError("Morate selektovati prodavca.");
        }
    }

    public void obrisiProdavca(ActionEvent event) {
        Prodavac prodavac = tabela.getSelectionModel().getSelectedItem();
        if(prodavac != null) {
            boolean obrisanProdavac = prodavacController.delete(prodavac.getJmb());
            if(obrisanProdavac){
                Zaposleni zaposleni = zaposleniController.getZaposleniByJmb(prodavac.getJmb());
                if(zaposleni != null) {
                    boolean obrisanZaposleni = zaposleniController.delete(zaposleni.getJmb());
                    if(obrisanZaposleni) {
                        loadDataToTable();
                        AlertBox.displayConfirmation("Prodavac je uspjesno obrisan.");
                    }
                }
            }else {
                AlertBox.displayError("Prodavac nije obrisan.");
            }
        }
    }

}
