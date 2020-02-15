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
import android.widget.EditText;
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

public class CreatePayslips extends AppCompatActivity {

    TextView admin,departments,emplist,text;
    ImageView iv25;
    android.support.v7.widget.Toolbar toolbar;

    EditText Name, EmployeeID, Designation, Payperiod, Location, Department, PAN, UAN, PaidDate, Account, Bankname, Earnings, Deductions, Adjustments, Totalamount, Basicamount, VariableDA, Statutory, Prefallowances, ESIContribution, PFContribution, Proftax, Nettotal, Comptotal, Grosstotal;
    TextView Grosssalary, Companydeductions, Netsalary,midname,lastname;
    Button createpayslip;

    String EMPLOYEEID,FIRSTNAME,MIDDLENAME,LASTNAME,DESIGNATION,DEPARTMENT,ACCOUNTNO,Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_create_payslips);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        admin = (TextView)findViewById(R.id.admin);
        departments = (TextView)findViewById(R.id.departments);
        emplist = (TextView)findViewById(R.id.emplist);
        iv25 = (ImageView) findViewById(R.id.iv25);

        text = (TextView) findViewById(R.id.text);
        Name = (EditText) findViewById(R.id.payname);
        EmployeeID = (EditText) findViewById(R.id.payempid);
        Designation = (EditText) findViewById(R.id.paydesg);
        Payperiod = (EditText) findViewById(R.id.payperiod);
        Location = (EditText) findViewById(R.id.paylocation);
        Department = (EditText) findViewById(R.id.payDept);
        PAN = (EditText) findViewById(R.id.paypan);
        UAN = (EditText) findViewById(R.id.payuan);
        PaidDate = (EditText) findViewById(R.id.paypaiddate);
        Account = (EditText) findViewById(R.id.payaccount);
        Bankname = (EditText) findViewById(R.id.paybankname);
        Earnings = (EditText) findViewById(R.id.payearning);
        Deductions = (EditText) findViewById(R.id.paydeduction);
        Adjustments = (EditText) findViewById(R.id.payadjustment);
        Totalamount = (EditText) findViewById(R.id.paytotal);
        Basicamount = (EditText) findViewById(R.id.paybasicamount);
        VariableDA = (EditText) findViewById(R.id.payvariable);
        Statutory = (EditText) findViewById(R.id.paystatutory);
        Prefallowances = (EditText) findViewById(R.id.payprefallowances);
        Grosstotal = (EditText) findViewById(R.id.paygrosstotal);
        ESIContribution = (EditText) findViewById(R.id.payesicontribution);
        PFContribution = (EditText) findViewById(R.id.payepfcontribution);
        Proftax = (EditText) findViewById(R.id.payproftax);
        Comptotal = (EditText) findViewById(R.id.paydeductiontotal);
        Nettotal = (EditText) findViewById(R.id.paynettotal);
        Netsalary = (TextView) findViewById(R.id.paynetsalary);
        Companydeductions = (TextView) findViewById(R.id.paycompdeductions);
        Grosssalary = (TextView) findViewById(R.id.paygrosssalary);
        createpayslip = (Button) findViewById(R.id.paycreatepayslip);
        midname = (TextView)findViewById(R.id.midname);
        lastname = (TextView)findViewById(R.id.lastname);

        EMPLOYEEID = getIntent().getStringExtra("empid");
        FIRSTNAME = getIntent().getStringExtra("firstname");
        MIDDLENAME = getIntent().getStringExtra("middlename");
        LASTNAME = getIntent().getStringExtra("lastname");
        DESIGNATION = getIntent().getStringExtra("designation");
        DEPARTMENT = getIntent().getStringExtra("department");
        ACCOUNTNO = getIntent().getStringExtra("accountno");
        EmployeeID.setText(EMPLOYEEID);
        Name.setText(FIRSTNAME+MIDDLENAME+"  "+LASTNAME);
        Designation.setText(DESIGNATION);
        Department.setText(DEPARTMENT);
        Account.setText(ACCOUNTNO);
        text.setText("Hr - DepartmentsList - EmpList - Payslip("+EMPLOYEEID+")");

        createpayslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePayslips.this, Popup.class);
                intent.putExtra("EmployeeID",EMPLOYEEID);
                startActivity(intent);
            }
        });


        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePayslips.this, HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePayslips.this, HrPayslips.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        emplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePayslips.this, EmployeeList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }

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
            Text = text.getText().toString();
            EMPLOYEEID = EmployeeID.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(EMPLOYEEID);
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

                Intent i = new Intent(CreatePayslips.this, QueryForm.class);
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

                Toast.makeText(CreatePayslips.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
