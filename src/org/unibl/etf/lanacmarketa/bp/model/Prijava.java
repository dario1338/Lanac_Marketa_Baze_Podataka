package org.unibl.etf.lanacmarketa.bp.model;

import javafx.collections.ObservableList;

public class Prijava {
    private final ObservableList<String> prodavci;

    public Prijava(ObservableList<String> list) {
        this.prodavci = list;
    }

    public ObservableList<String> getProdavci() {
        return prodavci;
    }

}
