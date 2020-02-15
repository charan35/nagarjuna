package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

public class Tickets_Dept_Names  {

    String SubName = null;
    String SubFullForm = null;
    String SubId = null;
    String SubDepartment = null;
    String subemail = null;


    public Tickets_Dept_Names(String Sname, String SFullForm, String SId, String SDepartment, String SEmail) {

        super();

        this.SubName = Sname;

        this.SubFullForm = SFullForm;

        this.SubId = SId;

        this.SubDepartment = SDepartment;

        this.subemail = SEmail;
    }

    public String getSubName() {

        return SubName;

    }
    public void setSubName(String code) {

        this.SubName = code;

    }
    public String getSubFullForm() {

        return SubFullForm;

    }
    public void setSubFullForm(String name) {

        this.SubFullForm = name;

    }

    public String getSubId() {

        return SubId;

    }
    public void setSubId(String id) {

        this.SubId = id;

    }

    public String getSubDepartment() {

        return SubDepartment;

    }
    public void setSubDepartment(String department) {

        this.SubDepartment = department;

    }
    public String getEmail(){
        return subemail;
    }

    public void setSubemail(String subemail) {
        this.subemail = subemail;
    }

    @Override
    public String toString() {

        return  SubName + " " + SubFullForm+ " " + SubId+ " " + SubDepartment+ " " + subemail ;

    }

}