package com.example.anushak.altaoss;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    RelativeLayout rellay1,rellay2;
    Handler handler = new Handler();
    EditText empid, password;
    String empID, Password;
    String FIRSTNAME=null,EMAIL=null,EMPID=null,PASSWORD=null,PROFILE,PROJECT,MESSAGE,MYPAY,ATTENDANCE,UPDATES,MYPROJECT,CAREERS,REWARDS,INTERVIEWS;
    String NEWEMPFORM,EMPDIRECTORY11,PROJECTDETAILS,EMPATTENDANCE11,REIMBURSEMENT,DOCUMENTSUPLOAD,PAYSLIPS1,CCCAMERA,VISITOR,LocationText;
    String PAYROLL,DOCUMENTSFETCH,PERMISSION,DEPARTMENT;
    TextView frgpass,locationText;
    RadioGroup Rg;
    Context ctx=this;
    RadioButton rdamdin,rdemp,hrrd;
    Button login,visitor;
    android.support.v7.widget.Toolbar toolbar;
    LocationManager locationManager;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocation();
        setContentView(R.layout.activity_main);

        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        frgpass=(TextView)findViewById(R.id.frgpass);
        empid = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);
        Rg=(RadioGroup)findViewById(R.id.radioGroup);
        rdamdin=(RadioButton)findViewById(R.id.adminrd);
        rdemp=(RadioButton)findViewById(R.id.emprd);
        hrrd=(RadioButton) findViewById(R.id.hrrd);
        login = (Button)findViewById(R.id.main_login);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationText = (TextView)findViewById(R.id.locationText);
        visitor = (Button)findViewById(R.id.visitor);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }


        frgpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmpVisitors.class);
                startActivity(intent);
            }
        });

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addNotification();
               LocationText = locationText.getText().toString();
               empID = empid.getText().toString();
               Password = password.getText().toString();
               if (TextUtils.isEmpty(empID)) {
                   empid.setError("Please enter Employee ID");
                   empid.requestFocus();
                   return;
               }
               if (TextUtils.isEmpty(Password)) {
                   password.setError("Please enter Password");
                   password.requestFocus();
                   return;
               }
               if(Rg.getCheckedRadioButtonId() == -1){

               }
               if(rdemp.isChecked()){
                   BackGround b = new BackGround();
                   b.execute(empID, Password);
               }
               if(hrrd.isChecked()){

                   BackGroundHr b = new BackGroundHr();
                   b.execute(empID, Password);
               }
               if(rdamdin.isChecked()){

                   BackGroundAdmin b = new BackGroundAdmin();
                   b.execute(empID, Password);
               }
               BackGroundGps G=new BackGroundGps();
               G.execute(empID,LocationText);
           }
       });

        handler.postDelayed(runnable,2000);//2000 is the timeout for the splash
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_foreground) //set icon for notification
                        .setContentTitle("Notifications") //set title of notification
                        .setContentText("Please Check our Notifications")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification

        Intent notificationIntent = new Intent(this, Notifications.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
       // notificationIntent.putExtra("message", "This is a notification message");

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    class BackGround extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                //URL url = new URL("http://altaitsolutions.com/Altadatabase/login.php");
                 URL url = new URL("http://altaitsolutions.com/Altadatabase/employee_login.php");
                String urlParams = "empid=" + empid + "&password=" + password;

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
                EMAIL = user_data.getString("officialemail");
                EMPID = user_data.getString("empid");
                PASSWORD = user_data.getString("password");
                PROFILE = user_data.getString("profile");
                PROJECT = user_data.getString("project");
                MESSAGE = user_data.getString("message");
                MYPAY = user_data.getString("mypay");
                ATTENDANCE = user_data.getString("attendance");
                UPDATES = user_data.getString("updates");
                MYPROJECT = user_data.getString("myproject");
                CAREERS = user_data.getString("careers");

                Intent i= new Intent(ctx,EmployeeDashboard.class);
               i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
                i.putExtra("empid", EMPID);
                i.putExtra("profile", PROFILE);
                i.putExtra("project", PROJECT);
                i.putExtra("message", MESSAGE);
                i.putExtra("mypay", MYPAY);
                i.putExtra("attendance", ATTENDANCE);
                i.putExtra("updates", UPDATES);
                i.putExtra("myproject", MYPROJECT);
                i.putExtra("careers", CAREERS);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ctx,"Invalid username and password", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
            }
        }
    }

    class BackGroundHr extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
              //  URL url = new URL("http://altaitsolutions.com/Altadatabase/hrlogin.php");
                URL url = new URL("http://altaitsolutions.com/Altadatabase/hr_login.php");
                String urlParams = "empid=" + empid + "&password=" + password;

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
                EMAIL = user_data.getString("officialemail");
                EMPID = user_data.getString("empid");
                PASSWORD = user_data.getString("password");

                PROFILE = user_data.getString("profile");
                PROJECT = user_data.getString("project");
                MESSAGE = user_data.getString("message");
                MYPAY = user_data.getString("mypay");
                ATTENDANCE = user_data.getString("attendance");
                UPDATES = user_data.getString("updates");
                NEWEMPFORM = user_data.getString("newempform");
                EMPDIRECTORY11 = user_data.getString("empdirectory");
                PROJECTDETAILS = user_data.getString("projectdetails");
                EMPATTENDANCE11 = user_data.getString("empattendance");
                REIMBURSEMENT = user_data.getString("reimbursement");
                DOCUMENTSUPLOAD = user_data.getString("documentsupload");
                PAYSLIPS1 = user_data.getString("payslips1");
                REWARDS = user_data.getString("rewards");
                CAREERS = user_data.getString("careers");
                INTERVIEWS = user_data.getString("interviews");

                Intent i= new Intent(ctx,HrDashboard.class);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
                i.putExtra("empid", EMPID);

                i.putExtra("profile", PROFILE);
                i.putExtra("project", PROJECT);
                i.putExtra("message", MESSAGE);
                i.putExtra("mypay", MYPAY);
                i.putExtra("attendance", ATTENDANCE);
                i.putExtra("updates", UPDATES);
                i.putExtra("newempform", NEWEMPFORM);
                i.putExtra("empdirectory", EMPDIRECTORY11);
                i.putExtra("projectdetails", PROJECTDETAILS);
                i.putExtra("empattendance", EMPATTENDANCE11);
                i.putExtra("reimbursement", REIMBURSEMENT);
                i.putExtra("documentsupload", DOCUMENTSUPLOAD);
                i.putExtra("payslips1", PAYSLIPS1);
                i.putExtra("rewards", REWARDS);
                i.putExtra("careers", CAREERS);
                i.putExtra("interviews", INTERVIEWS);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ctx,"Invalid username and password", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
            }
        }
    }

    class BackGroundAdmin extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
               // URL url = new URL("http://altaitsolutions.com/Altadatabase/adminlogin.php");
                URL url = new URL("http://altaitsolutions.com/Altadatabase/admin_login.php");
                String urlParams = "empid=" + empid + "&password=" + password;

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
                EMAIL = user_data.getString("officialemail");
                EMPID = user_data.getString("empid");
                DEPARTMENT =user_data.getString("department");
                PASSWORD = user_data.getString("password");

                PROFILE = user_data.getString("profile");
                PROJECT = user_data.getString("project");
                MESSAGE = user_data.getString("message");
                MYPAY = user_data.getString("mypay");
                ATTENDANCE = user_data.getString("attendance");
                UPDATES = user_data.getString("updates");
                NEWEMPFORM = user_data.getString("newempform");
                EMPDIRECTORY11 = user_data.getString("empdirectory");
                PROJECTDETAILS = user_data.getString("projectdetails");
                EMPATTENDANCE11 = user_data.getString("empattendance");
                REIMBURSEMENT = user_data.getString("reimbursement");
                DOCUMENTSUPLOAD = user_data.getString("documentsupload");
                PAYROLL = user_data.getString("payroll");
                DOCUMENTSFETCH = user_data.getString("documentsfetch");
                PERMISSION = user_data.getString("permission");
                CCCAMERA = user_data.getString("cccamera");
                PAYSLIPS1 = user_data.getString("payslips1");
                CAREERS = user_data.getString("careers");
                INTERVIEWS = user_data.getString("interviews");
                VISITOR = user_data.getString("visitor");

                Intent i= new Intent(ctx,AdminDashboard.class);
                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
                i.putExtra("empid", EMPID);
                i.putExtra("department",DEPARTMENT);
                i.putExtra("profile", PROFILE);
                i.putExtra("project", PROJECT);
                i.putExtra("message", MESSAGE);
                i.putExtra("mypay", MYPAY);
                i.putExtra("attendance", ATTENDANCE);
                i.putExtra("updates", UPDATES);
                i.putExtra("newempform", NEWEMPFORM);
                i.putExtra("empdirectory", EMPDIRECTORY11);
                i.putExtra("projectdetails", PROJECTDETAILS);
                i.putExtra("empattendance", EMPATTENDANCE11);
                i.putExtra("reimbursement", REIMBURSEMENT);
                i.putExtra("documentsupload", DOCUMENTSUPLOAD);
                i.putExtra("payroll", PAYROLL);
                i.putExtra("documentsfetch", DOCUMENTSFETCH);
                i.putExtra("permission", PERMISSION);
                i.putExtra("cccamera", CCCAMERA);
                i.putExtra("payslips1", PAYSLIPS1);
                i.putExtra("careers", CAREERS);
                i.putExtra("interviews", INTERVIEWS);
                i.putExtra("visitor", VISITOR);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(ctx,"Invalid username and password", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                startActivity(i);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loginmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id == R.id.aboutus)
        {
            Intent intent=new Intent(MainActivity.this,AboutUs.class);
            startActivity(intent);
        }
        if (id == R.id.helpdesk) {
            Intent intent=new Intent(MainActivity.this,HelpDesk.class);
            startActivity(intent);
        }
        if (id == R.id.contactus)
        {
            Intent intent=new Intent(MainActivity.this,ContactUs.class);
            startActivity(intent);
        }
        if (id == R.id.services)
        {
            Intent intent=new Intent(MainActivity.this,MainActivity1.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getLocation() {

        try {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        }

        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());

        try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText()+"\n"+addresses.get(0).getAddressLine(0)+","+
                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));

        }catch(Exception e)
        {
            Toast.makeText(MainActivity.this, "Something went wrong ", Toast.LENGTH_SHORT).show();
        }

    }

    /* @Override
     public void onProviderDisabled(String provider) {
         Toast.makeText(MainActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
     }
 */
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


    }

    /**
     * Called when the provider is enabled by the user.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(MainActivity.this, " GPS and Internet has Enabled", Toast.LENGTH_SHORT).show();
    }
    /**
     * Called when the provider is disabled by the user. If requestLocationUpdates
     * is called on an already disabled provider, this method is called
     * immediately.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderDisabled(String provider) {
        statusCheck();
    }

 /*   @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(MainActivity.this, " GPS and Internet has Enabled", Toast.LENGTH_SHORT).show();


    }*/

    class BackGroundGps extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String locationText = params[1];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/gps_location.php");

                String urlParams = "empid=" + empid + "&locationText=" + locationText;
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

            if (s.equals("")) {
                s = "Login success";
            }
            Toast toast = Toast.makeText(getApplicationContext(),
                    s, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();


        }
    }
    public void statusCheck() {


        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            buildAlertMessageNoGps();

        }
    }


    private void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, please enable it")

                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                    }
                });

               /* .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });*/
        final AlertDialog alert = builder.create();
        alert.show();


    }

}
