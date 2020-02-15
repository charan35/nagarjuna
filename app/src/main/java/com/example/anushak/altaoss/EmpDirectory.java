package com.example.anushak.altaoss;

import android.app.SearchManager;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
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

public class EmpDirectory extends AppCompatActivity {

    ListView listView;
    SearchView search;
    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/listitems.php";
    ListAdapter listAdapter;
    ProgressBar progressBar ;
    TextView text,text1;
    ImageView iv25,iv251;
    TextView admin,admin1,empdirectory,hr,employee;
    LinearLayout lq,lq1;
    String Employee,Text,Text1,FIRSTNAME = null, MIDDLENAME = null, LASTNAME = null, GENDER = null, BLOODGROUP = null, CONTACTNUMBER = null,DATEOFJOINING=null, PERMANENTADDRESS = null, CORRESPONDANCEADDRESS = null, OFFICIALEMAIL = null, PERSONALEMAIL = null, EMPID = null, DEPARTMENT = null, DESIGNATION = null,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_directory);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search = (SearchView) findViewById(R.id.edittext1);
        listView = (ListView) findViewById(R.id.listView1);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        text = (TextView)findViewById(R.id.Grid2);
        text1 = (TextView)findViewById(R.id.Grid1);

        admin = (TextView) findViewById(R.id.admin);
        admin1 = (TextView) findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        employee = (TextView)findViewById(R.id.employee);
        empdirectory = (TextView)findViewById(R.id.empdirectory);

        Employee = getIntent().getStringExtra("employee");
        Text1 = getIntent().getStringExtra("empid");
        employee.setText(Employee);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        listView.setTextFilterEnabled(true);
        //text.setText("Admin Dashboard - Employee List(Directory)");
        text1.setText(Text1);
        search.onActionViewExpanded(); //new Added line
        search.setIconifiedByDefault(false);
        search.setQueryHint("Search Here");

        if(!search.isFocused()) {
            search.clearFocus();
        }

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpDirectory.this,HrDashboard.class);
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

        admin1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpDirectory.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Employee.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Employee.equals("admin")? View.VISIBLE : View.GONE);

        if(Employee.equals("hr"))
        {
            text.setText("HR Dashboard - Employee List(Directory)");
        }
        else  if(Employee.equals("admin"))
        {
            text.setText("Admin Dashboard - Employee List(Directory)");
        }

        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(EmpDirectory.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGroundempdir b=new BackGroundempdir();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());

            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                listAdapter.getFilter().filter(text.toString());
                //listView.getSelectedItem();
                BackGroundempdir1 b = new BackGroundempdir1();
                b.execute(text,text,text,text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                listAdapter.getFilter().filter(text.toString());
                return false;
            }
        });

        new ParseJSonDataClass(EmpDirectory.this).execute();

    }

    class BackGroundempdir extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String middlename = params[1];
            String department = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/profile.php");
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
                FIRSTNAME = user_data_profile.getString("firstname");
                MIDDLENAME = user_data_profile.getString("middlename");
                LASTNAME = user_data_profile.getString("lastname");
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");
                DESIGNATION = user_data_profile.getString("designation");
                GENDER = user_data_profile.getString("gender");
                BLOODGROUP = user_data_profile.getString("bloodgroup");
                CONTACTNUMBER = user_data_profile.getString("contactno");
                DATEOFJOINING = user_data_profile.getString("dateofjoining");
                PERMANENTADDRESS = user_data_profile.getString("permanentaddress");
                CORRESPONDANCEADDRESS = user_data_profile.getString("correspondanceaddress");
                OFFICIALEMAIL = user_data_profile.getString("officialemail");
                PERSONALEMAIL = user_data_profile.getString("personalemail");

                Intent i = new Intent(EmpDirectory.this, Profile.class);
                i.putExtra("empid", EMPID);
                i.putExtra("department", DEPARTMENT);
                i.putExtra("designation", DESIGNATION);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("middlename", MIDDLENAME);
                i.putExtra("lastname", LASTNAME);
                i.putExtra("gender", GENDER);
                i.putExtra("bloodgroup", BLOODGROUP);
                i.putExtra("contactno", CONTACTNUMBER);
                i.putExtra("dateofjoining", DATEOFJOINING);
                i.putExtra("permanentaddress", PERMANENTADDRESS);
                i.putExtra("correspondanceaddress", CORRESPONDANCEADDRESS);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("personalemail", PERSONALEMAIL);
                i.putExtra("employee",Employee);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    class BackGroundempdir1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String middlename = params[1];
            String department = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/profile1.php");
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
                FIRSTNAME = user_data_profile.getString("firstname");
                MIDDLENAME = user_data_profile.getString("middlename");
                LASTNAME = user_data_profile.getString("lastname");
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");
                DESIGNATION = user_data_profile.getString("designation");
                GENDER = user_data_profile.getString("gender");
                BLOODGROUP = user_data_profile.getString("bloodgroup");
                CONTACTNUMBER = user_data_profile.getString("contactno");
                DATEOFJOINING = user_data_profile.getString("dateofjoining");
                PERMANENTADDRESS = user_data_profile.getString("permanentaddress");
                CORRESPONDANCEADDRESS = user_data_profile.getString("correspondanceaddress");
                OFFICIALEMAIL = user_data_profile.getString("officialemail");
                PERSONALEMAIL = user_data_profile.getString("personalemail");

                Intent i = new Intent(EmpDirectory.this, Profile.class);
                i.putExtra("empid", EMPID);
                i.putExtra("department", DEPARTMENT);
                i.putExtra("designation", DESIGNATION);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("middlename", MIDDLENAME);
                i.putExtra("lastname", LASTNAME);
                i.putExtra("gender", GENDER);
                i.putExtra("bloodgroup", BLOODGROUP);
                i.putExtra("contactno", CONTACTNUMBER);
                i.putExtra("dateofjoining", DATEOFJOINING);
                i.putExtra("permanentaddress", PERMANENTADDRESS);
                i.putExtra("correspondanceaddress", CORRESPONDANCEADDRESS);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("personalemail", PERSONALEMAIL);
                i.putExtra("employee",Employee);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpDirectory.this,"Data Not Found", Toast.LENGTH_SHORT).show();
            }
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
            progressBar.setVisibility(View.INVISIBLE);
            listAdapter = new ListAdapter(EmpDirectory.this, R.layout.custom_layout, SubjectList);
            listView.setAdapter(listAdapter);
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

                Intent i = new Intent(EmpDirectory.this, QueryForm.class);
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

                Toast.makeText(EmpDirectory.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}