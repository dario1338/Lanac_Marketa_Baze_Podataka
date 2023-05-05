package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Kasa;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperKasa {

    public static final String SQL_SELECT_ALL = "SELECT * FROM kasa";
    public static final String SQL_INSERT = "INSERT INTO kasa (MARKET_IdMarketa) VALUES (?)";
    public static final String SQL_SELECT_BY_IDMARKETA = "SELECT kasa.IdKase, MARKET_IdMarketa" +
                            " FROM kasa " +
                            "WHERE MARKET_IdMarketa=?";

    public static List<Kasa> selectAll() {
        List<Kasa> retVal = new ArrayList<Kasa>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Kasa(rs.getInt(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<Kasa> selectByMarket(Integer idMarketa) {
        List<Kasa> retVal = new ArrayList<Kasa>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal.add(new Kasa(rs.getInt(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static int insert (Kasa kasa) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            //ps.setInt(1, kasa.getIdKase());
            ps.setInt(1, kasa.getIdMarketa());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    kasa.setIdKase(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
