package com.example.anushak.altaoss;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;


public class NewEmployeeForm extends AppCompatActivity {

    EditText empid, department, designation, firstname, middlename, lastname, bloodgroup, contactno, interviewschedule, interviewdoneby, dateofjoining, permanentaddress, correspondanceaddress, officialemail, personalemail;
    String Empid, Department, Designation, Firstname, Middlename, Lastname, Gender,  Bloodgroup, Contactno, Interviewschedule, Interviewdoneby, Dateofjoining, Permanentaddress, Correspondanceaddress, Officialemail, Personalemail;
    EditText projectmanagermail,projectmanagerid,hrmail,hrid,accountno,password,answer,proofnumber,dob,bankname,ifsccode,cardholdername;
    String Projectmanagermail,Projectmanagerid,Hrmail,Hrid,Accountno,Password,Answer,Proofnumber,Idproof,Security,DOB,Bankname,Ifsccode,Cardholdername;
    Spinner idproof,security;
    RadioGroup radiogroup;
    Button submit;
    Context ctx=this;
    DatePickerDialog datePickerDialog;
    TextView text1,text2;
    ImageView iv25;
    TextView hr;
    String OFFICIALEMAIL,MANAGERMAIL,MANAGERID,HRMAIL,HRID,EMPID,Text1,Text2;
    android.support.v7.widget.Toolbar toolbar;
    ImageView date,date1,date2;
    TextView notificationtitle,notificationmessage;
    String NotificationTitle,NotificationMessage;

    ImageButton btn;
    ImageView imageView;

    Bitmap FixBitmap;

    String ImageTag = "image_tag" ;

    String ImageName = "image_data" ;
    String ImageName1 = "image_data1" ;

    ProgressDialog progressDialog ;

    ByteArrayOutputStream byteArrayOutputStream ;

    byte[] byteArray ;

    String ConvertImage ;

    String GetImageNameFromEditText,GetImageNameFromEditText1;

    HttpURLConnection httpURLConnection ;

    URL url;

    OutputStream outputStream;

    BufferedWriter bufferedWriter ;

    int RC ;

    BufferedReader bufferedReader ;

    StringBuilder stringBuilder;

    boolean check = true;

    String Iv1;
    private int GALLERY = 1, CAMERA = 2;
//    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_new_employee_form);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // requestMultiplePermissions();

        imageView = (ImageView) findViewById(R.id.imageview);
        btn = (ImageButton) findViewById(R.id.btn);
        empid = (EditText) findViewById(R.id.etID);
        department = (EditText) findViewById(R.id.etDepartment);
        designation = (EditText) findViewById(R.id.etDesignation);
        firstname = (EditText) findViewById(R.id.etFirstName);
        middlename = (EditText) findViewById(R.id.etMiddleName);
        lastname = (EditText) findViewById(R.id.etLastName);
        bloodgroup = (EditText) findViewById(R.id.etBloodGroup);
        contactno = (EditText) findViewById(R.id.etContactNumber);
        interviewschedule = (EditText) findViewById(R.id.etInterviewSchedule);
        interviewdoneby = (EditText) findViewById(R.id.etInterviewDoneBy);
        dateofjoining = (EditText) findViewById(R.id.etDateofJoining);
        permanentaddress = (EditText) findViewById(R.id.etPermanentAddress);
        correspondanceaddress = (EditText) findViewById(R.id.etCorresspondenceAddress);
        officialemail = (EditText) findViewById(R.id.etOfficialEmail);
        personalemail = (EditText) findViewById(R.id.etPersonalEmail);
        radiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        submit = (Button) findViewById(R.id.register_register);
        text1 = (TextView)findViewById(R.id.Grid1);
        text2 = (TextView)findViewById(R.id.Grid2);
        btn=(ImageButton)findViewById(R.id.btn);
        iv25 = (ImageView) findViewById(R.id.iv25);
        hr = (TextView) findViewById(R.id.hr);
        projectmanagermail = (EditText) findViewById(R.id.projectmanagermail);
        projectmanagerid = (EditText) findViewById(R.id.projectmanagerid);
        hrmail = (EditText) findViewById(R.id.hrmail);
        hrid = (EditText) findViewById(R.id.hrid);
        accountno = (EditText) findViewById(R.id.accountno);
        password = (EditText) findViewById(R.id.password);
        proofnumber = (EditText) findViewById(R.id.etDetails);
        answer = (EditText) findViewById(R.id.answer);
        security = (Spinner) findViewById(R.id.spinner);
        idproof = (Spinner) findViewById(R.id.spidentity);
        dob = (EditText) findViewById(R.id.dob);
        bankname = (EditText) findViewById(R.id.bankname);
        ifsccode = (EditText) findViewById(R.id.ifsccode);
        cardholdername = (EditText) findViewById(R.id.cardname);
        date = (ImageView) findViewById(R.id.date_dob);
        date1 = (ImageView) findViewById(R.id.date_doj);
        date2 = (ImageView) findViewById(R.id.date_Interview);

