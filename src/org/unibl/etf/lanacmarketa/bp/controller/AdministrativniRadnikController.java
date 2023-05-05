package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.AdministrativniRadnik;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperAdministrativniRadnik;

import java.util.List;

public class AdministrativniRadnikController {

    public List<AdministrativniRadnik> getAll() {
        return WrapperAdministrativniRadnik.selectAll();
    }

    public List<AdministrativniRadnik> getByIdMarketa(Integer idMarketa) {
        return WrapperAdministrativniRadnik.selectByMarket(idMarketa);
    }

    public boolean save (AdministrativniRadnik administrativniRadnik) {
        return WrapperAdministrativniRadnik.insert(administrativniRadnik) == 1;
    }
}
