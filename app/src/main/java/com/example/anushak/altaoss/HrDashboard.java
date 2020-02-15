package com.example.anushak.altaoss;

import android.annotation.SuppressLint;
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

public class HrDashboard extends AppCompatActivity {

    CardView ticketing,visitors,empdirectory, empdirectories, empattendance, empproject, empprojects1, empdocuments, iv1, iv3, iv4, iv6,payslips,rewards,careers,calendar,interviews,reimbursementdashboard;
    DrawerLayout mDrawerLayout;
    android.support.v7.widget.Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    Context context;
    String firstname, email, empid;
    TextView nameTV, emailTV, empID, text;
    String EMPID, OFFICIALEMAIL, MANAGERMAIL, MANAGERID, HRMAIL, HRID, Dashboard, Text, FIRSTNAME, MIDDLENAME, LASTNAME, DEPARTMENT, DESIGNATION, GENDER, BLOODGROUP, CONTACTNUMBER, CORRESPONDANCEADDRESS, PERMANENTADDRESS, PERSONALEMAIL, DATEOFJOINING;

    TextView profile, project, message, mypay, attendance, updates,rewardsp,careersp,interviewsp;
    TextView newempform, empdirectory11, projectdetails, empattendance11, reimbursement, documentsupload, payslips1;
    String Profile, Project, Message, Mypay, Attendance, Updates;
    String Newempform, Empdirectory11, Projectdetails, Empattendance11, Reimbursement, Documentsupload, Payslips1,Rewardsp,Careersp,Interviewsp;

    String PAYSLIPS, REIMBURSEMENT1;

