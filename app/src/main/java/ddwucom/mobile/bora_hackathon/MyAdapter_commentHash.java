package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter_commentHash<dataList> extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Comment> myDataArrayList;
    private LayoutInflater layoutInflater;
    private HashMap<String, String> data;
    private ArrayList<HashMap<String, String>> dataList;
    private String id;

    public MyAdapter_commentHash(Context context, int layout, List<Comment> myDataArrayList) {
        this.context = context;
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MyAdapter_commentHash(Context context, int layout, HashMap<String, String> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        //this.id = data.get("comment_id");
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MyAdapter_commentHash(Context context, int layout, ArrayList<HashMap<String, String>> dataList) {
        this.context = context;
        this.layout = layout;
        this.dataList = dataList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(dataList.get(position).get("comment_id"));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.comment = (TextView)convertView.findViewById(R.id.comment);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        //holder.comment.setText(myDataArrayList.get(pos).getComment());
        holder.comment.setText(dataList.get(pos).get("content"));
        return convertView;
    }

    static class ViewHolder {
        TextView comment;
    }
}