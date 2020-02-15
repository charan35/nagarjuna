package com.example.anushak.altaoss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class NotificationUpdate extends AppCompatActivity {

    TextView title,message,date,dashboard,notification,empid,text;
    EditText ettitle,etmessage,etdate;
    String Title,Message,Date,Empid,Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    Button edit,delete,update;
    ImageView iv25;
    android.support.v7.widget.Toolbar toolbar;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_update);

        title = (TextView)findViewById(R.id.title);
        message = (TextView)findViewById(R.id.message);
        date = (TextView)findViewById(R.id.date);
        ettitle = (EditText) findViewById(R.id.ettitle);
        etmessage = (EditText) findViewById(R.id.etmessage);
        etdate = (EditText) findViewById(R.id.etdate);
        edit = (Button)findViewById(R.id.edit);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv25 = (ImageView)findViewById(R.id.iv25);
        dashboard = (TextView)findViewById(R.id.dashboard);
        notification= (TextView)findViewById(R.id.notification);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        Title = getIntent().getStringExtra("title");
        Message = getIntent().getStringExtra("message");
        Date = getIntent().getStringExtra("Date");

        title.setText(Title);
        message.setText(Message);
        date.setText(Date);

        ettitle.setText(Title);
        etmessage.setText(Message);
        etdate.setText(Date);
        text.setText("Admin Dashboard - Notifications Dashboard - Update Notification("+Title+")");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setVisibility(8);
                ettitle.setVisibility(0);

                message.setVisibility(8);
                etmessage.setVisibility(0);

                date.setVisibility(8);
                etdate.setVisibility(0);

                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();

                title.setText(Title);
                message.setText(Message);
                date.setText(Date);

                title.setVisibility(0);
                ettitle.setVisibility(8);

                message.setVisibility(0);
                etmessage.setVisibility(8);

                date.setVisibility(0);
                etdate.setVisibility(8);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = ettitle.getText().toString();
                Message = etmessage.getText().toString();
                Date = etdate.getText().toString();
                BackGrounddelete b = new BackGrounddelete();
                b.execute(Title,Message,Date);
            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationUpdate.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationUpdate.this,NotificationsDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }



    public void Update()
    {
        Title = ettitle.getText().toString();
        Message = etmessage.getText().toString();
        Date = etdate.getText().toString();

        new Update(this,0).execute(Title,Message,Date);
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
                String title = (String) arg0[0];
                String message = (String) arg0[1];
                String Date = (String) arg0[2];

                String link = "http://altaitsolutions.com/Altadatabase/update_notifications.php";

                String data = URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
                data += "&" + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
                data += "&" + URLEncoder.encode("Date", "UTF-8") + "=" + URLEncoder.encode(Date, "UTF-8");

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

    class BackGrounddelete extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String message = params[1];
            String Date = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/delete_notification.php");
                String urlParams = "title=" + title+ "&message=" + message+ "&Date=" + Date;

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
            Toast.makeText(getApplicationContext(),"Data has been Deleted", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NotificationUpdate.this,NotificationsDashboard.class);
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

                Intent i = new Intent(NotificationUpdate.this, QueryForm.class);
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

                Toast.makeText(NotificationUpdate.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
