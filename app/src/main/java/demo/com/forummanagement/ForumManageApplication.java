package demo.com.forummanagement;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class ForumManageApplication extends Application {

    private List<Activity> activityList = new LinkedList<>();

    private static ForumManageApplication instance;

    private ForumManageApplication() {
    }

    public static ForumManageApplication getInstance() {
        if (null == instance) {
            instance = new ForumManageApplication();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

}
