package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class Zaposleni {

    String jmb;
    private String ime;
    private String prezime;
    private Integer idMarketa;

    public Zaposleni() {
    }

    public Zaposleni(String jmb, String ime, String prezime, Integer idMarketa) {
        this.jmb = jmb;
        this.ime = ime;
        this.prezime = prezime;
        this.idMarketa = idMarketa;
    }

    public String getJmb() {
        return jmb;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Integer getIdMarketa() {
        return idMarketa;
    }
    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setIdMarketa(Integer idMarketa) {
        this.idMarketa = idMarketa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zaposleni zaposleni = (Zaposleni) o;
        return Objects.equals(jmb, zaposleni.jmb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmb);
    }

    @Override
    public String toString() {
        return "Zaposleni{" +
                "jmb='" + jmb + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", idMarketa=" + idMarketa +
                '}';
    }

}
