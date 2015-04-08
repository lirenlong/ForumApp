package demo.com.forummanagement.fragments;


import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;

import demo.com.forummanagement.db.DBManager;

public abstract class BaseFragment extends Fragment {

    protected DBManager dbManager;
    protected FragmentManager fragmentManager;
    protected ActionBar actionBar;

    public BaseFragment(DBManager dbManager, FragmentManager fragmentManager, ActionBar actionBar) {
        this.dbManager = dbManager;
        this.fragmentManager = fragmentManager;
        this.actionBar = actionBar;
    }

    public abstract String getFragmentIdentifier();

    @Override
    public void onResume() {
        super.onResume();
        actionBar.setTitle(getFragmentIdentifier());
    }

}
