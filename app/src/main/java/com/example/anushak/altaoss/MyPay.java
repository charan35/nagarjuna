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

public class MyPay extends AppCompatActivity {

    TextView empId,firstName,text;
    Button paySlip,empReimbursement;
    String empid,firstname,Text;
    String FIRSTNAME = null,EMPID = null,OFFICIALEMAIL=null,MANAGERMAIL=null,MANAGERID=null,HRMAIL=null,HRID=null;

    TextView payslips,reimbursement1;
    String Payslips,Reimbursement1;

    LinearLayout lq,lq1,lq2;
    ImageView iv11,iv111,iv112;
    TextView dashboard,dashboard1,dashboard2;
    TextView text1;
    String Text1;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_pay);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        paySlip=(Button)findViewById(R.id.EmpPayslip);
        empReimbursement=(Button)findViewById(R.id.EmpReimbursement);
        firstName=(TextView)findViewById(R.id.EmpName);
        empId = (TextView) findViewById(R.id.EmpID);
        text = (TextView)findViewById(R.id.Grid2);
        text1 = (TextView) findViewById(R.id.text1);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        dashboard = (TextView) findViewById(R.id.dashboard);
        iv11 = (ImageView) findViewById(R.id.iv11);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        iv111 = (ImageView) findViewById(R.id.iv111);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        iv112 = (ImageView) findViewById(R.id.iv112);

        payslips = (TextView) findViewById(R.id.payslips);
        reimbursement1 = (TextView) findViewById(R.id.reimbursement1);

        //text.setText();
        empid = getIntent().getStringExtra("empid");
        firstname=getIntent().getStringExtra("firstname");
        Payslips=getIntent().getStringExtra("payslips");
        Reimbursement1=getIntent().getStringExtra("reimbursement1");
        Text1 = getIntent().getStringExtra("employee");

        empId.setText(empid);
        firstName.setText(firstname);
        payslips.setText(Payslips);
        reimbursement1.setText(Reimbursement1);
        text1.setText(Text1);

        empReimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Reimbursement1 = reimbursement1.getText().toString();
                BackGroundempreimbursement b = new BackGroundempreimbursement();
                b.execute(empid);
            }
        });

        paySlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                Payslips = payslips.getText().toString();
                BackGroundemppay b = new BackGroundemppay();
                b.execute(empid);
            }
        });

        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPay.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPay.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPay.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text1.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text1.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text1.equals("admin")? View.VISIBLE : View.GONE);

        if(Text1.equals("employee"))
        {
            text.setText("Employee Dashboard - Mypay" + "("+empid+")");
        }
        else if(Text1.equals("hr"))
        {
            text.setText("HR Dashboard - Mypay" + "("+empid+")");
        }
        else  if(Text1.equals("admin"))
        {
            text.setText("Admin Dashboard - Mypay" + "("+empid+")");
        }
    }

    class BackGroundemppay extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/pay.php");
                String urlParams = "empid=" + empid;

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

            if(Payslips.equals("YES")) {
                try {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data_profile = root.getJSONObject("user_data_profile");
                    FIRSTNAME = user_data_profile.getString("firstname");
                    EMPID = user_data_profile.getString("empid");

                    Intent i = new Intent(MyPay.this, PaySlips.class);
                    i.putExtra("empid", EMPID);
                    i.putExtra("firstname", FIRSTNAME);
                    i.putExtra("employee", Text1);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyPay.this, "Related data is not there", Toast.LENGTH_SHORT).show();
                }
            }
            else if(Payslips.equals("NO"))
            {
                Toast.makeText(getApplicationContext(),"You dont have permissions to access this", Toast.LENGTH_LONG).show();
            }
        }
    }

    class BackGroundempreimbursement extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/pay.php");
                String urlParams = "empid=" + empid;

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
            if(Reimbursement1.equals("YES")) {
                try {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data_profile = root.getJSONObject("user_data_profile");
                    FIRSTNAME = user_data_profile.getString("firstname");
                    EMPID = user_data_profile.getString("empid");

                    Intent i = new Intent(MyPay.this, EmpReimbursement.class);
                    i.putExtra("empid", EMPID);
                    i.putExtra("employee", Text1);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MyPay.this, "Related data is not there", Toast.LENGTH_SHORT).show();
                }
            }
            else if(Reimbursement1.equals("NO"))
            {
                Toast.makeText(getApplicationContext(),"You dont have permissions to access this", Toast.LENGTH_LONG).show();
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

                Intent i = new Intent(MyPay.this, QueryForm.class);
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

                Toast.makeText(MyPay.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}


