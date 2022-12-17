package com.areeb.adminpatholab.Model;

public class HomeModel {
   String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public String getUserAppointmentDate() {
        return UserAppointmentDate;
    }

    public void setUserAppointmentDate(String userAppointmentDate) {
        UserAppointmentDate = userAppointmentDate;
    }


    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }

    public String getPaymentmode() {
        return Paymentmode;
    }

    public void setPaymentmode(String paymentmode) {
        Paymentmode = paymentmode;
    }

    public HomeModel(String patientName, String patientEmail, String patientPhone, String otp, String userGender, String userAppointmentDate, String userAppointmentTime, String userAddress, String paymentmode) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientPhone = patientPhone;
        this.otp = otp;
        UserGender = userGender;
        UserAppointmentDate = userAppointmentDate;

        UserAddress = userAddress;
        Paymentmode = paymentmode;
    }

    public  HomeModel(){

    }

    String patientEmail;
    String patientPhone;
    String otp;
    String UserGender;
    String UserAppointmentDate;

    String UserAddress;
    String Paymentmode;
}
