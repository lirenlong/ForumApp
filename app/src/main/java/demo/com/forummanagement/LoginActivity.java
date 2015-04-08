package demo.com.forummanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {

    private Button confirmButton;
    private EditText usrEdittext, pwdEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ForumManageApplication.getInstance().addActivity(this);

        confirmButton = (Button) findViewById(R.id.login_button_confirm);
        usrEdittext = (EditText) findViewById(R.id.login_edittext_usr);
        pwdEdittext = (EditText) findViewById(R.id.login_edittext_pwd);

        final Context activityContext = this;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usr = usrEdittext.getText().toString();
                String pwd = pwdEdittext.getText().toString();

                if (usr != null && !usr.equals("")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("usr", usr);
                    startActivity(intent);
                } else {
                    Toast.makeText(activityContext, "用户名或密码错误，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
