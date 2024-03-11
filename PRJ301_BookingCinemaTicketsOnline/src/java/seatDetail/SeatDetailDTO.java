/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatDetail;

/**
 *
 * @author ROG STRIX
 */
public class SeatDetailDTO {

    private String seatID;
    private boolean seatStatus;
    private int roomID;
    private int showTimeID;

    public SeatDetailDTO() {
    }

    public SeatDetailDTO(String seatID, boolean seatStatus, int roomID, int showTimeID) {
        this.seatID = seatID;
        this.seatStatus = seatStatus;
        this.roomID = roomID;
        this.showTimeID = showTimeID;
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

    public int getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

}