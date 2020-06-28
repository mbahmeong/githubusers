package com.example.githubusers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListGitAdapter extends RecyclerView.Adapter<ListGitAdapter.ListViewHolder> {
    private ArrayList<Git> listGit;

    public ListGitAdapter(ArrayList<Git> list) {
        this.listGit = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_git, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Git git = listGit.get(position);
        Glide.with(holder.itemView.getContext()).load(git.getPhoto()).apply(new RequestOptions().override(55, 55)).into(holder.ivPhoto);
        holder.tvName.setText(git.getName());
        holder.tvUsername.setText(git.getUsername());
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

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvUsername;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvUsername = itemView.findViewById(R.id.tv_item_username);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Git data);
    }
}

