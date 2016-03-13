package android.de.meetat.search;

import android.content.Context;
import android.de.meetat.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import util.SimpledummyData;

/**
 * Created by raina on 13.03.2016.
 */
public class ListViewAdapter extends BaseAdapter {
    private final Context context;
    List<SimpledummyData.Row> list = SimpledummyData.rows;

    ListViewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar_image_search);
            holder.name = (TextView) convertView.findViewById(R.id.name_id_search);
            holder.name.setText(list.get(position).getName());
            Picasso.with(context).load(list.get(position).getAvatar()).centerCrop().fit().into(holder.avatar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }



    public static class ViewHolder {
        ImageView avatar;
        TextView name;
    }

}
