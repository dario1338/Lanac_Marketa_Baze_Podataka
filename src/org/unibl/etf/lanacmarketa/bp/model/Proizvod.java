package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class Proizvod {

    Integer proizvod;
    String sifraProizvoda;
    String naziv;
    double cijena;

    public Proizvod() {
    }

    public Proizvod(String naziv, double cijena, String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
        this.naziv = naziv;
        this.cijena = cijena;
    }

    public Proizvod(Integer proizvod, String naziv, double cijena, String sifraProizvoda) {
        this.proizvod = proizvod;
        this.sifraProizvoda = sifraProizvoda;
        this.naziv = naziv;
        this.cijena = cijena;
    }

    public Integer getProizvod() {
        return proizvod;
    }

    public void setProizvod(Integer proizvod) {
        this.proizvod = proizvod;
    }

    public String getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(String sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proizvod proizvod1 = (Proizvod) o;
        return Double.compare(proizvod1.cijena, cijena) == 0 && Objects.equals(proizvod, proizvod1.proizvod) && Objects.equals(sifraProizvoda, proizvod1.sifraProizvoda) && Objects.equals(naziv, proizvod1.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proizvod, sifraProizvoda, naziv, cijena);
    }

    @Override
    public String toString() {
        return "Proizvod{" +
                "proizvod=" + proizvod +
                ", sifraProizvoda='" + sifraProizvoda + '\'' +
                ", naziv='" + naziv + '\'' +
                ", cijena=" + cijena +
                '}';
    }

}
