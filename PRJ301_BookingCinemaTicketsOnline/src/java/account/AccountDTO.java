/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class AccountDTO {

    private String userName;
    private String password;
    private String lastName;
    private String googleID;
    private String googleName;
    private Date dob;
    private String email;
    private String phoneNumber;
    private boolean gender;
    private boolean role;

    public AccountDTO() {
    }

    public AccountDTO(String userName, String password, String lastName, String googleID, String googleName, Date dob, String email, String phoneNumber, boolean gender, boolean role) {
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.googleID = googleID;
        this.googleName = googleName;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getGoogleName() {
        return googleName;
    }

    public void setGoogleName(String googleName) {
        this.googleName = googleName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDTO{" + "userName=" + userName + ", password=" + password + ", lastName=" + lastName + ", googleID=" + googleID + ", googleName=" + googleName + ", dob=" + dob + ", email=" + email + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", role=" + role + '}';
    }

}
