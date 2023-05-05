package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Mjesto;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperMjesto;
import java.util.List;

public class MjestoController {

    public List<Mjesto> getAll() {
        return WrapperMjesto.selectAll();
    }
    public boolean save(Mjesto mjesto) {
        return WrapperMjesto.insert(mjesto) == 1;
    }
}
