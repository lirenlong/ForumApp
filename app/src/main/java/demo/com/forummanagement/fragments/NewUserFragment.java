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

import demo.com.forummanagement.R;
import demo.com.forummanagement.db.DBManager;
import demo.com.forummanagement.db.User;

@SuppressLint("ValidFragment")
public class NewUserFragment extends BaseFragment {

    private EditText name, gender, age, level, coin, intro;

    public NewUserFragment(DBManager dbManager, FragmentManager fragmentManager, ActionBar actionBar) {
        super(dbManager, fragmentManager, actionBar);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);

        name = (EditText) view.findViewById(R.id.new_user_name_text);
        gender = (EditText) view.findViewById(R.id.new_user_gender_txt);
        age = (EditText) view.findViewById(R.id.new_user_age_txt);
        level = (EditText) view.findViewById(R.id.new_user_level_txt);
        coin = (EditText) view.findViewById(R.id.new_user_coin_txt);
        intro = (EditText) view.findViewById(R.id.new_user_intro_txt);

        Button ok = (Button) view.findViewById(R.id.new_user_button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(
                        name.getText().toString(),
                        gender.getText().toString(),
                        Integer.valueOf(age.getText().toString()),
                        Integer.valueOf(level.getText().toString()),
                        Integer.valueOf(coin.getText().toString()),
                        intro.getText().toString()
                );
                dbManager.addUser(user);
            }
        });

        return view;
    }

    @Override
    public String getFragmentIdentifier() {
        return "新建用户";
    }
}
