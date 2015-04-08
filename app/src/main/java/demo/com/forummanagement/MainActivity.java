package demo.com.forummanagement;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import demo.com.forummanagement.db.DBManager;
import demo.com.forummanagement.fragments.BaseFragment;
import demo.com.forummanagement.fragments.DetailFragment;
import demo.com.forummanagement.fragments.NewUserFragment;
import demo.com.forummanagement.fragments.OperationFragment;
import demo.com.forummanagement.fragments.UsersFragment;


public class MainActivity extends Activity {

    private ActionBar actionBar;

    private Context context;

    private OperationHandler handler;
    private DBManager dbManager;
    private FragmentManager fragmentManager;

    private ProgressDialog progressDialog;

    private boolean isDataBaseReady = false;
    private String title;

    public static final int DB_READY = -1;

    public static final int ACTION_OPERATION = 0;
    public static final int ACTION_SHOW_ALL = 1;
    public static final int ACTION_SHOW_SPEC = 2;
    public static final int ACTION_SHOW_DETAIL = 3;
    public static final int ACTION_NEW_USER = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForumManageApplication.getInstance().addActivity(this);

        handler = new OperationHandler();
        context = this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        fragmentManager = getFragmentManager();

        title = "操作中心 [管理员:" + getIntent().getStringExtra("usr") + "]";
        actionBar = getActionBar();
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dbManager = new DBManager(getApplicationContext());
                handler.sendEmptyMessage(DB_READY);
            }
        }).start();
    }

    private void replaseFragment(BaseFragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_layout_container, fragment);
        if (addBackStack) {
            transaction.addToBackStack(fragment.getFragmentIdentifier());
        }
        transaction.commitAllowingStateLoss();
    }

    class OperationHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == DB_READY) {
                isDataBaseReady = true;
                progressDialog.dismiss();
            }
            if (isDataBaseReady) {
                BaseFragment fragment = null;
                switch (msg.what) {
                    case ACTION_OPERATION:
                    case DB_READY:
                        fragment = new OperationFragment(dbManager, fragmentManager, actionBar, handler, title);
                        break;
                    case ACTION_SHOW_ALL:
                        fragment = new UsersFragment(dbManager, fragmentManager, actionBar, null, context, handler);
                        break;
                    case ACTION_SHOW_SPEC:
                        fragment = new UsersFragment(dbManager, fragmentManager, actionBar, (String) msg.obj, context, handler);
                        break;
                    case ACTION_SHOW_DETAIL:
                        fragment = new DetailFragment(dbManager, fragmentManager, actionBar, (demo.com.forummanagement.db.User) msg.obj);
                        break;
                    case ACTION_NEW_USER:
                        fragment = new NewUserFragment(dbManager, fragmentManager, actionBar);
                }
                replaseFragment(fragment, true);
                actionBar.setTitle(fragment.getFragmentIdentifier());
            } else {
                Toast.makeText(context, "数据正在准备中，请稍等", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager manager = getFragmentManager();
                if (manager.getBackStackEntryCount() > 1) {
                    manager.popBackStack();
                } else {
                    ForumManageApplication.getInstance().exit();
                }
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "~(○゜▽゜○)~", Toast.LENGTH_LONG).show();
        }
        return true;
    }

}
