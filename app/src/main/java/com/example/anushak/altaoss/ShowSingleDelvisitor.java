package com.example.anushak.altaoss;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class ShowSingleDelvisitor extends AppCompatActivity {

    HttpParse httpParse = new HttpParse();
    ProgressDialog pDialog;

    // Http Url For Filter Student Data from Id Sent from previous activity.
    String HttpURL = "https://192.168.1.59/Androiduploadimage/Filtervisitordata.php";

    // Http URL for delete Already Open Student Record.
    String HttpUrlDeleteRecord = "https://192.168.1.59/Androiduploadimage/Deletevisitor.php";

    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    String FinalJSonObject ;
    TextView firstname, lastname, phone, email, contactperson, companyname, companybranch, reasonforvisit, dateofvisit, personalid, typeofvisit, empid;
    String FirstName, LastName, PhoneNum, EmailAddress, ContactPerson, CompanyName, Companybranch, Reasonforvisit, DateofVisit, Personalid, Typeofvisit, Empid;
    Button Edit, Delete;
    String TempItem;
    ProgressDialog progressDialog2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_show_single_delvisitor);

        firstname = findViewById(R.id.fname);
        lastname = findViewById(R.id.lname);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        contactperson = findViewById(R.id.contactperson);
        companyname = findViewById(R.id.companyname);
        companybranch = findViewById(R.id.companybranch);
        reasonforvisit = findViewById(R.id.reasonforvisit);
        dateofvisit = findViewById(R.id.dateofvisit);
        personalid = findViewById(R.id.personalid);
        typeofvisit = findViewById(R.id.typeofvisit);
        empid = findViewById(R.id.empid);

        Edit = (Button)findViewById(R.id.visedit);
        Delete = (Button)findViewById(R.id.visdelete);

        //Receiving the ListView Clicked item value send by previous activity.
        //Empid = getIntent().getStringExtra("ListViewValue");

        FirstName = getIntent().getStringExtra("fname");
        LastName = getIntent().getStringExtra("lname");
        PhoneNum = getIntent().getStringExtra("phone");
        ContactPerson = getIntent().getStringExtra("contactperson");
        EmailAddress = getIntent().getStringExtra("email");
        CompanyName = getIntent().getStringExtra("companyname");
        Companybranch = getIntent().getStringExtra("companybranch");
        Reasonforvisit = getIntent().getStringExtra("reasonforvisit");
        DateofVisit = getIntent().getStringExtra("dateofvisit");
        Personalid = getIntent().getStringExtra("personalid");
        Typeofvisit = getIntent().getStringExtra("typeofvisit");
        Empid = getIntent().getStringExtra("empid");

        firstname.setText(FirstName);
        lastname.setText(LastName);
        phone.setText(PhoneNum);
        email.setText(EmailAddress);
        contactperson.setText(ContactPerson);
        companyname.setText(CompanyName);
        companybranch.setText(Companybranch);
        reasonforvisit.setText(Reasonforvisit);
        dateofvisit.setText(DateofVisit);
        personalid.setText(Personalid);
        typeofvisit.setText(Typeofvisit);
        empid.setText(Empid);

        //Calling method to filter Student Record and open selected record.


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ShowSingleDelvisitor.this,VisitorUpdateActivity.class);

                i.putExtra("empid", TempItem);
                i.putExtra("fname", FirstName);
                i.putExtra("lname", LastName);
                i.putExtra("phone", PhoneNum);
                i.putExtra("email", EmailAddress);
                i.putExtra("contactperson", ContactPerson);
                i.putExtra("companyname", CompanyName);
                i.putExtra("companybranch", Companybranch);
                i.putExtra("reasonforvisit", Reasonforvisit);
                i.putExtra("dateofvisit",DateofVisit);
                i.putExtra("personalid",Personalid);
                i.putExtra("typeofvisit",Typeofvisit);
                i.putExtra("empid",Empid);

                startActivity(i);

                // Finishing current activity after opening next activity.
                finish();

            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new AlertDialog.Builder(ShowSingleDelvisitor.this)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete?")
                        .setNegativeButton(R.string.no, null)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg1, int arg0) {

                                FirstName =firstname.getText().toString();
                                LastName =lastname.getText().toString();
                                PhoneNum =phone.getText().toString();
                                EmailAddress =email.getText().toString();
                                ContactPerson =contactperson.getText().toString();
                                Empid =empid.getText().toString();
                                CompanyName =companyname.getText().toString();
                                Companybranch =companybranch.getText().toString();
                                Reasonforvisit =reasonforvisit.getText().toString();
                                DateofVisit =dateofvisit.getText().toString();
                                Personalid =personalid.getText().toString();
                                Typeofvisit =typeofvisit.getText().toString();
                                Update b = new Update();
                                b.execute(FirstName, LastName, PhoneNum, EmailAddress, ContactPerson,Empid, CompanyName, Companybranch, Reasonforvisit, DateofVisit, Personalid, Typeofvisit);
                            }
                        }).create().show();
            }
        });


    }

    class Update extends AsyncTask<String,Void,String>
    {
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
                String email = (String) arg0[3];
                String contactperson = (String) arg0[4];
                String empid = (String) arg0[5];
                String companyname = (String) arg0[6];
                String companybranch = (String) arg0[7];
                String reasonforvisit = (String) arg0[8];
                String dateofvisit = (String) arg0[9];
                String personalid = (String) arg0[10];
                String typeofvisit = (String) arg0[11];
                String link = "http://192.168.1.59/Androiduploadimage/Deletevisitor.php";

                String data = URLEncoder.encode("fname", "UTF-8") + "=" + URLEncoder.encode(firstname, "UTF-8");
                data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" + URLEncoder.encode(lastname, "UTF-8");
                data += "&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("contactperson", "UTF-8") + "=" + URLEncoder.encode(contactperson, "UTF-8");
                data += "&" + URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("companyname", "UTF-8") + "=" + URLEncoder.encode(companyname, "UTF-8");
                data += "&" + URLEncoder.encode("companybranch", "UTF-8") + "=" + URLEncoder.encode(companybranch, "UTF-8");
                data += "&" + URLEncoder.encode("reasonforvisit", "UTF-8") + "=" + URLEncoder.encode(reasonforvisit, "UTF-8");
                data += "&" + URLEncoder.encode("dateofvisit", "UTF-8") + "=" + URLEncoder.encode(dateofvisit, "UTF-8");
                data += "&" + URLEncoder.encode("personalid", "UTF-8") + "=" + URLEncoder.encode(personalid, "UTF-8");
                data += "&" + URLEncoder.encode("typeofvisit", "UTF-8") + "=" + URLEncoder.encode(typeofvisit, "UTF-8");

                URL url;
                url = new URL(link);
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

            Toast.makeText(ShowSingleDelvisitor.this,"Data has been deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(ShowSingleDelvisitor.this, Delvisitors.class);
            startActivity(i);
        }

    }

}
