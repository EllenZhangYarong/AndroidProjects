package com.ellen.tasktwelvejokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ellen on 16/1/6.
 */
public class JokesAdapter extends BaseAdapter {

    private List<JokeBean> jokeBeanList;
    private LayoutInflater inflater;

    public JokesAdapter(Context context, List<JokeBean> data) {
        this.jokeBeanList = data;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return jokeBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return jokeBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_joke, null);

            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(jokeBeanList.get(position).getJokeTitle());
        viewHolder.tvDate.setText(jokeBeanList.get(position).getJokeDate());

        return convertView;
    }

    class ViewHolder {
        private TextView tvTitle, tvDate;

    }
}
