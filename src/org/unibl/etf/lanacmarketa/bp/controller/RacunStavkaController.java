package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.RacunStavka;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperRacunStavka;

import java.util.List;

public class RacunStavkaController {

    public List<RacunStavka> getAll() { return WrapperRacunStavka.selectAll(); }
    public boolean save (RacunStavka racun) { return WrapperRacunStavka.insert(racun) == 1; }
}
