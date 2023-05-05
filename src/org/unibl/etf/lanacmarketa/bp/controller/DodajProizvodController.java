package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.unibl.etf.lanacmarketa.bp.model.AlertBox;
import org.unibl.etf.lanacmarketa.bp.model.Proizvod;

import java.util.List;

public class DodajProizvodController {

    @FXML
    TextField sifra;
    @FXML
    TextField naziv;
    @FXML
    TextField cijena;
    @FXML
    Button sacuvaj;
    Integer idProizvoda;
    String sifraProizvoda;
    String nazivProizvoda;
    double cijenaProizvoda;
    public ProizvodController proizvodController = new ProizvodController();
    private RadSaProizvodimaController radSaProizvodimaController = new RadSaProizvodimaController();
    public void setController (RadSaProizvodimaController radSaProizvodimaController) {
        this.radSaProizvodimaController = radSaProizvodimaController;
    }

    public void sacuvajProizvod(ActionEvent event) {
        if(sifra.getText().equals("") || naziv.getText().equals("") || cijena.getText().equals("")) {
            AlertBox.displayError("Popunite sva polja.");
        } else if (isNumeric(cijena.getText())) {
            boolean postojiProizvod = false;
            cijenaProizvoda = Double.parseDouble(cijena.getText());
            sifraProizvoda = sifra.getText();
            nazivProizvoda = naziv.getText();
            List<Proizvod> proizvodList = proizvodController.getAll();
            for(Proizvod p : proizvodList) {
                if(sifraProizvoda.equals(p.getSifraProizvoda())){
                    postojiProizvod = true;
                    break;
                }
            }
            if(postojiProizvod){
                AlertBox.displayError("Vec postoji proizvod sa unesenom sifrom.");
            }else {
                if(proizvodController.save(new Proizvod(nazivProizvoda, cijenaProizvoda, sifraProizvoda))){
                    AlertBox.displayConfirmation("Uspjesno ste dodali novi proizvod.");
                    sacuvaj.getScene().getWindow().hide();
                    radSaProizvodimaController.loadDataToTable();
                }else {
                    AlertBox.displayError("Dodavanje novog proizvoda nije uspjelo.");
                }
            }
        }else {
            AlertBox.displayError("U polje cijena morate unijeti broj.");
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

    public static boolean isNumericAndInt(String strNum) {
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
