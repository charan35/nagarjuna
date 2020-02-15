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

public class ReimbursementHr extends AppCompatActivity {

    TextView reimbursement,text1,text2,hr;
    android.widget.Spinner Spinner,Spinner1,type,reMonth,reYear;
    TextView empId, firstName;
    String empid, firstname,month,year,category,Text1,Text2;
    String FIRSTNAME = null;
    String EMPID = null,MONTH=null,YEAR=null,CATEGORY=null,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    String PDFURL=null;
    ImageView iv25;
    TextView reim,employee;
    String Employee;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_reimbursement_hr);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reimbursement = (TextView) findViewById(R.id.reimbursement);
        empId = (TextView) findViewById(R.id.tvEmpID);
        firstName = (TextView) findViewById(R.id.tvFirstName);
        Spinner =(android.widget.Spinner)findViewById(R.id.monthspinner);
        Spinner1 =(android.widget.Spinner)findViewById(R.id.yearspinner);
        type =(android.widget.Spinner)findViewById(R.id.type);
        reMonth =(android.widget.Spinner)findViewById(R.id.remonth);
        reYear =(android.widget.Spinner)findViewById(R.id.reyear);
        text1=(TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid2);
        iv25 = (ImageView) findViewById(R.id.iv25);
        reim = (TextView) findViewById(R.id.reim);
        hr = (TextView) findViewById(R.id.hr);
        employee = (TextView) findViewById(R.id.text);

        Text1 = getIntent().getStringExtra("message1");
        text1.setText(Text1);

        employee.setText("hr");
        empid = getIntent().getStringExtra("empid");
        firstname = getIntent().getStringExtra("firstname");
        empId.setText(empid);
        firstName.setText(firstname);
        text2.setText("HR Dashboard - Employee List(Re-imbursement)-"+empid);

        reimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = type.getSelectedItem().toString();
                if(text.equals("Mobile")){
                    Employee = employee.getText().toString();
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundmoble b = new BackGroundmoble();
                    b.execute(empid, category, month, year);
                }
                else if(text.equals("Conveyances")){
                    Employee = employee.getText().toString();
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundConveyances b = new BackGroundConveyances();
                    b.execute(empid, category, month, year);
                }
                else if(text.equals("General Expenses")){
                    Employee = employee.getText().toString();
                    empid = empId.getText().toString();
                    category = type.getSelectedItem().toString();
                    month = reMonth.getSelectedItem().toString();
                    year = reYear.getSelectedItem().toString();
                    BackGroundgeneral b = new BackGroundgeneral();
                    b.execute(empid, category, month, year);
                }
            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReimbursementHr.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        reim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReimbursementHr.this,HrReimbursement.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
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

                Intent i = new Intent(ReimbursementHr.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("category",CATEGORY);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Employee);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(ReimbursementHr.this,"Related Document is not there", Toast.LENGTH_SHORT).show();
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

                Intent i = new Intent(ReimbursementHr.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("category",CATEGORY);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Employee);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(ReimbursementHr.this,"Related Document is not there", Toast.LENGTH_SHORT).show();
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

            try {
                JSONObject root = new JSONObject(s);
                JSONObject admin_reimbursement = root.getJSONObject("admin_reimbursement");
                EMPID = admin_reimbursement.getString("empid");
                CATEGORY = admin_reimbursement.getString("category");
                MONTH = admin_reimbursement.getString("month");
                YEAR = admin_reimbursement.getString("year");
                PDFURL =admin_reimbursement.getString("PdfURL");

                Intent i = new Intent(ReimbursementHr.this, Reimbursementdocuments.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("category",CATEGORY);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Employee);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(ReimbursementHr.this,"Related Document is not there", Toast.LENGTH_SHORT).show();
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
            empid = empId.getText().toString();
            Text2 = text2.getText().toString();
            Text1 = text1.getText().toString();
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

                Intent i = new Intent(ReimbursementHr.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text2);
                i.putExtra("message1", Text1);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ReimbursementHr.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }


}
