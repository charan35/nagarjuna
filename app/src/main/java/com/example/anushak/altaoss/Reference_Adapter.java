package com.example.anushak.altaoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class Reference_Adapter extends ArrayAdapter<reference_update> {


    public ArrayList<reference_update> MainList;

    public ArrayList<reference_update> UpdateListTemp;

    public UpdateDataFilter updateDataFilter;

    public Reference_Adapter(Context context, int id, ArrayList<reference_update> updateArrayList) {

        super(context, id, updateArrayList);

        this.UpdateListTemp = new ArrayList<reference_update>();

        this.UpdateListTemp.addAll(updateArrayList);

        this.MainList = new ArrayList<reference_update>();

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
        TextView referencename;
        TextView referenceid;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.reference_updatelist, null);

            holder = new ViewHolder();

            holder.Name = (TextView) convertView.findViewById(R.id.name);
            holder.qualification = (TextView) convertView.findViewById(R.id.qualification);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.phone = (TextView) convertView.findViewById(R.id.mobile);
            holder.project = (TextView) convertView.findViewById(R.id.project);

            holder.referencename = (TextView) convertView.findViewById(R.id.referencename);
            holder.referenceid = (TextView) convertView.findViewById(R.id.referenceid);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       /* if (position % 2 == 1) {
            parent.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            parent.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }*/


        reference_update update = UpdateListTemp.get(position);

        holder.Name.setText(update.getProName());

        holder.qualification.setText(update.getPronotes());

        holder.email.setText(update.getProEmpId());

        holder.phone.setText(update.getDateandTime());

        holder.project.setText(update.getProject());

        holder.referencename.setText(update.getReferencename());

        holder.referenceid.setText(update.getReferenceid());

        return convertView;

    }

    private class UpdateDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<reference_update> arrayList1 = new ArrayList<reference_update>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    reference_update update = MainList.get(i);

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

            UpdateListTemp = (ArrayList<reference_update>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = UpdateListTemp.size(); i < l; i++)
                add(UpdateListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }



}
