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

public class QueryStatusAdapter extends ArrayAdapter<QueryState> {

    public ArrayList<QueryState> MainList;

    public ArrayList<QueryState> QueryListTemp;

    public QueryStatusAdapter.QueryDataFilter queryDataFilter ;

    public QueryStatusAdapter(Context context, int id, ArrayList<QueryState> queryArrayList) {

        super(context, id, queryArrayList);

        this.QueryListTemp = new ArrayList<QueryState>();

        this.QueryListTemp.addAll(queryArrayList);

        this.MainList = new ArrayList<QueryState>();

        this.MainList.addAll(queryArrayList);
    }

    @Override
    public Filter getFilter() {

        if (queryDataFilter == null){

            queryDataFilter  = new QueryStatusAdapter.QueryDataFilter();
        }
        return queryDataFilter;
    }


    public class ViewHolder {

        TextView Name;
        TextView QueryName;
        TextView Email;
        TextView Update;
        TextView Task;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        QueryStatusAdapter.ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.query_status, null);

            holder = new QueryStatusAdapter.ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.empid);
            holder.QueryName = (TextView) convertView.findViewById(R.id.query);
            holder.Email = (TextView) convertView.findViewById(R.id.email);
            holder.Update = (TextView) convertView.findViewById(R.id.queryupdate);
            holder.Task = (TextView) convertView.findViewById(R.id.status);

            convertView.setTag(holder);

        } else {
            holder = (QueryStatusAdapter.ViewHolder) convertView.getTag();
        }
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }

        QueryState queryState = QueryListTemp.get(position);

        holder.Name.setText(queryState.getName());

        holder.QueryName.setText(queryState.getQueryName());

        holder.Email.setText(queryState.getEmail());

        holder.Update.setText(queryState.getUpdate());

        holder.Task.setText(queryState.getTask());

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
                ArrayList<QueryState> arrayList1 = new ArrayList<QueryState>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    QueryState queryState = MainList.get(i);

                    if(queryState.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(queryState);
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

            QueryListTemp = (ArrayList<QueryState>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = QueryListTemp.size(); i < l; i++)
                add(QueryListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }

}