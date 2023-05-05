package org.unibl.etf.lanacmarketa.bp.model;

import java.util.Objects;

public class BonusKartica {

    Integer idKartice;
    String sifraKartice;
    Integer bodovi;

    public BonusKartica() {
    }

    public String getSifraKartice() {
        return sifraKartice;
    }

    public void setSifraKartice(String sifraKartice) {
        this.sifraKartice = sifraKartice;
    }

    public BonusKartica(Integer bodovi, String sifraKartice) {
        this.sifraKartice = sifraKartice;
        this.bodovi = bodovi;
    }
    public BonusKartica(Integer idKartice, Integer bodovi, String sifraKartice) {
        this.idKartice = idKartice;
        this.sifraKartice = sifraKartice;
        this.bodovi = bodovi;
    }

    public Integer getIdKartice() {
        return idKartice;
    }

    public void setIdKartice(Integer idKartice) {
        this.idKartice = idKartice;
    }

    public Integer getBodovi() {
        return bodovi;
    }

    public void setBodovi(Integer bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusKartica that = (BonusKartica) o;
        return Objects.equals(idKartice, that.idKartice) && Objects.equals(bodovi, that.bodovi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKartice, bodovi);
    }

    @Override
    public String toString() {
        return "BonusKartica{" +
                "idKartice=" + idKartice +
                ", bodovi=" + bodovi +
                '}';
    }

}
