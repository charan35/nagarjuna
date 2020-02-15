package com.example.anushak.altaoss;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Documents extends AppCompatActivity {

    TextView admin,documents,joiningletter,releavingletter,presentofferletter,previousofferletter,previousexperienceletter,previouspayslips,certificates,bankstatements,coursecertificates,patent;
    TextView empId, firstName,text1,text2;
    String empid, firstname,Text1,Text2;
    String FIRSTNAME = null, EMPID = null;
    ImageView iv25;
    String NAME = null, EMPURL = null,OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_documents);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        joiningletter = (TextView) findViewById(R.id.joiningletter);
        releavingletter = (TextView) findViewById(R.id.releavingletter);
        presentofferletter = (TextView) findViewById(R.id.presentofferletter);
        previousofferletter=(TextView)findViewById(R.id.previousofferletter);
        previousexperienceletter=(TextView)findViewById(R.id.previousexperienceletter);
        previouspayslips = (TextView)findViewById(R.id.previouspayslips);
        certificates = (TextView)findViewById(R.id.certificates);
        bankstatements = (TextView)findViewById(R.id.bankstatements);
        coursecertificates = (TextView)findViewById(R.id.coursecertificates);
        patent =(TextView)findViewById(R.id.patent);
        text1=(TextView)findViewById(R.id.Grid1);
        text2 =(TextView)findViewById(R.id.Grid2);
        admin = (TextView)findViewById(R.id.admin);
        documents = (TextView) findViewById(R.id.documents);
        iv25 = (ImageView) findViewById(R.id.iv25);

        empId = (TextView) findViewById(R.id.tvEmpID);
        firstName = (TextView) findViewById(R.id.tvFirstName);

        Text1 = getIntent().getStringExtra("email1");
        empid = getIntent().getStringExtra("empid");
        firstname = getIntent().getStringExtra("firstname");

        empId.setText(empid);
        firstName.setText(firstname);
        text1.setText(Text1);

        text2.setText("Admin Dashboard - EmpDocuments - Documents"+"("+empid+")");

        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Documents.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Documents.this,EmployeeDocuments.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        //bank statements
        bankstatements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundbank b = new BackGroundbank();
                b.execute(empid);
            }
        });
        //joining letter
        joiningletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundjoin b = new BackGroundjoin();
                b.execute(empid);
            }
        });
        //releaving letter
        releavingletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundreleaving b = new BackGroundreleaving();
                b.execute(empid);
            }
        });
        //present offer letter
        presentofferletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundpresentol b = new BackGroundpresentol();
                b.execute(empid);
            }
        });
        //previous offer letter
        previousofferletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundpreviousol b = new BackGroundpreviousol();
                b.execute(empid);
            }
        });
        //previous experience letter
        previousexperienceletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundpreviousexperience b = new BackGroundpreviousexperience();
                b.execute(empid);
            }
        });
        //payslips
        previouspayslips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundpreviouspayslips b = new BackGroundpreviouspayslips();
                b.execute(empid);
            }
        });
        //certificates
        certificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundcertificates b = new BackGroundcertificates();
                b.execute(empid);
            }
        });
        //course
        coursecertificates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundcourse b = new BackGroundcourse();
                b.execute(empid);
            }
        });
        //patent
        patent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empid = empId.getText().toString();
                BackGroundpatent b = new BackGroundpatent();
                b.execute(empid);
            }
        });

    }

    //Joining Letter
    class BackGroundjoin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/joiningPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");

                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid", EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //Releaving Letter
    class BackGroundreleaving extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/releavingPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");;
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid", EMPID);
                startActivity(i);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Present Offer Letter
    class BackGroundpresentol extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/presentofferPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Previous Offer Letter
    class BackGroundpreviousol extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/previousofferPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Previous Experience letter
    class BackGroundpreviousexperience extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/previousexperiencePdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Previous Payslips
    class BackGroundpreviouspayslips extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/previouspayslipsPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Certificates
    class BackGroundcertificates extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/certificatesPdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //BankStatements
    class BackGroundbank extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/bankstatementspdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Course Certificates
    class BackGroundcourse extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/coursecertificatespdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Patent Certificates
    class BackGroundpatent extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String data = "";
            int tmp;
            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/patentpdf.php");
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
                JSONObject pdfs = root.getJSONObject("pdfs");
                NAME = pdfs.getString("name");
                EMPURL=pdfs.getString("url");
                EMPID=pdfs.getString("empid");
                Intent i = new Intent(Documents.this, EmpDoc.class);
                i.putExtra("name", NAME);
                i.putExtra("url", EMPURL);
                i.putExtra("empid",EMPID);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Documents.this,"Related Document is not there..", Toast.LENGTH_SHORT).show();
            }
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
        if(id == R.id.query)
        {
            Text1 = text1.getText().toString();
            Text2 = text2.getText().toString();
            empid = empId.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(empid);
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

                Intent i = new Intent(Documents.this, QueryForm.class);
                i.putExtra("empid", EMPID);
                i.putExtra("officialemail", OFFICIALEMAIL);
                i.putExtra("managerId", MANAGERID);
                i.putExtra("projectmanagermail", MANAGERMAIL);
                i.putExtra("Hrid", HRID);
                i.putExtra("Hrmail", HRMAIL);
                i.putExtra("message", Text2);
                startActivity(i);
            }

            catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(Documents.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}
