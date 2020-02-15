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

public class EmpPayroll extends AppCompatActivity {

    ListView listView,listView2;
    SearchView search;
    ImageView image;
    TextView view;
    ArrayList<Subject> SubjectList = new ArrayList<Subject>();
    ArrayList<payroll> PayList = new ArrayList<payroll>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/listitems.php";
    String HttpURL1 = "http://altaitsolutions.com/Altadatabase/payroll.php";
    ListAdapter listAdapter;
    PayrollAdapter payrollAdapter;
    ProgressBar progressBar;
    String FIRSTNAME,EMPID,Text1,OFFICIALEMAIL,HRMAIL,HRID,MANAGERMAIL,MANAGERID,Empid,MONTH,YEAR,PDFURL;

    LinearLayout lq,lq1;
    TextView admin,admin1;
    ImageView iv25,iv251;
    TextView text2,grid,grid1;
    String Text2,Grid,Grid1;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_payroll);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.listView1);
        listView2 = (ListView) findViewById(R.id.listView2);
        search = (SearchView) findViewById(R.id.search);
        view = (TextView)findViewById(R.id.view);
        image = (ImageView)findViewById(R.id.image);
        grid = (TextView)findViewById(R.id.Grid);
        grid1 = (TextView)findViewById(R.id.Grid1);

        admin = (TextView) findViewById(R.id.admin);
        admin1 = (TextView) findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        text2 = (TextView) findViewById(R.id.text2);

        Grid = getIntent().getStringExtra("empid");
        Text2 = getIntent().getStringExtra("employee");
        text2.setText(Text2);
        grid.setText(Grid);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpPayroll.this,HrDashboard.class);
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
                Intent intent = new Intent(EmpPayroll.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("hr"))
        {
            grid1.setText("HR Dashboard - Employee Payroll");
        }
        else  if(Text2.equals("admin"))
        {
            grid1.setText("Admin Dashboard- Employee Payroll");
        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.image)
                {
                    image.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                }
            }
        });

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        search.onActionViewExpanded(); //new Added line
        search.setIconifiedByDefault(false);
        search.setQueryHint("Search Here");
        search.setBackgroundColor(Color.parseColor("#ffb300"));

        if(!search.isFocused()) {
            search.clearFocus();
        }
        progressBar = (ProgressBar)findViewById(R.id.progressbar);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                listAdapter.getFilter().filter(text.toString());
                //listView.getSelectedItem();
                BackGround5 b=new BackGround5();
                b.execute(text,text,text,text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                listAdapter.getFilter().filter(text.toString());
                return false;
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                payroll ListViewClickData = (payroll) parent.getItemAtPosition(position);
                Toast.makeText(EmpPayroll.this, ListViewClickData.getEmpid(), Toast.LENGTH_SHORT).show();
                BackGroundpay b=new BackGroundpay();
                b.execute(ListViewClickData.getEmpid(),ListViewClickData.getMonth(),ListViewClickData.getYear());

            }
        });

        new ParseJSonDataClass(this).execute();
        new ParseJSonDataClass1(this).execute();
    }


    class BackGroundpay extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String Empid = params[0];
            String month = params[1];
            String year = params[2];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/fetchpayslips.php");
                String urlParams = "empid=" + Empid + "&month=" + month+ "&year=" + year;

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
                JSONObject user_pay = root.getJSONObject("user_pay");
                EMPID = user_pay.getString("empid");
                MONTH = user_pay.getString("month");
                YEAR = user_pay.getString("year");
                PDFURL =user_pay.getString("url");

                Intent i = new Intent(EmpPayroll.this, EmpPayrollDoc.class);
                // i.putExtra("month", MONTH);
                i.putExtra("empid", EMPID);
                i.putExtra("url",PDFURL);
                i.putExtra("month",MONTH);
                i.putExtra("year",YEAR);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(EmpPayroll.this,"Related data is not there", Toast.LENGTH_SHORT).show();
            }

        }
    }

    class BackGround5 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String firstname = params[0];
            String middlename = params[1];
            String empid = params[2];
            String department = params[3];
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
                EMPID = user_data_profile.getString("empid");

                Intent i = new Intent(EmpPayroll.this,PayrollDocuments.class);
                i.putExtra("empid", EMPID);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("message1",Text1);
                i.putExtra("employee",Text2);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpPayroll.this,"Data Not Found", Toast.LENGTH_SHORT).show();
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
            listAdapter = new ListAdapter(EmpPayroll.this, R.layout.custom_layout, SubjectList);
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

                            payroll payroll1;

                            PayList = new ArrayList<payroll>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("name").toString();
                                String tempFullForm = jsonObject.getString("empid").toString();
                                String tempFullForm1 = jsonObject.getString("month").toString();
                                String tempFullForm2 = jsonObject.getString("year").toString();
                                String tempFullForm3 = jsonObject.getString("created_date").toString();

                                payroll1 = new payroll(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                PayList.add(payroll1);
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
            payrollAdapter = new PayrollAdapter(EmpPayroll.this, R.layout.cardview, PayList);
            listView2.setAdapter(payrollAdapter);
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
           /* Intent intent=new Intent(EditProfile.this,QueryForm.class);
            startActivity(intent);*/
            Grid1=grid1.getText().toString();
            Grid = grid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Grid);
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

                Intent i = new Intent(EmpPayroll.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Grid1);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpPayroll.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
