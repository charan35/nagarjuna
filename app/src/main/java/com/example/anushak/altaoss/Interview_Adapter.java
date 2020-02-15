package com.example.anushak.altaoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class Interview_Adapter extends ArrayAdapter<interview_updates> {

    public ArrayList<interview_updates> MainList;

    public ArrayList<interview_updates> UpdateListTemp;

    public UpdateDataFilter updateDataFilter;

    public Interview_Adapter(Context context, int id, ArrayList<interview_updates> updateArrayList) {

        super(context, id, updateArrayList);

        this.UpdateListTemp = new ArrayList<interview_updates>();

        this.UpdateListTemp.addAll(updateArrayList);

        this.MainList = new ArrayList<interview_updates>();

        this.MainList.addAll(updateArrayList);
    }

    @Override
    public Filter getFilter() {

        if (updateDataFilter == null) {

            updateDataFilter = new UpdateDataFilter();
        }
        return updateDataFilter;
    }


    public class ViewHolder {

        TextView Name;
        TextView qualification;
        TextView email;
        TextView phone;
        TextView project;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.interview_update_list, null);

            holder = new ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.projectname);
            holder.qualification = (TextView) convertView.findViewById(R.id.notes);
            holder.email = (TextView) convertView.findViewById(R.id.empid);
            holder.phone = (TextView) convertView.findViewById(R.id.dateandtime);
            holder.project = (TextView) convertView.findViewById(R.id.project);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       /* if (position % 2 == 1) {
            parent.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            parent.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }*/


        interview_updates update = UpdateListTemp.get(position);

        holder.Name.setText(update.getProName());

        holder.qualification.setText(update.getPronotes());

        holder.email.setText(update.getProEmpId());

        holder.phone.setText(update.getDateandTime());

        holder.project.setText(update.getProject());

        return convertView;

    }

    private class UpdateDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<interview_updates> arrayList1 = new ArrayList<interview_updates>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    interview_updates update = MainList.get(i);

                    if (update.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(update);
                }
                filterResults.count = arrayList1.size();

                filterResults.values = arrayList1;
            } else {
                synchronized (this) {
                    filterResults.values = MainList;

                    filterResults.count = MainList.size();
                }
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            UpdateListTemp = (ArrayList<interview_updates>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = UpdateListTemp.size(); i < l; i++)
                add(UpdateListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}