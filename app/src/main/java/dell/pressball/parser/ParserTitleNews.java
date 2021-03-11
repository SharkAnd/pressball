package dell.pressball.parser;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import dell.pressball.R;
import dell.pressball.activity.MainView;
import dell.pressball.fragments.FragmentNews;
import dell.pressball.parser.model.News;
import dell.pressball.presenter.MainPresenter;


public class ParserTitleNews extends AsyncTask<Integer, Void, ArrayList<News>> {

    public FragmentNews fragmentNews = null;
    private ArrayList arrayList;
    private News news;

    @Override
    protected void onPostExecute(ArrayList<News> arrayList) {
        super.onPostExecute(arrayList);
        fragmentNews.getArrayListNews(arrayList);
    }

    @Override
    protected ArrayList doInBackground(Integer... params) {
        arrayList = new ArrayList<>();
        if (params[0]== R.id.news){
            onNews();
        } else if (params[0]==R.id.pb_online){
            onPBonline();
        } else if (params[0]==R.id.blogs){
            onBlogs();
        } else if (params[0]==R.id.digest){
            onDigest();
        }
        return arrayList;
    }

    private void onNews(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.pressball.by/news/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements daysNews = document.getElementsByClass("last_news1").get(0).getElementsByTag("table");
        for (int i=1; i<daysNews.size(); i++){
            Elements newsT = daysNews.get(i).getElementsByTag("tr");
            for (int j=1; j<newsT.size(); j++){
                if (newsT.get(j).getElementsByClass("time").text()!=null){
                    news = new News(
                            newsT.get(j).getElementsByTag("a").text(),
                            newsT.get(j).getElementsByTag("a").attr("href"),
                            null,
                            newsT.get(j).getElementsByClass("time").text(),
                            null,
                            null,
                            null
                    );
                    arrayList.add(news);
                }
            }
        }
    }

    private void onPBonline(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.pressball.by/pbonline/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element pbOnline = document.getElementsByClass("center_pad").get(0);
        for (int i = 0; i < pbOnline.getElementsByTag("h1").size(); i++) {
            news = new News(
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").text(),
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").attr("href"),
                    pbOnline.getElementsByClass("rybrika").get(i).text(),
                    pbOnline.getElementsByClass("date").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByTag("img").attr("src"),
                    pbOnline.getElementsByClass("news").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByClass("author").text()
            );

            arrayList.add(news);
        }
    }

    private void onBlogs(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.pressball.by/articles/blogs/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element pbOnline = document.getElementsByClass("center_pad").get(0);
        for (int i = 0; i < pbOnline.getElementsByTag("h1").size(); i++) {
            news = new News(
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").text(),
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").attr("href"),
                    pbOnline.getElementsByClass("rybrika").get(i).text(),
                    pbOnline.getElementsByClass("date").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByTag("img").attr("src"),
                    pbOnline.getElementsByClass("news").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByClass("author").text()
            );

            arrayList.add(news);
        }
    }

    private void onDigest(){
        Document document = null;
        try {
            document = Jsoup.connect("https://www.pressball.by/articles/others/digest").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element pbOnline = document.getElementsByClass("center_pad").get(0);
        for (int i = 0; i < pbOnline.getElementsByTag("h1").size(); i++) {
            news = new News(
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").text(),
                    pbOnline.getElementsByTag("h1").get(i).getElementsByTag("a").attr("href"),
                    pbOnline.getElementsByClass("rybrika").get(i).text(),
                    pbOnline.getElementsByClass("date").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByTag("img").attr("src"),
                    pbOnline.getElementsByClass("news").get(i).text(),
                    pbOnline.getElementsByClass("news").get(i).getElementsByClass("author").text()
            );

            arrayList.add(news);
        }
    }

}
