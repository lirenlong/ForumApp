package demo.com.forummanagement.fragments;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import demo.com.forummanagement.MainActivity;
import demo.com.forummanagement.R;
import demo.com.forummanagement.db.DBManager;

@SuppressLint("ValidFragment")
public class OperationFragment extends BaseFragment {

    private Handler handler;
    private String title;

    public OperationFragment(DBManager dbManager, FragmentManager fragmentManager, ActionBar actionBar, Handler handler, String title) {
        super(dbManager, fragmentManager, actionBar);
        this.handler = handler;
        this.title = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);

        Button showAllButton = (Button) view.findViewById(R.id.op_button_showall);
        showAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(MainActivity.ACTION_SHOW_ALL);
            }
        });

        Button searchButton = (Button) view.findViewById(R.id.op_button_specok);
        final EditText userName = (EditText) view.findViewById(R.id.op_edittext_specusr);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = new Message();
                message.what = MainActivity.ACTION_SHOW_SPEC;
                message.obj = userName.getText().toString();
                handler.sendMessage(message);
            }
        });

        Button addButton = (Button) view.findViewById(R.id.op_button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(MainActivity.ACTION_NEW_USER);
            }
        });

        return view;
    }

    @Override
    public String getFragmentIdentifier() {
        return title;
    }

}
