/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

import dal.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import ticket.TicketDTO;

/**
 *
 * @author Admin
 */
public class BookingDAO {

    public BookingDTO createBooking(String bookingId, int numbOfBooks, int priceTotal, LocalDate bookingDate, String userName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO BOOKING VALUES (?, ?, ?, ?, ?)");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setString(1, bookingId);
            stm.setInt(2, numbOfBooks);
            stm.setInt(3, priceTotal);
            stm.setDate(4, Date.valueOf(bookingDate));
            stm.setString(5, userName);

            stm.executeUpdate();

            BookingDTO booking = new BookingDTO(bookingId, numbOfBooks, priceTotal, bookingDate, userName);
            return booking;

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
