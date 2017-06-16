package com.softengine.nethaber.network;

import android.os.AsyncTask;

import com.softengine.nethaber.model.NewsModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mehme on 12.06.2017.
 */

public class GetNews extends AsyncTask<Void, Void, ArrayList<NewsModel>> {

    private String newsUrl;
    protected NewsListener newsListener;

    public GetNews(String newsUrl,NewsListener newsListener) {
        this.newsUrl = newsUrl;
        this.newsListener = newsListener;
    }


    @Override
    protected ArrayList<NewsModel> doInBackground(Void... params) {

        ArrayList<NewsModel> news = new ArrayList<>();

        try {
            URL xmlUrl = new URL(newsUrl);
            InputStream stream = xmlUrl.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(stream);

            NodeList list = doc.getElementsByTagName("item");

            for(int i = 0 ; i < list.getLength() ; i++)
            {
                Element element = (Element) list.item(i);
                String link = element.getElementsByTagName("link").item(0).getFirstChild().getTextContent();
                String description = element.getElementsByTagName("description").item(0).getFirstChild().getTextContent();
                String title = element.getElementsByTagName("title").item(0).getFirstChild().getTextContent();
                String image = element.getElementsByTagName("image").item(0).getFirstChild().getTextContent();
                String pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getTextContent();
                //String id = element.getElementsByTagName("category").item(0).getFirstChild().getTextContent();
                news.add(new NewsModel("",title,description,pubDate,image,link));
            }
        } catch (MalformedURLException e) {
            // Rss kaynağı değişti
            e.printStackTrace();
            newsListener.onFailGettingNews("Rss kaynağı değişti");
        } catch (IOException e) {
            // URL bozuk
            e.printStackTrace();
            newsListener.onFailGettingNews("URL bozuk");
        } catch (ParserConfigurationException e) {
            // Br hata oluştu
            e.printStackTrace();
            newsListener.onFailGettingNews("Br hata oluştu");
        } catch (SAXException e) {
            // Bir hata oluştu
            e.printStackTrace();
            newsListener.onFailGettingNews("Bir hata oluştu");
        }

        return news;

    }



    @Override
    protected void onPostExecute(ArrayList<NewsModel> newsModels) {
        super.onPostExecute(newsModels);
        newsListener.onSuccessGettingNews(newsModels);
    }


    Response getResponse(Request request, OkHttpClient client) throws IOException {

        /**
         *  OkHttpClient client = new OkHttpClient();

         Request request = new Request.Builder()
         .url(this.newsUrl)
         .build();

         Response response = null;

         try {
         response = getResponse(request, client);
         if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
         } catch (IOException e) {
         e.printStackTrace();
         }
         *
         *
         */
        Response response = client.newCall(request).execute();
        return response;
    }

}
