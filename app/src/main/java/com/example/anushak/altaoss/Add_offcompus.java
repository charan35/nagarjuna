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

public class Add_offcompus extends AppCompatActivity {

    String HttpURL = "http://altaitsolutions.com/Altadatabase/offcompus_list.php";

    ListView listView;
    SearchView search;
    ArrayList<interview_updates> interviewUpdatesList = new ArrayList<interview_updates>();

    Interview_Adapter interviewAdapter;

    static String FNAME = null, MNAME = null, EMAIL = null, MOBILE = null, PROJECT = null;

    android.support.v7.widget.Toolbar toolbar;
    LinearLayout lq,lq1;
    ImageView iv25,iv251;
    TextView empid,text,text1,admin,admin1,dashboard,dashboard1;
    String Empid,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_add_offcompus);

        listView = (ListView) findViewById(R.id.list);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        admin = (TextView)findViewById(R.id.admin);
        admin1 = (TextView)findViewById(R.id.admin1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);

        Empid = getIntent().getStringExtra("empid");
        Text = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        text.setText(Text);

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Interviews Dashboard - OffCampus");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Interviews Dashboard - OffCampus");
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
                Intent intent = new Intent(Add_offcompus.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_offcompus.this,Interviews_dashboard.class);
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
                Intent intent = new Intent(Add_offcompus.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_offcompus.this,Interviews_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                interview_updates ListViewClickData = (interview_updates) parent.getItemAtPosition(position);
                Toast.makeText(Add_offcompus.this, ListViewClickData.getProName(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Add_offcompus.this,Offcompus_single.class);

                intent.putExtra("name",ListViewClickData.getProName());
                intent.putExtra("qualification",ListViewClickData.getPronotes());
                intent.putExtra("email",ListViewClickData.getProEmpId());
                intent.putExtra("phone",ListViewClickData.getDateandTime());
                intent.putExtra("project",ListViewClickData.getProject());
                intent.putExtra("empid",Empid);
                intent.putExtra("employee",Text);

                startActivity(intent);


            }
        });


        search = (SearchView) findViewById(R.id.searchupdates);

        search.onActionViewExpanded(); //new Added line
        search.setIconifiedByDefault(false);
        search.setQueryHint("Search Here");

        if (!search.isFocused()) {
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

                interviewAdapter.getFilter().filter(text.toString());
                return false;
            }
        });
        new ParseJSonDataClass2(this).execute();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_contact:
                Intent intent=new Intent(Add_offcompus.this,Offcompus_reg.class);
                intent.putExtra("empid",Empid);
                intent.putExtra("employee",Text);
                startActivity(intent);
                return true;
            case R.id.action_about:
                Empid = empid.getText().toString();
                Text1 = text1.getText().toString();
                BackGroundQuery b = new BackGroundQuery();
                b.execute(Empid);
                break;

        }

        return super.onOptionsItemSelected(item);
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

                            interview_updates update;

                            interviewUpdatesList = new ArrayList<interview_updates>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("name").toString();
                                String tempFullForm = jsonObject.getString("qualification").toString();
                                String tempEmpid = jsonObject.getString("email").toString();
                                String tempDateandTime = jsonObject.getString("phone").toString();
                                String tempProject = jsonObject.getString("project").toString();

                                update = new interview_updates(tempName, tempFullForm, tempEmpid, tempDateandTime, tempProject);

                                interviewUpdatesList.add(update);
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
            interviewAdapter = new Interview_Adapter(Add_offcompus.this, R.layout.update_list, interviewUpdatesList);
            listView.setAdapter(interviewAdapter);
        }
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

                Intent i = new Intent(Add_offcompus.this, QueryForm.class);
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

                Toast.makeText(Add_offcompus.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
