package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.Proizvod;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class IzmijeniProizvodController implements Initializable {

    @FXML
    TextField sifra;
    @FXML
    TextField naziv;
    @FXML
    TextField cijena;
    @FXML
    Button sacuvaj;

    Proizvod proizvod = RadSaProizvodimaController.proizvodFromParentView;
    ProizvodController proizvodController = new ProizvodController();

    String sifraProizvoda = proizvod.getSifraProizvoda();
    String nazivProizvoda = proizvod.getNaziv();
    double cijenaProizvoda = proizvod.getCijena();
    String sifraIzabranogProizvoda = sifraProizvoda;
    List<Proizvod> proizvodList = proizvodController.getAll();
    private Integer idProizvoda;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataToTable();
    }

    public void loadDataToTable() {
        sifra.setText(sifraProizvoda);
        naziv.setText(nazivProizvoda);
        cijena.setText(String.valueOf(cijenaProizvoda));
    }

    private RadSaProizvodimaController radSaProizvodimaController = new RadSaProizvodimaController();
    public void setController (RadSaProizvodimaController radSaProizvodimaController) {
        this.radSaProizvodimaController = radSaProizvodimaController;
    }

    @FXML
    public void izmijeniProizvod(ActionEvent event) {
        if(proizvod != null) {
            for (Proizvod p : proizvodList) {
                if (proizvod.getSifraProizvoda().equals(p.getSifraProizvoda())) {
                    idProizvoda = p.getProizvod();
                    break;
                }
            }
        }
        if(sifra.getText().equals("") || naziv.getText().equals("") || cijena.getText().equals("")) {
            AlertBox.displayError("Popunite sva polja.");
        } else if (isNumeric(cijena.getText())) {
            boolean postojiProizvod = false;
            cijenaProizvoda = Double.parseDouble(cijena.getText());
            sifraProizvoda = sifra.getText();
            nazivProizvoda = naziv.getText();
            for(Proizvod p : proizvodList) {
                if(!sifraProizvoda.equals(sifraIzabranogProizvoda) && sifraProizvoda.equals(p.getSifraProizvoda())){
                    postojiProizvod = true;
                    break;
                }
            }
            if(postojiProizvod){
                AlertBox.displayError("Vec postoji proizvod sa sifrom koju ste unijeli.");
            }else {
                if(proizvodController.update(new Proizvod(idProizvoda, nazivProizvoda, cijenaProizvoda, sifraProizvoda))){
                    AlertBox.displayConfirmation("Uspjesno ste azurirali proizvod.");
                    sacuvaj.getScene().getWindow().hide();
                    radSaProizvodimaController.loadDataToTable();
                }else {
                    AlertBox.displayError("Izmjena je uspjesno izvrsena.");
                }
            }
        }else {
            AlertBox.displayError("U polju cijena morate unijeti broj.");
        }
    }

    public static boolean isNumeric(String strNum) {
        if(strNum == null) {
            return false;
        }
        try{
            double num = Double.parseDouble(strNum);
        }catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
