package com.example.anushak.altaoss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Rewards extends AppCompatActivity {

    TextView empid,text,text1,dashboard,dashboard1;
    String Empid,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,Text,Text1;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo,iv25,iv251;

    String FinalHttpData = "";
    BufferedWriter bufferedWriter ;
    WebCallParse webCallParse = new WebCallParse();
    BufferedReader bufferedReader ;
    OutputStream outputStream ;
    StringBuilder stringBuilder = new StringBuilder();
    String Result ;
    ProgressDialog pDialog;
    String HttpURL = "http://altaitsolutions.com/Altadatabase/empprojects.php";
    String ParseResult ;
    HashMap<String, String> ResultHash = new HashMap<>();
    URL url;
    List<String> listString = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter ;
    String FinalJSonObject,EMPID ;
    ListView rewardslist;
    HorizontalScrollView lq,lq1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_rewards);

        rewardslist = (ListView)findViewById(R.id.listview);
        empid = (TextView)findViewById(R.id.empid);
        text = (TextView)findViewById(R.id.text);
        text1 = (TextView)findViewById(R.id.text1);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (ImageView)findViewById(R.id.logo);
        lq = (HorizontalScrollView) findViewById(R.id.lq);
        lq1 = (HorizontalScrollView) findViewById(R.id.lq1);
        iv25 = (ImageView)findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        Text1 = getIntent().getStringExtra("employee");
        Empid = getIntent().getStringExtra("empid");
        text1.setText(Text1);
        empid.setText(Empid);

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
                Intent intent = new Intent(Rewards.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        dashboard1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Rewards.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        lq.setVisibility(Text1.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text1.equals("hr")? View.VISIBLE : View.GONE);

        if(Text1.equals("employee"))
        {
            text.setText("Employee Dashboard - Rewards("+Empid+")");
        }
        else if(Text1.equals("hr"))
        {
            text.setText("HR Dashboard - Rewards("+Empid+")");
        }

        HttpWebCall(Empid);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(Rewards.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();
                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;
                //Parsing the Stored JSOn String.
                new GetHttpResponse(Rewards.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("CategoryName",params[0]);

                ParseResult = webCallParse.postRequest(ResultHash);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

    public class WebCallParse {

        public String postRequest(HashMap<String, String> Data) {

            try {
                url = new URL(HttpURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(12000);

                httpURLConnection.setConnectTimeout(12000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.setDoOutput(true);

                outputStream = httpURLConnection.getOutputStream();

                bufferedWriter = new BufferedWriter(

                        new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(FinalDataParse(Data));

                bufferedWriter.flush();

                bufferedWriter.close();

                outputStream.close();

                if (httpURLConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {

                    bufferedReader = new BufferedReader(
                            new InputStreamReader(
                                    httpURLConnection.getInputStream()
                            )
                    );
                    FinalHttpData = bufferedReader.readLine();
                }
                else {
                    FinalHttpData = "Something Went Wrong";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return FinalHttpData;
        }

        public String FinalDataParse(HashMap<String, String> hashMap2) throws UnsupportedEncodingException {

            for(Map.Entry<String, String> map_entry : hashMap2.entrySet()){

                stringBuilder.append("&");

                stringBuilder.append(URLEncoder.encode(map_entry.getKey(), "UTF-8"));

                stringBuilder.append("=");

                stringBuilder.append(URLEncoder.encode(map_entry.getValue(), "UTF-8"));

            }

            Result = stringBuilder.toString();

            return Result ;
        }
    }

    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

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
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);

                            listString.add(jsonObject.getString("projectname").toString()+"\n"+jsonObject.getString("taskstatus").toString()+"\n"+jsonObject.getString("date").toString()) ;
                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
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
        protected void onPostExecute(Void result) {

            arrayAdapter = new ArrayAdapter<>(Rewards.this, android.R.layout.simple_list_item_1, android.R.id.text1, listString);

            rewardslist.setAdapter(arrayAdapter);
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

                Intent i = new Intent(Rewards.this, QueryForm.class);
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

                Toast.makeText(Rewards.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
