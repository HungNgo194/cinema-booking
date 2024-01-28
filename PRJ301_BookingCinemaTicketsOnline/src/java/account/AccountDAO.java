/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import dal.DBUtils;
import java.io.Serializable;
import static java.lang.Boolean.FALSE;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public AccountDTO checkExistAccount(String userNameK, String passwordK) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, userNameK);
            stm.setString(2, passwordK);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String lastName = rs.getString("lastName");
                String googleID = rs.getString("googleID");
                String googleName = rs.getString("googleName");
                Date dob = rs.getDate("dob");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                boolean gender = rs.getBoolean("gender");
                boolean role = rs.getBoolean("role");

                AccountDTO accountDTO = new AccountDTO(userName, password, lastName, googleID, googleName, dob, email, phoneNumber, gender, role);
                return accountDTO;
            }
        } catch (SQLException e) {
            System.out.println("SQL: ");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public AccountDTO createAccountGG(String googleID, String googleName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO ACCOUNT VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, googleName);
            stm.setString(2, "null");
            stm.setString(3, googleName);
            stm.setString(4, googleID);
            stm.setString(5, googleName);
            stm.setDate(6, Date.valueOf(LocalDate.MAX));
            stm.setString(7, "null");
            stm.setString(8, "null");
            stm.setBoolean(9, false);
            stm.setBoolean(10, false);

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL: ");
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

    public AccountDTO checkAccountGG(String userNameK) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM ACCOUNT WHERE USERNAME = ?");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, userNameK);
            rs = stm.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String lastName = rs.getString("lastNam e");
                String googleID = rs.getString("googleID");
                String googleName = rs.getString("googleName");
                Date dob = rs.getDate("dob");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                boolean gender = rs.getBoolean("gender");
                boolean role = rs.getBoolean("role");

                AccountDTO accountDTO = new AccountDTO(userName, password, lastName, googleID, googleName, dob, email, phoneNumber, gender, role);
                return accountDTO;
            }
        } catch (SQLException e) {
            System.out.println("SQL: ");
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
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
