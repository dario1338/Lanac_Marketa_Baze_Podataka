package org.unibl.etf.lanacmarketa.bp.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Zaduzuje {

    Timestamp odVremena;
    Timestamp doVremena;
    Integer idKase;
    String jmb;
    double stanje;


    public Zaduzuje() {
    }

    public Zaduzuje(Integer idKase,String jmb, double stanje, Timestamp odVremena, Timestamp doVremena) {
        this.odVremena = odVremena;
        this.idKase = idKase;
        this.jmb = jmb;
        this.doVremena = doVremena;
        this.stanje = stanje;
    }

    public Timestamp getOdVremena() {
        return odVremena;
    }

    public void setOdVremena(Timestamp odVremena) {
        this.odVremena = odVremena;
    }

    public Timestamp getDoVremena() {
        return doVremena;
    }

    public void setDoVremena(Timestamp doVremena) {
        this.doVremena = doVremena;
    }

    public Integer getIdKase() {
        return idKase;
    }

    public void setIdKase(Integer idKase) {
        this.idKase = idKase;
    }

    public String getJmb() {
        return jmb;
    }

    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    public double getStanje() {
        return stanje;
    }

    public void setStanje(double stanje) {
        this.stanje = stanje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zaduzuje zaduzuje = (Zaduzuje) o;
        return Double.compare(zaduzuje.stanje, stanje) == 0 && Objects.equals(odVremena, zaduzuje.odVremena) && Objects.equals(doVremena, zaduzuje.doVremena) && Objects.equals(idKase, zaduzuje.idKase) && Objects.equals(jmb, zaduzuje.jmb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odVremena, doVremena, idKase, jmb, stanje);
    }

    @Override
    public String toString() {
        return "Zaduzuje{" +
                "odVremena=" + odVremena +
                ", doVremena=" + doVremena +
                ", idKase=" + idKase +
                ", jmb='" + jmb + '\'' +
                ", stanje=" + stanje +
                '}';
    }

}
