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
import android.widget.ArrayAdapter;
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
import java.util.HashSet;
import java.util.List;

public class EmpProjects extends AppCompatActivity implements View.OnClickListener{

    ListView MobileDetailsListView,MobileDetailsListView1,MobileDetailsListView2,MobileDetailsListView3;
    ProgressBar MobileProgressBar;
    String HttpUrl = "http://altaitsolutions.com/Altadatabase/MobileData.php";
    String HttpUrl1 = "http://altaitsolutions.com/Altadatabase/ongoing.php";
    String HttpUrl2 = "http://altaitsolutions.com/Altadatabase/completedprojects.php";
    String HttpUrl3 = "http://altaitsolutions.com/Altadatabase/futureprojects.php";
    List<String> MobileList= new ArrayList<String>();
    List<String> MobileList1 = new ArrayList<String>();
    List<String> MobileList2 = new ArrayList<String>();
    List<String> MobileList3 = new ArrayList<String>();
    ArrayAdapter<String> MobileArrayAdapter,MobileArrayAdapter1,MobileArrayAdapter2,MobileArrayAdapter3 ;
    String PROJECTNAME,CLIENTNAME,STARTDATE,ENDDATE,MANAGERNAME,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,TEXT;
    TextView name,text1,grid,grid1,ongoing;
    ImageView image;
    SearchView searchView;

