package com.example.anushak.altaoss;

/**
 * Created by Anushak on 29-03-2019.
 */

public class Notification {

    String NTitle = null;
    String NMessage = null;
    String NDate = null;

    public Notification(String Ntitle, String Nmessage, String Ndate) {

        super();

        this.NTitle = Ntitle;

        this.NMessage = Nmessage;

        this.NDate = Ndate;

    }
    public String getNTitle() {

        return NTitle;

    }
    public void setNTitle(String title) {

        this.NTitle = title;

    }
    public String getNMessage() {

        return NMessage;

    }
    public void setNMessage(String message) {

        this.NMessage = message;

    }

    public String getNDate() {

        return NDate;

    }
    public void setNDate(String date) {

        this.NDate = date;

    }

    @Override
    public String toString() {

        return  NTitle + " " + NMessage+ " " + NDate ;

    }
}
