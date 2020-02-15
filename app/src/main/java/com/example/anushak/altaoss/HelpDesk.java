package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HelpDesk extends AppCompatActivity {

    EditText name, email, mobile, comment;
    String Name, Email, Mobile, Comment;
    Button submit;
    Context ctx=this;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_help_desk);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        comment = (EditText) findViewById(R.id.comment);
        submit = (Button) findViewById(R.id.submit);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (ImageView)findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = name.getText().toString();
                Email = email.getText().toString();
                Mobile = mobile.getText().toString();
                Comment = comment.getText().toString();


                if (TextUtils.isEmpty(Name)) {
                    name.setError("Please enter Name");
                    name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Please enter Email");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Mobile)) {
                    mobile.setError("Please enter Mobile");
                    mobile.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Comment)) {
                    comment.setError("Please enter Comment");
                    comment.requestFocus();
                    return;
                }

                BackGround b = new BackGround();
                b.execute(Name, Email, Mobile, Comment);

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"infoalta@altaitsolutions.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , Comment);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HelpDesk.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String email = params[1];
            String mobile = params[2];
            String comment = params[3];

            String data = "";
            int tmp;

            try {
                URL url = new URL("http://altaitsolutions.com/Altadatabase/contactus.php");
                String urlParams = "name="+name+"&email="+email+"&mobile="+mobile+"&comment="+comment;

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
}

