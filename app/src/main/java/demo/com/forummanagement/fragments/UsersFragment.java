package demo.com.forummanagement.fragments;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

import demo.com.forummanagement.MainActivity;
import demo.com.forummanagement.R;
import demo.com.forummanagement.adapters.UsersListAdapter;
import demo.com.forummanagement.db.DBManager;
import demo.com.forummanagement.db.User;

@SuppressLint("ValidFragment")
public class UsersFragment extends BaseFragment {

    private String specName;
    private Context context;
    private Handler handler;

    public UsersFragment(DBManager dbManager, FragmentManager fragmentManager, ActionBar actionBar, String specName, Context context, Handler handler) {
        super(dbManager, fragmentManager, actionBar);
        this.specName = specName;
        this.dbManager = dbManager;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        PullToRefreshListView list = (PullToRefreshListView) view.findViewById(R.id.users_list);

        final List<User> users = dbManager.queryUsers(specName);

        final UsersListAdapter adapter = new UsersListAdapter(context);
        adapter.setUsers(users);
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = (User) adapter.getItem(position - 1);
                Message message = new Message();
                message.what = MainActivity.ACTION_SHOW_DETAIL;
                message.obj = user;
                handler.sendMessage(message);
            }
        });

        return view;
    }

    @Override
    public String getFragmentIdentifier() {
        return "用户列表";
    }
}
