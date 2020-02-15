package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QueryForm extends AppCompatActivity {

    EditText activityname;
    Button querysubmit;
    String Activityname;
    String email,empid,mmail,mid,hmail,hid,ReasonName,month;
    TextView emailTV,empID,Mmail,Mid,Hmail,Hid,reasonname,Month,Year;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_form);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (ImageView)findViewById(R.id.logo);
        activityname = (EditText)findViewById(R.id.editText5);
        querysubmit= (Button)findViewById(R.id.querysubmit);
        emailTV = (TextView)findViewById(R.id.email);
        empID = (TextView)findViewById(R.id.empid);
        Mid = (TextView)findViewById(R.id.toaddressid1);
        Mmail = (TextView)findViewById(R.id.toaddress1);
        Hid = (TextView)findViewById(R.id.toaddressid2);
        Hmail = (TextView)findViewById(R.id.toaddress2);
        reasonname = (TextView)findViewById(R.id.reasonname);
        Month = (TextView)findViewById(R.id.month);
        Year = (TextView)findViewById(R.id.year);

        empid = getIntent().getStringExtra("empid");
        email = getIntent().getStringExtra("officialemail");
        mid = getIntent().getStringExtra("managerId");
        mmail = getIntent().getStringExtra("projectmanagermail");
        hid = getIntent().getStringExtra("Hrid");
        hmail = getIntent().getStringExtra("Hrmail");
        ReasonName = getIntent().getStringExtra("message");
        month = getIntent().getStringExtra("month");

        reasonname.setText(ReasonName);
        empID.setText(empid);
        emailTV.setText(" "+email);
        Mid.setText(mid);
        Mmail.setText(" "+mmail);
        Hid.setText(hid);
        Hmail.setText(" "+hmail);
        Month.setText("Query Updated");

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        querysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empID.getText().toString();
                email = emailTV.getText().toString();
                mid = Mid.getText().toString();
                mmail = Mmail.getText().toString();
                hid = Hid.getText().toString();
                hmail = Hmail.getText().toString();
                ReasonName = reasonname.getText().toString();
                Activityname = activityname.getText().toString();
                month = Month.getText().toString();
                BackGroundquery b = new BackGroundquery();
                b.execute(empid,email,mid,mmail,hid,hmail,ReasonName,Activityname,month);

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                //i.putExtra(Intent.ACTION_SEND, email);
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{hmail,mmail});
                i.putExtra(Intent.EXTRA_SUBJECT, ReasonName);
                //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
                i.putExtra(Intent.EXTRA_TEXT   , Activityname);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(QueryForm.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }


    class BackGroundquery extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String Empid = params[0];
            String Email = params[1];
            String Managerid = params[2];
            String ManagerMail = params[3];
            String Hrid = params[4];
            String Hrmail = params[5];
            String Reasonname = params[6];
            String ActivityName = params[7];
            String Status = params[8];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/register2.php");
                String urlParams = "Empid="+Empid+"&Email="+Email+"&Managerid="+Managerid+"&ManagerMail="+ManagerMail+"&Hrid="+Hrid+"&Hrmail="+Hrmail+"&Reasonname="+Reasonname+"&ActivityName="+ActivityName+"&Status="+Status;
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
            Toast.makeText(QueryForm.this, s, Toast.LENGTH_LONG).show();
            /*Intent I = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(I);*/
        }
    }
}
