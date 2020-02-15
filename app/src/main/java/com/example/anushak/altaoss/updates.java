package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

public class updates  {

    String ProName = null;
    String Pronotes = null;
    String ProEmpId = null;
    String DateandTime = null;

    public updates(String Pname, String Pnotes, String Eid, String Dateandtime) {

        super();

        this.ProName = Pname;

        this.Pronotes = Pnotes;

        this.ProEmpId = Eid;

        this.DateandTime = Dateandtime;

    }

    public String getProName() {

        return ProName;

    }
    public void setProName(String code) {

        this.ProName = code;

    }
    public String getPronotes() {

        return Pronotes;

    }
    public void setPronotes(String notes) {

        this.Pronotes = notes;

    }
    public String getProEmpId() {

        return ProEmpId;

    }
    public void setProEmpId(String id) {

        this.ProEmpId = id;

    }
    public String getDateandTime() {

        return DateandTime;

    }
    public void setDateandTime(String dateandTime) {

        this.DateandTime = dateandTime;

    }

    @Override
    public String toString() {

        return  ProName + " " + Pronotes + " " + ProEmpId + " " + DateandTime ;

    }

}