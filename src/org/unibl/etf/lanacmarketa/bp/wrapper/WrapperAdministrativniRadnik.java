package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.AdministrativniRadnik;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperAdministrativniRadnik {

    private static final String SQL_SELECT_ALL = "SELECT administrativni_radnik.JMB, Ime, Prezime, MARKET_IdMarketa " +
            "FROM administrativni_radnik, zaposleni " +
            "WHERE administrativni_radnik.JMB = zaposleni.JMB";
    private static final String SQL_SELECT_BY_IDMARKETA = "SELECT administrativni_radnik.JMB, Ime, Prezime, MARKET_IdMarketa " +
            "FROM administrativni_radnik, zaposleni " +
            "WHERE administrativni_radnik.JMB = zaposleni.JMB AND MARKET_IdMarketa=?";
    private static final String SQL_INSERT = "INSERT INTO administrativni_radnik (JMB) VALUES (?)";

    public static List<AdministrativniRadnik> selectAll() {
        List<AdministrativniRadnik> retVal = new ArrayList<AdministrativniRadnik>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try{
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()){
                retVal.add(new AdministrativniRadnik(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4)));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<AdministrativniRadnik> selectByMarket(Integer idMarketa) {
        List<AdministrativniRadnik> retVal = new ArrayList<AdministrativniRadnik>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();

            while(rs.next()) {
                retVal.add(new AdministrativniRadnik(rs.getString(1),
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


    public static int insert (AdministrativniRadnik administrativniRadnik) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {administrativniRadnik.getJmb()};
            ps = DBUtil.prepareStatement(c, SQL_INSERT, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