        notificationtitle = (TextView) findViewById(R.id.notificationtitle);
        notificationmessage = (TextView) findViewById(R.id.notificationmessage);

        Text1 = getIntent().getStringExtra("empid");
        text1.setText(Text1);
        text2.setText("HR Dashboard - Employee From-"+Text1);

        notificationtitle.setText("New Employee");
        notificationmessage.setText("New Employee has been created by "+Text1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        if (ContextCompat.checkSelfPermission(NewEmployeeForm.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA},
                        5);
            }
        }



        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewEmployeeForm.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                interviewschedule.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewEmployeeForm.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateofjoining.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(NewEmployeeForm.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        iv25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEmployeeForm.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

    }


    private void showPictureDialog(){
        android.support.v7.app.AlertDialog.Builder pictureDialog = new android.support.v7.app.AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Photo Gallery",
                "Camera",
                "Cancel"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    FixBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    // String path = saveImage(bitmap);
                    //Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(FixBitmap);
                    // ShowSelectedImage.findFocus();
                    //             UploadImageOnServerButton.setVisibility(View.VISIBLE);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(NewEmployeeForm.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            FixBitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(FixBitmap);
            //       UploadImageOnServerButton.setVisibility(View.VISIBLE);
            /*Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            intent.putExtra("data", FixBitmap);
            startActivity(intent);*///  saveImage(thumbnail);
            //Toast.makeText(ShadiRegistrationPart5.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }



    public void register_register(View v){
        NotificationTitle = notificationtitle.getText().toString();
        NotificationMessage = notificationmessage.getText().toString();
        Iv1 = imageView.getImageMatrix().toString();
        Empid = empid.getText().toString();
        Department = department.getText().toString();
        Designation = designation.getText().toString();
        Firstname = firstname.getText().toString();
        Middlename = middlename.getText().toString();
        Lastname = lastname.getText().toString();
        Bloodgroup = bloodgroup.getText().toString();
        Contactno = contactno.getText().toString();
        Interviewschedule = interviewschedule.getText().toString();
        Interviewdoneby = interviewdoneby.getText().toString();
        Dateofjoining = dateofjoining.getText().toString();
        Permanentaddress = permanentaddress.getText().toString();
        Correspondanceaddress = correspondanceaddress.getText().toString();
        Officialemail = officialemail.getText().toString();
        Personalemail = personalemail.getText().toString();
        Gender = ((RadioButton) findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString().trim();
        Projectmanagermail = projectmanagermail.getText().toString();
        Projectmanagerid = projectmanagerid.getText().toString();
        Hrmail = hrmail.getText().toString();
        Hrid = hrid.getText().toString();
        Accountno = accountno.getText().toString();
        Password = password.getText().toString();
        Idproof = idproof.getSelectedItem().toString();
        Security = security.getSelectedItem().toString();
        Answer = answer.getText().toString();
        Proofnumber = proofnumber.getText().toString();
        DOB = dob.getText().toString();
        Bankname = bankname.getText().toString();
        Ifsccode = ifsccode.getText().toString();
        Cardholdername = cardholdername.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        //i.putExtra(Intent.ACTION_SEND, email);
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{Officialemail});
        i.putExtra(Intent.EXTRA_SUBJECT, "Login Details");
        //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
        i.putExtra(Intent.EXTRA_TEXT   , "UserName:"+Empid+"   Password:"+Password+"   Security Question:"+Security+"   Answer:"+Answer);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(NewEmployeeForm.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(Empid)) {
            empid.setError("Please enter ID");
            empid.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Department)) {
            department.setError("Please enter Department");
            department.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Designation)) {
            designation.setError("Please enter Designation");
            designation.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Firstname)) {
            firstname.setError("Please enter F_name");
            firstname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Middlename)) {
            middlename.setError("Please enter M_name");
            middlename.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Lastname)) {
            lastname.setError("Please enter L_name");
            lastname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            password.setError("Please enter Password");
            password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Bloodgroup)) {
            bloodgroup.setError("Please enter Blood_group");
            bloodgroup.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Contactno)) {
            contactno.setError("Please enter Contact_no");
            contactno.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Interviewschedule)) {
            interviewschedule.setError("Please enter Interview_schedule");
            interviewschedule.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Interviewdoneby)) {
            interviewdoneby.setError("Please enter Interview_doneby");
            interviewdoneby.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Dateofjoining)) {
            dateofjoining.setError("Please enter Dateofjoining");
            dateofjoining.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Permanentaddress)) {
            permanentaddress.setError("Please enter Permanent_address");
            permanentaddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Correspondanceaddress)) {
            correspondanceaddress.setError("Please enter Correspondance_address");
            correspondanceaddress.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Officialemail)) {
            officialemail.setError("Please enter Official_email");
            officialemail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Personalemail)) {
            personalemail.setError("Please enter Personal_email");
            personalemail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Projectmanagermail)) {
            projectmanagermail.setError("Please enter Project Manager Mail");
            projectmanagermail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Projectmanagerid)) {
            projectmanagerid.setError("Please enter Project Manager ID");
            projectmanagerid.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Hrmail)) {
            hrmail.setError("Please enter HR Email");
            hrmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Hrid)) {
            hrid.setError("Please enter HR ID");
            hrid.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Accountno)) {
            accountno.setError("Please enter Account no");
            accountno.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Proofnumber)) {
            proofnumber.setError("Please enter Proof Number");
            proofnumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Answer)) {
            answer.setError("Please enter Answer");
            answer.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(DOB)) {
            dob.setError("Please enter Date of Birth");
            dob.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Bankname)) {
            bankname.setError("Please enter Bank Name");
            bankname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Ifsccode)) {
            ifsccode.setError("Please enter IFSC Code");
            ifsccode.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(Cardholdername)) {
            cardholdername.setError("Please enter Card Holder Name");
            cardholdername.requestFocus();
            return;
        }

        BackGround b = new BackGround();
        b.execute(Empid, Department, Designation, Firstname, Middlename, Lastname, Gender, Bloodgroup, Contactno, Interviewschedule, Interviewdoneby, Dateofjoining, Permanentaddress, Correspondanceaddress, Officialemail, Personalemail, Projectmanagermail, Projectmanagerid, Hrmail,Hrid, Accountno, Iv1, Password, Idproof, Proofnumber, Security, Answer,DOB,Bankname,Ifsccode,Cardholdername);

        BackGroundNotification b1 = new BackGroundNotification();
        b1.execute(NotificationTitle,NotificationMessage);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String department = params[1];
            String designation = params[2];
            String firstname = params[3];
            String middlename = params[4];
            String lastname = params[5];
            String gender = params[6];
            String bloodgroup = params[7];
            String contactno = params[8];
            String interviewschedule = params[9];
            String interviewdoneby = params[10];
            String dateofjoining = params[11];
            String permanentaddress = params[12];
            String correspondanceaddress = params[13];
            String officialemail = params[14];
            String personalemail = params[15];
            String projectmanagermail = params[16];
            String projectmanagerid = params[17];
            String hrmail = params[18];
            String hrid = params[19];
            String accountno = params[20];
            String iv1 = params[21];
            String password = params[22];
            String idproof = params[23];
            String proofnumber = params[24];
            String security = params[25];
            String answer = params[26];
            String dob = params[27];
            String bankname = params[28];
            String ifsccode = params[29];
            String cardholdername = params[30];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/register1.php");
                String urlParams = "empid="+empid+"&department="+department+"&designation="+designation+"&firstname="+firstname+"&middlename="+middlename+"&lastname="+lastname+"&gender="+gender+"&bloodgroup="+bloodgroup+"&contactno="+contactno+"&interviewschedule="+interviewschedule+"&interviewdoneby="+interviewdoneby+"&dateofjoining="+dateofjoining+"&permanentaddress="+permanentaddress+"&correspondanceaddress="+correspondanceaddress+"&officialemail="+officialemail+"&personalemail="+personalemail+"&projectmanagermail="+projectmanagermail+"&managerid="+projectmanagerid+"&Hrmail="+hrmail+"&Hrid="+hrid+"&accountno="+accountno+"&iv1="+iv1+"&password="+password+"&idproof="+idproof+"&proofnumber="+proofnumber+"&security="+security+"&answer="+answer+"&dob="+dob+"&bankname="+bankname+"&ifsccode="+ifsccode+"&cardholdername="+cardholdername;

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
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }

    class BackGroundNotification extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String title = params[0];
            String message = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/notification.php");
                String urlParams = "title="+title+"&message="+message;
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
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(NewEmployeeForm.this, s, Toast.LENGTH_LONG).show();
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
            Text2 = text2.getText().toString();
            Text1 = text1.getText().toString();
            BackGroundQuery b = new BackGroundQuery();
            b.execute(Text1);
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

                Intent i = new Intent(NewEmployeeForm.this, QueryForm.class);
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

                Toast.makeText(NewEmployeeForm.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }
}

