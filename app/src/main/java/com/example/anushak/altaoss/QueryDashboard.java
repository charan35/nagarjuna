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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

public class QueryDashboard extends AppCompatActivity {

    ListView listView,listView1,listView2,listView3,listView4;
    ArrayList<Query> QueryList = new ArrayList<Query>();
    ArrayList<Query> QueryList1 = new ArrayList<Query>();
    ArrayList<Query> QueryList2 = new ArrayList<Query>();
    ArrayList<Query> QueryList3 = new ArrayList<Query>();
    ArrayList<Query> QueryList4 = new ArrayList<Query>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/querylist.php";
    String HttpURL1 = "http://altaitsolutions.com/Altadatabase/HrpendingQueries.php";
    String HttpURL2 = "http://altaitsolutions.com/Altadatabase/HrresolvedQueries.php";
    String HttpURL3 = "http://altaitsolutions.com/Altadatabase/HrunresolveQueries.php";
    String HttpURL4 = "http://altaitsolutions.com/Altadatabase/HrunableQueries.php";
    QueryAdapter queryAdapter,queryAdapter1,queryAdapter2,queryAdapter3,queryAdapter4;
    ProgressBar progressBar;
    String EMPID,EMAIL,QUERY,STATUS,Text,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,Empid,Text2;
    android.support.v7.widget.Toolbar toolbar;
    TextView total,text1,text,dashboard,dashboard1,empid,text2;
    RelativeLayout relativeLayout;
    SearchView searchView;
    ImageView image,iv25,iv251;
    HorizontalScrollView lq,lq1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_query_dashboard);

        listView = (ListView) findViewById(R.id.listView);
        listView1 = (ListView)findViewById(R.id.listView1);
        listView2 = (ListView)findViewById(R.id.listView2);
        listView3 = (ListView)findViewById(R.id.listView3);
        listView4 = (ListView)findViewById(R.id.listView4);
        searchView = (SearchView) findViewById(R.id.search);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        total = (TextView) findViewById(R.id.total);
        text1 = (TextView) findViewById(R.id.text1);
        text = (TextView) findViewById(R.id.text);
        empid = (TextView) findViewById(R.id.empid);
        text2 = (TextView) findViewById(R.id.querytext);
        image = (ImageView) findViewById(R.id.image);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        relativeLayout = (RelativeLayout)findViewById(R.id.linear2);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.onActionViewExpanded(); //new Added line
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Here");
        searchView.setBackgroundColor(Color.parseColor("#0bcce4"));

        listView.setTextFilterEnabled(true);
        Empid = getIntent().getStringExtra("empid");
        Text = getIntent().getStringExtra("employee");
        text.setText(Text);
        empid.setText(Empid);

        new ParseJSonDataClass(QueryDashboard.this).execute();
        new ParseJSonDataClass1(QueryDashboard.this).execute();
        new ParseJSonDataClass2(QueryDashboard.this).execute();
        new ParseJSonDataClass3(QueryDashboard.this).execute();
        new ParseJSonDataClass4(QueryDashboard.this).execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Query ListViewClickData = (Query) parent.getItemAtPosition(position);
                Toast.makeText(QueryDashboard.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
                BackGround b = new BackGround();
                b.execute(ListViewClickData.getName(), ListViewClickData.getQueryName(), ListViewClickData.getEmail());
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Query ListViewClickData = (Query) parent.getItemAtPosition(position);
                Toast.makeText(QueryDashboard.this, ListViewClickData.getName(), Toast.LENGTH_SHORT).show();
                BackGround b = new BackGround();
                b.execute(ListViewClickData.getName(), ListViewClickData.getQueryName(), ListViewClickData.getEmail());
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.image)
                {
                    image.setVisibility(View.GONE);
                    text1.setVisibility(View.GONE);
                    searchView.setVisibility(View.VISIBLE);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                queryAdapter.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                return false;
            }
            @Override
            public boolean onQueryTextChange(String text) {

                queryAdapter.getFilter().filter(text);
                return false;
            }
        });

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueryDashboard.this,HrDashboard.class);
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
                Intent intent = new Intent(QueryDashboard.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        if(Text.equals("hr"))
        {
            text2.setText("HR Dashboard - Queries List");
        }
        else if(Text.equals("admin"))
        {
            text2.setText("Admin Dashboard - Queries List");
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

                            Query query;

                            QueryList = new ArrayList<Query>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("Empid").toString();
                                String tempFullForm = jsonObject.getString("Email").toString();
                                String tempFullForm1 = jsonObject.getString("ReasonName").toString();
                                String tempFullForm2 = jsonObject.getString("Activityname").toString();
                                String tempFullForm3 = jsonObject.getString("Status").toString();

                                query = new Query(tempName, tempFullForm, tempFullForm1,tempFullForm2, tempFullForm3);

                                QueryList.add(query);
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
            queryAdapter = new QueryAdapter(QueryDashboard.this, R.layout.query_layout, QueryList);
            listView.setAdapter(queryAdapter);
           total.setText("Updated Queries-"+ String.valueOf(listView.getCount()));
        }
    }

    private class ParseJSonDataClass1 extends AsyncTask<Void, Void, Void> {
        public Context context1;
        String FinalJSonResult1;

        public ParseJSonDataClass1(Context context) {

            this.context1 = context;
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

                    FinalJSonResult1 = httpServiceClass.getResponse();

                    if (FinalJSonResult1 != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult1);

                            JSONObject jsonObject;

                            Query query;

                            QueryList1 = new ArrayList<Query>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("Empid").toString();
                                String tempFullForm = jsonObject.getString("Email").toString();
                                String tempFullForm1 = jsonObject.getString("ReasonName").toString();
                                String tempFullForm2 = jsonObject.getString("Activityname").toString();
                                String tempFullForm3 = jsonObject.getString("Status").toString();

                                query = new Query(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                QueryList1.add(query);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context1, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            queryAdapter1 = new QueryAdapter(QueryDashboard.this, R.layout.query_layout, QueryList1);
            listView1.setAdapter(queryAdapter1);
        }
    }

    private class ParseJSonDataClass2 extends AsyncTask<Void, Void, Void> {
        public Context context2;
        String FinalJSonResult2;

        public ParseJSonDataClass2(Context context) {

            this.context2 = context;
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

                    FinalJSonResult2 = httpServiceClass.getResponse();

                    if (FinalJSonResult2 != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult2);

                            JSONObject jsonObject;

                            Query query;

                            QueryList2 = new ArrayList<Query>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("Empid").toString();
                                String tempFullForm = jsonObject.getString("Email").toString();
                                String tempFullForm1 = jsonObject.getString("ReasonName").toString();
                                String tempFullForm2 = jsonObject.getString("Activityname").toString();
                                String tempFullForm3 = jsonObject.getString("Status").toString();

                                query = new Query(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                QueryList2.add(query);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context2, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            queryAdapter2 = new QueryAdapter(QueryDashboard.this, R.layout.query_layout, QueryList2);
            listView2.setAdapter(queryAdapter2);
        }
    }

    private class ParseJSonDataClass3 extends AsyncTask<Void, Void, Void> {
        public Context context3;
        String FinalJSonResult3;

        public ParseJSonDataClass3(Context context) {

            this.context3 = context;
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

                    FinalJSonResult3 = httpServiceClass.getResponse();

                    if (FinalJSonResult3 != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult3);

                            JSONObject jsonObject;

                            Query query;

                            QueryList3 = new ArrayList<Query>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("Empid").toString();
                                String tempFullForm = jsonObject.getString("Email").toString();
                                String tempFullForm1 = jsonObject.getString("ReasonName").toString();
                                String tempFullForm2 = jsonObject.getString("Activityname").toString();
                                String tempFullForm3 = jsonObject.getString("Status").toString();

                                query = new Query(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                QueryList3.add(query);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context3, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            queryAdapter3 = new QueryAdapter(QueryDashboard.this, R.layout.query_layout, QueryList3);
            listView3.setAdapter(queryAdapter3);
        }
    }

    private class ParseJSonDataClass4 extends AsyncTask<Void, Void, Void> {
        public Context context4;
        String FinalJSonResult4;

        public ParseJSonDataClass4(Context context) {

            this.context4 = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL4);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult4 = httpServiceClass.getResponse();

                    if (FinalJSonResult4 != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult4);

                            JSONObject jsonObject;

                            Query query;

                            QueryList4 = new ArrayList<Query>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("Empid").toString();
                                String tempFullForm = jsonObject.getString("Email").toString();
                                String tempFullForm1 = jsonObject.getString("ReasonName").toString();
                                String tempFullForm2 = jsonObject.getString("Activityname").toString();
                                String tempFullForm3 = jsonObject.getString("Status").toString();

                                query = new Query(tempName, tempFullForm, tempFullForm1,tempFullForm2,tempFullForm3);

                                QueryList4.add(query);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context4, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
            queryAdapter4 = new QueryAdapter(QueryDashboard.this, R.layout.query_layout, QueryList4);
            listView4.setAdapter(queryAdapter4);
        }
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String email = params[1];
            String query = params[2];
           // String status = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/querystatus.php");
                String urlParams = "Empid=" + empid+ "&Email=" + email+ "&ReasonName=" + query;

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
                EMPID = user_data_profile.getString("Empid");
                EMAIL = user_data_profile.getString("Email");
                QUERY = user_data_profile.getString("ReasonName");
                STATUS = user_data_profile.getString("Activityname");
               // DEPARTMENT = user_data_profile.getString("department");

                Intent i = new Intent(QueryDashboard.this, QueryStatus.class);
                i.putExtra("Empid", EMPID);
                i.putExtra("Email", EMAIL);
                i.putExtra("ReasonName", QUERY);
                i.putExtra("Activityname", STATUS);
                i.putExtra("employee", Text);

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
        getMenuInflater().inflate(R.menu.totalqueries,menu);
        getMenuInflater().inflate(R.menu.filtermenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text2=text2.getText().toString();
            Empid = empid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Empid);
        }

        if(id == R.id.pending)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.VISIBLE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    // TODO Auto-generated method stub
                    queryAdapter1.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String text) {

                    queryAdapter1.getFilter().filter(text);
                    return false;
                }
            });
            total.setText("Pending Queries-"+ String.valueOf(listView1.getCount()));
        }
        if(id == R.id.resolved)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.VISIBLE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    // TODO Auto-generated method stub
                    queryAdapter2.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String text) {
                    queryAdapter2.getFilter().filter(text);
                    return false;
                }
            });
            total.setText("Resolved Queries-"+ String.valueOf(listView2.getCount()));
        }
        if(id == R.id.unresolved)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.VISIBLE);
            listView4.setVisibility(View.GONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    // TODO Auto-generated method stub
                    queryAdapter3.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String text) {
                    queryAdapter3.getFilter().filter(text);
                    return false;
                }
            });
            total.setText("UnResolved Queries-"+ String.valueOf(listView3.getCount()));
        }
        if(id == R.id.unable)
        {
            listView.setVisibility(View.GONE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.VISIBLE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    // TODO Auto-generated method stub
                    queryAdapter4.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String text) {
                    queryAdapter4.getFilter().filter(text);
                    return false;
                }
            });
            total.setText("Unable to Resolve Queries-"+ String.valueOf(listView4.getCount()));
        }
        if(id == R.id.totalquery)
        {
            listView.setVisibility(View.VISIBLE);
            listView1.setVisibility(View.GONE);
            listView2.setVisibility(View.GONE);
            listView3.setVisibility(View.GONE);
            listView4.setVisibility(View.GONE);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    // TODO Auto-generated method stub
                    queryAdapter.getFilter().filter(text);
                /*BackGroundempdir b = new BackGroundempdir();
                b.execute(text);*/
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String text) {

                    queryAdapter.getFilter().filter(text);
                    return false;
                }
            });
            total.setText("Updated Queries-"+ String.valueOf(listView.getCount()));
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

                Intent i = new Intent(QueryDashboard.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text2);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(QueryDashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
