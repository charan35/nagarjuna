package com.example.anushak.altaoss;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

public class EmpReimbursement extends AppCompatActivity {

    Button SelectButton, UploadButton,SelectButton1,UploadButton1,SelectButton2,UploadButton2;

    TextView empId,text;
    String empid,Text;

    EditText PdfNameEditText,PdfNameEditText1,PdfNameEditText2,PdfNameEditText4 ;
    TextView PdfNameEditText3,mobilestatus;
    ImageView Pdfdate;
    Spinner PdfSpinner1,PdfSpinner2,monthspinner2,yesrspinner2;

    EditText PdfNameEdittext,PdfNameEdittext1,PdfNameEdittext2 ;
    Spinner Pdfspinner1,Pdfspinner2,monthspinner3,yesrspinner3;
    ImageView dateimage;
    TextView generalstatus;

    EditText PdfNameeditText2,PdfNameeditText3,PdfNameeditText4 ,PdfNameeditText5;
    TextView PdfNameeditText,PdfNameeditText1,PdfTextview;
    Spinner Pdfspinner,PdfSpinner,monthspinner1,yesrspinner1;
    ImageView Pdfdate1,Pdfdate2;
    TextView conveyancestatus;

    String MANAGERMAIL,MANAGERID,HRMAIL,HRID,OFFICIALEMAIL,EMPID,DateImage;

    LinearLayout lq,lq1,lq2;
    ImageView iv13,iv131,iv132;
    TextView dashboard,mypay,dashboard1,mypay1,dashboard2,mypay2;
    TextView text2;
    String Text2;
    ViewFlipper v_flipper;

    Uri uri;

    public static final String PDF_UPLOAD_HTTP_URL = "http://altaitsolutions.com/Altadatabase/file_upload.php";
    public static final String PDF_UPLOAD_HTTP_URL1 = "http://altaitsolutions.com/Altadatabase/file_upload1.php";
    public static final String PDF_UPLOAD_HTTP_URL2 = "http://altaitsolutions.com/Altadatabase/file_upload2.php";

    public int PDF_REQ_CODE = 1;

    String MobileStatus,PdfNameHolder, PdfNameHolder1,PdfNameHolder2,PdfNameHolder3,PdfNameHolder4,PdfSpinnerHolder1,PdfSpinnerHolder2,PdfMonthSpinner2,PdfYearSpinner2,PdfPathHolder, PdfID;
    String GeneralStatus,PdfNameholder, PdfNameholder1,PdfNameholder2,PdfSpinnerholder1,PdfSpinnerholder2,PdfMonthSpinner3,PdfYearSpinner3,PdfPathHolder1, PdfID1;
    String ConveyanceStatus,PdfnameHolder,PdfnameHolder1,PdfnameHolder2,PdfnameHolder3,PdfnameHolder4,PdfnameHolder5,Pdfspinnerholder1,PdfSpinnerhHolder1,PdfTextHolder1,PdfMonthSpinner1,PdfYearSpinner1,PdfPathHolder2, PdfID2;

