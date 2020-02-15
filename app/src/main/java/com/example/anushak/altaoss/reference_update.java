package com.example.anushak.altaoss;

import java.util.ArrayList;

public class reference_update {


    private ArrayList<String> names =new ArrayList<>();

    String Name = null;
    String Qualification = null;
    String email = null;
    String phone = null;

    String project = null;
    String referencename = null;

    String referenceid = null;



    public reference_update(String Pname, String Pnotes, String Eid, String Dateandtime, String Project, String Referencename, String Referenceid) {

        super();

        this.Name = Pname;

        this.Qualification = Pnotes;

        this.email = Eid;

        this.phone = Dateandtime;
        this.project = Project;

        this.referencename = Referencename;
        this.referenceid = Referenceid;

    }

    public String getProName() {

        return Name;

    }
    public void setProName(String code) {

        this.Name = code;

    }
    public String getPronotes() {

        return Qualification;

    }

    public void setPronotes(String notes) {

        this.Qualification = notes;

    }


    public String getProEmpId() {

        return email;

    }
    public void setProEmpId(String id) {

        this.email = id;

    }

    public String getDateandTime() {

        return phone;
    }
    public void setDateandTime(String dateandTime) {

        this.phone = dateandTime;

    }
    public String getProject(){
        return project;

    }
    public void setReferencename(String ReferenceName){
        this.referencename = ReferenceName;
    }
    public String getReferencename(){
        return referencename;
    }
    public void setReferenceid(String ReferenceId){
        this.referenceid = ReferenceId;
    }
    public String getReferenceid(){

        return referenceid;
    }


    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {

        return  Name + " " + Qualification + " " + email + " " + phone + " " + project + " "+ referencename + " " + referenceid;

    }


}
