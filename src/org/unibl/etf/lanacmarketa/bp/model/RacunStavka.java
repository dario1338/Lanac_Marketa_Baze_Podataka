package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class RacunStavka {

    Integer idRacuna;
    Integer idProizvoda;
    Integer kolicina;
    double cijena;

    public RacunStavka() {
    }

    public RacunStavka(Integer idRacuna, Integer idProizvoda, Integer kolicina, double cijena) {
        this.idRacuna = idRacuna;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
        this.cijena = cijena;
    }

    public Integer getIdRacuna() {
        return idRacuna;
    }

    public void setIdRacuna(Integer idRacuna) {
        this.idRacuna = idRacuna;
    }

    public Integer getIdProizvoda() {
        return idProizvoda;
    }

    public void setIdProizvoda(Integer idProizvoda) {
        this.idProizvoda = idProizvoda;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
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
        RacunStavka that = (RacunStavka) o;
        return Double.compare(that.cijena, cijena) == 0 && Objects.equals(idRacuna, that.idRacuna) && Objects.equals(idProizvoda, that.idProizvoda) && Objects.equals(kolicina, that.kolicina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRacuna, idProizvoda, kolicina, cijena);
    }

    @Override
    public String toString() {
        return "RacunStavka{" +
                "idRacuna=" + idRacuna +
                ", idProizvoda=" + idProizvoda +
                ", kolicina=" + kolicina +
                ", cijena=" + cijena +
                '}';
    }

}
