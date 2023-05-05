package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class Kasa {

    Integer idKase;
    Integer idMarketa;

    public Kasa() {
    }

    public Integer getIdKase() {
        return idKase;
    }

    public void setIdKase(Integer idKase) {
        this.idKase = idKase;
    }

    public Kasa(Integer idKase, Integer idMarketa) {

        this.idMarketa = idMarketa;
        this.idKase = idKase;
    }

    public Kasa(Integer idMarketa) {
        this.idMarketa = idMarketa;
    }

    public Integer getIdMarketa() {
        return idMarketa;
    }

    public void setIdMarketa(Integer idMarketa) {
        this.idMarketa = idMarketa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kasa kasa = (Kasa) o;
        return Objects.equals(idKase, kasa.idKase) && Objects.equals(idMarketa, kasa.idMarketa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKase, idMarketa);
    }

    @Override
    public String toString() {
        return "Kasa{" +
                "idKase=" + idKase +
                ", idMarketa=" + idMarketa +
                '}';
    }

}
