package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class Attendance extends AppCompatActivity implements View.OnClickListener{

    TextView empid, email;
    Button yearlyattendance;
    TextView applyleave, subject, subject1,text;
    String Email, Empid, Applyleave, Subject, Subject1, HRMAIL, HRID, MANAGERMAIL, MANAGERID, OFFICIALEMAIL, EMPID,Text;

    TextView text1;
    String Text1;
    ImageView iv14, iv141, iv142;
    TextView dashboard, dashboard1, dashboard2;
    LinearLayout lq, lq1, lq2;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_attendance);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        empid = (TextView) findViewById(R.id.attname);
        email = (TextView) findViewById(R.id.attemail);
        applyleave = (TextView) findViewById(R.id.applyleave);
        yearlyattendance = (Button) findViewById(R.id.yearlyattendance);
        subject = (TextView) findViewById(R.id.subject);
        subject1 = (TextView) findViewById(R.id.subject1);
        text = (TextView)findViewById(R.id.Grid);

        text1 = (TextView) findViewById(R.id.text1);
        iv14 = (ImageView) findViewById(R.id.iv14);
        iv141 = (ImageView) findViewById(R.id.iv141);
        iv142 = (ImageView) findViewById(R.id.iv142);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);

        Email = getIntent().getStringExtra("officialemail");
        Empid = getIntent().getStringExtra("empid");
        Applyleave = getIntent().getStringExtra("button");
        Subject = getIntent().getStringExtra("subject");
        Subject1 = getIntent().getStringExtra("subject1");
        Text1 = getIntent().getStringExtra("employee");

        email.setText(Email);
        empid.setText(Empid);
        applyleave.setText(Applyleave);
        subject.setText(Subject);
        subject1.setText(Subject1);
        text1.setText(Text1);
        text.setText("Employee DashBoard - Attendance" + "(" + Empid + ")");

        applyleave.setOnClickListener(this);
        yearlyattendance.setOnClickListener(this);

        iv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Attendance.this, EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text1.equals("employee") ? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text1.equals("hr") ? View.GONE : View.GONE);
        lq2.setVisibility(Text1.equals("admin") ? View.GONE : View.GONE);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.applyleave:
                Empid = empid.getText().toString();
                Intent i = new Intent(Attendance.this, Leave.class);
                i.putExtra("empid", Empid);
                startActivity(i);
                break;

            case R.id.yearlyattendance:
                Subject = subject1.getText().toString();
                Text1 = text1.getText().toString();
                Intent i1 = new Intent(Attendance.this, YearlyAttendance.class);
                i1.putExtra("officialemail", Email);
                i1.putExtra("empid", Empid);
                i1.putExtra("subject1", Subject1);
                i1.putExtra("employee", Text1);
                startActivity(i1);
                break;
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

                Intent i = new Intent(Attendance.this, QueryForm.class);
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

                Toast.makeText(Attendance.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }


}

