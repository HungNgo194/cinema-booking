/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seat;

import dal.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ticket.TicketDTO;

/**
 *
 * @author Admin
 */
public class SeatDAO {

    public SeatDTO modifiedStatus(String seatId, int roomID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("UPDATE SEAT SET seatStatus = 1 where seatID = ? and roomID = ?");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setString(1, seatId);
            stm.setInt(2, roomID);

            stm.executeUpdate();

            SeatDTO seat = new SeatDTO(seatId, true, roomID);
            return seat;

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
        return null;
    }

    public ArrayList<SeatDTO> checkSeatStatus(int roomID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ArrayList<SeatDTO> seats = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT seatID FROM SEAT WHERE seatStatus = 1 and roomID = ?");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setInt(1, roomID);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Process each result and initialize seatId
                String seatId = rs.getString("seatID");
                SeatDTO seat = new SeatDTO(seatId, true, roomID);
                seats.add(seat);
            }
            return seats;
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
        return seats; // Return an empty ArrayList if there are no results
    }

}
