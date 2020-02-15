package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class Leave extends AppCompatActivity {

    TextView text1,text2;
    String Text1,Text2,EMPID,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    ImageView iv14;
    TextView dashboard,attendance;
    Spinner leavetype,applyto;
    EditText fromdate,todate,reason,noofdays;
    Button submit;
    String Fromdate,Todate,Reason,Leavetype,Noofdays,Applyto;
    android.support.v7.widget.Toolbar toolbar;
    DatePickerDialog datePickerDialog;
    int day1,day2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv14 = (ImageView) findViewById(R.id.iv14);
        dashboard =(TextView) findViewById(R.id.dashboard);
        attendance = (TextView) findViewById(R.id.attendance);

        text1 = (TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid2);

        noofdays = (EditText)findViewById(R.id.noofdays);
        leavetype = (Spinner)findViewById(R.id.leavetype);
        applyto = (Spinner)findViewById(R.id.applyto);
        reason = (EditText)findViewById(R.id.reason);
        fromdate = (EditText) findViewById(R.id.fromdate);
        todate = (EditText) findViewById(R.id.todate);
        submit = (Button)findViewById(R.id.submit);

        Text1 = getIntent().getStringExtra("empid");

        text1.setText(Text1);
        text2.setText("Employee Dashboard - Attendance - Leave" + "("+Text1+")");

        iv14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Leave.this,Attendance.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        fromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Leave.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                fromdate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        todate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                datePickerDialog = new DatePickerDialog(Leave.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                todate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        /*long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        //return ("" + (int) dayCount + " Days");*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Text1 = text1.getText().toString();
                Leavetype = leavetype.getSelectedItem().toString();
                Reason = reason.getText().toString();
                Fromdate = fromdate.getText().toString();
                Todate = todate.getText().toString();
                Noofdays = noofdays.getText().toString();
                Applyto = applyto.getSelectedItem().toString();

               // long difference = Math.abs(Fromdate.getTime() - Todate.getTime());

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                //i.putExtra(Intent.ACTION_SEND, email);
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{});
                i.putExtra(Intent.EXTRA_SUBJECT, "Login Details");
                //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
                i.putExtra(Intent.EXTRA_TEXT   , "Employee ID:"+Text1+"   Reason:"+Reason+"   No.Of Days:"+Noofdays);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Leave.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(Reason)) {
                    reason.setError("Please enter Reason");
                    reason.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Fromdate)) {
                    fromdate.setError("Please select From Date");
                    fromdate.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Todate)) {
                    todate.setError("Please select From Date");
                    todate.requestFocus();
                    return;
                }
                BackGroundLeave b = new BackGroundLeave();
                b.execute(Text1,Leavetype,Reason,Fromdate,Todate,Noofdays,Applyto);
            }
        });

    }

    class BackGroundLeave extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String leavetype = params[1];
            String reason = params[2];
            String fromdate = params[3];
            String todate = params[4];
            String noofdays = params[5];
            String applyto = params[6];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/Leave.php");
                String urlParams = "empid="+empid+"&leavetype="+leavetype+"&reason="+reason+"&fromdate="+fromdate+"&todate="+todate+"&noofdays="+noofdays+"&applyto="+applyto;

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
            Toast.makeText(Leave.this, s, Toast.LENGTH_LONG).show();
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
            Text2=text2.getText().toString();
            Text1 = text1.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Text1);
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

                Intent i = new Intent(Leave.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text2);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Leave.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
