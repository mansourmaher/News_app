package com.example.newsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.NewResponse;
import com.example.newsapp.NewsDetailActivity;
import com.example.newsapp.R;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapters extends RecyclerView.Adapter<NewsAdapters.ViewHolder>{
    public List<NewResponse.Article> mylist;
    public Context myContext;


    public NewsAdapters(Context context, List<NewResponse.Article> data)
    {
        this.mylist = data;
        this.myContext = context;

    }
    @NonNull
    @Override
    public NewsAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsitem, parent, false);
        return new NewsAdapters.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapters.ViewHolder holder, int position) {
        final NewResponse.Article item =mylist.get(position);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.createdAt.setText(item.getPublishedAt());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(myContext, NewsDetailActivity.class);
            intent.putExtra("image_url", item.getUrlToImage());
            intent.putExtra("title", item.getTitle());
            intent.putExtra("content", item.getContent());
            intent.putExtra("date", item.getPublishedAt());
            myContext.startActivity(intent);
        });



    }
    @Override
    public int getItemCount() {
        return mylist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout layoutItem;
        public TextView title;
        public TextView content;
        public TextView createdAt;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem=itemView.findViewById(R.id.itemView);
            title = itemView.findViewById(R.id.note_title);
            content=itemView.findViewById(R.id.note_content_preview);
            createdAt=itemView.findViewById(R.id.published_date);



        }
    }
}
