package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.Calendar;

public class ForgotPassword extends AppCompatActivity {

    EditText empid, email,dob,answer1;
    String empID, emailID,dateOfBirth,Answer,Security,Text;
    Spinner securityquestions;
    String EMAIL=null, DOB=null, SECURITY=null, ANSWER1=null,EMPID=null,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    Context ctx=this;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo;
    TextView text;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_forgot_password);
        empid = (EditText) findViewById(R.id.forgot_id);
        email= (EditText) findViewById(R.id.forgot_email);
        dob = (EditText) findViewById(R.id.forgot_dob);
        answer1=(EditText)findViewById(R.id.forgotanswer);
        securityquestions=(Spinner)findViewById(R.id.securityquestion);
        text = (TextView)findViewById(R.id.text);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (ImageView)findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ForgotPassword.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }
    public void forgotsecuritybtn(View v){
        empID = empid.getText().toString();
        emailID = email.getText().toString();
        dateOfBirth = dob.getText().toString();
        Security = securityquestions.getSelectedItem().toString();
        Answer =answer1.getText().toString();
        BackGround b = new BackGround();
        b.execute(empID,emailID,dateOfBirth,Security,Answer);

        if (TextUtils.isEmpty(emailID)) {
            email.setError("Please enter Email");
            email.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
            email.setError("Please enter valid Email");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(empID)) {
            empid.setError("Please enter EmployeeID");
            empid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(dateOfBirth)) {
            dob.setError("Please enter DateOfBirth");
            dob.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Answer)) {
            answer1.setError("Please enter Answer");
            answer1.requestFocus();
            return;
        }
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String email = params[1];
            String dob = params[2];
            String security = params[3];
            String answer = params[4];
            String data="";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/password.php");
                String urlParams = "empid="+empid+"&personalemail="+email+"&dob="+dob+"&security="+security+"&answer="+answer;
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

            //  String err = null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                EMPID = user_data.getString("empid");
                EMAIL = user_data.getString("personalemail");
                DOB = user_data.getString("dob");
                SECURITY = user_data.getString("security");
                ANSWER1 = user_data.getString("answer");


                Intent i = new Intent(ctx, CreatePassword.class);
                i.putExtra("empid", EMPID);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();
                //   err = "Exception: "+e.getMessage();

                Toast.makeText(ctx,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}


