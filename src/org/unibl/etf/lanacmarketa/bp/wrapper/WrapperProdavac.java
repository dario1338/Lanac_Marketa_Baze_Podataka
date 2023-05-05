package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Prodavac;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperProdavac {

    private static final String SQL_SELECT_ALL = "SELECT prodavac.JMB, Ime, Prezime, MARKET_IdMarketa" +
            " FROM prodavac, zaposleni" +
            " WHERE prodavac.JMB = zaposleni.JMB";

    private static final String SQL_SELECT_BY_IDMARKETA = "SELECT prodavac.JMB, Ime, Prezime, MARKET_IdMarketa " +
            "FROM prodavac, zaposleni " +
            "WHERE prodavac.JMB = zaposleni.JMB AND MARKET_IdMarketa=?";
    private static final String SQL_INSERT = "INSERT INTO prodavac (JMB) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM prodavac WHERE JMB=?";

    public static List<Prodavac> selectAll() {
        List<Prodavac> retVal = new ArrayList<Prodavac>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try{
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);
            while(rs.next()) {
                retVal.add(new Prodavac(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<Prodavac> selectByMarket(Integer idMarketa) {
        List<Prodavac> retVal = new ArrayList<Prodavac>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal.add(new Prodavac(rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static int insert (Prodavac prodavac) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {prodavac.getJmb()};
            ps = DBUtil.prepareStatement(c, SQL_INSERT, false, values);
            retVal = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int deleteProdavacByJMB(String jmb) {
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

}
