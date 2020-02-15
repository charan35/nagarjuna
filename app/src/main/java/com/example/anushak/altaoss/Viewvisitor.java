package com.example.anushak.altaoss;

public class Viewvisitor {
    String VFName = null;
    String VLName = null;
    String VReasonforvisit = null;
    String VCName = null;
    String VCPName = null;
    String VDVisit = null;

    public Viewvisitor(String Vfname, String Vlname, String Vreasonforvisit, String Vcname, String Vcpname, String Vdvisit) {

        super();

        this.VFName = Vfname;

        this.VLName = Vlname;

        this.VReasonforvisit = Vreasonforvisit;

        this.VCName = Vcname;

        this.VCPName = Vcpname;

        this.VDVisit = Vdvisit;

    }
    public String getVFName() {

        return VFName;

    }
    public void setVFName(String fname) {

        this.VFName = fname;

    }
    public String getVLName() {

        return VLName;

    }
    public void setVLName(String lname) {

        this.VLName = lname;

    }

    public String getVReasonforvisit() {

        return VReasonforvisit;

    }
    public void setVReasonforvisit(String reasonforvisit) {

        this.VReasonforvisit = reasonforvisit;

    }

    public String getVCName() {

        return VCName;

    }
    public void setVCName(String cname) {

        this.VCName = cname;

    }

    public String getVCPName() {

        return VCPName;

    }
    public void setVCPName(String cpname) {

        this.VCPName = cpname;

    }

    public String getVDVisit() {

        return VDVisit;

    }
    public void setVDVisit(String datevisit) {

        this.VDVisit = datevisit;

    }

    @Override
    public String toString() {

        return  VFName + " " + VLName+ " " + VReasonforvisit+ " " + VCName+ " " +VCPName+ " " +VDVisit ;

    }
}
