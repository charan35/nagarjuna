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

public class RewardsUpdate extends AppCompatActivity {

    TextView empid,text1,dashboard,dashboard1,querytext;
    String Empid,FIRSTNAME,MIDDLENAME,LASTNAME,EMPID,DEPARTMENT,Text1,Querytext,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    ListView listView;
    SearchView search;
    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/listitems.php";
    ListAdapter listAdapter;
    ProgressBar progressBar ;
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv25,iv251;
    HorizontalScrollView lq,lq1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_rewards_update);

        empid = (TextView)findViewById(R.id.empid);
        querytext = (TextView)findViewById(R.id.query);
        text1 = (TextView)findViewById(R.id.text1);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search = (SearchView) findViewById(R.id.edittext1);
        listView = (ListView) findViewById(R.id.listView1);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);

        Empid = getIntent().getStringExtra("empid");
        Text1 = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        text1.setText(Text1);
        listView.setTextFilterEnabled(true);

        search.onActionViewExpanded(); //new Added line
        search.setIconifiedByDefault(false);
        search.setQueryHint("Search Here");

        if(!search.isFocused()) {
            search.clearFocus();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Subject ListViewClickData = (Subject)parent.getItemAtPosition(position);
                Toast.makeText(RewardsUpdate.this, ListViewClickData.getSubName(), Toast.LENGTH_SHORT).show();
                BackGroundreward b=new BackGroundreward();
                b.execute(ListViewClickData.getSubName(),ListViewClickData.getSubFullForm(),ListViewClickData.getSubDepartment(),ListViewClickData.getSubId());
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                listAdapter.getFilter().filter(text.toString());
                //listView.getSelectedItem();
                /*BackGroundempdir1 b = new BackGroundempdir1();
                b.execute(text,text,text,text);*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                listAdapter.getFilter().filter(text.toString());
                return false;
            }
        });

        lq.setVisibility(Text1.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text1.equals("admin")? View.VISIBLE : View.GONE);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RewardsUpdate.this,HrDashboard.class);
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
                Intent intent = new Intent(RewardsUpdate.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(Text1.equals("hr"))
        {
            querytext.setText("HR Dashboard - Rewards(Emp List)");
        }
        else  if(Text1.equals("admin"))
        {
            querytext.setText("Admin Dashboard - Rewards(Emp List)");
        }

        new ParseJSonDataClass(RewardsUpdate.this).execute();
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
            listAdapter = new ListAdapter(RewardsUpdate.this, R.layout.custom_layout, SubjectList);
            listView.setAdapter(listAdapter);
        }
    }

    class BackGroundreward extends AsyncTask<String, String, String> {

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

                Intent i = new Intent(RewardsUpdate.this, RewardsSubmit.class);
                i.putExtra("empid", EMPID);
                i.putExtra("department", DEPARTMENT);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("middlename", MIDDLENAME);
                i.putExtra("lastname", LASTNAME);
                i.putExtra("employee", Text1);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

        if (id == R.id.query)
        {
            Querytext=querytext.getText().toString();
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

                Intent i = new Intent(RewardsUpdate.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Querytext);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(RewardsUpdate.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
