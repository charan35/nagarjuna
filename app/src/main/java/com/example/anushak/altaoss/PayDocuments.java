package com.example.anushak.altaoss;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class PayDocuments extends AppCompatActivity {

    TextView Empid,Month,Year,text,urltext,payname;
    ImageButton Url;
    Button download;
    String url, empid,month,year,Text;
    String OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,URLTEXT,PAYNAME;
    DownloadManager dm;
    android.support.v7.widget.Toolbar toolbar;

    LinearLayout lq,lq1,lq2;
    TextView admin,admin1,admin2,pay,pay11,pay12,pays,pays1,pays2,payt,payt1,payt2;
    ImageView iv25,iv251,iv252;
    TextView text2;
    String Text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_pay_documents);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Url = (ImageButton) findViewById(R.id.paydocUrl);
        Empid = (TextView) findViewById(R.id.PayID);
        Month = (TextView) findViewById(R.id.month);
        Year = (TextView) findViewById(R.id.year);
        text = (TextView)findViewById(R.id.Grid2);
        download = (Button)findViewById(R.id.download);
        urltext = (TextView)findViewById(R.id.url);
        payname = (TextView)findViewById(R.id.payname);

        admin = (TextView) findViewById(R.id.admin);
        admin1 = (TextView) findViewById(R.id.admin1);
        admin2 = (TextView) findViewById(R.id.admin2);
        pay = (TextView) findViewById(R.id.pay);
        pay11 = (TextView) findViewById(R.id.pay11);
        pay12 = (TextView) findViewById(R.id.pay12);
        pays = (TextView) findViewById(R.id.pays);
        pays1 = (TextView) findViewById(R.id.pays1);
        pays2 = (TextView) findViewById(R.id.pays2);
        payt = (TextView) findViewById(R.id.payt);
        payt1 = (TextView) findViewById(R.id.payt1);
        iv25 = (ImageView) findViewById(R.id.iv25);
        iv251 = (ImageView) findViewById(R.id.iv251);
        iv252 = (ImageView) findViewById(R.id.iv252);
        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        text2 = (TextView) findViewById(R.id.text2);

        url = getIntent().getStringExtra("url");
        empid = getIntent().getStringExtra("empid");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");
        Text2 = getIntent().getStringExtra("employee");
        URLTEXT = getIntent().getStringExtra("url");

        urltext.setText(URLTEXT);

        Empid.setText(empid);
        Month.setText(month);
        Year.setText(year);
        Month.setText(month);
        Year.setText(year);
        text2.setText(Text2);
        payname.setText("Payslip("+month+"-"+ year+")");

        //Url.setText(url);

        Url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = dm.enqueue(request);
                Toast.makeText(PayDocuments.this,"Downloaded successfully...", Toast.LENGTH_LONG).show();
                empid=Empid.getText().toString();
                URLTEXT = urltext.getText().toString();
                PAYNAME = payname.getText().toString();
                BackGroundDownload b = new BackGroundDownload();
                b.execute(empid,URLTEXT,PAYNAME);
            }
        });



        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pays.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,PaySlips.class);
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

        admin1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pay11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pays1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,PaySlips.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv252.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        pay12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        pays2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayDocuments.this,PaySlips.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });



        lq.setVisibility(Text2.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);

        if(Text2.equals("employee"))
        {
            text.setText("Employee Dashboard - My Pay - paySlip" + "\n "+month +"/"+ year);
        }

        else  if(Text2.equals("admin"))
        {
            text.setText("Admin Dashboard - My Pay - paySlip" + "\n "+month +"/"+ year);
        }
        else  if(Text2.equals("hr"))
        {
            text.setText("HR Dashboard - My Pay - paySlip" + "\n "+month +"/"+ year);
        }


    }

    class BackGroundDownload extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String url1 = params[1];
            String name = params[2];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.55/AltaCRM/downloads.php");
                String urlParams = "empid="+empid+"&url="+url1+"&name="+name;

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
          /*  if (s.equals("")) {
                s = "Data saved successfully.";
            }

            Toast.makeText(EmpPayrollDoc.this, s, Toast.LENGTH_LONG).show();*/
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
            empid = Empid.getText().toString();
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

                Intent i = new Intent(PayDocuments.this, QueryForm.class);
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

                Toast.makeText(PayDocuments.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}
