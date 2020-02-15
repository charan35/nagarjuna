package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Profile extends AppCompatActivity {

    TextView empId,text,dept,desig,firstName,middleName,lastName,genderEmp,bloodGroup,contactnumber,dateofjoining,permanentAddress,correspondanceAddress,officialEmail,personalEmail;
    String empid,Text,department,designation,firstname,middlename,lastname,gender,bloodgroup,contactno,doj,permanentaddress,correspondanceaddress,officialemail,personalemail;
    ImageView profileImage;
    String FIRSTNAME=null;
    String MANAGERMAIL=null,MANAGERID=null,HRMAIL=null,HRID=null,EMPID=null,OFFICIALEMAIL=null,Grid;

    LinearLayout lq,lq1,lq2;
    ImageView iv8,iv81,iv82;
    TextView dashboard,dashboard1,dashboard2,emplist1,emplist2,grid;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_profile);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profileImage = (ImageView) findViewById(R.id.profile_image);
        empId = (TextView) findViewById(R.id.tvEmpID);
        dept = (TextView) findViewById(R.id.tvDepartment);
        desig = (TextView) findViewById(R.id.tvDesignation);
        firstName = (TextView) findViewById(R.id.tvFirstName);
        middleName = (TextView) findViewById(R.id.tvMiddleName);
        lastName = (TextView) findViewById(R.id.tvLastName);
        genderEmp = (TextView) findViewById(R.id.tvGender);
        bloodGroup = (TextView) findViewById(R.id.tvBloodGroup);
        contactnumber = (TextView) findViewById(R.id.tvContactNumber);
        dateofjoining=(TextView)findViewById(R.id.tvdateofjoining);
        permanentAddress = (TextView) findViewById(R.id.tvPermanentAddress);
        correspondanceAddress = (TextView) findViewById(R.id.tvCorrespondanceAddress);
        officialEmail = (TextView) findViewById(R.id.tvOfficialEmail);
        personalEmail = (TextView) findViewById(R.id.tvPersonalEmail);
        emplist1 = (TextView)findViewById(R.id.emplist1);
        emplist2 = (TextView)findViewById(R.id.emplist2);
        grid = (TextView)findViewById(R.id.Grid);

        text = (TextView)findViewById(R.id.text1);

        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        iv8 = (ImageView) findViewById(R.id.iv8);
        iv81 = (ImageView) findViewById(R.id.iv81);
        iv82 = (ImageView) findViewById(R.id.iv82);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);

     //   image = getIntent().getStringExtra("url");

        empid = getIntent().getStringExtra("empid");
        department=getIntent().getStringExtra("department");
        designation=getIntent().getStringExtra("designation");
        firstname=getIntent().getStringExtra("firstname");
        middlename=getIntent().getStringExtra("middlename");
        lastname=getIntent().getStringExtra("lastname");
        gender=getIntent().getStringExtra("gender");
        bloodgroup=getIntent().getStringExtra("bloodgroup");
        contactno=getIntent().getStringExtra("contactno");
        doj = getIntent().getStringExtra("dateofjoining");
        permanentaddress=getIntent().getStringExtra("permanentaddress");
        correspondanceaddress=getIntent().getStringExtra("correspondanceaddress");
        officialemail=getIntent().getStringExtra("officialemail");
        personalemail=getIntent().getStringExtra("personalemail");

        Text = getIntent().getStringExtra("employee");

        text.setText(Text);
        empId.setText(empid);
        dept.setText(department);
        desig.setText(designation);
        firstName.setText(firstname);
        middleName.setText(middlename);
        lastName.setText(lastname);
        genderEmp.setText(gender);
        bloodGroup.setText(bloodgroup);
        contactnumber.setText(contactno);
        dateofjoining.setText(doj);
        permanentAddress.setText(permanentaddress);
        correspondanceAddress.setText(correspondanceaddress);
        officialEmail.setText(officialemail);
        personalEmail.setText(personalemail);

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv81.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        emplist1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv82.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        emplist2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EmpDirectory.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("employee"))
        {
            grid.setText("Employee Dashboard - Profile("+empid+")");
        }
        else if(Text.equals("hr"))
        {
            grid.setText("HR Dashboard - Employee Directory - Profile("+empid+")");
        }
       else  if(Text.equals("admin"))
        {
            grid.setText("Admin Dashboard - Employee Directory - Profile("+empid+")");
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
            Grid = grid.getText().toString();
            empid = empId.getText().toString();
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

                Intent i = new Intent(Profile.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message",Grid);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Profile.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}

