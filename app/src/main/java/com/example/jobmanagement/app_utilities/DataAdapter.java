package com.example.jobmanagement.app_utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobmanagement.R;
import com.example.jobmanagement.data_models.JobAdvert;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
        implements View.OnClickListener
{
    private ArrayList<JobAdvert> list;


    public DataAdapter(ArrayList<JobAdvert> jobAdvertList)
    {
        list = jobAdvertList;
    }

    @Override
    public int getItemCount() {
        return ApplicationClass.jobAdverts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView itemImage;

        public TextView itemJobTitle;
        public TextView itemJobLocation;
        public TextView itemAdCompany;
        public TextView itemMinQualification;
        public TextView itemSalary;

        public ImageView itemApply;
        public ImageView itemInfo;
        public ImageView itemUpdate;
        public ImageView itemDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.imageView);

            itemJobTitle = (TextView)itemView.findViewById(R.id.tvJobTitle);
            itemJobLocation = (TextView)itemView.findViewById(R.id.tvJobLocation);
            itemAdCompany = (TextView)itemView.findViewById(R.id.tvAdCompany);
            itemMinQualification = (TextView)itemView.findViewById(R.id.tvMinQualification);
            itemSalary = (TextView)itemView.findViewById(R.id.tvSalary);

            itemApply = (ImageView)itemView.findViewById(R.id.ivApply);
            itemInfo = (ImageView)itemView.findViewById(R.id.ivInfo);
            itemUpdate = (ImageView)itemView.findViewById(R.id.ivUpdate);
            itemDelete = (ImageView)itemView.findViewById(R.id.ivDelete);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i)
    {

        viewHolder.itemJobTitle.setText(list.get(i).getJobTitle());
        viewHolder.itemJobLocation.setText(list.get(i).getJobLocation());
        viewHolder.itemAdCompany.setText(list.get(i).getJobCompany());
        viewHolder.itemMinQualification.setText(list.get(i).getJobQualification());
        viewHolder.itemSalary.setText(list.get(i).getJobSalary());

    }
    @Override
    public void onClick(View view) {

        ImageView ivApply;
        ivApply = view.findViewById(R.id.ivApply);
        switch(view.getId())
        {
            case R.id.ivApply:
                ivApply.setImageResource(R.drawable.favorite);
                break;

                //Needs modifications
//            case R.id.ivInfo:
//            case R.id.ivUpdate:
//            case R.id.ivDelete:
//                break;
        }
    }
}
