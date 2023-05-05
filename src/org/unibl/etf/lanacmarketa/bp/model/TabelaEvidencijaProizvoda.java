package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class TabelaEvidencijaProizvoda {

    public String sifra;
    public String naziv;
    public Integer kolicina;

    public TabelaEvidencijaProizvoda() {
    }

    public TabelaEvidencijaProizvoda(String sifra, String naziv, Integer kolicina) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.kolicina = kolicina;
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

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "TabelaEvidencijaProizvoda{" +
                "sifra='" + sifra + '\'' +
                ", naziv='" + naziv + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabelaEvidencijaProizvoda that = (TabelaEvidencijaProizvoda) o;
        return Objects.equals(sifra, that.sifra) && Objects.equals(naziv, that.naziv) && Objects.equals(kolicina, that.kolicina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, naziv, kolicina);
    }

}
