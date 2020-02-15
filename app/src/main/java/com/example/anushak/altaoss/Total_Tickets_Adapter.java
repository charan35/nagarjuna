package com.example.anushak.altaoss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class  Total_Tickets_Adapter extends ArrayAdapter<Tickets> {

    public ArrayList<Tickets> MainList;

    public ArrayList<Tickets> TicketsListTemp;

    public TicketsDataFilter ticketsDataFilter;

    public Total_Tickets_Adapter(Context context, int id, ArrayList<Tickets> ticketArrayList) {

        super(context, id, ticketArrayList);

        this.TicketsListTemp = new ArrayList<Tickets>();

        this.TicketsListTemp.addAll(ticketArrayList);

        this.MainList = new ArrayList<Tickets>();

        this.MainList.addAll(ticketArrayList);
    }

    @Override
    public Filter getFilter() {

        if (ticketsDataFilter == null) {

            ticketsDataFilter = new TicketsDataFilter();
        }
        return ticketsDataFilter;
    }


    public class ViewHolder {

        TextView TicketTitle;
        TextView TicketEmpid;
        TextView TicketDate;
        TextView TicketStatus;
        TextView TicketPriority;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.ticket_layout, null);

            holder = new ViewHolder();

            holder.TicketTitle = (TextView) convertView.findViewById(R.id.tickettitle);
            holder.TicketEmpid = (TextView) convertView.findViewById(R.id.ticketempid);
            holder.TicketDate = (TextView) convertView.findViewById(R.id.ticketdate);
            holder.TicketStatus = (TextView) convertView.findViewById(R.id.status);
            holder.TicketPriority = (TextView) convertView.findViewById(R.id.priority);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
       /* if (position % 2 == 1) {
            parent.setBackgroundColor(Color.parseColor("#D3F3ED"));
        } else {
            parent.setBackgroundColor(Color.parseColor("#EFD4CB"));
        }*/


        Tickets tickets = TicketsListTemp.get(position);

        holder.TicketTitle.setText(tickets.getTicketTitle());

        holder.TicketEmpid.setText(tickets.getTicketEmpid());

        holder.TicketDate.setText(tickets.getTicketDate());

        holder.TicketStatus.setText(tickets.getTicketStatus());

        holder.TicketPriority.setText(tickets.getTicketPriority());

        return convertView;

    }

    private class TicketsDataFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if (charSequence != null && charSequence.toString().length() > 0) {
                ArrayList<Tickets> arrayList1 = new ArrayList<Tickets>();

                for (int i = 0, l = MainList.size(); i < l; i++) {
                    Tickets tickets = MainList.get(i);

                    if (tickets.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(tickets);
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

            TicketsListTemp = (ArrayList<Tickets>) filterResults.values;

            notifyDataSetChanged();

            clear();

            for (int i = 0, l = TicketsListTemp.size(); i < l; i++)
                add(TicketsListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}