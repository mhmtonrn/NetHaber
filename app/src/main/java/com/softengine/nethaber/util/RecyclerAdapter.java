package com.softengine.nethaber.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softengine.nethaber.R;
import com.softengine.nethaber.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mehme on 13.06.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<NewsModel> newsModelList=null;
    Context context;
    public RecyclerAdapter(List<NewsModel> newsModelList,Context context) {
        this.newsModelList = newsModelList;
        this.context=context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,pubdate;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.row_title);
            desc= (TextView) itemView.findViewById(R.id.row_desc);
            pubdate= (TextView) itemView.findViewById(R.id.row_pubdate);
            image= (ImageView) itemView.findViewById(R.id.row_image);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        NewsModel news=newsModelList.get(position);
        holder.title.setText(Html.fromHtml(news.getTitle()));
        holder.desc.setText(news.getDescription());
        holder.pubdate.setText(Html.fromHtml(news.getPubDate()));
        Picasso.with(context).load(newsModelList.get(position).getImage()).fit().into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsModelList.get(position).getLink()));
                context.startActivity(browserIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (newsModelList ==null){
            return 0;
        }
        return newsModelList.size();
    }






}
