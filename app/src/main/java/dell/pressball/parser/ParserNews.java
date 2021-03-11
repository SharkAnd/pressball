package dell.pressball.parser;

import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dell.pressball.R;
import dell.pressball.activity.MainView;
import dell.pressball.activity.NewsView;
import dell.pressball.presenter.MainPresenter;

import static dell.pressball.R.*;

public class ParserNews extends AsyncTask<HashMap<Integer,String>,Void,String> {

    private NewsView listener;
    private MainPresenter mainPresenter;
    private String text;
    private String url;
    private int itemId;

    public void setOnResultsListener(NewsView listener) {
        this.listener = listener;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.closeProgressBar();
        listener.showNews(s, url);
    }

    @Override
    protected String doInBackground(HashMap<Integer,String>... params) {

        HashMap<Integer, String> hashMap = params[0];
        Set<Map.Entry<Integer, String>> set = hashMap.entrySet();
        for (Map.Entry<Integer, String> me : set) {
            url = me.getValue();
            itemId = me.getKey();
        }
        if(itemId== R.id.news){
            onNews(url);
        } else {
            onPosts(url);
        }
        return text;
    }

    private void onPosts(String url) {
        Document document;
        try {

            document = Jsoup.connect(url).get();
            Element title = document.getElementsByClass("center_pad").get(0).getElementsByTag("h1").get(0);
            text = "<h1>"+title.html()+"</h1>";
            Elements element1 = document.select(".inner_news");
            text = text+element1.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onNews(String url) {
        Document document;
        try {
            document = Jsoup.connect(url).get();
            Element title = document.getElementById("lenta").getElementsByTag("h1").get(0);
            text = "<h1>"+title.html()+"</h1>";
            Elements element1 = document.select(".lenta_news");
            text = text+element1.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
