package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Zaduzuje;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperZaduzuje {
    private static final String SQL_SELECT_ALL = "SELECT * FROM zaduzuje";
    private static final String SQL_INSERT = "INSERT INTO zaduzuje (KASA_IdKase, PRODAVAC_JMB, Stanje, OdVremena, DoVremena) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE zaduzuje SET Stanje=?, DoVremena=? WHERE PRODAVAC_JMB=? AND OdVremena=?";
    private static final String SQL_DELETE = "DELETE FROM zaduzuje WHERE PRODAVAC_JMB=?";

    public static List<Zaduzuje> selectAll() {
        List<Zaduzuje> retVal = new ArrayList<Zaduzuje>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Zaduzuje(rs.getInt(1),
                                rs.getString(2),
                                rs.getDouble(3),
                                rs.getTimestamp(4),
                                rs.getTimestamp(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static int insert (Zaduzuje zaduzuje) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, zaduzuje.getIdKase());
            ps.setString(2, zaduzuje.getJmb());
            ps.setDouble(3, zaduzuje.getStanje());
            ps.setTimestamp(4, zaduzuje.getOdVremena());
            ps.setTimestamp(5, zaduzuje.getDoVremena());
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int update(Zaduzuje zaduzuje) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {zaduzuje.getStanje(), zaduzuje.getDoVremena(), zaduzuje.getJmb(), zaduzuje.getOdVremena()};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int deleteZaduzenjeByJMB(String jmb) {
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
