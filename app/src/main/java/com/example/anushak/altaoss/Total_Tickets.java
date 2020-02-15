package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
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

public class Total_Tickets extends AppCompatActivity {

    ArrayList<Tickets> TicketsList = new ArrayList<Tickets>();
    String HttpURL = "http://192.168.1.54/Ticketing_Tool/tickets_list.php";
    Total_Tickets_Adapter total_tickets_adapter;
    ListView listView;
    String Name,Empid,Email,Title,Date,Subject,Discription,Priority,Status;
    ImageView preview;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2,tickets,tickets1,tickets2,text,text1,empid;
    android.support.v7.widget.Toolbar toolbar;
    String Text,Text1,EmpId,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total__tickets);

        listView = (ListView) findViewById(R.id.list);

        preview=findViewById(R.id.preview);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView)findViewById(R.id.lq2);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        iv252 = (ImageView)findViewById(R.id.iv252);
        dashboard=(TextView)findViewById(R.id.dashboard);
        dashboard1=(TextView)findViewById(R.id.dashboard1);
        dashboard2=(TextView)findViewById(R.id.dashboard2);
        tickets=(TextView)findViewById(R.id.tickets);
        tickets1=(TextView)findViewById(R.id.tickets1);
        tickets2=(TextView)findViewById(R.id.tickets2);
        text = (TextView) findViewById(R.id.text);
        text1 = (TextView) findViewById(R.id.text1);
        empid = (TextView) findViewById(R.id.empid);

        listView.setEmptyView(preview); //if list is empty it will show image as empty

        Text = getIntent().getStringExtra("employee");
        EmpId = getIntent().getStringExtra("empid");

        text.setText(Text);
        empid.setText(EmpId);

        lq.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Ticketing Tool Dashboard - Total Tickets");
        }
        else if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Ticketing Tool Dashboard - Total Tickets");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Ticketing Tool Dashboard - Total Tickets");
        }

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Total_Tickets.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Total_Tickets.this,TicketingDashboard.class);
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
                Intent intent = new Intent(Total_Tickets.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Total_Tickets.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv252.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Total_Tickets.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        tickets2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Total_Tickets.this,TicketingDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Tickets ListViewClickData = (Tickets) parent.getItemAtPosition(position);

                Toast.makeText(Total_Tickets.this, ListViewClickData.getTicketTitle(), Toast.LENGTH_SHORT).show();

                Total_Tickets.BackGround b=new Total_Tickets.BackGround();
                b.execute( ListViewClickData.getTicketTitle(),ListViewClickData.getTicketEmpid(),ListViewClickData.getTicketDate(),ListViewClickData.getTicketStatus(),ListViewClickData.getTicketPriority());


            }
        });

        new ParseJSonDataClass2(this).execute();
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
            total_tickets_adapter = new Total_Tickets_Adapter(Total_Tickets.this, R.layout.ticket_layout, TicketsList);
            listView.setAdapter(total_tickets_adapter);
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

                Intent i = new Intent(Total_Tickets.this, Single_Ticket.class);

                i.putExtra("name", Name);
                i.putExtra("empid", Empid);
                i.putExtra("email", Email);
                i.putExtra("title", Title);
                i.putExtra("date", Date);
                i.putExtra("subject", Subject);
                i.putExtra("discription", Discription);
                i.putExtra("priority", Priority);
                i.putExtra("status", Status);
                i.putExtra("employee",Text);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Total_Tickets.this, "Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Query
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
            Text1=text1.getText().toString();
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

                Intent i = new Intent(Total_Tickets.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text1);
                // i.putExtra("message1", Text1);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Total_Tickets.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
