package com.example.basalam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.basalam.R;
import com.example.basalam.datamodel.Getset;
import com.squareup.picasso.Picasso;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    private Context context;
    List<Getset> data;
    public adapter(Context context, List<Getset> data){
        this.context=context;
        this.data=data;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.card_view,parent,false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        final Getset getset=data.get(position);
        holder.title.setText(getset.getTitle());
        Picasso.with(context).load(getset.getUrlimg()).into(holder.image);
        //Glide.with(context).load(getset.getUrlimg()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewholder extends RecyclerView.ViewHolder
    {

       public ImageView image;
       public TextView title;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            title =(TextView)itemView.findViewById(R.id.txt_title);
            image =(ImageView)itemView.findViewById(R.id.image);

        }

    }

}
