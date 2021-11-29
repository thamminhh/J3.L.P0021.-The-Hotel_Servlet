/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.user;

import java.io.Serializable;

/**
 *
 * @author CND
 */
public class UsersRegisterError implements Serializable {

    private String emailLengErr;
    private String passwordLengErr ;
    private String confirmNotMatch;
    private String fullnameLengErr;
    private String emailIsExist;
    private String emailFormatErr;

    public String getEmailFormatErr() {
        return emailFormatErr;
    }

    public void setEmailFormatErr(String emailFormatErr) {
        this.emailFormatErr = emailFormatErr;
    }


    public UsersRegisterError() {
    }

    public String getEmailLengErr() {
        return emailLengErr;
    }

    public void setEmailLengErr(String emailLengErr) {
        this.emailLengErr = emailLengErr;
    }

    public String getPasswordLengErr() {
        return passwordLengErr;
    }

    public void setPasswordLengErr(String passwordLengErr) {
        this.passwordLengErr = passwordLengErr;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getFullnameLengErr() {
        return fullnameLengErr;
    }

    public void setFullnameLengErr(String fullnameLengErr) {
        this.fullnameLengErr = fullnameLengErr;
    }

    public String getEmailIsExist() {
        return emailIsExist;
    }

    public void setEmailIsExist(String emailIsExist) {
        this.emailIsExist = emailIsExist;
    }

 

}   