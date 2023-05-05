package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.unibl.etf.lanacmarketa.bp.model.*;

import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class ProdavacMenuController implements Initializable {

    @FXML
    Label labelProdavac;
    @FXML
    Label labelKasa;
    @FXML
    Label zaUplatu;
    @FXML
    Button noviRacun;
    @FXML
    Button zakljuciK;
    @FXML
    Button dodajProizvod;
    @FXML
    Button potvrdiBonusKarticu;
    @FXML
    Button dodajBodove;
    @FXML
    Button skiniBodove;
    @FXML
    Button potvrdiKupovinu;
    @FXML
    Button ukloni;
    @FXML
    TextField unesiSifruProizvoda;
    @FXML
    TextField kolicina;
    @FXML
    TextField unesiteSifruBK;
    @FXML
    TextArea bodovi;
    @FXML
    AnchorPane pane;
    @FXML
    TableView<TabelaDodaniProizvodi> tabelaDodaniProizvodi;
    @FXML
    TableColumn<TabelaDodaniProizvodi, String> sifra;
    @FXML
    TableColumn<TabelaDodaniProizvodi, String> naziv;
    @FXML
    TableColumn<TabelaDodaniProizvodi, Double> cijena;
    @FXML
    TableColumn<TabelaDodaniProizvodi, Integer> kolicinaTabela;
    @FXML
    TableColumn<TabelaDodaniProizvodi, Double> ukupno;
    public static final double POTREBAN_IZNOS_NA_RACUNU_ZA_JEDAN_BOD = 7;
    public static final int POTREBAN_BROJ_BODOVA_ZA_POPUST = 100;
    public static final double VRIJEDNOST_POPUSTA = 25;
    public static boolean bodoviPromijenjeno = false;
    public BonusKartica bonusKartica;
    LinkedHashMap<String, TabelaDodaniProizvodi> linkedHashMap = new LinkedHashMap<>();
    HashMap<String, Integer> proizvodi = new HashMap<>();
    public Racun racun;
    ProizvodController proizvodController = new ProizvodController();
    List<Proizvod> proizvodList = proizvodController.getAll();
    public ZaduzujeController zaduzujeController = new ZaduzujeController();

    public ZaduzujeController zaduzujeControllerParent = new ZaduzujeController();

    public void setController(ZaduzujeController zaduzujeController) {
        this.zaduzujeControllerParent = zaduzujeController;
    }

    public RacunController racunController = new RacunController();
    public RacunStavkaController racunStavkaController = new RacunStavkaController();
    public BonusKarticaController bonusKarticaController = new BonusKarticaController();
    public String prodavacImePrezime = ZaduzujeController.prodavacImePrezime;
    public String zaduzenaKasa = ZaduzujeController.zaduzenaKasa;

    public Zaduzuje zaduzuje = ZaduzujeController.zaduzujeFromParent;
    public double pocetnoStanjeKasa = zaduzuje.getStanje();
    public double krajnjeStanjeKasa = pocetnoStanjeKasa;
    public String bodoviNaKartici="";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelProdavac.setText(prodavacImePrezime);
        labelKasa.setText(zaduzenaKasa);
        pane.setDisable(true);
        setZaUplatuSaPopustom("");
        setTabelaDodaniProizvodi();
        setBodoviTextArea();

    }

    public Integer getIdKaseFromZaduzenaKasa(String zaduzenaKasa) {
        return ZaduzujeController.id.get(zaduzenaKasa);
    }

    public void setTabelaDodaniProizvodi() {
        ObservableList<TabelaDodaniProizvodi> list = FXCollections.observableArrayList();
        double sum = 0;

        for(Map.Entry<String, TabelaDodaniProizvodi> i : linkedHashMap.entrySet()){
            list.addAll(i.getValue());
            sum += i.getValue().getUkupno();
        }

        sifra.setCellValueFactory(new PropertyValueFactory<TabelaDodaniProizvodi, String>("sifra"));
        naziv.setCellValueFactory(new PropertyValueFactory<TabelaDodaniProizvodi, String>("naziv"));
        cijena.setCellValueFactory(new PropertyValueFactory<TabelaDodaniProizvodi, Double>("cijena"));
        kolicinaTabela.setCellValueFactory(new PropertyValueFactory<TabelaDodaniProizvodi, Integer>("kolicina"));
        ukupno.setCellValueFactory(new PropertyValueFactory<TabelaDodaniProizvodi, Double>("ukupno"));
        tabelaDodaniProizvodi.setItems(list);
        String s = Double.toString(sum);
        zaUplatu.setText(s);
    }

    public void setBodoviTextArea() {
        bodovi.setText(bodoviNaKartici);
    }

    public void setZaUplatuSaPopustom(String zaUplatuSaPopustom) {
        zaUplatu.setText(zaUplatuSaPopustom);
    }

    public void kreirajNoviRacun(ActionEvent event) {
        pane.setDisable(false);
        dodajBodove.setDisable(true);
        skiniBodove.setDisable(true);
        potvrdiBonusKarticu.setDisable(true);
        dodajProizvod.setDisable(false);
        ukloni.setDisable(false);
        potvrdiBonusKarticu.setDisable(false);
        bodoviPromijenjeno = false;
        linkedHashMap.clear();
        proizvodi.clear();
        setTabelaDodaniProizvodi();
    }

    public void potvrdiKupuvinuR(ActionEvent event) {
        if(!linkedHashMap.isEmpty()) {
            boolean isSavedRacun;
            Date date = new Date();
            long time = date.getTime();
            Timestamp timestamp = new Timestamp(time);
            timestamp.setNanos(0);
            Integer idKase = getIdKaseFromZaduzenaKasa(zaduzenaKasa);
            double ukupnoZaUplatu = Double.parseDouble(zaUplatu.getText());
            if(bodoviPromijenjeno){
             racun = new Racun(timestamp, idKase, ukupnoZaUplatu, bonusKartica.getIdKartice());
             isSavedRacun = racunController.save(racun);
            }else {
                racun = new Racun(timestamp, idKase, ukupnoZaUplatu);
                isSavedRacun = racunController.saveWithBonus(racun);
            }
            List<Racun> racunList = racunController.getRacunByTimeAndKasa(timestamp, idKase);
            Integer racunId = racunList.get(0).getIdRacuna();
            for(Map.Entry<String, TabelaDodaniProizvodi> i : linkedHashMap.entrySet()){
                boolean isSaved = false;
                RacunStavka racunStavka = new RacunStavka(racunId, proizvodi.get(i.getValue().getSifra()), i.getValue().getKolicina(), i.getValue().getCijena());
                isSaved = racunStavkaController.save(racunStavka);
                if(isSaved){
                    AlertBox.displayConfirmation("Uspjesno ste sacuvali stavku.");
                }
            }
            if(isSavedRacun){
                AlertBox.displayConfirmation("Uspjesno ste kreirali racun.");
                pane.setDisable(true);
                krajnjeStanjeKasa += ukupnoZaUplatu;
                linkedHashMap.clear();
                setTabelaDodaniProizvodi();
                unesiteSifruBK.setText("");
                bodovi.setText("");
            }
        }
    }

    public void potvrdiBonusKarticuE(ActionEvent event) {
        if(!unesiteSifruBK.getText().equals("")){
            if(isNumeric(unesiteSifruBK.getText())){
                String sifraBK = unesiteSifruBK.getText();
                bonusKartica = bonusKarticaController.getBonusKarticaBySifraKartice(sifraBK);
                if(bonusKartica != null) {
                    bodoviNaKartici = bonusKartica.getBodovi().toString();
                    dodajBodove.setDisable(false);
                    skiniBodove.setDisable(false);
                    setBodoviTextArea();
                } else {
                    AlertBox.displayError("Ne postoji kartica sa unesenom sifrom.");
                }
            }else {
                AlertBox.displayError("Sifra BONUS KARTICE se sastoji od 6 brojeva.");
            }
        }else {
            AlertBox.displayError("Unesite sifru BONUS KARTICE.");
        }
    }

    public void dodajBodoveE(ActionEvent event) {
        double ukupnoZaUplatu = Double.parseDouble(zaUplatu.getText());
        if(ukupnoZaUplatu >= POTREBAN_IZNOS_NA_RACUNU_ZA_JEDAN_BOD) {
           double rezultatDijeljenja = ukupnoZaUplatu / POTREBAN_IZNOS_NA_RACUNU_ZA_JEDAN_BOD;
           Integer ostvareniBodovi = (int) rezultatDijeljenja;
           BonusKartica bonusKarticaDodajBodove = new BonusKartica(bonusKartica.getIdKartice(), (ostvareniBodovi + bonusKartica.getBodovi()), bonusKartica.getSifraKartice());
           bonusKarticaController.update(bonusKarticaDodajBodove);
           bodoviNaKartici = bonusKarticaDodajBodove.getBodovi().toString();
           setBodoviTextArea();
           bodoviPromijenjeno = true;
           dodajProizvod.setDisable(true);
           ukloni.setDisable(true);
           dodajBodove.setDisable(true);
           potvrdiBonusKarticu.setDisable(true);
        }else {
            AlertBox.displayError("Vrijednost racuna nije dovoljna da biste dobili bodove.");
        }
    }

    public void skiniBodoveE(ActionEvent event) {
        int trenutniBrojBodova = Integer.parseInt(bodoviNaKartici);
        if(trenutniBrojBodova >= POTREBAN_BROJ_BODOVA_ZA_POPUST) {
            int brojBodovaNakonPopusta = trenutniBrojBodova - POTREBAN_BROJ_BODOVA_ZA_POPUST;
            double ukupnoZaUplatu = Double.parseDouble(zaUplatu.getText()) - VRIJEDNOST_POPUSTA;
            if(ukupnoZaUplatu > 0) {
                BonusKartica bonusKarticaSkiniBodove = new BonusKartica(bonusKartica.getIdKartice(), brojBodovaNakonPopusta, bonusKartica.getSifraKartice());
                bonusKarticaController.update(bonusKarticaSkiniBodove);
                bodoviNaKartici = bonusKarticaSkiniBodove.getBodovi().toString();
                setZaUplatuSaPopustom(Double.toString(ukupnoZaUplatu));
                setBodoviTextArea();
                bodoviPromijenjeno = true;
            }else {
                ukupnoZaUplatu = 0;
                BonusKartica bonusKarticaSkiniBodove = new BonusKartica(bonusKartica.getIdKartice(), brojBodovaNakonPopusta, bonusKartica.getSifraKartice());
                bonusKarticaController.update(bonusKarticaSkiniBodove);
                bodoviNaKartici = bonusKarticaSkiniBodove.getBodovi().toString();
                setZaUplatuSaPopustom(Double.toString(ukupnoZaUplatu));
                setBodoviTextArea();
                bodoviPromijenjeno = true;
            }
            potvrdiBonusKarticu.setDisable(true);
            dodajProizvod.setDisable(true);
            ukloni.setDisable(true);
        }else {
           AlertBox.displayError("Nemate dovoljan broj bodova za popust.");
        }
    }

    public void dodajProizvod(ActionEvent event) {
        if(!unesiSifruProizvoda.getText().equals("") && !kolicina.getText().equals("")) {
            if(isNumeric(kolicina.getText())) {
                boolean postoji = false;
                String sifraP = unesiSifruProizvoda.getText();
                int kolicinaP = Integer.parseInt(kolicina.getText());
                for (Proizvod p : proizvodList) {
                    if (sifraP.equals(p.getSifraProizvoda())) {
                        postoji = true;
                        TabelaDodaniProizvodi proizvod = new TabelaDodaniProizvodi(sifraP, p.getNaziv(), p.getCijena(), kolicinaP, (p.getCijena() * kolicinaP));
                        if (linkedHashMap.containsKey(proizvod.getSifra())) {
                            TabelaDodaniProizvodi old = linkedHashMap.get(proizvod.getSifra());
                            int kolicina = old.getKolicina() + proizvod.getKolicina();
                            double ukupno = kolicina * old.getCijena();
                            linkedHashMap.put(old.getSifra(), new TabelaDodaniProizvodi(old.getSifra(), old.getNaziv(), old.getCijena(), kolicina, ukupno));
                        } else {
                            linkedHashMap.put(proizvod.getSifra(), proizvod);
                            proizvodi.put(p.getSifraProizvoda(), p.getProizvod());
                        }
                        unesiSifruProizvoda.setText("");
                        kolicina.setText("");
                        setTabelaDodaniProizvodi();
                        potvrdiBonusKarticu.setDisable(false);
                    }
                }
                if (!postoji) {
                    AlertBox.displayError("Ne postoji proizvod sa unesenom sifrom.");
                }
            }else {
               AlertBox.displayError("U polje za kolicinu morate unijeti broj.");
            }
        }else {
       AlertBox.displayError("Unesite sifru proizvoda i kolicinu.");
        }
    }

    public void ukloniProizvod(ActionEvent event) {
        TabelaDodaniProizvodi proizvod = tabelaDodaniProizvodi.getSelectionModel().getSelectedItem();
        linkedHashMap.remove(proizvod.getSifra());
        proizvodi.remove(proizvod.getSifra());
        setTabelaDodaniProizvodi();
        if(tabelaDodaniProizvodi.getItems().isEmpty()){
            potvrdiBonusKarticu.setDisable(true);
        }
    }



    public void zakljuciKasu (ActionEvent event) {
        List<Zaduzuje> zaduzujeList = zaduzujeController.getAll();

        for(Zaduzuje z : zaduzujeList) {
            if( zaduzuje.getOdVremena().compareTo(z.getOdVremena()) == 0 && zaduzuje.getJmb().equals(z.getJmb())) {
                zaduzuje.setStanje(krajnjeStanjeKasa);
                Date date = new Date();
                long time = date.getTime();
                zaduzuje.setDoVremena(new Timestamp(time));
                zaduzujeController.update(zaduzuje);
                zaduzujeControllerParent.prodavci.getItems().add(prodavacImePrezime);
                zaduzujeControllerParent.kase.getItems().add(zaduzenaKasa);
                zaduzujeControllerParent.refreshProdavciAndKaseChoiceBox();
                AlertBox.displayConfirmation("Zakljucena kasa.");
                break;
            }
        }
        zakljuciK.getScene().getWindow().hide();
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
