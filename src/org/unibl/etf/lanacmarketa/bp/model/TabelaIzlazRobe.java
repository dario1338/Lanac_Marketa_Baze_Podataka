package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class TabelaIzlazRobe {

    public String nazivMarketa;
    public String sifraProizvoda;
    public String nazivProizvoda;
    public int izasloRobe;
    public int trenutnoStanje;

    public TabelaIzlazRobe() {
    }

    public TabelaIzlazRobe(String nazivMarketa, String sifraProizvoda, String nazivProizvoda, int izasloRobe, int trenutnoStanje) {
        this.nazivMarketa = nazivMarketa;
        this.sifraProizvoda = sifraProizvoda;
        this.nazivProizvoda = nazivProizvoda;
        this.izasloRobe = izasloRobe;
        this.trenutnoStanje = trenutnoStanje;
    }

    public String getNazivMarketa() {
        return nazivMarketa;
    }

    public void setNazivMarketa(String nazivMarketa) {
        this.nazivMarketa = nazivMarketa;
    }

    public String getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public int getIzasloRobe() {
        return izasloRobe;
    }

    public void setIzasloRobe(int izasloRobe) {
        this.izasloRobe = izasloRobe;
    }

    public int getTrenutnoStanje() {
        return trenutnoStanje;
    }

    public void setTrenutnoStanje(int trenutnoStanje) {
        this.trenutnoStanje = trenutnoStanje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabelaIzlazRobe that = (TabelaIzlazRobe) o;
        return izasloRobe == that.izasloRobe && trenutnoStanje == that.trenutnoStanje && Objects.equals(nazivMarketa, that.nazivMarketa) && Objects.equals(sifraProizvoda, that.sifraProizvoda) && Objects.equals(nazivProizvoda, that.nazivProizvoda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazivMarketa, sifraProizvoda, nazivProizvoda, izasloRobe, trenutnoStanje);
    }

    @Override
    public String toString() {
        return "TabelaIzlazRobe{" +
                "nazivMarketa='" + nazivMarketa + '\'' +
                ", sifraProizvoda='" + sifraProizvoda + '\'' +
                ", nazivProizvoda='" + nazivProizvoda + '\'' +
                ", izasloRobe=" + izasloRobe +
                ", trenutnoStanje=" + trenutnoStanje +
                '}';
    }

}
