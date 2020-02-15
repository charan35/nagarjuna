package com.example.anushak.altaoss;

/**
 * Created by Anushak on 05-04-2019.
 */

public class Reimbursement {
    String RType = null;
    String RId = null;
    String RDate = null;
    String RStatus = null;

    public Reimbursement(String Rtype, String Rid, String Rdate, String Rstatus) {

        super();

        this.RType = Rtype;

        this.RId = Rid;

        this.RDate = Rdate;

        this.RStatus = Rstatus;

    }

    public String getRType() {

        return RType;

    }
    public void setRType(String type) {

        this.RType = type;

    }
    public String getRId() {

        return RId;

    }
    public void setRId(String id) {

        this.RId = id;

    }
    public String getRDate() {

        return RDate;

    }
    public void setRDate(String date) {

        this.RDate = date;

    }

    public String getRStatus() {

        return RStatus;

    }
    public void setRStatus(String status) {

        this.RStatus = status;

    }

    @Override
    public String toString() {

        return  RType + " " + RId + " " + RDate+ " " + RStatus ;

    }

}
