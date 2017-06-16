package com.softengine.nethaber.network;

import com.softengine.nethaber.model.NewsModel;

import java.util.ArrayList;

/**
 * Created by mehme on 12.06.2017.
 */

public interface NewsListener {
     void onSuccessGettingNews(ArrayList<NewsModel> news);
     void onFailGettingNews(String failMessage);

}
