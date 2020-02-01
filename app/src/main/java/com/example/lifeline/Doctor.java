package com.example.lifeline;




public class Doctor {

    public String doc_name;
    public String doc_graduate;
    public String doc_profile;
    public String doc_dpt;
    public String doc_info;
    public int doc_token;


    public Doctor() {

    }

    public Doctor(String doc_name, String doc_graduate, String doc_dpt, String doc_profile, String doc_info, int doc_token) {

        this.doc_name = doc_name;
        this.doc_graduate = doc_graduate;
        this.doc_dpt = doc_dpt;
        this.doc_profile = doc_profile;
        this.doc_info = doc_info;
        this.doc_token= doc_token;

    }

    public int getDoc_token() {
        return doc_token;
    }

    public void setDoc_token(int doc_token) {
        this.doc_token = doc_token;
    }

    public String getDoc_info() {
        return doc_info;
    }

    public void setDoc_info(String doc_info) {
        this.doc_info = doc_info;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public String getDoc_graduate() {
        return doc_graduate;
    }

    public void setDoc_graduate(String doc_graduate) {
        this.doc_graduate = doc_graduate;
    }

    public String getDoc_profile() {
        return doc_profile;
    }

    public void setDoc_profile(String doc_profile) {
        this.doc_profile = doc_profile;
    }

    public String getDoc_dpt() {
        return doc_dpt;
    }

    public void setDoc_dpt(String doc_dpt) {
        this.doc_dpt = doc_dpt;
    }
}
