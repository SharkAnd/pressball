package dell.pressball.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.util.HashMap;

import dell.pressball.R;
import dell.pressball.parser.ParserNews;

import static dell.pressball.adapter.MyCustomAdapterS.map;

public class NewsActivity extends AppCompatActivity implements NewsView {

    private ProgressBar progressBar;
    WebView webView;
    ParserNews parserNews;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.qwerty);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
        progressBar = (ProgressBar)findViewById(R.id.progressBarM);
        progressBar.setVisibility(View.VISIBLE);

        webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        onParsNews(map);

    }



    @Override
    public void closeProgressBar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onParsNews(HashMap<Integer, String> map) {
        parserNews = new ParserNews();
        parserNews.setOnResultsListener(this);
        parserNews.execute(map);
    }

    @Override
    public void showNews(String news, String url) {
        this.url = url;
        closeProgressBar();
        news = "<link rel=\"stylesheet\" type=\"text/css\" href=\"stl.css\" />"+news;
        webView.loadDataWithBaseURL("file:///android_asset/", news, "text/html", "UTF-8",  null);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            onShare();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onShare(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        startActivity(intent);
    }
}
