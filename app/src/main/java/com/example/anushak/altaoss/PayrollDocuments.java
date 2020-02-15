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

public class PayrollDocuments extends AppCompatActivity {

    TextView payslips,reimbursement,text,text1;
    android.widget.Spinner Spinner,Spinner1,type,reMonth,reYear;
    TextView empId, firstName;
    String empid, firstname,month,year,category,Text,Text1;
    String FIRSTNAME = null;
    ImageView iv12;
    TextView admin,emppayroll;
    String EMPID = null,MONTH=null,YEAR=null,CATEGORY=null,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    String PDFURL=null;
    TextView text2;
    String Text2;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_payroll_documents);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        payslips = (TextView) findViewById(R.id.paySlips);
        reimbursement = (TextView) findViewById(R.id.reimbursement);
        empId = (TextView) findViewById(R.id.tvEmpID);
        firstName = (TextView) findViewById(R.id.tvFirstName);
        Spinner =(android.widget.Spinner)findViewById(R.id.monthspinner);
        Spinner1 =(android.widget.Spinner)findViewById(R.id.yearspinner);
        type =(android.widget.Spinner)findViewById(R.id.type);
        reMonth =(android.widget.Spinner)findViewById(R.id.remonth);
        reYear =(android.widget.Spinner)findViewById(R.id.reyear);
        text = (TextView)findViewById(R.id.Grid2);
        text1 = (TextView)findViewById(R.id.Grid1);
        iv12 = (ImageView) findViewById(R.id.iv12);
        admin = (TextView) findViewById(R.id.admin);
        emppayroll = (TextView) findViewById(R.id.emppayroll);
        text2 = (TextView) findViewById(R.id.text2);

        empid = getIntent().getStringExtra("empid");
        firstname = getIntent().getStringExtra("firstname");
        Text2 = getIntent().getStringExtra("employee");

        empId.setText(empid);
        firstName.setText(firstname);
        text2.setText(Text2);
        text.setText("Admin - Employee Payroll - "+empid+"(payroll)");

       /* ArrayAdapter<CharSequence> ar = ArrayAdapter.createFromResource(this, R.array.Expenses, android.R.layout.simple_list_item_1);
        ar.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        type.setAdapter(ar);*/
        iv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayrollDocuments.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        emppayroll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayrollDocuments.this,EmpPayroll.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
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

        reimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = type.getSelectedItem().toString();
                if (text.equals("Mobile")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundmoble b = new BackGroundmoble();
                    b.execute(empid, category, month, year);
                }
                if (text.equals("General Expenses")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundgeneral b = new BackGroundgeneral();
                    b.execute(empid, category, month, year);
                }
                if (text.equals("Conveyances")){
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundConveyances b = new BackGroundConveyances();
                    b.execute(empid, category, month, year);
                }
            }
        });
    }

    class BackGroundConveyances extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String category =params[1];
            String month = params[2];
            String year = params[3];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/fetchconveyances.php");
                String urlParams = "empid=" + empid + "&category=" + category + "&month=" + month + "&year=" + year;

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
                JSONObject admin_conveyances = root.getJSONObject("admin_conveyances");
                EMPID = admin_conveyances.getString("empid");
                CATEGORY = admin_conveyances.getString("category");
                MONTH = admin_conveyances.getString("month");
                YEAR = admin_conveyances.getString("year");
                PDFURL =admin_conveyances.getString("PdfURL");

                Intent i = new Intent(PayrollDocuments.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("category",CATEGORY);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(PayrollDocuments.this,"Related data is not there", Toast.LENGTH_SHORT).show();
            }

        }
    }


    class BackGroundgeneral extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String category =params[1];
            String month = params[2];
            String year = params[3];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/fetchgeneral.php");
                String urlParams = "empid=" + empid + "&category=" + category + "&month=" + month + "&year=" + year;

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
                JSONObject admin_conveyances = root.getJSONObject("admin_conveyances");
                EMPID = admin_conveyances.getString("empid");
                CATEGORY = admin_conveyances.getString("category");
                MONTH = admin_conveyances.getString("month");
                YEAR = admin_conveyances.getString("year");
                PDFURL =admin_conveyances.getString("PdfURL");

                Intent i = new Intent(PayrollDocuments.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("category",CATEGORY);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(PayrollDocuments.this,"Related data is not there", Toast.LENGTH_SHORT).show();
            }

        }
    }


    class BackGroundmoble extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String category =params[1];
            String month = params[2];
            String year = params[3];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/fetchreimbursement.php");
                String urlParams = "empid=" + empid + "&category=" + category + "&month=" + month + "&year=" + year;

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
                JSONObject admin_reimbursement = root.getJSONObject("admin_reimbursement");
                EMPID = admin_reimbursement.getString("empid");
                CATEGORY = admin_reimbursement.getString("category");
                MONTH = admin_reimbursement.getString("month");
                YEAR = admin_reimbursement.getString("year");
                PDFURL =admin_reimbursement.getString("PdfURL");

                Intent i = new Intent(PayrollDocuments.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("category",CATEGORY);
                i.putExtra("employee",Text2);

                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(PayrollDocuments.this,"Related data is not there", Toast.LENGTH_SHORT).show();
            }

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

                Intent i = new Intent(PayrollDocuments.this, EmpPayrollDoc.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("url",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(PayrollDocuments.this,"Related data is not there", Toast.LENGTH_SHORT).show();
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
        if(id == R.id.query)
        {
            Text = text.getText().toString();
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

                Intent i = new Intent(PayrollDocuments.this, QueryForm.class);
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

                Toast.makeText(PayrollDocuments.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
