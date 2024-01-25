/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Cinema {

    private int cinemaID;
    private String cinemaName;
    private String city;
    private String address;
    private int hotline;

    public Cinema() {
    }

    public Cinema(int cinemaID, String cinemaName, String city, String address, int hotline) {
        this.cinemaID = cinemaID;
        this.cinemaName = cinemaName;
        this.city = city;
        this.address = address;
        this.hotline = hotline;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHotline() {
        return hotline;
    }

    public void setHotline(int hotline) {
        this.hotline = hotline;
    }

    @Override
    public String toString() {
        return "Cinema{" + "cinemaID=" + cinemaID + ", cinemaName=" + cinemaName + ", city=" + city + ", address=" + address + ", hotline=" + hotline + '}';
    }

}
