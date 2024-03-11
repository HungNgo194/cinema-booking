/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatDetail;

import dal.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SeatDetailDAO {

    public boolean addSeat(String seatID, boolean seatStatus, int roomID, int showTimeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        List list = new ArrayList();
        StringBuilder query = new StringBuilder("insert into SEATDETAILS VALUES (?, ?, ?, ?) ");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setString(1, seatID);
            stm.setBoolean(2, true);
            stm.setInt(3, roomID);
            stm.setInt(4, showTimeID);

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e);
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<SeatDetailDTO> getAllByShowTimeID(int showTimeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List list = new ArrayList();
        StringBuilder query = new StringBuilder("SELECT * FROM SEATDETAILS WHERE showTimeID = ? ");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setInt(1, showTimeID);
            rs = stm.executeQuery();
            while (rs.next()) {
                String seatID = rs.getString("seatID");
                boolean seatStatus = rs.getBoolean("seatStatus");
                int roomID = rs.getInt("roomID");
                showTimeID = rs.getInt("showTimeID");
                SeatDetailDTO seat = new SeatDetailDTO(seatID, seatStatus, roomID, showTimeID);
                list.add(seat);
            }
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: " + e);
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
