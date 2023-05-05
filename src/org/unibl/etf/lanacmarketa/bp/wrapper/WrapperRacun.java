package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Racun;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperRacun {

    public static final String SQL_SELECT_ALL = "SELECT * FROM racun";
    private static final String SQL_SELECT_BY_TIME_AND_KASA = "SELECT * FROM racun WHERE Datum=? AND KASA_IdKase=?";
    public static final String SQL_INSERT = "INSERT INTO racun (Datum, KASA_IdKase, Iznos, BONUS_KARTICA_IdKartice) VALUES (?, ?, ?, ?)";
    public static final String SQL_INSERT_WITH_BONUS = "INSERT INTO racun (Datum, KASA_IdKase, Iznos) VALUES (?, ?, ?)";

    public static List<Racun> selectAll() {
        List<Racun> retVal = new ArrayList<Racun>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Racun(rs.getInt(1),
                                rs.getTimestamp(2),
                                rs.getInt(3),
                                rs.getDouble(4),
                                rs.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static List<Racun> selectRacunByTimeAndKasa(Timestamp time, Integer idKase) {
        List<Racun> retVal = new ArrayList<Racun>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_TIME_AND_KASA);
            ps.setTimestamp(1, time);
            ps.setInt(2, idKase);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal.add(new Racun(rs.getInt(1),
                                rs.getTimestamp(2),
                                rs.getInt(3),
                                rs.getDouble(4),
                                rs.getInt(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }

    public static int insert (Racun racun) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, racun.getDatum());
            ps.setInt(2, racun.getIdKase());
            ps.setDouble(3, racun.getIznos());
            ps.setInt(4, racun.getIdKartice());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    racun.setIdRacuna(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int insertWithBonus (Racun racun) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT_WITH_BONUS, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, racun.getDatum());
            ps.setInt(2, racun.getIdKase());
            ps.setDouble(3, racun.getIznos());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    racun.setIdRacuna(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
