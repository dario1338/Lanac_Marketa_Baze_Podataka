package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Proizvod;
import org.unibl.etf.lanacmarketa.bp.model.Zaposleni;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperProizvod;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperZaposleni;

import java.util.List;

public class ZaposleniController {

    public List<Zaposleni> getAll() {
        return WrapperZaposleni.selectAll();
    }
    public List<Zaposleni> getByIdMarketa(Integer idMarketa) {
        return WrapperZaposleni.selectByMarket(idMarketa);
    }
    public boolean save (Zaposleni zaposleni) {
        return WrapperZaposleni.insert(zaposleni) == 1;
    }
    public int update (Zaposleni zaposleni) {
        return WrapperZaposleni.update(zaposleni);
    }
    public boolean delete (String jmb) { return  WrapperZaposleni.deleteZaposleniByJMB(jmb) == 1; }
    public Zaposleni getZaposleniByJmb (String jmb) {
        return WrapperZaposleni.selectZaposleniByJMB(jmb);
    }
    public boolean saveZaposleni(Zaposleni zaposleni) { return WrapperZaposleni.dodajZaposlenog(zaposleni) == 0; }

}
