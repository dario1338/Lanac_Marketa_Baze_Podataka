package org.unibl.etf.lanacmarketa.bp.model;

public class Prodavac extends Zaposleni{

    public Prodavac() {
        super();
    }

    public Prodavac(String jmb, String ime, String prezime, Integer idMarketa) {
        super(jmb, ime, prezime, idMarketa);
        this.jmb = jmb;
    }

    public Prodavac(String jmb) {
        this.jmb = jmb;
    }

    @Override
    public void setJmb(String jmb) {
        this.jmb = jmb;
    }

    @Override
    public String getJmb() {
        return jmb;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
