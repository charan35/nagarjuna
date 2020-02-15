package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Addvisitor extends AppCompatActivity {

    EditText fname, lname, phone, contactperson, companyname, companybranch, whomtocontact, email, reasonforvisit, dateofvisit, personalid,empId;
    String Fname, Lname, Phone, Contactperson, Companyname, Companybranch, Whomtocontact, Email, Reasonforvisit, Dateofvisit, Personalid, personal_id, Typeofvisit,EmpId;
    Spinner Personal_id, typeofvisit;
    Button save;
    DatePickerDialog datePickerDialog;
    ImageView iv25;
    TextView text,empid,admin,dashboard,text1;
    String Text,Empid,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,Text1;
    android.support.v7.widget.Toolbar toolbar;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv251,iv252;
    TextView dashboard1,dashboard2,admin1,admin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_addvisitor);

        fname = (EditText) findViewById(R.id.visfname);
        lname = (EditText) findViewById(R.id.vislname);
        phone = (EditText) findViewById(R.id.phone);
        contactperson = (EditText) findViewById(R.id.viscontact);
        companyname = (EditText) findViewById(R.id.viscomname);
        companybranch = (EditText) findViewById(R.id.viscombranch);
        whomtocontact = (EditText) findViewById(R.id.viswhomtocontact);
        email = (EditText) findViewById(R.id.visemail);
        reasonforvisit = (EditText) findViewById(R.id.visreasonforvisit);
        dateofvisit = (EditText) findViewById(R.id.visdateofvisit);
        Personal_id = (Spinner) findViewById(R.id.spiidentity);
        personalid = (EditText) findViewById(R.id.vispersonalid);
        empId = (EditText) findViewById(R.id.visempid);
        text1 = (TextView) findViewById(R.id.text1);
        typeofvisit = (Spinner) findViewById(R.id.spitypevisit);
        save = (Button) findViewById(R.id.save);

        lq = (HorizontalScrollView) findViewById(R.id.lq);
        lq1 = (HorizontalScrollView) findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView) findViewById(R.id.lq2);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        iv252 = (ImageView) findViewById(R.id.iv252);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        admin = (TextView)findViewById(R.id.admin);
        admin1 = (TextView)findViewById(R.id.admin1);
        admin2 = (TextView)findViewById(R.id.admin2);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Empid = getIntent().getStringExtra("empid");
        Text1 = getIntent().getStringExtra("employee");
        empid.setText(Empid);
        text1.setText(Text1);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,Admin_Visitor_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv251.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,EmpVisitorsList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        iv252.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addvisitor.this,EmpVisitorsList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text1.equals("admin")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text1.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text1.equals("employee")? View.VISIBLE : View.GONE);

        if(Text1.equals("employee"))
        {
            text.setText("Employee Dashboard - My Visitors - Add Visitor");
        }
        else if(Text1.equals("hr"))
        {
            text.setText("HR Dashboard -  - My Visitors - Add Visitor");
        }
        else if(Text1.equals("admin"))
        {
            text.setText("Admin Dashboard - Visitor Dashboard - Add Visitor");
        }

        dateofvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(Addvisitor.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateofvisit.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fname = fname.getText().toString();
                Lname = lname.getText().toString();
                Phone = phone.getText().toString();
                Contactperson = contactperson.getText().toString();
                Companyname = companyname.getText().toString();
                Companybranch = companybranch.getText().toString();
                Whomtocontact = whomtocontact.getText().toString();
                Email = email.getText().toString();
                Reasonforvisit = reasonforvisit.getText().toString();
                Dateofvisit = dateofvisit.getText().toString();
                personal_id = Personal_id.getSelectedItem().toString();
                Personalid = personalid.getText().toString();
                Typeofvisit = typeofvisit.getSelectedItem().toString();
                EmpId = empId.getText().toString();

                Addvisitor.BackGround b = new Addvisitor.BackGround();
                b.execute(Fname, Lname, Phone, Contactperson, Companyname, Companybranch, Whomtocontact, Email, Reasonforvisit, Dateofvisit, personal_id, Personalid, Typeofvisit,EmpId);

            }
        });

    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String fname = params[0];
            String lname = params[1];
            String phone = params[2];
            String contactperson = params[3];
            String companyname = params[4];
            String companybranch = params[5];
            String whomtocontact = params[6];
            String email = params[7];
            String reasonforvisit = params[8];
            String dateofvisit = params[9];
            String personal_id = params[10];
            String personalid = params[11];
            String typeofvisit = params[12];
            String empid = params[13];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/add_visitor.php");
                String urlParams = "fname=" + fname + "&lname=" + lname + "&phone=" + phone + "&contactperson=" + contactperson + "&companyname=" + companyname + "&companybranch=" + companybranch + "&whomtocontact=" + whomtocontact + "&email=" + email + "&reasonforvisit=" + reasonforvisit + "&dateofvisit=" + dateofvisit + "&personal_id=" + personal_id + "&personalid=" + personalid + "&typeofvisit=" + typeofvisit+ "&empid=" + empid;
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

            if (s.equals("")) {
                s = "Data saved successfully.";
            }
            Toast toast = Toast.makeText(getApplicationContext(),
                    s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
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

                Intent i = new Intent(Addvisitor.this, QueryForm.class);
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

                Toast.makeText(Addvisitor.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
