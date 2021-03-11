package dell.pressball.activity;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MainView {
    void closeDrawer();
    void getFragment(Fragment fragment);
    void showProgressBar();
    void closeProgressBar();
}
