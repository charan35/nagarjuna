package com.example.anushak.altaoss;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

public class EmployeeDocuments extends AppCompatActivity {

    ListView listView;
    SearchView editText;
    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/listitems.php";
    ListAdapter listAdapter;
    ProgressBar progressBar ;
    TextView text,text1,text2;
    String FIRSTNAME = null,EMPID = null,Text,Text1,Text2,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,MIDDLENAME,DEPARTMENT;
    ImageView iv25;
    TextView admin;
    android.support.v7.widget.Toolbar toolbar;
    TextView textview;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_employee_documents);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView1);

        editText = (SearchView) findViewById(R.id.edittext1);
        iv25 = (ImageView)findViewById(R.id.iv25);
        admin = (TextView) findViewById(R.id.admin);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        text = (TextView)findViewById(R.id.Grid2);
        text1 = (TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid);
        textview = (TextView)findViewById(R.id.textview);
        image = (ImageView)findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.image)
                {
                    image.setVisibility(View.GONE);
                    textview.setVisibility(View.GONE);
                    editText.setVisibility(View.VISIBLE);
                }
            }
        });

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        editText.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        editText.onActionViewExpanded(); //new Added line
        editText.setIconifiedByDefault(false);
        editText.setQueryHint("Search Here");
        editText.setBackgroundColor(Color.parseColor("#0bcce4"));

        Text1 = getIntent().getStringExtra("empid");
        Text2 = getIntent().getStringExtra("email");

        listView.setTextFilterEnabled(true);
        text.setText("Admin Dashboard - Employee List(Documents)");
        text1.setText(Text1);
        text2.setText(Text2);

        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(EmployeeDocuments.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGround5 b=new BackGround5();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());
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
                Intent intent = new Intent(EmployeeDocuments.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

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

        new ParseJSonDataClass(this).execute();

    }

    class BackGround5 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String middlename = params[1];
            String department = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/reimbursement.php");
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
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");


                Intent i = new Intent(EmployeeDocuments.this,Documents.class);
                i.putExtra("empid", EMPID);
                i.putExtra("firstname", FIRSTNAME);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(EmployeeDocuments.this,"Related Document is not there.", Toast.LENGTH_LONG).show();
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
                URL url = new URL("http://altaitsolutions.com/Altadatabase/reimbursement1.php");
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
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");

                Intent i = new Intent(EmployeeDocuments.this,Documents.class);
                i.putExtra("empid", EMPID);
                i.putExtra("firstname", FIRSTNAME);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmployeeDocuments.this,"Related Document is not there.", Toast.LENGTH_LONG).show();
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
            listAdapter = new ListAdapter(EmployeeDocuments.this, R.layout.custom_layout, SubjectList);
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
            Text2 = text2.getText().toString();
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

                Intent i = new Intent(EmployeeDocuments.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text);
                i.putExtra("email1",Text2);
                i.putExtra("message1",Text1);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmployeeDocuments.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}