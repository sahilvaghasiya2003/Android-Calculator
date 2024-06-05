package com.example.mycalculator;

public class btn {
    private String number;
    private String nid;

    public btn(String value, String nid) {
        this.number = value;
        this.nid = nid;
    }

    public String getNid(){
        return nid;
    }

    public void setNid(String nid){
        this.nid = nid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String value) {
        this.number = value;
    }


}
