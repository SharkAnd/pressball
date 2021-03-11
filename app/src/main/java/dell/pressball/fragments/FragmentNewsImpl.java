package dell.pressball.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dell.pressball.R;
import dell.pressball.activity.MainActivity;
import dell.pressball.activity.MainView;
import dell.pressball.adapter.MyCustomAdapterS;
import dell.pressball.parser.ParserTitleNews;
import dell.pressball.presenter.MainPresenter;


public class FragmentNewsImpl extends Fragment
        implements FragmentNews{

    private MainView mainView;
    private int itemId;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ParserTitleNews parserTitleNews;
    private FragmentNews fragmentNews;
    private MainPresenter mainPresenter;

    public FragmentNewsImpl(MainView mainView, int itemId, MainPresenter mainPresenter) {
        this.mainView=mainView;
        this.itemId=itemId;
        this.mainPresenter=mainPresenter;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        parserTitleNews = new ParserTitleNews();
        parserTitleNews.fragmentNews = this;
        parserTitleNews.execute(itemId);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pbonline, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.news_list);
        recyclerView.setHasFixedSize(true);
        return rootView;
    }

    @Override
    public void getArrayListNews(ArrayList news) {
        MyCustomAdapterS adapter = new MyCustomAdapterS(news,itemId);
        recyclerView.setAdapter(adapter);
        mainView.closeProgressBar();
        mainPresenter.closeSwipeRefresh();
    }
}
