package com.example.anushak.altaoss;

/**
 * Created by Anushak on 21-03-2019.
 */

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DownloadAdapter extends ArrayAdapter<Downloads> {

    public ArrayList<Downloads> MainList;

    public ArrayList<Downloads> DownloadsListTemp;

    public DownloadAdapter.DownloadDataFilter downloadDataFilter ;

    public DownloadAdapter(Context context, int id, ArrayList<Downloads> downloadArrayList) {

        super(context, id, downloadArrayList);

        this.DownloadsListTemp = new ArrayList<Downloads>();

        this.DownloadsListTemp.addAll(downloadArrayList);

        this.MainList = new ArrayList<Downloads>();

        this.MainList.addAll(downloadArrayList);
    }

    @Override
    public Filter getFilter() {

        if (downloadDataFilter == null){

            downloadDataFilter  = new DownloadAdapter.DownloadDataFilter();
        }
        return downloadDataFilter;
    }

    public class ViewHolder {

        TextView Empid;
        TextView Url;
        TextView Type;
        Button View;
        Button Delete;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        DownloadAdapter.ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.downloads_layout, null);

            holder = new DownloadAdapter.ViewHolder();

            holder.Empid = (TextView) convertView.findViewById(R.id.empid);
            holder.Url = (TextView) convertView.findViewById(R.id.url);
            holder.Type = (TextView) convertView.findViewById(R.id.type);
            holder.View = (Button) convertView.findViewById(R.id.view);
            holder.Delete = (Button) convertView.findViewById(R.id.delete);

            convertView.setTag(holder);

        } else {
            holder = (DownloadAdapter.ViewHolder) convertView.getTag();
        }
        /* if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }*/
        final Downloads downloads = DownloadsListTemp.get(position);

        holder.Empid.setText(downloads.getEmpid());

        holder.Url.setText(downloads.getUrl());

        holder.Type.setText(downloads.getType());

        holder.View.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(downloads.getUrl()));
                v.getContext().startActivity(intent);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new AlertDialog.Builder(getContext())
                        .setTitle(downloads.getType())
                        .setMessage("Are you sure you want to delete?")
                        .setNegativeButton(R.string.no, null)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg1, int arg0) {
                                Update b = new Update();
                                b.execute(downloads.getEmpid(),downloads.getUrl(),downloads.getType());
                            }
                        }).create().show();
            }
        });
        return convertView;
    }

    class Update extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String empid = (String) arg0[0];
                String durl = (String) arg0[1];
                String type = (String) arg0[2];

                String link = "http://192.168.1.55/AltaCRM/delete_downloads.php";

                String data = URLEncoder.encode("empid", "UTF-8") + "=" + URLEncoder.encode(empid, "UTF-8");
                data += "&" + URLEncoder.encode("url", "UTF-8") + "=" + URLEncoder.encode(durl, "UTF-8");
                data += "&" + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");

                URL url;
                url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {
                return new String("Expection: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getContext(),"Data has been deleted", Toast.LENGTH_LONG).show();
        }
    }

    private class DownloadDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<Downloads> arrayList1 = new ArrayList<Downloads>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    Downloads downloads = MainList.get(i);

                    if(downloads.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(downloads);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            }
            else
            {
                synchronized(this)
                {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            DownloadsListTemp = (ArrayList<Downloads>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = DownloadsListTemp.size(); i < l; i++)
                add(DownloadsListTemp.get(i));
            notifyDataSetInvalidated();
        }
    }

}