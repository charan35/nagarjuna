package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Preferences extends AppCompatActivity {

    ListView listView1,listView2,listView3;
    EditText editText;
    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    ArrayList<Subject> SubjectList1 = new ArrayList<Subject>();
    ArrayList<Subject> SubjectList2 = new ArrayList<Subject>();
    String HttpURL1 = "http://altaitsolutions.com/Altadatabase/listitems1.php";
    String HttpURL2 = "http://altaitsolutions.com/Altadatabase/listitems2.php";
    String HttpURL3 = "http://altaitsolutions.com/Altadatabase/listitems3.php";
    ListAdapter listAdapter,listAdapter1,listAdapter2;
    ProgressBar progressbar1;
    TextView text,text1,text2;
    String FIRSTNAME = null,EMPID = null,Text,Text1,Text2,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,MIDDLENAME,DEPARTMENT;
    String PROFILE,PROJECT,MESSAGE,MYPAY,PAYSLIPS,REIMBURSEMENT1,ATTENDANCE,UPDATES,MYPROJECT,CAREERS;
    String NEWEMPFORM,EMPDIRECTORY,PROJECTDETAILS,EMPATTENDANCE,REIMFETCH,DOCUMENTSUPLOAD,DOWNLOADPAYSLIPS,REWARDS,INTERVIEWS;
    String DOCUMENTSFETCH,CCCAMERA,PERMISSION,PAYROLL;
    ImageView iv25;
    TextView admin;
    Button projectpreferences;

    Button b1,b2,b3;
    LinearLayout lv1,lv2,lv3;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView1 = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);
        listView3 = (ListView) findViewById(R.id.listView3);

        editText = (EditText) findViewById(R.id.edittext1);
        iv25 = (ImageView)findViewById(R.id.iv25);
        admin = (TextView) findViewById(R.id.admin);
        progressbar1 = (ProgressBar)findViewById(R.id.progressbar1);

        text = (TextView)findViewById(R.id.Grid2);
        text1 = (TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid);

        projectpreferences = (Button) findViewById(R.id.projectpreferences);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        lv1 = (LinearLayout) findViewById(R.id.lv1);
        lv2 = (LinearLayout) findViewById(R.id.lv2);
        lv3 = (LinearLayout) findViewById(R.id.lv3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv1.setVisibility(View.GONE);
                lv2.setVisibility(View.GONE);
                lv3.setVisibility(View.VISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv1.setVisibility(View.GONE);
                lv2.setVisibility(View.VISIBLE);
                lv3.setVisibility(View.GONE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv1.setVisibility(View.VISIBLE);
                lv2.setVisibility(View.GONE);
                lv3.setVisibility(View.GONE);
            }
        });

        Text1 = getIntent().getStringExtra("empid");
        Text2 = getIntent().getStringExtra("email");

        listView1.setTextFilterEnabled(true);
        listView2.setTextFilterEnabled(true);

        text.setText("Admin Dashboard - Preferences");
        text1.setText(Text1);
        text2.setText(Text2);

        projectpreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Preferences.this,ProjectPreferences.class);
                intent.putExtra("empid",Text1);
                startActivity(intent);
            }
        });

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Preferences.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listView1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Preferences.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGround5 b=new BackGround5();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());
            }
        });

        listView2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Preferences.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGround5 b=new BackGround5();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());
            }
        });

        listView3.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(Preferences.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGround5 b=new BackGround5();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());
            }
        });

        ///

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence stringVar, int start, int before, int count) {

                listAdapter.getFilter().filter(stringVar.toString());
                listAdapter1.getFilter().filter(stringVar.toString());
                listAdapter2.getFilter().filter(stringVar.toString());

            }
        });

        new ParseJSonDataClass(this).execute();
        new ParseJSonDataClass1(this).execute();
        new ParseJSonDataClass2(this).execute();
    }

    class BackGround5 extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String middlename = params[1];
            String department = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/pay1.php");
                //URL url = new URL("http://192.168.1.55/AltaCRM/pay1.php");
                String urlParams = "firstname=" + firstname+ "&middlename=" + middlename+ "&department=" + department+ "&empid=" + empid;

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
                EMPID = user_data_profile.getString("empid");

                PROFILE = user_data_profile.getString("profile");
                PROJECT = user_data_profile.getString("project");
                MESSAGE = user_data_profile.getString("message");
                MYPAY = user_data_profile.getString("mypay");
                PAYSLIPS = user_data_profile.getString("payslips");
                REIMBURSEMENT1 = user_data_profile.getString("reimbursement1");
                ATTENDANCE = user_data_profile.getString("attendance");
                UPDATES = user_data_profile.getString("updates");
                MYPROJECT = user_data_profile.getString("myproject");
                NEWEMPFORM = user_data_profile.getString("newempform");
                EMPDIRECTORY = user_data_profile.getString("empdirectory");
                PROJECTDETAILS = user_data_profile.getString("projectdetails");
                EMPATTENDANCE = user_data_profile.getString("empattendance");
                REIMFETCH = user_data_profile.getString("reimbursement");
                DOCUMENTSUPLOAD = user_data_profile.getString("documentsupload");
                DOWNLOADPAYSLIPS = user_data_profile.getString("payslips1");
                REWARDS = user_data_profile.getString("rewards");
                INTERVIEWS = user_data_profile.getString("interviews");
                CAREERS = user_data_profile.getString("careers");
                DOCUMENTSFETCH = user_data_profile.getString("documentsfetch");
                CCCAMERA = user_data_profile.getString("cccamera");
                PERMISSION = user_data_profile.getString("permission");
                PAYROLL = user_data_profile.getString("payroll");


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(Preferences.this,Permissions.class);
            i.putExtra("empid", EMPID);

            i.putExtra("profile", PROFILE);
            i.putExtra("project", PROJECT);
            i.putExtra("message", MESSAGE);
            i.putExtra("mypay", MYPAY);
            i.putExtra("payslips", PAYSLIPS);
            i.putExtra("reimbursement1", REIMBURSEMENT1);
            i.putExtra("attendance", ATTENDANCE);
            i.putExtra("updates", UPDATES);
            i.putExtra("myproject", MYPROJECT);
            i.putExtra("newempform", NEWEMPFORM);
            i.putExtra("empdirectory", EMPDIRECTORY);
            i.putExtra("projectdetails", PROJECTDETAILS);
            i.putExtra("empattendance", EMPATTENDANCE);
            i.putExtra("reimbursement", REIMFETCH);
            i.putExtra("documentsupload", DOCUMENTSUPLOAD);
            i.putExtra("payslips1", DOWNLOADPAYSLIPS);
            i.putExtra("rewards", REWARDS);
            i.putExtra("interviews", INTERVIEWS);
            i.putExtra("careers", CAREERS);
            i.putExtra("permission", PERMISSION);
            i.putExtra("cccamera", CCCAMERA);
            i.putExtra("documentsfetch", DOCUMENTSFETCH);
            i.putExtra("payroll", PAYROLL);

            startActivity(i);
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
            progressbar1.setVisibility(View.INVISIBLE);
            listAdapter = new ListAdapter(Preferences.this, R.layout.custom_layout, SubjectList);
            listView1.setAdapter(listAdapter);
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
            progressbar1.setVisibility(View.INVISIBLE);
            listAdapter1 = new ListAdapter(Preferences.this, R.layout.custom_layout, SubjectList1);
            listView2.setAdapter(listAdapter1);
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
            progressbar1.setVisibility(View.INVISIBLE);
            listAdapter2 = new ListAdapter(Preferences.this, R.layout.custom_layout, SubjectList2);
            listView3.setAdapter(listAdapter2);
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
            Text1 = text1.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Text1);
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

                Intent i = new Intent(Preferences.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Preferences.this,"Invalid Details",Toast.LENGTH_LONG).show();
            }
        }
    }

}