package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.RacunStavka;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperRacunStavka {

    public static final String SQL_SELECT_ALL = "SELECT * FROM racun_stavka";
    public static final String SQL_INSERT = "INSERT INTO racun_stavka (RACUN_IdRacuna, PROIZVOD_IdProizvoda, Kolicina, Cijena) VALUES (?, ?, ? ,?)";

    public static List<RacunStavka> selectAll() {
        List<RacunStavka> retVal = new ArrayList<RacunStavka>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new RacunStavka(rs.getInt(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getDouble(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static int insert (RacunStavka stavka) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT);
            ps.setInt(1, stavka.getIdRacuna());
            ps.setInt(2, stavka.getIdProizvoda());
            ps.setInt(3, stavka.getKolicina());
            ps.setDouble(4, stavka.getCijena());
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

}
