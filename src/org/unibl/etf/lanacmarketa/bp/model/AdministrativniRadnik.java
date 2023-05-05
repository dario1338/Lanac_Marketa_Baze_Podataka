package org.unibl.etf.lanacmarketa.bp.model;

public class AdministrativniRadnik extends Zaposleni{

    public AdministrativniRadnik() {
        super();
    }

    public AdministrativniRadnik(String jmb, String ime, String prezime, Integer idMarketa) {
        super(jmb, ime, prezime, idMarketa);
        this.jmb = jmb;
    }

    public AdministrativniRadnik(String jmb) {
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
