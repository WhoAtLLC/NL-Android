package com.root.wishlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.root.wishlist.R;
import com.root.wishlist.pojo.CountryCodeName;

import java.util.ArrayList;


public class CountryCodeAdapret extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<CountryCodeName> countryCode;
    ArrayList<String> countryName;
    private android.widget.TextView countoryName1;
    private android.widget.TextView countoryCode1;
    private ArrayList<CountryCodeName> mStringFilterList;
    private CountryCodeFilter countryCodeFilter;

    public CountryCodeAdapret(Context context, ArrayList<CountryCodeName> countryCode) {
        this.context = context;
        this.countryCode = countryCode;
        mStringFilterList = countryCode;
        getFilter();
    }

    @Override
    public int getCount() {
        return countryCode.size();
    }

    @Override
    public Object getItem(int position) {
        return countryCode.get(position).getCountryCode();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_spinner_item, parent, false);
        this.countoryCode1 = (TextView) view.findViewById(R.id.countoryCode);
        this.countoryName1 = (TextView) view.findViewById(R.id.countoryName);
        countoryName1.setText(countryCode.get(position).getCountryName());
        countoryCode1.setText(countryCode.get(position).getCountryCode());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (countryCodeFilter == null) {

            countryCodeFilter = new CountryCodeFilter();
        }

        return countryCodeFilter;
    }

    private class CountryCodeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<CountryCodeName> filterList = new ArrayList<>();
                filterList.clear();
                for (int i = 0; i < mStringFilterList.size(); i++) {

                    if (mStringFilterList.get(i).getCountryName().toString().toLowerCase().startsWith(constraint.toString().toLowerCase())) {

                        filterList.add(new CountryCodeName(mStringFilterList.get(i).getCountryName(), mStringFilterList.get(i).getCountryCode()));

                    }
                }
                results.count = filterList.size();
                results.values = filterList;

            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countryCode = (ArrayList<CountryCodeName>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }


        }

    }
}
