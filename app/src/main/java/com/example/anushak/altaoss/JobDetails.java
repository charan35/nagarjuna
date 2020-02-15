package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

public class JobDetails extends AppCompatActivity {

    TextView title,vacancies,experience,bondperiod,salary,joblocation,interviewlocation,skills;
    String Title,Vacancies,Experience,Bond,Salary,JobLocation,InterviewLocation,Skills;
    android.support.v7.widget.Toolbar toolbar;
    TextView text,empid,text1;
    String Text,Empid,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2,career,career1,career2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_job_details);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        empid = (TextView)findViewById(R.id.empid);
        lq = (HorizontalScrollView) findViewById(R.id.lq);
        lq1 = (HorizontalScrollView) findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView) findViewById(R.id.lq2);
        title = (TextView)findViewById(R.id.title);
        vacancies = (TextView)findViewById(R.id.vacancies);
        experience = (TextView)findViewById(R.id.experience);
        bondperiod = (TextView)findViewById(R.id.bondperiod);
        salary = (TextView)findViewById(R.id.salary);
        joblocation = (TextView)findViewById(R.id.joblocation);
        interviewlocation = (TextView)findViewById(R.id.interviewlocation);
        skills = (TextView)findViewById(R.id.skills);
        iv25 = (ImageView)findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        iv252 = (ImageView)findViewById(R.id.iv252);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);
        career = (TextView)findViewById(R.id.career);
        career1 = (TextView)findViewById(R.id.career1);
        career2 = (TextView)findViewById(R.id.career2);

        Title = getIntent().getStringExtra("JobTitle");
        Vacancies = getIntent().getStringExtra("Vacancies");
        Experience = getIntent().getStringExtra("Experience");
        Bond = getIntent().getStringExtra("BondPeriod");
        Salary = getIntent().getStringExtra("Salary");
        JobLocation = getIntent().getStringExtra("JobLocation");
        InterviewLocation = getIntent().getStringExtra("InterviewLocation");
        Skills = getIntent().getStringExtra("Skillrequirments");
        Text = getIntent().getStringExtra("employee");
        Empid = getIntent().getStringExtra("empid");

        title.setText(Title);
        vacancies.setText(Vacancies);
        experience.setText(Experience);
        bondperiod.setText(Bond);
        salary.setText(Salary);
        joblocation.setText(JobLocation);
        interviewlocation.setText(InterviewLocation);
        skills.setText(Skills);

        text.setText(Text);
        empid.setText(Empid);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Careers - Job Details");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Careers - Job Details");
        }
        else  if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Careers - Job Details");
        }

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        career.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv251.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        career1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv252.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        career2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobDetails.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

    //Query
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        getMenuInflater().inflate(R.menu.new_job_alert, menu);
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text1=text1.getText().toString();
            Empid = empid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Empid);
        }
        if(id == R.id.share)
        {

        }
        if(id == R.id.job)
        {
            if(Text.equals("admin")||Text.equals("hr")) {
                Intent intent = new Intent(JobDetails.this, Create_Job.class);
                intent.putExtra("empid",Empid);
                intent.putExtra("employee",Text);
                startActivity(intent);
            }
            else {
                Toast.makeText(JobDetails.this,"You Don't have permission to access.", Toast.LENGTH_LONG).show();
            }
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

                Intent i = new Intent(JobDetails.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text1);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(JobDetails.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
