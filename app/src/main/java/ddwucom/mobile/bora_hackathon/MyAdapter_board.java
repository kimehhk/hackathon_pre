package ddwucom.mobile.bora_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter_board extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Board> myDataArrayList;
    private LayoutInflater layoutInflater;

    public MyAdapter_board(Context context, int layout, ArrayList<Board> myDataArrayList) {
        this.context = context;
        this.layout = layout;
        this.myDataArrayList = myDataArrayList;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        return myDataArrayList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ViewHolder holder;

        if(convertView == null) {
            convertView = layoutInflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.context = (TextView)convertView.findViewById(R.id.et_boardContext);
            holder.title = (TextView)convertView.findViewById(R.id.et_boardTitle);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.context.setText(myDataArrayList.get(pos).getContext());
        holder.title.setText((myDataArrayList.get(pos).getTitle()));

        return convertView;
    }

    static class ViewHolder {
        TextView context;
        TextView title;
    }
}
