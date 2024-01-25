/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;

/**
 *
 * @author Admin
 */
public class DAO {

    public Cinema getAll() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("select id, firstname, lastname, age from student");
        try {
            con = DBUtils.getConnection();
            String sql;
            sql = String.valueOf(query);
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int cinemaID = rs.getInt("cinemaID");
                String cinemaName = rs.getString("cinemaName");
                String city = rs.getString("city");
                String address = rs.getString("address");
                int hotline = rs.getInt("hotline");
                Cinema cinema = new Cinema(cinemaID, cinemaName, city, address, hotline);
                return cinema;
            }
        } catch (SQLException e) {
            System.out.println("SQL: ");
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }
}
