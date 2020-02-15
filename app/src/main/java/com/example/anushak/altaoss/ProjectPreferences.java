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

public class ProjectPreferences extends AppCompatActivity {

    ListView MobileDetailsListView;
    ProgressBar MobileProgressBar;
    String HttpUrl = "http://altaitsolutions.com/Altadatabase/MobileData.php";
    List<String> MobileList = new ArrayList<String>();
    ArrayAdapter<String> MobileArrayAdapter ;
    String PROJECTNAME,CLIENTNAME,STARTDATE,ENDDATE,MANAGERNAME,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,TEXT;
    TextView name,text1,grid,grid1;
    ImageView image;
    SearchView searchView;
    ImageView iv25;
    TextView admin,preferences;
    android.support.v7.widget.Toolbar toolbar;
    String Text1,Text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_project_preferences);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileDetailsListView = (ListView)findViewById(R.id.listview1);
        name = (TextView) findViewById(R.id.name);
        searchView = (SearchView) findViewById(R.id.search);
        text1 = (TextView) findViewById(R.id.text1);
        image = (ImageView) findViewById(R.id.image);
        grid = (TextView)findViewById(R.id.Grid);
        grid1 = (TextView)findViewById(R.id.Grid1);
        MobileProgressBar = (ProgressBar)findViewById(R.id.progressBar);
        iv25 = (ImageView)findViewById(R.id.iv25);
        admin = (TextView) findViewById(R.id.admin);
        preferences = (TextView)findViewById(R.id.preferences);

        Text1 = getIntent().getStringExtra("empid");
        grid.setText(Text1);
        grid1.setText("Admin Dashboard - Preferences - Project Preferences");

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
                Intent intent = new Intent(ProjectPreferences.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectPreferences.this,Preferences.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.onActionViewExpanded(); //new Added line
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Here");
        searchView.setBackgroundColor(Color.parseColor("#0bcce4"));

        /*SET THE ADAPTER TO LISTVIEW*/
        new GetHttpResponse(ProjectPreferences.this).execute();

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
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
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
            MobileArrayAdapter = new ArrayAdapter<String>(ProjectPreferences.this,android.R.layout.simple_list_item_1,android.R.id.text1, MobileList);

            MobileDetailsListView.setAdapter(MobileArrayAdapter);

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
                Intent i = new Intent(ProjectPreferences.this, EmpList.class);
                i.putExtra("projectname", PROJECTNAME);
                i.putExtra("clientname", CLIENTNAME);
                i.putExtra("startdate", STARTDATE);
                i.putExtra("enddate", ENDDATE);
                i.putExtra("firstname", MANAGERNAME);
                i.putExtra("empid",Text1);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ProjectPreferences.this,"Related Data is not there.", Toast.LENGTH_LONG).show();
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
            Text2 = grid1.getText().toString();
            Text1 = grid.getText().toString();
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

                Intent i = new Intent(ProjectPreferences.this, QueryForm.class);
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

                Toast.makeText(ProjectPreferences.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}