    LinearLayout lq,lq1,l1,l2,l3,l4;
    TextView admin,admin1;
    ImageView iv25,iv251;
    TextView text2;
    String Text2;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_projects);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileDetailsListView = (ListView)findViewById(R.id.listview1);
        MobileDetailsListView1 = (ListView)findViewById(R.id.listview2);
        MobileDetailsListView2 = (ListView)findViewById(R.id.listview3);
        MobileDetailsListView3 = (ListView)findViewById(R.id.listview4);
        name = (TextView) findViewById(R.id.name);
        searchView = (SearchView) findViewById(R.id.search);
        text1 = (TextView) findViewById(R.id.text1);
        image = (ImageView) findViewById(R.id.image);
        grid = (TextView)findViewById(R.id.Grid);
        grid1 = (TextView)findViewById(R.id.Grid1);
        MobileProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        admin = (TextView) findViewById(R.id.admin);
        admin1 = (TextView) findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        text2 = (TextView) findViewById(R.id.text2);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3 = (LinearLayout) findViewById(R.id.l3);
        l4 = (LinearLayout) findViewById(R.id.l4);
        ongoing = (TextView)findViewById(R.id.ongoing);

        EMPID = getIntent().getStringExtra("empid");
        Text2 = getIntent().getStringExtra("employee");
        grid.setText(EMPID);
        text2.setText(Text2);

        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        l3.setOnClickListener(this);
        l4.setOnClickListener(this);

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

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpProjects.this,HrDashboard.class);
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
                Intent intent = new Intent(EmpProjects.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("hr"))
        {
            grid1.setText("HR Dashboard - Project List");
        }
        else  if(Text2.equals("admin"))
        {
            grid1.setText("Admin Dashboard - Project List");
        }


        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.onActionViewExpanded(); //new Added line
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Here");
        searchView.setBackgroundColor(Color.parseColor("#0bcce4"));

    /*SET THE ADAPTER TO LISTVIEW*/
        new GetHttpResponse(EmpProjects.this).execute();
        new GetHttpResponse1(EmpProjects.this).execute();
        new GetHttpResponse2(EmpProjects.this).execute();
        new GetHttpResponse3(EmpProjects.this).execute();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                MobileArrayAdapter.getFilter().filter(text);
                BackGroundempdir b = new BackGroundempdir();
                b.execute(text);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                MobileArrayAdapter.getFilter().filter(text);
                return false;
            }
        });

        MobileDetailsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                EMPID = grid.getText().toString();
                BackGroundempdir b = new BackGroundempdir();
                b.execute(MobileList.get(position).toString());
                /*Intent intent = new Intent(getApplicationContext(),ShowDetailsActivity.class);
                intent.putExtra("ListViewValue", MobileList.get(position).toString());
                startActivity(intent);*/
            }
        });

        MobileDetailsListView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                EMPID = grid.getText().toString();
                BackGroundempdir b = new BackGroundempdir();
                b.execute(MobileList1.get(position).toString());
            }
        });
        MobileDetailsListView2.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                EMPID = grid.getText().toString();
                BackGroundempdir b = new BackGroundempdir();
                b.execute(MobileList2.get(position).toString());
            }
        });
        MobileDetailsListView3.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                EMPID = grid.getText().toString();
                BackGroundempdir b = new BackGroundempdir();
                b.execute(MobileList3.get(position).toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.l1:
                MobileDetailsListView.setVisibility(View.GONE);
                MobileDetailsListView1.setVisibility(View.VISIBLE);
                MobileDetailsListView2.setVisibility(View.GONE);
                MobileDetailsListView3.setVisibility(View.GONE);
                name.setText("Ongoing Projects- "+MobileDetailsListView1.getAdapter().getCount());
                Toast.makeText(getApplicationContext(), "Total number of Items are:" + MobileDetailsListView1.getAdapter().getCount() , Toast.LENGTH_SHORT).show();
                break;
            case R.id.l2:
                // set editbox invivible
                MobileDetailsListView.setVisibility(View.GONE);
                MobileDetailsListView1.setVisibility(View.GONE);
                MobileDetailsListView2.setVisibility(View.VISIBLE);
                MobileDetailsListView3.setVisibility(View.GONE);
                name.setText("Completed Projects- "+MobileDetailsListView2.getAdapter().getCount());
                Toast.makeText(getApplicationContext(), "Total number of Items are:" + MobileDetailsListView2.getAdapter().getCount() , Toast.LENGTH_SHORT).show();
                break;
            case R.id.l3:
                // set editbox invivible
                MobileDetailsListView.setVisibility(View.GONE);
                MobileDetailsListView1.setVisibility(View.GONE);
                MobileDetailsListView2.setVisibility(View.GONE);
                MobileDetailsListView3.setVisibility(View.VISIBLE);
                name.setText("Future Projects- "+MobileDetailsListView3.getAdapter().getCount());
                Toast.makeText(getApplicationContext(), "Total number of Items are:" + MobileDetailsListView3.getAdapter().getCount() , Toast.LENGTH_SHORT).show();
                break;
            case R.id.l4:
                // set editbox invivible
                MobileDetailsListView.setVisibility(View.VISIBLE);
                MobileDetailsListView1.setVisibility(View.GONE);
                MobileDetailsListView2.setVisibility(View.GONE);
                MobileDetailsListView3.setVisibility(View.GONE);
                name.setText("Total Projects- "+MobileDetailsListView.getAdapter().getCount());
                Toast.makeText(getApplicationContext(), "Total number of Items are:" + MobileDetailsListView.getAdapter().getCount() , Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServiceClass httpServicesClass = new HttpServiceClass(HttpUrl);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            for(int i=0; i<jsonArray.length(); i++)

                            {
                                jsonObject = jsonArray.getJSONObject(i);

                                MobileList.add(jsonObject.getString("projectname").toString()) ;

                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            MobileProgressBar.setVisibility(View.GONE);

            MobileDetailsListView.setVisibility(View.VISIBLE);

            // Start code for remove duplicate listview values.

            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(MobileList);
            MobileList.clear();
            MobileList.addAll(hashSet);

            //End code here for remove duplicate values.
            MobileArrayAdapter = new ArrayAdapter<String>(EmpProjects.this,android.R.layout.simple_list_item_1,android.R.id.text1, MobileList);

            MobileDetailsListView.setAdapter(MobileArrayAdapter);

        }
    }

    private class GetHttpResponse1 extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        public GetHttpResponse1(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServiceClass httpServicesClass = new HttpServiceClass(HttpUrl1);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            for(int j=0; j<jsonArray.length(); j++)
                            {
                                jsonObject = jsonArray.getJSONObject(j);
                                MobileList1.add(jsonObject.getString("projectname").toString()) ;
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            MobileProgressBar.setVisibility(View.GONE);

           // MobileDetailsListView1.setVisibility(View.VISIBLE);

            // Start code for remove duplicate listview values.

            HashSet<String> hashSet1 = new HashSet<String>();

            hashSet1.addAll(MobileList1);
            MobileList1.clear();
            MobileList1.addAll(hashSet1);

            //End code here for remove duplicate values.
            MobileArrayAdapter1 = new ArrayAdapter<String>(EmpProjects.this,android.R.layout.simple_list_item_1,android.R.id.text1, MobileList1);

            MobileDetailsListView1.setAdapter(MobileArrayAdapter1);

        }
    }

    private class GetHttpResponse2 extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        public GetHttpResponse2(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServiceClass httpServicesClass = new HttpServiceClass(HttpUrl2);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            for(int j=0; j<jsonArray.length(); j++)
                            {
                                jsonObject = jsonArray.getJSONObject(j);
                                MobileList2.add(jsonObject.getString("projectname").toString()) ;
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            MobileProgressBar.setVisibility(View.GONE);

            // MobileDetailsListView1.setVisibility(View.VISIBLE);

            // Start code for remove duplicate listview values.

            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(MobileList2);
            MobileList2.clear();
            MobileList2.addAll(hashSet);

            //End code here for remove duplicate values.
            MobileArrayAdapter2 = new ArrayAdapter<String>(EmpProjects.this,android.R.layout.simple_list_item_1,android.R.id.text1, MobileList2);

            MobileDetailsListView2.setAdapter(MobileArrayAdapter2);

        }
    }

    private class GetHttpResponse3 extends AsyncTask<Void, Void, Void> {
        public Context context;

        String JSonResult;

        public GetHttpResponse3(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpServiceClass httpServicesClass = new HttpServiceClass(HttpUrl3);
            try
            {
                httpServicesClass.ExecutePostRequest();

                if(httpServicesClass.getResponseCode() == 200)
                {
                    JSonResult = httpServicesClass.getResponse();

                    if(JSonResult != null)
                    {
                        JSONArray jsonArray = null;

                        try {
                            jsonArray = new JSONArray(JSonResult);

                            JSONObject jsonObject;

                            for(int j=0; j<jsonArray.length(); j++)
                            {
                                jsonObject = jsonArray.getJSONObject(j);
                                MobileList3.add(jsonObject.getString("projectname").toString());
                            }
                        }
                        catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                else
                {
                    Toast.makeText(context, httpServicesClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)

        {
            MobileProgressBar.setVisibility(View.GONE);

            // MobileDetailsListView1.setVisibility(View.VISIBLE);

            // Start code for remove duplicate listview values.

            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(MobileList3);
            MobileList3.clear();
            MobileList3.addAll(hashSet);

            //End code here for remove duplicate values.
            MobileArrayAdapter3 = new ArrayAdapter<String>(EmpProjects.this,android.R.layout.simple_list_item_1,android.R.id.text1, MobileList3);

            MobileDetailsListView3.setAdapter(MobileArrayAdapter3);

        }
    }

    class BackGroundempdir extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String projectname = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/new1.php");
                String urlParams = "projectname="+projectname;

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
                PROJECTNAME = user_data_profile.getString("projectname");
                CLIENTNAME = user_data_profile.getString("clientname");
                STARTDATE = user_data_profile.getString("startdate");
                ENDDATE = user_data_profile.getString("enddate");
                MANAGERNAME = user_data_profile.getString("firstname");

                Intent i = new Intent(EmpProjects.this, ShowDetailsActivity.class);
                i.putExtra("projectname", PROJECTNAME);
                i.putExtra("clientname", CLIENTNAME);
                i.putExtra("startdate", STARTDATE);
                i.putExtra("enddate", ENDDATE);
                i.putExtra("firstname", MANAGERNAME);
                i.putExtra("empid",EMPID);
                i.putExtra("employee",Text2);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpProjects.this,"Related Data is not there.", Toast.LENGTH_LONG).show();
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
        if(id == R.id.query)
        {
            TEXT = grid1.getText().toString();
            EMPID = grid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(EMPID);
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

                Intent i = new Intent(EmpProjects.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", TEXT);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmpProjects.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}