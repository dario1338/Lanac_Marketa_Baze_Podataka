package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.EvidencijaProizvoda;
import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperEvidencijaProizvoda;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EvidencijaProizvodaController implements Initializable {

    public List<EvidencijaProizvoda> getAll() {
        return WrapperEvidencijaProizvoda.selectAll();
    }
    public List<EvidencijaProizvoda> getProizvodiFromMarket(Integer idMarketa){
        return WrapperEvidencijaProizvoda.selectPoizvodiFromMarket(idMarketa);
    }
    public boolean save (EvidencijaProizvoda evidencijaProizvoda) { return WrapperEvidencijaProizvoda.insert(evidencijaProizvoda) == 1; }
    public boolean delete(Integer idMarketa, Integer idProizvoda) { return WrapperEvidencijaProizvoda.deleteProizvodFromEvidencijaP(idMarketa, idProizvoda) == 1; }
    public boolean update(EvidencijaProizvoda evidencijaProizvoda) { return WrapperEvidencijaProizvoda.update(evidencijaProizvoda) == 1; }
    public EvidencijaProizvoda getProizvodFromEvidencijaP(Integer idMarketa, Integer idProizvoda) {
        return WrapperEvidencijaProizvoda.selectPoizvodFromMarket(idMarketa, idProizvoda);
    }

    @FXML
    ChoiceBox<String> marketiChoiceBox;
    @FXML
    Button potvrdi;
    public static Integer idMarketaFromParent;
    MarketController marketController = new MarketController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        List<Market> marketList = marketController.getAll();
        for(Market m : marketList) {
            list.add(m.getNaziv());
            marketiChoiceBox.setItems(list);
        }
    }

    @FXML
    public void potvrdiMarket(ActionEvent event) {
        if(marketiChoiceBox.getValue() != null) {
            String imeMarketa = marketiChoiceBox.getValue();
            Market market = marketController.getMarketByMarketName(imeMarketa);
            if(market != null){
               idMarketaFromParent = market.getIdMarketa();
               try{
                   FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EvidencijaProizvodaMarket.fxml"));
                   Scene scene = new Scene(fxmlLoader.load());
                   Stage stage = new Stage();
                   stage.initModality(Modality.WINDOW_MODAL);
                   stage.initOwner(((Node) event.getSource()).getScene().getWindow());
                   stage.setTitle(imeMarketa);
                   stage.setScene(scene);
                   stage.show();
               }catch(IOException e) {
                   e.printStackTrace();
               }
            }else {
                AlertBox.displayError("Market nije uspjesno ucitan.");
            }
        }else {
            AlertBox.displayError("Morate izabrati market.");
        }
    }
}
