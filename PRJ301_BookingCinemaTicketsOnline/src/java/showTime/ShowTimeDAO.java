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
        return list;
    }

    public List<ShowTimeDTO> getShowTimeByMovieID(int movieID) throws SQLException {
        List list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShowTimeDTO showTime = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE movieID = ?");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setInt(1, movieID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                movieID = rs.getInt("movieID");
                showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                list.add(showTime);
            }
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
        return list;
    }

    public ShowTimeDTO getShowTimeByID(int showTimeID) throws SQLException {
        List list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShowTimeDTO showTime = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE showTimeID = ?");
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query.toString());
            stm.setInt(1, showTimeID);
            rs = stm.executeQuery();
            while (rs.next()) {
                showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID = rs.getInt("movieID");
                showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                return showTime;
            }
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
        return showTime;
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

    public ShowTimeDTO getLatestShowTime() {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShowTimeDTO latestShowTime = null;

        try {
            con = DBUtils.getConnection();
            String query = "SELECT TOP 1 * FROM ShowTime ORDER BY showTimeID DESC";
            stm = con.prepareStatement(query);
            rs = stm.executeQuery();

            if (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID = rs.getInt("movieID");
                latestShowTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
            }
        } catch (SQLException e) {
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

        return latestShowTime;
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

    public List<ShowTimeDTO> getShowTimesForRoomAndDateRangeForModifyAction(int showTimeID) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ShowTimeDTO> showTimes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE showTimeID = ? ");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, showTimeID);
            rs = stm.executeQuery();
            while (rs.next()) {
                showTimeID = rs.getInt("showTimeID");
                LocalDate openDate = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
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

    public List<ShowTimeDTO> getShowTimesForRoomAndDateRangeForAddAction(int movieID, int roomID, LocalDate openDate, LocalDate closeDate) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ShowTimeDTO> showTimes = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE movieID = ? AND roomID = ? AND openDate >= ? AND closeDate <= ?");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, movieID);
            stm.setInt(2, roomID);
            stm.setDate(3, Date.valueOf(openDate));
            stm.setDate(4, Date.valueOf(closeDate));
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                openDate = rs.getDate("openDate").toLocalDate();
                closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                roomID = rs.getInt("roomID");
                movieID = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                showTimes.add(showTime);
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        }
        return showTimes;
    }

    public ShowTimeDTO getShowTime(int movieID, int roomID, LocalDate openDate, LocalDate closeDate, LocalTime hourStart, LocalTime hourEnd) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE movieID = ? AND roomID = ? AND openDate >= ? AND closeDate <= ? AND hourStart >= AND hourEnd <= ?");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, movieID);
            stm.setInt(2, roomID);
            stm.setDate(3, Date.valueOf(openDate));
            stm.setDate(4, Date.valueOf(closeDate));
            stm.setTime(5, Time.valueOf(hourStart));
            stm.setTime(6, Time.valueOf(hourEnd));
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                openDate = rs.getDate("openDate").toLocalDate();
                closeDate = rs.getDate("closeDate").toLocalDate();
                hourStart = rs.getTime("hourStart").toLocalTime();
                hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                roomID = rs.getInt("roomID");
                movieID = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
                return showTime;
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        }
        return null;
    }
}
