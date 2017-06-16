package com.softengine.nethaber.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softengine.nethaber.R;
import com.softengine.nethaber.model.NewsModel;
import com.softengine.nethaber.network.GetNews;
import com.softengine.nethaber.network.NewsListener;
import com.softengine.nethaber.util.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


public class TumHaberler extends Fragment {

    ArrayList<NewsModel> newsModelList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    RecyclerAdapter adapter;


    public TumHaberler(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_tum_haberler, container, false);
        newsModelList=new ArrayList<>();



        adapter=new RecyclerAdapter(newsModelList,mContext);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycle_tum_haberler);
        mLinearLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);


        new GetNews("http://www.haberturk.com/rss", new NewsListener() {
            @Override
            public void onSuccessGettingNews(ArrayList<NewsModel> news) {

                for (NewsModel item:news){
                    newsModelList.add(item);
                    Log.d("haber",/*item.getCategori()+" "+item.getTitle()+ ""+ item.getDescription()+" "+item.getPubDate()+" "+item.getLink()+""+*/item.getImage()+"\n\n\n");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailGettingNews(String failMessage) {
                Log.d("Tag",""+failMessage);
                Snackbar snackbar = Snackbar
                        .make(view, ""+failMessage, Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        }).execute();


        return view;
    }
}
