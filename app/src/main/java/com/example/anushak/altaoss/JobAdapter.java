package com.example.anushak.altaoss;

/**
 * Created by Anushak on 23-01-2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class JobAdapter extends ArrayAdapter<JobData> {

    public ArrayList<JobData> MainList;

    public ArrayList<JobData> JobListTemp;

    public JobAdapter.JobDataFilter jobDataFilter ;

    public JobAdapter(Context context, int id, ArrayList<JobData> jobArrayList) {

        super(context, id, jobArrayList);

        this.JobListTemp = new ArrayList<JobData>();

        this.JobListTemp.addAll(jobArrayList);

        this.MainList = new ArrayList<JobData>();

        this.MainList.addAll(jobArrayList);
    }

    @Override
    public Filter getFilter() {

        if (jobDataFilter == null){

            jobDataFilter  = new JobAdapter.JobDataFilter();
        }
        return jobDataFilter;
    }


    public class ViewHolder {

        TextView JobTitle;
        TextView JobVacancies;
        TextView JobExperience;
        TextView JobLocation;
        TextView JobSalary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        JobAdapter.ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.job_layout, null);

            holder = new JobAdapter.ViewHolder();

            holder.JobTitle = (TextView) convertView.findViewById(R.id.jobtitle);
            holder.JobVacancies = (TextView) convertView.findViewById(R.id.noofvacancies);
            holder.JobExperience = (TextView) convertView.findViewById(R.id.experience);
            holder.JobLocation = (TextView) convertView.findViewById(R.id.joblocation);
            holder.JobSalary = (TextView) convertView.findViewById(R.id.salary);

            convertView.setTag(holder);

        } else {
            holder = (JobAdapter.ViewHolder) convertView.getTag();
        }

        JobData jobData = JobListTemp.get(position);

        holder.JobTitle.setText(jobData.getJTitle());

        holder.JobVacancies.setText(jobData.getJVacancies());

        holder.JobExperience.setText(jobData.getJExperience());

        holder.JobLocation.setText(jobData.getJLocation());

        holder.JobSalary.setText(jobData.getJSalary());

        return convertView;

    }

    private class JobDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<JobData> arrayList1 = new ArrayList<JobData>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    JobData jobData = MainList.get(i);

                    if(jobData.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(jobData);
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

            JobListTemp = (ArrayList<JobData>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = JobListTemp.size(); i < l; i++)
                add(JobListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}