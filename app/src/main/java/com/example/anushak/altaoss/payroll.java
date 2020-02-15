package com.example.anushak.altaoss;

/**
 * Created by Anushak on 12-02-2019.
 */

public class payroll {

    String Name = null;
    String Empid = null;
    String Month = null;
    String Year = null;
    String Date = null;

    public payroll(String Pname, String Pempid, String Pmonth, String Pyear, String Pdate) {

        super();

        this.Name = Pname;

        this.Empid = Pempid;

        this.Month = Pmonth;

        this.Year = Pyear;

        this.Date = Pdate;
    }

    public String getName() {

        return Name;

    }
    public void setName(String name) {

        this.Name = name;

    }
    public String getEmpid() {

        return Empid;

    }
    public void setEmpid(String empid) {

        this.Empid = empid;

    }

    public String getMonth() {

        return Month;

    }
    public void setMonth(String month) {

        this.Month = month;

    }

    public String getYear() {

        return Year;

    }
    public void setYear(String year) {

        this.Year = year;

    }

    public String getDate() {

        return Date;

    }
    public void setDate(String date) {

        this.Date = date;

    }

    @Override
    public String toString() {

        return  Name + " " + Empid+ " " + Month+ " " + Year+ " " + Date ;

    }

}
