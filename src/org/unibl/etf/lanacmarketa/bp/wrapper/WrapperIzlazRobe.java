package org.unibl.etf.lanacmarketa.bp.wrapper;

import org.unibl.etf.lanacmarketa.bp.model.TabelaIzlazRobe;
import org.unibl.etf.lanacmarketa.bp.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WrapperIzlazRobe {

    public static final String SQL_SELECT_ALL = "SELECT * FROM izlaz_robe_sedam_dana";

    public static List<TabelaIzlazRobe> selectAll() {
        List<TabelaIzlazRobe> retVal = new ArrayList<>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try{
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while(rs.next()){
                retVal.add(new TabelaIzlazRobe(rs.getString(1),
                                            rs.getString(2),
                                            rs.getString(3),
                                            rs.getInt(4),
                                            rs.getInt(5)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs, s, c);
        }
        return retVal;
    }

}
