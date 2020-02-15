package com.example.anushak.altaoss;

import android.app.AlertDialog;
import android.content.Context;
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

public class AdminDashboard extends AppCompatActivity {

    String firstname, email,empid;
    TextView nameTV, emailTV,empID,text;
    DrawerLayout mDrawerLayout;
    android.support.v7.widget.Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context context;
    CardView ticketingtool,reimbursementdashboard,empdirectory,empattendance,empprojects,emppayroll,empdocuments,cccamera,preferences,my_profile,messages,my_pay,our_updates,calendar,interviews,careers,visitors;
    String EMPID=null,OFFICIALEMAIL=null,MANAGERMAIL=null,MANAGERID=null,HRMAIL=null,HRID=null,Text,MIDDLENAME,LASTNAME,FIRSTNAME,DEPARTMENT,DESIGNATION,DATEOFJOINING,GENDER,BLOODGROUP,CONTACTNUMBER,CORRESPONDANCEADDRESS,PERMANENTADDRESS,PERSONALEMAIL;

    TextView profile, project, message, mypay, attendance, updates,department;
    TextView newempform, empdirectory11, projectdetails, empattendance11, reimbursement, documentsupload;
    TextView payroll, documentsfetch, permission, cccamera1,careersp,interviewsp,visitorp;
    String Profile, Project, Message, Mypay, Attendance, Updates;
    String Newempform, Empdirectory11, Projectdetails, Empattendance11, Reimbursement, Documentsupload;
    String Payroll, Documentsfetch, Permission, Cccamera,Careersp,Interviewsp,Visitorp,Department;

