package org.unibl.etf.lanacmarketa.bp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;

public class AdministrativniRadnikMenuController {

   @FXML
   MenuItem radSaZaposlenim;
   @FXML
   MenuItem proizvodi;
   @FXML
   MenuItem evidencijaProizvoda;
   @FXML
   MenuItem izlazRobe;

   public void radSaZaposlenimEvent(ActionEvent event) {
       try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RadSaZaposlenim.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rad sa zaposlenim");
            stage.show();
       } catch (IOException io) {
            io.printStackTrace();
       }
   }

    public void proizvodiEvent(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RadSaProizvodima.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Rad sa proizvodima");
            stage.show();
        } catch (
                IOException io) {
            io.printStackTrace();
        }
    }

    public void evidencijaProizvodaEvent(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EvidencijaProizvoda.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Evidencija proizvoda");
            stage.show();
        } catch (
                IOException io) {
            io.printStackTrace();
        }
    }

    public void izlazRobeEvent(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IzlazRobe.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Izlaz robe u posljednjih sedam dana");
            stage.show();
        } catch (
                IOException io) {
            io.printStackTrace();
        }
    }

}
