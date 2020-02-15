package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

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

public class Conveyances extends AppCompatActivity {

    SwipeMenuListView listView;
    ListView approvelist,rejectlist;
    ProgressBar progressBar;
    ArrayList<Reimbursement> ReimbursementList = new ArrayList<Reimbursement>();
    ArrayList<Reimbursement> ReimbursementList1 = new ArrayList<Reimbursement>();
    ArrayList<Reimbursement> ReimbursementList2 = new ArrayList<Reimbursement>();
    String HttpURL = "http://altaitsolutions.com/Altadatabase/conveyances.php";
    String HttpURL1 = "http://altaitsolutions.com/Altadatabase/conveyanceapprove.php";
    String HttpURL2 = "http://altaitsolutions.com/Altadatabase/conveyancereject.php";
    ReimbursementAdapter reimbursementAdapter,reimbursementAdapter1,reimbursementAdapter2;
    TextView empid,text,text1,dashboard,dashboard1,reimbursement,reimbursement1;
    ImageView iv25,iv251;
    HorizontalScrollView lq,lq1;
    android.support.v7.widget.Toolbar toolbar;

    String Empid,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,CATEGORY,CLAIMEDAMOUNT,TYPE,DATE,PDFURL,STATUS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_conveyances);

        listView = (SwipeMenuListView)findViewById(R.id.listView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        dashboard = (TextView)findViewById(R.id.dashboard);
        dashboard1 = (TextView)findViewById(R.id.dashboard1);
        reimbursement = (TextView)findViewById(R.id.reimbursement);
        reimbursement1 = (TextView)findViewById(R.id.reimbursement1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        approvelist = (ListView)findViewById(R.id.approvelist);
        rejectlist = (ListView)findViewById(R.id.rejectlist);

        Empid = getIntent().getStringExtra("empid");
        Text = getIntent().getStringExtra("employee");

        empid.setText(Empid);
        text.setText(Text);

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv251.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Conveyances.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        reimbursement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Conveyances.this,ReimbursementDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Conveyances.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        reimbursement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Conveyances.this,ReimbursementDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Reimbursement Dashboard - Conveyances");
        }
        else if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Reimbursement Dashboard - Conveyances");
        }
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
                        0xff)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set item title
                deleteItem.setTitle("Close");
                // set item title fontsize
                deleteItem.setTitleSize(18);
                // set item title font color
                deleteItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Reimbursement value = reimbursementAdapter.getItem(position);
                switch (index) {
                    case 0:
                       // Toast.makeText(Conveyances.this,"Open",Toast.LENGTH_LONG).show();
                       /* BackGround b = new BackGround();
                        b.execute(value.getRId());*/
                        BackGroundMobile b = new BackGroundMobile();
                        b.execute(value.getRType(),value.getRId(),value.getRDate(),value.getRStatus());
                        break;
                    case 1:
                        Toast.makeText(Conveyances.this,"Close", Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });


        new ParseJSonDataClass(Conveyances.this).execute();
        new ParseJSonDataClass1(Conveyances.this).execute();
        new ParseJSonDataClass2(Conveyances.this).execute();
    }

    class BackGroundMobile extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String category = params[1];
            String date = params[2];
            String status = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/conveyancefetch.php");
                String urlParams = "empid=" + empid+ "&Type=" + category + "&date=" + date+ "&status=" + status;

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
                JSONObject user_data_profile = root.getJSONObject("user_data_profile");
                EMPID = user_data_profile.getString("empid");
                CATEGORY = user_data_profile.getString("category");
                DATE = user_data_profile.getString("date");
                STATUS = user_data_profile.getString("status");
                TYPE = user_data_profile.getString("Type");
                CLAIMEDAMOUNT = user_data_profile.getString("ClaimedAmount");
                PDFURL = user_data_profile.getString("PdfURL");

                Intent i = new Intent(Conveyances.this, ConveyancesUpdate.class);
                i.putExtra("empid",EMPID);
                i.putExtra("category",CATEGORY);
                i.putExtra("date",DATE);
                i.putExtra("status",STATUS);
                i.putExtra("Type",TYPE);
                i.putExtra("ClaimedAmount",CLAIMEDAMOUNT);
                i.putExtra("PdfURL",PDFURL);
                i.putExtra("employee",Text);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Conveyances.this,"Error", Toast.LENGTH_SHORT).show();
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

                            Reimbursement reimbursement;

                            ReimbursementList = new ArrayList<Reimbursement>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("empid").toString();
                                String tempFullForm = jsonObject.getString("Type").toString();
                                String tempFullForm1 = jsonObject.getString("date").toString();
                                String tempFullForm2 = jsonObject.getString("status").toString();

                                reimbursement = new Reimbursement(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                ReimbursementList.add(reimbursement);
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
            reimbursementAdapter = new ReimbursementAdapter(Conveyances.this, R.layout.reimbursement_layout, ReimbursementList);
            listView.setAdapter(reimbursementAdapter);

        }
    }

    //Approve
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

                            Reimbursement reimbursement1;

                            ReimbursementList1 = new ArrayList<Reimbursement>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("empid").toString();
                                String tempFullForm = jsonObject.getString("Type").toString();
                                String tempFullForm1 = jsonObject.getString("date").toString();
                                String tempFullForm2 = jsonObject.getString("status").toString();

                                reimbursement1 = new Reimbursement(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                ReimbursementList1.add(reimbursement1);
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
            reimbursementAdapter1 = new ReimbursementAdapter(Conveyances.this, R.layout.reimbursement_layout, ReimbursementList1);
            approvelist.setAdapter(reimbursementAdapter1);

        }
    }

    //Reject
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

                            Reimbursement reimbursement2;

                            ReimbursementList2 = new ArrayList<Reimbursement>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("empid").toString();
                                String tempFullForm = jsonObject.getString("Type").toString();
                                String tempFullForm1 = jsonObject.getString("date").toString();
                                String tempFullForm2 = jsonObject.getString("status").toString();

                                reimbursement2 = new Reimbursement(tempName, tempFullForm, tempFullForm1,tempFullForm2);

                                ReimbursementList2.add(reimbursement2);
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
            reimbursementAdapter2 = new ReimbursementAdapter(Conveyances.this, R.layout.reimbursement_layout, ReimbursementList2);
            rejectlist.setAdapter(reimbursementAdapter2);

        }
    }
    //Query
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        getMenuInflater().inflate(R.menu.reimbursement_menu,menu);
        getMenuInflater().inflate(R.menu.refresh_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if (id == R.id.query)
        {
            Text1=text1.getText().toString();
            Empid = empid.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Empid);
        }
        if (id == R.id.approve)
        {
            listView.setVisibility(View.GONE);
            approvelist.setVisibility(View.VISIBLE);
            rejectlist.setVisibility(View.GONE);
        }
        if (id == R.id.reject)
        {
            listView.setVisibility(View.GONE);
            rejectlist.setVisibility(View.VISIBLE);
            approvelist.setVisibility(View.GONE);
        }
        if (id == R.id.refresh)
        {
            listView.setVisibility(View.VISIBLE);
            rejectlist.setVisibility(View.GONE);
            approvelist.setVisibility(View.GONE);
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

                Intent i = new Intent(Conveyances.this, QueryForm.class);
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

                Toast.makeText(Conveyances.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
