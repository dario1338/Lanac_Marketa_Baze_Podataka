package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Kasa;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperKasa;
import java.util.List;

public class KasaController {

    public List<Kasa> getAll() { return WrapperKasa.selectAll(); }
    public boolean save (Kasa kasa) { return WrapperKasa.insert(kasa) == 1; }
    public List<Kasa> getByIdMarketa(Integer idMarketa) {
        return WrapperKasa.selectByMarket(idMarketa);
    }
}
