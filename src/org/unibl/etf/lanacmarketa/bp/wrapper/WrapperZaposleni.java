package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Proizvod;
import org.unibl.etf.lanacmarketa.bp.model.Zaposleni;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperZaposleni {

    private static final String SQL_SELECT_ALL = "SELECT * FROM zaposleni";
    private static final String SQL_SELECT_BY_IDMARKETA = "SELECT * FROM zaposleni WHERE MARKET_IdMarketa=?";
    private static final String SQL_SELECT_BY_JMB = "SELECT * FROM zaposleni WHERE JMB=?";
    private static final String SQL_INSERT = "INSERT INTO zaposleni (JMB, Ime, Prezime, MARKET_IdMarketa) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE zaposleni SET MARKET_IdMarketa=? WHERE JMB=?";
    private static final String SQL_DELETE = "DELETE FROM zaposleni WHERE JMB=?";
    private static final String SQL_DODAJ_ZAPOSLENOG_PROCEDURA = "{CALL kreiranje_zaposlenog(?, ?, ?, ?)}";

    public static List<Zaposleni> selectAll() {
        List<Zaposleni> retVal = new ArrayList<Zaposleni>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Zaposleni(rs.getString(1),
                                rs.getString(2),
                                rs.getString("Prezime"),
                                rs.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<Zaposleni> selectByMarket(Integer idMarketa) {
        List<Zaposleni> retVal = new ArrayList<Zaposleni>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal.add(new Zaposleni(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static int insert (Zaposleni zaposleni) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, zaposleni.getJmb());
            ps.setString(2, zaposleni.getIme());
            ps.setString(3, zaposleni.getPrezime());
            ps.setInt(4, zaposleni.getIdMarketa());
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int dodajZaposlenog (Zaposleni zaposleni) {
        int retVal = 0;

        Connection c = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            cs = c.prepareCall(SQL_DODAJ_ZAPOSLENOG_PROCEDURA);
            cs.setString(1, zaposleni.getJmb());
            cs.setString(2, zaposleni.getIme());
            cs.setString(3, zaposleni.getPrezime());
            cs.setInt(4, zaposleni.getIdMarketa());
            retVal = cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(cs, c);
        }
        return retVal;
    }

    public static int update(Zaposleni zaposleni) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {zaposleni.getIdMarketa(), zaposleni.getJmb()};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int deleteZaposleniByJMB(String jmb) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_DELETE);
            ps.setString(1, jmb);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static Zaposleni selectZaposleniByJMB(String jmb) {
        Zaposleni retVal = new Zaposleni();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_JMB);
            ps.setString(1, jmb);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new Zaposleni(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

}
