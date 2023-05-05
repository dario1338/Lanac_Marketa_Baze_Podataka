package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.BonusKartica;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperBonusKartica;


import java.util.List;

public class BonusKarticaController {

    public List<BonusKartica> getAll() { return WrapperBonusKartica.selectAll(); }
    public BonusKartica getBonusKarticaBySifraKartice(String sifra) { return WrapperBonusKartica.selectKarticaBySifraKaritce(sifra); }

    public boolean save (BonusKartica bonusKartica) { return WrapperBonusKartica.insert(bonusKartica) == 1; }

    public boolean update(BonusKartica bonusKartica) { return WrapperBonusKartica.update(bonusKartica) == 1; }

}
