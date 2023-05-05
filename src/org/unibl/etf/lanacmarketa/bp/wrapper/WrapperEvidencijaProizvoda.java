package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.EvidencijaProizvoda;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperEvidencijaProizvoda {

    private static final String SQL_INSERT = "INSERT INTO evidencija_proizvoda(MARKET_IdMarketa, PROIZVOD_IdProizvoda, Kolicina) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM evidencija_proizvoda";
    private static final String SQL_SELECT_BY_IDMARKETA = "SELECT * FROM evidencija_proizvoda WHERE MARKET_IdMarketa=?";
    private static final String SQL_SELECT_BY_ID_MARKETA_ID_PROIZVODA = "SELECT * FROM evidencija_proizvoda WHERE MARKET_IdMarketa=? AND PROIZVOD_IdProizvoda=?";
    private static final String SQL_DELETE = "DELETE FROM evidencija_proizvoda WHERE MARKET_IdMarketa=? AND PROIZVOD_IdProizvoda=?";
    private static final String SQL_UPDATE = "UPDATE evidencija_proizvoda SET Kolicina=? WHERE MARKET_IdMarketa=? AND PROIZVOD_IdProizvoda=?";

    public static List<EvidencijaProizvoda> selectAll() {
        List<EvidencijaProizvoda> retVal = new ArrayList<EvidencijaProizvoda>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new EvidencijaProizvoda(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<EvidencijaProizvoda> selectPoizvodiFromMarket(Integer idMarketa) {
        List<EvidencijaProizvoda> retVal = new ArrayList<EvidencijaProizvoda>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_IDMARKETA);
            ps.setInt(1, idMarketa);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal.add(new EvidencijaProizvoda(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static EvidencijaProizvoda selectPoizvodFromMarket(Integer idMarketa, Integer idProizvoda) {
        EvidencijaProizvoda retVal = new EvidencijaProizvoda();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_ID_MARKETA_ID_PROIZVODA);
            ps.setInt(1, idMarketa);
            ps.setInt(2, idProizvoda);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new EvidencijaProizvoda(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static int insert (EvidencijaProizvoda evidencijaProizvoda) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, evidencijaProizvoda.getIdMarketa());
            ps.setInt(2, evidencijaProizvoda.getIdProizvoda());
            ps.setInt(3, evidencijaProizvoda.getKolicina());
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int update(EvidencijaProizvoda evidencijaProizvoda) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {evidencijaProizvoda.getKolicina(), evidencijaProizvoda.getIdMarketa(), evidencijaProizvoda.getIdProizvoda()};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int deleteProizvodFromEvidencijaP(Integer idMarketa, Integer idProizvoda) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_DELETE);
            ps.setInt(1, idMarketa);
            ps.setInt(2, idProizvoda);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
