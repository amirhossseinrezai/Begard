package com.example.begard;

public class User {
    public String UserID;
    public String fullName;
    public String email;
    public String password;
    public String number;

    public User() {
        this.email = email;
        this.number = number;
        this.UserID = UserID;
        this.password = password;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  //  public User getUser(String gID){
//User gUser = new User();

  //  }
}
