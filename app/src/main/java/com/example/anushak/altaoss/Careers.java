package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class Careers extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    TextView text,empid,text1;
    String Text,Empid,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,JOBTITLE,VACANCIES,LOCATION,SALARY,EXPERIENCE,INTERVIEWLOCATION,SKILLS,BOND;
    HorizontalScrollView lq,lq1,lq2;
    ListView listView;
    ProgressBar progressBar;
    ArrayList<JobData> JobList = new ArrayList<JobData>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/jobs_list.php";
    JobAdapter jobAdapter;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_careers);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        empid = (TextView)findViewById(R.id.empid);
        lq = (HorizontalScrollView) findViewById(R.id.lq);
        lq1 = (HorizontalScrollView) findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView) findViewById(R.id.lq2);
        listView = (ListView)findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        iv25 = (ImageView)findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        iv252 = (ImageView)findViewById(R.id.iv252);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        dashboard2 = (TextView)findViewById(R.id.dashboard2);

        Text = getIntent().getStringExtra("employee");
        Empid = getIntent().getStringExtra("empid");

        text.setText(Text);
        empid.setText(Empid);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Careers");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Careers");
        }
        else  if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Careers");
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
                Intent intent = new Intent(Careers.this,HrDashboard.class);
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
                Intent intent = new Intent(Careers.this,AdminDashboard.class);
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
                Intent intent = new Intent(Careers.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JobData ListViewClickData = (JobData) parent.getItemAtPosition(position);
                Toast.makeText(Careers.this, ListViewClickData.getJTitle(), Toast.LENGTH_SHORT).show();
                BackGroundJob b=new BackGroundJob();
                b.execute(ListViewClickData.getJTitle(),ListViewClickData.getJVacancies(),ListViewClickData.getJExperience(),ListViewClickData.getJLocation(),ListViewClickData.getJSalary());
            }
        });

        new ParseJSonDataClass(Careers.this).execute();
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

                            JobData jobData;

                            JobList = new ArrayList<JobData>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("JobTitle").toString();
                                String tempFullForm = jsonObject.getString("Vacancies").toString();
                                String tempFullForm1 = jsonObject.getString("Experience").toString();
                                String tempFullForm2 = jsonObject.getString("JobLocation").toString();
                                String tempFullForm3 = jsonObject.getString("Salary").toString();

                                jobData = new JobData(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                JobList.add(jobData);
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
            progressBar.setVisibility(View.INVISIBLE);
            jobAdapter = new JobAdapter(Careers.this, R.layout.job_layout, JobList);
            listView.setAdapter(jobAdapter);
        }
    }

    class BackGroundJob extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String JobTitle = params[0];
            String Vacancies = params[1];
            String Experience = params[2];
            String Location = params[3];
            String Salary = params[4];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/job_details.php");
                String urlParams = "JobTitle=" + JobTitle+ "&Vacancies=" + Vacancies+ "&Experience=" + Experience+ "&JobLocation=" + Location+ "&Salary=" + Salary;

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
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data_profile = root.getJSONObject("user_data_profile");
                JOBTITLE = user_data_profile.getString("JobTitle");
                VACANCIES = user_data_profile.getString("Vacancies");
                LOCATION= user_data_profile.getString("JobLocation");
                EXPERIENCE = user_data_profile.getString("Experience");
                SALARY = user_data_profile.getString("Salary");
                INTERVIEWLOCATION = user_data_profile.getString("InterviewLocation");
                SKILLS = user_data_profile.getString("Skillrequirments");
                BOND = user_data_profile.getString("BondPeriod");

                Intent i = new Intent(Careers.this, JobDetails.class);
                i.putExtra("JobTitle", JOBTITLE);
                i.putExtra("Vacancies", VACANCIES);
                i.putExtra("JobLocation", LOCATION);
                i.putExtra("Experience", EXPERIENCE);
                i.putExtra("Salary", SALARY);
                i.putExtra("InterviewLocation", INTERVIEWLOCATION);
                i.putExtra("Skillrequirments", SKILLS);
                i.putExtra("BondPeriod", BOND);
                i.putExtra("employee",Text);
                i.putExtra("empid",Empid);

                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

                Intent i = new Intent(Careers.this, QueryForm.class);
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

                Toast.makeText(Careers.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
