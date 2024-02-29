/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showTime;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Admin
 */
public class ShowTimeDTO {

    private int showTimeID;
    private LocalDate openDate;
    private LocalDate closeDate;
    private LocalTime hourStart;
    private LocalTime hourEnd;
    private boolean showStatus;
    private int roomID;
    private int movieID;

    public ShowTimeDTO() {
    }

    public ShowTimeDTO(int showTimeID, LocalDate openDate, LocalDate closeDate, LocalTime hourStart, LocalTime hourEnd, boolean showStatus, int roomID, int movieID) {
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

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public LocalTime getHourStart() {
        return hourStart;
    }

    public void setHourStart(LocalTime hourStart) {
        this.hourStart = hourStart;
    }

    public LocalTime getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(LocalTime hourEnd) {
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
        return "ShowTimeDTO{" + "showTimeID=" + showTimeID + ", openDate=" + openDate + ", closeDate=" + closeDate + ", hourStart=" + hourStart + ", hourEnd=" + hourEnd + ", showStatus=" + showStatus + ", roomID=" + roomID + ", movieID=" + movieID + '}';
    }

}
