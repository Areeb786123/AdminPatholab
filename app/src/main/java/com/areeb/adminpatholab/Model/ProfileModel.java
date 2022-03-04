package com.areeb.adminpatholab.Model;

public class ProfileModel {

    public String  name , email, Address,ProfilePic,Phone;


    public ProfileModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public  ProfileModel(String name , String email, String Phone , String Address, String ProfielPic){
        this.name = name ;
         this.email = email ;
         this.Phone =Phone;
         this.Address =Address;
         this.ProfilePic = ProfielPic;

    }



}
