package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

public class Tickets  {

    String TicketTitle = null;
    String TicketEmpid = null;
    String TicketDate = null;
    String TicketStatus= null;
    String TicketPriority = null;

    public Tickets(String Ttitle, String Tempid, String Tdate,String Tstatus, String Tpriority) {

        super();

        this.TicketTitle = Ttitle;

        this.TicketEmpid = Tempid;

        this.TicketDate = Tdate;

        this.TicketStatus = Tstatus;

        this.TicketPriority = Tpriority;

    }

    public String getTicketTitle() {

        return TicketTitle;

    }
    public void setTicketTitle(String title) {

        this.TicketTitle = title;

    }
    public String getTicketEmpid() {

        return TicketEmpid;

    }
    public void setTicketEmpid(String empid) {

        this.TicketEmpid = empid;

    }
    public String getTicketDate() {

        return TicketDate;

    }
    public void setTicketDate(String date) {

        this.TicketDate = date;

    }
    public String getTicketStatus() {

        return TicketStatus;

    }
    public void setTicketStatus(String status) {

        this.TicketStatus = status;

    }
    public String getTicketPriority() {

        return TicketPriority;

    }
    public void setTicketPriority(String priority) {

        this.TicketPriority = priority;

    }


    @Override
    public String toString() {

        return  TicketTitle + " " + TicketEmpid + " " + TicketDate + " " + TicketStatus + " " + TicketPriority;

    }

}