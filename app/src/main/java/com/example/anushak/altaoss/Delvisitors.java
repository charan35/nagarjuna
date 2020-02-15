package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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

public class Delvisitors extends AppCompatActivity {

    ListView listView;
    ProgressBar progressBar;
    ArrayList<Viewvisitor> VisitorsList = new ArrayList<Viewvisitor>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/visitor_list.php";
    String FirstName, LastName, PhoneNum, EmailAddress, ContactPerson, CompanyName, Companybranch, Reasonforvisit, DateofVisit, Personalid, Typeofvisit, Empid;
    ViewvisitorAdapter viewvisitorAdapter;
    android.support.v7.widget.Toolbar toolbar;
    ImageView iv25;
    TextView text,empid,admin,dashboard;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_delvisitors);

        iv25 = (ImageView)findViewById(R.id.iv25);
        dashboard = (TextView)findViewById(R.id.dashboard);
        admin = (TextView)findViewById(R.id.admin);
        text = (TextView)findViewById(R.id.text);
        empid = (TextView)findViewById(R.id.empid);
        listView = (ListView)findViewById(R.id.vislistview);
        progressBar = (ProgressBar)findViewById(R.id.visprogress);
        searchView = (SearchView) findViewById(R.id.searchupdates);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Empid = getIntent().getStringExtra("empid");
        empid.setText(Empid);

        text.setText("Admin Dashboard - Visitor Dashboard - Delete/Edit Visitors");

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delvisitors.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delvisitors.this,Admin_Visitor_dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Viewvisitor ListViewClickData = (Viewvisitor) parent.getItemAtPosition(position);
                Toast.makeText(Delvisitors.this, ListViewClickData.getVFName(), Toast.LENGTH_SHORT).show();
                BackGroundviewvisitor b=new BackGroundviewvisitor();
                b.execute(ListViewClickData.getVFName(), ListViewClickData.getVLName(), ListViewClickData.getVReasonforvisit(), ListViewClickData.getVCName(), ListViewClickData.getVCPName(), ListViewClickData.getVDVisit());
            }
        });

        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search By Name/Date");

        if (!searchView.isFocused()) {
            searchView.clearFocus();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                viewvisitorAdapter.getFilter().filter(text.toString());
                return false;
            }
        });

        new ParseJSonDataClass(Delvisitors.this).execute();

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

                            Viewvisitor viewvisitor;

                            VisitorsList = new ArrayList<Viewvisitor>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("fname").toString();
                                String tempFullForm = jsonObject.getString("lname").toString();
                                String tempFullForm1 = jsonObject.getString("reasonforvisit").toString();
                                String tempName2 = jsonObject.getString("companyname").toString();
                                String tempFullForm3 = jsonObject.getString("contactperson").toString();
                                String tempFullForm14 = jsonObject.getString("dateofvisit").toString();

                                viewvisitor = new Viewvisitor(tempName, tempFullForm, tempFullForm1, tempName2, tempFullForm3, tempFullForm14);

                                VisitorsList.add(viewvisitor);
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
            viewvisitorAdapter = new ViewvisitorAdapter(Delvisitors.this, R.layout.visitorlayout, VisitorsList);
            listView.setAdapter(viewvisitorAdapter);
            Toast.makeText(context, "Total Number of Visitors are:" + listView.getAdapter().getCount(), Toast.LENGTH_SHORT).show();
        }
    }

    class BackGroundviewvisitor extends AsyncTask<String, String, String> {

        protected String doInBackground(String... params) {
            String Firstname = params[0];
            String Lastname = params[1];
            String Reasonforvisit = params[2];
            String Companyname = params[3];
            String Contactperson = params[4];
            String Dateofvisit = params[5];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/visitor_details.php");
                String urlParams = "fname=" + Firstname+ "&lname=" + Lastname+ "&reasonforvisit=" + Reasonforvisit+ "&companyname=" + Companyname+ "&contactperson=" + Contactperson+ "&dateofvisit=" + Dateofvisit;

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
                FirstName = user_data_profile.getString("fname");
                LastName = user_data_profile.getString("lname");
                PhoneNum= user_data_profile.getString("phone");
                ContactPerson = user_data_profile.getString("contactperson");
                EmailAddress = user_data_profile.getString("email");
                CompanyName = user_data_profile.getString("companyname");
                Companybranch = user_data_profile.getString("companybranch");
                Reasonforvisit = user_data_profile.getString("reasonforvisit");
                DateofVisit = user_data_profile.getString("dateofvisit");
                Personalid = user_data_profile.getString("personalid");
                Typeofvisit = user_data_profile.getString("typeofvisit");
                Empid = user_data_profile.getString("empid");

                Intent i = new Intent(Delvisitors.this, ShowSingleDelvisitor.class);
                i.putExtra("fname", FirstName);
                i.putExtra("lname", LastName);
                i.putExtra("phone", PhoneNum);
                i.putExtra("email", EmailAddress);
                i.putExtra("contactperson", ContactPerson);
                i.putExtra("companyname", CompanyName);
                i.putExtra("companybranch", Companybranch);
                i.putExtra("reasonforvisit", Reasonforvisit);
                i.putExtra("dateofvisit",DateofVisit);
                i.putExtra("personalid",Personalid);
                i.putExtra("typeofvisit",Typeofvisit);
                i.putExtra("empid",Empid);

                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Delvisitors.this, "invalid Details...!", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
