package com.root.wishlist.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.root.wishlist.pojo.leads.getintroduce.IntroCompanies;
import com.root.wishlist.R;
import com.root.wishlist.util.SquareImageView;

import java.util.ArrayList;
import java.util.List;


public class MyintroCompaniesAdapter extends RecyclerView.Adapter<MyintroCompaniesAdapter.ViewHolder> {
    Context context;
    List<IntroCompanies> companies;
    ArrayList<Integer> selectedcompany = new ArrayList<>();

    Typeface normalFont;

    public MyintroCompaniesAdapter(List<IntroCompanies> companies, Context context) {
        this.context = context;
        this.companies = companies;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = (LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.company_details, viewGroup, false));
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.companyname.setText(companies.get(position).getTitle());
        Glide.with(context).load(companies.get(position).getProfileImageUrl()).error(R.drawable.wish).placeholder(R.drawable.wish).into(holder.companyimage);
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    selectedcompany.add(companies.get(position).getId());
                    SelectedCompany selectedCompany = (SelectedCompany) context;
                    selectedCompany.selectCompany(selectedcompany);
                    Log.d("checked", String.valueOf(selectedcompany.size()));
                } else {
                    selectedcompany.remove(companies.get(position).getId());
                    SelectedCompany selectedCompany = (SelectedCompany) context;
                    selectedCompany.selectCompany(selectedcompany);
                }
                Log.d("unselect", String.valueOf(selectedcompany.size()));

            }
        });
        normalFont = Typeface.createFromAsset(context.getAssets(), "Font/Gotham-Book.otf");
        holder.companyname.setTypeface(normalFont);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public interface SelectedCompany {
        void selectCompany(ArrayList<Integer> companyId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SquareImageView companyimage;
        private TextView companyname;
        private CheckBox checkbox;

        public ViewHolder(View view) {
            super(view);
            this.checkbox = (CheckBox) view.findViewById(R.id.checkbox);
            this.companyname = (TextView) view.findViewById(R.id.company_name);
            this.companyimage = (SquareImageView) view.findViewById(R.id.company_image);
        }
    }

}
