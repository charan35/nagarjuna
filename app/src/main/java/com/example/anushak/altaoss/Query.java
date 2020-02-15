package com.example.anushak.altaoss;

/**
 * Created by Anushak on 21-03-2019.
 */

public class Query  {

    String Name = null;
    String QueryName = null;
    String Email = null;
    String Update = null;
    String Status = null;

    public Query(String Qname, String QFullForm, String Qemail, String Qupdate, String Qstatus) {

        super();

        this.Name = Qname;

        this.QueryName = QFullForm;

        this.Email = Qemail;

        this.Update = Qupdate;

        this.Status = Qstatus;
    }

    public String getName() {

        return Name;

    }
    public void setName(String name) {

        this.Name = name;

    }
    public String getQueryName() {

        return QueryName;

    }
    public void setQueryName(String qname) {

        this.QueryName = qname;

    }

    public String getEmail() {

        return Email;

    }
    public void setEmail(String email) {

        this.Email = email;

    }

    public String getUpdate() {

        return Update;

    }
    public void setUpdate(String update) {

        this.Update = update;

    }

    public String getStatus() {

        return Status;

    }
    public void setStatus(String status) {

        this.Status = status;

    }


    @Override
    public String toString() {

        return  Name + " " + QueryName+ " " + Email+ " " + Update+ " " + Status ;

    }

}