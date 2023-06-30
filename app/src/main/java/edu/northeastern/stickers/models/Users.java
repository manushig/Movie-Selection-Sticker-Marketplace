package edu.northeastern.stickers.models;

public class Users {

    private String userId;
    private String email;
    private String name;

    public Users() {
    }

    public Users(String userId, String email, String name) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
