package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.BonusKartica;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WrapperBonusKartica {

    private static final String SQL_SELECT_ALL = "SELECT * FROM bonus_kartica";
    private static final String SQL_SELECT_BY_SIFRA_KARTICE = "SELECT * FROM bonus_kartica WHERE SifraKartice=?";
    private static final String SQL_INSERT = "INSERT INTO bonus_kartica(Bodovi, SifraKartice) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE bonus_kartica SET Bodovi=? WHERE SifraKartice=?";

    public static List<BonusKartica> selectAll() {
        List<BonusKartica> retVal = new ArrayList<BonusKartica>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()) {
                retVal.add(new BonusKartica(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

    public static BonusKartica selectKarticaBySifraKaritce(String sifraKartice) {
        BonusKartica retVal = new BonusKartica();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_BY_SIFRA_KARTICE);
            ps.setString(1, sifraKartice);
            rs = ps.executeQuery();
            while(rs.next()) {
                retVal = new BonusKartica(rs.getInt(1), rs.getInt(2),rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, ps, c);
        }
        return retVal;
    }


    public static int insert (BonusKartica bonusKartica) {
        int retVal = 0;

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, bonusKartica.getBodovi());
            ps.setString(2, bonusKartica.getSifraKartice());
            retVal = ps.executeUpdate();

            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if(rs.next())
                    bonusKartica.setIdKartice(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public static int update(BonusKartica bonusKartica) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {bonusKartica.getBodovi(), bonusKartica.getSifraKartice()};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }

        return retVal;
    }

}
