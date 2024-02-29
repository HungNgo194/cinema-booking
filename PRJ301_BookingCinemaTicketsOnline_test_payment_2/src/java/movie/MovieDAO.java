/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import dal.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MovieDAO {

    public List<MovieDTO> getAll() throws SQLException {
        List<MovieDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM MOVIE");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                String movieName = rs.getString("movieName");
                String movieContent = rs.getString("movieContent");
                String actor = rs.getString("actor");
                String director = rs.getString("director");
                int age = rs.getInt("age");
                MovieDTO movie = new MovieDTO(movieID, movieName, movieContent, actor, director, age);
                list.add(movie);
            }
            return list;
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
        return null;
    }

    public MovieDTO checkExistMovie(String movieName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM MOVIE WHERE movieName = ?");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, movieName);
            rs = stm.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                movieName = rs.getString("movieName");
                String movieContent = rs.getString("movieContent");
                String actor = rs.getString("actor");
                String director = rs.getString("director");
                int age = rs.getInt("age");
                MovieDTO movie = new MovieDTO(movieID, movieName, movieContent, actor, director, age);
                return movie;
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
        return null;
    }

    public boolean addNewMovie(String movieName, String movieContent, String actor, String director, int age) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("INSERT INTO MOVIE VALUES (?, ?, ?, ?, ?)");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, movieName);
            stm.setString(2, movieContent);
            stm.setString(3, actor);
            stm.setString(4, director);
            stm.setInt(5, age);

            int executeUpdate = stm.executeUpdate();
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

    public void deleteMovie(String movieName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        StringBuilder query = new StringBuilder("DELETE FROM MOVIE WHERE movieName = ?");
        try {
            String sql = null;
            sql = String.valueOf(query);
            con = DBUtils.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, movieName);
            stm.executeUpdate();
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
                System.out.println("An error occurred while closing the resources: ");
                e.printStackTrace();
            }
        }
    }

    public void modifyMovie(String movieName, String movieContent, String actor, String director, int age) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUtils.getConnection();
            String query = "UPDATE MOVIE SET movieName = ?,  movieContent = ?, actor = ?, director = ?, age = ? WHERE movieName = ?";
            stm = con.prepareStatement(query);
            stm.setString(1, movieName);
            stm.setString(2, movieContent);
            stm.setString(3, actor);
            stm.setString(4, director);
            stm.setInt(5, age);
            stm.setString(6, movieName); // setting movieName again for WHERE clause
            stm.executeUpdate();
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
                System.out.println("An error occurred while closing the resources: ");
                e.printStackTrace();
            }
        }
    }

    public List<MovieDTO> search(String movieName) throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<MovieDTO> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM MOVIE ");
        if (movieName != null && !movieName.isEmpty()) {
            query.append(" WHERE movieName like ? ");
        }
        try {
            con = DBUtils.getConnection();
            String sql;
            sql = String.valueOf(query);
            stm = con.prepareStatement(sql);
            if (movieName != null && !movieName.isEmpty()) {
                stm.setString(1, "%" + movieName + "%");
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                int movieID = rs.getInt("movieID");
                movieName = rs.getString("movieName");
                String movieContent = rs.getString("movieContent");
                String actor = rs.getString("actor");
                String director = rs.getString("director");
                int age = rs.getInt("age");
                MovieDTO movie = new MovieDTO(movieID, movieName, movieContent, actor, director, age);
                list.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("SQL: ");
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

}
