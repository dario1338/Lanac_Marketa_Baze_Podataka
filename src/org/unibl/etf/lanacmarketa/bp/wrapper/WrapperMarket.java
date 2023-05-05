package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Market;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperMarket {

    private static final String SQL_SELECT_ALL = "SELECT * FROM market";
    private static final String SQL_SELECT_BY_IDMARKETA = "SELECT * FROM market WHERE IdMarketa=?";
    private static final String SQL_SELECT_BY_MARKET_NAME = "SELECT * FROM market WHERE Naziv=?";
    private static final String SQL_INSERT = "INSERT INTO market(IdMarketa, Naziv, Adresa, MJESTO_IdMjesta) VALUES(?,?,?,?)";

    public static List<Market> selectAll() {
        List<Market> retVal = new ArrayList<Market>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Market(rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static Market selectMarketByIdMarket(Integer idMarketa) {
        Market retVal = new Market();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new Market(rs.getInt(1),
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

    public static Market selectMarketByMarketName(String imeMarketa) {
        Market retVal = new Market();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_MARKET_NAME);
            ps.setString(1, imeMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new Market(rs.getInt(1),
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


    public static int insert (Market market) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, market.getIdMarketa());
            ps.setString(2, market.getNaziv());
            ps.setString(3, market.getAdresa());
            ps.setInt(4, market.getIdMjesta());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    market.setIdMarketa(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
