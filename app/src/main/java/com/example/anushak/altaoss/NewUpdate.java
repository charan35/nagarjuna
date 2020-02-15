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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewUpdate extends AppCompatActivity {

    EditText projectname, notes, updatedby;
    Spinner taskstatus;
    TextView empid,date1,text,project;
    String Project,Empid,Projectname, Notes,Taskstatus, Updatedby,Date1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,Text,Employee;
    Button submit;
    ImageView dateimage;
    Context ctx=this;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ImageView iv25,iv251,iv252;
    TextView dashboard,ourupdate,dashboard1,ourupdate1,dashboard2,ourupdate2,employee;
    HorizontalScrollView lq,lq1,lq2;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_new_update);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empid = (TextView) findViewById(R.id.empid);
        projectname = (EditText) findViewById(R.id.projectname);
        notes = (EditText) findViewById(R.id.notes);
        taskstatus = (Spinner) findViewById(R.id.taskstatus);
        updatedby = (EditText) findViewById(R.id.updatedby);
        date1 = (TextView) findViewById(R.id.date);
        submit = (Button) findViewById(R.id.submit);
        dateimage = (ImageView) findViewById(R.id.date1);
        text = (TextView) findViewById(R.id.Grid2);
        project = (TextView) findViewById(R.id.project);
        iv25 = (ImageView) findViewById(R.id.iv25);
        dashboard = (TextView) findViewById(R.id.dashboard);
        ourupdate = (TextView) findViewById(R.id.ourupdate);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        ourupdate1 = (TextView) findViewById(R.id.ourupdate1);
        iv252 = (ImageView) findViewById(R.id.iv252);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        ourupdate2 = (TextView) findViewById(R.id.ourupdate2);
        employee = (TextView)findViewById(R.id.employee);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView)findViewById(R.id.lq2);

        Employee = getIntent().getStringExtra("employee");
        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);
        employee.setText(Employee);
        project.setText("Project");

        dateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  HH:mm a"); //Date and time
                String currentDate = sdf.format(calendar.getTime());

//Day of Name in full form like,"Saturday", or if you need the first three characters you have to put "EEE" in the date format and your result will be "Sat".
                SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
                Date date = new Date();
                String dayName = sdf_.format(date);
                date1.setText("" + dayName + "  " + currentDate + "");
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
                Intent intent = new Intent(NewUpdate.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        ourupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
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
                Intent intent = new Intent(NewUpdate.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        ourupdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
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
                Intent intent = new Intent(NewUpdate.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        ourupdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUpdate.this,OurUpdates.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(NewUpdate.this,R.layout.support_simple_spinner_dropdown_item,getResources().getStringArray(R.array.Task_Status));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskstatus.setAdapter(adapter);

        lq.setVisibility(Employee.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Employee.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Employee.equals("admin")? View.VISIBLE : View.GONE);

        if(Employee.equals("employee"))
        {
            text.setText("Employee Dashboard - Our updates - New Update" + "("+Empid+")");
        }
        else if(Employee.equals("hr"))
        {
            text.setText("HR Dashboard - Our updates - New Update" + "("+Empid+")");
        }
        else  if(Employee.equals("admin"))
        {
            text.setText("Admin Dashboard - Our updates - New Update" + "("+Empid+")");
        }

    }

    public void submit(View v) {
        Empid=empid.getText().toString();
        Projectname = projectname.getText().toString();
        Notes = notes.getText().toString();
        Taskstatus = taskstatus.getSelectedItem().toString();
        Updatedby = updatedby.getText().toString();
        Date1 = date1.getText().toString();
        Project = project.getText().toString();

        if (TextUtils.isEmpty(Projectname)) {
            projectname.setError("Please enter Projectname");
            projectname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Notes)) {
            notes.setError("Please enter Notes");
            notes.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Updatedby)) {
            updatedby.setError("Please enter Updatedby");
            updatedby.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Date1)) {
            date1.setError("Please select Date");
            date1.requestFocus();
            return;
        }

        BackGround7 b = new BackGround7();
        b.execute(Empid, Projectname, Notes, Taskstatus, Updatedby, Date1, Project);

    }

    class BackGround7 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String projectname = params[1];
            String notes = params[2];
            String taskstatus = params[3];
            String updatedby = params[4];
            String date1 = params[5];
            String project = params[6];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/employee_new_update.php");
                String urlParams = "empid="+empid+"&projectname="+projectname+"&notes="+notes+"&taskstatus="+taskstatus+"&updatedby="+updatedby+"&date="+date1+"&Type="+project;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
           /* if (s.equals("")) {
                s = "Data saved successfully.";
            }

            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();*/
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

                Intent i = new Intent(NewUpdate.this, QueryForm.class);
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

                Toast.makeText(NewUpdate.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }


}
