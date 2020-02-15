package com.example.anushak.altaoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anushak on 12-02-2019.
 */

public class PayrollAdapter extends ArrayAdapter<payroll> {

    public ArrayList<payroll> MainList;

    public ArrayList<payroll> PayListTemp;

    public PayrollAdapter.PayDataFilter payDataFilter ;

    public PayrollAdapter(Context context, int id, ArrayList<payroll> payArrayList) {

        super(context, id, payArrayList);

        this.PayListTemp = new ArrayList<payroll>();

        this.PayListTemp.addAll(payArrayList);

        this.MainList = new ArrayList<payroll>();

        this.MainList.addAll(payArrayList);
    }

    @Override
    public Filter getFilter() {

        if (payDataFilter == null){

            payDataFilter  = new PayrollAdapter.PayDataFilter();
        }
        return payDataFilter;
    }


    public class ViewHolder {

        TextView Name;
        TextView Empid;
        TextView Month;
        TextView Year;
        TextView Date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PayrollAdapter.ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.cardview, null);

            holder = new PayrollAdapter.ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.name);
            holder.Empid = (TextView) convertView.findViewById(R.id.empid);
            holder.Month = (TextView) convertView.findViewById(R.id.month);
            holder.Year = (TextView) convertView.findViewById(R.id.year);
            holder.Date = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);

        } else {
            holder = (PayrollAdapter.ViewHolder) convertView.getTag();
        }
        if (position % 2 == 1) {
            //convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
            convertView.setBackgroundResource(R.color.yellow);

        } else {
            //convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
            convertView.setBackgroundResource(R.color.Blue);
        }
        /* if (position % 2 == 1) {
            //convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
            convertView.setBackgroundResource(R.color.endblue);

        } else {
            //convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
            convertView.setBackgroundResource(R.color.endblue);
        }*/


        payroll payroll1 = PayListTemp.get(position);

        holder.Name.setText(payroll1.getName());

        holder.Empid.setText(payroll1.getEmpid());

        holder.Month.setText(payroll1.getMonth());

        holder.Year.setText(payroll1.getYear());

        holder.Date.setText(payroll1.getDate());

        return convertView;

    }

    private class PayDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<payroll> arrayList1 = new ArrayList<payroll>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    payroll payroll1 = MainList.get(i);

                    if(payroll1.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(payroll1);
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

            PayListTemp = (ArrayList<payroll>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = PayListTemp.size(); i < l; i++)
                add(PayListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}
