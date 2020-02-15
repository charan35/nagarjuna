package com.example.anushak.altaoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewvisitorAdapter extends ArrayAdapter<Viewvisitor> {

    public ArrayList<Viewvisitor> MainList;

    public ArrayList<Viewvisitor> VisitorListTemp;

    public ViewvisitorAdapter.VisitorDataFilter visitorDataFilter;

    public ViewvisitorAdapter(Context context, int id, ArrayList<Viewvisitor> viewvisitorArrayList) {

        super(context, id, viewvisitorArrayList);

        this.VisitorListTemp = new ArrayList<Viewvisitor>();

        this.VisitorListTemp.addAll(viewvisitorArrayList);

        this.MainList = new ArrayList<Viewvisitor>();

        this.MainList.addAll(viewvisitorArrayList);
    }

    @Override
    public Filter getFilter() {

        if (visitorDataFilter == null) {

            visitorDataFilter = new ViewvisitorAdapter.VisitorDataFilter();
        }
        return visitorDataFilter;
    }


    public class ViewHolder {

        TextView Fname;
        TextView Lname;
        TextView Reasonforvisit;
        TextView Cname;
        TextView Cpname;
        TextView Dvisit;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewvisitorAdapter.ViewHolder holder = null;
        if (convertView == null) {

          //  View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.visitorlayout, null);

            holder = new ViewvisitorAdapter.ViewHolder();

            holder.Fname = (TextView) convertView.findViewById(R.id.visfname);
            holder.Lname = (TextView) convertView.findViewById(R.id.vislname);
            holder.Reasonforvisit = (TextView) convertView.findViewById(R.id.visreasonforvisit);
            holder.Cname = (TextView) convertView.findViewById(R.id.viscname);
            holder.Cpname = (TextView) convertView.findViewById(R.id.viscpname);
            holder.Dvisit = (TextView) convertView.findViewById(R.id.visdvisit);

            convertView.setTag(holder);

        } else {
            holder = (ViewvisitorAdapter.ViewHolder) convertView.getTag();
        }


        Viewvisitor viewvisitor = VisitorListTemp.get(position);

        holder.Fname.setText(viewvisitor.getVFName());

        holder.Lname.setText(viewvisitor.getVLName());

        holder.Reasonforvisit.setText(viewvisitor.getVReasonforvisit());

        holder.Cname.setText(viewvisitor.getVCName());

        holder.Cpname.setText(viewvisitor.getVCPName());

        holder.Dvisit.setText(viewvisitor.getVDVisit());

        return convertView;

    }

    private class VisitorDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<Viewvisitor> arrayList1 = new ArrayList<Viewvisitor>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    Viewvisitor viewvisitor = MainList.get(i);

                    if (viewvisitor.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(viewvisitor);
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

            VisitorListTemp = (ArrayList<Viewvisitor>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = VisitorListTemp.size(); i < l; i++)
                add(VisitorListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }
}

