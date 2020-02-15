package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Calendar;

public class MyProject extends AppCompatActivity {

    EditText projectname, teamleadername, clientname;
    Spinner projectstatus;
    TextView empid,startdate,enddate,firstname,text;
    ImageView date1,date2;
    String Empid,FirstName,Projectname, TeamLeaderName,ProjectStatus, StartDate,EndDate,ClientName,Text;
    Button projectsubmit;
    Context ctx=this;
    DatePickerDialog datePickerDialog;
    ImageView iv25;
    TextView dashboard,subject;
    String MANAGERMAIL=null,MANAGERID=null,HRMAIL=null,HRID=null,EMPID=null,OFFICIALEMAIL=null,Subject;
    android.support.v7.widget.Toolbar toolbar;
    TextView notificationtitle,notificationmessage;
    String NotificationTitle,NotificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_project);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empid = (TextView) findViewById(R.id.empid);
        firstname = (TextView) findViewById(R.id.firstname);
        projectname = (EditText) findViewById(R.id.projectname);
        teamleadername = (EditText) findViewById(R.id.teamleadername);
        clientname = (EditText) findViewById(R.id.clientname);
        projectstatus = (Spinner) findViewById(R.id.projectstatus);
        startdate = (TextView) findViewById(R.id.startdate);
        enddate = (TextView) findViewById(R.id.enddate);
        date1 = (ImageView) findViewById(R.id.date1);
        date2 = (ImageView) findViewById(R.id.date2);
        projectsubmit = (Button) findViewById(R.id.projectsubmit);
        text = (TextView) findViewById(R.id.Grid2);
        dashboard = (TextView) findViewById(R.id.dashboard);
        iv25 = (ImageView) findViewById(R.id.iv25);
        subject = (TextView)findViewById(R.id.message);
        notificationtitle = (TextView) findViewById(R.id.notificationtitle);
        notificationmessage = (TextView) findViewById(R.id.notificationmessage);

        FirstName = getIntent().getStringExtra("firstname");
        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);
        firstname.setText(FirstName);

        subject.setText("Employee Dashboard  - New Project" + "("+Empid+")");
        notificationtitle.setText("New Project Alert");
        notificationmessage.setText("New Project has been created by"+ "("+Empid+")");

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MyProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MyProject.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                enddate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProject.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }

    public void projectsubmit(View v) {
        NotificationTitle = notificationtitle.getText().toString();
        NotificationMessage = notificationmessage.getText().toString();
        Empid=empid.getText().toString();
        FirstName=firstname.getText().toString();
        Projectname = projectname.getText().toString();
        TeamLeaderName = teamleadername.getText().toString();
        ClientName = clientname.getText().toString();
        StartDate = startdate.getText().toString();
        EndDate = enddate.getText().toString();
        ProjectStatus = projectstatus.getSelectedItem().toString();

        if (TextUtils.isEmpty(Projectname)) {
            projectname.setError("Please enter Projectname");
            projectname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(TeamLeaderName)) {
            teamleadername.setError("Please enter TeamLeader Name");
            teamleadername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(ClientName)) {
            clientname.setError("Please enter Client Name");
            clientname.requestFocus();
            return;
        }
        BackGround7 b = new BackGround7();
        b.execute(Empid,Projectname, TeamLeaderName, ClientName, StartDate, EndDate,ProjectStatus,FirstName);

        BackGroundNotification b1 = new BackGroundNotification();
        b1.execute(NotificationTitle,NotificationMessage);
    }
    class BackGround7 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid=params[0];
            String projectname=params[1];
            String teamleadername=params[2];
            String clientname=params[3];
            String startdate=params[4];
            String enddate=params[5];
            String projectstatus=params[6];
            String firstname=params[7];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/new_project.php");
                String urlParams = "empid="+empid+"&projectname="+projectname+"&teamleadername="+teamleadername+"&clientname="+clientname+"&startdate="+startdate+"&enddate="+enddate+"&projectstatus="+projectstatus+"&firstname="+firstname;
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
            Toast.makeText(MyProject.this, s, Toast.LENGTH_LONG).show();
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
            Subject=subject.getText().toString();
            Empid=empid.getText().toString();
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

                Intent i = new Intent(MyProject.this, QueryForm.class);
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

                Toast.makeText(MyProject.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
