/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

/**
 *
 * @author Admin
 */
public class TicketDTO {

    private int ticketID;
    private int showTimeID;
    private String seatID;
    private String bookingID;

    public TicketDTO() {
    }

    public TicketDTO(int ticketID, int showTimeID, String seatID, String bookingID) {
        this.ticketID = ticketID;
        this.showTimeID = showTimeID;
        this.seatID = seatID;
        this.bookingID = bookingID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(int showTimeID) {
        this.showTimeID = showTimeID;
    }

    public String getSeatID() {
        return seatID;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketID=" + ticketID + ", showTimeID=" + showTimeID + ", seatID=" + seatID + ", bookingID=" + bookingID + '}';
    }

}
