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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public boolean checkAll() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("select * from BOOKING b join TICKET t on b.bookingID = t.bookingID where DATEDIFF(DAY, GETDATE(), bookingDate) = 0");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
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
        return false;
    }

    public List<BookingDTO> getAllByUserName(String userName) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM BOOKING WHERE userName = ?");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, userName);
            rs = stm.executeQuery();
            while (rs.next()) {
                String bookingID = rs.getString("bookingID");
                int numberOfBooking = rs.getInt("numberOfBooking");
                int priceTotal = rs.getInt("priceTotal");
                LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
                userName = rs.getString("userName");
                BookingDTO booking = new BookingDTO(bookingID, numberOfBooking, priceTotal, bookingDate, userName);
                list.add(booking);
            }
        } catch (SQLException e) {
            System.out.println("SQL: ");
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
