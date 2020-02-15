package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TicketingDashboard extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    android.support.v7.widget.Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context context;
    Context ctx = this;
    CardView newticket,totaltickets,tickets_history,my_task,open_tickets,closed_tickets,solved_tickets,pending_tickets;
    TextView empid,name,email,department,text,text1;
    String Empid,Name,Email,Department,Text,Text1,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID;
    HorizontalScrollView lq,lq1,lq2;
    ImageView iv25,iv251,iv252;
    TextView dashboard,dashboard1,dashboard2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing_dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //navigationView.setNavigationItemSelectedListener(this);
        View navView =  navigationView.inflateHeaderView(R.layout.header);
        empid=(TextView)findViewById(R.id.empid);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.email);
        department=(TextView)findViewById(R.id.department);
        text=(TextView)findViewById(R.id.text);
        text1=(TextView)findViewById(R.id.text1);
        lq = (HorizontalScrollView)findViewById(R.id.lq);
        lq1 = (HorizontalScrollView)findViewById(R.id.lq1);
        lq2 = (HorizontalScrollView)findViewById(R.id.lq2);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView)findViewById(R.id.iv251);
        iv252 = (ImageView)findViewById(R.id.iv252);
        dashboard=(TextView)findViewById(R.id.dashboard);
        dashboard1=(TextView)findViewById(R.id.dashboard1);
        dashboard2=(TextView)findViewById(R.id.dashboard2);

        newticket=(CardView)findViewById(R.id.newticket);
        totaltickets=(CardView)findViewById(R.id.totaltickets);
        open_tickets =(CardView)findViewById(R.id.open_tickets);
        solved_tickets =(CardView)findViewById(R.id.solved_tickets);
        pending_tickets =(CardView)findViewById(R.id.pending_tickets);

        tickets_history=(CardView)findViewById(R.id.tickets_history);
        my_task=(CardView)findViewById(R.id.my_task);
        closed_tickets=(CardView)findViewById(R.id.closed_tickets);

        Department=getIntent().getStringExtra("department");
        Text=getIntent().getStringExtra("employee");
        department.setText(Department);

        Empid = getIntent().getStringExtra("empid");
        Name=getIntent().getStringExtra("firstname");
        Email=getIntent().getStringExtra("officialemail");
        name.setText(Name);
        email.setText(Email);
        empid.setText(Empid);
        text.setText(Text);

        lq.setVisibility(Text.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text.equals("admin")? View.VISIBLE : View.GONE);

        if(Text.equals("employee"))
        {
            text1.setText("Employee Dashboard - Ticketing Tool Dashboard("+Empid+")");
        }
        else if(Text.equals("hr"))
        {
            text1.setText("HR Dashboard - Ticketing Tool Dashboard("+Empid+")");
        }
        else  if(Text.equals("admin"))
        {
            text1.setText("Admin Dashboard - Ticketing Tool Dashboard("+Empid+")");
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
                Intent intent = new Intent(TicketingDashboard.this,EmployeeDashboard.class);
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
                Intent intent = new Intent(TicketingDashboard.this,HrDashboard.class);
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
                Intent intent = new Intent(TicketingDashboard.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        newticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Ticket_raising_form.class);
                i.putExtra("empid",Empid);
                i.putExtra("officialemail",Email);
                i.putExtra("firstname",Name);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

       totaltickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,My_tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });


          tickets_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Total_Tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

       my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,My_Task.class);
                i.putExtra("empid",Empid);
                i.putExtra("officialemail",Email);
                i.putExtra("department",Department);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });
        open_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Open_Tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });
       closed_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Closed_Tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

          solved_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Solved_Tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });
       pending_tickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(TicketingDashboard.this,Pending_Tickets.class);
                i.putExtra("empid",Empid);
                i.putExtra("employee",Text);
                startActivity(i);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.raiseticket:
                        Intent intent=new Intent(TicketingDashboard.this,Ticket_raising_form.class);
                        startActivity(intent);
                        break;
                    case R.id.changepassword:
                       /* Intent intent1=new Intent(Ticketing_dashboard.this,ChangePassword.class);
                        intent1.putExtra("empid",empid);
                        startActivity(intent1);*/
                        break;
                    case R.id.logout:
                        Intent intent4=new Intent(TicketingDashboard.this,MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                        break;
                }
                return false;
            }
        });

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
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

                Intent i = new Intent(TicketingDashboard.this, QueryForm.class);
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

                Toast.makeText(TicketingDashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
