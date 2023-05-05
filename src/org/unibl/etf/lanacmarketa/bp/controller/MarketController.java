package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperMarket;
import java.util.List;

public class MarketController {

    public List<Market> getAll() {
        return WrapperMarket.selectAll();
    }
    public Market getMarketByIdMarket(Integer idMarketa){
        return WrapperMarket.selectMarketByIdMarket(idMarketa);
    }
    public Market getMarketByMarketName(String imeMarketa) { return WrapperMarket.selectMarketByMarketName(imeMarketa); }
    public boolean save (Market market) {return WrapperMarket.insert(market) == 1;}
}
