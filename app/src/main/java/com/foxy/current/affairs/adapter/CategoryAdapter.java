package com.foxy.current.affairs.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.foxy.current.affairs.CurrentAffairsActivity;
import com.foxy.current.affairs.R;
import com.foxy.current.affairs.model.Category;

import java.util.List;

/**
 * Created by JISAN on 2/16/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> categories;
    Context context;

    public CategoryAdapter(List<Category> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list, null);
        return new CategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        final Category category = categories.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;
        Drawable drawable = TextDrawable.builder().buildRound(category.getFirst(), generator.getRandomColor());
        holder.letter.setImageDrawable(drawable);
        holder.name.setText(category.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CurrentAffairsActivity.class).putExtra("name", category.getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView letter;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.letter);
            name = itemView.findViewById(R.id.name);
        }
    }
}
