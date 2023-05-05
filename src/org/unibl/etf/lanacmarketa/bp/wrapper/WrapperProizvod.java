package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.*;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperProizvod {

    public static final String SQL_SELECT_ALL = "SELECT * FROM proizvod";
    public static final String SQL_INSERT = "INSERT INTO proizvod (Naziv, Cijena, SifraProizvoda) VALUES (?, ? ,?)";
    private static final String SQL_UPDATE = "UPDATE proizvod SET Naziv=?, Cijena=?, SifraProizvoda=? WHERE IdProizvoda=?";
    private static final String SQL_DELETE = "DELETE FROM proizvod WHERE SifraProizvoda=?";
    private static final String SQL_SELECT_BY_ID_PROIZVODA = "SELECT * FROM proizvod WHERE IdProizvoda=?";
    private static final String SQL_SELECT_BY_SIFRA_PROIZVODA = "SELECT * FROM proizvod WHERE SifraProizvoda=?";
    private static final String Query = "{CALL dodaj_proizvod(?, ?, ?, ?)}";

    public static List<Proizvod> selectAll() {
        List<Proizvod> retVal = new ArrayList<Proizvod>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Proizvod(rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static Proizvod selectPoizvodByIdProizvoda(Integer idProizvoda) {
        Proizvod retVal = new Proizvod();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_ID_PROIZVODA);
            ps.setInt(1, idProizvoda);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new Proizvod(rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static Proizvod selectPoizvodBySifraProizvoda(String sifra) {
        Proizvod retVal = new Proizvod();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_SIFRA_PROIZVODA);
            ps.setString(1, sifra);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new Proizvod(rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }


    public static List<Proizvod> selectAllWithoutId() {
        List<Proizvod> retVal = new ArrayList<Proizvod>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Proizvod(
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static int insert (Proizvod proizvod) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proizvod.getNaziv());
            ps.setDouble(2, proizvod.getCijena());
            ps.setString(3, proizvod.getSifraProizvoda());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    proizvod.setProizvod(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int dodajProizvod (Proizvod proizvod) {
        int retVal = 0;

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            cs = c.prepareCall(Query);
            cs.setInt(1, proizvod.getProizvod());
            cs.setString(2, proizvod.getNaziv());
            cs.setDouble(3, proizvod.getCijena());
            cs.setString(4, proizvod.getSifraProizvoda());
            retVal = cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(cs, c);
        }
        return retVal;
    }

    public static int update(Proizvod proizvod) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {proizvod.getNaziv(), proizvod.getCijena(), proizvod.getSifraProizvoda(), proizvod.getProizvod()};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int deleteProizvod(String sifraProizvoda) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_DELETE);
            ps.setString(1, sifraProizvoda);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            AlertBox.displayError(e.getMessage());
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
