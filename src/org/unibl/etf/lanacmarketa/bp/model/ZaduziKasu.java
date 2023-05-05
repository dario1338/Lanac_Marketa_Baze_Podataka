package org.unibl.etf.lanacmarketa.bp.model;

import javafx.collections.ObservableList;

public class ZaduziKasu {
    private final ObservableList<String> kase;
    public ZaduziKasu(ObservableList<String> list) {
        this.kase = list;
    }
    public ObservableList<String> getKase() {
        return kase;
    }

}
