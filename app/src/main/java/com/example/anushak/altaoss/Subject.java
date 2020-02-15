package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

public class Subject  {

    String SubName = null;
    String SubFullForm = null;
    String SubId = null;
    String SubDepartment = null;

    public Subject(String Sname, String SFullForm, String SId, String SDepartment) {

        super();

        this.SubName = Sname;

        this.SubFullForm = SFullForm;

        this.SubId = SId;

        this.SubDepartment = SDepartment;
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

    @Override
    public String toString() {

        return  SubName + " " + SubFullForm+ " " + SubId+ " " + SubDepartment ;

    }

}