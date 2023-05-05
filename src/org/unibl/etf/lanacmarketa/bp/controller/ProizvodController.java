package org.unibl.etf.lanacmarketa.bp.controller;

import org.unibl.etf.lanacmarketa.bp.model.Proizvod;
import org.unibl.etf.lanacmarketa.bp.wrapper.WrapperProizvod;

import java.util.List;

public class ProizvodController {

    public List<Proizvod> getAll() { return WrapperProizvod.selectAll(); }
    public List<Proizvod> getAllWithoutId() { return WrapperProizvod.selectAllWithoutId(); }
    public Proizvod getProizvodByIdProizvod(Integer idProizvoda) { return WrapperProizvod.selectPoizvodByIdProizvoda(idProizvoda); }
    public boolean save (Proizvod proizvod) { return WrapperProizvod.insert(proizvod) == 1; }
    public boolean saveProizvod(Proizvod proizvod) { return WrapperProizvod.dodajProizvod(proizvod) == 0; }
    public boolean delete (String sifraProizvoda) { return  WrapperProizvod.deleteProizvod(sifraProizvoda) == 1; }
    public boolean update(Proizvod proizvod) { return WrapperProizvod.update(proizvod) == 1; }
    public Proizvod getProizvodBySifraProizvod(String sifra) { return WrapperProizvod.selectPoizvodBySifraProizvoda(sifra); }
}
