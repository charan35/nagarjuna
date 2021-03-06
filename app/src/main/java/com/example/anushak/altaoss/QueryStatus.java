package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class QueryStatus extends AppCompatActivity {

    TextView empid,email,query,description,text,text1;
    Button submit;
    RadioGroup radiogroup;
    String Empid,Email,Query,Gender,QueryDescription,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    android.support.v7.widget.Toolbar toolbar;
    HorizontalScrollView lq,lq1;
    ImageView iv25,iv251;
    TextView dashboard,dashboard1,list,list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_status);

        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        email = (TextView)findViewById(R.id.email);
        query = (TextView)findViewById(R.id.query);
        description = (TextView)findViewById(R.id.QueryDescription);
        submit = (Button)findViewById(R.id.submit);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        list = (TextView) findViewById(R.id.list);
        list1 = (TextView) findViewById(R.id.list1);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Empid = getIntent().getStringExtra("Empid");
        Email = getIntent().getStringExtra("Email");
        Query = getIntent().getStringExtra("ReasonName");
        QueryDescription = getIntent().getStringExtra("Activityname");
        Text = getIntent().getStringExtra("employee");
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);

        empid.setText(Empid);
        email.setText(Email);
        query.setText(Query);
        description.setText(QueryDescription);
        text.setText(Text);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Empid = empid.getText().toString();
                Email = email.getText().toString();
                Query = query.getText().toString();
                Gender = ((RadioButton) findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString().trim();

                new Update(QueryStatus.this,0).execute(Empid,Email,Query,Gender);
            }
        });

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryStatus.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryStatus.this,QueryDashboard.class);
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

        dashboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryStatus.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryStatus.this,QueryDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Queries List - Query Status Update("+Empid+")");
        }
        else if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Queries List - Query Status Update("+Empid+")");
        }
    }

    class Update extends AsyncTask<String, Void, String>

    {
        public Update (Context context, int flag)
        {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String empid = (String) arg0[0];
                String email = (String) arg0[1];
                String query = (String) arg0[2];
                String status = (String) arg0[3];

                String link = "http://altaitsolutions.com/Altadatabase/querystatusupdate.php";
                String data = URLEncoder.encode("Empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("ReasonName", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8");
                data += "&" + URLEncoder.encode("Status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {
                return new String("Expection: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(),"Data has been updated", Toast.LENGTH_LONG).show();


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

                Intent i = new Intent(QueryStatus.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text1);
                // i.putExtra("message1", Text1);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(QueryStatus.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