    TextView admin,profiletext;
    String Admin,PAYSLIPS,REIMBURSEMENT1,ProfileText;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_admin_dashboard);

        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //navigationView.setNavigationItemSelectedListener(this);
        View navView =  navigationView.inflateHeaderView(R.layout.header);

        logo = (ImageView)toolbar.findViewById(R.id.logo);
        nameTV = (TextView) navView.findViewById(R.id.adminname);
        emailTV = (TextView) navView.findViewById(R.id.adminemail);
        empID = (TextView) navView.findViewById(R.id.adminempid);
        empdirectory = (CardView) findViewById(R.id.empdirectory);
        empattendance = (CardView) findViewById(R.id.empattendance);
        empprojects = (CardView) findViewById(R.id.empprojects);
        emppayroll = (CardView) findViewById(R.id.emppayroll);
        empdocuments = (CardView) findViewById(R.id.empdocuments);
        cccamera = (CardView) findViewById(R.id.cccamera);
        preferences = (CardView) findViewById(R.id.preferences);
        my_profile = (CardView) findViewById(R.id.my_profile);
        messages = (CardView) findViewById(R.id.messages);
        my_pay = (CardView) findViewById(R.id.my_pay);
        our_updates = (CardView) findViewById(R.id.our_updates);
        calendar = (CardView) findViewById(R.id.calendar);
        interviews = (CardView)findViewById(R.id.interviews);
        careers =(CardView)findViewById(R.id.careers);
        reimbursementdashboard = (CardView)findViewById(R.id.reimbursementdashboard);
        visitors= (CardView)findViewById(R.id.visitors);
        ticketingtool = (CardView)findViewById(R.id.ticketingtool);
        text = (TextView) findViewById(R.id.Grid2);
        admin = (TextView) findViewById(R.id.employee);
        profiletext = (TextView) findViewById(R.id.Grid);
        department = (TextView) findViewById(R.id.department);

        profile = (TextView) findViewById(R.id.profile);
        project = (TextView) findViewById(R.id.project);
        message = (TextView) findViewById(R.id.message);
        mypay = (TextView) findViewById(R.id.mypay);
        attendance = (TextView) findViewById(R.id.attendance);
        updates = (TextView) findViewById(R.id.updates);
        newempform = (TextView) findViewById(R.id.newempform);
        empdirectory11 = (TextView) findViewById(R.id.empdirectory11);
        projectdetails = (TextView) findViewById(R.id.projectdetails);
        empattendance11 = (TextView) findViewById(R.id.empattendance11);
        reimbursement = (TextView) findViewById(R.id.reimbursement);
        documentsupload = (TextView) findViewById(R.id.documentsupload);
        payroll = (TextView) findViewById(R.id.payroll);
        documentsfetch = (TextView) findViewById(R.id.documentsfetch);
        permission = (TextView) findViewById(R.id.permission);
        cccamera1 = (TextView) findViewById(R.id.cccamera1);
        careersp = (TextView) findViewById(R.id.careersp);
        interviewsp = (TextView) findViewById(R.id.interviewsp);
        visitorp = (TextView) findViewById(R.id.visitorp);

        firstname = getIntent().getStringExtra("firstname");
        email = getIntent().getStringExtra("officialemail");
        empid = getIntent().getStringExtra("empid");
        Department = getIntent().getStringExtra("department");

        Profile = getIntent().getStringExtra("profile");
        Project = getIntent().getStringExtra("project");
        Message = getIntent().getStringExtra("message");
        Mypay = getIntent().getStringExtra("mypay");
        Attendance = getIntent().getStringExtra("attendance");
        Updates = getIntent().getStringExtra("updates");
        Newempform = getIntent().getStringExtra("newempform");
        Empdirectory11 = getIntent().getStringExtra("empdirectory");
        Projectdetails = getIntent().getStringExtra("projectdetails");
        Empattendance11 = getIntent().getStringExtra("empattendance");
        Reimbursement = getIntent().getStringExtra("reimbursement");
        Documentsupload = getIntent().getStringExtra("documentsupload");
        Payroll = getIntent().getStringExtra("payroll");
        Documentsfetch = getIntent().getStringExtra("documentsfetch");
        Permission = getIntent().getStringExtra("permission");
        Cccamera = getIntent().getStringExtra("cccamera");
        Careersp = getIntent().getStringExtra("careers");
        Interviewsp = getIntent().getStringExtra("interviews");
        Visitorp = getIntent().getStringExtra("visitor");

        nameTV.setText(firstname);
        emailTV.setText(email);
        empID.setText(empid);
        text.setText("Admin Dashboard"+ "("+empid+")");
        admin.setText("admin");
        department.setText(Department);

        profile.setText(Profile);
        project.setText(Project);
        message.setText(Message);
        mypay.setText(Mypay);
        attendance.setText(Attendance);
        updates.setText(Updates);
        newempform.setText(Newempform);
        empdirectory11.setText(Empdirectory11);
        projectdetails.setText(Projectdetails);
        empattendance11.setText(Empattendance11);
        reimbursement.setText(Reimbursement);
        documentsupload.setText(Documentsupload);
        payroll.setText(Payroll);
        documentsfetch.setText(Documentsfetch);
        permission.setText(Permission);
        cccamera1.setText(Cccamera);
        careersp.setText(Careersp);
        interviewsp.setText(Interviewsp);
        visitorp.setText(Visitorp);

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
                        Intent intent=new Intent(AdminDashboard.this,Settings.class);
                        startActivity(intent);
                        break;
                    case R.id.rewards:
                        Intent intent2 = new Intent(AdminDashboard.this, RewardsUpdate.class);
                        intent2.putExtra("employee",Admin);
                        intent2.putExtra("empid",empid);
                        startActivity(intent2);
                        break;
                    case R.id.querydashboard:
                        Intent intent3=new Intent(AdminDashboard.this,QueryDashboard.class);
                        intent3.putExtra("employee",Admin);
                        intent3.putExtra("empid",empid);
                        startActivity(intent3);
                        break;
                    case R.id.mydownloads:
                        Intent intent5=new Intent(AdminDashboard.this,MyDownloads.class);
                        intent5.putExtra("employee",Admin);
                        intent5.putExtra("empid",empid);
                        startActivity(intent5);
                        break;
                    case R.id.changepassword:
                        Intent intent1=new Intent(AdminDashboard.this,ChangePassword.class);
                        intent1.putExtra("empid",empid);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        Intent intent4=new Intent(AdminDashboard.this,MainActivity.class)
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

        //My Profile
        Profile = profile.getText().toString();
        if (Profile.equals("YES")) {
            my_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Admin = admin.getText().toString();
                    ProfileText = profiletext.getText().toString();
                    Profile = profile.getText().toString();
                    BackGround b = new BackGround();
                    b.execute(empid);
                }
            });
        } else if (Profile.equals("NO")) {
         //   Toast.makeText(getApplicationContext(), "You don't have permission to access this1111111111111", Toast.LENGTH_LONG).show();
            my_profile.setVisibility(View.GONE);
        }

        else if (Profile.equals("EDIT")) {
            my_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Admin = admin.getText().toString();
                    ProfileText = profiletext.getText().toString();
                    Profile = profile.getText().toString();
                    BackGrounde be = new BackGrounde();
                    be.execute(empid);
                }
            });
        }

        //My Pay
        Mypay = mypay.getText().toString();
        if (Mypay.equals("YES")) {
            my_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Admin = admin.getText().toString();
                    BackGround1 b = new BackGround1();
                    b.execute(empid);
                }
            });
        } else if (Mypay.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            my_pay.setVisibility(View.GONE);
        }

        //Our_updates
        Updates = updates.getText().toString();
        if (Updates.equals("YES")) {
            empid = empID.getText().toString();
            Admin = admin.getText().toString();
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

        //Employee Directory
        Empdirectory11 = empdirectory11.getText().toString();
        if (Empdirectory11.equals("YES")) {
            empid = empID.getText().toString();
            empdirectory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Admin = admin.getText().toString();
                    Intent intent = new Intent(AdminDashboard.this, EmpDirectory.class);
                    intent.putExtra("employee",Admin);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Empdirectory11.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empdirectory.setVisibility(View.GONE);
        }

        //Employee Projects
        Projectdetails = projectdetails.getText().toString();
        if (Projectdetails.equals("YES")) {
            empid = empID.getText().toString();
            empprojects.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Admin = admin.getText().toString();
                    Intent intent = new Intent(AdminDashboard.this, EmpProjects.class);
                    intent.putExtra("employee",Admin);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Projectdetails.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empprojects.setVisibility(View.GONE);
        }

        //messages
        Message = message.getText().toString();
        if (Message.equals("YES")) {
            messages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this, MessagesActivity.class);
                    startActivity(intent);
                }
            });
        } else if (Message.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            messages.setVisibility(View.GONE);
        }

        //Employee Attendance
        Empattendance11 = empattendance11.getText().toString();
        if (Empattendance11.equals("YES")) {
            empid = empID.getText().toString();
            empattendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AdminDashboard.this,"Employee Attendance", Toast.LENGTH_LONG).show();
                    empid = empID.getText().toString();
                    BackGroundattendance b = new BackGroundattendance();
                    b.execute(empid);
                }
            });
        } else if (Empattendance11.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empattendance.setVisibility(View.GONE);
        }

        //Employee Payroll
        Payroll = payroll.getText().toString();
        if (Payroll.equals("YES")) {
            empid = empID.getText().toString();
            Admin = admin.getText().toString();
            emppayroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this, EmpPayroll.class);
                    intent.putExtra("employee",Admin);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Payroll.equals("NO")) {
          //  Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            emppayroll.setVisibility(View.GONE);
        }

        //Employee Documents
        Documentsfetch = documentsfetch.getText().toString();
        if (Documentsfetch.equals("YES")) {
            empid = empID.getText().toString();
            email = emailTV.getText().toString();
            empdocuments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this, EmployeeDocuments.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Documentsfetch.equals("NO")) {
         //   Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empdocuments.setVisibility(View.GONE);
        }

        //Preferences
        Permission = permission.getText().toString();
        if (Permission.equals("YES")) {
            preferences.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this, Preferences.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Permission.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            preferences.setVisibility(View.GONE);
        }

        Cccamera = cccamera1.getText().toString();
        if (Cccamera.equals("YES")) {
            cccamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*   Intent intent = new Intent(AdminDashboard.this, AdminDashboard.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);*/
                    Toast.makeText(getApplicationContext(), "CC camera...", Toast.LENGTH_LONG).show();

                }
            });
        } else if (Cccamera.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            cccamera.setVisibility(View.GONE);
        }



        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, CalendarActivity.class);
                intent.putExtra("employee",Admin);
                intent.putExtra("empid",empid);
                startActivity(intent);
            }
        });



        Interviewsp = interviewsp.getText().toString();
        if (Interviewsp.equals("YES")) {
            interviews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this,Interviews_dashboard.class);
                    intent.putExtra("empid",empid);
                    intent.putExtra("employee",Admin);
                    startActivity(intent);

                }
            });
        } else if (Interviewsp.equals("NO")) {
            // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            interviews.setVisibility(View.GONE);
        }

        Careersp = careersp.getText().toString();
        if (Careersp.equals("YES")) {
            careers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this,Careers.class);
                    intent.putExtra("empid",empid);
                    intent.putExtra("employee",Admin);
                    startActivity(intent);

                }
            });
        } else if (Careersp.equals("NO")) {
            // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            careers.setVisibility(View.GONE);
        }
        reimbursementdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, ReimbursementDashboard.class);
                intent.putExtra("employee",Admin);
                intent.putExtra("empid",empid);
                startActivity(intent);
            }
        });

            visitors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AdminDashboard.this, Admin_Visitor_dashboard.class);
                    intent.putExtra("employee",Admin);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });

        ticketingtool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminDashboard.this, TicketingDashboard.class);
                i.putExtra("empid",empid);
                i.putExtra("officialemail",email);
                i.putExtra("firstname",firstname);
                i.putExtra("department",Department);
                i.putExtra("employee",Admin);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //My Profile
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

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(AdminDashboard.this, Profile.class);
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
            i.putExtra("employee",Admin);
            startActivity(i);
        }
    }

    //My Profile
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

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(AdminDashboard.this, EditProfile.class);
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
            i.putExtra("employee",Admin);
            startActivity(i);
        }
    }

    //Mypay
    class BackGround1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                // URL url = new URL("http://altaitsolutions.com/Altadatabase/mypay.php");
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
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                FIRSTNAME = user_data.getString("firstname");
                EMPID = user_data.getString("empid");
                PAYSLIPS = user_data.getString("payslips");
                REIMBURSEMENT1 = user_data.getString("reimbursement1");

                Intent i = new Intent(AdminDashboard.this, MyPay.class);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("empid", EMPID);
                i.putExtra("payslips", PAYSLIPS);
                i.putExtra("reimbursement1", REIMBURSEMENT1);
                i.putExtra("employee",Admin);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(AdminDashboard.this, "Related data is not there", Toast.LENGTH_SHORT).show();
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
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                EMPID = user_data.getString("empid");

                Intent i = new Intent(AdminDashboard.this, OurUpdates.class);
                i.putExtra("empid", EMPID);
                i.putExtra("employee",Admin);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(AdminDashboard.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AdminDashboard.this, MainActivity.class);
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
                OFFICIALEMAIL = user_data_profile.getString("officialemail");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(AdminDashboard.this,EmployeeAttendance.class);
            i.putExtra("empid", EMPID);
            i.putExtra("officialemail", OFFICIALEMAIL);
            i.putExtra("employee",Admin);
            startActivity(i);
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
            Text=text.getText().toString();
            empid = empID.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(empid);
        }
        if(id == R.id.newsfeed)
        {
            Intent intent = new Intent(AdminDashboard.this,NewsFeed.class);
            intent.putExtra("employee",Admin);
            startActivity(intent);
        }
        if(id == R.id.notification)
        {
            Intent intent = new Intent(AdminDashboard.this,NotificationsDashboard.class);
           // intent.putExtra("employee",Admin);
            intent.putExtra("empid",empid);
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

                Intent i = new Intent(AdminDashboard.this, QueryForm.class);
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

                Toast.makeText(AdminDashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
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
                        AdminDashboard.super.onBackPressed();
                    }
                }).create().show();
    }

}
