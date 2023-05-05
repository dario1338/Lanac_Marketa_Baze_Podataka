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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.Proizvod;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RadSaProizvodimaController implements Initializable {

    @FXML
    Button dodaj;
    @FXML
    Button izmijeni;
    @FXML
    Button obrisi;
    @FXML
    TableView<Proizvod> proizvodTableView;
    @FXML
    TableColumn<Proizvod, String> sifraProizvoda;
    @FXML
    TableColumn<Proizvod, String> nazivProizvoda;
    @FXML
    TableColumn<Proizvod, Double> cijenaProizvoda;

    ProizvodController proizvodController = new ProizvodController();

    public static Proizvod proizvodFromParentView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataToTable();
    }

    public void loadDataToTable() {
        sifraProizvoda.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("sifraProizvoda"));
        nazivProizvoda.setCellValueFactory(new PropertyValueFactory<Proizvod, String>("naziv"));
        cijenaProizvoda.setCellValueFactory(new PropertyValueFactory<Proizvod, Double>("cijena"));
        proizvodTableView.setItems(getProizvodi());
    }

    public ObservableList<Proizvod> getProizvodi() {
        List<Proizvod> proizvodList = proizvodController.getAllWithoutId();
        ObservableList<Proizvod> list = FXCollections.observableArrayList();
        for(Proizvod p : proizvodList){
            list.addAll(p);
        }
        return list;
    }

    public void dodajProizvod(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajProizvod.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            DodajProizvodController dodajProizvodController = loader.getController();
            dodajProizvodController.setController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setTitle("Dodaj novi proizvod.");
            stage.show();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void izmijeniProizvod(ActionEvent event) {
        proizvodFromParentView = proizvodTableView.getSelectionModel().getSelectedItem();
        if(proizvodFromParentView != null){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IzmijeniProizvod.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);

                IzmijeniProizvodController izmijeniProizvodController = loader.getController();
                izmijeniProizvodController.setController(this);

                Stage stage = new Stage();
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setTitle("Izimijeni proizvod");
                stage.show();
            }catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            AlertBox.displayError("Morate selektovati proizvod.");
        }
    }

    public void obrisiProizvod(ActionEvent event) {
        Proizvod proizvod = proizvodTableView.getSelectionModel().getSelectedItem();
        if(proizvod != null) {
            boolean obrisanProizvod = proizvodController.delete(proizvod.getSifraProizvoda());
            if (obrisanProizvod) {
                AlertBox.displayConfirmation("Uspjesno ste obrisali proizvod.");
                loadDataToTable();
            } else {
                AlertBox.displayError("Brisanje nije uspjesno izvrseno.");
            }
        }else {
            AlertBox.displayError("Morate selektovati proizvod.");
        }
    }

}
