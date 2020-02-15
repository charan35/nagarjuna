package com.example.anushak.altaoss;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ContactUs extends AppCompatActivity {

    ImageView fb,insta,twitter,pinterest,tumblr,youtube,linkedin,google;
    TextView location, contact, mobile1, email1,website,name, email, mobile, comment;
    String Name, Email, Mobile, Comment;
    Button submit;
    Context ctx=this;
    android.support.v7.widget.Toolbar toolbar;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        comment = (EditText) findViewById(R.id.comment);
        submit = (Button) findViewById(R.id.submit);

        location = (TextView) findViewById(R.id.location);
        contact = (TextView) findViewById(R.id.contact);
        mobile1 = (TextView) findViewById(R.id.mobile1);
        email1 = (TextView) findViewById(R.id.email1);
        website = (TextView) findViewById(R.id.website);

        fb = (ImageView) findViewById(R.id.fb);
        insta = (ImageView) findViewById(R.id.insta);
        twitter = (ImageView) findViewById(R.id.twitter);
        pinterest = (ImageView) findViewById(R.id.pin);
        tumblr = (ImageView) findViewById(R.id.tumblr);
        youtube = (ImageView) findViewById(R.id.youtube);
        linkedin = (ImageView) findViewById(R.id.linkedin);
        google = (ImageView) findViewById(R.id.googleplus);
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

                BackGroundcontact b = new BackGroundcontact();
                b.execute(Name, Email, Mobile, Comment);


                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("message/rfc822");
                        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"infoalta@altaitsolutions.com"});
                        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                        //i.putExtra(Intent.EXTRA_TEXT   , Mobile);
                        i.putExtra(Intent.EXTRA_TEXT   , Comment);
                        try {
                            startActivity(Intent.createChooser(i, "Send mail..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ContactUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }

            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:12.803915,77.514190"));
                startActivity(intent);

            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:080-28432432"));
                startActivity(intent);
            }
        });

        mobile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7337774647"));
                startActivity(intent);
            }
        });

        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"infoalta@altaitsolutions.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.altaitsolutions.com"));
                startActivity(intent);
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/altaitsolutions"));
                startActivity(intent);
            }
        });


        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.instagram.com/altaitsolutions"));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/alta_it"));
                startActivity(intent);
            }
        });

        pinterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://in.pinterest.com/altaitsolutions"));
                startActivity(intent);
            }
        });


        tumblr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://goaltait.tumblr.com/"));
                startActivity(intent);
            }
        });


        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCG-9-2kBC3GCwIXlNrFSSsQ?view_as=subscriber"));
                startActivity(intent);
            }
        });


        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.linkedin.com/company/alta-it-solutions/"));
                startActivity(intent);
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://plus.google.com/u/0/112220474128522817130"));
                startActivity(intent);
            }
        });
    }

    class BackGroundcontact extends AsyncTask<String, String, String> {

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