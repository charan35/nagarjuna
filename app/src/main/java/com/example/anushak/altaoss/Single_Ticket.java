package com.example.anushak.altaoss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
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
import java.util.ArrayList;

public class Single_Ticket extends AppCompatActivity {

    ListView listView,listView1,listView2;

    SearchView search;
    Button assignbtn;
    Context ctx;

    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    ArrayList<Subject> SubjectList1 = new ArrayList<Subject>();
    ArrayList<Subject> SubjectList2 = new ArrayList<Subject>();

    String HttpURL = "http://192.168.1.54/Ticketing_Tool/hardware_dept_list.php";
    String HttpURL1 = "http://192.168.1.54/Ticketing_Tool/networking_dept_list.php";
    String HttpURL2 = "http://192.168.1.54/Ticketing_Tool/software_dept_list.php";

    ListAdapter listAdapter,listAdapter1,listAdapter2;

    Spinner assign;
    private ProgressDialog progressDialog;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2,tickets,tickets1,tickets2,text,text1,form,form1,form2;
    android.support.v7.widget.Toolbar toolbar;
    String Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    CountDownTimer CDT;
    int i = 3;


    TextView name,empid,email,title,date,subject,discription,priority,task_assign,status;
    String Name,Empid,Email,Title,Date,Subject,Discription,Priority,Task_assign,Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single__ticket);

        assign = (Spinner) findViewById(R.id.assign);

        name=(TextView)findViewById(R.id.name);
        empid=(TextView)findViewById(R.id.empid);
        email=(TextView)findViewById(R.id.phonenum);
        title=(TextView)findViewById(R.id.title);
        date=(TextView)findViewById(R.id.date);
        subject=(TextView)findViewById(R.id.subject);
        discription=(TextView)findViewById(R.id.discription);
        priority=(TextView)findViewById(R.id.priority);
        listView = (ListView) findViewById(R.id.hardware);
        listView1 = (ListView)findViewById(R.id.software);
        listView2 = (ListView)findViewById(R.id.networking);
        task_assign=(TextView)findViewById(R.id.empidlist);
        assignbtn=(Button)findViewById(R.id.assignbtn);
        status=(TextView)findViewById(R.id.status);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        form=(TextView)findViewById(R.id.form);
        form1=(TextView)findViewById(R.id.form1);
        form2=(TextView)findViewById(R.id.form2);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);


        Name=getIntent().getStringExtra("name");
        Empid=getIntent().getStringExtra("empid");
        Email=getIntent().getStringExtra("email");
        Title=getIntent().getStringExtra("title");
        Date=getIntent().getStringExtra("date");
        Subject=getIntent().getStringExtra("subject");
        Discription=getIntent().getStringExtra("discription");
        Priority=getIntent().getStringExtra("priority");
        Status=getIntent().getStringExtra("status");
        Text = getIntent().getStringExtra("employee");

        text.setText(Text);
        name.setText(Name);
        empid.setText(Empid);
        email.setText(Email);
        title.setText(Title);
        date.setText(Date);
        subject.setText(Subject);
        discription.setText(Discription);
        priority.setText(Priority);
        status.setText(Status);

        lq.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Ticketing Tool Dashboard - Total Tickets - Ticket Assign Form");
        }
        else if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Ticketing Tool Dashboard - Total Tickets - Ticket Assign Form");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Ticketing Tool Dashboard - Total Tickets - Ticket Assign Form");
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
                Intent intent = new Intent(Single_Ticket.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,Total_Tickets.class);
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
                Intent intent = new Intent(Single_Ticket.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,Total_Tickets.class);
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
                Intent intent = new Intent(Single_Ticket.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        form2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Single_Ticket.this,Total_Tickets.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        assignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Update();
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
                        break;
                    case 1:
                        // set editbox invivible
                        listView.setVisibility(View.VISIBLE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.GONE);
                        break;
                    case 2:
                        // set editbox invivible
                        listView.setVisibility(View.GONE);
                        listView1.setVisibility(View.VISIBLE);
                        listView2.setVisibility(View.GONE);
                        break;
                    case 3:
                        // set editbox invivible
                        listView.setVisibility(View.GONE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.VISIBLE);
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

                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Single_Ticket.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();

                task_assign.setText(ListViewClickData.getSubId());

                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });  new ParseJSonDataClass(Single_Ticket.this).execute();


        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Single_Ticket.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                task_assign.setText(ListViewClickData.getSubId());
                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });  new ParseJSonDataClass1(Single_Ticket.this).execute();


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Single_Ticket.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                task_assign.setText(ListViewClickData.getSubId());
                //  EmpDirectory.BackGroundempdir b=new EmpDirectory.BackGroundempdir();
                // b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });  new ParseJSonDataClass2(Single_Ticket.this).execute();

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

                            Subject subject;

                            SubjectList = new ArrayList<Subject>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                subject = new Subject(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                SubjectList.add(subject);
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
            listAdapter = new ListAdapter(Single_Ticket.this, R.layout.custom_layout, SubjectList);
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

                            Subject subject;

                            SubjectList1 = new ArrayList<Subject>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                subject = new Subject(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                SubjectList1.add(subject);
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
            listAdapter1 = new ListAdapter(Single_Ticket.this, R.layout.custom_layout, SubjectList1);
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

                            Subject subject;

                            SubjectList2 = new ArrayList<Subject>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("firstname").toString();
                                String tempFullForm = jsonObject.getString("middlename").toString();
                                String tempFullForm1 = jsonObject.getString("empid").toString();
                                String tempFullForm2 = jsonObject.getString("department").toString();

                                subject = new Subject(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                SubjectList2.add(subject);
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
            listAdapter2 = new ListAdapter(Single_Ticket.this, R.layout.custom_layout, SubjectList2);
            listView2.setAdapter(listAdapter2);
        }
    }

//update code for assign task

    public void Update()
    {

        Empid = empid.getText().toString();
        Status= status.getText().toString();
        Name = name.getText().toString();
        Email = email.getText().toString();
        Title = title.getText().toString();
        Date = date.getText().toString();
        Subject = subject.getText().toString();
        Discription = discription.getText().toString();
        Priority = priority.getText().toString();
        Task_assign = task_assign.getText().toString();

        new Single_Ticket.Update(this,0).execute(Empid,Status,Name,Email,Title,Date,Subject,Discription,Priority,Task_assign);

    }

    class Update extends AsyncTask<String,Void,String>

    {
        public Update (Context context, int flag)
        {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Single_Ticket.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.setProgress(i);
            progressDialog.show();

            CDT = new CountDownTimer(1000, 500){

                @Override
                public void onTick(long l) {

                    //  progressDialog.setMessage("Please wait.." + i + " sec");
                    progressDialog = ProgressDialog.show(Single_Ticket.this, "Sending message", +i + "Please wait...", false, false);
                    i--;

                }

                @Override
                public void onFinish() {

                    progressDialog.dismiss();

                }
            }.start();

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
                String task_assign = (String) arg0[9];

                String link = "http://192.168.1.54/Ticketing_Tool/task_assign1.php";

                String data = URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode(status, "UTF-8");
                data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("title", "UTF-8") + "=" + URLEncoder.encode(title, "UTF-8");
                data += "&" + URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                data += "&" + URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(subject, "UTF-8");
                data += "&" + URLEncoder.encode("discription", "UTF-8") + "=" + URLEncoder.encode(discription, "UTF-8");
                data += "&" + URLEncoder.encode("priority", "UTF-8") + "=" + URLEncoder.encode(priority, "UTF-8");
                data += "&" + URLEncoder.encode("task_assign", "UTF-8") + "=" + URLEncoder.encode(task_assign, "UTF-8");

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

            progressDialog.dismiss();

            Toast.makeText(Single_Ticket.this,"Data has been updated", Toast.LENGTH_LONG).show();

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

                Intent i = new Intent(Single_Ticket.this, QueryForm.class);
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

                Toast.makeText(Single_Ticket.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
