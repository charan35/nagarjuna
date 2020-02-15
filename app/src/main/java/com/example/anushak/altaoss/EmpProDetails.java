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

public class EmpProDetails extends AppCompatActivity {

    TextView projectname,clientname,managername,startdate,enddate,dashboard,projectslist,empid,text;
    String Projectname,Clientname,Managername,Startdate,Enddate,Empid,Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_pro_details);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        projectname = (TextView)findViewById(R.id.projectname);
        clientname = (TextView)findViewById(R.id.clientname);
        managername = (TextView)findViewById(R.id.managername);
        startdate = (TextView)findViewById(R.id.startdate);
        enddate = (TextView)findViewById(R.id.enddate);
        dashboard = (TextView) findViewById(R.id.dashboard);
        projectslist = (TextView) findViewById(R.id.empprojects);
        iv25 = (ImageView) findViewById(R.id.iv25);
        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);

        Projectname = getIntent().getStringExtra("projectname");
        Clientname = getIntent().getStringExtra("clientname");
        Managername = getIntent().getStringExtra("firstname");
        Startdate = getIntent().getStringExtra("startdate");
        Enddate = getIntent().getStringExtra("enddate");
        Empid = getIntent().getStringExtra("empid");

        projectname.setText(Projectname);
        clientname.setText(Clientname);
        managername.setText(Managername);
        startdate.setText(Startdate);
        enddate.setText(Enddate);
        empid.setText(Empid);
        text.setText("Dashboard - Emp Projects("+Empid+") - Project Details");

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpProDetails.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        projectslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpProDetails.this,ProjectsList.class);
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
            Empid = empid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Empid);
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

                Intent i = new Intent(EmpProDetails.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text);
                // i.putExtra("message1", Text1);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpProDetails.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }


}
