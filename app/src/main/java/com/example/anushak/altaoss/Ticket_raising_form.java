package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Ticket_raising_form extends AppCompatActivity {

    ListView listView, listView1, listView2 ,listView3;

    ArrayList<Tickets_Dept_Names> SubjectList = new ArrayList<Tickets_Dept_Names>();
    ArrayList<Tickets_Dept_Names> SubjectList1 = new ArrayList<Tickets_Dept_Names>();
    ArrayList<Tickets_Dept_Names> SubjectList2 = new ArrayList<Tickets_Dept_Names>();
    ArrayList<Tickets_Dept_Names> SubjectList3 = new ArrayList<Tickets_Dept_Names>();

    String HttpURL = "http://192.168.1.54/Ticketing_Tool/hardware_dept_list.php";
    String HttpURL1 = "http://192.168.1.54/Ticketing_Tool/networking_dept_list.php";
    String HttpURL2 = "http://192.168.1.54/Ticketing_Tool/software_dept_list.php";
    String HttpURL3 = "http://192.168.1.54/Ticketing_Tool/admin_dept_list.php";

    Ticketing_Dept_Adapter listAdapter, listAdapter1, listAdapter2, listAdapter3;

    Spinner assign;
    EditText title, subject, discription;
    TextView name, empid, email, task_assign, status,emailid,text,text1;
    Button submit;
    Spinner priority;
    String Name, Empid, Email, Title, Subject, Discription, Priority, Status, Task_assign,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2,tickets,tickets1,tickets2;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_raising_form);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView =  findViewById(R.id.hardware);
        listView1 =  findViewById(R.id.software);
        listView2 =  findViewById(R.id.networking);
        listView3 =  findViewById(R.id.admin);
        task_assign = findViewById(R.id.empidlist);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView)findViewById(R.id.lq2);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        iv252 = (ImageView)findViewById(R.id.iv252);
        dashboard=(TextView)findViewById(R.id.dashboard);
        dashboard1=(TextView)findViewById(R.id.dashboard1);
        dashboard2=(TextView)findViewById(R.id.dashboard2);
        tickets=(TextView)findViewById(R.id.tickets);
        tickets1=(TextView)findViewById(R.id.tickets1);
        tickets2=(TextView)findViewById(R.id.tickets2);

        assign = (Spinner) findViewById(R.id.assign);

        title = (EditText) findViewById(R.id.title);
        name = (TextView) findViewById(R.id.name);
        empid = (TextView) findViewById(R.id.empid);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);

        emailid = (TextView) findViewById(R.id.emailid);
        email = (TextView) findViewById(R.id.phonenum);

        subject = (EditText) findViewById(R.id.subject);
        discription = (EditText) findViewById(R.id.discription);
        status = (TextView) findViewById(R.id.status);

        priority = (Spinner) findViewById(R.id.priority);

        submit = (Button) findViewById(R.id.submit);

        Empid = getIntent().getStringExtra("empid");
        Name = getIntent().getStringExtra("firstname");
        Email = getIntent().getStringExtra("officialemail");
        Text = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        email.setText(Email);
        name.setText(Name);
        text.setText(Text);

        lq.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Ticketing Tool Dashboard - Ticketing Raising Form");
        }
        else if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Ticketing Tool Dashboard - Ticketing Raising Form");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Ticketing Tool Dashboard - Ticketing Raising Form");
        }

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_raising_form.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_raising_form.this,TicketingDashboard.class);
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
                Intent intent = new Intent(Ticket_raising_form.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_raising_form.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv252.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_raising_form.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket_raising_form.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        assign.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        listView.setVisibility(View.GONE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.GONE);
                        break;
                    case 1:
                        // set editbox invivible
                        listView.setVisibility(View.GONE);//doing
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        // set editbox invivible
                        listView.setVisibility(View.VISIBLE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.GONE);

                        break;
                    case 3:
                        // set editbox invivible
                        listView.setVisibility(View.GONE);
                        listView1.setVisibility(View.VISIBLE);
                        listView2.setVisibility(View.GONE);
                        listView3.setVisibility(View.GONE);

                        break;
                    case 4:
                        // set editbox invivible
                        listView.setVisibility(View.GONE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.VISIBLE);
                        listView3.setVisibility(View.GONE);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);

                Tickets_Dept_Names ListViewClickData = (Tickets_Dept_Names) parent.getItemAtPosition(position);
                Toast.makeText(Ticket_raising_form.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();

                task_assign.setText(ListViewClickData.getSubId());
                emailid.setText(ListViewClickData.getEmail());

                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });
        new ParseJSonDataClass(Ticket_raising_form.this).execute();


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                Tickets_Dept_Names ListViewClickData = (Tickets_Dept_Names) parent.getItemAtPosition(position);
                Toast.makeText(Ticket_raising_form.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                task_assign.setText(ListViewClickData.getSubId());
                emailid.setText(ListViewClickData.getEmail());

                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });
        new ParseJSonDataClass1(Ticket_raising_form.this).execute();


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                Tickets_Dept_Names ListViewClickData = (Tickets_Dept_Names) parent.getItemAtPosition(position);
                Toast.makeText(Ticket_raising_form.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                task_assign.setText(ListViewClickData.getSubId());
                emailid.setText(ListViewClickData.getEmail());

                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });
        new ParseJSonDataClass2(Ticket_raising_form.this).execute();


        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                Tickets_Dept_Names ListViewClickData = (Tickets_Dept_Names) parent.getItemAtPosition(position);
                Toast.makeText(Ticket_raising_form.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                task_assign.setText(ListViewClickData.getSubId());
                emailid.setText(ListViewClickData.getEmail());

                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });
        new ParseJSonDataClass3(Ticket_raising_form.this).execute();




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (priority.getSelectedItem().toString().trim().equals("Set Priority")) {

                    Toast.makeText(Ticket_raising_form.this, "Plese select Spinner item", Toast.LENGTH_SHORT).show();

                }
                else {

                    Name = name.getText().toString();
                    Empid = empid.getText().toString();
                    Email = email.getText().toString();
                    Title = title.getText().toString();
                    Subject = subject.getText().toString();
                    Discription = discription.getText().toString();
                    Priority = priority.getSelectedItem().toString();
                    Task_assign = task_assign.getText().toString();
                    Status = status.getText().toString();

                    BackGround b = new BackGround();
                    b.execute(Name, Empid, Email, Title, Subject, Discription, Priority, Task_assign, Status);

                    sendEmail();//mail sending method calling


                }
            }
        });
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String empid = params[1];
            String email = params[2];
            String title = params[3];
            String subject = params[4];
            String discription = params[5];
            String priority = params[6];
            String task_assign = params[7];
            String status = params[8];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.54/Ticketing_Tool/register.php");
                String urlParams = "name=" + name + "&empid=" + empid + "&email=" + email + "&title=" + title + "&subject=" + subject + "&discription=" + discription + "&priority=" + priority + "&task_assign=" + task_assign + "&status=" + status;
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

        }
    }


    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Tickets_Dept_Names details;

                            SubjectList = new ArrayList<Tickets_Dept_Names>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();
                                String tempFullForm3 = jsonObject.getString("officialemail").toString();

                                details = new Tickets_Dept_Names(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                SubjectList.add(details);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            //   progressBar.setVisibility(View.INVISIBLE);
            listAdapter = new Ticketing_Dept_Adapter(Ticket_raising_form.this, R.layout.ticketing_dept_view, SubjectList);
            listView.setAdapter(listAdapter);
        }
    }


    private class ParseJSonDataClass1 extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass1(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL1);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Tickets_Dept_Names details;

                            SubjectList1 = new ArrayList<Tickets_Dept_Names>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                String tempFullForm3 = jsonObject.getString("officialemail").toString();

                                details = new Tickets_Dept_Names(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                SubjectList1.add(details);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            //   progressBar.setVisibility(View.INVISIBLE);
            listAdapter1 = new Ticketing_Dept_Adapter(Ticket_raising_form.this, R.layout.ticketing_dept_view, SubjectList1);
            listView1.setAdapter(listAdapter1);
        }
    }


    private class ParseJSonDataClass2 extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass2(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL2);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {
                        JSONArray jsonArray = null;

                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Tickets_Dept_Names details;

                            SubjectList2 = new ArrayList<Tickets_Dept_Names>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                String tempFullForm3 = jsonObject.getString("officialemail").toString();

                                details = new Tickets_Dept_Names(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                SubjectList2.add(details);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            //   progressBar.setVisibility(View.INVISIBLE);
            listAdapter2 = new Ticketing_Dept_Adapter(Ticket_raising_form.this, R.layout.ticketing_dept_view, SubjectList2);
            listView2.setAdapter(listAdapter2);
        }
    }


    private class ParseJSonDataClass3 extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass3(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL3);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {
                        JSONArray jsonArray = null;

                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Tickets_Dept_Names details;

                            SubjectList3 = new ArrayList<Tickets_Dept_Names>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                String tempFullForm3 = jsonObject.getString("officialemail").toString();

                                details = new Tickets_Dept_Names(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                SubjectList3.add(details);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            //   progressBar.setVisibility(View.INVISIBLE);
            listAdapter3 = new Ticketing_Dept_Adapter(Ticket_raising_form.this, R.layout.ticketing_dept_view, SubjectList3);
            listView3.setAdapter(listAdapter3);
        }
    }


    private void sendEmail() {

        //Getting content for email
        String email1 = emailid.getText().toString().trim();
        String email2 = email.getText().toString().trim();

        String subject1 = subject.getText().toString().trim();
        String message = discription.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email1,email2, subject1, message);


        //Executing sendmail to send email
        sm.execute();
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

                Intent i = new Intent(Ticket_raising_form.this, QueryForm.class);
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

                Toast.makeText(Ticket_raising_form.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}

