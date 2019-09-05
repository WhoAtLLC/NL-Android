package com.root.wishlist.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;

public class IndexListSearchAdapter extends ArrayAdapter<String> implements SectionIndexer,Filterable {
    private ArrayList<String> companyList;
    private ArrayList<String> mCompanyFilter;
    private Context context;
    private static String sections = "abcdefghilmnopqrstuvz";
    ValueFilter valueFilter;

    public IndexListSearchAdapter(Context context, ArrayList<String> companyList) {
        super(context, android.R.layout.simple_list_item_1,companyList);
        this.companyList = companyList;
        this.context = context;
        mCompanyFilter=companyList;
        getFilter();
    }

//    public IndexListSearchAdapter(ArrayList<String> companyList, Context context) {
//
//        this.companyList = companyList;
//        this.context = context;
//        mCompanyFilter=companyList;
//        getFilter();
//    }

    public int getCount() {
        return companyList.size();
    }

    public String getItem(int position) {
        return companyList.get(position);
    }

    public long getItemId(int position) {
        return companyList.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }
        TextView text = (TextView) v.findViewById(android.R.id.text1);
        text.setText(companyList.get(position));
        return v;
    }

    @Override
    public int getPositionForSection(int section) {
        Log.d("ListView", "Get position for section");
        for (int i = 0; i < this.getCount(); i++) {
            String item = this.getItem(i).toLowerCase();
            if (item.charAt(0) == sections.charAt(section))
                return i;
        }
        return 0;
    }

    @Override
    public int getSectionForPosition(int arg0) {
        Log.d("ListView", "Get section");
        return 0;
    }

    @Override
    public Object[] getSections() {
        Log.d("ListView", "Get sections");
        String[] sectionsArr = new String[sections.length()];
        for (int i = 0; i < sections.length(); i++)
            sectionsArr[i] = "" + sections.charAt(i);
        return sectionsArr;
    }

    @Override
    public Filter getFilter() {
        if(valueFilter!=null)
        {
            valueFilter=new ValueFilter();
        }

        return valueFilter;
    }
    private class ValueFilter extends Filter {


        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<String> filterList = new ArrayList<String>();
                for (int i = 0; i < mCompanyFilter.size(); i++) {

                    if (mCompanyFilter.get(i).contains(constraint)) {

                        filterList.add(mCompanyFilter.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = mCompanyFilter.size();
                results.values = mCompanyFilter;
            }

            return results;
        }

        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            companyList = (ArrayList<String>) results.values;
            notifyDataSetChanged();

        }

    }
}