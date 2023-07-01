package edu.northeastern.stickers.models;

public class Users {

    private java.lang.String userId;
    private java.lang.String email;
    private java.lang.String name;
    private String fcmToken;
    public Users() {
    }

    public Users(java.lang.String userId, java.lang.String email, java.lang.String name) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public Users(java.lang.String email, java.lang.String name) {
        this.email = email;
        this.name = name;
    }

    public Users(String email, String name, String userId, String fcmToken) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.fcmToken = fcmToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getUserId() {
        return userId;
    }

    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }
}
