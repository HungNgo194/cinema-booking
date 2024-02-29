/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showTime;

import account.AccountDTO;
import dal.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ShowTimeDAO {

    public List<ShowTimeDTO> getAll() throws SQLException {
        List list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                list.add(showTime);

            }
            return list;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
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

    public List<ShowTimeDTO> getAllWithName(String movieName) throws SQLException {
        List list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT st.showTimeID, st.openDate, st.closeDate, st.hourStart, st.hourEnd, st.showStatus,st.roomID,st.movieID "
                + "FROM SHOWTIME st JOIN MOVIE mv ON st.movieID = st.movieID WHERE movieName = ? ";
        try {
            con = DBUtils.getConnection();
            stm.setString(1, movieName);
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                list.add(showTime);

            }
            return list;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
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

    public List<String> getAllShowTimes(int movieID1) throws SQLException {
        List<String> list = new ArrayList<>(); // Specify the type of list
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT openDate FROM SHOWTIME WHERE movieID = ? GROUP BY openDate";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, movieID1); // Set parameter after preparing the statement
            rs = stm.executeQuery();
            while (rs.next()) {
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                list.add(openDate.toString());
            }
            return list;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
            throw e; // Throw the exception or handle it appropriately
        } finally {
            if (rs != null) {
                rs.close(); // Close ResultSet
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<LocalTime> getShowTimesWithOpenDate(LocalDate openDate) throws SQLException {
        List<LocalTime> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT hourStart FROM SHOWTIME WHERE openDate = ?";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setDate(1, Date.valueOf(openDate));
            rs = stm.executeQuery();
            while (rs.next()) {
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                list.add(hourStart);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
            throw e; // Throw the exception or handle it appropriately
        } finally {
            if (rs != null) {
                rs.close(); // Close ResultSet
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean insertNewShowTime(LocalDate openDate, LocalDate closeDate, LocalTime hourStart, LocalTime hourEnd, boolean showStatus, int roomID, int movieID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO SHOWTIME (openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID) VALUES (?, ?, ?, ?, ?, ?, ?)");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setDate(1, Date.valueOf(openDate));
            stm.setDate(2, Date.valueOf(closeDate));
            stm.setTime(3, Time.valueOf(hourStart));
            stm.setTime(4, Time.valueOf(hourEnd));
            stm.setBoolean(5, true);
            stm.setInt(6, roomID);
            stm.setInt(7, movieID);

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
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

    public boolean modifyShowTime(LocalDate openDate, LocalDate closeDate, LocalTime hourStart, LocalTime hourEnd, boolean showStatus, int roomID, int movieID, int showTimeID) {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("update SHOWTIME set openDate = ?, closeDate = ?, hourStart = ?, hourEnd = ?, showStatus = ?, roomID = ?, movieID = ? \n "
                + " where showTimeID = ? ");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());

            stm.setDate(1, Date.valueOf(openDate));
            stm.setDate(2, Date.valueOf(closeDate));
            stm.setTime(3, Time.valueOf(hourStart));
            stm.setTime(4, Time.valueOf(hourEnd));
            stm.setBoolean(5, showStatus);
            stm.setInt(6, roomID);
            stm.setInt(7, movieID);
            stm.setInt(8, showTimeID);

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("An SQL error occurred: ");
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<ShowTimeDTO> getShowTimesForRoomAndDateRange(int roomID, LocalDate openDate, LocalDate closeDate) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ShowTimeDTO> showTimes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE roomID = ? AND openDate >= ? AND closeDate <= ?");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, roomID);
            stm.setDate(2, Date.valueOf(openDate));
            stm.setDate(3, Date.valueOf(closeDate));
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                openDate = rs.getDate("openDate").toLocalDate();
                closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                roomID = rs.getInt("roomID");
                int movieID = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                showTimes.add(showTime);
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        }
        return showTimes;
    }
}
