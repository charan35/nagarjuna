package com.example.anushak.altaoss;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class Ticketing_Dept_Adapter extends ArrayAdapter<Tickets_Dept_Names> {

    public ArrayList<Tickets_Dept_Names> MainList;

    public ArrayList<Tickets_Dept_Names> dept_ListTemp;

    public SubjectDataFilter subjectDataFilter ;

    public Ticketing_Dept_Adapter(Context context, int id, ArrayList<Tickets_Dept_Names> ticketing_dept_ArrayList) {

        super(context, id, ticketing_dept_ArrayList);

        this.dept_ListTemp = new ArrayList<Tickets_Dept_Names>();

        this.dept_ListTemp.addAll(ticketing_dept_ArrayList);

        this.MainList = new ArrayList<Tickets_Dept_Names>();

        this.MainList.addAll(ticketing_dept_ArrayList);
    }

    @Override
    public Filter getFilter() {

        if (subjectDataFilter == null){

            subjectDataFilter  = new SubjectDataFilter();
        }
        return subjectDataFilter;
    }


    public class ViewHolder {

        TextView SubjectName;
        TextView SubjectFullForm;
        TextView subjestId;
        TextView subjectDepartment;
        TextView subjectEmail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.ticketing_dept_view, null); //layout view

            holder = new ViewHolder();

            holder.SubjectName = (TextView) convertView.findViewById(R.id.textviewName);
            holder.SubjectFullForm = (TextView) convertView.findViewById(R.id.textviewFullForm);
            holder.subjestId = (TextView) convertView.findViewById(R.id.textviewId);
            holder.subjectDepartment = (TextView) convertView.findViewById(R.id.department);
            holder.subjectEmail = (TextView) convertView.findViewById(R.id.email);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }


        Tickets_Dept_Names details = dept_ListTemp.get(position);

        holder.SubjectName.setText(details.getSubName());

        holder.SubjectFullForm.setText(details.getSubFullForm());

        holder.subjestId.setText(details.getSubId());

        holder.subjectDepartment.setText(details.getSubDepartment());

        holder.subjectEmail.setText(details.getEmail());

        return convertView;

    }

    private class SubjectDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<Tickets_Dept_Names> arrayList1 = new ArrayList<Tickets_Dept_Names>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    Tickets_Dept_Names subject = MainList.get(i);

                    if(subject.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(subject);
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

            dept_ListTemp = (ArrayList<Tickets_Dept_Names>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = dept_ListTemp.size(); i < l; i++)
                add(dept_ListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}