package dell.pressball.presenter;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;

import dell.pressball.R;
import dell.pressball.activity.MainView;
import dell.pressball.fragments.FragmentNewsImpl;


public class MainPresenterImpl implements MainPresenter, NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    private MainView mainView;
    private Fragment fragment;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MenuItem item;

    public MainPresenterImpl(MenuItem menuItem, SwipeRefreshLayout swipeRefreshLayout, NavigationView navigationView, MainView mainView) {
        this.item = menuItem;
        this.swipeRefreshLayout= swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.dispatchSetSelected(true);
        this.mainView = mainView;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        this.item = item;
        mainView.closeDrawer();
        mainView.showProgressBar();
        fragment = new FragmentNewsImpl(mainView, item.getItemId(),this);
        mainView.getFragment(fragment);
        return true;

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        fragment = new FragmentNewsImpl(mainView, item.getItemId(), this);
        mainView.getFragment(fragment);
    }

    @Override
    public void closeSwipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
