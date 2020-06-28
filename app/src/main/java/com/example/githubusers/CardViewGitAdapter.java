package com.example.githubusers;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewGitAdapter extends RecyclerView.Adapter<CardViewGitAdapter.CardViewHolder> {
    private ArrayList<Git> listGit;

    public CardViewGitAdapter(ArrayList<Git> list) {
        this.listGit = list;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_git, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewHolder holder, int position) {
        Git git = listGit.get(position);
        Glide.with(holder.itemView.getContext()).load(git.getPhoto()).apply(new RequestOptions().override(350, 550)).into(holder.imgPhoto);
        holder.tvName.setText(git.getName());
        holder.tvDetail.setText(git.getUsername());
        holder.bSuka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Kamu menyukai " + listGit.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.bBagikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(holder.itemView.getContext(), "Bagikan " + listGit.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();

                //untuk mengirim HTML: text/html
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Yuk download aplikasi Nenenx Galax di http://inilinkceritanya.com/");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                holder.bBagikan.getContext().startActivity(shareIntent);

            }
        });
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

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;
        Button bSuka, bBagikan;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            bSuka = itemView.findViewById(R.id.b_suka);
            bBagikan = itemView.findViewById(R.id.b_bagikan);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Git data);
    }
}
