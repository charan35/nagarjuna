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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class OurUpdates extends AppCompatActivity {

    Button bt1;
    ListView listView;
    TextView empid,text;
    String Empid,MANAGERMAIL,MANAGERID,HRMAIL,HRID,OFFICIALEMAIL;
    ArrayList<updates> UpdatesList = new ArrayList<updates>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/ProjectsList.php";
    UpdatesAdapter updatesAdapter;
    String FIRSTNAME = null, EMPID = null,Text;
    SearchView search;
    ImageView iv15,iv151,iv152;

    TextView dashboard,dashboard1,dashboard2;
    LinearLayout lemp,lhr,ladmin;
    TextView text1;
    String Text1;
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_our_updates);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.list);
        bt1 = (Button) findViewById(R.id.newupdate);
        search = (SearchView) findViewById(R.id.searchupdates);
        empid = (TextView) findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.Grid2);
        iv15 = (ImageView) findViewById(R.id.iv15);
        dashboard = (TextView) findViewById(R.id.dashboard);

        iv151 = (ImageView) findViewById(R.id.iv151);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);

        iv152 = (ImageView) findViewById(R.id.iv152);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);

        text1 = (TextView) findViewById(R.id.text1);

        lemp = (LinearLayout) findViewById(R.id.lemp);
        lhr = (LinearLayout) findViewById(R.id.lhr);
        ladmin = (LinearLayout) findViewById(R.id.ladmin);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        Text1 = getIntent().getStringExtra("employee");
        text1.setText(Text1);

        listView.setTextFilterEnabled(true);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text1 = text1.getText().toString();
                BackGround7 b = new BackGround7();
                b.execute(Empid);
            }
        });

        iv15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OurUpdates.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv152.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OurUpdates.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv151.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OurUpdates.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        lemp.setVisibility(Text1.equals("employee")? View.VISIBLE : View.GONE);
        lhr.setVisibility(Text1.equals("hr")? View.VISIBLE : View.GONE);
        ladmin.setVisibility(Text1.equals("admin")? View.VISIBLE : View.GONE);

        if(Text1.equals("employee"))
        {
            text.setText("Employee Dashboard - Our Updates"+ "("+Empid+")");
        }
        else if(Text1.equals("hr"))
        {
            text.setText("HR Dashboard - Our Updates"+ "("+Empid+")");
        }
        else  if(Text1.equals("admin"))
        {
            text.setText("Admin Dashboard - Our Updates"+ "("+Empid+")");
        }

        search.onActionViewExpanded(); //new Added line
        search.setIconifiedByDefault(false);
        search.setQueryHint("Search Here");

        if(!search.isFocused()) {
            search.clearFocus();
        }

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                updatesAdapter.getFilter().filter(text.toString());
                return false;
            }
        });
        new ParseJSonDataClass2(this).execute();
    }

    class BackGround7 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/commonfile.php");
                String urlParams = "empid="+empid;

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

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                EMPID=user_data.getString("empid");

                Intent i = new Intent(OurUpdates.this, NewUpdate.class);
                i.putExtra("empid", EMPID);
                i.putExtra("employee",Text1);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
            }
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

                            updates update;

                            UpdatesList = new ArrayList<updates>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("projectname").toString();
                                String tempFullForm = jsonObject.getString("notes").toString();
                                String tempEmpid = jsonObject.getString("empid").toString();
                                String tempDateandTime = jsonObject.getString("date").toString();

                                update = new updates(tempName, tempFullForm, tempEmpid, tempDateandTime);

                                UpdatesList.add(update);
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
            updatesAdapter = new UpdatesAdapter(OurUpdates.this, R.layout.update_list, UpdatesList);
            listView.setAdapter(updatesAdapter);
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
            Text=text.getText().toString();
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

                Intent i = new Intent(OurUpdates.this, QueryForm.class);
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

                Toast.makeText(OurUpdates.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
