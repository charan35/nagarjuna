package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
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

public class RewardsSubmit extends AppCompatActivity {

    TextView firstname,middlename,lastname,empid,department,text,dashboard,dashboard1,list,list1,text1,reward;
    Button submit;
    android.support.v7.widget.Toolbar toolbar;
    String Empid,Firstname,Middlename,Lastname,Department,Category,Type,Description,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,Reward;
    Spinner category,type;
    EditText description;
    HorizontalScrollView lq,lq1;
    ImageView iv25,iv251;
    TextView notificationtitle,notificationmessage;
    String NotificationTitle,NotificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_rewards_submit);

        firstname = (TextView)findViewById(R.id.firstname);
        middlename = (TextView)findViewById(R.id.middlename);
        lastname = (TextView)findViewById(R.id.lastname);
        empid = (TextView)findViewById(R.id.empid);
        department = (TextView)findViewById(R.id.department);
        submit = (Button)findViewById(R.id.submit);
        category = (Spinner)findViewById(R.id.category);
        type = (Spinner)findViewById(R.id.type);
        description = (EditText)findViewById(R.id.description);
        text = (TextView) findViewById(R.id.text);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        list = (TextView) findViewById(R.id.list);
        list1 = (TextView) findViewById(R.id.list1);
        text1 = (TextView)findViewById(R.id.query);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notificationtitle = (TextView) findViewById(R.id.notificationtitle);
        notificationmessage = (TextView) findViewById(R.id.notificationmessage);
        reward = (TextView) findViewById(R.id.reward);

         Firstname =  getIntent().getStringExtra("firstname");
         Middlename =  getIntent().getStringExtra("middlename");
         Lastname =  getIntent().getStringExtra("lastname");
         Empid =  getIntent().getStringExtra("empid");
         Department =  getIntent().getStringExtra("department");
         Text =  getIntent().getStringExtra("employee");

        notificationtitle.setText("Rewards Alert");
        notificationmessage.setText(Empid+" got a reward.");

        lastname.setText(Lastname+"."+Firstname+" "+Middlename);
        empid.setText(Empid);
        text.setText(Text);
        department.setText(Department);
        reward.setText("Reward");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationTitle = notificationtitle.getText().toString();
                NotificationMessage = notificationmessage.getText().toString();
                Empid=empid.getText().toString();
                Lastname = lastname.getText().toString();
                Department = department.getText().toString();
                Category= category.getSelectedItem().toString();
                Type = type.getSelectedItem().toString();
                Description = description.getText().toString();
                Reward = reward.getText().toString();
                if (TextUtils.isEmpty(Description)) {
                    description.setError("Please enter Description");
                    description.requestFocus();
                    return;
                }

                BackGround7 b = new BackGround7();
                b.execute(Empid, Lastname, Department, Category, Type, Description,Reward);

                BackGroundNotification b1 = new BackGroundNotification();
                b1.execute(NotificationTitle,NotificationMessage);
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
                Intent intent = new Intent(RewardsSubmit.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardsSubmit.this,RewardsUpdate.class);
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
                Intent intent = new Intent(RewardsSubmit.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        list1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardsSubmit.this,RewardsUpdate.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Rewards(Emp List) - Update("+Empid+")");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Rewards(Emp List) - Update("+Empid+")");
        }

    }
    class BackGround7 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String projectname = params[1];
            String notes = params[2];
            String taskstatus = params[3];
            String updatedby = params[4];
            String date1 = params[5];
            String reward = params[6];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/employee_new_update.php");
                String urlParams = "empid="+empid+"&projectname="+projectname+"&notes="+notes+"&taskstatus="+taskstatus+"&updatedby="+updatedby+"&date="+date1+"&Type="+reward;
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
            /*if (s.equals("")) {
                s = "Data saved successfully.";
            }
            Toast.makeText(RewardsSubmit.this, s, Toast.LENGTH_LONG).show();*/
        }
    }

    class BackGroundNotification extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String message = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/notification.php");
                String urlParams = "title="+title+"&message="+message;
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
            Toast.makeText(RewardsSubmit.this, s, Toast.LENGTH_LONG).show();
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

                Intent i = new Intent(RewardsSubmit.this, QueryForm.class);
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

                Toast.makeText(RewardsSubmit.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
