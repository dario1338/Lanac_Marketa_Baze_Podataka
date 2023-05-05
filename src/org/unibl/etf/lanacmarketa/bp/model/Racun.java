package org.unibl.etf.lanacmarketa.bp.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Racun {

    Integer idRacuna;
    Timestamp datum;
    Integer idKase;
    double iznos;
    Integer idKartice;

    public Racun() {
    }

    public Racun(Timestamp datum, Integer idKase, double iznos) {
        this.datum = datum;
        this.idKase = idKase;
        this.iznos = iznos;
    }

    public Racun(Timestamp datum, Integer idKase, double iznos, Integer idKartice) {
        this.datum = datum;
        this.idKase = idKase;
        this.iznos = iznos;
        this.idKartice = idKartice;
    }

    public Racun(Integer idRacuna, Timestamp datum, Integer idKase, double iznos, Integer idKartice) {
        this.idRacuna = idRacuna;
        this.datum = datum;
        this.idKase = idKase;
        this.iznos = iznos;
        this.idKartice = idKartice;
    }

    public Integer getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(Integer idRacuna) {
        this.idRacuna = idRacuna;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public Integer getIdKase() {
        return idKase;
    }

    public void setIdKase(Integer idKase) {
        this.idKase = idKase;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Integer getIdKartice() {
        return idKartice;
    }

    public void setIdKartice(Integer idKartice) {
        this.idKartice = idKartice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racun racun = (Racun) o;
        return Double.compare(racun.iznos, iznos) == 0 && Objects.equals(idRacuna, racun.idRacuna) && Objects.equals(datum, racun.datum) && Objects.equals(idKase, racun.idKase) && Objects.equals(idKartice, racun.idKartice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRacuna, datum, idKase, iznos, idKartice);
    }

    @Override
    public String toString() {
        return "Racun{" +
                "idRacuna=" + idRacuna +
                ", datum=" + datum +
                ", idKase=" + idKase +
                ", iznos=" + iznos +
                ", idKartice=" + idKartice +
                '}';
    }

}
