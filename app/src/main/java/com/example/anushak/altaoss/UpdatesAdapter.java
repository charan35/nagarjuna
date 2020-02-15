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

public class UpdatesAdapter extends ArrayAdapter<updates> {

    public ArrayList<updates> MainList;

    public ArrayList<updates> UpdateListTemp;

    public UpdatesAdapter.UpdateDataFilter updateDataFilter ;

    public UpdatesAdapter(Context context, int id, ArrayList<updates> updateArrayList) {

        super(context, id, updateArrayList);

        this.UpdateListTemp = new ArrayList<updates>();

        this.UpdateListTemp.addAll(updateArrayList);

        this.MainList = new ArrayList<updates>();

        this.MainList.addAll(updateArrayList);
    }

    @Override
    public Filter getFilter() {

        if (updateDataFilter == null){

            updateDataFilter  = new UpdatesAdapter.UpdateDataFilter();
        }
        return updateDataFilter;
    }


    public class ViewHolder {

        TextView ProjectName;
        TextView ProjectNotes;
        TextView EmpId;
        TextView DateandTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UpdatesAdapter.ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.update_list, null);

            holder = new UpdatesAdapter.ViewHolder();

            holder.ProjectName = (TextView) convertView.findViewById(R.id.projectname);
            holder.ProjectNotes = (TextView) convertView.findViewById(R.id.notes);
            holder.EmpId = (TextView) convertView.findViewById(R.id.empid);
            holder.DateandTime = (TextView) convertView.findViewById(R.id.dateandtime);

            convertView.setTag(holder);

        } else {
            holder = (UpdatesAdapter.ViewHolder) convertView.getTag();
        }
       /* if (position % 2 == 1) {
            parent.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            parent.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }*/


        updates update = UpdateListTemp.get(position);

        holder.ProjectName.setText(update.getProName());

        holder.ProjectNotes.setText(update.getPronotes());

        holder.EmpId.setText(update.getProEmpId());

        holder.DateandTime.setText(update.getDateandTime());

        return convertView;

    }

    private class UpdateDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<updates> arrayList1 = new ArrayList<updates>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    updates update = MainList.get(i);

                    if(update.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(update);
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

            UpdateListTemp = (ArrayList<updates>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = UpdateListTemp.size(); i < l; i++)
                add(UpdateListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}