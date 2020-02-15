package com.example.anushak.altaoss;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class My_Task_single extends AppCompatActivity {

    Button submit;
    Context ctx;
    Spinner status;

    TextView name, empid, email, title, date, subject, discription, priority, task_assign,officialemail,department;
    String Name, Empid, Email, Title, Date, Subject, Discription, Priority, Task_assign,Status,Officialemail,Department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__task_single);

        status = (Spinner) findViewById(R.id.status);

        name = (TextView) findViewById(R.id.name);
        empid = (TextView) findViewById(R.id.empid);
        email = (TextView) findViewById(R.id.phonenum);
        title = (TextView) findViewById(R.id.title);
        date = (TextView) findViewById(R.id.date);
        subject = (TextView) findViewById(R.id.subject);
        discription = (TextView) findViewById(R.id.discription);
        priority = (TextView) findViewById(R.id.priority);

        officialemail=(TextView)findViewById(R.id.officialemail);

        task_assign = (TextView) findViewById(R.id.empidlist);
        submit = (Button) findViewById(R.id.submit);


        department=(TextView)findViewById(R.id.department);

        Department=getIntent().getStringExtra("department");
        department.setText(Department);


        Name = getIntent().getStringExtra("name");
        Empid = getIntent().getStringExtra("empid");
        Email = getIntent().getStringExtra("email");
        Title = getIntent().getStringExtra("title");
        Date = getIntent().getStringExtra("date");
        Subject = getIntent().getStringExtra("subject");
        Discription = getIntent().getStringExtra("discription");
        Priority = getIntent().getStringExtra("priority");


        Officialemail=getIntent().getStringExtra("officialemail");
        officialemail.setText(Officialemail);

        name.setText(Name);
        empid.setText(Empid);
        email.setText(Email);
        title.setText(Title);
        date.setText(Date);
        subject.setText(Subject);
        discription.setText(Discription);
        priority.setText(Priority);

        //status update need to do with php


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Update();
                sendEmail();
            }
        });

    }

//update code for assign task

    public void Update()
    {

        Empid = empid.getText().toString();

        Status = status.getSelectedItem().toString();
        Name = name.getText().toString();
        Email = email.getText().toString();
        Title = title.getText().toString();
        Date = date.getText().toString();
        Subject = subject.getText().toString();
        Discription = discription.getText().toString();
        Priority = priority.getText().toString();


        new My_Task_single.Update(this,0).execute(Empid,Status,Name,Email,Title,Date,Subject,Discription,Priority);
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

                String empid = (String) arg0[0];
                String status = (String) arg0[1];
                String name = (String) arg0[2];
                String email = (String) arg0[3];
                String title = (String) arg0[4];
                String date = (String) arg0[5];
                String subject = (String) arg0[6];
                String discription = (String) arg0[7];
                String priority = (String) arg0[8];


                String link = "http://192.168.1.54/Ticketing_Tool/task_assign.php";

                String data = URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
                data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8");
                data += "&" + URLEncoder.encode("discription", "UTF-8") + "=" + URLEncoder.encode(discription, "UTF-8");
                data += "&" + URLEncoder.encode("priority", "UTF-8") + "=" + URLEncoder.encode(priority, "UTF-8");

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


            Toast.makeText(getApplicationContext(),"Data has been updated", Toast.LENGTH_LONG).show();

        }
    }


    private void sendEmail() {

        //Getting content for email
        String email1 = officialemail.getText().toString().trim();
        String email2 = email.getText().toString().trim();

        String subject1 = status.getSelectedItem().toString();

        String message = discription.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email1,email2, subject1, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
