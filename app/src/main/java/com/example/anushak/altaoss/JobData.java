package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

public class JobData  {

    String JTitle = null;
    String JVacancies = null;
    String JExperience = null;
    String JLocation = null;
    String JSalary = null;

    public JobData(String Jtitle, String Jvacancies, String Jexperience, String Jlocation, String Jsalary) {

        super();

        this.JTitle = Jtitle;

        this.JVacancies = Jvacancies;

        this.JExperience = Jexperience;

        this.JLocation = Jlocation;

        this.JSalary = Jsalary;
    }

    public String getJTitle() {

        return JTitle;

    }
    public void setJTitle(String title) {

        this.JTitle = title;

    }
    public String getJVacancies() {

        return JVacancies;

    }
    public void setJVacancies(String vacancies) {

        this.JVacancies = vacancies;

    }

    public String getJLocation() {

        return JLocation;

    }
    public void setJLocation(String location) {

        this.JLocation = location;

    }

    public String getJSalary() {

        return JSalary;

    }
    public void setJSalary(String salary) {

        this.JSalary = salary;

    }

    public String getJExperience() {

        return JExperience;

    }
    public void setJExperience(String experience) {

        this.JExperience = experience;

    }

    @Override
    public String toString() {

        return  JTitle + " " + JVacancies+ " " + JExperience+ " " + JLocation+ " " + JSalary ;

    }

}