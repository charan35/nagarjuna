package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permissions extends AppCompatActivity {

    TextView firstName;
    String empid;

    Spinner profile,project,message,mypay,attendance,updates,payslips,reimbursement1,myproject,careers;
    Button button;

    Spinner profileh,projecth,messageh,mypayh,payslipsh,reimbursement1h,attendanceh,updatesh,myprojecth,hnewempform,hempdirectory,hprojectdetails,hempattendance,hreimbursement,hdocumentsupload,hpayslips1,hrewards,hcareers,hinterviews;
    Button buttonh;

    Spinner profilea,projecta,messagea,mypaya,payslipsa,reimbursement1a,attendancea,updatesa,aempdirectory,aprojectdetails,aempattendance,apayroll,adocumentsfetch,apermissions,acccamera,apayslips1,adocumentsupload,areimbursement,anewempform,amyprojects,arewards,acareers,ainterviews;
    Button buttona;

    TextView project1,profile1,message1,mypay1,payslips1,reimbursement11,updates1,attendance1,myproject1,careers1;
    String Profile1,Project1,Message1,Mypay1,Payslips1,Reimbursement11,Updates1,Attendance1,Myproject1,Careers1;

    TextView projecth1,profileh1,messageh1,mypayh1,payslipsh1,reimbursement1h1,updatesh1,attendanceh1,myprojecth1,hnewempform1,hempdirectory1,hprojectdetails1,hempattendance1,hreimbursement1,hdocumentsupload1,hpayslips11,hrewards1,hcareers1,hinterviews1;
    String Profileh1,Projecth1,Messageh1,Mypayh1,Payslipsh1,Reimbursement1h1,Updatesh1,Attendanceh1,Myprojecth1,Hnewempform1,Hempdirectory1,Hprojectdetails1,Hempattendance1,Hreimbursement1,Hdocumentsupload1,Hpayslips11,Hrewards1,Hcareers1,Hinterviews1;

    TextView profilea1,projecta1,messagea1,mypaya1,payslipsa1,reimbursement1a1,attendancea1,updatesa1,aempdirectory1,aprojectdetails1,aempattendance1,apayroll1,adocumentsfetch1,apermissions1,acccamera1,apayslips11,adocumentsupload1,areimbursement1,anewempform1,amyprojects1,arewards1,acareers1,ainterviews1;
    String Profilea1,Projecta1,Messagea1,Mypaya1,Payslipsa1,Reimbursement1a1,Attendancea1,Updatesa1,Aempdirectory1,Aprojectdetails1,Aempattendance1,Apayroll1,Adocumentsfetch1,Apermissions1,Acccamera1,Apayslips11,Adocumentsupload1,Areimbursement1,Anewempform1,Amyprojects1,Arewards1,Acareers1,Ainterviews1;

    Context ctx=this;

    LinearLayout lemp,lhr,ladmin;

    TextView admin,emplist,text;
    ImageView iv25;
    String OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,Text;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lemp = (LinearLayout) findViewById(R.id.lemp);
        lhr = (LinearLayout) findViewById(R.id.lhr);
        ladmin = (LinearLayout) findViewById(R.id.ladmin);
        admin =(TextView)findViewById(R.id.admin);
        emplist =(TextView)findViewById(R.id.emplist);
        iv25 = (ImageView)findViewById(R.id.iv25);
        text = (TextView)findViewById(R.id.text);

        profile = (Spinner) findViewById(R.id.profile);
        project = (Spinner) findViewById(R.id.project);
        message = (Spinner) findViewById(R.id.message);
        mypay = (Spinner) findViewById(R.id.mypay);
        attendance = (Spinner) findViewById(R.id.attendance);
        updates = (Spinner) findViewById(R.id.updates);
        payslips = (Spinner) findViewById(R.id.payslips);
        reimbursement1 = (Spinner) findViewById(R.id.reimbursement1);
        myproject = (Spinner) findViewById(R.id.myproject);
        careers = (Spinner) findViewById(R.id.careers);
        button = (Button) findViewById(R.id.button);

        profileh = (Spinner) findViewById(R.id.profileh);
        projecth = (Spinner) findViewById(R.id.projecth);
        messageh = (Spinner) findViewById(R.id.messageh);
        mypayh = (Spinner) findViewById(R.id.mypayh);
        payslipsh = (Spinner) findViewById(R.id.payslipsh);
        reimbursement1h = (Spinner) findViewById(R.id.reimbursement1h);
        attendanceh = (Spinner) findViewById(R.id.attendanceh);
        updatesh = (Spinner) findViewById(R.id.updatesh);
        myprojecth = (Spinner) findViewById(R.id.myprojecth);
        hnewempform = (Spinner) findViewById(R.id.hnewempform);
        hempdirectory = (Spinner) findViewById(R.id.hempdirectory);
        hprojectdetails = (Spinner) findViewById(R.id.hprojectdetails);
        hempattendance = (Spinner) findViewById(R.id.hempattendance);
        hreimbursement = (Spinner) findViewById(R.id.hreimbursement);
        hdocumentsupload = (Spinner) findViewById(R.id.hdocumentsupload);
        hpayslips1 = (Spinner) findViewById(R.id.hpayslips);
        hrewards = (Spinner) findViewById(R.id.hrewards);
        hcareers = (Spinner) findViewById(R.id.hcareers);
        hinterviews = (Spinner) findViewById(R.id.hinterviews);
        buttonh = (Button) findViewById(R.id.buttonh);

        profilea = (Spinner) findViewById(R.id.profilea);
        projecta = (Spinner) findViewById(R.id.projecta);
        messagea = (Spinner) findViewById(R.id.messagea);
        mypaya = (Spinner) findViewById(R.id.mypaya);
        payslipsa = (Spinner) findViewById(R.id.payslipsa);
        reimbursement1a = (Spinner) findViewById(R.id.reimbursement1a);
        attendancea = (Spinner) findViewById(R.id.attendancea);
        updatesa = (Spinner) findViewById(R.id.updatesa);
        aempdirectory = (Spinner) findViewById(R.id.aempdirectory);
        aprojectdetails = (Spinner) findViewById(R.id.aprojectdetails);
        aempattendance = (Spinner) findViewById(R.id.aempattendance);
        apayroll = (Spinner) findViewById(R.id.apayroll);
        adocumentsfetch = (Spinner) findViewById(R.id.adocumentsfetch);
        apermissions = (Spinner) findViewById(R.id.apermissions);
        acccamera = (Spinner) findViewById(R.id.acccamera);
        apayslips1 = (Spinner) findViewById(R.id.apayslips);
        anewempform = (Spinner) findViewById(R.id.anewempform);
        areimbursement = (Spinner) findViewById(R.id.areimbursement);
        adocumentsupload = (Spinner) findViewById(R.id.adocumentsupload);
        amyprojects = (Spinner) findViewById(R.id.amyprojects);
        arewards = (Spinner) findViewById(R.id.arewards);
        acareers = (Spinner) findViewById(R.id.acareers);
        ainterviews = (Spinner) findViewById(R.id.ainterviews);
        buttona = (Button) findViewById(R.id.buttona);

        firstName = (TextView) findViewById(R.id.tvFirstName);

        profile1 = ((TextView) findViewById(R.id.profile1));
        project1 = ((TextView) findViewById(R.id.project1));
        message1 = ((TextView) findViewById(R.id.message1));
        mypay1 = ((TextView) findViewById(R.id.mypay1));
        payslips1 = ((TextView) findViewById(R.id.payslips1));
        reimbursement11 = ((TextView) findViewById(R.id.reimbursement11));
        attendance1 = ((TextView) findViewById(R.id.attendance1));
        updates1 = ((TextView) findViewById(R.id.updates1));
        myproject1 = ((TextView) findViewById(R.id.myproject1));
        careers1 = ((TextView) findViewById(R.id.careers1));

        profileh1 = (TextView) findViewById(R.id.profileh1);
        projecth1 = (TextView) findViewById(R.id.projecth1);
        messageh1 = (TextView) findViewById(R.id.messageh1);
        mypayh1 = (TextView) findViewById(R.id.mypayh1);
        payslipsh1 = (TextView) findViewById(R.id.payslipsh1);
        reimbursement1h1 = (TextView) findViewById(R.id.reimbursement1h1);
        attendanceh1 = (TextView) findViewById(R.id.attendanceh1);
        updatesh1 = (TextView) findViewById(R.id.updatesh1);
        myprojecth1 = (TextView) findViewById(R.id.myprojecth1);
        hnewempform1 = (TextView) findViewById(R.id.hnewempform1);
        hempdirectory1 = (TextView) findViewById(R.id.hempdirectory1);
        hprojectdetails1 = (TextView) findViewById(R.id.hprojectdetails1);
        hempattendance1 = (TextView) findViewById(R.id.hempattendance1);
        hreimbursement1 = (TextView) findViewById(R.id.hreimbursement1);
        hdocumentsupload1 = (TextView) findViewById(R.id.hdocumentsupload1);
        hpayslips11 = (TextView) findViewById(R.id.hpayslips1);
        hrewards1 = (TextView) findViewById(R.id.hrewards1);
        hcareers1 = (TextView) findViewById(R.id.hcareers1);
        hinterviews1 = (TextView) findViewById(R.id.hinterviews1);

        profilea1 = (TextView) findViewById(R.id.profilea1);
        projecta1 = (TextView) findViewById(R.id.projecta1);
        messagea1 = (TextView) findViewById(R.id.messagea1);
        mypaya1 = (TextView) findViewById(R.id.mypaya1);
        payslipsa1 = (TextView) findViewById(R.id.payslipsa1);
        reimbursement1a1 = (TextView) findViewById(R.id.reimbursement1a1);
        attendancea1 = (TextView) findViewById(R.id.attendancea1);
        updatesa1 = (TextView) findViewById(R.id.updatesa1);
        aempdirectory1 = (TextView) findViewById(R.id.aempdirectory1);
        aprojectdetails1 = (TextView) findViewById(R.id.aprojectdetails1);
        aempattendance1 = (TextView) findViewById(R.id.aempattendance1);
        apayroll1 = (TextView) findViewById(R.id.apayroll1);
        adocumentsfetch1 = (TextView) findViewById(R.id.adocumentsfetch1);
        apermissions1 = (TextView) findViewById(R.id.apermissions1);
        acccamera1 = (TextView) findViewById(R.id.acccamera1);
        apayslips11 = (TextView) findViewById(R.id.apayslips1);
        anewempform1 = (TextView) findViewById(R.id.anewempform1);
        areimbursement1 = (TextView) findViewById(R.id.areimbursement1);
        adocumentsupload1 = (TextView) findViewById(R.id.adocumentsupload1);
        amyprojects1 = (TextView) findViewById(R.id.amyprojects1);
        arewards1 = (TextView) findViewById(R.id.arewards1);
        acareers1 = (TextView) findViewById(R.id.acareers1);
        ainterviews1 = (TextView) findViewById(R.id.ainterviews1);


        Profile1 = getIntent().getStringExtra("profile");
        Project1 = getIntent().getStringExtra("project");
        Message1 = getIntent().getStringExtra("message");
        Mypay1 = getIntent().getStringExtra("mypay");
        Payslips1 = getIntent().getStringExtra("payslips");
        Reimbursement11 = getIntent().getStringExtra("reimbursement1");
        Attendance1 = getIntent().getStringExtra("attendance");
        Updates1 = getIntent().getStringExtra("updates");
        Myproject1 = getIntent().getStringExtra("myproject");
        Careers1 = getIntent().getStringExtra("careers");

        Profileh1 = getIntent().getStringExtra("profile");
        Projecth1 = getIntent().getStringExtra("project");
        Messageh1 = getIntent().getStringExtra("message");
        Mypayh1 = getIntent().getStringExtra("mypay");
        Payslipsh1 = getIntent().getStringExtra("payslips");
        Reimbursement1h1 = getIntent().getStringExtra("reimbursement1");
        Attendanceh1 = getIntent().getStringExtra("attendance");
        Updatesh1 = getIntent().getStringExtra("updates");
        Myprojecth1 = getIntent().getStringExtra("myproject");
        Hnewempform1 = getIntent().getStringExtra("newempform");
        Hempdirectory1 = getIntent().getStringExtra("empdirectory");
        Hprojectdetails1 = getIntent().getStringExtra("projectdetails");
        Hempattendance1 = getIntent().getStringExtra("empattendance");
        Hreimbursement1 = getIntent().getStringExtra("reimbursement");
        Hdocumentsupload1 = getIntent().getStringExtra("documentsupload");
        Hpayslips11 = getIntent().getStringExtra("payslips1");
        Hcareers1 = getIntent().getStringExtra("careers");
        Hrewards1 = getIntent().getStringExtra("rewards");
        Hinterviews1 = getIntent().getStringExtra("interviews");

        Profilea1 = getIntent().getStringExtra("profile");
        Projecta1 = getIntent().getStringExtra("project");
        Messagea1 = getIntent().getStringExtra("message");
        Mypaya1 = getIntent().getStringExtra("mypay");
        Payslipsa1 = getIntent().getStringExtra("payslips");
        Reimbursement1a1 = getIntent().getStringExtra("reimbursement1");
        Attendancea1 = getIntent().getStringExtra("attendance");
        Updatesa1 = getIntent().getStringExtra("updates");
        Amyprojects1 = getIntent().getStringExtra("myproject");
        Anewempform1 = getIntent().getStringExtra("newempform");
        Aempdirectory1 = getIntent().getStringExtra("empdirectory");
        Aprojectdetails1 = getIntent().getStringExtra("projectdetails");
        Aempattendance1 = getIntent().getStringExtra("empattendance");
        Areimbursement1 = getIntent().getStringExtra("reimbursement");
        Adocumentsupload1 = getIntent().getStringExtra("documentsupload");
        Apayslips11 = getIntent().getStringExtra("payslips1");
        Acareers1 = getIntent().getStringExtra("careers");
        Arewards1 = getIntent().getStringExtra("rewards");
        Ainterviews1 = getIntent().getStringExtra("interviews");
        Apayroll1 = getIntent().getStringExtra("payroll");
        Apermissions1 = getIntent().getStringExtra("permission");
        Acccamera1 = getIntent().getStringExtra("cccamera");
        Adocumentsfetch1 = getIntent().getStringExtra("documentsfetch");


        profile1.setText(Profile1);
        project1.setText(Project1);
        message1.setText(Message1);
        mypay1.setText(Mypay1);
        payslips1.setText(Payslips1);
        reimbursement11.setText(Reimbursement11);
        attendance1.setText(Attendance1);
        updates1.setText(Updates1);
        myproject1.setText(Myproject1);
        careers1.setText(Careers1);

        profileh1.setText(Profileh1);
        projecth1.setText(Projecth1);
        messageh1.setText(Messageh1);
        mypayh1.setText(Mypayh1);
        payslipsh1.setText(Payslipsh1);
        reimbursement1h1.setText(Reimbursement1h1);
        attendanceh1.setText(Attendanceh1);
        updatesh1.setText(Updatesh1);
        myprojecth1.setText(Myprojecth1);
        hnewempform1.setText(Hnewempform1);
        hempdirectory1.setText(Hempdirectory1);
        hprojectdetails1.setText(Hprojectdetails1);
        hempattendance1.setText(Hempattendance1);
        hreimbursement1.setText(Hreimbursement1);
        hdocumentsupload1.setText(Hdocumentsupload1);
        hpayslips11.setText(Hpayslips11);
        hcareers1.setText(Hcareers1);
        hrewards1.setText(Hrewards1);
        hinterviews1.setText(Hinterviews1);

        profilea1.setText(Profilea1);
        projecta1.setText(Projecta1);
        messagea1.setText(Messagea1);
        mypaya1.setText(Mypaya1);
        payslipsa1.setText(Payslipsa1);
        reimbursement1a1.setText(Reimbursement1a1);
        attendancea1.setText(Attendancea1);
        updatesa1.setText(Updatesa1);
        amyprojects1.setText(Amyprojects1);
        anewempform1.setText(Anewempform1);
        aempdirectory1.setText(Aempdirectory1);
        aprojectdetails1.setText(Aprojectdetails1);
        aempattendance1.setText(Aempattendance1);
        areimbursement1.setText(Areimbursement1);
        adocumentsupload1.setText(Adocumentsupload1);
        apayslips11.setText(Apayslips11);
        acareers1.setText(Acareers1);
        arewards1.setText(Arewards1);
        ainterviews1.setText(Ainterviews1);
        adocumentsfetch1.setText(Adocumentsfetch1);
        apayroll1.setText(Apayroll1);
        acccamera1.setText(Acccamera1);
        apermissions1.setText(Apermissions1);

////////////   Spinner

        profile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                profile.setVisibility(View.GONE);
                profile1.setVisibility(View.VISIBLE);
                profile1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setVisibility(View.VISIBLE);
                profile1.setVisibility(View.GONE);
            }
        });

        project.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                project.setVisibility(View.GONE);
                project1.setVisibility(View.VISIBLE);
                project1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        project1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                project.setVisibility(View.VISIBLE);
                project1.setVisibility(View.GONE);
            }
        });

        message.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                message.setVisibility(View.GONE);
                message1.setVisibility(View.VISIBLE);
                message1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        message1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setVisibility(View.VISIBLE);
                message1.setVisibility(View.GONE);
            }
        });

        mypay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                mypay.setVisibility(View.GONE);
                mypay1.setVisibility(View.VISIBLE);
                mypay1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        mypay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypay.setVisibility(View.VISIBLE);
                mypay1.setVisibility(View.GONE);
            }
        });

        payslips.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                payslips.setVisibility(View.GONE);
                payslips1.setVisibility(View.VISIBLE);
                payslips1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        payslips1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payslips.setVisibility(View.VISIBLE);
                payslips1.setVisibility(View.GONE);
            }
        });

        reimbursement1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                reimbursement1.setVisibility(View.GONE);
                reimbursement11.setVisibility(View.VISIBLE);
                reimbursement11.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        reimbursement11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reimbursement1.setVisibility(View.VISIBLE);
                reimbursement11.setVisibility(View.GONE);
            }
        });

        attendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                attendance.setVisibility(View.GONE);
                attendance1.setVisibility(View.VISIBLE);
                attendance1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        attendance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendance.setVisibility(View.VISIBLE);
                attendance1.setVisibility(View.GONE);
            }
        });

        updates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                updates.setVisibility(View.GONE);
                updates1.setVisibility(View.VISIBLE);
                updates1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        updates1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updates.setVisibility(View.VISIBLE);
                updates1.setVisibility(View.GONE);
            }
        });

        myproject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                myproject.setVisibility(View.GONE);
                myproject1.setVisibility(View.VISIBLE);
                myproject1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        myproject1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myproject.setVisibility(View.VISIBLE);
                myproject1.setVisibility(View.GONE);
            }
        });

        careers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                careers.setVisibility(View.GONE);
                careers1.setVisibility(View.VISIBLE);
                careers1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        careers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careers.setVisibility(View.VISIBLE);
                careers1.setVisibility(View.GONE);
            }
        });



        profileh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                profileh.setVisibility(View.GONE);
                profileh1.setVisibility(View.VISIBLE);
                profileh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        profileh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileh.setVisibility(View.VISIBLE);
                profileh1.setVisibility(View.GONE);
            }
        });

        projecth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                projecth.setVisibility(View.GONE);
                projecth1.setVisibility(View.VISIBLE);
                projecth1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        projecth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projecth.setVisibility(View.VISIBLE);
                projecth1.setVisibility(View.GONE);
            }
        });

        messageh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                messageh.setVisibility(View.GONE);
                messageh1.setVisibility(View.VISIBLE);
                messageh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        messageh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageh.setVisibility(View.VISIBLE);
                messageh1.setVisibility(View.GONE);
            }
        });

        mypayh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                mypayh.setVisibility(View.GONE);
                mypayh1.setVisibility(View.VISIBLE);
                mypayh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        mypayh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypayh.setVisibility(View.VISIBLE);
                mypayh1.setVisibility(View.GONE);
            }
        });

        payslipsh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                payslipsh.setVisibility(View.GONE);
                payslipsh1.setVisibility(View.VISIBLE);
                payslipsh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        payslipsh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payslipsh.setVisibility(View.VISIBLE);
                payslipsh1.setVisibility(View.GONE);
            }
        });

        reimbursement1h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                reimbursement1h.setVisibility(View.GONE);
                reimbursement1h1.setVisibility(View.VISIBLE);
                reimbursement1h1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        reimbursement1h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reimbursement1h.setVisibility(View.VISIBLE);
                reimbursement1h1.setVisibility(View.GONE);
            }
        });

        attendanceh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                attendanceh.setVisibility(View.GONE);
                attendanceh1.setVisibility(View.VISIBLE);
                attendanceh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        attendanceh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendanceh.setVisibility(View.VISIBLE);
                attendanceh1.setVisibility(View.GONE);
            }
        });

        updatesh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                updatesh.setVisibility(View.GONE);
                updatesh1.setVisibility(View.VISIBLE);
                updatesh1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        updatesh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatesh.setVisibility(View.VISIBLE);
                updatesh1.setVisibility(View.GONE);
            }
        });

        myprojecth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                myprojecth.setVisibility(View.GONE);
                myprojecth1.setVisibility(View.VISIBLE);
                myprojecth1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        myprojecth1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myprojecth.setVisibility(View.VISIBLE);
                myprojecth1.setVisibility(View.GONE);
            }
        });

        hnewempform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hnewempform.setVisibility(View.GONE);
                hnewempform1.setVisibility(View.VISIBLE);
                hnewempform1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hnewempform1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hnewempform.setVisibility(View.VISIBLE);
                hnewempform1.setVisibility(View.GONE);
            }
        });

        hempdirectory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hempdirectory.setVisibility(View.GONE);
                hempdirectory1.setVisibility(View.VISIBLE);
                hempdirectory1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hempdirectory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hempdirectory.setVisibility(View.VISIBLE);
                hempdirectory1.setVisibility(View.GONE);
            }
        });

        hprojectdetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hprojectdetails.setVisibility(View.GONE);
                hprojectdetails1.setVisibility(View.VISIBLE);
                hprojectdetails1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hprojectdetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hprojectdetails.setVisibility(View.VISIBLE);
                hprojectdetails1.setVisibility(View.GONE);
            }
        });

        hempattendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hempattendance.setVisibility(View.GONE);
                hempattendance1.setVisibility(View.VISIBLE);
                hempattendance1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hempattendance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hempattendance.setVisibility(View.VISIBLE);
                hempattendance1.setVisibility(View.GONE);
            }
        });

        hreimbursement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hreimbursement.setVisibility(View.GONE);
                hreimbursement1.setVisibility(View.VISIBLE);
                hreimbursement1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hreimbursement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hreimbursement.setVisibility(View.VISIBLE);
                hreimbursement1.setVisibility(View.GONE);
            }
        });

        hdocumentsupload.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hdocumentsupload.setVisibility(View.GONE);
                hdocumentsupload1.setVisibility(View.VISIBLE);
                hdocumentsupload1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hdocumentsupload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hdocumentsupload.setVisibility(View.VISIBLE);
                hdocumentsupload1.setVisibility(View.GONE);
            }
        });

        hpayslips1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hpayslips1.setVisibility(View.GONE);
                hpayslips11.setVisibility(View.VISIBLE);
                hpayslips11.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hpayslips11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpayslips1.setVisibility(View.VISIBLE);
                hpayslips11.setVisibility(View.GONE);
            }
        });

        hrewards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hrewards.setVisibility(View.GONE);
                hrewards1.setVisibility(View.VISIBLE);
                hrewards1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hrewards1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrewards.setVisibility(View.VISIBLE);
                hrewards1.setVisibility(View.GONE);
            }
        });

        hcareers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hcareers.setVisibility(View.GONE);
                hcareers1.setVisibility(View.VISIBLE);
                hcareers1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hcareers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hcareers.setVisibility(View.VISIBLE);
                hcareers1.setVisibility(View.GONE);
            }
        });

        hinterviews.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                hinterviews.setVisibility(View.GONE);
                hinterviews1.setVisibility(View.VISIBLE);
                hinterviews1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        hinterviews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hinterviews.setVisibility(View.VISIBLE);
                hinterviews1.setVisibility(View.GONE);
            }
        });



        profilea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                profilea.setVisibility(View.GONE);
                profilea1.setVisibility(View.VISIBLE);
                profilea1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        profilea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilea.setVisibility(View.VISIBLE);
                profilea1.setVisibility(View.GONE);
            }
        });

        projecta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                projecta.setVisibility(View.GONE);
                projecta1.setVisibility(View.VISIBLE);
                projecta1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        projecta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projecta.setVisibility(View.VISIBLE);
                projecta1.setVisibility(View.GONE);
            }
        });

        messagea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                messagea.setVisibility(View.GONE);
                messagea1.setVisibility(View.VISIBLE);
                messagea1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        messagea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messagea.setVisibility(View.VISIBLE);
                messagea1.setVisibility(View.GONE);
            }
        });

        mypaya.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                mypaya.setVisibility(View.GONE);
                mypaya1.setVisibility(View.VISIBLE);
                mypaya1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        mypaya1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypaya.setVisibility(View.VISIBLE);
                mypaya1.setVisibility(View.GONE);
            }
        });

        payslipsa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                payslipsa.setVisibility(View.GONE);
                payslipsa1.setVisibility(View.VISIBLE);
                payslipsa1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        payslipsa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payslipsa.setVisibility(View.VISIBLE);
                payslipsa1.setVisibility(View.GONE);
            }
        });

        reimbursement1a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                reimbursement1a.setVisibility(View.GONE);
                reimbursement1a1.setVisibility(View.VISIBLE);
                reimbursement1a1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        reimbursement1a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reimbursement1a.setVisibility(View.VISIBLE);
                reimbursement1a1.setVisibility(View.GONE);
            }
        });

        attendancea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                attendancea.setVisibility(View.GONE);
                attendancea1.setVisibility(View.VISIBLE);
                attendancea1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        attendancea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attendancea.setVisibility(View.VISIBLE);
                attendancea1.setVisibility(View.GONE);
            }
        });

        updatesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                updatesa.setVisibility(View.GONE);
                updatesa1.setVisibility(View.VISIBLE);
                updatesa1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        updatesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatesa.setVisibility(View.VISIBLE);
                updatesa1.setVisibility(View.GONE);
            }
        });

        aempdirectory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                aempdirectory.setVisibility(View.GONE);
                aempdirectory1.setVisibility(View.VISIBLE);
                aempdirectory1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        aempdirectory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aempdirectory.setVisibility(View.VISIBLE);
                aempdirectory1.setVisibility(View.GONE);
            }
        });

        aprojectdetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                aprojectdetails.setVisibility(View.GONE);
                aprojectdetails1.setVisibility(View.VISIBLE);
                aprojectdetails1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        aprojectdetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aprojectdetails.setVisibility(View.VISIBLE);
                aprojectdetails1.setVisibility(View.GONE);
            }
        });

        aempattendance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                aempattendance.setVisibility(View.GONE);
                aempattendance1.setVisibility(View.VISIBLE);
                aempattendance1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        aempattendance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aempattendance.setVisibility(View.VISIBLE);
                aempattendance1.setVisibility(View.GONE);
            }
        });

        apayroll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                apayroll.setVisibility(View.GONE);
                apayroll1.setVisibility(View.VISIBLE);
                apayroll1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        apayroll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apayroll.setVisibility(View.VISIBLE);
                apayroll1.setVisibility(View.GONE);
            }
        });

        adocumentsfetch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                adocumentsfetch.setVisibility(View.GONE);
                adocumentsfetch1.setVisibility(View.VISIBLE);
                adocumentsfetch1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        adocumentsfetch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adocumentsfetch.setVisibility(View.VISIBLE);
                adocumentsfetch1.setVisibility(View.GONE);
            }
        });

        apermissions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                apermissions.setVisibility(View.GONE);
                apermissions1.setVisibility(View.VISIBLE);
                apermissions1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        apermissions1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apermissions.setVisibility(View.VISIBLE);
                apermissions1.setVisibility(View.GONE);
            }
        });

        acccamera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                acccamera.setVisibility(View.GONE);
                acccamera1.setVisibility(View.VISIBLE);
                acccamera1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        acccamera1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acccamera.setVisibility(View.VISIBLE);
                acccamera1.setVisibility(View.GONE);
            }
        });

        apayslips1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                apayslips1.setVisibility(View.GONE);
                apayslips11.setVisibility(View.VISIBLE);
                apayslips11.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        apayslips11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apayslips1.setVisibility(View.VISIBLE);
                apayslips11.setVisibility(View.GONE);
            }
        });

        anewempform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                anewempform.setVisibility(View.GONE);
                anewempform1.setVisibility(View.VISIBLE);
                anewempform1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        anewempform1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anewempform.setVisibility(View.VISIBLE);
                anewempform1.setVisibility(View.GONE);
            }
        });

        areimbursement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                areimbursement.setVisibility(View.GONE);
                areimbursement1.setVisibility(View.VISIBLE);
                areimbursement1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        areimbursement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areimbursement.setVisibility(View.VISIBLE);
                areimbursement1.setVisibility(View.GONE);
            }
        });

        adocumentsupload.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                adocumentsupload.setVisibility(View.GONE);
                adocumentsupload1.setVisibility(View.VISIBLE);
                adocumentsupload1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        adocumentsupload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adocumentsupload.setVisibility(View.VISIBLE);
                adocumentsupload1.setVisibility(View.GONE);
            }
        });

        amyprojects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                amyprojects.setVisibility(View.GONE);
                amyprojects1.setVisibility(View.VISIBLE);
                amyprojects1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        amyprojects1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amyprojects.setVisibility(View.VISIBLE);
                amyprojects1.setVisibility(View.GONE);
            }
        });

        arewards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                arewards.setVisibility(View.GONE);
                arewards1.setVisibility(View.VISIBLE);
                arewards1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        arewards1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arewards.setVisibility(View.VISIBLE);
                arewards1.setVisibility(View.GONE);
            }
        });

        acareers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                acareers.setVisibility(View.GONE);
                acareers1.setVisibility(View.VISIBLE);
                acareers1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        acareers1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acareers.setVisibility(View.VISIBLE);
                acareers1.setVisibility(View.GONE);
            }
        });

        ainterviews.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the spinner selected item text
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                // Display the selected item into the
                ainterviews.setVisibility(View.GONE);
                ainterviews1.setVisibility(View.VISIBLE);
                ainterviews1.setText(selectedItemText);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        ainterviews1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ainterviews.setVisibility(View.VISIBLE);
                ainterviews1.setVisibility(View.GONE);
            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Permissions.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        emplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Permissions.this,Preferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        empid = getIntent().getStringExtra("empid");

        firstName.setText(empid);
        text.setText("Admin Dasboard - Employee list - Preference("+empid+")");

        empid = firstName.getText().toString();


        if(empid.startsWith("A"))
        {
            lemp.setVisibility(View.VISIBLE);
            lhr.setVisibility(View.GONE);
            ladmin.setVisibility(View.GONE);
        }
        else if(empid.startsWith("H"))
        {
            lemp.setVisibility(View.GONE);
            lhr.setVisibility(View.VISIBLE);
            ladmin.setVisibility(View.GONE);
        }
        else if(empid.startsWith("L"))
        {
            lemp.setVisibility(View.GONE);
            lhr.setVisibility(View.GONE);
            ladmin.setVisibility(View.VISIBLE);
        }
    }



    public void register(View v) {
        if (profile1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (project1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (message1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (mypay1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (payslips1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (reimbursement11.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (attendance1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (updates1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (myproject1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else if (careers1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }
        else {
            empid = firstName.getText().toString();
            Profile1 = profile1.getText().toString();
            Project1 = project1.getText().toString();
            Message1 = message1.getText().toString();
            Mypay1 = mypay1.getText().toString();
            Payslips1 = payslips1.getText().toString();
            Reimbursement11 = reimbursement11.getText().toString();
            Attendance1 = attendance1.getText().toString();
            Updates1 = updates1.getText().toString();
            Myproject1 = myproject1.getText().toString();
            Careers1 = careers1.getText().toString();

            BackGround b = new BackGround();
            b.execute(empid, Profile1, Project1, Message1, Mypay1, Payslips1, Reimbursement11, Attendance1, Updates1, Myproject1, Careers1);
        }
    }
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String profile = params[1];
            String project = params[2];
            String message = params[3];
            String mypay = params[4];
            String payslips = params[5];
            String reimbursement1 = params[6];
            String attendance = params[7];
            String updates = params[8];
            String myproject = params[9];
            String careers = params[10];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/employeepermissions.php");
                //URL url = new URL("http://192.168.1.55/AltaCRM/employeepermissions.php");
                String urlParams = "empid="+empid+"&profile="+profile+"&project="+project+"&message="+message+"&mypay="+mypay+"&payslips="+payslips+"&reimbursement1="+reimbursement1+"&attendance="+attendance+"&updates="+updates+"&myproject="+myproject+"&careers="+careers;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void register1(View v) {
        if (profileh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (projecth1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (messageh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (mypayh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (payslipsh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (reimbursement1h1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (attendanceh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (updatesh1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (myprojecth1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hnewempform1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hempdirectory1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hprojectdetails1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hempattendance1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hreimbursement1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hdocumentsupload1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hpayslips11.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hrewards1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hcareers1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (hinterviews1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else {
            empid = firstName.getText().toString();
            Profileh1 = profileh1.getText().toString();
            Projecth1 = projecth1.getText().toString();
            Messageh1 = messageh1.getText().toString();
            Mypayh1 = mypayh1.getText().toString();
            Payslipsh1 = payslipsh1.getText().toString();
            Reimbursement1h1 = reimbursement1h1.getText().toString();
            Attendanceh1 = attendanceh1.getText().toString();
            Updatesh1 = updatesh1.getText().toString();
            Hnewempform1 = hnewempform1.getText().toString();
            Hempdirectory1 = hempdirectory1.getText().toString();
            Hprojectdetails1 = hprojectdetails1.getText().toString();
            Hempattendance1 = hempattendance1.getText().toString();
            Hreimbursement1 = hreimbursement1.getText().toString();
            Hdocumentsupload1 = hdocumentsupload1.getText().toString();
            Hpayslips11 = hpayslips11.getText().toString();
            Myprojecth1 = myprojecth1.getText().toString();
            Hrewards1 = hrewards1.getText().toString();
            Hcareers1 = hcareers1.getText().toString();
            Hinterviews1 = hinterviews1.getText().toString();

            BackGround1 b1 = new BackGround1();
            b1.execute(empid, Profileh1, Projecth1, Messageh1, Mypayh1, Payslipsh1, Reimbursement1h1, Attendanceh1, Updatesh1, Hnewempform1, Hempdirectory1, Hprojectdetails1, Hempattendance1, Hreimbursement1, Hdocumentsupload1, Hpayslips11, Myprojecth1, Hrewards1, Hcareers1, Hinterviews1);
        }
    }

    class BackGround1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String empid = params[0];
            String profile = params[1];
            String project = params[2];
            String message = params[3];
            String mypay = params[4];
            String payslips = params[5];
            String reimbursement1 = params[6];
            String attendance = params[7];
            String updates = params[8];
            String hnewempform = params[9];
            String hempdirectory = params[10];
            String hprojectdetails = params[11];
            String hempattendance = params[12];
            String hreimbursement = params[13];
            String hdocumentsupload = params[14];
            String hpayslips1 = params[15];
            String myprojecth = params[16];
            String hrewards = params[17];
            String hcareers = params[18];
            String hinterviews = params[19];

            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/hrpermissions.php");
                //URL url = new URL("http://192.168.1.55/AltaCRM/hrpermissions.php");
                String urlParams = "empid="+empid+"&profile="+profile+"&project="+project+"&message="+message+"&mypay="+mypay+"&payslips="+payslips+"&reimbursement1="+reimbursement1+"&attendance="+attendance+"&updates="+updates+"&newempform="+hnewempform+"&empdirectory="+hempdirectory+"&projectdetails="+hprojectdetails+"&empattendance="+hempattendance+"&reimbursement="+hreimbursement+"&documentsupload="+hdocumentsupload+"&payslips1="+hpayslips1+"&myproject="+myprojecth+"&rewards="+hrewards+"&careers="+hcareers+"&interviews="+hinterviews;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            finish();
        }
    }


    public void register2(View v) {
        if (profilea1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (projecta1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (messagea1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (mypaya1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (payslipsa1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (reimbursement1a1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (attendancea1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (updatesa1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (aempdirectory1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (aprojectdetails1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (aempattendance1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (apayroll1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (adocumentsfetch1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (apermissions1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (acccamera1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (apayslips11.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (anewempform1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (areimbursement1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (adocumentsupload1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (amyprojects1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (arewards1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (acareers1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        } else if (ainterviews1.getText().toString().trim().equals("CHANGE")) {
            Toast.makeText(getApplicationContext(), "Please select proper preference", Toast.LENGTH_LONG).show();
        }else {
            empid = firstName.getText().toString();
            Profilea1 = profilea1.getText().toString();
            Projecta1 = projecta1.getText().toString();
            Messagea1 = messagea1.getText().toString();
            Mypaya1 = mypaya1.getText().toString();
            Payslipsa1 = payslipsa1.getText().toString();
            Reimbursement1a1 = reimbursement1a1.getText().toString();
            Attendancea1 = attendancea1.getText().toString();
            Updatesa1 = updatesa1.getText().toString();
            Aempdirectory1 = aempdirectory1.getText().toString();
            Aprojectdetails1 = aprojectdetails1.getText().toString();
            Aempattendance1 = aempattendance1.getText().toString();
            Apayroll1 = apayroll1.getText().toString();
            Adocumentsfetch1 = adocumentsfetch1.getText().toString();
            Apermissions1 = apermissions1.getText().toString();
            Acccamera1 = acccamera1.getText().toString();
            Apayslips11 = apayslips11.getText().toString();
            Anewempform1 = anewempform1.getText().toString();
            Areimbursement1 = areimbursement1.getText().toString();
            Adocumentsupload1 = adocumentsupload1.getText().toString();
            Amyprojects1 = amyprojects1.getText().toString();
            Arewards1 = arewards1.getText().toString();
            Acareers1 = acareers1.getText().toString();
            Ainterviews1 = ainterviews1.getText().toString();

            BackGround2 b2 = new BackGround2();
            b2.execute(empid, Profilea1, Projecta1, Messagea1, Mypaya1, Payslipsa1, Reimbursement1a1, Attendancea1, Updatesa1, Aempdirectory1, Aprojectdetails1, Aempattendance1, Apayroll1, Adocumentsfetch1, Apermissions1, Acccamera1, Apayslips11, Anewempform1, Areimbursement1, Adocumentsupload1, Amyprojects1, Arewards1, Acareers1, Ainterviews1);
        }
    }

    class BackGround2 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String profile = params[1];
            String project = params[2];
            String message = params[3];
            String mypay = params[4];
            String payslips = params[5];
            String reimbursement1 = params[6];
            String attendance = params[7];
            String updates = params[8];
            String aempdirectory = params[9];
            String aempattendance = params[10];
            String aprojectdetails = params[11];
            String apayroll = params[12];
            String adocumentsfetch = params[13];
            String apermissions = params[14];
            String acccamera = params[15];
            String apayslips1 = params[16];
            String anewempform = params[17];
            String areimbursement = params[18];
            String adocumentsupload = params[19];
            String amyprojects = params[20];
            String arewards = params[21];
            String acareers = params[22];
            String ainterviews = params[23];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/adminpermissions.php");
                //URL url = new URL("http://192.168.1.55/AltaCRM/adminpermissions.php");
                String urlParams = "empid="+empid+"&profile="+profile+"&project="+project+"&message="+message+"&mypay="+mypay+"&payslips="+payslips+"&reimbursement1="+reimbursement1+"&attendance="+attendance+"&updates="+updates+"&empdirectory="+aempdirectory+"&empattendance="+aempattendance+"&projectdetails="+aprojectdetails+"&payroll="+apayroll+"&documentsfetch="+adocumentsfetch+"&permission="+apermissions+"&cccamera="+acccamera+"&payslips1="+apayslips1+"&newempform="+anewempform+"&reimbursement="+areimbursement+"&documentsupload="+adocumentsupload+"&myproject="+amyprojects+"&rewards="+arewards+"&careers="+acareers+"&interviews="+ainterviews;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text = text.getText().toString();
            empid = firstName.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(empid);
        }

        return super.onOptionsItemSelected(item);
    }
    class BackGroundQuery extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data="";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/admindashboardquery.php");
                String urlParams = "empid="+empid;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();
                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data1 = root.getJSONObject("user_data1");
                OFFICIALEMAIL = user_data1.getString("officialemail");
                EMPID = user_data1.getString("empid");
                MANAGERMAIL = user_data1.getString("projectmanagermail");
                MANAGERID = user_data1.getString("managerId");
                HRMAIL = user_data1.getString("Hrmail");
                HRID = user_data1.getString("Hrid");

                Intent i = new Intent(Permissions.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Permissions.this,"Invalid Details",Toast.LENGTH_LONG).show();
            }
        }
    }

}