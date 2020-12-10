package com.example.basalam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.basalam.R;
import com.example.basalam.model.Data;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    private final List<Data> data;

    public adapter(List<Data> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Data data = this.data.get(position);
        holder.title.setText(data.getTitle());

        GlideUrl url = new GlideUrl(data.getImageAddress(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "User-Agent")
                .build());

        Glide.with(holder.itemView.getContext())
                .load(url)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