    TextView employee;
    String HR,Department;
    ImageView logo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_hr_dashboard);
        toolbar = (android.support.v7.widget.Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //navigationView.setNavigationItemSelectedListener(this);
        View navView =  navigationView.inflateHeaderView(R.layout.header);

        logo = (ImageView)toolbar.findViewById(R.id.logo);
        empdirectory = (CardView) findViewById(R.id.empdirectory);
        empproject = (CardView) findViewById(R.id.empproject);
        empdirectories = (CardView) findViewById(R.id.empdirectories);
        empattendance = (CardView) findViewById(R.id.empattendance);
        empprojects1 = (CardView) findViewById(R.id.empprojects1);
        empdocuments = (CardView) findViewById(R.id.empdocuments);
        payslips = (CardView)findViewById(R.id.payslips);
        rewards = (CardView)findViewById(R.id.rewards);
        iv1 = (CardView) findViewById(R.id.iv1);
        iv3 = (CardView) findViewById(R.id.iv3);
        iv4 = (CardView) findViewById(R.id.iv4);
        iv6 = (CardView) findViewById(R.id.iv6);
        careers = (CardView)findViewById(R.id.careers);
        calendar = (CardView)findViewById(R.id.calendar);
        interviews= (CardView)findViewById(R.id.interviews);
        reimbursementdashboard = (CardView)findViewById(R.id.reimbursementdashboard);
        visitors = (CardView)findViewById(R.id.visitors);
        ticketing = (CardView)findViewById(R.id.ticketing);

        nameTV = (TextView) navView.findViewById(R.id.adminname);
        emailTV = (TextView) navView.findViewById(R.id.adminemail);
        empID = (TextView) navView.findViewById(R.id.adminempid);
        text = (TextView) findViewById(R.id.Grid2);

        employee = (TextView) findViewById(R.id.employee);

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
        payslips1 = (TextView) findViewById(R.id.payslips1);
        rewardsp = (TextView) findViewById(R.id.rewardsp);
        careersp = (TextView) findViewById(R.id.careersp);
        interviewsp = (TextView) findViewById(R.id.interviewsp);


        firstname = getIntent().getStringExtra("firstname");
        email = getIntent().getStringExtra("officialemail");
        empid = getIntent().getStringExtra("empid");

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
        Payslips1 = getIntent().getStringExtra("payslips1");
        Rewardsp = getIntent().getStringExtra("rewards");
        Careersp = getIntent().getStringExtra("careers");
        Interviewsp = getIntent().getStringExtra("interviews");

        nameTV.setText(firstname);
        emailTV.setText(email);
        empID.setText(empid);
        text.setText("HR Dashboard("+empid+")");
        employee.setText("hr");

        Department ="employee";

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
        payslips1.setText(Payslips1);
        rewardsp.setText(Rewardsp);
        careersp.setText(Careersp);
        interviewsp.setText(Interviewsp);

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
                        Intent intent=new Intent(HrDashboard.this,Settings.class);
                        startActivity(intent);
                        break;
                    case R.id.rewards:
                        Intent intent2=new Intent(HrDashboard.this,Rewards.class);
                        intent2.putExtra("employee",HR);
                        intent2.putExtra("empid",empid);
                        startActivity(intent2);
                        break;
                    case R.id.querydashboard:
                        Intent intent3=new Intent(HrDashboard.this,QueryDashboard.class);
                        intent3.putExtra("employee",HR);
                        intent3.putExtra("empid",empid);
                        startActivity(intent3);
                        break;
                    case R.id.mydownloads:
                        Intent intent5=new Intent(HrDashboard.this,MyDownloads.class);
                        intent5.putExtra("employee",HR);
                        intent5.putExtra("empid",empid);
                        startActivity(intent5);
                        break;
                    case R.id.changepassword:
                        Intent intent1=new Intent(HrDashboard.this,ChangePassword.class);
                        intent1.putExtra("empid",empid);
                        startActivity(intent1);
                        break;
                    case R.id.logout:
                        Intent intent4=new Intent(HrDashboard.this,MainActivity.class)
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

        Newempform = newempform.getText().toString();
        if (Newempform.equals("YES")) {
            empid = empID.getText().toString();
            empdirectory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, NewEmployeeForm.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Newempform.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empdirectory.setVisibility(View.GONE);
        }


        Empdirectory11 = empdirectory11.getText().toString();
        if (Empdirectory11.equals("YES")) {
            empid = empID.getText().toString();
            empdirectories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, EmpDirectory.class);
                    intent.putExtra("employee",HR);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Empdirectory11.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empdirectories.setVisibility(View.GONE);
        }


        Projectdetails = projectdetails.getText().toString();
        if (Projectdetails.equals("YES")) {
            empid = empID.getText().toString();
            empproject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, EmpProjects.class);
                    intent.putExtra("employee",HR);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Projectdetails.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empproject.setVisibility(View.GONE);
        }


        Documentsupload = documentsupload.getText().toString();
        if (Documentsupload.equals("YES")) {
            empid = empID.getText().toString();
            empdocuments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, UploadDocuments.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Documentsupload.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empdocuments.setVisibility(View.GONE);
        }

        Empattendance11 = empattendance11.getText().toString();
        if (Empattendance11.equals("YES")) {
            empid = empID.getText().toString();
            empattendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HR = employee.getText().toString();
                    empid = empID.getText().toString();
                    BackGroundattendance b = new BackGroundattendance();
                    b.execute(empid);
                }
            });
        } else if (Empattendance11.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empattendance.setVisibility(View.GONE);
        }

        Profile = profile.getText().toString();
        if (Profile.equals("YES")) {
            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Profile = profile.getText().toString();
                    BackGround b = new BackGround();
                    b.execute(empid);
                }
            });
        } else if (Profile.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this.", Toast.LENGTH_LONG).show();
            iv1.setVisibility(View.GONE);
        }
        else if (Profile.equals("EDIT")) {
            iv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Profile = profile.getText().toString();
                    BackGrounde be = new BackGrounde();
                    be.execute(empid);
                }
            });
        }

        Message = message.getText().toString();
        if (Message.equals("YES")) {
            iv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, MessagesActivity.class);
                    startActivity(intent);
                }
            });
        } else if (Message.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            iv3.setVisibility(View.GONE);
        }

        Mypay = mypay.getText().toString();
        if (Mypay.equals("YES")) {
            iv4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empid = empID.getText().toString();
                    BackGround1 b = new BackGround1();
                    b.execute(empid);
                }
            });
        } else if (Mypay.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            iv4.setVisibility(View.GONE);
        }


        Updates = updates.getText().toString();
        if (Updates.equals("YES")) {
            empid = empID.getText().toString();
            HR = employee.getText().toString();
            iv6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BackGroundnewupdate b = new BackGroundnewupdate();
                    b.execute(empid);
                }
            });
        } else if (Updates.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            iv6.setVisibility(View.GONE);
        }


        //Hr Reimbursement
        Reimbursement = reimbursement.getText().toString();
        if (Reimbursement.equals("YES")) {
            empid = empID.getText().toString();
            empprojects1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, HrReimbursement.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Reimbursement.equals("NO")) {
           // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            empprojects1.setVisibility(View.GONE);
        }

        //Payslips
        Payslips1 = payslips1.getText().toString();
        if (Payslips1.equals("YES")) {
            empid = empID.getText().toString();
            payslips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, HrPayslips.class);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Payslips1.equals("NO")) {
            //Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            payslips.setVisibility(View.GONE);
        }

        //Rewards
        Rewardsp = rewardsp.getText().toString();
        if (Rewardsp.equals("YES")) {
            empid = empID.getText().toString();
            rewards.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, RewardsUpdate.class);
                    intent.putExtra("employee",HR);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Rewardsp.equals("NO")) {
            // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            rewards.setVisibility(View.GONE);
        }

        //Careers
        Careersp = careersp.getText().toString();
        if (Careersp.equals("YES")) {
            empid = empID.getText().toString();
            careers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, Careers.class);
                    intent.putExtra("employee",HR);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Careersp.equals("NO")) {
            // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            careers.setVisibility(View.GONE);
        }

        //Calendar
        reimbursementdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrDashboard.this, CalendarActivity.class);
                intent.putExtra("employee",HR);
                intent.putExtra("empid",empid);
                startActivity(intent);
            }
        });

        visitors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrDashboard.this, EmpVisitorsList.class);
                intent.putExtra("employee",HR);
                intent.putExtra("empid",empid);
                startActivity(intent);
            }
        });


        //Interviews
        Interviewsp = interviewsp.getText().toString();
        if (Interviewsp.equals("YES")) {
            empid = empID.getText().toString();
            interviews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HrDashboard.this, Interviews_dashboard.class);
                    intent.putExtra("employee",HR);
                    intent.putExtra("empid",empid);
                    startActivity(intent);
                }
            });
        } else if (Interviewsp.equals("NO")) {
            // Toast.makeText(getApplicationContext(), "You don't have permission to access this", Toast.LENGTH_LONG).show();
            interviews.setVisibility(View.GONE);
        }
        reimbursementdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrDashboard.this, ReimbursementDashboard.class);
                intent.putExtra("employee",HR);
                intent.putExtra("empid",empid);
                startActivity(intent);
            }
        });

        ticketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HrDashboard.this,TicketingDashboard.class);
                i.putExtra("empid",empid);
                i.putExtra("officialemail",email);
                i.putExtra("firstname",firstname);
                i.putExtra("department",Department);
                i.putExtra("employee",HR);
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

                Intent i = new Intent(HrDashboard.this, Profile.class);
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
                i.putExtra("employee",HR);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(HrDashboard.this, "Related data is not there.", Toast.LENGTH_SHORT).show();
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

                Intent i = new Intent(HrDashboard.this, EditProfile.class);
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
                i.putExtra("employee",HR);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(HrDashboard.this, "Related data is not there.", Toast.LENGTH_SHORT).show();
            }

        }
    }


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

                Intent i = new Intent(HrDashboard.this, OurUpdates.class);
                i.putExtra("empid", EMPID);
                i.putExtra("employee",HR);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(HrDashboard.this, "Related data is not there.", Toast.LENGTH_SHORT).show();

            }

        }
    }

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

                Intent i = new Intent(HrDashboard.this, MyPay.class);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("empid", EMPID);
                i.putExtra("payslips", PAYSLIPS);
                i.putExtra("reimbursement1", REIMBURSEMENT1);
                i.putExtra("employee",HR);

                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(HrDashboard.this, "Related data is not there", Toast.LENGTH_SHORT).show();
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
            Intent i = new Intent(HrDashboard.this,EmployeeAttendance.class);
            i.putExtra("empid", EMPID);
            i.putExtra("officialemail", OFFICIALEMAIL);
            i.putExtra("employee",HR);
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
        if(id == R.id.notification)
        {
           Intent intent = new Intent(HrDashboard.this,Notifications.class);
           intent.putExtra("employee",HR);
            intent.putExtra("empid",empid);
           startActivity(intent);
        }
        if(id == R.id.newsfeed)
        {
            Intent intent = new Intent(HrDashboard.this,NewsFeed.class);
            intent.putExtra("employee",HR);
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

                Intent i = new Intent(HrDashboard.this, QueryForm.class);
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

                Toast.makeText(HrDashboard.this,"Invalid Details", Toast.LENGTH_LONG).show();
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
                        HrDashboard.super.onBackPressed();
                    }
                }).create().show();
    }
}
