package dell.pressball.activity;


import java.util.HashMap;

public interface NewsView {
    void closeProgressBar();
    void onParsNews(HashMap<Integer,String> map);
    void showNews(String news, String url);
}
