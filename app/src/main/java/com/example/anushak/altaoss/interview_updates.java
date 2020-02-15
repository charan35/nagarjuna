package com.example.anushak.altaoss;

import java.util.ArrayList;

public class interview_updates {

    private ArrayList<String> names =new ArrayList<>();

    String Name = null;
    String Qualification = null;
    String email = null;
    String phone = null;

    String project = null;

    public interview_updates(String Pname, String Pnotes, String Eid, String Dateandtime, String Project) {

        super();

        this.Name = Pname;

        this.Qualification = Pnotes;

        this.email = Eid;

        this.phone = Dateandtime;
        this.project = Project;

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

    public void setProject(String project) {
        this.project = project;
    }

    @Override
    public String toString() {

        return  Name + " " + Qualification + " " + email + " " + phone + " " + project ;

    }

}