package com.example.anushak.altaoss;

/**
 * Created by Anushak on 21-03-2019.
 */

public class Downloads  {

    String Empid = null;
    String Url = null;
    String Type = null;

    public Downloads(String DEmpid, String DUrl, String DType) {

        super();

        this.Empid = DEmpid;

        this.Url = DUrl;

        this.Type = DType;

    }

    public String getEmpid() {

        return Empid;

    }
    public void setEmpid(String empid) {

        this.Empid = empid;

    }
    public String getUrl() {

        return Url;

    }
    public void setUrl(String url) {

        this.Url = url;

    }

    public String getType() {

        return Type;

    }
    public void setType(String type) {

        this.Type = type;

    }

    @Override
    public String toString() {

        return  Empid + " " + Url+ " " + Type;

    }

}