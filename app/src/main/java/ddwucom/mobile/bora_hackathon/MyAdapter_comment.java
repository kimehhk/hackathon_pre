package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter_comment extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Comment> myDataArrayList;
    private LayoutInflater layoutInflater;

    public MyAdapter_comment(Context context, int layout, List<Comment> myDataArrayList) {
        this.context = context;
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public MyAdapter_comment(int layout, List<Comment> comments) {
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
    }

    @Override
    public int getCount() {
        return myDataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return myDataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return myDataArrayList.get(position).getComment_id();
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
        holder.comment.setText("hi");
        return convertView;
    }

    static class ViewHolder {
        TextView comment;
    }
}
