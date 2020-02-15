package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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

public class VisitorviewActivity extends AppCompatActivity {

    TextView firstname, lastname, phone, email, contactperson, companyname, companybranch, reasonforvisit, dateofvisit, personalid, typeofvisit, empid;
    String EmpId,Firstname, Lastname, Phone, Email, Contactperson, Companyname, Companybranch, Reasonforvisit, Dateofvisit, Personalid, Typeofvisit, Empid;
    TextView empId,text,admin,dashboard,list;
    ImageView iv25;
    android.support.v7.widget.Toolbar toolbar;
    String Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_visitorview);

        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        contactperson = findViewById(R.id.contactperson);
        companyname = findViewById(R.id.companyname);
        companybranch = findViewById(R.id.companybranch);
        reasonforvisit = findViewById(R.id.reasonforvisit);
        dateofvisit = findViewById(R.id.dateofvisit);
        personalid = findViewById(R.id.personalid);
        typeofvisit = findViewById(R.id.typeofvisit);
        empid = findViewById(R.id.empid);
        dashboard = (TextView)findViewById(R.id.dashboard);
        list = (TextView)findViewById(R.id.list);
        admin = (TextView)findViewById(R.id.admin);
        text = (TextView)findViewById(R.id.text);
        empId = (TextView)findViewById(R.id.empId);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EmpId = getIntent().getStringExtra("empid");
        empId.setText(EmpId);


        Firstname = getIntent().getStringExtra("fname");
        Lastname = getIntent().getStringExtra("lname");
        Phone = getIntent().getStringExtra("phone");
        Contactperson = getIntent().getStringExtra("contactperson");
        Companyname = getIntent().getStringExtra("companyname");
        Companybranch = getIntent().getStringExtra("companybranch");
        Email = getIntent().getStringExtra("email");
        Reasonforvisit = getIntent().getStringExtra("reasonforvisit");
        Dateofvisit = getIntent().getStringExtra("dateofvisit");
        Personalid = getIntent().getStringExtra("personalid");
        Typeofvisit = getIntent().getStringExtra("typeofvisit");
        Empid = getIntent().getStringExtra("empid");

        firstname.setText(Firstname);
        lastname.setText(Lastname);
        phone.setText(Phone);
        email.setText(Email);
        contactperson.setText(Contactperson);
        companyname.setText(Companyname);
        companybranch.setText(Companybranch);
        reasonforvisit.setText(Reasonforvisit);
        dateofvisit.setText(Dateofvisit);
        personalid.setText(Personalid);
        typeofvisit.setText(Typeofvisit);
        empid.setText(Empid);

        text.setText("Admin Dashboard - Visitor Dashboard - View Visitors");

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorviewActivity.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorviewActivity.this,Admin_Visitor_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisitorviewActivity.this,Viewvistors.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }
    //Query
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
            Text=text.getText().toString();
            EmpId = empId.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(EmpId);
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

                Intent i = new Intent(VisitorviewActivity.this, QueryForm.class);
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

                Toast.makeText(VisitorviewActivity.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
