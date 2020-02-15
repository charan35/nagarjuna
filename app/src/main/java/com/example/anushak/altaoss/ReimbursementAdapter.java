package com.example.anushak.altaoss;

/**
 * Created by Anushak on 05-04-2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReimbursementAdapter extends ArrayAdapter<Reimbursement> {

    public ArrayList<Reimbursement> MainList;

    public ArrayList<Reimbursement> ReimbursementListTemp;

    public ReimbursementAdapter.ReimbursementDataFilter reimbursementDataFilter ;

    public ReimbursementAdapter(Context context, int id, ArrayList<Reimbursement> reimbursementArrayList) {

        super(context, id, reimbursementArrayList);

        this.ReimbursementListTemp = new ArrayList<Reimbursement>();

        this.ReimbursementListTemp.addAll(reimbursementArrayList);

        this.MainList = new ArrayList<Reimbursement>();

        this.MainList.addAll(reimbursementArrayList);
    }

    @Override
    public Filter getFilter() {

        if (reimbursementDataFilter == null){

            reimbursementDataFilter  = new ReimbursementAdapter.ReimbursementDataFilter();
        }
        return reimbursementDataFilter;
    }


    public class ViewHolder {

        TextView RType;
        TextView RId,RDate,RStatus;
        Button RApprove,RReject;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ReimbursementAdapter.ViewHolder holder = null;
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.reimbursement_layout, null);

            holder = new ReimbursementAdapter.ViewHolder();

            holder.RType = (TextView) convertView.findViewById(R.id.type);
            holder.RId = (TextView) convertView.findViewById(R.id.empid);
            holder.RDate = (TextView) convertView.findViewById(R.id.date);
            holder.RStatus = (TextView) convertView.findViewById(R.id.status);
            /*holder.RApprove = (Button) convertView.findViewById(R.id.approve);
            holder.RReject = (Button) convertView.findViewById(R.id.reject);
*/
            final ViewHolder finalHolder = holder;
            /*holder.RApprove.setOnClickListener(new View.OnClickListener(){
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //do something
                   // finalHolder.RApprove.setText("clicked");
                    finalHolder.RApprove.setTextColor(R.color.gradStart);

                }
            });
            holder.RReject.setOnClickListener(new View.OnClickListener(){
                @SuppressLint("ResourceAsColor")
                @Override
                public void onClick(View v) {
                    //do something
                    finalHolder.RReject.setTextColor(R.color.gradStop);
                }
            });
*/
            convertView.setTag(holder);

        } else {
            holder = (ReimbursementAdapter.ViewHolder) convertView.getTag();
        }

        Reimbursement reimbursement = ReimbursementListTemp.get(position);

        holder.RType.setText(reimbursement.getRType());

        holder.RId.setText(reimbursement.getRId());

        holder.RDate.setText(reimbursement.getRDate());

        holder.RStatus.setText(reimbursement.getRStatus());

        return convertView;

    }

    private class ReimbursementDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<Reimbursement> arrayList1 = new ArrayList<Reimbursement>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    Reimbursement reimbursement = MainList.get(i);

                    if(reimbursement.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(reimbursement);
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

            ReimbursementListTemp = (ArrayList<Reimbursement>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = ReimbursementListTemp.size(); i < l; i++)
                add(ReimbursementListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}