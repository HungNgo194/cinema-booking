/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Booking {

    private String bookingID;
    private int numberOfBooking;
    private int priceTotal;
    private Date bookingDate;
    private String userName;

    public Booking() {
    }

    public Booking(String bookingID, int numberOfBooking, int priceTotal, Date bookingDate, String userName) {
        this.bookingID = bookingID;
        this.numberOfBooking = numberOfBooking;
        this.priceTotal = priceTotal;
        this.bookingDate = bookingDate;
        this.userName = userName;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public int getNumberOfBooking() {
        return numberOfBooking;
    }

    public void setNumberOfBooking(int numberOfBooking) {
        this.numberOfBooking = numberOfBooking;
    }

    public int getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(int priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingID=" + bookingID + ", numberOfBooking=" + numberOfBooking + ", priceTotal=" + priceTotal + ", bookingDate=" + bookingDate + ", userName=" + userName + '}';
    }

}
