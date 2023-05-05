package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class Mjesto {

    private Integer idMjesta;
    private String naziv;

    public Mjesto() {
    }

    public Mjesto(Integer idMjesta, String naziv) {
        this.idMjesta = idMjesta;
        this.naziv = naziv;
    }

    public Integer getIdMjesta() {
        return idMjesta;
    }

    public void setIdMjesta(Integer idMjesta) {
        this.idMjesta = idMjesta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mjesto mjesto = (Mjesto) o;
        return Objects.equals(idMjesta, mjesto.idMjesta) && Objects.equals(naziv, mjesto.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMjesta, naziv);
    }

    @Override
    public String toString() {
        return "Mjesto{" +
                "idMjesta=" + idMjesta +
                ", naziv='" + naziv + '\'' +
                '}';
    }

}
