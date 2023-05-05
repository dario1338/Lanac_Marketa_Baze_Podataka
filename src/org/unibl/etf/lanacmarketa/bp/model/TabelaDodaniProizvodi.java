package org.unibl.etf.lanacmarketa.bp.model;

public class TabelaDodaniProizvodi {

    String sifra;
    String naziv;
    double cijena;
    Integer kolicina;
    double ukupno;

    public TabelaDodaniProizvodi() {
    }

    public TabelaDodaniProizvodi(String sifra, String naziv, double cijena, Integer kolicina, double ukupno) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.ukupno = ukupno;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

}
