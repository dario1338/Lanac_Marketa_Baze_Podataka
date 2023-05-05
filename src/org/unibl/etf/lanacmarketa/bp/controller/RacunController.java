package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Racun;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperRacun;

import java.sql.Timestamp;
import java.util.List;

public class RacunController {

    public List<Racun> getAll() { return WrapperRacun.selectAll(); }
    public List<Racun> getRacunByTimeAndKasa(Timestamp time, Integer idKasa) {
        return WrapperRacun.selectRacunByTimeAndKasa(time, idKasa);
    }
    public boolean save (Racun racun) { return WrapperRacun.insert(racun) == 1; }
    public boolean saveWithBonus(Racun racun) { return WrapperRacun.insertWithBonus(racun) == 1; }
}
