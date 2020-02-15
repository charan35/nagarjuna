package com.example.anushak.altaoss;

/**
 * Created by Anushak on 21-03-2019.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class QueryAdapter extends ArrayAdapter<Query> {

    public ArrayList<Query> MainList;

    public ArrayList<Query> QueryListTemp;

    public QueryAdapter.QueryDataFilter queryDataFilter ;

    public QueryAdapter(Context context, int id, ArrayList<Query> queryArrayList) {

        super(context, id, queryArrayList);

        this.QueryListTemp = new ArrayList<Query>();

        this.QueryListTemp.addAll(queryArrayList);

        this.MainList = new ArrayList<Query>();

        this.MainList.addAll(queryArrayList);
    }

    @Override
    public Filter getFilter() {

        if (queryDataFilter == null){

            queryDataFilter  = new QueryAdapter.QueryDataFilter();
        }
        return queryDataFilter;
    }


    public class ViewHolder {

        TextView Name;
        TextView QueryName;
        TextView Email;
        TextView Update;
        TextView Status;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        QueryAdapter.ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.query_layout, null);

            holder = new QueryAdapter.ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.empid);
            holder.QueryName = (TextView) convertView.findViewById(R.id.query);
            holder.Email = (TextView) convertView.findViewById(R.id.email);
            holder.Update = (TextView) convertView.findViewById(R.id.queryupdate);
            holder.Status = (TextView) convertView.findViewById(R.id.status);

            convertView.setTag(holder);

        } else {
            holder = (QueryAdapter.ViewHolder) convertView.getTag();
        }
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }

        Query query = QueryListTemp.get(position);

        holder.Name.setText(query.getName());

        holder.QueryName.setText(query.getQueryName());

        holder.Email.setText(query.getEmail());

        holder.Update.setText(query.getUpdate());

        holder.Status.setText(query.getStatus());

        return convertView;
    }

    private class QueryDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<Query> arrayList1 = new ArrayList<Query>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    Query query = MainList.get(i);

                    if(query.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(query);
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

            QueryListTemp = (ArrayList<Query>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = QueryListTemp.size(); i < l; i++)
                add(QueryListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }

}