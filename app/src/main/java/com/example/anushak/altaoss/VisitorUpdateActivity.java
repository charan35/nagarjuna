package com.example.anushak.altaoss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

public class VisitorUpdateActivity extends AppCompatActivity {
    String HttpURL = "https://192.168.1.59/Androiduploadimage/Updatevisitor.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText firstname, lastname, phone, contactperson, email, companyname, companybranch, reasonforvisit, dateofvisit, personalid, typeofvisit, empid;
    Button visupdate;
    String Firstname, Lastname, Phone, Contactperson, Email, Companyname, Companybranch, Reasonforvisit, Dateofvisit, Personalid, Typeofvisit, Empid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_visitor_update);

        firstname = findViewById(R.id.viseditfname);
        lastname = findViewById(R.id.viseditlname);
        phone = findViewById(R.id.viseditphone);
        contactperson = findViewById(R.id.viseditcontactperson);
        email = findViewById(R.id.viseditemail);
        companyname = findViewById(R.id.viseditcompanyname);
        companybranch = findViewById(R.id.viseditcompanyaddress);
        reasonforvisit = findViewById(R.id.viseditreasonforvisit);
        dateofvisit = findViewById(R.id.viseditdateofvisit);
        personalid = findViewById(R.id.viseditpersonalid);
        typeofvisit = findViewById(R.id.visedittypeofvisit);
        empid = findViewById(R.id.viseditempid);

        visupdate = findViewById(R.id.viseditupdate);

        Firstname = getIntent().getStringExtra("fname");
        Lastname = getIntent().getStringExtra("lname");
        Phone = getIntent().getStringExtra("phone");
        Contactperson = getIntent().getStringExtra("contactperson");
        Email = getIntent().getStringExtra("email");
        Companyname = getIntent().getStringExtra("companyname");
        Companybranch = getIntent().getStringExtra("companybranch");
        Reasonforvisit = getIntent().getStringExtra("reasonforvisit");
        Dateofvisit = getIntent().getStringExtra("dateofvisit");
        Personalid = getIntent().getStringExtra("personalid");
        Typeofvisit = getIntent().getStringExtra("typeofvisit");
        Empid = getIntent().getStringExtra("empid");

        firstname.setText(Firstname);
        lastname.setText(Lastname);
        phone.setText(Phone);
        email.setText(Email);
        contactperson.setText(Contactperson);
        companyname.setText(Companyname);
        companybranch.setText(Companybranch);
        reasonforvisit.setText(Reasonforvisit);
        dateofvisit.setText(Dateofvisit);
        personalid.setText(Personalid);
        typeofvisit.setText(Typeofvisit);
        empid.setText(Empid);

        visupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataFromEditText();

                new Update(VisitorUpdateActivity.this,0).execute(Firstname, Lastname, Phone, Contactperson, Email, Companyname, Companybranch, Reasonforvisit, Dateofvisit, Personalid, Typeofvisit, Empid);
            }
        });
    }

    private void GetDataFromEditText() {
        Firstname =firstname.getText().toString();
        Lastname =lastname.getText().toString();
        Phone =phone.getText().toString();
        Contactperson =contactperson.getText().toString();
        Email =email.getText().toString();
        Companyname =companyname.getText().toString();
        Companybranch =companybranch.getText().toString();
        Reasonforvisit =reasonforvisit.getText().toString();
        Dateofvisit =dateofvisit.getText().toString();
        Personalid =personalid.getText().toString();
        Typeofvisit =typeofvisit.getText().toString();
        Empid =empid.getText().toString();

    }

    class Update extends AsyncTask<String,Void,String>

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
                String firstname = (String) arg0[0];
                String lastname = (String) arg0[1];
                String phone = (String) arg0[2];
                String contactperson = (String) arg0[3];
                String email = (String) arg0[4];
                String companyname = (String) arg0[5];
                String companybranch = (String) arg0[6];
                String reasonforvisit = (String) arg0[7];
                String dateofvisit = (String) arg0[8];
                String personalid = (String) arg0[9];
                String typeofvisit = (String) arg0[10];
                String empid = (String) arg0[11];


                String link = "http://192.168.1.59/Androiduploadimage/Updatevisitor.php";
                String data = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8");
                data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8");
                data += "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                data += "&" + URLEncoder.encode("contactperson", "UTF-8") + "=" + URLEncoder.encode(contactperson, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("companyname", "UTF-8") + "=" + URLEncoder.encode(companyname, "UTF-8");
                data += "&" + URLEncoder.encode("companybranch", "UTF-8") + "=" + URLEncoder.encode(companybranch, "UTF-8");
                data += "&" + URLEncoder.encode("reasonforvisit", "UTF-8") + "=" + URLEncoder.encode(reasonforvisit, "UTF-8");
                data += "&" + URLEncoder.encode("dateofvisit", "UTF-8") + "=" + URLEncoder.encode(dateofvisit, "UTF-8");
                data += "&" + URLEncoder.encode("personalid", "UTF-8") + "=" + URLEncoder.encode(personalid, "UTF-8");
                data += "&" + URLEncoder.encode("typeofvisit", "UTF-8") + "=" + URLEncoder.encode(typeofvisit, "UTF-8");
                data += "&" + URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");

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
            Intent i = new Intent(VisitorUpdateActivity.this, Delvisitors.class);
            startActivity(i);


        }
    }


}
