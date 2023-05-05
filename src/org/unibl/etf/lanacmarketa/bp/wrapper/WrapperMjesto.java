package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.Mjesto;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperMjesto {

    private static final String SQL_SELECT_ALL = "SELECT * FROM mjesto";
    private static final String SQL_INSERT = "INSERT INTO mjesto(IdMjesta, Naziv) VALUES(?,?)";

    public static List<Mjesto> selectAll() {
        List<Mjesto> retVal = new ArrayList<Mjesto>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new Mjesto(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static int insert(Mjesto mjesto) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {mjesto.getIdMjesta(), mjesto.getNaziv()};
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
