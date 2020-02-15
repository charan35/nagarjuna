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

public class NotificationAdapter extends ArrayAdapter<Notification> {

    public ArrayList<Notification> MainList;

    public ArrayList<Notification> NotificationListTemp;

    public NotificationAdapter.NotificationDataFilter notificationDataFilter ;

    public NotificationAdapter(Context context, int id, ArrayList<Notification> notificationArrayList) {

        super(context, id, notificationArrayList);

        this.NotificationListTemp = new ArrayList<Notification>();

        this.NotificationListTemp.addAll(notificationArrayList);

        this.MainList = new ArrayList<Notification>();

        this.MainList.addAll(notificationArrayList);
    }

    @Override
    public Filter getFilter() {

        if (notificationDataFilter == null){

            notificationDataFilter  = new NotificationAdapter.NotificationDataFilter();
        }
        return notificationDataFilter;
    }


    public class ViewHolder {

        TextView Title;
        TextView Message;
        TextView Date;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NotificationAdapter.ViewHolder holder = null;
        if (convertView == null) {

            //View view = super.getView(position, convertView, parent);

            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.notification_layout, null);

            holder = new NotificationAdapter.ViewHolder();

            holder.Title = (TextView) convertView.findViewById(R.id.title);
            holder.Message = (TextView) convertView.findViewById(R.id.message);
            holder.Date = (TextView) convertView.findViewById(R.id.date);

            convertView.setTag(holder);

        } else {
            holder = (NotificationAdapter.ViewHolder) convertView.getTag();
        }


        Notification notification = NotificationListTemp.get(position);

        holder.Title.setText(notification.getNTitle());

        holder.Message.setText(notification.getNMessage());

        holder.Date.setText(notification.getNDate());

        return convertView;

    }

    private class NotificationDataFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            charSequence = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();

            if(charSequence != null && charSequence.toString().length() > 0)
            {
                ArrayList<Notification> arrayList1 = new ArrayList<Notification>();

                for(int i = 0, l = MainList.size(); i < l; i++)
                {
                    Notification notification = MainList.get(i);

                    if(notification.toString().toLowerCase().contains(charSequence))

                        arrayList1.add(notification);
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

            NotificationListTemp = (ArrayList<Notification>)filterResults.values;

            notifyDataSetChanged();

            clear();

            for(int i = 0, l = NotificationListTemp.size(); i < l; i++)
                add(NotificationListTemp.get(i));

            notifyDataSetInvalidated();
        }
    }


}
