/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import dal.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class MembershipDAO {

    public MembershipDTO createMemberShip(String memberID, int totalSpend, int discount, String userName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        MembershipDTO member = null;
        String sql = "insert into membership values (?, ?, ?, ?)";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, memberID);
            stm.setInt(2, totalSpend);
            stm.setInt(3, discount);
            stm.setString(4, userName);

            stm.executeUpdate();
            member = new MembershipDTO(memberID, totalSpend, discount, userName);
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

    public MembershipDTO checkExistMemberShip(String userName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        MembershipDTO member = null;
        String sql = "select  * from membership where userName = ?";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, userName);
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return member;
    }

    public int getMemberShipDiscount(String userName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int discount = 0;
        String sql = "select discount from membership where userName = ?";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, userName);
            rs = stm.executeQuery();
            if (rs.next()) {
                // Retrieve the discount value from the result set
                discount = rs.getInt("discount");
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return discount;
    }
}