    LinearLayout linearLayout1,linearLayout2,linearLayout3;
    DatePickerDialog datePickerDialog;

    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_emp_reimbursement);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lq = (LinearLayout) findViewById(R.id.lq);
        lq1 = (LinearLayout) findViewById(R.id.lq1);
        lq2 = (LinearLayout) findViewById(R.id.lq2);
        iv13 = (ImageView) findViewById(R.id.iv13);
        iv131 = (ImageView) findViewById(R.id.iv131);
        iv132 = (ImageView) findViewById(R.id.iv132);
        dashboard = (TextView) findViewById(R.id.dashboard);
        dashboard1 = (TextView) findViewById(R.id.dashboard1);
        dashboard2 = (TextView) findViewById(R.id.dashboard2);
        mypay = (TextView) findViewById(R.id.mypay);
        mypay1 = (TextView) findViewById(R.id.mypay1);
        mypay2 = (TextView) findViewById(R.id.mypay2);
        text2 = (TextView) findViewById(R.id.text2);

        linearLayout1 = (LinearLayout) findViewById(R.id.linear1);
        SelectButton = (Button) findViewById(R.id.button);
        UploadButton = (Button) findViewById(R.id.button2);
        PdfNameEditText = (EditText) findViewById(R.id.editText);
        PdfNameEditText1 = (EditText) findViewById(R.id.editText1);
        PdfNameEditText2 = (EditText) findViewById(R.id.editText2);
        PdfNameEditText3 = (TextView) findViewById(R.id.editText3);
        PdfNameEditText4 = (EditText) findViewById(R.id.editText4);
        PdfSpinner1=(Spinner)findViewById(R.id.Spinner1);
        PdfSpinner2=(Spinner)findViewById(R.id.spinner2);
        monthspinner2=(Spinner)findViewById(R.id.mobilemonth1);
        yesrspinner2=(Spinner)findViewById(R.id.mobileyear1);
        Pdfdate =(ImageView)findViewById(R.id.general_date);
        mobilestatus = (TextView)findViewById(R.id.mobilestatus);

        int images[]={R.drawable.reimbursement, R.drawable.empreimbursement, R.drawable.reimbursement2};

        v_flipper = (ViewFlipper)findViewById(R.id.v_flipper);

        empId=(TextView)findViewById(R.id.textview);
        text = (TextView)findViewById(R.id.Grid2);
        empid = getIntent().getStringExtra("empid");
        Text2 = getIntent().getStringExtra("employee");

        empId.setText(empid);
        text2.setText(Text2);

        linearLayout2 = (LinearLayout) findViewById(R.id.linear2);
        SelectButton1 = (Button) findViewById(R.id.expensesbutton);
        UploadButton1 = (Button) findViewById(R.id.submitbutton2);
        PdfNameEdittext = (EditText) findViewById(R.id.expenseseditText1);
        PdfNameEdittext1 = (EditText) findViewById(R.id.expenseseditText2);
        PdfNameEdittext2 = (EditText) findViewById(R.id.expenseseditText3);
        Pdfspinner1=(Spinner)findViewById(R.id.generalSpinner1);
        Pdfspinner2=(Spinner)findViewById(R.id.expensesspinner2);
        monthspinner3=(Spinner)findViewById(R.id.generalmonth1);
        yesrspinner3=(Spinner)findViewById(R.id.generalyear1);
        dateimage=(ImageView)findViewById(R.id.dateimage);
        generalstatus = (TextView)findViewById(R.id.generalstatus);

        linearLayout3 = (LinearLayout) findViewById(R.id.linear3);
        SelectButton2 = (Button) findViewById(R.id.conveyancebutton);
        UploadButton2 = (Button) findViewById(R.id.submitbutton3);
        PdfNameeditText=(TextView)findViewById(R.id.conveyanceeditText1);
        PdfNameeditText1=(TextView)findViewById(R.id.conveyanceeditText2);
        PdfNameeditText2=(EditText)findViewById(R.id.conveyanceeditText3);
        PdfNameeditText3=(EditText)findViewById(R.id.conveyanceeditText4);
        PdfNameeditText4=(EditText)findViewById(R.id.conveyanceeditText5);
        PdfNameeditText5=(EditText)findViewById(R.id.conveyanceeditText6);
        Pdfspinner=(Spinner)findViewById(R.id.conveyanceSpinner1);
        PdfSpinner=(Spinner)findViewById(R.id.conveyanceSpinner2);
        PdfTextview=(TextView)findViewById(R.id.conveyancetextview);
        monthspinner1=(Spinner)findViewById(R.id.conveyancemonth1);
        yesrspinner1=(Spinner)findViewById(R.id.conveyanceyear1);
        Pdfdate1=(ImageView)findViewById(R.id.date_re);
        Pdfdate2=(ImageView)findViewById(R.id.date1_re);
        mobilestatus = (TextView)findViewById(R.id.mobilestatus);
        conveyancestatus = (TextView)findViewById(R.id.conveyancestatus);

        mobilestatus.setText("update");
        conveyancestatus.setText("update");
        generalstatus.setText("update");

        for (int image: images)
        {
            flipperImages(image);
        }

        iv13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,EmployeeDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv131.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,HrDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        iv132.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,AdminDashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        mypay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpReimbursement.this,MyPay.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        PdfNameeditText5.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (PdfNameeditText4.getText().toString().trim().length()== 0)
                {
                    Toast.makeText(EmpReimbursement.this, "Enter Wages First", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (PdfNameeditText4.getText().toString().trim().length() != 0 && PdfNameeditText5.getText().toString().trim().length() > 0) {
                    float a = Float.parseFloat(PdfNameeditText4.getText().toString().trim());
                    float b = Float.parseFloat(PdfNameeditText5.getText().toString().trim());
                    float c = a * b;
                    PdfTextview.setText("" + c);

                }
            }
        });
        Pdfdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EmpReimbursement.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                PdfNameeditText.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        Pdfdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EmpReimbursement.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                PdfNameeditText1.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Pdfdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EmpReimbursement.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                PdfNameEditText3.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        //
        dateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                final int mYear = c.get(Calendar.YEAR); // current year
                final int mMonth = c.get(Calendar.MONTH); // current month
                final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(EmpReimbursement.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                PdfNameEdittext1.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        lq.setVisibility(Text2.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("employee"))
        {
            text.setText("Employee Dashboard - My pay - Reimbursement" +"("+empid+")");
        }
        else if(Text2.equals("hr"))
        {
            text.setText("HR Dashboard - My pay - Reimbursement" +"("+empid+")");
        }
        else  if(Text2.equals("admin"))
        {
            text.setText("Admin Dashboard - My pay - Reimbursement" +"("+empid+")");
        }

        PdfSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.GONE);
                        break;
                    case 1:
                        // set editbox invivible
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.GONE);
                        v_flipper.setVisibility(View.GONE);
                        break;
                    case 2:
                        // set editbox invivible
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        linearLayout3.setVisibility(View.GONE);
                        v_flipper.setVisibility(View.GONE);
                        break;
                    case 3:
                        // set editbox invivible
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.GONE);
                        linearLayout3.setVisibility(View.VISIBLE);
                        v_flipper.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // set editbox invivible

            }
        });

        AllowRunTimePermission();

        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PDF selection code start from here .

                Intent intent = new Intent();

                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });

        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfUploadFunction();

            }
        });


        SelectButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PDF selection code start from here .

                Intent intent = new Intent();

                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });

        UploadButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfUploadFunction1();

            }
        });

        SelectButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // PDF selection code start from here .

                Intent intent = new Intent();

                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

            }
        });

        UploadButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PdfUploadFunction2();

            }
        });

        /*lq.setVisibility(Text2.equals("employee")? View.VISIBLE : View.GONE);
        lq1.setVisibility(Text2.equals("hr")? View.VISIBLE : View.GONE);
        lq2.setVisibility(Text2.equals("admin")? View.VISIBLE : View.GONE);

        if(Text2.equals("employee"))
        {
            text.setText("Employee Dashboard - My pay - Reimbursement" +"("+empid+")");
        }
        else if(Text2.equals("hr"))
        {
            text.setText("HR Dashboard - My pay - Reimbursement" +"("+empid+")");
        }
        else  if(Text2.equals("admin"))
        {
            text.setText("Admin Dashboard - My pay - Reimbursement" +"("+empid+")");
        }*/

    }

    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            SelectButton.setText("PDF is Selected");
        }
    }

    public void PdfUploadFunction() {

        empid=empId.getText().toString().trim();
        PdfNameHolder = PdfNameEditText.getText().toString().trim();
        PdfNameHolder1 = PdfNameEditText1.getText().toString().trim();
        PdfNameHolder2 = PdfNameEditText2.getText().toString().trim();
        PdfNameHolder3 = PdfNameEditText3.getText().toString().trim();
        PdfNameHolder4 = PdfNameEditText4.getText().toString().trim();
        PdfSpinnerHolder1 = PdfSpinner1.getSelectedItem().toString().trim();
        PdfSpinnerHolder2 = PdfSpinner2.getSelectedItem().toString().trim();
        PdfMonthSpinner2=monthspinner2.getSelectedItem().toString().trim();
        PdfYearSpinner2=yesrspinner2.getSelectedItem().toString().trim();
        MobileStatus = mobilestatus.getText().toString();

        if (TextUtils.isEmpty(PdfNameHolder)) {
            PdfNameEditText.setError("Please enter Mobile Number");
            PdfNameEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(PdfNameHolder1)) {
            PdfNameEditText1.setError("Please enter Operator Name");
            PdfNameEditText1.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(PdfNameHolder2)) {
            PdfNameEditText2.setError("Please enter Bill Number");
            PdfNameEditText2.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(PdfNameHolder3)) {
            PdfNameEditText3.setError("Please enter Bill Date");
            PdfNameEditText3.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(PdfNameHolder4)) {
            PdfNameEditText4.setError("Please enter Claimed Amount");
            PdfNameEditText4.requestFocus();
            return;
        }



        PdfPathHolder = FilePath.getPath(this, uri);

        if (PdfPathHolder == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        } else {

            try {

                PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID, PDF_UPLOAD_HTTP_URL)
                        .addFileToUpload(PdfPathHolder, "pdf")
                        .addParameter("name", PdfNameHolder)
                        .addParameter("mobile", PdfNameHolder1)
                        .addParameter("operator", PdfNameHolder2)
                        .addParameter("billno", PdfNameHolder3)
                        .addParameter("billeddate", PdfSpinnerHolder1)
                        .addParameter("category", PdfSpinnerHolder2)
                        .addParameter("paymentmode", PdfNameHolder4)
                        .addParameter("empid", empid)
                        .addParameter("month", PdfMonthSpinner2)
                        .addParameter("year", PdfYearSpinner2)
                        .addParameter("status", MobileStatus)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(10)
                        .startUpload();
                Intent i=new Intent(EmpReimbursement.this,EmpReimbursement.class);
                startActivity(i);

            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void PdfUploadFunction1() {

        empid=empId.getText().toString().trim();
        PdfNameholder = PdfNameEdittext.getText().toString().trim();
        PdfNameholder1 = PdfNameEdittext1.getText().toString().trim();
        PdfNameholder2 = PdfNameEdittext2.getText().toString().trim();
        PdfSpinnerHolder1 = PdfSpinner1.getSelectedItem().toString().trim();
        PdfSpinnerholder1 = Pdfspinner1.getSelectedItem().toString().trim();
        PdfSpinnerholder2 = Pdfspinner2.getSelectedItem().toString().trim();
        PdfMonthSpinner3=monthspinner3.getSelectedItem().toString().trim();
        PdfYearSpinner3=yesrspinner3.getSelectedItem().toString().trim();
        GeneralStatus = generalstatus.getText().toString();

        PdfPathHolder1 = FilePath.getPath(this, uri);

        if (PdfPathHolder1 == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        } else {

            try {

                PdfID1 = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID1, PDF_UPLOAD_HTTP_URL1)
                        .addFileToUpload(PdfPathHolder1, "pdf")
                        .addParameter("name", PdfSpinnerholder1)
                        .addParameter("select", PdfNameholder)
                        .addParameter("billno", PdfNameholder1)
                        .addParameter("billeddate", PdfSpinnerHolder1)
                        .addParameter("category", PdfSpinnerholder2)
                        .addParameter("paymentmode", PdfNameholder2)
                        .addParameter("empid", empid)
                        .addParameter("month", PdfMonthSpinner3)
                        .addParameter("year", PdfYearSpinner3)
                        .addParameter("status", GeneralStatus)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(10)
                        .startUpload();
                Intent i=new Intent(EmpReimbursement.this,EmpReimbursement.class);
                startActivity(i);

            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void PdfUploadFunction2() {

        empid=empId.getText().toString().trim();
        PdfnameHolder = PdfNameeditText.getText().toString().trim();
        PdfnameHolder1 = PdfNameeditText1.getText().toString().trim();
        PdfnameHolder2 = PdfNameeditText2.getText().toString().trim();
        PdfnameHolder3 = PdfNameeditText3.getText().toString().trim();
        PdfnameHolder4 = PdfNameeditText4.getText().toString().trim();
        PdfnameHolder5 = PdfNameeditText5.getText().toString().trim();
        PdfSpinnerHolder1 = PdfSpinner1.getSelectedItem().toString().trim();
        Pdfspinnerholder1 = Pdfspinner.getSelectedItem().toString().trim();
        PdfSpinnerhHolder1 = PdfSpinner.getSelectedItem().toString().trim();
        PdfTextHolder1 = PdfTextview.getText().toString().trim();
        PdfMonthSpinner1=monthspinner1.getSelectedItem().toString().trim();
        PdfYearSpinner1=yesrspinner1.getSelectedItem().toString().trim();
        ConveyanceStatus = conveyancestatus.getText().toString();

        PdfPathHolder2 = FilePath.getPath(this, uri);

        if (PdfPathHolder2 == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        } else {

            try {

                PdfID2 = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID1, PDF_UPLOAD_HTTP_URL2)
                        .addFileToUpload(PdfPathHolder2, "pdf")
                        .addParameter("name", Pdfspinnerholder1)
                        .addParameter("startdate", PdfnameHolder)
                        .addParameter("enddate", PdfnameHolder1)
                        .addParameter("source", PdfnameHolder2)
                        .addParameter("destination", PdfnameHolder3)
                        .addParameter("totalkms", PdfnameHolder4)
                        .addParameter("rsperkm", PdfnameHolder5)
                        .addParameter("paymentmode", PdfSpinnerhHolder1)
                        .addParameter("claimedamount", PdfTextHolder1)
                        .addParameter("category", PdfSpinnerHolder1)
                        .addParameter("empid", empid)
                        .addParameter("month", PdfMonthSpinner1)
                        .addParameter("year", PdfYearSpinner1)
                        .addParameter("status", ConveyanceStatus)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(10)
                        .startUpload();
                Intent i=new Intent(EmpReimbursement.this,EmpReimbursement.class);
                startActivity(i);

            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void AllowRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(EmpReimbursement.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(EmpReimbursement.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(EmpReimbursement.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(EmpReimbursement.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(EmpReimbursement.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
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
           /* Intent intent=new Intent(EditProfile.this,QueryForm.class);
            startActivity(intent);*/
            Text=text.getText().toString();
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

                Intent i = new Intent(EmpReimbursement.this, QueryForm.class);
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

                Toast.makeText(EmpReimbursement.this,"Invalid Details", Toast.LENGTH_LONG).show();
            }
        }
    }

}