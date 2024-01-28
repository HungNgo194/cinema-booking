/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room;

/**
 *
 * @author Admin
 */
public class RoomDTO {

    private int roomID;
    private int numberOfSeats;
    private int cinemaID;

    public RoomDTO() {
    }

    public RoomDTO(int roomID, int numberOfSeats, int cinemaID) {
        this.roomID = roomID;
        this.numberOfSeats = numberOfSeats;
        this.cinemaID = cinemaID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    @Override
    public String toString() {
        return "Room{" + "roomID=" + roomID + ", numberOfSeats=" + numberOfSeats + ", cinemaID=" + cinemaID + '}';
    }

}
