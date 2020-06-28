package com.example.githubusers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridGitAdapter extends RecyclerView.Adapter<GridGitAdapter.GridViewHolder> {
    private ArrayList<Git> listGit;

    public GridGitAdapter(ArrayList<Git> list) {
        this.listGit = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_git, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Git git = listGit.get(position);
        Glide.with(holder.itemView.getContext()).load(git.getPhoto()).apply(new RequestOptions().override(350, 550)).into(holder.imgPhoto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listGit.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listGit.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Git data);
    }
}
