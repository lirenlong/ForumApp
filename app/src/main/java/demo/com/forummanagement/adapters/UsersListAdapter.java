package demo.com.forummanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import demo.com.forummanagement.R;
import demo.com.forummanagement.db.User;

/**
 * Created by MS on 2015/4/5.
 */
public class UsersListAdapter extends BaseAdapter {

    private List<User> users;
    private Context context;

    public UsersListAdapter(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.item_list_users, null);
            viewHolder.userName = (TextView) convertView.findViewById(R.id.item_users_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.userName.setText(users.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        TextView userName;
    }
}
