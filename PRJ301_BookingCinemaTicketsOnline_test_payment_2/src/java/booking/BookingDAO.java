/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booking;

import account.AccountDTO;
import dal.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 *
 * @author Admin
 */
public class BookingDAO implements Serializable {

    public BookingDTO createBooking(String bookingId, int numbOfBookings, int totalPrice, Date bookingDate, String userName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO BOOKING VALUES (?, ?, ?, ?, ?)");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setString(1, bookingId);
            stm.setInt(2, numbOfBookings);
            stm.setInt(3, totalPrice);
            stm.setDate(4, bookingDate);
            stm.setString(5, userName);

            stm.executeUpdate();

            BookingDTO newbook = new BookingDTO(bookingId, numbOfBookings, totalPrice, bookingDate, userName);
            return newbook;

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

//    public AccountDTO checkExistBooking(String userNameK, String passwordK) throws SQLException {
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//        StringBuilder query = new StringBuilder("SELECT * FROM ACCOUNT WHERE userName = ? and ");
//        try {
//            String sql = null;
//            sql = String.valueOf(query);
//            con = DBUtils.getConnection();
//            stm = con.prepareStatement(sql);
//            stm.setString(1, userNameK);
//            stm.setString(2, passwordK);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                String userName = rs.getString("userName");
//                String password = rs.getString("password");
//                String fullName = rs.getString("fullName");
//                String googleID = rs.getString("googleID");
//                String googleName = rs.getString("googleName");
//                String email = rs.getString("email");
//                String phoneNumber = rs.getString("phoneNumber");
//                boolean gender = rs.getBoolean("gender");
//                boolean role = rs.getBoolean("role");
//
//                AccountDTO accountDTO = new AccountDTO(userName, password, fullName, googleID, googleName, email, phoneNumber, gender, role);
//                return accountDTO;
//            }
//        } catch (SQLException e) {
//            System.out.println("SQL: ");
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stm != null) {
//                stm.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//        return null;
//    }
}
