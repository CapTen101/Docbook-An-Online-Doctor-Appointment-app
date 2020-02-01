package com.example.lifeline;

public class User {
    public String name, branch, email, my_doctor;
    private String enrollment;
    public int my_token;

    public User() {
    }

    public User(String name, String branch, String email, String enrollment, String my_doctor, int my_token) {
        this.name = name;
        this.email = email;
        this.enrollment = enrollment;
        this.my_doctor = my_doctor;
        this.my_token = my_token;
        this.branch = branch;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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


    public String getMy_doctor() {
        return my_doctor;
    }

    public void setMy_doctor(String my_doctor) {
        this.my_doctor = my_doctor;
    }

    public int getMy_token() {
        return my_token;
    }

    public void setMy_token(int my_token) {
        this.my_token = my_token;
    }
}
