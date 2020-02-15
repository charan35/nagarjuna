package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class Create_Job extends AppCompatActivity {

    EditText jobtitle,vacancies,experience,bondperiod,salary,joblocation,interviewlocation,skillrequirements;
    Button submit;
    String JobTitle,Vacancies,Experience,BondPeriod,Salary,JobLocation,InterviewLocation,Skillrequirments;
    TextView notificationtitle,notificationmessage;
    String NotificationTitle,NotificationMessage;
    TextView text,empid,text1;
    HorizontalScrollView lq,lq1;
    ImageView iv25,iv251;
    TextView dashboard,dashboard1,details,details1,career,career1;
    android.support.v7.widget.Toolbar toolbar;
    String Text,Empid,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_create__job);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        empid = (TextView)findViewById(R.id.empid);
        lq = (HorizontalScrollView) findViewById(R.id.lq);
        lq1 = (HorizontalScrollView) findViewById(R.id.lq1);
        jobtitle = (EditText)findViewById(R.id.jobtitle);
        vacancies = (EditText)findViewById(R.id.vacancies);
        experience = (EditText)findViewById(R.id.experience);
        bondperiod = (EditText)findViewById(R.id.bondperiod);
        salary = (EditText)findViewById(R.id.salary);
        joblocation = (EditText)findViewById(R.id.joblocation);
        interviewlocation = (EditText)findViewById(R.id.interviewlocation);
        skillrequirements = (EditText)findViewById(R.id.skillrequirements);
        submit = (Button) findViewById(R.id.submit);
        notificationtitle = (TextView) findViewById(R.id.notificationtitle);
        notificationmessage = (TextView) findViewById(R.id.notificationmessage);
        iv25 = (ImageView)findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        details = (TextView)findViewById(R.id.details);
        details1 = (TextView)findViewById(R.id.details1);
        career = (TextView)findViewById(R.id.career);
        career1 = (TextView)findViewById(R.id.career1);

        Text = getIntent().getStringExtra("employee");
        Empid = getIntent().getStringExtra("empid");

        notificationtitle.setText("New JOB Alert");
        notificationmessage.setText("New Jobs are Available in our office. For more details please go through careers.");

        text.setText(Text);
        empid.setText(Empid);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Careers - Job Details - New Job Update");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Careers - Job Details- New Job Update");
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
                Intent intent = new Intent(Create_Job.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        career.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Job.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Job.this,JobDetails.class);
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
                Intent intent = new Intent(Create_Job.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        career1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Job.this,Careers.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        details1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create_Job.this,JobDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationTitle = notificationtitle.getText().toString();
                NotificationMessage = notificationmessage.getText().toString();
                JobTitle = jobtitle.getText().toString();
                Vacancies = vacancies.getText().toString();
                Experience = experience.getText().toString();
                BondPeriod = bondperiod.getText().toString();
                Salary = salary.getText().toString();
                JobLocation = joblocation.getText().toString();
                InterviewLocation = interviewlocation.getText().toString();
                Skillrequirments = skillrequirements.getText().toString();

                if (TextUtils.isEmpty(JobTitle)) {
                    jobtitle.setError("Please enter Job Title");
                    jobtitle.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Vacancies)) {
                    vacancies.setError("Please enter Vacancies");
                    vacancies.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Experience)) {
                    experience.setError("Please enter Experience");
                    experience.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(BondPeriod)) {
                    bondperiod.setError("Please enter BondPeroid");
                    bondperiod.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Salary)) {
                    salary.setError("Please enter Salary");
                    salary.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(JobLocation)) {
                    joblocation.setError("Please enter Job Location");
                    joblocation.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(InterviewLocation)) {
                    interviewlocation.setError("Please enter Interview Location");
                    interviewlocation.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Skillrequirments)) {
                    skillrequirements.setError("Please enter Skill Requirments");
                    skillrequirements.requestFocus();
                    return;
                }

                BackGroundJob b = new BackGroundJob();
                b.execute(JobTitle,Vacancies,Experience,BondPeriod,Salary,JobLocation,InterviewLocation,Skillrequirments);

                BackGroundNotification b1 = new BackGroundNotification();
                b1.execute(NotificationTitle,NotificationMessage);
            }
        });
    }

    class BackGroundJob extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String JobTitle = params[0];
            String Vacancies = params[1];
            String Experience = params[2];
            String BondPeriod = params[3];
            String Salary = params[4];
            String JobLocation = params[5];
            String InterviewLocation = params[6];
            String Skillrequirments = params[7];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/Create_job.php");
                String urlParams = "JobTitle="+JobTitle+"&Vacancies="+Vacancies+"&Experience="+Experience+"&BondPeriod="+BondPeriod+"&Salary="+Salary+"&JobLocation="+JobLocation+"&InterviewLocation="+InterviewLocation+"&Skillrequirments="+Skillrequirments;
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
           /* if(s.equals("")){
                s="Data saved successfully.";
            }*/
            //Toast.makeText(Create_Job.this, s, Toast.LENGTH_LONG).show();
        }
    }

    class BackGroundNotification extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String message = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/notification.php");
                String urlParams = "title="+title+"&message="+message;
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
            Toast.makeText(Create_Job.this, s, Toast.LENGTH_LONG).show();
        }
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
            Text1=text1.getText().toString();
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

                Intent i = new Intent(Create_Job.this, QueryForm.class);
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

                Toast.makeText(Create_Job.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
