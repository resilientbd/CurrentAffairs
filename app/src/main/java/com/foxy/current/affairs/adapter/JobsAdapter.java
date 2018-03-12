package com.foxy.current.affairs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foxy.current.affairs.R;
import com.foxy.current.affairs.model.Job;

import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    private List<Job> jobs;
    private Context context;

    public JobsAdapter(List<Job> jobs, Context context) {
        this.jobs = jobs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_list_item, null);
        return new JobsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Job job = jobs.get(position);
        holder.textViewheader.setText(job.getHeader());
        holder.textViewtitle.setText(job.getTitle());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewheader;
        TextView textViewtitle;

        ViewHolder(View itemView) {
            super(itemView);
            textViewheader = itemView.findViewById(R.id.header);
            textViewtitle= itemView.findViewById(R.id.title);
        }
    }
}
