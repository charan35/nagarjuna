package com.example.anushak.altaoss;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DateWiseActivity extends AppCompatActivity {
    TextView Datesearch;
    ListView listView;
    ViewvisitorAdapter viewvisitorAdapter;
    ProgressBar progressBar;
    ArrayList<Viewvisitor> VisitorsList = new ArrayList<Viewvisitor>();
    String HttpURL = "http://192.168.1.59/Androiduploadimage/visitor_list.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wise);

        listView = findViewById(R.id.visdatelist);
        Datesearch = findViewById(R.id.datetext);
        progressBar = findViewById(R.id.visprogress);

        new ParseJSonDataClass(DateWiseActivity.this).execute();
    }

    private class ParseJSonDataClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpURL);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);

                            JSONObject jsonObject;

                            Viewvisitor viewvisitor;

                            VisitorsList = new ArrayList<Viewvisitor>();

                            for (int i = 0; i < jsonArray.length(); i++) {

                                jsonObject = jsonArray.getJSONObject(i);

                                String tempName = jsonObject.getString("fname").toString();
                                String tempFullForm = jsonObject.getString("lname").toString();
                                String tempFullForm1 = jsonObject.getString("reasonforvisit").toString();
                                String tempName2 = jsonObject.getString("companyname").toString();
                                String tempFullForm3 = jsonObject.getString("contactperson").toString();
                                String tempFullForm14 = jsonObject.getString("dateofvisit").toString();

                                viewvisitor = new Viewvisitor(tempName, tempFullForm, tempFullForm1, tempName2, tempFullForm3, tempFullForm14);

                                VisitorsList.add(viewvisitor);
                            }
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {
                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressBar.setVisibility(View.INVISIBLE);
            viewvisitorAdapter = new ViewvisitorAdapter(DateWiseActivity.this, R.layout.visitorlayout, VisitorsList);
            listView.setAdapter(viewvisitorAdapter);
            Toast.makeText(context, "Total Number of Visitors are:" + listView.getAdapter().getCount(), Toast.LENGTH_SHORT).show();
        }
    }

}
