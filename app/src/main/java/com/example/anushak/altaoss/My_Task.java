package com.example.anushak.altaoss;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class My_Task extends AppCompatActivity {


    TextView empid,text,text1,officialmail,department;
    String Empid,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    String FinalHttpData = "";
    BufferedWriter bufferedWriter ;
    WebCallParse webCallParse = new WebCallParse();
    BufferedReader bufferedReader ;
    OutputStream outputStream ;
    StringBuilder stringBuilder = new StringBuilder();
    String Result;
    ListView TicketsListView;
    ProgressBar progressBar;
    ProgressDialog pDialog;
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();
    URL url;
    ArrayList<Tickets> TicketsList = new ArrayList<Tickets>();
    String HttpURL = "http://192.168.1.54/Ticketing_Tool/my_tasks.php";
    Total_Tickets_Adapter total_tickets_adapter;
    String FinalJSonObject;

    String Name,Email,Title,Date,Subject,Discription,Priority,Status,Officialemail,Department,Department1;
    ImageView preview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__task);

        empid = (TextView)findViewById(R.id.empid);
        officialmail = (TextView)findViewById(R.id.officialmail);

        TicketsListView = (ListView)findViewById(R.id.list);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Empid = getIntent().getStringExtra("empid");

        Officialemail = getIntent().getStringExtra("officialemail");

        department=(TextView)findViewById(R.id.department);
        Department ="hai";

        Department=getIntent().getStringExtra("department");
        department.setText(Department);

        officialmail.setText(Officialemail);

        preview=findViewById(R.id.preview);

        TicketsListView.setEmptyView(preview); //if list is empty it will show image as empty

        empid.setText(Empid);

        Department1="Admin";

        if(Department.equals(Department1)){

            TicketsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Tickets ListViewClickData = (Tickets) parent.getItemAtPosition(position);

                    Toast.makeText(My_Task.this, ListViewClickData.getTicketTitle(), Toast.LENGTH_SHORT).show();

                    My_Task.BackGround1 b=new My_Task.BackGround1();
                    b.execute( ListViewClickData.getTicketTitle(),ListViewClickData.getTicketEmpid(),ListViewClickData.getTicketDate(),ListViewClickData.getTicketStatus(),ListViewClickData.getTicketPriority());


                }
            });

        }else {

            TicketsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    Tickets ListViewClickData = (Tickets) parent.getItemAtPosition(position);

                    Toast.makeText(My_Task.this, ListViewClickData.getTicketTitle(), Toast.LENGTH_SHORT).show();

                    My_Task.BackGround b = new My_Task.BackGround();
                    b.execute(ListViewClickData.getTicketTitle(), ListViewClickData.getTicketEmpid(), ListViewClickData.getTicketDate(), ListViewClickData.getTicketStatus(), ListViewClickData.getTicketPriority());


                }
            });
        }


        HttpWebCall(Empid);
    }

    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(My_Task.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();
                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;
                //Parsing the Stored JSOn String.
                new ParseJSonDataClass(My_Task.this).execute();

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

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {

        public Context context;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);

                        JSONObject jsonObject;

                        Tickets tickets;

                        TicketsList = new ArrayList<Tickets>();

                        for (int i = 0; i < jsonArray.length(); i++) {

                            jsonObject = jsonArray.getJSONObject(i);

                            String Title = jsonObject.getString("title").toString();
                            String Empid = jsonObject.getString("empid").toString();
                            String Date = jsonObject.getString("date").toString();
                            String Status = jsonObject.getString("status").toString();
                            String Priority = jsonObject.getString("priority").toString();

                            tickets = new Tickets(Title, Empid, Date, Status, Priority);
                            TicketsList.add(tickets);
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
        protected void onPostExecute(Void result)

        {
            progressBar.setVisibility(View.INVISIBLE);
            total_tickets_adapter = new Total_Tickets_Adapter(My_Task.this, R.layout.ticket_layout, TicketsList);
            TicketsListView.setAdapter(total_tickets_adapter);

        }
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String empid = params[1];
            String date = params[2];
            String status = params[3];
            String priority = params[4];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://192.168.1.54/Ticketing_Tool/tickets_data.php");
                String urlParams = "title=" + title + "&empid=" + empid + "&date=" + date + "&status=" + status + "&priority=" + priority;

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
                JSONObject user_data1 = root.getJSONObject("user_data1");
                Name = user_data1.getString("name");
                Empid = user_data1.getString("empid");
                Email = user_data1.getString("email");
                Title = user_data1.getString("title");
                Date = user_data1.getString("date");
                Subject = user_data1.getString("subject");
                Discription = user_data1.getString("discription");
                Priority = user_data1.getString("priority");
                Status = user_data1.getString("status");
                
                Intent i = new Intent(My_Task.this,My_Task_single.class);
                i.putExtra("name", Name);
                i.putExtra("empid", Empid);
                i.putExtra("email", Email);
                i.putExtra("officialemail",Officialemail);
                i.putExtra("department",Department);
                i.putExtra("title", Title);
                i.putExtra("date", Date);
                i.putExtra("subject", Subject);
                i.putExtra("discription", Discription);
                i.putExtra("priority", Priority);
                i.putExtra("status", Status);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(My_Task.this, "Invalid Details", Toast.LENGTH_LONG).show();
            }

        }
    }


    class BackGround1 extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String empid = params[1];
            String date = params[2];
            String status = params[3];
            String priority = params[4];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://192.168.1.54/Ticketing_Tool/tickets_data.php");
                String urlParams = "title=" + title + "&empid=" + empid + "&date=" + date + "&status=" + status + "&priority=" + priority;

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
                JSONObject user_data1 = root.getJSONObject("user_data1");
                Name = user_data1.getString("name");
                Empid = user_data1.getString("empid");
                Email = user_data1.getString("email");
                Title = user_data1.getString("title");
                Date = user_data1.getString("date");
                Subject = user_data1.getString("subject");
                Discription = user_data1.getString("discription");
                Priority = user_data1.getString("priority");
                Status = user_data1.getString("status");

                Intent i = new Intent(My_Task.this,Single_Ticket.class);
                i.putExtra("name", Name);
                i.putExtra("empid", Empid);
                i.putExtra("email", Email);
                i.putExtra("officialemail",Officialemail);
                i.putExtra("title", Title);
                i.putExtra("date", Date);
                i.putExtra("subject", Subject);
                i.putExtra("discription", Discription);
                i.putExtra("priority", Priority);
                i.putExtra("status", Status);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(My_Task.this, "Invalid Details", Toast.LENGTH_LONG).show();
            }

        }
    }


}