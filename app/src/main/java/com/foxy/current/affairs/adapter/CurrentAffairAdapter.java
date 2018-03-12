package com.foxy.current.affairs.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.foxy.current.affairs.DetailsActivity;
import com.foxy.current.affairs.R;
import com.foxy.current.affairs.model.CurrentAffair;

import java.util.List;

public class CurrentAffairAdapter extends RecyclerView.Adapter<CurrentAffairAdapter.ViewHolder> {
    private List<CurrentAffair> currentAffairs;
    private Context context;

    public CurrentAffairAdapter(List<CurrentAffair> currentAffairs, Context context) {
        this.currentAffairs = currentAffairs;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CurrentAffair currentAffair = currentAffairs.get(position);
        holder.textViewtitle.setText(currentAffair.getTitle());
        holder.textViewbody.setText(currentAffair.getBody());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent details=new Intent(context,DetailsActivity.class);
                details.putExtra("title",currentAffair.getTitle());
                details.putExtra("body",currentAffair.getBody());
                context.startActivity(details);
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentAffairs.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
         TextView textViewtitle;
         TextView textViewbody;

         ViewHolder(View itemView) {
            super(itemView);
            textViewtitle = itemView.findViewById(R.id.titleId);
            textViewbody = itemView.findViewById(R.id.bodyId);
        }
    }
}
