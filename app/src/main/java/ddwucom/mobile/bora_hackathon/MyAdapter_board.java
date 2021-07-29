package ddwucom.mobile.bora_hackathon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_board extends RecyclerView.Adapter<MyAdapter_board.CustomViewHolder> {
    private ArrayList<Board> bList = null;
    private Activity context = null;
    private AdapterView.OnItemClickListener listener = null;

    public MyAdapter_board(Activity context, ArrayList<Board> list) {
        this.context = context;
        this.bList = list;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView content;
        //protected TextView date;

        public CustomViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.boardTitle);
            this.content = view.findViewById(R.id.boardContent);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        //listener.onItemClick(CustomViewHolder.this, v, pos, getItemId());
                    }
                }
            });
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_adapter_view, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view) ;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewHolder, int position) {
        viewHolder.title.setText(bList.get(position).getTitle());
        viewHolder.content.setText(bList.get(position).getContext());
    }

    @Override
    public int getItemCount() {
        return (null != bList ? bList.size() : 0);
    }

}