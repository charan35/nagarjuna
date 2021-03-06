package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

public class Interviews_dashboard extends AppCompatActivity {

    CardView walkin,reference,scheduled,offcompus,interview_history;
    android.support.v7.widget.Toolbar toolbar;
    TextView empid,text,text1,admin,admin1;
    String Empid,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    LinearLayout lq,lq1;
    ImageView iv25,iv251;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_interviews_dashboard);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        walkin = (CardView) findViewById(R.id.iv1);

        reference = (CardView) findViewById(R.id.iv2);
        scheduled = (CardView) findViewById(R.id.iv3);

        offcompus = (CardView) findViewById(R.id.iv4);
        interview_history = (CardView) findViewById(R.id.iv5);

        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        admin = (TextView)findViewById(R.id.admin);
        admin1 = (TextView)findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);

        Empid = getIntent().getStringExtra("empid");
        Text = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        text.setText(Text);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Interviews Dashboard");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Interviews Dashboard");
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
                Intent intent = new Intent(Interviews_dashboard.this,HrDashboard.class);
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
                Intent intent = new Intent(Interviews_dashboard.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        walkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Interviews_dashboard.this,Add_walkin.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

        reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Interviews_dashboard.this,Add_reference.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

        scheduled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(Interviews_dashboard.this,Add_scheduled.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

        offcompus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(Interviews_dashboard.this,Add_offcompus.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);

            }
        });
        interview_history.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(Interviews_dashboard.this,Interview_history.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);

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
            Empid = empid.getText().toString();
            Text1 = text1.getText().toString();
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

                Intent i = new Intent(Interviews_dashboard.this, QueryForm.class);
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

                Toast.makeText(Interviews_dashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }
}
