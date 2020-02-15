package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class EmpVisitors extends AppCompatActivity {

    EditText fname, lname, phone, contactperson, companyname, companybranch, whomtocontact, email, reasonforvisit, dateofvisit, personalid,empID;
    String Fname, Lname, Phone, Contactperson, Companyname, Companybranch, Whomtocontact, Email, Reasonforvisit, Dateofvisit, Personalid, personal_id, Typeofvisit,EmpId;
    Spinner Personal_id, typeofvisit;
    Button save;
    DatePickerDialog datePickerDialog;
    HorizontalScrollView lq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_visitors);
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
        empID = (EditText) findViewById(R.id.visempid);
        typeofvisit = (Spinner) findViewById(R.id.spitypevisit);
        lq = (HorizontalScrollView)findViewById(R.id.lq);

        save = (Button) findViewById(R.id.save);

        dateofvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EmpVisitors.this,
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
                EmpId = empID.getText().toString();

                BackGround b = new BackGround();
                b.execute(Fname, Lname, Phone, Contactperson, Companyname, Companybranch, Whomtocontact, Email, Reasonforvisit, Dateofvisit,  personal_id, Personalid, Typeofvisit,EmpId);

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
                String urlParams = "fname=" + fname + "&lname=" + lname + "&phone=" + phone + "&contactperson=" + contactperson + "&companyname=" + companyname + "&companybranch=" + companybranch + "&whomtocontact=" + whomtocontact + "&email=" + email + "&reasonforvisit=" + reasonforvisit + "&dateofvisit=" + dateofvisit + "&personal_id=" + personal_id + "&personalid=" + personalid + "&typeofvisit=" + typeofvisit+ "&empid=" + empid ;
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
}
