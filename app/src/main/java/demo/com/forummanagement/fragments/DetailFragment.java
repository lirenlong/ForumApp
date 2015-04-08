package demo.com.forummanagement.fragments;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import demo.com.forummanagement.R;
import demo.com.forummanagement.db.DBManager;
import demo.com.forummanagement.db.User;

@SuppressLint("ValidFragment")
public class DetailFragment extends BaseFragment {

    private User currentUser;

    public DetailFragment(DBManager dbManager, FragmentManager fragmentManager, ActionBar actionBar, User user) {
        super(dbManager, fragmentManager, actionBar);
        this.currentUser = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        final TextView name = (TextView) view.findViewById(R.id.detail_name);
        final EditText gender = (EditText) view.findViewById(R.id.detail_gender_txt);
        final EditText age = (EditText) view.findViewById(R.id.detail_age_txt);
        final EditText level = (EditText) view.findViewById(R.id.detail_level_txt);
        final EditText coin = (EditText) view.findViewById(R.id.detail_coin_txt);
        final EditText intro = (EditText) view.findViewById(R.id.detail_intro_txt);

        name.setText("用户名: " + currentUser.getName());
        gender.setText(currentUser.getGender());
        level.setText(String.valueOf(currentUser.getLevel()));
        coin.setText(String.valueOf(currentUser.getCoin()));
        intro.setText(currentUser.getIntro());

        Button ok = (Button) view.findViewById(R.id.detail_button_ok);
        Button del = (Button) view.findViewById(R.id.detail_button_del);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.deleteUser(currentUser.getName());
                fragmentManager.popBackStack();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setAge(Integer.valueOf(age.getText().toString()));
                currentUser.setCoin(Integer.valueOf(coin.getText().toString()));
                currentUser.setLevel(Integer.valueOf(level.getText().toString()));
                currentUser.setIntro(intro.getText().toString());
                dbManager.updateInfo(currentUser);
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    @Override
    public String getFragmentIdentifier() {
        return "用户详情";
    }
}
