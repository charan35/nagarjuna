package com.example.anushak.altaoss;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.android.gms.vision.face.FaceDetector;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FaceLogin extends AppCompatActivity {

    private static final String LOG_TAG = "FACE API";
    private static final int PHOTO_REQUEST = 10;
    private TextView scanResults;
    private ImageView imageView;
    private Uri imageUri;
    Button button,login;
    private FaceDetector detector;
    private static final int REQUEST_WRITE_PERMISSION = 20;
    private static final String SAVED_INSTANCE_URI = "uri";
    private static final String SAVED_INSTANCE_BITMAP = "bitmap";
    private static final String SAVED_INSTANCE_RESULT = "result";
    Bitmap editedBitmap;

    TextView DeviceID,lefteye,righteye,smile,id,firstname,email;
    TextView empid,department;
    String unique_id,Smile,LeftEye,RightEye,Empid,Id,Email,Firstname,Department,FIRSTNAME,EMAIL,DEPARTMENT;
    String PROFILE,PROJECT,MESSAGE,MYPAY,ATTENDANCE,UPDATES,MYPROJECT,CAREERS,REWARDS,INTERVIEWS;
    String NEWEMPFORM,EMPDIRECTORY11,PROJECTDETAILS,EMPATTENDANCE11,REIMBURSEMENT,DOCUMENTSUPLOAD,PAYSLIPS1,CCCAMERA,VISITOR;
    String PAYROLL,DOCUMENTSFETCH,PERMISSION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_login);
        button = (Button) findViewById(R.id.button);
        login = (Button) findViewById(R.id.login);
        scanResults = (TextView) findViewById(R.id.results);
        imageView = (ImageView) findViewById(R.id.scannedResults);
        lefteye = (TextView)findViewById(R.id.lefteye);
        righteye = (TextView)findViewById(R.id.righteye);
        smile = (TextView)findViewById(R.id.smile);
        empid = (TextView) findViewById(R.id.empid);
        firstname = (TextView)findViewById(R.id.firstname);
        email = (TextView) findViewById(R.id.email);
        department = (TextView) findViewById(R.id.department);
        id = (TextView) findViewById(R.id.id);

        Id = getIntent().getStringExtra("employee");
        id.setText(Id);

        Empid = getIntent().getStringExtra("Empid");
        empid.setText(Empid);

        Firstname = getIntent().getStringExtra("firstname");
        firstname.setText(Firstname);

        Email = getIntent().getStringExtra("officialemail");
        email.setText(Email);

        Department = getIntent().getStringExtra("department");
        department.setText(Department);

        DeviceID = (TextView)findViewById(R.id.textView1);
        unique_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        DeviceID.setText(unique_id);

        if (savedInstanceState != null) {
            editedBitmap = savedInstanceState.getParcelable(SAVED_INSTANCE_BITMAP);
            if (savedInstanceState.getString(SAVED_INSTANCE_URI) != null) {
                imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
            }
            imageView.setImageBitmap(editedBitmap);
            scanResults.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
        }
        detector = new FaceDetector.Builder(getApplicationContext())
                .setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(FaceLogin.this, new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
                button.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Smile = smile.getText().toString();
                LeftEye = lefteye.getText().toString();
                RightEye = righteye.getText().toString();
                Empid = empid.getText().toString();

                if(Id == "Employee") {
                    BackGroundemp b = new BackGroundemp();
                    b.execute(Smile, LeftEye, RightEye, Empid);
                }
                else  if(Id == "Hr") {
                    BackGroundhr b = new BackGroundhr();
                    b.execute(Smile, LeftEye, RightEye, Empid);
                }
                else if(Id == "Admin") {
                    BackGroundadmin b = new BackGroundadmin();
                    b.execute(Smile, LeftEye, RightEye, Empid);
                }
            }
        });
    }


    class BackGroundemp extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String smile = params[0];
            String lefteye = params[1];
            String righteye = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.56/Sample/employeelogin.php");
                String urlParams = "Smile=" + smile + "&LeftEye=" + lefteye + "&RightEye=" + righteye + "&Empid=" + empid;

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
                Empid = user_data.getString("Empid");


                Intent i= new Intent(FaceLogin.this,EmployeeDashboard.class);
                i.putExtra("Empid", Empid);

                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
                i.putExtra("department",DEPARTMENT);

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

                Toast.makeText(FaceLogin.this,"Invalid username and password", Toast.LENGTH_SHORT).show();

            }
        }
    }


    class BackGroundhr extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String smile = params[0];
            String lefteye = params[1];
            String righteye = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.56/Sample/hrlogin.php");
                String urlParams = "Smile=" + smile + "&LeftEye=" + lefteye + "&RightEye=" + righteye + "&Empid=" + empid;

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
                Empid = user_data.getString("Empid");


                Intent i= new Intent(FaceLogin.this,HrDashboard.class);
                i.putExtra("Empid", Empid);

                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
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
                i.putExtra("payslips1", PAYSLIPS1);
                i.putExtra("rewards", REWARDS);
                i.putExtra("careers", CAREERS);
                i.putExtra("interviews", INTERVIEWS);
                startActivity(i);

            } catch (JSONException e) {
                e.printStackTrace();

                Toast.makeText(FaceLogin.this,"Invalid username and password", Toast.LENGTH_SHORT).show();
            }
        }
    }


    class BackGroundadmin extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String smile = params[0];
            String lefteye = params[1];
            String righteye = params[2];
            String empid = params[3];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.1.56/Sample/adminlogin.php");
                String urlParams = "Smile=" + smile + "&LeftEye=" + lefteye + "&RightEye=" + righteye + "&Empid=" + empid;

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
                Empid = user_data.getString("Empid");


                Intent i= new Intent(FaceLogin.this,AdminDashboard.class);
                i.putExtra("Empid", Empid);

                i.putExtra("firstname", FIRSTNAME);
                i.putExtra("officialemail", EMAIL);
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

                Toast.makeText(FaceLogin.this,"Invalid username and password", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(FaceLogin.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST && resultCode == RESULT_OK) {
            launchMediaScanIntent();
            try {
                scanFaces();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load Image", Toast.LENGTH_SHORT).show();
                Log.e(LOG_TAG, e.toString());
            }
        }
    }

    private void scanFaces() throws Exception {
        Bitmap bitmap = decodeBitmapUri(this, imageUri);
        if (detector.isOperational() && bitmap != null) {
            editedBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                    .getHeight(), bitmap.getConfig());
            float scale = getResources().getDisplayMetrics().density;
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.rgb(255, 61, 61));
            paint.setTextSize((int) (14 * scale));
            paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3f);
            Canvas canvas = new Canvas(editedBitmap);
            canvas.drawBitmap(bitmap, 0, 0, paint);
            Frame frame = new Frame.Builder().setBitmap(editedBitmap).build();
            SparseArray<Face> faces = detector.detect(frame);
            scanResults.setText(null);
            for (int index = 0; index < faces.size(); ++index) {
                Face face = faces.valueAt(index);
                canvas.drawRect(
                        face.getPosition().x,
                        face.getPosition().y,
                        face.getPosition().x + face.getWidth(),
                        face.getPosition().y + face.getHeight(), paint);
                scanResults.setText(scanResults.getText() + "Face " + (index + 1) + "\n");
                scanResults.setText(scanResults.getText() + "Smile probability:" + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsSmilingProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "Left Eye Open Probability: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsLeftEyeOpenProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "Right Eye Open Probability: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(face.getIsRightEyeOpenProbability()) + "\n");
                scanResults.setText(scanResults.getText() + "---------" + "\n");

                smile.setText(face.getIsSmilingProbability() + "\n");
                lefteye.setText(face.getIsLeftEyeOpenProbability() + "\n");
                righteye.setText(face.getIsRightEyeOpenProbability() + "\n");

                for (Landmark landmark : face.getLandmarks()) {
                    int cx = (int) (landmark.getPosition().x);
                    int cy = (int) (landmark.getPosition().y);
                    canvas.drawCircle(cx, cy, 5, paint);
                }
            }

            if (faces.size() == 0) {
                scanResults.setText("Scan Failed: Found nothing to scan");
            } else {
                imageView.setImageBitmap(editedBitmap);
                scanResults.setText(scanResults.getText() + "No of Faces Detected: " + "\n");
                scanResults.setText(scanResults.getText() + String.valueOf(faces.size()) + "\n");
                scanResults.setText(scanResults.getText() + "---------" + "\n");
            }
        } else {
            scanResults.setText("Could not set up the detector!");
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(), "picture.jpg");
        imageUri = FileProvider.getUriForFile(FaceLogin.this,
                BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (imageUri != null) {
            outState.putParcelable(SAVED_INSTANCE_BITMAP, editedBitmap);
            outState.putString(SAVED_INSTANCE_URI, imageUri.toString());
            outState.putString(SAVED_INSTANCE_RESULT, scanResults.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }



    private void launchMediaScanIntent() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(imageUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private Bitmap decodeBitmapUri(Context ctx, Uri uri) throws FileNotFoundException {
        int targetW = 600;
        int targetH = 600;
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ctx.getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeStream(ctx.getContentResolver()
                .openInputStream(uri), null, bmOptions);
    }
}
