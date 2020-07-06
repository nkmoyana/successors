package com.example.jobmanagement.app_utilities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jobmanagement.R;
import com.example.jobmanagement.data_models.JobAdvert;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>
        implements View.OnClickListener
{
    private List<JobAdvert> list;
    private CardViewButtonClickListener activityCallBack;
    private int i;

    public DataAdapter(List<JobAdvert> jobAdvertList)
    {
        list = jobAdvertList;
    }

    @Override
    public int getItemCount() {
        return list.size();
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

        ImageView ivApply = v.findViewById(R.id.ivApply);
        ivApply.setOnClickListener(this);

        ImageView ivInfo = v.findViewById(R.id.ivInfo);
        ivInfo.setOnClickListener(this);

        ImageView ivUpdate = v.findViewById(R.id.ivUpdate);
        ivUpdate.setOnClickListener(this);

        ImageView ivDelete = v.findViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(this);


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

    //Just Testing, please verify
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        try{
            if (recyclerView.getContext() instanceof CardViewButtonClickListener)
            {
                activityCallBack = (CardViewButtonClickListener) recyclerView.getContext();
            }
            else
            {
                throw new ClassCastException(recyclerView.getContext().toString()
                        + "must implement DataAdapter.CardViewButtonClickListener");
            }
        }
        catch (ClassCastException e)
        {
            Log.e("ClassCastException", e.getMessage());
        }
    }

   private void UpdateImageViewFunc(JobAdvert jobAdvert)
   {
       activityCallBack.onEditAdvertClick(jobAdvert);
   }

    private void InfoImageViewFunc(int index)
    {
        activityCallBack.onViewAdvertClick(index);
    }

   private void ApplyImageViewFunc(ImageView ivApply)
   {
       ivApply.setImageResource(R.drawable.favorite);
   }

   private void DeleteImageViewFunc(JobAdvert jobAdvert)
   {
       activityCallBack.onDeleteAdvertClick(jobAdvert);
   }

    @Override
    public  void onClick(View view) {

        i = list.indexOf((JobAdvert) view.getTag());
        ImageView ivApply;
        ivApply = view.findViewById(R.id.ivApply);

        switch(view.getId())
        {
            case R.id.ivUpdate:
                UpdateImageViewFunc(list.get(0));
                break;

            case R.id.ivInfo:
                InfoImageViewFunc(0);
                break;

            case R.id.ivApply:
                ApplyImageViewFunc(ivApply);
                break;

            case R.id.ivDelete:
                DeleteImageViewFunc(list.get(i));
                break;
        }
    }
}
