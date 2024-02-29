/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import booking.BookingDTO;
import dal.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class TicketDAO {

    public TicketDTO createTicket( int showTimeId, String seatId, String bookingId) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO TICKET VALUES (?, ?, ?)");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());
            
            stm.setInt(1, showTimeId);
            stm.setString(2, seatId);
            stm.setString(3, bookingId);

            stm.executeUpdate();

            TicketDTO ticket = new TicketDTO( showTimeId, seatId, bookingId);
            return ticket;

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
}
