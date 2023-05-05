package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Prodavac;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperProdavac;
import java.util.List;

public class ProdavacController {

    public List<Prodavac> getAll() {
        return WrapperProdavac.selectAll();
    }
    public List<Prodavac> getByIdMarketa(Integer idMarketa) {
        return WrapperProdavac.selectByMarket(idMarketa);
    }
    public boolean save (Prodavac prodavac) {
        return WrapperProdavac.insert(prodavac) == 1;
    }
    public boolean delete (String jmb) {
        return WrapperProdavac.deleteProdavacByJMB(jmb) == 1;
    }
}
