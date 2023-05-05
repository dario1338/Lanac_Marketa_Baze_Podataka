package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class Market {

    Integer idMarketa;
    String naziv;
    String adresa;
    Integer idMjesta;

    public Market() {
    }

    public Market(String naziv, String adresa, Integer idMjesta) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.idMjesta = idMjesta;
    }

    public Market(Integer idMarketa, String naziv, String adresa, Integer idMjesta) {
        this.idMarketa = idMarketa;
        this.naziv = naziv;
        this.adresa = adresa;
        this.idMjesta = idMjesta;
    }

    public Integer getIdMarketa() {
        return idMarketa;
    }

    public void setIdMarketa(Integer idMarketa) {
        this.idMarketa = idMarketa;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Integer getIdMjesta() {
        return idMjesta;
    }

    public void setIdMjesta(Integer idMjesta) {
        this.idMjesta = idMjesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return Objects.equals(idMarketa, market.idMarketa) && Objects.equals(naziv, market.naziv) && Objects.equals(adresa, market.adresa) && Objects.equals(idMjesta, market.idMjesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarketa, naziv, adresa, idMjesta);
    }

    @Override
    public String toString() {
        return "Market{" +
                "idMatketa=" + idMarketa +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", idMjesta=" + idMjesta +
                '}';
    }

}
