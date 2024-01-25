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
public class Seat {

    private String seatID;
    private boolean seatStatus;
    private int roomID;

    public Seat() {
    }

    public Seat(String seatID, boolean seatStatus, int roomID) {
        this.seatID = seatID;
        this.seatStatus = seatStatus;
        this.roomID = roomID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public boolean isSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(boolean seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Seat{" + "seatID=" + seatID + ", seatStatus=" + seatStatus + ", roomID=" + roomID + '}';
    }

}
