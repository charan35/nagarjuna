package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class EditProfile extends AppCompatActivity {

    TextView empId,dept,desig,firstName,middleName,lastName,genderEmp,bloodGroup,dateofjoining,contactnumber,permanentAddress,correspondanceAddress,officialEmail,personalEmail;
    String empid,department,designation,firstname,middlename,lastname,gender,bloodgroup,doj,contactno,permanentaddress,correspondanceaddress,officialemail,personalemail;
    EditText etempId,etdept,etdesig,etfirstName,etmiddleName,etlastName,etgenderEmp,etbloodGroup,etdateofjoining,etcontactnumber,etpermanentAddress,etcorrespondanceAddress,etofficialEmail,etpersonalEmail;
    Context ctx=this;
    ImageView profileImage;
    Button button,submit;
    String FIRSTNAME=null,OFFICIALEMAIL,HRMAIL,HRID,MANAGERMAIL,MANAGERID,EMPID;
    ImageView edit;

    TextView text2,subject;
    String Text2,Subject;
    LinearLayout lq,lq1,lq2;
    TextView dashboard,dashboard1,dashboard2;
    ImageView iv8,iv81,iv82;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_edit_profile);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        iv8 = (ImageView) findViewById(R.id.iv8);
        iv81 = (ImageView) findViewById(R.id.iv81);
        iv82 = (ImageView) findViewById(R.id.iv82);
        text2 = (TextView) findViewById(R.id.text2);
        subject = (TextView) findViewById(R.id.message);

        edit = (ImageView) findViewById(R.id.edit);
        submit = (Button) findViewById(R.id.submit);
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

        etempId = (EditText) findViewById(R.id.etEmpID);
        etdept = (EditText) findViewById(R.id.etDepartment);
        etdesig = (EditText) findViewById(R.id.etDesignation);
        etfirstName = (EditText) findViewById(R.id.etFirstName);
        etmiddleName = (EditText) findViewById(R.id.etMiddleName);
        etlastName = (EditText) findViewById(R.id.etLastName);
        etgenderEmp = (EditText) findViewById(R.id.etGender);
        etbloodGroup = (EditText) findViewById(R.id.etBloodGroup);
        etcontactnumber = (EditText) findViewById(R.id.etContactNumber);
        etdateofjoining=(EditText)findViewById(R.id.etdateofjoining);
        etpermanentAddress = (EditText) findViewById(R.id.etPermanentAddress);
        etcorrespondanceAddress = (EditText) findViewById(R.id.etCorrespondanceAddress);
        etofficialEmail = (EditText) findViewById(R.id.etOfficialEmail);
        etpersonalEmail = (EditText) findViewById(R.id.etPersonalEmail);

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
        Text2 = getIntent().getStringExtra("employee");


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
        text2.setText(Text2);

        etempId.setText(empid);
        etdept.setText(department);
        etdesig.setText(designation);
        etfirstName.setText(firstname);
        etmiddleName.setText(middlename);
        etlastName.setText(lastname);
        etgenderEmp.setText(gender);
        etbloodGroup.setText(bloodgroup);
        etcontactnumber.setText(contactno);
        etdateofjoining.setText(doj);
        etpermanentAddress.setText(permanentaddress);
        etcorrespondanceAddress.setText(correspondanceaddress);
        etofficialEmail.setText(officialemail);
        etpersonalEmail.setText(personalemail);

        iv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this,EmployeeDashboard.class);
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

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this,HrDashboard.class);
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
                Intent intent = new Intent(EditProfile.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text2.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("employee"))
        {
            subject.setText("Employee Dashboard - Edit Profile" + "("+empid+")");
        }
        else if(Text2.equals("hr"))
        {
            subject.setText("HR Dashboard - Edit Profile("+empid+")");
        }
        else  if(Text2.equals("admin"))
        {
            subject.setText("Admin Dashboard - Edit Profile("+empid+")");
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empId.setVisibility(8);
                etempId.setVisibility(0);

                dept.setVisibility(8);
                etdept.setVisibility(0);

                desig.setVisibility(8);
                etdesig.setVisibility(0);

                firstName.setVisibility(8);
                etfirstName.setVisibility(0);

                middleName.setVisibility(8);
                etmiddleName.setVisibility(0);

                lastName.setVisibility(8);
                etlastName.setVisibility(0);

                genderEmp.setVisibility(8);
                etgenderEmp.setVisibility(0);

                bloodGroup.setVisibility(8);
                etbloodGroup.setVisibility(0);

                contactnumber.setVisibility(8);
                etcontactnumber.setVisibility(0);

                dateofjoining.setVisibility(8);
                etdateofjoining.setVisibility(0);

                permanentAddress.setVisibility(8);
                etpermanentAddress.setVisibility(0);

                correspondanceAddress.setVisibility(8);
                etcorrespondanceAddress.setVisibility(0);

                officialEmail.setVisibility(8);
                etofficialEmail.setVisibility(0);

                personalEmail.setVisibility(8);
                etpersonalEmail.setVisibility(0);

                submit.setVisibility(0);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();

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


                empId.setVisibility(0);
                etempId.setVisibility(8);

                dept.setVisibility(0);
                etdept.setVisibility(8);

                desig.setVisibility(0);
                etdesig.setVisibility(8);

                firstName.setVisibility(0);
                etfirstName.setVisibility(8);

                middleName.setVisibility(0);
                etmiddleName.setVisibility(8);

                lastName.setVisibility(0);
                etlastName.setVisibility(8);

                genderEmp.setVisibility(0);
                etgenderEmp.setVisibility(8);

                bloodGroup.setVisibility(0);
                etbloodGroup.setVisibility(8);

                contactnumber.setVisibility(0);
                etcontactnumber.setVisibility(8);

                dateofjoining.setVisibility(0);
                etdateofjoining.setVisibility(8);

                permanentAddress.setVisibility(0);
                etpermanentAddress.setVisibility(8);

                correspondanceAddress.setVisibility(0);
                etcorrespondanceAddress.setVisibility(8);

                officialEmail.setVisibility(0);
                etofficialEmail.setVisibility(8);

                personalEmail.setVisibility(0);
                etpersonalEmail.setVisibility(8);

                submit.setVisibility(8);
            }
        });
    }

    public void Update()
    {
        empid = etempId.getText().toString();
        department = etdept.getText().toString();
        designation = etdesig.getText().toString();
        firstname = etfirstName.getText().toString();
        middlename = etmiddleName.getText().toString();
        lastname = etlastName.getText().toString();
        gender = etgenderEmp.getText().toString();
        bloodgroup = etbloodGroup.getText().toString();
        doj = etdateofjoining.getText().toString();
        contactno = etcontactnumber.getText().toString();
        permanentaddress = etpermanentAddress.getText().toString();
        correspondanceaddress = etcorrespondanceAddress.getText().toString();
        officialemail = etofficialEmail.getText().toString();
        personalemail = etpersonalEmail.getText().toString();

        new Update(this,0).execute(empid,department,designation,firstname,middlename,lastname,gender,bloodgroup,doj,contactno,permanentaddress,correspondanceaddress,officialemail,personalemail);

    }

    class Update extends AsyncTask<String, Void, String>

    {
        public Update (Context context, int flag)
        {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String empid = (String) arg0[0];
                String department = (String) arg0[1];
                String designation = (String) arg0[2];
                String firstname = (String) arg0[3];
                String middlename = (String) arg0[4];
                String lastname = (String) arg0[5];
                String gender = (String) arg0[6];
                String bloodgroup = (String) arg0[7];
                String dateofjoining = (String) arg0[8];
                String contactno = (String) arg0[9];
                String permanentaddress = (String) arg0[10];
                String correspondanceaddress = (String) arg0[11];
                String officialemail = (String) arg0[12];
                String personalemail = (String) arg0[13];

                String link = "http://altaitsolutions.com/Altadatabase/new2.php";

                String data = URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("department", "UTF-8") + "=" + URLEncoder.encode(department, "UTF-8");
                data += "&" + URLEncoder.encode("designation", "UTF-8") + "=" + URLEncoder.encode(designation, "UTF-8");
                data += "&" + URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8");
                data += "&" + URLEncoder.encode("middlename", "UTF-8") + "=" + URLEncoder.encode(middlename, "UTF-8");
                data += "&" + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8");
                data += "&" + URLEncoder.encode("gender", "UTF-8") + "=" + URLEncoder.encode(gender, "UTF-8");
                data += "&" + URLEncoder.encode("bloodgroup", "UTF-8") + "=" + URLEncoder.encode(bloodgroup, "UTF-8");
                data += "&" + URLEncoder.encode("dateofjoining", "UTF-8") + "=" + URLEncoder.encode(dateofjoining, "UTF-8");
                data += "&" + URLEncoder.encode("contactno", "UTF-8") + "=" + URLEncoder.encode(contactno, "UTF-8");
                data += "&" + URLEncoder.encode("permanentaddress", "UTF-8") + "=" + URLEncoder.encode(permanentaddress, "UTF-8");
                data += "&" + URLEncoder.encode("correspondanceaddress", "UTF-8") + "=" + URLEncoder.encode(correspondanceaddress, "UTF-8");
                data += "&" + URLEncoder.encode("officialemail", "UTF-8") + "=" + URLEncoder.encode(officialemail, "UTF-8");
                data += "&" + URLEncoder.encode("personalemail", "UTF-8") + "=" + URLEncoder.encode(personalemail, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {
                return new String("Expection: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(),"Data has been updated", Toast.LENGTH_LONG).show();
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

        if (id == R.id.query)
        {
           /* Intent intent=new Intent(EditProfile.this,QueryForm.class);
            startActivity(intent);*/
            Subject=subject.getText().toString();
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
                URL url = new URL("http://192.168.1.55/AltaCRM/admindashboardquery.php");
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

                Intent i = new Intent(EditProfile.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Subject);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EditProfile.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}



