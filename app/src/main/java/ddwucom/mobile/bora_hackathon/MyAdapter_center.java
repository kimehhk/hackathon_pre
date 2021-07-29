package ddwucom.mobile.bora_hackathon;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter_center extends BaseAdapter {
    final static String TAG = "BaseAdapter";
    private Context context;
    private int layout;
    private ArrayList<Center> myDataArrayList = new ArrayList<Center>();
    private LayoutInflater layoutInflater;

    public MyAdapter_center(Context context, int layout, ArrayList<Center> myDataArrayList) {
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
            holder.receptionContent = (TextView)convertView.findViewById(R.id.tv_receptionContent);
            holder.institution = (TextView)convertView.findViewById(R.id.tv_institution);
            holder.number = (TextView)convertView.findViewById(R.id.tv_number);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.receptionContent.setText(myDataArrayList.get(pos).getReceptionContent());
        holder.institution.setText(myDataArrayList.get(pos).getInstitution());
        holder.number.setText(myDataArrayList.get(pos).getNumber());

        holder.institution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = myDataArrayList.get(pos).getAddress();
                Log.d(TAG, "address :" + address);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                v.getContext().startActivity(intent);
            }
        });

        holder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String institutionNumber = myDataArrayList.get(pos).getNumber();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + institutionNumber));
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView receptionContent;
        TextView institution;
        TextView number;
    }
}
