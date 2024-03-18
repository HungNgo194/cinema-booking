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
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShowTimeDTO showTime = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE showTimeID = ? and showStatus = 1");
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

    // MINH AN 
    public List<ShowTimeDTO> getAllShowTimes(int movieID1) throws SQLException {
        List<ShowTimeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * "
                + "FROM SHOWTIME "
                + "WHERE movieID = ? "
                + "AND closeDate >= CONVERT(date, GETDATE()) and showStatus = 1";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, movieID1);
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
            throw e;
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
    }

    public List<ShowTimeDTO> getAllShowTimesUnique(int movieID1) throws SQLException {
        List<ShowTimeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM SHOWTIME WHERE movieID = ? AND DATEDIFF(DAY, GETDATE(), openDate) < 1 and DATEDIFF(DAY, GETDATE(), closeDate) >= 0 AND showStatus = 1 GROUP BY "
                + "    closeDate, "
                + "    showTimeID, "
                + "    openDate, "
                + "    hourStart, "
                + "    hourEnd, "
                + "    roomID, "
                + "    movieID, "
                + "    showStatus";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, movieID1);
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
            throw e;
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
    }

    public List<String> getAllCloseDate(int movieID1) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT closeDate FROM SHOWTIME WHERE movieID = ? GROUP BY closeDate";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setInt(1, movieID1); // Set parameter after preparing the statement
            rs = stm.executeQuery();
            while (rs.next()) {
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                list.add(closeDate.toString());
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

    public List<ShowTimeDTO> getShowTimesWithOpenDateAndMovieId(LocalDate openDate, int movieID) throws SQLException {
        List<ShowTimeDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM SHOWTIME WHERE openDate = ? and movieID = ? and showStatus = 1";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setDate(1, Date.valueOf(openDate));
            stm.setInt(2, movieID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                LocalDate openDate1 = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID1 = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate1, closeDate, hourStart, hourEnd, showStatus, roomID, movieID1);
                list.add(showTime);

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

    public List<ShowTimeDTO> getShowTimeId2(LocalDate openDate, int movieID) throws SQLException {
        List<ShowTimeDTO> list = new ArrayList<>();
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        String query = "select * from SHOWTIME where openDate = ? and showStatus = 1 and movieID = ?";
        try {
            con = DBUtils.getConnection();
            stm = con.prepareStatement(query);
            stm.setDate(1, Date.valueOf(openDate));
            stm.setInt(2, movieID);
            rs = stm.executeQuery();
            while (rs.next()) {
                int showTimeID = rs.getInt("showTimeID");
                LocalDate openDate1 = rs.getDate("openDate").toLocalDate();
                LocalDate closeDate = rs.getDate("closeDate").toLocalDate();
                LocalTime hourStart = rs.getTime("hourStart").toLocalTime();
                LocalTime hourEnd = rs.getTime("hourEnd").toLocalTime();
                boolean showStatus = rs.getBoolean("showStatus");
                int roomID = rs.getInt("roomID");
                int movieID1 = rs.getInt("movieID");

                ShowTimeDTO showTime = new ShowTimeDTO(showTimeID, openDate1, closeDate, hourStart, hourEnd, showStatus, roomID, movieID1);
                list.add(showTime);

            }
            return list;
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
            throw e;
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public List<Integer> getShowTimeWithopenDateAndRoomID(LocalDate openDate, int roomID, int movieID) throws SQLException {
        List<Integer> result = new ArrayList<>();
        String query = "SELECT showTimeID FROM SHOWTIME WHERE  openDate = ? and roomID = ? and movieID = ?";

        try (Connection con = DBUtils.getConnection();
                PreparedStatement stm = con.prepareStatement(query)) {

            stm.setDate(1, Date.valueOf(openDate));
            stm.setInt(2, roomID);
            stm.setInt(3, movieID);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int showTimeID = rs.getInt("showTimeID");
                    result.add(showTimeID);
                }

            }
        } catch (SQLException e) {
            // Log the error using a logging framework
            System.out.println("An SQL error occurred: " + e.getMessage());
            throw e;
        }

        return result;
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

    ///
    public ShowTimeDTO getShowTimesWithID(int showTimeID1) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShowTimeDTO showTime = null;
        StringBuilder query = new StringBuilder("SELECT * FROM SHOWTIME WHERE showTimeID = ? and showStatus = 1");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, showTimeID1);
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

                showTime = new ShowTimeDTO(showTimeID, openDate, closeDate, hourStart, hourEnd, showStatus, roomID, movieID);
            }

        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        }
        return showTime;
    }

    // to set showtime status to 0
    public void setShowTimeStatus(int movieID, LocalDate openDate) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("UPDATE SHOWTIME SET showStatus = 0 where movieID = ? and openDate = ?");

        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, movieID);
            stm.setDate(2, Date.valueOf(openDate));
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An SQL error occurred: ");
            e.printStackTrace();
        }
    }

}
