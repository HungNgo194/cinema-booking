/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import account.AccountDTO;
import booking.BookingDTO;
import dal.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */

/* 
    private long id;
    private int amount;
    private String orderInfo;
    private String responseCode;
    private String transactionNo;
    private String bank;
    private String payDate;
    private String transactionStatus;
    private BookingDTO booking;
    private AccountDTO account;
 */
public class PaymentDAO {

    public Boolean createPayment(Long id, int amount, String orderInfo, String responseCode, int transactionNo, String bank, LocalDate payDate, String transactionStatus, BookingDTO booking, AccountDTO account) {
        Boolean b = false;
        try {
            String sql = "INSERT INTO PAYMENT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = DBUtils.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setLong(1, id);
            stm.setInt(2, amount);
            stm.setString(3, orderInfo);
            stm.setString(4, responseCode);
            stm.setInt(5, transactionNo);
            stm.setString(6, bank);
            stm.setDate(7, Date.valueOf(payDate));
            stm.setString(8, transactionStatus);
            stm.setString(9, booking.getBookingID());
            stm.setString(10, account.getUserName());

            if (stm.executeUpdate() > 0) {
                b = true;
            }

            con.close();
        } catch (SQLException ex) {
            System.out.println("Error in servlet. Details:" + ex.getMessage());
            ex.printStackTrace();

        }
        return b;
    }
}
