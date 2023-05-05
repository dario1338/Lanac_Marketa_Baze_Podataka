package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class EvidencijaProizvoda {

    Integer idMarketa;
    Integer idProizvoda;
    Integer kolicina;

    public EvidencijaProizvoda() {
    }

    public EvidencijaProizvoda(Integer idMarketa, Integer idProizvoda, Integer kolicina) {
        this.idMarketa = idMarketa;
        this.idProizvoda = idProizvoda;
        this.kolicina = kolicina;
    }

    public Integer getIdMarketa() {
        return idMarketa;
    }

    public void setIdMarketa(Integer idMarketa) {
        this.idMarketa = idMarketa;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvidencijaProizvoda that = (EvidencijaProizvoda) o;
        return Objects.equals(idMarketa, that.idMarketa) && Objects.equals(idProizvoda, that.idProizvoda) && Objects.equals(kolicina, that.kolicina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarketa, idProizvoda, kolicina);
    }

    @Override
    public String toString() {
        return "EvidencijaProizvoda{" +
                "idMarketa=" + idMarketa +
                ", idProizvoda=" + idProizvoda +
                ", kolicina=" + kolicina +
                '}';
    }

}
