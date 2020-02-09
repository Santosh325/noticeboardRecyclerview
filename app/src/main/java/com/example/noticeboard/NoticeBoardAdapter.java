package com.example.noticeboard;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.NoticeViewHolder> {
    List<Notes> notesList;
    private Context context;

    public NoticeBoardAdapter(List<Notes> notesList, Context context)
    {
        this.context = context;
        this.notesList = notesList;
    }
    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item,parent,false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        final Notes data = notesList.get(position);
        holder.title.setText(data.getTitle());
        holder.desc.setText(data.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,viewactivity.class);
                i.putExtra("id",data.getId());
                i.putExtra("title",data.getTitle());

                i.putExtra("description",data.getDesc());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return notesList.size();
    }
    public class NoticeViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc;
        public NoticeViewHolder(View itemview) {
            super(itemview);
            title = itemview.findViewById(R.id.title);
            desc = itemview.findViewById(R.id.description);
        }
    }
}
