package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
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

public class reference_reg extends AppCompatActivity {
    TextView name, qualification, email, phone,project, reference_name,reference_id,interviewtype,status;
    Button reg;
    String Name, Qualification, Email, Phone,Project,Reference_name,Reference_id,Interviewtype,Status;

    android.support.v7.widget.Toolbar toolbar;
    LinearLayout lq,lq1;
    ImageView iv25,iv251;
    TextView empid,text,text1,admin,admin1,dashboard,dashboard1,form,form1;
    String Empid,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_reference_reg);

        name = (TextView) findViewById(R.id.firstname);
        qualification = (TextView) findViewById(R.id.qualification);
        email = (TextView) findViewById(R.id.register_email);
        phone = (TextView) findViewById(R.id.mobile);
        reference_name = (TextView)findViewById(R.id.referencename);
        reference_id = (TextView)findViewById(R.id.referenceid);
        project = (TextView)findViewById(R.id.project);
        reg = (Button) findViewById(R.id.register_register);
        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        admin = (TextView)findViewById(R.id.admin);
        admin1 = (TextView)findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        form = (TextView)findViewById(R.id.form);
        form1 = (TextView)findViewById(R.id.form1);
        status = (TextView) findViewById(R.id.status);

        interviewtype = (TextView) findViewById(R.id.reference);


        Empid = getIntent().getStringExtra("empid");
        Text = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        text.setText(Text);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Interviews Dashboard - Reference candidates list - New Registration");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Interviews Dashboard - Reference candidates list - New Registration");
        }

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,Interviews_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,Add_reference.class);
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

        admin1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,Interviews_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(reference_reg.this,Add_reference.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = name.getText().toString();
                Qualification = qualification.getText().toString();
                Email = email.getText().toString();
                Phone = phone.getText().toString();
                Reference_name = reference_name.getText().toString();
                Reference_id = reference_id.getText().toString();
                Project = project.getText().toString();
                Status=status.getText().toString();


                Interviewtype = interviewtype.getText().toString();




                if (TextUtils.isEmpty(Name)) {
                    name.setError("Please enter Name");
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Qualification)) {
                    qualification.setError("Please enter Qualification");
                    qualification.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Please enter Email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Phone)) {
                    phone.setError("Please enter Phonenumber");
                    phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Reference_name)) {
                    reference_name.setError("Please enter Reference_name");
                    reference_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Reference_id)) {
                    reference_id.setError("Please Enter Reference_id");
                    reference_id.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(Project)) {
                    project.setError("Please Enter Project Name");
                    project.requestFocus();
                    return;
                }


                BackGround b = new BackGround();
                b.execute(Name, Qualification, Email, Phone, Project, Reference_name, Reference_id,Interviewtype, Status);


            }
        });
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String qualification = params[1];
            String email = params[2];
            String phone = params[3];
            String project = params[4];
            String referencename = params[5];
            String referenceid = params[6];
            String Interviewtype = params[7];
            String status = params[8];


            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/reference_reg.php");
                String urlParams = "name=" + name + "&qualification=" + qualification + "&email=" + email + "&phone=" + phone + "&project=" + project + "&referencename=" + referencename+ "&referenceid=" + referenceid+ "&interviewtype=" + Interviewtype+ "&status=" + status;
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
            Text1=text1.getText().toString();
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

                Intent i = new Intent(reference_reg.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text1);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(reference_reg.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}

