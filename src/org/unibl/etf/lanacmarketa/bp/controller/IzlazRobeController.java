package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.unibl.etf.lanacmarketa.bp.model.TabelaIzlazRobe;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperIzlazRobe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IzlazRobeController implements Initializable {

    @FXML
    TableView<TabelaIzlazRobe> izlazRobeTableView;
    @FXML
    TableColumn<TabelaIzlazRobe, String> market;
    @FXML
    TableColumn<TabelaIzlazRobe, String> sifra;
    @FXML
    TableColumn<TabelaIzlazRobe, String> nazivP;
    @FXML
    TableColumn<TabelaIzlazRobe, Integer> izlaz;
    @FXML
    TableColumn<TabelaIzlazRobe, Integer> stanje;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        market.setCellValueFactory(new PropertyValueFactory<TabelaIzlazRobe, String>("nazivMarketa"));
        sifra.setCellValueFactory(new PropertyValueFactory<TabelaIzlazRobe, String>("sifraProizvoda"));
        nazivP.setCellValueFactory(new PropertyValueFactory<TabelaIzlazRobe, String>("nazivProizvoda"));
        izlaz.setCellValueFactory(new PropertyValueFactory<TabelaIzlazRobe, Integer>("izasloRobe"));
        stanje.setCellValueFactory(new PropertyValueFactory<TabelaIzlazRobe, Integer>("trenutnoStanje"));
        izlazRobeTableView.setItems(getIzlazRobe());
    }

    public ObservableList<TabelaIzlazRobe> getIzlazRobe() {
        List<TabelaIzlazRobe> izlazRobeList = WrapperIzlazRobe.selectAll();
        ObservableList<TabelaIzlazRobe> list = FXCollections.observableArrayList();
        for(TabelaIzlazRobe t : izlazRobeList) {
            list.addAll(t);
        }
        return list;
    }
}
