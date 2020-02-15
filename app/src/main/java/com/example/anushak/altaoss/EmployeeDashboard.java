package com.example.anushak.altaoss;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.WindowManager;
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

public class EmployeeDashboard extends AppCompatActivity {

    TextView emp_name;
    String Emp_Name,Department;
    DrawerLayout mDrawerLayout;
    android.support.v7.widget.Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    TextView Name,Email,EmpId;
    String firstname,email,empid;
    TextView profile, project, message, mypay, attendance, updates,myprojectp,careersp,employee,text1,text2;
    String Profile, Project, Message, Mypay, Attendance, Updates,Myprojectp,Careersp,Employee,Text1,Text2;
    CardView ticketing,my_profile,projects,our_updates,emp_attendance,my_pay,messages,myprojects,calendar,careers,reimbursementdashboard,visitors;
    String FIRSTNAME = null, EMAIL = null,MIDDLENAME = null, LASTNAME = null, GENDER = null, BLOODGROUP = null, CONTACTNUMBER = null, DATEOFJOINING = null, PERMANENTADDRESS = null, CORRESPONDANCEADDRESS = null, OFFICIALEMAIL = null, PERSONALEMAIL = null, EMPID = null, DEPARTMENT = null, DESIGNATION = null;
    String PAYSLIPS,REIMBURSEMENT1,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_employee_dashboard);

        emp_name = (TextView)findViewById(R.id.home_name);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //navigationView.setNavigationItemSelectedListener(this);
        View navView =  navigationView.inflateHeaderView(R.layout.header);
        logo = (ImageView)toolbar.findViewById(R.id.logo);
        Name = (TextView) navView.findViewById(R.id.adminname);
        Email = (TextView) navView.findViewById(R.id.adminemail);
        EmpId = (TextView) navView.findViewById(R.id.adminempid);
        my_profile = (CardView)findViewById(R.id.my_profile);
        projects = (CardView)findViewById(R.id.projects);
        our_updates = (CardView)findViewById(R.id.our_updates);
        emp_attendance = (CardView)findViewById(R.id.emp_attendance);
        my_pay = (CardView)findViewById(R.id.my_pay);
        messages = (CardView)findViewById(R.id.messages);
        myprojects = (CardView)findViewById(R.id.myprojects);
        calendar = (CardView)findViewById(R.id.calendar);
        careers = (CardView)findViewById(R.id.careers);
        reimbursementdashboard = (CardView)findViewById(R.id.reimbursementdashboard);
        visitors = (CardView)findViewById(R.id.visitors);
        ticketing = (CardView)findViewById(R.id.ticketingtool);

        profile = ((TextView) findViewById(R.id.profile));
        project = ((TextView) findViewById(R.id.project));
        message = ((TextView) findViewById(R.id.message));
        mypay = ((TextView) findViewById(R.id.mypay));
        attendance = ((TextView) findViewById(R.id.attendance));
        updates = ((TextView) findViewById(R.id.updates));
        myprojectp = ((TextView) findViewById(R.id.myprojectp));
        careersp = ((TextView) findViewById(R.id.careersp));
        employee = (TextView) findViewById(R.id.employee);


        text1 = (TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid2);

        employee.setText("employee");
        Emp_Name = getIntent().getStringExtra("firstname");
        firstname = getIntent().getStringExtra("firstname");
        email = getIntent().getStringExtra("officialemail");
        empid = getIntent().getStringExtra("empid");

        Profile = getIntent().getStringExtra("profile");
        Project = getIntent().getStringExtra("project");
        Message = getIntent().getStringExtra("message");
        Mypay = getIntent().getStringExtra("mypay");
        Attendance = getIntent().getStringExtra("attendance");
        Updates = getIntent().getStringExtra("updates");
        Myprojectp = getIntent().getStringExtra("myproject");
        Careersp = getIntent().getStringExtra("careers");

        text1.setText("Apply Leave");
        emp_name.setText(Emp_Name);
        Name.setText("Hi, "+firstname);
        Email.setText(email);
        EmpId.setText(empid);

        text2.setText("Employee DashBoard" + "(" + empid + ")");

        profile.setText(Profile);
        project.setText(Project);
        message.setText(Message);
        mypay.setText(Mypay);
        attendance.setText(Attendance);
        updates.setText(Updates);
        myprojectp.setText(Myprojectp);
        careersp.setText(Careersp);

        Department ="employee";

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.setting:
                        Intent intent=new Intent(EmployeeDashboard.this,Settings.class);
                        startActivity(intent);
                        break;

                    case R.id.rewards:
                        Intent intent2=new Intent(EmployeeDashboard.this,Rewards.class);
                        intent2.putExtra("employee",Employee);
                        intent2.putExtra("empid",empid);
                        startActivity(intent2);
                        break;
                    case R.id.querydashboard:
                        Intent intent3=new Intent(EmployeeDashboard.this,EmpQuery.class);
                        intent3.putExtra("empid",empid);
                        startActivity(intent3);
                        break;
                    case R.id.mydownloads:
                        Intent intent5=new Intent(EmployeeDashboard.this,MyDownloads.class);
                        intent5.putExtra("employee",Employee);
                        intent5.putExtra("empid",empid);
                        startActivity(intent5);
                        break;
                    case R.id.changepassword:
                        Intent intent1=new Intent(EmployeeDashboard.this,ChangePassword.class);
                        intent1.putExtra("empid",empid);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        Intent intent4=new Intent(EmployeeDashboard.this,MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent4);
                        //finish();
                        break;
                }
                return false;
            }
        });

        //MY Profile
        Profile = profile.getText().toString();
        if (Profile.equals("YES")) {
            my_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Employee = employee.getText().toString();
                    Profile = profile.getText().toString();
                    BackGround b = new BackGround();
                    b.execute(empid);
                }
            });
        } else if (Profile.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            my_profile.setVisibility(View.GONE);
        }

        else if (Profile.equals("EDIT")) {
            my_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Employee = employee.getText().toString();
                    Profile = profile.getText().toString();
                    BackGrounde be = new BackGrounde();
                    be.execute(empid);
                }
            });
        }

        //My Projects
        Project = project.getText().toString();
        if (Project.equals("YES")) {
            projects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empid = EmpId.getText().toString();
                    BackGroundprojects b = new BackGroundprojects();
                    b.execute(empid);
                }
            });
        } else if (Project.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            projects.setVisibility(View.GONE);
        }

        //Messages
        Message = message.getText().toString();
        if (Message.equals("YES")) {
            messages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EmployeeDashboard.this, MessagesActivity.class);
                    startActivity(intent);
                }
            });
        } else if (Message.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            messages.setVisibility(View.GONE);
        }

        //MyPay
        Mypay = mypay.getText().toString();
        if (Mypay.equals("YES")) {
            my_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empid = EmpId.getText().toString();
                    BackGround1 b = new BackGround1();
                    b.execute(empid);
                }
            });
        } else if (Mypay.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            my_pay.setVisibility(View.GONE);
        }

        //Attendance
        Attendance = attendance.getText().toString();
        if (Attendance.equals("YES")) {
            emp_attendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empid = EmpId.getText().toString();
                    Text1 = text1.getText().toString();
                    /*Text2 = text2.getText().toString();
                    Text3 = text3.getText().toString();*/
                    BackGroundattendance b = new BackGroundattendance();
                    b.execute(empid);
                }
            });
        } else if (Attendance.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            emp_attendance.setVisibility(View.GONE);
        }

        //our_updates
        Updates = updates.getText().toString();
        if (Updates.equals("YES")) {
            empid = EmpId.getText().toString();
            Employee = employee.getText().toString();
            our_updates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BackGroundnewupdate b = new BackGroundnewupdate();
                    b.execute(empid);
                }
            });
        } else if (Updates.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            our_updates.setVisibility(View.GONE);
        }

        //My Projects
        Myprojectp = myprojectp.getText().toString();
        if (Myprojectp.equals("YES")) {
            empid = EmpId.getText().toString();
            Employee = employee.getText().toString();
            myprojects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EmployeeDashboard.this, ProjectsList.class);
                    intent.putExtra("empid",empid);
                    intent.putExtra("employee",Employee);
                    startActivity(intent);
                }
            });
        } else if (Myprojectp.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            myprojects.setVisibility(View.GONE);
        }


        //Calendar
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDashboard.this, CalendarActivity.class);
                intent.putExtra("empid",empid);
                intent.putExtra("employee",Employee);
                startActivity(intent);
            }
        });


        //Careers
        Careersp = careersp.getText().toString();
        if (Careersp.equals("YES")) {
            empid = EmpId.getText().toString();
            Employee = employee.getText().toString();
            careers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EmployeeDashboard.this, Careers.class);
                    intent.putExtra("empid",empid);
                    intent.putExtra("employee",Employee);
                    startActivity(intent);
                }
            });
        } else if (Careersp.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            careers.setVisibility(View.GONE);
        }

        reimbursementdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDashboard.this, EmpReimbursementDashboard.class);
                intent.putExtra("empid",empid);
                intent.putExtra("employee",Employee);
                startActivity(intent);
            }
        });

        visitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployeeDashboard.this, EmpVisitorsList.class);
                intent.putExtra("empid",empid);
                intent.putExtra("employee",Employee);
                startActivity(intent);
            }
        });

        ticketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EmployeeDashboard.this,TicketingDashboard.class);
                i.putExtra("empid",empid);
                i.putExtra("officialemail",email);
                i.putExtra("firstname",firstname);
                i.putExtra("department",Department);
                i.putExtra("employee",Employee);
                startActivity(i);

            }
        });


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/emp_profile.php");
                String urlParams = "empid=" + empid;

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
                FIRSTNAME = user_data_profile.getString("firstname");
                MIDDLENAME = user_data_profile.getString("middlename");
                LASTNAME = user_data_profile.getString("lastname");
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");
                DESIGNATION = user_data_profile.getString("designation");
                GENDER = user_data_profile.getString("gender");
                BLOODGROUP = user_data_profile.getString("bloodgroup");
                CONTACTNUMBER = user_data_profile.getString("contactno");
                DATEOFJOINING = user_data_profile.getString("dateofjoining");
                PERMANENTADDRESS = user_data_profile.getString("permanentaddress");
                CORRESPONDANCEADDRESS = user_data_profile.getString("correspondanceaddress");
                OFFICIALEMAIL = user_data_profile.getString("officialemail");
                PERSONALEMAIL = user_data_profile.getString("personalemail");


                Intent i = new Intent(EmployeeDashboard.this, Profile.class);
                i.putExtra("empid", EMPID);
                i.putExtra("department", DEPARTMENT);
                i.putExtra("designation", DESIGNATION);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("middlename", MIDDLENAME);
                i.putExtra("lastname", LASTNAME);
                i.putExtra("gender", GENDER);
                i.putExtra("bloodgroup", BLOODGROUP);
                i.putExtra("contactno", CONTACTNUMBER);
                i.putExtra("dateofjoining", DATEOFJOINING);
                i.putExtra("permanentaddress", PERMANENTADDRESS);
                i.putExtra("correspondanceaddress", CORRESPONDANCEADDRESS);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("personalemail", PERSONALEMAIL);
                i.putExtra("employee",Employee);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(EmployeeDashboard.this, "Related data is not there.", Toast.LENGTH_SHORT).show();
            }


        }
    }

    class BackGrounde extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/emp_profile.php");
                String urlParams = "empid=" + empid;

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
                FIRSTNAME = user_data_profile.getString("firstname");
                MIDDLENAME = user_data_profile.getString("middlename");
                LASTNAME = user_data_profile.getString("lastname");
                EMPID = user_data_profile.getString("empid");
                DEPARTMENT = user_data_profile.getString("department");
                DESIGNATION = user_data_profile.getString("designation");
                GENDER = user_data_profile.getString("gender");
                BLOODGROUP = user_data_profile.getString("bloodgroup");
                CONTACTNUMBER = user_data_profile.getString("contactno");
                DATEOFJOINING = user_data_profile.getString("dateofjoining");
                PERMANENTADDRESS = user_data_profile.getString("permanentaddress");
                CORRESPONDANCEADDRESS = user_data_profile.getString("correspondanceaddress");
                OFFICIALEMAIL = user_data_profile.getString("officialemail");
                PERSONALEMAIL = user_data_profile.getString("personalemail");


                Intent i = new Intent(EmployeeDashboard.this, EditProfile.class);
                i.putExtra("empid", EMPID);
                i.putExtra("department", DEPARTMENT);
                i.putExtra("designation", DESIGNATION);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("middlename", MIDDLENAME);
                i.putExtra("lastname", LASTNAME);
                i.putExtra("gender", GENDER);
                i.putExtra("bloodgroup", BLOODGROUP);
                i.putExtra("contactno", CONTACTNUMBER);
                i.putExtra("dateofjoining", DATEOFJOINING);
                i.putExtra("permanentaddress", PERMANENTADDRESS);
                i.putExtra("correspondanceaddress", CORRESPONDANCEADDRESS);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("personalemail", PERSONALEMAIL);
                i.putExtra("employee",Employee);
                startActivity(i);
            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmployeeDashboard.this, "Related data is not there.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class BackGroundprojects extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/myproject.php");
                String urlParams = "empid=" + empid;

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
                    FIRSTNAME = user_data_profile.getString("firstname");
                    EMPID = user_data_profile.getString("empid");

                    Intent i = new Intent(EmployeeDashboard.this, MyProject.class);
                    i.putExtra("firstname", FIRSTNAME);
                    i.putExtra("empid", EMPID);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(EmployeeDashboard.this, "You Don't have Sufficient privileges to Access My Projects.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    //our_updates
    class BackGroundnewupdate extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/commonfile.php");
                String urlParams = "empid=" + empid;

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

            Updates = updates.getText().toString();
             try {
                    JSONObject root = new JSONObject(s);
                    JSONObject user_data = root.getJSONObject("user_data");
                    EMPID = user_data.getString("empid");
                    // Employee = user_data.getString("employee");

                    Intent i = new Intent(EmployeeDashboard.this, OurUpdates.class);
                    i.putExtra("empid", EMPID);
                    i.putExtra("employee",Employee);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(EmployeeDashboard.this, "You Don't have Sufficient privileges to Access this.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(EmployeeDashboard.this, EmployeeDashboard.class);
                    startActivity(i);
                }

        }
    }

    //attendance
    class BackGroundattendance extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/attendance.php");
                String urlParams = "empid=" + empid;

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
                    //FIRSTNAME = user_data_profile.getString("firstname");
                    EMPID = user_data_profile.getString("empid");
                    EMAIL = user_data_profile.getString("officialemail");

                    Intent i = new Intent(EmployeeDashboard.this, Attendance.class);
                    i.putExtra("empid", EMPID);
                    i.putExtra("officialemail", EMAIL);
                    i.putExtra("button", Text1);
                /*i.putExtra("subject", Text2);
                i.putExtra("subject1", Text3);*/
                    i.putExtra("employee", Employee);
                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(EmployeeDashboard.this, "Database Error", Toast.LENGTH_SHORT).show();
                }

            }

    }

    //mypay
    class BackGround1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                //URL url = new URL("http://altaitsolutions.com/Altadatabase/mypay.php");
                URL url = new URL("http://altaitsolutions.com/Altadatabase/mypay.php");
                String urlParams = "empid=" + empid;

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
                    JSONObject user_data = root.getJSONObject("user_data");
                    FIRSTNAME = user_data.getString("firstname");
                    EMPID = user_data.getString("empid");
                    PAYSLIPS = user_data.getString("payslips");
                    REIMBURSEMENT1 = user_data.getString("reimbursement1");

                    Intent i = new Intent(EmployeeDashboard.this, MyPay.class);
                    i.putExtra("firstname", FIRSTNAME);
                    i.putExtra("empid", EMPID);
                    i.putExtra("payslips", PAYSLIPS);
                    i.putExtra("reimbursement1", REIMBURSEMENT1);
                    i.putExtra("employee",Employee);

                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(EmployeeDashboard.this, "Related data is not there", Toast.LENGTH_SHORT).show();
                }
            }

    }

    //Query
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.queryform,menu);
        getMenuInflater().inflate(R.menu.notification,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.query)
        {
            Text2=text2.getText().toString();
            empid = EmpId.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(empid);
        }
        if(id == R.id.notification)
        {
            Intent intent = new Intent(EmployeeDashboard.this,Notifications.class);
            intent.putExtra("employee",Employee);
            intent.putExtra("empid",empid);
            startActivity(intent);
        }
        if(id == R.id.newsfeed)
        {
            Intent intent = new Intent(EmployeeDashboard.this,NewsFeed.class);
            intent.putExtra("employee",Employee);
            startActivity(intent);
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

                Intent i = new Intent(EmployeeDashboard.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text2);
                // i.putExtra("message1", Text1);
                startActivity(i);
            }
            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(EmployeeDashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit? If Yes Please logout First...!")
                .setNegativeButton(android.R.string.yes, null)
                .setPositiveButton(android.R.string.no, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg1, int arg0) {
                        EmployeeDashboard.super.onBackPressed();
                    }
                }).create().show();
    }
}