package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.Calendar;

public class PaySlips extends AppCompatActivity {

    TextView payslips,text;
    android.widget.Spinner Spinner,Spinner1,type,reMonth,reYear;
    TextView empId, firstName;
    String empid, firstname,month,year,Text,reyear,category;
    String FIRSTNAME = null;
    String EMPID = null,MONTH=null,YEAR=null,CATEGORY=null,MANAGERMAIL,MANAGERID,HRMAIL,HRID,OFFICIALEMAIL;
    String PDFURL=null;
    android.support.v7.widget.Toolbar toolbar;

    TextView text2;
    String Text2;
    LinearLayout lq,lq1,lq2;
    ImageView iv12,iv121,iv122;
    TextView dashboard,mypay,dashboard1,dashboard2,mypay1,mypay2;
    ArrayList<String> years = new ArrayList<String>();
    ArrayList<String> months = new ArrayList<String>();
    ArrayList<String> months1 = new ArrayList<String>();
    static final String[] Months = new String[] { "January", "February",
            "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pay_slips);

        final int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2018; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);

        Spinner1 =(android.widget.Spinner)findViewById(R.id.yearspinner);
        Spinner1.setAdapter(adapter);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        payslips = (TextView) findViewById(R.id.paySlips);
        empId = (TextView) findViewById(R.id.tvEmpID);
        firstName = (TextView) findViewById(R.id.tvFirstName);
        Spinner =(android.widget.Spinner)findViewById(R.id.monthspinner);
        text = (TextView)findViewById(R.id.Grid2);

        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        text2 = (TextView) findViewById(R.id.text2);
        iv12 = (ImageView) findViewById(R.id.iv12);
        iv121 = (ImageView) findViewById(R.id.iv121);
        iv122 = (ImageView) findViewById(R.id.iv122);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        mypay = (TextView) findViewById(R.id.mypay);
        mypay1 = (TextView) findViewById(R.id.mypay1);
        mypay2 = (TextView) findViewById(R.id.mypay2);

        empid = getIntent().getStringExtra("empid");
        firstname = getIntent().getStringExtra("firstname");
        Text2 = getIntent().getStringExtra("employee");

        empId.setText(empid);
        firstName.setText(firstname);
        text2.setText(Text2);

        Spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                        for (int i = 1; i <= Months.length; i++) {
                        //Spinner =(Spinner)findViewById(Months[i]);
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PaySlips.this, android.R.layout.simple_spinner_item, Months);
                        Spinner.setAdapter(adapter1);
                    }

                }
                else
                {
                    final int thisMonth = Calendar.getInstance().get(Calendar.MONTH);
                    for (int i = 1; i <= Months.length-thisMonth; i++) {
                        months.add(Integer.toString(i));
                        //Spinner.setAdapter(Months[i]);
                        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(PaySlips.this, android.R.layout.simple_spinner_item, Months);
                        Spinner.setAdapter(adapter1);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        payslips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                month = Spinner.getSelectedItem().toString();
                year = Spinner1.getSelectedItem().toString();
                BackGroundpay b = new BackGroundpay();
                b.execute(empid,month,year);
            }
        });

        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv121.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                //finish();
            }
        });

        iv122.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaySlips.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text2.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("employee"))
        {
            text.setText("Employee Dashboard - My pay - PaySlips" + "("+empid+")");
        }
        else if(Text2.equals("hr"))
        {
            text.setText("HR Dashboard - My pay - PaySlips" + "("+empid+")");
        }
        else  if(Text2.equals("admin"))
        {
            text.setText("Admin Dashboard - My pay - PaySlips" + "("+empid+")");
        }
    }
    class BackGroundpay extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String Empid = params[0];
            String month = params[1];
            String year = params[2];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/fetchpayslips.php");
                String urlParams = "empid=" + Empid + "&month=" + month+ "&year=" + year;

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
            // String err=null;

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_pay = root.getJSONObject("user_pay");
                EMPID = user_pay.getString("empid");
                MONTH = user_pay.getString("month");
                YEAR = user_pay.getString("year");
                PDFURL =user_pay.getString("url");

                Intent i = new Intent(PaySlips.this, PayDocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("url",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(PaySlips.this,"Related data is not there", Toast.LENGTH_SHORT).show();
            }

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

                Intent i = new Intent(PaySlips.this, QueryForm.class);
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

                Toast.makeText(PaySlips.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
