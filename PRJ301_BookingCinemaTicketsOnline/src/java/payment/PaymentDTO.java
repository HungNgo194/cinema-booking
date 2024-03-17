/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payment;

import account.AccountDTO;
import booking.BookingDTO;
import java.sql.Date;
import java.time.LocalDate;
/**
 *
 * @author Admin
 */
public class PaymentDTO {
    /*[id]
      ,[amount]
      ,[orderInfo]
      ,[responseCode]
      ,[transactionNo]
      ,[bank]
      ,[payDate]
      ,[transactionStatus]
      ,[bookingID] Object
      ,[userName] Object
    */
    
  private Long id;
  private int amount;
  private String orderInfo;
  private String responseCode;
  private int transactionNo;
  private String bank;
  private LocalDate payDate;
  private String transactionStatus;
  private BookingDTO booking;
  private AccountDTO account;

    public PaymentDTO() {
    }

    public PaymentDTO(Long id, int amount, String orderInfo, String responseCode, int transactionNo, String bank, LocalDate payDate, String transactionStatus, BookingDTO booking, AccountDTO account) {
        this.id = id;
        this.amount = amount;
        this.orderInfo = orderInfo;
        this.responseCode = responseCode;
        this.transactionNo = transactionNo;
        this.bank = bank;
        this.payDate = payDate;
        this.transactionStatus = transactionStatus;
        this.booking = booking;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(int transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
  
}
