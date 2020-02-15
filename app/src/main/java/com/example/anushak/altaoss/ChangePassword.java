package com.example.anushak.altaoss;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.Spinner;
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

public class  ChangePassword extends AppCompatActivity {

    String empid1;
    TextView empID,text;
    EditText password;
    EditText c_password;
    Button submit;
    String password1,c_password1,Text;

    Spinner security;
    EditText answer;
    String security1,answer1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    Context context;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_change_password);

        empID = (TextView)findViewById(R.id.password_id);
        text = (TextView)findViewById(R.id.text);
        empid1 = getIntent().getStringExtra("empid");
        empID.setText(empid1);
        text.setText("Change Password  ("+empid1+")");

        password = (EditText)findViewById(R.id.newpassword);
        c_password =(EditText)findViewById(R.id.newconfirmpassword);
        submit =(Button)findViewById(R.id.confirmpassword);

        security = (Spinner) findViewById(R.id.security);
        answer = (EditText) findViewById(R.id.answer);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (ImageView)findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void Update(View v)
    {

        empid1 = empID.getText().toString();
        security1 = security.getSelectedItem().toString();
        answer1 = answer.getText().toString();
        password1 = password.getText().toString();
        c_password1 = c_password.getText().toString();

        if (TextUtils.isEmpty(password1)) {
            password.setError("Please enter Password");
            password.requestFocus();
            return;
        }
        if (!c_password1.equals(password1)) {
            c_password.setError("Password Does not Match");
            c_password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(answer1)) {
            answer.setError("Please enter Answer");
            answer.requestFocus();
            return;
        }


        new Update(this,0).execute(empid1,security1,answer1,password1);

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
                String empid1 = (String) arg0[0];
                String security1 = (String) arg0[1];
                String answer1 = (String) arg0[2];
                //String answer2 = (String) arg0[3];
                String password1 = (String) arg0[3];


                String link = "http://altaitsolutions.com/Altadatabase/changepassword.php";
                String data = URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid1, "UTF-8");
                data += "&" + URLEncoder.encode("security", "UTF-8") + "=" + URLEncoder.encode(security1, "UTF-8");
                data += "&" + URLEncoder.encode("answer", "UTF-8") + "=" + URLEncoder.encode(answer1, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password1, "UTF-8");

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
            empid1 = empID.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(empid1);
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

                Intent i = new Intent(ChangePassword.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message",Text);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ChangePassword.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
