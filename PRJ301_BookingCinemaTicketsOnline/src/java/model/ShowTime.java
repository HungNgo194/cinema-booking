/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class ShowTime {

    private int showTimeID;
    private Date openDate;
    private Date closeDate;
    private Time hourStart;
    private Time hourEnd;
    private boolean showStatus;
    private int roomID;
    private int movieID;

    public ShowTime() {
    }

    public ShowTime(int showTimeID, Date openDate, Date closeDate, Time hourStart, Time hourEnd, boolean showStatus, int roomID, int movieID) {
        this.showTimeID = showTimeID;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.showStatus = showStatus;
        this.roomID = roomID;
        this.movieID = movieID;
    }

    public int getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Time getHourStart() {
        return hourStart;
    }

    public void setHourStart(Time hourStart) {
        this.hourStart = hourStart;
    }

    public Time getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(Time hourEnd) {
        this.hourEnd = hourEnd;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "ShowTime{" + "showTimeID=" + showTimeID + ", openDate=" + openDate + ", closeDate=" + closeDate + ", hourStart=" + hourStart + ", hourEnd=" + hourEnd + ", showStatus=" + showStatus + ", roomID=" + roomID + ", movieID=" + movieID + '}';
    }

}
